package com.powernode.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirstController {
    @RequestMapping(value = "/test")
    public String hehe(){
        return "first";
    }

    @RequestMapping(value = "/heihei")
    public String heihei(){
        return "other";
    }

    @RequestMapping( "/")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/login/{username}/{password}")
    public String testRESTFulURL(@PathVariable("username")String username,
                                 @PathVariable("password")String password)
    {
        System.out.println("用户名："+username+"密码："+password);
        return "ok";
    }

    //当前端发送的请求路径是/user/login，请求方式是post，则可以正常映射
    //@RequestMapping(value = "/user/login",method={RequestMethod.POST})
    @PostMapping("/user/login")
    public String userLogin(){
        System.out.println("处理登录的请求。。。");
        return "ok";
    }
}
