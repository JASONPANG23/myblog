package com.jason.aspect;

import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

@Aspect
public class Markdown2HtmlConverter {

    @Pointcut("execution(public * com.jason.service.ArticleService.getArticles(..))")
    public void webLog(){}

    @AfterReturning(returning = "result",value = "webLog()")
    public void afterReturning(Object result){
        if(result instanceof List){
            System.out.println("list");
        }else if (result instanceof PageInfo){
            System.out.println("list");
        }
    }

}
