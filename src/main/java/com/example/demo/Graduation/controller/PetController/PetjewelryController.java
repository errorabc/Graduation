package com.example.demo.Graduation.controller.PetController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.Activity;
import com.example.demo.Graduation.entity.PetfoodEntity;
import com.example.demo.Graduation.entity.PetjewelryEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.ActivityService;
import com.example.demo.Graduation.service.PetService.PetFoodService;
import com.example.demo.Graduation.service.PetService.PetJewelryService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//宠物饰品管理
@Controller
@RequestMapping(value = "/petjewelry")
public class PetjewelryController {
    @Autowired
    private PetJewelryService petJewelryService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "")
    public String petjewelrylist(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, PetjewelryEntity petjewelryEntity) {
        PageInfo<PetjewelryEntity> PetjewelryEntityList = petJewelryService.FindAllPetFoodInfo(pageNo, pageSize, petjewelryEntity);
        model.addAttribute("petjewelry", PetjewelryEntityList);
        model.addAttribute("petjewelryname", petjewelryEntity.getPetjewelryname());
        model.addAttribute("petjewelrytype", petjewelryEntity.getPetjewelrytype());
        return "PetJewelry/petjewelrylist";
    }

    //跳转到添加界面
    @GetMapping(value = "/GetAddPetjewelry")
    public String GetAddPetjewelry() {
        return "PetJewelry/petjewelryadd";
    }

    //添加饰品信息
    @LogAop("添加饰品信息")
    @RequiresPermissions("petjewelry:add")
    @PostMapping(value = "/AddPetjewelry")
    @ResponseBody
    public Result AddPetjewelry(PetjewelryEntity petjewelryEntity) {
        Result result = petJewelryService.AddPetJewelry(petjewelryEntity);
        return result;
    }


    //查询饰品名是否重复
    @PostMapping(value = "/VerificationPetJewelryName")
    @ResponseBody
    public Result VerificationPetJewelryName(@RequestParam("jewelryname") String jewelryname) {
        Result result = petJewelryService.VerificationPetJewelryName(jewelryname);
        return result;
    }

    //删除饰品信息
    @LogAop("删除饰品信息")
    @RequiresPermissions("petjewelry:delete")
    @PostMapping(value = "/DeletePetjewelry")
    @ResponseBody
    public Result DeletePetjewelry(@RequestParam("id") String id) {
        Result result = petJewelryService.DeletePetjewelry(id);
        return result;
    }


    //跳转到添加库存界面
    @GetMapping(value = "/GetIncreaseStock")
    public String GetIncreaseStock(@RequestParam("id") String id, Model model) {
        PetjewelryEntity petjewelryEntity = petJewelryService.IdFindPetjewelryInfo(id);
        model.addAttribute("petjewelry", petjewelryEntity);
        return "PetJewelry/IncreaseStock";
    }

    //添加库存
    @LogAop("添加饰品库存")
    @RequiresPermissions("petjewelry:increasestock")
    @PostMapping(value = "/IncreaseStock")
    @ResponseBody
    public Result IncreaseStock(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number) {
        Result result = petJewelryService.IncreaseStock(id, number);
        return result;
    }

    //跳转到减少库存界面
    @GetMapping("/GetReduceStock")
    public String GetReduceStock(@RequestParam("id") String id, Model model) {
        PetjewelryEntity petjewelryEntity = petJewelryService.IdFindPetjewelryInfo(id);
        List<Activity> activityList = activityService.FindUnderwayActivity();
        model.addAttribute("activity", activityList);
        model.addAttribute("petjewelry", petjewelryEntity);
        return "PetJewelry/ReduceStock";
    }


    //减少库存
    @LogAop("减少饰品库存")
    @RequiresPermissions("petjewelry:reducestock")
    @PostMapping(value = "/ReduceStock")
    @ResponseBody
    public Result ReduceStock(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number, @RequestParam("member_name") String member_name, @RequestParam("activityid") String activityid) {
        Result result = petJewelryService.ReduceStock(id, number, member_name, activityid);
        return result;
    }


    //跳转到报废库存界面
    @GetMapping(value = "/GetScrap")
    public String GetScrap(@RequestParam("id") String id, Model model) {
        PetjewelryEntity petjewelryEntity = petJewelryService.IdFindPetjewelryInfo(id);
        model.addAttribute("petjewelry", petjewelryEntity);
        return "PetJewelry/Scrap";
    }


    //报废库存
    @LogAop("饰品报废")
    @RequiresPermissions("petjewelry:scrap")
    @PostMapping(value = "/Scrap")
    @ResponseBody
    public Result Scrap(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number) {
        Result result = petJewelryService.Scrap(id, number);
        return result;
    }


    //跳转到饰品界面
    @GetMapping(value = "/GetUpdatePetjewelry")
    public String GetUpdatePetjewelry(@RequestParam("id") String id, Model model) {
        PetjewelryEntity petjewelryEntity = petJewelryService.IdFindPetjewelryInfo(id);
        model.addAttribute("petjewelry", petjewelryEntity);
        return "PetJewelry/petjewelryupdate";
    }


    //修改饰品信息
    @LogAop("修改饰品信息")
    @PostMapping(value = "/UpdatePetjewelry")
    @RequiresPermissions("petjewelry:update")
    @ResponseBody
    public Result UpdatePetjewelry(PetjewelryEntity petjewelryEntity) {
        Result result = petJewelryService.UpdatePetjewelry(petjewelryEntity);
        return result;
    }
}
