package com.jpp.webservice.web.comm.aop.logging;

import com.jpp.webservice.web.comm.aop.logging.vo.ReqResInfo;

public interface LogPrinter {
	public void printReqLog(ReqResInfo reqResInfo);
	public void printResLog(ReqResInfo reqResInfo, Object proceedingJoinPointReturnValue);
}
