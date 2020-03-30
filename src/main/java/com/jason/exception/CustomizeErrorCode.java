package com.jason.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    ARTICLE_NOT_FOUND(2001,"该文章不存在"),
    CATEGORY_NOT_FOUND(2002,"该分类不存在"),
    TAG_NOT_FOUND(2003,"该标签不存在") ,
    SYS_ERROR(2004, "服务冒烟了，要不然你稍后再试试"),
    PARSE_TAG_ERROR(2005,"解析Tag出错"),
    ILLEGAL_ERROR(2006,"参数不合法")
    ;

    private Integer code;
    private String messag;

    CustomizeErrorCode(Integer code,String message){
        this.code = code ;
        this.messag = message ;
    }

    @Override
    public String getMessage() {
        return messag;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
