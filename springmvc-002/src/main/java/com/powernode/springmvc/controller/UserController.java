package com.powernode.springmvc.controller;

import com.powernode.springmvc.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class UserController {
    @RequestMapping("/")
    public String toRegister(){
        return "register";
    }



    @PostMapping("/user/reg")
    public String register(User user){
        System.out.println(user);
        return "ok";
    }
    //@RequestMapping(value = "/user/reg" ,method = RequestMethod.POST)
    /*@PostMapping("/user/reg")
    public String register(
            @RequestParam(name = "username")
            String a,
            @RequestParam("password")
            String password,
            @RequestParam("sex")
            String sex,
            @RequestParam("interest")
            String[] interests,
            @RequestParam("intro")
            String intro
    ){


        System.out.println(a);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interests));
        System.out.println(intro);

        return "ok";
    }*/
    /*public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] interests = request.getParameterValues("interest");
        String intro = request.getParameter("intro");

        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interests));
        System.out.println(intro);

        return "ok";
    }*/
}
