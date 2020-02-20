package com.jpp.webservice.web.comm.aop.logging.vo;

public class LogVO {
	
	/** 로그 출력 메시지 (parameter정보) */
	public static enum LOG_MSG {
		CANNOT_PRINT_INPARAM("{IN_PARAMS LENGTH IS TOO LONG TO PRINT}"),
		CANNOT_PRINT_OUTPARAM("{OUT_PARAMS LENGTH IS TOO LONG TO PRINT}"),
		CANNOT_PRINT_VALUE("VALUE LENGTH IS TOO LONG TO PRINT"),
		NOTHING_TO_PRINT("RETURN TYPE IS VOID");
                
        private String msg;
        
        private LOG_MSG(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
	
	/** 로그 출력 메시지 (성공/실패 정보) */
	public static enum LOG_RESULT_MSG {
		SUCCESS("success"),
		FAIL("fail");
                
        private String msg;
        
        private LOG_RESULT_MSG(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
