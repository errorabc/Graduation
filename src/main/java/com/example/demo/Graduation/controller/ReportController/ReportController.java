package com.example.demo.Graduation.controller.ReportController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/Report")
public class ReportController {

    @RequestMapping(value = "")
    public String GetReportlist() {
        return "Report/ReportEcharts";
    }
}
