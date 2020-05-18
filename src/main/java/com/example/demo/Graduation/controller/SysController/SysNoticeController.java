package com.example.demo.Graduation.controller.SysController;

import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.SysNoticeEntity;
import com.example.demo.Graduation.service.SysNoticeService.SysNoticeService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/sysnotice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping(value = "")
    public String GetSysNotice(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "name", defaultValue = "") String name) {
        PageInfo<SysNoticeEntity> sysnoticelist = sysNoticeService.FindAllSysNoticeInfo(pageNo, pageSize, name);
        model.addAttribute("sysnoticelist", sysnoticelist);
        model.addAttribute("name", name);
        return "SysNotice/sysnoticelist";
    }

    //挑战到添加界面
    @GetMapping(value = "/GetAddNotice")
    public String GetAddSnotice() {
        return "SysNotice/addsysnotice";
    }


    //添加
    @PostMapping(value = "/AddNotice")
    @ResponseBody
    public Result AddSnotice(SysNoticeEntity sysNoticeEntity) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        Result result = sysNoticeService.AddSnotice(sysNoticeEntity);
        return result;
    }

    //跳转到详情界面
    @GetMapping(value = "/GetDetails")
    public String GetDetails(@RequestParam("id") String id, Model model) {
        SysNoticeEntity sysNoticeEntity = sysNoticeService.IdFindSysNoticeInfo(id);
        model.addAttribute("notice", sysNoticeEntity);
        return "SysNotice/deetails";
    }


    //跳转到修改界面
    @GetMapping(value = "/GetUpdateNotice")
    public String GetUpdateNotice(@RequestParam("id") String id, Model model) {
        SysNoticeEntity sysNoticeEntity = sysNoticeService.IdFindSysNoticeInfo(id);
        model.addAttribute("notice", sysNoticeEntity);
        return "SysNotice/updatenotice";
    }

    //修改
    @PostMapping(value = "/UpdateNotice")
    @ResponseBody
    public Result UpdateNotice(SysNoticeEntity sysNoticeEntity) {
        Result result = sysNoticeService.UpdateNotice(sysNoticeEntity);
        return result;
    }

    //删除
    @PostMapping(value = "/DeleteNotice")
    @ResponseBody
    public Result DeleteNotice(@RequestParam("id") String id) {
        Result result = sysNoticeService.DeleteNotice(id);
        return result;
    }
}
