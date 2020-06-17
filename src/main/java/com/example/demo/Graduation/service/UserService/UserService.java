package com.example.demo.Graduation.service.UserService;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.Tool.PasswordUtil;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PasswordUtil passwordUtil;


    //查询用户的信息
    public UserEntity FindUserInfo(String username) {
        UserEntity user = userDao.FindUserInfo(username);
        return user;
    }

    /*
    根据用户名查询用户，角色信息
     */
    public RoleEntity Finduserinfo2(String username) {
        RoleEntity roleEntity = userDao.Finduserinfo2(username);
        return roleEntity;
    }


    //不同权限的获取不同的用户信息
    public PageInfo RoleFindUserinfo(int PageNo, int PageSzie, String username, String name) {
        String loginname = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        UserEntity userEntity=userDao.FindUserInfo(loginname);//当前登陆用户的权限
        PageHelper.startPage(PageNo, PageSzie);
        List<UserEntity> userEntityList = userDao.UserNameFindUserInfo(username, userEntity.getRoleEntity().getName(), name);
        PageInfo<UserEntity> pagelist = new PageInfo<UserEntity>(userEntityList);
        return pagelist;
    }

    //查询用户名是否重复
    public Result UsernameFindUser(String username) throws Exception {
        if (null != username) {
            UserEntity userEntity = userDao.UsernameFindUser(username);
            if (null != userEntity) {
                return Result.error(0, "账号已存在");
            } else {
                return Result.success(1, "账号可以使用");

            }
        } else {
            return Result.error(0, "账号为空");
        }
    }

    /*
    添加用户
     */
    public Result AddUserInfo(UserEntity userEntity, String name) throws Exception {
        RoleEntity roleEntity = roleDao.RoleNameFindRoleInfo(name);
        String ConfidentialPassword = passwordUtil.PasswordConfidential(userEntity.getUsername(), userEntity.getPassword());
        String uuid = UUID.randomUUID().toString();
        userEntity.setId(uuid);
        userEntity.setPassword(ConfidentialPassword);
        if (userDao.AddUserInfo(userEntity)) {//把新用户添加到用户表中去
            if (userDao.AddUserRoleInfo(uuid, roleEntity.getId())) {  //给新用户分配角色
                return Result.success(1, "添加成功");
            } else {
                return Result.error(0, "角色添加失败");
            }
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //封停用户
    public Result SealUser(String id) {
        UserEntity userEntity = userDao.UserIdFindUserinfo(id);
        if (userEntity.getRoleEntity().getName().equals("超级管理员")) {
            return Result.error(0, "超级管理员不可被封停");
        } else {
            if (userDao.SealUser(id)) {
                return Result.success(1, "封停成功");
            } else {
                return Result.error(0, "封停失败");
            }
        }
    }

    //解封用户
    public Result RelieveSealUser(String id) {
        if (userDao.RelieveSealUser(id)) {
            return Result.success(1, "解封成功");
        } else {
            return Result.error(0, "解封失败");
        }
    }

    /*
    删除用户
     */
    public Result DeleteUserInfo(String id) {
        UserEntity userEntity = userDao.UserIdFindUserinfo(id);
        if (userEntity.getRoleEntity().getName().equals("超级管理员")) {
            return Result.error(0, "超级管理员不可被删除");
        } else {
            if (roleDao.DeleteUserRole(id)) {   //删除用户和角色之间的关联
                if (userDao.DeleteUserInfo(id)) {  //删除用户的信息
                    return Result.success(1, "删除成功");
                } else {
                    return Result.error(0, "删除失败");
                }
            } else {
                return Result.error(0, "用户角色删除失败");
            }
        }
    }

    //根据用户id获取用户信息
    public UserEntity UserIdFindUserinfo(String id) {
        UserEntity userEntity = userDao.UserIdFindUserinfo(id);
        return userEntity;
    }

    /*
    修改用户信息
     */
    public Result UpdateUser(UserEntity userEntity, String name) throws Exception {
        RoleEntity roleEntity = roleDao.RoleNameFindRoleInfo(name);//查询角色名称的id
        UserEntity user = userDao.UserIdFindUserinfo(userEntity.getId());//获取用户的信息
        String ConfidentialPassword = passwordUtil.PasswordConfidential(user.getUsername(), userEntity.getPassword());//根据密码和盐值生成加密生成新密码
        if (userEntity.getRoleEntity().getName().equals("超级管理员")) {
            return Result.error(0, "超级管理员不可被删除");
        } else {
            if (userDao.UpdateUserRole(userEntity.getId(), roleEntity.getId())) {//修改用户的角色
                if (userDao.UpdateUserPassword(ConfidentialPassword, userEntity.getId())) {//修改用户的密码
                    return Result.success(1, "修改成功");
                } else {
                    return Result.error(0, "用户密码修改失败");
                }
            } else {
                return Result.error(0, "用户角色修改失败");
            }
        }

    }


    //修改密码
    public Result UpdatePassword(String username, String password) throws Exception {
        UserEntity userEntity = userDao.UsernameFindUser(username);
        String ConfidentialPassword = passwordUtil.PasswordConfidential(username, password);//根据密码和盐值生成加密生成新密码
        if (userDao.UpdateUserPassword(ConfidentialPassword, userEntity.getId())) {
            return Result.success(1, "密码修改成功");
        } else {
            return Result.error(0, "密码修改失败");
        }

    }


    //根据用户名查询用户信息
    public UserEntity UserNameFindUserInfo(String username) {
        UserEntity userEntity = userDao.UsernameFindUser(username);
        return userEntity;
    }
}
