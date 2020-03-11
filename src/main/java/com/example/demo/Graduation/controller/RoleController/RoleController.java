package com.example.demo.Graduation.controller.RoleController;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Tool.PasswordUtil;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.service.RoleService.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
