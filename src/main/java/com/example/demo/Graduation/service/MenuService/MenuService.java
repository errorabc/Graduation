package com.example.demo.Graduation.service.MenuService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Dao.MenuDao.MenuDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    //查询所有菜单
    public JSONArray FindAllMenu() {
        List<MenuEntity> list = menuDao.FindAllMenu();
        JSONArray jsonArray = new JSONArray();
        for (MenuEntity i : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("menuName", i.getName());
            jsonObject.put("menuType", i.getType());
            jsonObject.put("id", i.getId());
            jsonObject.put("url", i.getUrl());
            jsonObject.put("parentId", i.getParent_id());
            jsonObject.put("perms", i.getPermission());
            jsonObject.put("orderNum", i.getSort());
            jsonObject.put("icon", "#");
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //查询菜单名是否重复
    public String VerificationMenuName(String name) {
        MenuEntity menuEntity = menuDao.VerificationMenuName(name);
        if (null != menuEntity) {
            return "已存在";
        } else {
            return "可以使用";
        }
    }

    //查询菜单地址是否重复
    public String VerificationMenuUrl(String url) {
        MenuEntity menuEntity = menuDao.VerificationMenuUrl(url);
        if (null != menuEntity) {
            return "已存在";
        } else {
            return "可以使用";
        }
    }

    //查询菜单权限是否重复
    public String VerificationMenuPermission(String permission) {
        MenuEntity menuEntity = menuDao.VerificationMenuPermission(permission);
        if (null != menuEntity) {
            return "已存在";
        } else {
            return "可以使用";
        }
    }

    //添加目录
    public String AddCatalog(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(1);
        menuEntity.setParent_id("0");
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return "添加成功";
                        } else {
                            return "添加失败,服务器异常";
                        }
                    } else {
                        return "目录权限已存在";
                    }

                } else {
                    return "目录地址已存在";
                }
            } else {
                return "目录名字已存在";
            }
        } else {
            return "有必填项为空";
        }
    }


    //添加菜单
    public String AddMenu(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(2);
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return "添加成功";
                        } else {
                            return "添加失败,服务器异常";
                        }
                    } else {
                        return "菜单权限已存在";
                    }

                } else {
                    return "菜单地址已存在";
                }
            } else {
                return "菜单名字已存在";
            }
        } else {
            return "有必填项为空";
        }
    }

    //添加按钮

    public String AddButton(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(3);
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return "添加成功";
                        } else {
                            return "添加失败,服务器异常";
                        }
                    } else {
                        return "按钮权限已存在";
                    }

                } else {
                    return "按钮地址已存在";
                }
            } else {
                return "按钮名字已存在";
            }
        } else {
            return "有必填项为空";
        }
    }

    //删除目录，菜单，按钮
    public String DeleteMenu(String id) {
        MenuEntity menuEntity = menuDao.IDFindResoucesinfo(id);
        //删除目录
        if (menuEntity.getType() == 1) {
            List<MenuEntity> menuEntityList = menuDao.ParentIdFindResoucesinfo(id);
            //循环删除目录下面的菜单和按钮
            for (MenuEntity m : menuEntityList) {
                menuDao.DeleteMenu(m.getId());
                menuDao.DeleteMenuUser(m.getId());
            }
            //删除目录
            menuDao.DeleteMenu(id);
            menuDao.DeleteMenuUser(id);
            if (null != menuDao.IDFindResoucesinfo(id)) {
                return "目录删除失败";
            } else {
                return "目录删除成功";
            }

        }


        //删除菜单
        if (menuEntity.getType() == 2) {
            if (menuDao.DeleteMenu(id)) {//删除按钮信息
                menuDao.DeleteMenuUser(id); //删除按钮和用户的绑定
                return "菜单删除成功";
            } else {
                return "菜单删除失败";
            }
        }

        //删除按钮----直接删除按钮信息和用户绑定的信息
        if (menuEntity.getType() == 3) {
            if (menuDao.DeleteMenu(id)) {//删除按钮信息
                menuDao.DeleteMenuUser(id); //删除按钮和用户的绑定
                return "按钮删除成功";
            } else {
                return "按钮删除失败";
            }
        }
        return "";
    }


}
