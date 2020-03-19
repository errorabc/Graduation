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

    public Result UpdateRoleInfo(RoleEntity roleEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        RoleEntity roleEntity1 = roleDao.RoleNameFindRoleInfo(roleEntity.getName());
        roleEntity.setUpdatetime(DateTime.strToDateLong(df.format(new Date())));
        int flag = 0;

        if (!roleEntity1.getName().equals("超级管理员")) {
            if (null != roleEntity1) {
                if (roleEntity1.getId().equals(roleEntity.getId())) {

                } else {
                    flag = 1;
                    return Result.error(0, "角色名已经存在");
                }
            }
            //继续操作下去
            if (flag == 0) {
                if (roleDao.UpdateRoleInfo(roleEntity)) {
                    return Result.success(1, "修改成功");
                } else {
                    return Result.error(0, "修改失败");
                }
            } else {
                return Result.error(0, "服务器异常");
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
                if (roleDao.DeleteRoleinfo(id)) {
                    return Result.error(1, "删除成功");
                } else {
                    return Result.error(0, "删除失败");
                }
            }
        }

    }

    /*
        layui菜单树
     */
    public JSONArray layuitree() {
        List<MenuEntity> mulu = menuDao.FindMenusType11();//一级菜单
        JSONObject jsonObject2 = null;
        JSONObject jsonObject = null;
        JSONObject jsonObject3 = null;
        JSONObject jsonObject4 = null;
        JSONArray jsonArray4 = new JSONArray();
        for (MenuEntity item : mulu) {
            List<MenuEntity> caidan = menuDao.ParentIdFindResoucesinfo(item.getId());  //二级菜单
            JSONArray jsonArray3 = new JSONArray();
            JSONArray jsonArray2 = new JSONArray();
            for (MenuEntity item2 : caidan) { //拿出所有的二级菜单
                System.out.println(item2.getName());
                List<MenuEntity> anniu = menuDao.ParentIdFindResoucesinfo(item2.getId());//三级菜单
                JSONArray jsonArray1 = new JSONArray();
                for (MenuEntity item3 : anniu) {
                    jsonObject = new JSONObject();
                    jsonObject.put("id", item3.getId());
                    jsonObject.put("title", item3.getName());
                    jsonArray1.add(jsonObject);
                }
                jsonObject2 = new JSONObject();
                jsonObject2.put("id", item2.getId());
                jsonObject2.put("title", item2.getName());
                jsonObject2.put("children", jsonArray1);
                jsonArray3.add(jsonObject2);
            }
            jsonObject3 = new JSONObject();
            jsonObject3.put("id", item.getId());
            jsonObject3.put("title", item.getName());
            jsonObject3.put("children", jsonArray3);
            jsonArray4.add(jsonObject3);
        }
        return jsonArray4;
    }

    //查询角色关联的菜单信息
    public List<RoleResourcesEntity> FindRoleAllResources(String id) {
        List<RoleResourcesEntity> roleResourcesLists = roleDao.FindRoleAllResources(id);
        return roleResourcesLists;
    }
}
