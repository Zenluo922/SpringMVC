package com.powernode.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestScopeTestController {
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","用model接口完成request数据共享");
        return "ok";
    }
}
