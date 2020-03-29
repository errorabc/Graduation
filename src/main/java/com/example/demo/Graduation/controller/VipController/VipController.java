package com.example.demo.Graduation.controller.VipController;

import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.example.demo.Graduation.service.VipService.VipService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/VipInfo")
public class VipController {
    @Autowired
    private VipService vipService;


    @RequestMapping("")
    public String GetVipInfoList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, VipinfoEntity vipinfoEntity) {
        PageInfo<VipinfoEntity> viplist = vipService.FindAllVipInfo(pageNo, pageSize, vipinfoEntity.getName());
        model.addAttribute("viplist", viplist);
        model.addAttribute("vipname", vipinfoEntity.getName());
        return "Vip/viplist";
    }

    //跳转到添加VIP界面
    @RequestMapping("/GetAddVip")
    public String GetAddVip() {
        return "Vip/vipadd";
    }

    //跳转到修改VIP界面
    @RequestMapping("/GetUpdateVip")
    public String GetUpdateVip(@Param("id") String id, Model model) {
        VipinfoEntity vipinfoEntity = vipService.IdFindVipInfo(id);
        model.addAttribute("vipinfo", vipinfoEntity);
        return "Vip/vipupdate";
    }

    //添加VIP
    @PostMapping(value = "/AddVip")
    @ResponseBody
    public Result AddVip(VipinfoEntity vipinfoEntity) {
        Result result = vipService.AddVip(vipinfoEntity);
        return result;
    }

    //修改VIP信息
    @PostMapping(value = "/UpdateVip")
    @ResponseBody
    public Result UpdateVip(VipinfoEntity vipinfoEntity) {
        Result result = vipService.UpdateVip(vipinfoEntity);
        return result;
    }


    //查询vip名字是否重复
    @PostMapping(value = "/VerificationVipName")
    @ResponseBody
    public Result VerificationVipName(@RequestParam("name") String name) {
        Result result = vipService.VerificationVipName(name);
        return result;
    }


}
