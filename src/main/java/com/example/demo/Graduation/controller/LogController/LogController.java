package com.example.demo.Graduation.controller.LogController;

import com.example.demo.Graduation.entity.LogEntity.LogEntity;
import com.example.demo.Graduation.service.LogService.LogService;
import com.github.pagehelper.PageInfo;
import javafx.application.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/syslog")
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    //跳转到日志管理界面
    @RequestMapping(value = "")
    public String syslog(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, LogEntity logEntity) {
        PageInfo<LogEntity> logEntityList = logService.FindSysLog(pageNo, pageSize, logEntity.getStarttime(), logEntity.getEndtime(), logEntity.getUsername());
        System.out.println("进来了");
        model.addAttribute("loglist", logEntityList);
        model.addAttribute("logEntity", logEntity);//查询条件回显
        return "SysLog/syslog";
    }

    @RequestMapping(value = "/detailslog")
    public String detailslog(Model model, @Param("id") String id) {
        LogEntity logEntity = logService.IdFindLog(id);
        model.addAttribute("loglist", logEntity);
        return "SysLog/detailslog";
    }


}
