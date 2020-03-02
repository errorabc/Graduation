package com.example.demo.Graduation.service.RoleService;

import com.example.demo.Graduation.Dao.RoleDao.RoleDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
