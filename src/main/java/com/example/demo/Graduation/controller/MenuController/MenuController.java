package com.example.demo.Graduation.controller.MenuController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/menuinfo")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "")
    public String menulist(Model model) {


        return "SysMenu/menulist";
    }

    //获取所有的菜单
    @RequestMapping(value = "/FindAllMenu")
    @ResponseBody
    public JSONArray FindAllMenu() {
        JSONArray jsonArray = menuService.FindAllMenu();
        return jsonArray;
    }

    //删除某个菜单
    @PostMapping(value = "/deletemenu")
    @ResponseBody
    public String deletemenu(@Param("id") String id) {

        String flag = null;
        return flag;
    }

}
