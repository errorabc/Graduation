package com.example.demo.Graduation.service.RoleService;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

import java.util.List;

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
}
