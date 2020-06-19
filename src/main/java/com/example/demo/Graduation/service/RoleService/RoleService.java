package com.example.demo.Graduation.service.RoleService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.Graduation.Dao.MenuDao.MenuDao;
import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.*;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDao menuDao;

    public List<RoleEntity> DifferentRoleFindRoleInfo(String username) {
        RoleEntity roleEntity = userDao.Finduserinfo2(username);//根据用户的账号查询用户的信息
        List<RoleEntity> roleEntityList = roleDao.DifferentRoleFindRoleInfo(roleEntity.getName());
        return roleEntityList;
    }

    public PageInfo FindRoleInfo(int PageNo, int PageSzie, String name) {
        PageHelper.startPage(PageNo, PageSzie);
        List<RoleEntity> roleEntityList = roleDao.FindRoleInfo(name);
        PageInfo<RoleEntity> pagelist = new PageInfo<RoleEntity>(roleEntityList);
        return pagelist;
    }

    public Result AddRoleinfo(RoleEntity roleEntity, String ztree) {
        JSONArray jsonztree = JSONArray.parseArray(ztree);
        String uuid = UUID.randomUUID().toString();
        RoleEntity roleEntity1 = roleDao.RoleNameFindRoleInfo(roleEntity.getName());
        if (null != roleEntity1) {
            return Result.error(0, "角色名已经存在");
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            roleEntity.setCreatetime(DateTime.strToDateLong(df.format(new Date())));//生成时间
            roleEntity.setUpdatetime(DateTime.strToDateLong(df.format(new Date())));//修改时间
            roleEntity.setId(uuid);
            if (roleDao.AddRoleinfo(roleEntity)) {
                for (int i = 0; i < jsonztree.size(); i++) {
                    roleDao.AddRoleResources(uuid, jsonztree.getJSONObject(i).getString("id"));
                }
                return Result.success(1, "添加成功");
            } else {
                return Result.error(0, "添加失败");
            }
        }
    }

    public Result VerificationRoleName(String name) {
        RoleEntity roleEntity = roleDao.RoleNameFindRoleInfo(name);
        if (null != roleEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");
        }


    }

    public RoleEntity IdFindRoleInfo(String id) {
        RoleEntity roleEntity = roleDao.IdFindRoleInfo(id);
        return roleEntity;
    }


    //修改角色信息
    public Result UpdateRoleInfo(RoleEntity roleEntity, String ztree) {
        JSONArray jsonztree = JSONArray.parseArray(ztree);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        RoleEntity roleEntity1 = roleDao.RoleNameFindRoleInfo(roleEntity.getName());
        roleEntity.setUpdatetime(DateTime.strToDateLong(df.format(new Date())));
        if (!roleEntity1.getName().equals("超级管理员")) {
            if (null != roleEntity1) { //判断角色名字是否重复了
                if (roleEntity1.getId().equals(roleEntity.getId())) {//名字虽然重复了,但是修改的是自己的
                    if (roleDao.UpdateRoleInfo(roleEntity)) {
                        roleDao.DeleteRoleResources(roleEntity.getId()); //删除原有的菜单信息
                        for (int i = 0; i < jsonztree.size(); i++) {
                            roleDao.AddRoleResources(roleEntity.getId(), jsonztree.getJSONObject(i).getString("id"));//重新添加菜单信息
                        }
                        return Result.success(1, "修改成功");
                    } else {
                        return Result.error(0, "修改失败");
                    }
                } else {
                    return Result.error(0, "角色名已经存在");
                }
            } else {//没有重复,可以使用,直接修改
                if (roleDao.UpdateRoleInfo(roleEntity)) {
                    roleDao.DeleteRoleResources(roleEntity.getId()); //删除原有的菜单信息
                    for (int i = 0; i < jsonztree.size(); i++) {
                        roleDao.AddRoleResources(roleEntity.getId(), jsonztree.getJSONObject(i).getString("id"));//重新添加菜单信息
                    }
                    return Result.success(1, "修改成功");
                } else {
                    return Result.error(0, "修改失败");
                }
            }
        } else {
            return Result.error(0, "超级管理员不可被修改");
        }
    }


    public Result DeleteRoleInfo(String id) {
        RoleEntity roleEntity = roleDao.IdFindRoleInfo(id);
        List<RoleEntity> roleEntity1 = roleDao.RoleIdFindUserRole(id);//查询当前角色是否有用户关联
        if (roleEntity.getName().equals("超级管理员")) {
            return Result.error(0, "超级管理员不可被删除");
        } else {
            if (roleEntity1.size() > 0) {
                return Result.error(0, "有用户为当前角色，请先更改用户的角色信息");
            } else {
                if (roleDao.DeleteRoleinfo(id)) { //删除角色信息
                    roleDao.DeleteRoleResources(id);
                    return Result.success(1, "删除成功");
                } else {
                    return Result.error(0, "删除失败");
                }
            }
        }

    }

    //查询角色关联的菜单信息
    public List<RoleResourcesEntity> FindRoleAllResources(String id) {
        List<RoleResourcesEntity> roleResourcesLists = roleDao.FindRoleAllResources(id);
        return roleResourcesLists;
    }
}
