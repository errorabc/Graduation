package com.example.demo.Graduation.service.MenuService;

import com.example.demo.Graduation.Dao.MenuDao.MenuDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;

    public List<MenuEntity> FindeMenus(String Username) {
        UserEntity userEntity = userDao.Finduserinfo2(Username);//根据用户的账号查询用户的信息
        String userrolename = userEntity.getRoleEntity().getDescription();//当前登录用户的账号的权限
        String userid = userEntity.getId();//当前登录用户的账号的id
        List<MenuEntity> menuEntityList = menuDao.FindMenusType1(userrolename, userid);
        return menuEntityList;
    }

    public List<MenuEntity> FindMenusParentId(String username) {
        List<MenuEntity> menuEntityList = menuDao.FindMenusParentId(username);
        return menuEntityList;
    }

    public List<MenuEntity> UserNameFindPerssiom(String username) {
        UserEntity userEntity = userDao.Finduserinfo2(username);
        String userrolename = userEntity.getRoleEntity().getDescription();//当前登录用户的账号的权限
        List<MenuEntity> menuEntityList = menuDao.UserNameFindPerssiom(username, userrolename);
        return menuEntityList;
    }

    /*
    获取当前用户所拥有的三级按钮权限
     */
    public List<MenuEntity> RoleFindMenuButton(String username) {
        MenuEntity menuEntity = menuDao.Menu2NameFindID("用户管理");
        List<MenuEntity> menuEntityList = menuDao.RoleUsernameFindButton(username, menuEntity.getId());
        return menuEntityList;
    }

}
