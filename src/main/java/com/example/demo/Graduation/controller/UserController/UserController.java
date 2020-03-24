package com.example.demo.Graduation.controller.UserController;


import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.RoleService.RoleService;
import com.example.demo.Graduation.service.UserService.UserService;
import com.github.pagehelper.PageInfo;
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
    public String UserInfo(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, UserEntity userEntity) {
        PageInfo<UserEntity> userEntityPageInfo = userService.RoleFindUserinfo(pageNo, pageSize, userEntity.getUsername());
        model.addAttribute("username", userEntity.getUsername());
        model.addAttribute("userinfo", userEntityPageInfo);
        return "SysUser/userlist";
    }

    //跳转到添加用户界面
    @RequiresPermissions("user:add")
    @RequestMapping("/addusers")
    public String AddUsers(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);
        List<MenuEntity> menuEntityList = menuService.FindAllsMenu();
        model.addAttribute("rolenamelist", roleEntityList);
        model.addAttribute("menualllist", menuEntityList);
        return "SysUser/useradd";
    }


    //查询账号是否重复
    @PostMapping(value = "/VerificationUsername")
    @ResponseBody
    public Result VerificationUsername(String username) throws Exception {

        Result result = userService.UsernameFindUser(username);
        return result;
    }

    /*
    添加用户
     */
    @LogAop("添加用户")
    @PostMapping(value = "/AddUserInfo")
    @ResponseBody
    public Result AddUserInfo(UserEntity userEntity, @RequestParam("name") String name) throws Exception {
        System.out.println("进来了");
        Result result = userService.AddUserInfo(userEntity, name);
        return result;
    }

    //封停账号
    @LogAop("封停账号")
    @PostMapping(value = "/SealUser")
    @ResponseBody
    public Result SealUser(String id) {
        Result result = userService.SealUser(id);
        return result;
    }

    /*
    解封账号
     */
    @LogAop("解封账号")
    @PostMapping(value = "/RelieveSealUser")
    @ResponseBody
    public Result RelieveSealUser(String id) {
        Result result = userService.RelieveSealUser(id);
        return result;
    }

    /*
    删除
     */
    @LogAop("解封用户")
    @PostMapping(value = "/DeleteUserInfo")
    @ResponseBody
    public Result DeleteUserInfo(String id) {
        Result result = userService.DeleteUserInfo(id);
        return result;
    }

    /*
    跳转到用户修改界面
     */
    @RequestMapping(value = "/UpdateUserInfo")
    public String GetUpdateUser(String id, Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        UserEntity userEntity = userService.UserIdFindUserinfo(id);//用户id查询用户信息
        List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);//查询用户的可以修改的权限
        model.addAttribute("rolenamelist", roleEntityList);
        model.addAttribute("user", userEntity);
        return "SysUser/userupdattest";
    }

    //修改用户信息
    @LogAop("修改用户信息")
    @PostMapping(value = "/UpdateUser")
    @ResponseBody
    public Result UpdateUser(UserEntity userEntity, String name) throws Exception {
        Result result = userService.UpdateUser(userEntity, name);
        return result;
    }


}
