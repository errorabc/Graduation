package com.example.demo.Graduation.controller.WeComeController;

import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.UserService.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//欢迎界面
@Controller
@RequestMapping("/menu")
public class WeComeController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;


    /*
    跳转到桌面页
     */
    @RequestMapping(value = "/Welcome")
    public String Test2(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//已经登录的用户
        RoleEntity roleEntity = userService.Finduserinfo2(LoginUsername);
        model.addAttribute("roleEntity", roleEntity);
        model.addAttribute("username",LoginUsername);
        return "welcome";
    }


}
