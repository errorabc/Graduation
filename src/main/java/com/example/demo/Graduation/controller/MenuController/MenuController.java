package com.example.demo.Graduation.controller.MenuController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/menuinfo")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //跳转到菜单管理界面
    @RequestMapping(value = "")
    public String menulist(Model model) {

        return "SysMenu/menulist";
    }


    //跳转到添加目录界面
    @RequestMapping(value = "/AddContents")
    public String AddContents() {
        return "SysMenu/AddContents";
    }

    //跳转到添加菜单界面
    @RequestMapping(value = "/GetAddMenu")
    public String GetAddMenu(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "SysMenu/AddMenu";
    }

    //跳转到添加按钮界面
    @RequestMapping(value = "/GetAddButton")
    public String GetAddButton(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "SysMenu/AddBuuton";
    }

    //获取所有的菜单
    @RequestMapping(value = "/FindAllMenu")
    @ResponseBody
    public JSONArray FindAllMenu() {
        JSONArray jsonArray = menuService.FindAllMenu();
        return jsonArray;
    }

    //删除菜单,按钮,或者目录
    @LogAop("删除菜单")
    @PostMapping(value = "/deletemenu")
    @ResponseBody
    public String deletemenu(@Param("id") String id) {
        System.out.println(id);
        String flag = menuService.DeleteMenu(id);
        return flag;
    }


    //查询菜单名是否存在
    @PostMapping(value = "/VerificationMenuName")
    @ResponseBody
    public String VerificationMenuName(@RequestParam("name") String name) {
        String flag = menuService.VerificationMenuName(name);
        return flag;
    }

    //查询菜单地址是否存在
    @PostMapping(value = "/VerificationMenuUrl")
    @ResponseBody
    public String VerificationMenuUrl(@RequestParam("url") String url) {
        String flag = menuService.VerificationMenuUrl(url);
        return flag;
    }

    //查询菜单权限是否存在
    @PostMapping(value = "/VerificationMenuPermission")
    @ResponseBody
    public String VerificationMenuPermission(@RequestParam("permission") String permission) {
        String flag = menuService.VerificationMenuPermission(permission);
        return flag;
    }

    //添加菜单
    @LogAop("添加目录")
    @PostMapping(value = "/AddCatalog")
    @ResponseBody
    public String AddCatalog(MenuEntity menuEntity) {
        String flag = menuService.AddCatalog(menuEntity);
        return flag;
    }

    //添加菜单
    @LogAop("添加菜单")
    @PostMapping(value = "/AddMenu")
    @ResponseBody
    public String AddMenu(MenuEntity menuEntity) {
        String flag = menuService.AddMenu(menuEntity);
        return flag;
    }

    //添加菜单
    @LogAop("添加按钮")
    @PostMapping(value = "/AddButton")
    @ResponseBody
    public String AddButton(MenuEntity menuEntity) {
        String flag = menuService.AddButton(menuEntity);
        return flag;
    }
}
