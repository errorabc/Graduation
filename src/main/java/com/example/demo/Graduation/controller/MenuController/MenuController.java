package com.example.demo.Graduation.controller.MenuController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/FindAllMenu")
    @ResponseBody
    public JSONArray FindAllMenu() {
        List<MenuEntity> list = menuService.FindAllMenu();
        JSONArray jsonArray = new JSONArray();
        for (MenuEntity i : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("menuName", i.getName());
            jsonObject.put("menuType", i.getType());
            jsonObject.put("id", i.getId());
            jsonObject.put("url", i.getUrl());
            jsonObject.put("parentId", i.getParent_id());
            jsonObject.put("perms", i.getPermission());
            jsonObject.put("orderNum",i.getSort());
            jsonObject.put("icon","#");
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


}
