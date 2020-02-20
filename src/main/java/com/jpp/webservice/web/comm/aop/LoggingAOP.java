package com.jpp.webservice.web.comm.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jpp.webservice.web.comm.aop.logging.LogPrinterConsole;
import com.jpp.webservice.web.comm.aop.logging.LogPrinterFile;
import com.jpp.webservice.web.comm.aop.logging.LogPrinterStrategy;
import com.jpp.webservice.web.comm.aop.logging.vo.ReqResInfo;
import com.jpp.webservice.web.comm.constants.CustomException;
import com.jpp.webservice.web.controller.UserController;

@Component
@Aspect
public class LoggingAOP {

	@Around("execution(public * com.jpp.webservice..*Controller.*(..))"
			+ "&& !@annotation(com.jpp.webservice.web.comm.aop.logging.NoLogging)")
	public Object objAround(ProceedingJoinPoint pjp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		ReqResInfo reqResInfo = new ReqResInfo(pjp, request);
		LogPrinterStrategy logPrinter = new LogPrinterStrategy(reqResInfo, new LogPrinterConsole());
		
		logPrinter.printReqLog();
		
		Object pjpObj = null;
		Map<String, Object> resParam = null;
		
		try {
			pjpObj = pjp.proceed();
			return pjpObj;
		} catch (CustomException ce) {
		   reqResInfo.setSuccess(false);
		   
		   resParam = new HashMap<String, Object>();
		   resParam.put("error_msg", ce.getErrorDesc());
		   
			pjpObj = new ResponseEntity<>(resParam, ce.getHttpStatus());
		} catch (Throwable e) {
			reqResInfo.setSuccess(false);
			resParam = new HashMap<String, Object>();
			
         resParam.put("error_msg", HttpStatus.INTERNAL_SERVER_ERROR);
			
			pjpObj = new ResponseEntity<>(resParam, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			logPrinter.printResLog(pjpObj);
			LogPrinterStrategy logPrinterFile = new LogPrinterStrategy(reqResInfo, new LogPrinterFile());
			logPrinterFile.printResLog(pjpObj);
		}
		
		return pjpObj;
	}

	
}
