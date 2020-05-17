package com.example.demo.Graduation.Configure.shiro;

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
        List<MenuEntity> menuEntityList = menuService.UserNameFindPerssiom(loginusernname);//获取当前用户的所有权限


        for (MenuEntity menu : menuEntityList) {
            System.out.println(menu.getPermission() + "权限");
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
        UserEntity user = userService.FindUserInfo(username);

        dbusername = user.getUsername();//从数据库里面取得账号
        dbstatus = user.getStatus();//从数据库里面取得密码
        dbpassword = user.getPassword();//从数据库里面取得登录黑名单状态
        if (user == null) {
            throw new UnknownAccountException("此用户不存在");
        }
        if (dbstatus != 1) {
            throw new LockedAccountException("此帐号已被锁定，禁止登录！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, dbpassword, ByteSource.Util.bytes(username + SALT), getName());
        return info;
    }


    /**
     * 重写方法,清除当前用户的的 授权缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     *
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
