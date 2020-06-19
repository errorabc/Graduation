package com.example.demo.Graduation.controller.RoleController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.RoleResourcesEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.RoleService.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//角色管理
@Controller
@RequestMapping(value = "/roleinfo")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    //跳转到角色管理
    @RequestMapping(value = "")
    public String rolelist(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, RoleEntity roleEntity) {
        PageInfo<RoleEntity> pageInfolist = roleService.FindRoleInfo(pageNo, pageSize, roleEntity.getName());
        model.addAttribute("rolelist", pageInfolist);
        model.addAttribute("rolename", roleEntity.getName());
        return "SysRole/rolelist";
    }

    //跳转到添加角色界面
    @RequestMapping(value = "/GetRoleAdd")
    public String GetRoleAdd(Model model) {
        JSONArray jsonArray = menuService.FindAllMenuZtree();
        model.addAttribute("ztree", jsonArray);
        return "SysRole/roleadd";
    }

    //添加角色
    @LogAop("添加角色信息")
    @PostMapping(value = "/AddRoleinfo")
    @RequiresPermissions("sys:role:add")
    @ResponseBody
    public Result AddRoleinfo(RoleEntity roleEntity, @RequestParam("ztree") String ztree) {
        Result result = roleService.AddRoleinfo(roleEntity, ztree);
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
        RoleEntity roleEntity = roleService.IdFindRoleInfo(id);//查询用户的信息
        JSONArray jsonArray = menuService.FindAllMenuZtree();
        List<RoleResourcesEntity> roleResourcesLists = roleService.FindRoleAllResources(id);
        model.addAttribute("ztree", jsonArray);
        model.addAttribute("role", roleEntity);
        model.addAttribute("roleresources", roleResourcesLists);
        return "SysRole/roleupdate";
    }

    //修改角色信息
    @LogAop("修改角色信息")
    @PostMapping(value = "/UpdateRoleInfo")
    @RequiresPermissions("sys:role:update")
    @ResponseBody
    public Result UpdateRoleInfo(RoleEntity roleEntity, @RequestParam("ztree") String ztree) {
        Result result = roleService.UpdateRoleInfo(roleEntity, ztree);
        return result;
    }

    //删除角色信息
    @LogAop("删除角色信息")
    @PostMapping(value = "/DeleteRoleInfo")
    @RequiresPermissions("sys:role:delete")
    @ResponseBody
    public Result DeleteRoleInfo(@RequestParam("id") String id) {
        Result result = roleService.DeleteRoleInfo(id);
        return result;
    }
}
