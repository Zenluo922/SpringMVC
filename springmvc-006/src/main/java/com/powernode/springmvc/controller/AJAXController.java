package com.powernode.springmvc.controller;

import com.powernode.springmvc.bean.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;


@RestController
//@Controller
public class AJAXController {
    /*@RequestMapping(value = "/ajax",method = RequestMethod.GET)
    public String ajax(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("Hello ajax,my name is SpringMVC");
        return null;
    }*/

    @GetMapping("/ajax")
    //@ResponseBody
    public User ajax(){
        User user = new User(1001L,"张无忌","123");
        return user;
    }

}
