package com.powernode.springmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    public String tip(Exception e, Model model){
        model.addAttribute("yiChang",e);
        return "tip";
    }
}
