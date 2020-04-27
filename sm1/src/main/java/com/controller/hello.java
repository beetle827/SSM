package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hello {

    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello");
        return "success";
    }
}
