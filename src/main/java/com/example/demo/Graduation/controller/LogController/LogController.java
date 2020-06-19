package com.example.demo.Graduation.controller.LogController;

import com.example.demo.Graduation.entity.LogEntity;
import com.example.demo.Graduation.service.LogService.LogService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//操作日志
@RequestMapping(value = "/syslog")
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    //跳转到日志管理界面
    @RequestMapping(value = "")
    public String syslog(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, LogEntity logEntity) {
        PageInfo<LogEntity> logEntityList = logService.FindSysLog(pageNo, pageSize, logEntity.getStarttime(), logEntity.getEndtime(), logEntity.getUsername());
        model.addAttribute("loglist", logEntityList);
        model.addAttribute("logEntity", logEntity);//查询条件回显
        return "SysLog/syslog";
    }

    //日志详情
    @RequestMapping(value = "/detailslog")
    public String detailslog(Model model, @Param("id") String id) {
        LogEntity logEntity = logService.IdFindLog(id);
        model.addAttribute("loglist", logEntity);
        return "SysLog/detailslog";
    }


}
