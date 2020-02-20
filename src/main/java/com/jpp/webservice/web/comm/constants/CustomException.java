package com.jpp.webservice.web.comm.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -4848357483839823542L;

    private HttpStatus httpStatus;
    
    public CustomException(int code, String errDesc) {
        super(errDesc);
        httpStatus = HttpStatus.valueOf(code);
    }
    
    public CustomException(HttpStatus httpStatus, String errDesc){
        super(errDesc);
        this.httpStatus = httpStatus;
    }
    
    public CustomException(HttpStatus httpStatus){
        super(httpStatus.getReasonPhrase());
        this.httpStatus = httpStatus;
    }
    
    public String getErrorDesc() {
        return this.getMessage();
    }
    
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
    
}
