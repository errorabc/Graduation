package com.example.demo.Graduation.controller.RoleController;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Tool.PasswordUtil;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.service.RoleService.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/roleinfo")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //跳转到角色管理
    @RequestMapping(value = "")
    public String rolelist(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, RoleEntity roleEntity) {
        PageInfo<RoleEntity> pageInfolist = roleService.FindRoleInfo(pageNo, pageSize, roleEntity.getName());
        System.out.println(pageInfolist.getSize());
        model.addAttribute("rolelist", pageInfolist);
        model.addAttribute("rolename", roleEntity.getName());
        return "SysRole/rolelist";
    }

    //跳转到添加角色界面
    @RequestMapping(value = "/GetRoleAdd")
    public String GetRoleAdd(Model model) {
        return "SysRole/roleadd";
    }

    //添加角色
    @PostMapping(value = "/AddRoleinfo")
    @ResponseBody
    public Result AddRoleinfo(RoleEntity roleEntity) {
        Result result = roleService.AddRoleinfo(roleEntity);
        return result;
    }

    //验证角色名称是否重复了
    @PostMapping(value = "/VerificationRoleName")
    @ResponseBody
    public Result VerificationRoleName(@RequestParam("name") String name) {
        Result result = roleService.VerificationRoleName(name);
        return result;
    }

    //跳转到修改角色界面
    @RequestMapping(value = "/GetRoleUpdate")
    public String GetRoleUpdate(@RequestParam("id") String id, Model model) {
        RoleEntity roleEntity = roleService.IdFindRoleInfo(id);
        model.addAttribute("role", roleEntity);
        return "SysRole/roleupdate";
    }

    //修改角色信息
    @PostMapping(value = "/UpdateRoleInfo")
    @ResponseBody
    public Result UpdateRoleInfo(RoleEntity roleEntity) {
        Result result = roleService.UpdateRoleInfo(roleEntity);
        return result;
    }

    //删除角色信息
    @PostMapping(value = "/DeleteRoleInfo")
    @ResponseBody
    public String DeleteRoleInfo(@RequestParam("id") String id) {
        return "";
    }
}
