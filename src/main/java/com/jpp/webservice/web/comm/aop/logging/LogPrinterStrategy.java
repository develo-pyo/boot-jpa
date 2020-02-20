package com.jpp.webservice.web.comm.aop.logging;

import com.jpp.webservice.web.comm.aop.logging.vo.ReqResInfo;

/** strategy pattern 이 적용된 log printer */
public class LogPrinterStrategy {
	
	private ReqResInfo reqResInfo = null;
	private LogPrinter printLogI = null;
	
	/** Constructor
	 *  ResResInfo : 요청/응답 정보 객체
	 *  LogPrinter : log 출력을 담당할 log printer 객체
	 *  */
	public LogPrinterStrategy(ReqResInfo reqResInfo, LogPrinter printLogI) {
		this.reqResInfo = reqResInfo;
		this.printLogI = printLogI;
	}
	
	/** 요청 로그 출력 */
	public void printReqLog() {
		printLogI.printReqLog(reqResInfo);
	}
	
	/** 응답 로그 출력 */
	public void printResLog(Object proceedingJoinPointReturnValue) {
		printLogI.printResLog(reqResInfo, proceedingJoinPointReturnValue);
	}
	
}
