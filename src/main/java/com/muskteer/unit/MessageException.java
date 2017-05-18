/**
 * FileName:   MessageException.java
 * @Description EXCEPTION
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.unit;

public class MessageException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long exceptionCode;
    private String message;
    public long getExceptionCode() {
        return exceptionCode;
    }
    public void setExceptionCode(long exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public MessageException(long exceptionCode, String message) {
        super();
        this.exceptionCode = exceptionCode;
        this.message = message;
    }
    public MessageException(String message) {
        super();
        this.message = message;
    }
    

}
