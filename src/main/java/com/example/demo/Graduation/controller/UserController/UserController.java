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
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//用户管理
@Controller
@RequestMapping("/userinfo")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //跳转到用户管理界面
    @RequiresPermissions("sys:user:list")
    @RequestMapping("")
    public String UserInfo(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, UserEntity userEntity) {
        PageInfo<UserEntity> userEntityPageInfo = userService.RoleFindUserinfo(pageNo, pageSize, userEntity.getUsername(), userEntity.getRolename());
        model.addAttribute("user", userEntity);
        model.addAttribute("userinfo", userEntityPageInfo);
        return "SysUser/userlist";
    }

    //跳转到添加用户界面
    @RequiresPermissions("sys:user:add")
    @RequestMapping("/Getaddusers")
    public String Getaddusers(Model model) {
        try {
            String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
            List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);
            model.addAttribute("rolenamelist", roleEntityList);
        } catch (AuthorizationException a) {
            a.printStackTrace();
        }
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
    @PostMapping(value = "/addusers")
    @RequiresPermissions("sys:user:add")
    @ResponseBody
    public Result AddUserInfo(UserEntity userEntity, @RequestParam("name") String name) throws Exception {
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
    @LogAop("删除用户")
    @PostMapping(value = "/DeleteUserInfo")
    @ResponseBody
    public Result DeleteUserInfo(String id) {
        Result result = userService.DeleteUserInfo(id);
        return result;
    }

    /*
    跳转到用户修改界面
     */
    @RequestMapping(value = "/GetUpdateUser")
    public String GetUpdateUser(String id, Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        UserEntity userEntity = userService.UserIdFindUserinfo(id);//用户id查询用户信息
        List<RoleEntity> roleEntityList = roleService.DifferentRoleFindRoleInfo(LoginUsername);//查询用户的可以修改的权限
        model.addAttribute("rolenamelist", roleEntityList);
        model.addAttribute("user", userEntity);
        return "SysUser/userupdate";
    }

    //修改用户信息
    @LogAop("修改用户信息")
    @PostMapping(value = "/UpdateUser")
    @RequiresPermissions("sys:user:update")
    @ResponseBody
    public Result UpdateUser(UserEntity userEntity, String name) throws Exception {
        Result result = userService.UpdateUser(userEntity, name);
        return result;
    }


    //跳转到修改密码界面
    @GetMapping(value = "/GetUpdatePassword")
    public String GetUpdatePassword(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        UserEntity userEntity = userService.UserNameFindUserInfo(LoginUsername);
        model.addAttribute("user", userEntity);
        return "System/updatepassword";
    }


    //修改密码
    @LogAop(value = "修改密码")
    @PostMapping(value = "/UpdatePassword")
    @ResponseBody
    public Result UpdatePassword(@RequestParam("password") String password, @RequestParam("username") String username) throws Exception {
        Result result = userService.UpdatePassword(username, password);
        return result;
    }
}
