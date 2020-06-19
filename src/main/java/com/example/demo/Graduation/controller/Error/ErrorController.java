package com.example.demo.Graduation.controller.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
    @RequestMapping(value = "/500")
    public String error500() throws Exception {
        System.out.println("这是500");
        return "Error/500";
    }

    @RequestMapping(value = "/404")
    public String error404() throws Exception {
        System.out.println("这是404");
        return "Error/404";
    }

}
