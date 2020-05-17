package com.example.demo.Graduation.controller.SysController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sysnotice")
public class SysNoticeController {

    @RequestMapping(value = "")
    public String GetSysNotice() {
        return "SysNotice/sysnotice";
    }
}
