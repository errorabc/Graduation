package com.example.demo.Graduation.controller.PetController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.Tool.SendSms;
import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.PetfosterEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.MemberSerice;
import com.example.demo.Graduation.service.PetService.PetFosterService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

//宠物寄养管理
@Controller
@RequestMapping(value = "/petfoster")
public class PetFosterController {
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private SendSms sendSms;


    @RequestMapping(value = "")
    public String petfosterlist(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, PetfosterEntity petfosterEntity) {
        PageInfo<PetfosterEntity> petfosterlist = petFosterService.FindAllInfo(pageNo, pageSize, petfosterEntity.getFeedername(), petfosterEntity.getFeederphone());
        model.addAttribute("petfosterlist", petfosterlist);
        model.addAttribute("feedername", petfosterEntity.getFeedername());
        model.addAttribute("feederphone", petfosterEntity.getFeederphone());
        return "Petfoster/petfosterlist";
    }

    //跳转到添加宠物寄养界面
    @GetMapping(value = "/GetAddPetFoster")
    public String GetAddPetFoster() {
        return "Petfoster/petfosteradd";
    }

    //添加寄养
    @LogAop("添加宠物寄养信息")
    @RequiresPermissions("petfoster:add")
    @PostMapping(value = "/AddPetFoster")
    @ResponseBody
    public Result AddPetFoster(PetfosterEntity petfosterEntity, @RequestParam("endtime") String endtime) {
        Result result = null;
        try {
            petfosterEntity.setFosterendtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime));
            result = petFosterService.AddPetFoster(petfosterEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //跳转到详情界面
    @RequestMapping(value = "/GetDetailsPetFoster")
    public String Details(@RequestParam("id") String id, Model model) {
        PetfosterEntity petfosterEntity = petFosterService.IdFindPetFosterinfo(id);
        model.addAttribute("petfoster", petfosterEntity);
        return "Petfoster/petfosterdetails";
    }

    //删除
    @LogAop("删除宠物寄养信息")
    @RequiresPermissions("petfoster:delete")
    @PostMapping(value = "/DeletePetFoster")
    @ResponseBody
    public Result DeletePetFoster(@RequestParam("id") String id) {
        Result result = petFosterService.DeletePetFoster(id);
        return result;
    }


    //跳转到修改界面
    @RequestMapping(value = "GetUpdatePetFoster")
    public String GetUpdatePetFoster(@RequestParam("id") String id, Model model) {
        PetfosterEntity petfosterEntity = petFosterService.IdFindPetFosterinfo(id);
        model.addAttribute("petfoster", petfosterEntity);
        return "Petfoster/petfosterupdate";
    }


    //修改寄养信息
    @LogAop("修改宠物寄养信息")
    @RequiresPermissions("petfoster:update")
    @PostMapping(value = "/UpdatePetFoster")
    @ResponseBody
    public Result UpdatePetFoster(PetfosterEntity petfosterEntity, @RequestParam("endtime") String endtime, @RequestParam("starttime") String starttime) {
        try {
            petfosterEntity.setFosterstartime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starttime));
            petfosterEntity.setFosterendtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endtime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = petFosterService.UpdatePetFoster(petfosterEntity);
        return result;
    }


    //结束寄养
    @LogAop("结束宠物寄养")
    @RequiresPermissions("petfoster:stop")
    @PostMapping(value = "/StopFoster")
    @ResponseBody
    public Result StopFoster(@RequestParam("id") String id) {
        Result result = petFosterService.StopFoster(id);
        return result;
    }


    //发送短信
    @LogAop("发送短信")
    @RequiresPermissions("petfoster:sendsms")
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public Result SendSms(@RequestParam("phone")String phone) {
        Result result = sendSms.TxSendMessage(phone);
        return result;
    }


}
