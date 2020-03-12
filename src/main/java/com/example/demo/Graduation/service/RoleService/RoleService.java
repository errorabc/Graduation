package com.example.demo.Graduation.service.RoleService;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

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

    public List<RoleEntity> DifferentRoleFindRoleInfo(String username) {
        UserEntity userEntity = userDao.Finduserinfo2(username);//根据用户的账号查询用户的信息
        List<RoleEntity> roleEntityList = roleDao.DifferentRoleFindRoleInfo(userEntity.getRoleEntity().getDescription());
        return roleEntityList;
    }

    public PageInfo FindRoleInfo(int PageNo, int PageSzie, String name) {
        PageHelper.startPage(PageNo, PageSzie);
        List<RoleEntity> roleEntityList = roleDao.FindRoleInfo(name);
        PageInfo<RoleEntity> pagelist = new PageInfo<RoleEntity>(roleEntityList);
        return pagelist;
    }

    public Result AddRoleinfo(RoleEntity roleEntity) {
        RoleEntity roleEntity1 = roleDao.RoleNameFindRoleInfo(roleEntity.getName());
        if (null != roleEntity1) {
            return Result.error(0, "角色名已经存在");
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            roleEntity.setCreatetime(DateTime.strToDateLong(df.format(new Date())));
            roleEntity.setUpdatetime(DateTime.strToDateLong(df.format(new Date())));
            roleEntity.setId(UUID.randomUUID().toString());

            if (roleDao.AddRoleinfo(roleEntity)) {
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
}
