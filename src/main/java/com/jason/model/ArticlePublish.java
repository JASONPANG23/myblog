package com.jason.model;

public enum ArticlePublish {
    All(-1),
    UN_PUBLISH(0) ,
    PUBLISH(1);

    private Integer code ;
    ArticlePublish(Integer code){
        this.code = code ;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
