package com.example.demo.Graduation.controller.UserController;


import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.RoleService.RoleService;
import com.example.demo.Graduation.service.UserService.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userinfo")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //跳转到用户管理界面
    @RequiresPermissions("user:user")
    @RequestMapping("")
    public String UserInfo(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        List<UserEntity> userEntityList = userService.RoleFindUserinfo(LoginUsername);//查询用户的信息
        List<MenuEntity> menuEntityListButton = menuService.RoleFindMenuButton(LoginUsername);
        //查询当前用户在用户管理界面拥有的界面
        model.addAttribute("userinfo", userEntityList);
        model.addAttribute("menubutton", menuEntityListButton);
        model.addAttribute("btton", menuEntityListButton);
        return "SysUser/userinfo";
    }

    //跳转到添加用户界面
    @RequiresPermissions("user:add")
    @RequestMapping("/addusers")
    public String AddUsers(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);
        model.addAttribute("rolenamelist", roleEntityList);
        return "SysUser/AddUserinfo";
    }


    //查询账号是否重复
    @PostMapping(value = "/AccountRepetition")
    @ResponseBody
    public String AccountRepetition(String username) {
        try {
            String flag = userService.UsernameFindUser(username);
            return flag;
        } catch (Exception e) {
            return "服务器异常";
        }
    }

    /*
    添加用户
     */
    @LogAop("添加用户")
    @PostMapping(value = "/AddUserInfo")
    @ResponseBody
    public String AddUserInfo(UserEntity userEntity, @RequestParam("description") String name) throws Exception {
        String flag = userService.AddUserInfo(userEntity, name);
        return flag;
    }

    //封停账号
    @PostMapping(value = "/SealUser")
    @ResponseBody
    public String SealUser(String id) {
        String flag = userService.SealUser(id);
        return flag;
    }

    /*
    解封账号
     */
    @PostMapping(value = "/RelieveSealUser")
    @ResponseBody
    public String RelieveSealUser(String id) {
        String flag = userService.RelieveSealUser(id);
        return flag;
    }

    /*
    删除
     */
    @PostMapping(value = "/DeleteUserInfo")
    @ResponseBody
    public String DeleteUserInfo(String id) {
        String flag = userService.DeleteUserInfo(id);
        return flag;
    }

    /*
    跳转到用户修改界面
     */
    @RequestMapping(value = "/UpdateUserInfo")
    public String GetUpdateUser(String id, Model model) {
        logger.info("测试");
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        UserEntity userEntity = userService.UserIdFindUserinfo(id);//用户id查询用户信息
        List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);//查询用户的可以修改的权限
        model.addAttribute("rolenamelist", roleEntityList);
        model.addAttribute("user", userEntity);
        model.addAttribute("id", id);
        return "SysUser/UpdateUser";
    }

    //修改用户信息
    @PostMapping(value = "/UpdateUser")
    @ResponseBody
    public String UpdateUser(UserEntity userEntity, String name) throws Exception {
        String flag = userService.UpdateUser(userEntity, name);
        return flag;
    }

}
