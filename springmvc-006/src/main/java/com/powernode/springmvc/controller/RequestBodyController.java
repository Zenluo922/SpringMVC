package com.powernode.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RequestBodyController {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody String requestBodyStr){
        return "ok";
    }
}
