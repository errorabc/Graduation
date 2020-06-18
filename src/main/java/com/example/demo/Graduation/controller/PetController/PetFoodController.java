package com.example.demo.Graduation.controller.PetController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.Activity;
import com.example.demo.Graduation.entity.PetfoodEntity;
import com.example.demo.Graduation.entity.PetfosterEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.ActivityService;
import com.example.demo.Graduation.service.PetService.PetFoodService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//宠物食品管理
@Controller
@RequestMapping("/PetFoodinfo")
public class PetFoodController {

    @Autowired
    private PetFoodService petFoodService;
    @Autowired
    private ActivityService activityService;

    //查询
    @RequestMapping(value = "")
    public String GetPetFood(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, PetfoodEntity petfoodEntity) {
        PageInfo<PetfoodEntity> petfoodlist = petFoodService.FindAllPetFoodInfo(pageNo, pageSize, petfoodEntity);
        model.addAttribute("petfoodlist", petfoodlist);
        model.addAttribute("foodname", petfoodEntity.getFoodname());
        model.addAttribute("foodtype", petfoodEntity.getFoodtype());
        return "PetFood/petfoodlist";
    }

    //跳转到添加界面
    @GetMapping(value = "/GetAddPetFood")
    public String GetAddPetFodd() {
        return "PetFood/petfoodadd";
    }


    //查询食品名称是否重复
    @PostMapping(value = "/VerificationPetFoodName")
    @ResponseBody
    public Result VerificationPetFoodName(@RequestParam("foodname") String foodname) {
        Result resul = petFoodService.VerificationPetFoodName(foodname);
        return resul;
    }


    //添加食品信息
    @LogAop("添加食品信息")
    @RequiresPermissions("petfood:add")
    @PostMapping(value = "/AddPetFood")
    @ResponseBody
    public Result AddPetFood(PetfoodEntity petfoodEntity) {
        Result result = petFoodService.AddPetFood(petfoodEntity);
        return result;
    }


    //删除食品信息
    @LogAop("删除食品信息")
    @RequiresPermissions("petfood:delete")
    @PostMapping(value = "/DeletePetFood")
    @ResponseBody
    public Result DeletePetFood(@RequestParam("id") String id) {
        Result result = petFoodService.DeletePetFood(id);
        return result;
    }


    //跳转到添加库存界面
    @GetMapping(value = "/GetIncreaseStock")
    public String GetIncreaseStock(@RequestParam("id") String id, Model model) {
        PetfoodEntity petfood = petFoodService.IdFindPetFoodInfo(id);
        model.addAttribute("petfood", petfood);
        return "PetFood/IncreaseStock";
    }

    //跳转到减少库存界面
    @GetMapping(value = "/GetReduceStock")
    public String GetReduceStock(@RequestParam("id") String id, Model model) {
        PetfoodEntity petfood = petFoodService.IdFindPetFoodInfo(id);
        List<Activity> activityList = activityService.FindUnderwayActivity();
        model.addAttribute("activity", activityList);
        model.addAttribute("petfood", petfood);
        return "PetFood/ReduceStock";
    }


    //添加库存
    @LogAop("添加食品库存")
    @RequiresPermissions("petfood:increasestock")
    @PostMapping(value = "/IncreaseStock")
    @ResponseBody
    public Result IncreaseStock(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number) {
        Result result = petFoodService.IncreaseStock(id, number);
        return result;
    }

    //减少库存
    @LogAop("减少食品库存")
    @RequiresPermissions("petfood:reducestock")
    @PostMapping(value = "/ReduceStock")
    @ResponseBody
    public Result ReduceStock(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number, @RequestParam("member_name") String member_name, @RequestParam("activityid") String activityid) {
        Result result = petFoodService.ReduceStock(id, number, member_name, activityid);
        return result;
    }

    //跳转到报废库存界面
    @GetMapping(value = "/GetScrap")
    public String GetScrap(@RequestParam("id") String id, Model model) {
        PetfoodEntity petfood = petFoodService.IdFindPetFoodInfo(id);
        model.addAttribute("petfood", petfood);
        return "PetFood/Scrap";
    }

    //报废库存
    @LogAop("食品报废")
    @RequiresPermissions("petfood:scrap")
    @PostMapping(value = "/Scrap")
    @ResponseBody
    public Result Scrap(@RequestParam("id") String id, @RequestParam("IncreasNumber") int number) {
        Result result = petFoodService.Scrap(id, number);
        return result;
    }


    //跳转到修改视频信息界面
    @GetMapping(value = "/GetUpdatePetFood")
    public String GetUpdatePetFood(@RequestParam("id") String id, Model model) {
        PetfoodEntity petfood = petFoodService.IdFindPetFoodInfo(id);
        model.addAttribute("petfood", petfood);
        return "PetFood/petfoodupdate";
    }

    //修改食品信息
    @LogAop("修改食品信息")
    @RequiresPermissions("petfood:update")
    @PostMapping(value = "/UpdatePetFood")
    @ResponseBody
    public Result UpdatePetFood(PetfoodEntity petfoodEntity) {
        Result result = petFoodService.UpdatePetFood(petfoodEntity);
        return result;
    }


}
