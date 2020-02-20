package com.jpp.webservice.web.comm.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.jpp.webservice.web.comm.constants.CustomException;

public class CommUtils {
	
   //validation check request parameters
	public static boolean paramValidate(String[] params, Map<String, Object> inParam) {
		boolean _result = true;
		
		try {
			for (String item : params) {
				
				if(!inParam.containsKey(item)) {
				   throw new CustomException(HttpStatus.BAD_REQUEST, String.format("%s not exist", item));
				}
				
				if(String.valueOf(inParam.get(item)).isEmpty()) {
					throw new CustomException(HttpStatus.BAD_REQUEST, String.format("%s is null", item));
				}
				
			}
		} catch(CustomException e) {
			throw e;
		} catch(Exception e) {
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid request parameter");
		}
		
		return _result;
	}
	
	
   //req server IP get
   public static String getIp(HttpServletRequest request){
      String result = null;
      	
      result = request.getHeader("X-Forwarded-For");
      
      if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
         result = request.getHeader("Proxy-Client-IP");
      }
      if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
         result = request.getHeader("WL-Proxy-Client-IP");
      }
      if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
         result = request.getHeader("HTTP_CLIENT_IP");
      }
      if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
         result = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
      	result = request.getRemoteAddr();
      }
      
      return result==null?"":result; 
   }
 
   
   public static String toStr(Object input) {
      String rs = "";
      try {
         rs = input.toString();
      } catch (Exception e) {
      }
      return rs;
   }
}
