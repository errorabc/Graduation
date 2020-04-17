package com.example.demo.Graduation.Configure;

import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.entity.UserEntity;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.UserService.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    private static final String SALT = "Yiqiwan";
    private String dbusername;
    private int dbstatus = 0;
    private String dbpassword;


    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String loginusernname = (String) principalCollection.getPrimaryPrincipal();  //获取当前登录的用户名
        List<MenuEntity> menuEntityList = menuService.UserNameFindPerssiom(loginusernname);//根据登录的用户名查询他的权限
        for (MenuEntity menu : menuEntityList) {
            if (!StringUtils.isEmpty(menu.getPermission())) {
                info.addStringPermission(menu.getPermission());
            }
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        List<UserEntity> userEntity1 = userService.FindUserInfo(username);
        for (UserEntity u : userEntity1) {
            dbusername = u.getUsername();//从数据库里面取得账号
            dbstatus = u.getStatus();//从数据库里面取得密码
            dbpassword = u.getPassword();//从数据库里面取得登录黑名单状态
        }
        if (userEntity1.size() == 0) {
            throw new UnknownAccountException("此用户不存在");
        }
        if (dbstatus != 1) {
            throw new LockedAccountException("此帐号已被锁定，禁止登录！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, dbpassword, ByteSource.Util.bytes(username + SALT), getName());
        return info;
    }
}
