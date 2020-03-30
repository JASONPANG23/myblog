package com.jason.model.vo;

import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;

public class ResultVO<T>{

    private Integer code;
    private String message ;
    private T data ;

    public ResultVO(Integer code,String message,T data){
        this.code = code ;
        this.message = message ;
        this.data = data ;
    }

    public ResultVO(){}


    public static <T> ResultVO<T>  ok(T data){
        ResultVO<T> result = new ResultVO<T>();
        result.data = data ;
        result.message = "ok" ;
        result.code = 200 ;
        return result ;
    }

    public static <T> ResultVO<T> ok(){
        ResultVO<T> result = new ResultVO<T>();
        result.message = "ok" ;
        result.code = 200 ;
        return result ;
    }

    public static <T> ResultVO<T> errorOf(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage()) ;
    }

    public static <T> ResultVO<T>  errorOf(Integer code, String message) {
        ResultVO<T> result = new ResultVO<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResultVO<T> errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
