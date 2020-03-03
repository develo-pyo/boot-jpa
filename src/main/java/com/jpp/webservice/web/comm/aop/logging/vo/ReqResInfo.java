package com.jpp.webservice.web.comm.aop.logging.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpp.webservice.web.comm.aop.logging.vo.LogVO.LOG_MSG;
import com.jpp.webservice.web.comm.utils.CommUtils;

/** request, response 파라미터를 추출 및 가공하는 class */
public class ReqResInfo {
	
	protected static final Logger logger = LoggerFactory.getLogger(ReqResInfo.class);
	protected static final Logger FILE_LOGGER = LoggerFactory.getLogger("fileLogger");
	
	private long startTime;
	private HttpServletRequest request;
	private ProceedingJoinPoint pjp;
	private String controller;
	private String path;
	private String addr;
	private int port;
	private String said = "";
	private boolean isSuccess = false;
	
	protected String requestParamStr = "";
	protected String responseParamStr = null;
	
	/** Constructor
	 *  1. request시작 시각 저장
	 *  2. requestParameter 정보 저장
	 *  3. HttpServletRequest 객체내에 존재하는 정보 저장
	 *  */
	public ReqResInfo(ProceedingJoinPoint pjp, HttpServletRequest request){
		this.pjp = pjp;
		this.request = request;
		retrieveRequestInfo();
		retrieveRequestParam();
		setStartTime();
	}
	
	/** request 시작 시각 set */
	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}
	
	/** HttpServletRequest 객체내에 존재하는 정보 set */
	private void retrieveRequestInfo() {
		this.controller = (String) pjp.getTarget().getClass().getSimpleName();
		this.path = request.getRequestURI();
		this.addr = request.getRemoteAddr();
		this.port = request.getRemotePort();
	}
	
	/** requestParameter 정보 저장 */
	@SuppressWarnings("unchecked")
	private void retrieveRequestParam() {
		
		boolean isXmlType = false;
		Map<String, Object> tmpMap = null;
		
		try {
			//args 에 String, Map 등의 input parameter가 여러개 존재하지 않아야 함
		   if(pjp.getArgs() != null) {
		      tmpMap = new HashMap<String, Object>();
		      Set<Entry<String, Object>> entrySet = null;
		      
   			for (Object obj : pjp.getArgs()) {
   				if (obj instanceof Map) {
   					entrySet = ((Map<String, Object>) obj).entrySet();
   				}  else if (obj instanceof String){
   					if("application/xml".equalsIgnoreCase(request.getHeader("Content-Type"))) {
   						isXmlType = true;
   						requestParamStr = obj.toString();
   					} else {
   						Map<String, Object> tmpMap2 = new ObjectMapper().readValue((String)obj, Map.class);
   						entrySet = tmpMap2.entrySet();
   					}
   				}
   			}
   			//value 가 1500자 이상인 경우 원래의 value 대신 길이초과 로그메시지 출력
   			if(entrySet != null) {
      			for(Map.Entry<String, Object> entry : entrySet){
      				if(CommUtils.toStr(entry.getValue()).length() < 1500){
      					tmpMap.put(entry.getKey(), entry.getValue());
      				} else {
      					tmpMap.put(entry.getKey(), LOG_MSG.CANNOT_PRINT_VALUE.getMsg());
      				}
      			}
   			}
		   }
		} catch (JsonParseException e) {
			logger.debug("exception during parsing input param : {}", e);
		} catch (Exception e) {
			logger.debug("exception during parsing input param : {}", e);
		}
		
		if(!isXmlType) {
			requestParamStr = tmpMap!=null?tmpMap.toString():"";
		}
	}
	
	/** responseParameter 정보 저장 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setResponseInfo(Object proceedingJoinPointReturnValue) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		Set<Entry<String, Object>> entrySet = null;
		
		try {
			if(proceedingJoinPointReturnValue instanceof ModelAndView){
				ModelAndView mav = (ModelAndView) proceedingJoinPointReturnValue;
				entrySet = mav.getModel().entrySet();
			} else if (proceedingJoinPointReturnValue instanceof String) {
				Map<String, Object> tmpMap2 = new ObjectMapper().readValue((String)proceedingJoinPointReturnValue, Map.class);
				entrySet = tmpMap2.entrySet();
			} else if (proceedingJoinPointReturnValue instanceof Map) {
				Map<String, Object> tmpMap2 = (Map<String, Object>)proceedingJoinPointReturnValue;
				entrySet = tmpMap2.entrySet();
			} else if (proceedingJoinPointReturnValue instanceof ResponseEntity) {
			   ResponseEntity re = (ResponseEntity) proceedingJoinPointReturnValue;
			   
			   retrieveSuccess(re.getStatusCode().value());
			   
			   Object body = re.getBody();
			   if(body != null) {
   			   if(body instanceof Map) {
   			      Map tmpMap2 = (Map) body;
   			      entrySet = tmpMap2.entrySet();
   			   } else if (body instanceof String) {
   			      Map tmpMap2 = new ObjectMapper().readValue((String)body, Map.class);
   			      entrySet = tmpMap2.entrySet();
   			   }
			   }
			}
			
			//value 가 1500자 이상인 경우 원래의 value 대신 길이초과 로그메시지 출력
			if(entrySet != null) {
   			for(Map.Entry<String, Object> entry : entrySet){
   				if(CommUtils.toStr(entry.getValue()).length() < 1500){
   					tmpMap.put(entry.getKey(), entry.getValue());
   				} else {
   					tmpMap.put(entry.getKey(), LOG_MSG.CANNOT_PRINT_VALUE.getMsg());
   				}
   			}
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
			logger.debug("exception during parsing input param : {}", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("exception during parsing input param : {}", e);
		}
		
		responseParamStr = tmpMap.toString();
	}
	
	/** controller 로직 수행 후 성공/실패 여부 저장 */
	private void retrieveSuccess(int httpCd) {
	   if(httpCd > 199 && httpCd < 300) {
	      isSuccess = true;
	   }
	}
	
	/** 요청에 따른 로직 수행 성공/실패 여부 저장 
	 * : exception 에 의한 실패시 성공/실패 코드값을 강제로 실패상태로 만들기 위함 
	 * */
	public void setSuccess(boolean success) {
		this.isSuccess = success; 
	}

	public long getStartTime() {
		return startTime;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public ProceedingJoinPoint getPjp() {
		return pjp;
	}

	public String getController() {
		return controller;
	}

	public String getPath() {
		return path;
	}

	public String getAddr() {
		return addr;
	}

	public int getPort() {
		return port;
	}

	public String getSaid() {
		return said;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getResponseParamStr() {
		return responseParamStr;
	}

	public String getRequestParamStr() {
		return requestParamStr;
	}
	
	
}
