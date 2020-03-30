package com.jason.advice;

import com.jason.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    String handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {

        if (e instanceof CustomizeException) {
            model.addAttribute("msg", e.getMessage());
        } else {
            log.error("handle error", e);
            model.addAttribute("msg", e.getMessage());
        }

        model.addAttribute("exception", e);
        return "site/error";
    }
}
