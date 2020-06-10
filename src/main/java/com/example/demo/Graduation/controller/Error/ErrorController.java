package com.example.demo.Graduation.controller.Error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
    @RequestMapping(value = "/500")
    public String error500() throws Exception {
        return "Error/500";
    }
}
