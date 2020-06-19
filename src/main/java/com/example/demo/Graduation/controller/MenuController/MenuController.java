package com.example.demo.Graduation.controller.MenuController;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MenuService.MenuService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//菜单管理
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
    @RequiresPermissions("menu:add")
    public String AddContents() {
        return "SysMenu/catalogadd";
    }

    //跳转到添加菜单界面
    @RequestMapping(value = "/GetAddMenu")
    @RequiresPermissions("menu:add")
    public String GetAddMenu(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "SysMenu/menuadd";
    }

    //跳转到添加按钮界面
    @RequestMapping(value = "/GetAddButton")
    @RequiresPermissions("menu:add")
    public String GetAddButton(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "SysMenu/buttonadd";
    }

    /*
    跳转到修改界面
     */
    @RequestMapping(value = "/GetUpdateResouces")
    @RequiresPermissions("menu:update")
    public String GetUpdateResouces(Model model, @RequestParam("id") String id) {
        MenuEntity menuEntity = menuService.IDFindResoucesinfo(id);
        model.addAttribute("menuinfo", menuEntity);
        return "SysMenu/menusupdate";
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
    @RequiresPermissions("menu:delete")
    @ResponseBody
    public Result deletemenu(@Param("id") String id) {
        Result result = menuService.DeleteMenu(id);
        return result;
    }


    //查询菜单名是否重复
    @PostMapping(value = "/VerificationMenuName")
    @ResponseBody
    public Result VerificationMenuName(@RequestParam("name") String name) {
        Result result = menuService.VerificationMenuName(name);
        return result;
    }

    //查询菜单地址是否重复
    @PostMapping(value = "/VerificationMenuUrl")
    @ResponseBody
    public Result VerificationMenuUrl(@RequestParam("url") String url) {
        Result result = menuService.VerificationMenuUrl(url);
        return result;
    }

    //查询菜单权限是否重复
    @PostMapping(value = "/VerificationMenuPermission")
    @ResponseBody
    public Result VerificationMenuPermission(@RequestParam("permission") String permission) {
        Result result = menuService.VerificationMenuPermission(permission);
        return result;
    }

    //添加目录
    @LogAop("添加目录")
    @PostMapping(value = "/AddCatalog")
    @RequiresPermissions("menu:add")
    @ResponseBody
    public Result AddCatalog(MenuEntity menuEntity) {
        Result result = menuService.AddCatalog(menuEntity);
        return result;
    }

    //添加菜单
    @LogAop("添加菜单")
    @PostMapping(value = "/AddMenu")
    @RequiresPermissions("menu:add")
    @ResponseBody
    public Result AddMenu(MenuEntity menuEntity) {
        Result result = menuService.AddMenu(menuEntity);
        return result;
    }

    //添加按钮
    @LogAop("添加按钮")
    @PostMapping(value = "/AddButton")
    @RequiresPermissions("menu:add")
    @ResponseBody
    public Result AddButton(MenuEntity menuEntity) {
        Result result = menuService.AddButton(menuEntity);
        return result;
    }

    //修改菜单
    @LogAop("修改菜单")
    @PostMapping(value = "/UpdateMenus")
    @RequiresPermissions("menu:update")
    @ResponseBody
    public Result UpdateMenus(MenuEntity menuEntity) throws Exception {
        Result result = menuService.UpdateMenus(menuEntity);
        return result;
    }

}
