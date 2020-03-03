package com.jpp.webservice.web.comm.aop.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpp.webservice.web.comm.aop.logging.vo.LogVO.LOG_MSG;
import com.jpp.webservice.web.comm.aop.logging.vo.LogVO.LOG_RESULT_MSG;
import com.jpp.webservice.web.comm.aop.logging.vo.ReqResInfo;

public class LogPrinterConsole implements LogPrinter {
	
	protected static final Logger logger = LoggerFactory.getLogger(LogPrinterConsole.class);
	
	/** 요청 로그 출력 */
	@Override
	public void printReqLog(ReqResInfo reqResInfo) {
		
		try {
		   String inputParam = reqResInfo.getRequestParamStr();
   		logger.info("REQUEST|CONTROLLER:{}|METHOD:{}|REMOTEADDR:{}|PORT:{}|IN_PARAMS:{}",
   				reqResInfo.getController(), reqResInfo.getPath(), reqResInfo.getAddr(), reqResInfo.getPort()
   				, inputParam == null ? "": (inputParam.length()>=1500?LOG_MSG.CANNOT_PRINT_INPARAM.getMsg():inputParam));
		}catch(Exception e) {
		   logger.error(e.getMessage());
		}
	}

	/** 응답 로그 출력 */
	@Override
	public void printResLog(ReqResInfo reqResInfo, Object proceedingJoinPointReturnValue) {
		
		try {
		   reqResInfo.setResponseInfo(proceedingJoinPointReturnValue);
		   
		   String inputParam = reqResInfo.getRequestParamStr();
		   String outParam = reqResInfo.getResponseParamStr();
		   logger.info("RESPONSE|CONTROLLER:{}|METHOD:{}|RESULT:{}|REMOTEADDR:{}|PORT:{}|TIME:{}|IN_PARAMS:{}|OUT_PARAMS:{}",
		         reqResInfo.getController(), reqResInfo.getPath()
		         , reqResInfo.isSuccess()?LOG_RESULT_MSG.SUCCESS.getMsg():LOG_RESULT_MSG.FAIL.getMsg()
		               , reqResInfo.getAddr(), reqResInfo.getPort()
		               , System.currentTimeMillis() - reqResInfo.getStartTime()
		               , inputParam==null?"":(inputParam.length()>=1500?LOG_MSG.CANNOT_PRINT_INPARAM.getMsg():inputParam)
		                     , outParam==null?"":(outParam.length()>=1500?LOG_MSG.CANNOT_PRINT_OUTPARAM.getMsg():outParam));
		   
      }catch(Exception e) {
         logger.error(e.getMessage());
      }
	}
	
}
