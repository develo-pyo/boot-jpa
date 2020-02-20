package com.jpp.webservice.web.comm.aop.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpp.webservice.web.comm.aop.logging.vo.ReqResInfo;

public class LogPrinterFile implements LogPrinter {
	
	protected static final Logger logger = LoggerFactory.getLogger("fileLogger");
	
	@Override
	public void printReqLog(ReqResInfo reqResInfo) {
		this.printLogOnFile(reqResInfo);
	}
	
	@Override
	public void printResLog(ReqResInfo reqResInfo, Object proceedingJoinPointReturnValue) {
		this.printLogOnFile(reqResInfo);
	}
	
	private void printLogOnFile(ReqResInfo reqResInfo) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);
		String date = sd.format(new Date());
		
		logger.info("[{}],METHOD_NM:{},EXEC_TIME:{}", date, reqResInfo.getPjp().getSignature().getName()
				, reqResInfo.isSuccess()?System.currentTimeMillis()-reqResInfo.getStartTime():-1);
	}
}
