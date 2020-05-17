package com.example.demo.Graduation.service.MenuService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Dao.MenuDao.MenuDao;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity;
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

    //查询一级菜单
    public List<MenuEntity> FindMenusType1(String username) {
        RoleEntity roleEntity = userDao.Finduserinfo2(username);//根据用户的账号查询用户的信息
        List<MenuEntity> menuEntityList = menuDao.FindMenusType1(roleEntity.getId());//获取一级菜单
        return menuEntityList;
    }

    //查询二级菜单
    public List<MenuEntity> FindMenusType2(String username) {
        RoleEntity roleEntity = userDao.Finduserinfo2(username);//根据用户的账号查询用户的信息
        List<MenuEntity> menuEntityList = menuDao.FindMenusType2(roleEntity.getId());
        return menuEntityList;
    }

    //根据用户名字查询菜单权限
    public List<MenuEntity> UserNameFindPerssiom(String username) {
        RoleEntity roleEntity = userDao.Finduserinfo2(username);//根据用户的账号查询用户的信息
        List<MenuEntity> menuEntityList = menuDao.UserNameFindPerssiom(roleEntity.getId());
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


    //查询所有菜单封装成ztree
    public JSONArray FindAllMenuZtree() {
        List<MenuEntity> list = menuDao.FindAllMenu();
        JSONArray jsonArray = new JSONArray();
        for (MenuEntity i : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", i.getId());
            jsonObject.put("name", i.getName());
            jsonObject.put("pId", i.getParent_id());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //查询菜单名是否重复
    public Result VerificationMenuName(String name) {
        MenuEntity menuEntity = menuDao.VerificationMenuName(name);
        if (null != menuEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");
        }
    }

    //查询菜单地址是否重复
    public Result VerificationMenuUrl(String url) {
        MenuEntity menuEntity = menuDao.VerificationMenuUrl(url);
        if (null != menuEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");
        }
    }

    //查询菜单权限是否重复
    public Result VerificationMenuPermission(String permission) {
        MenuEntity menuEntity = menuDao.VerificationMenuPermission(permission);
        if (null != menuEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");
        }
    }

    //添加目录
    public Result AddCatalog(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(1);
        menuEntity.setParent_id("0");
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return Result.success(1, "添加成功");
                        } else {
                            return Result.error(0, "添加失败,服务器异常");
                        }
                    } else {
                        return Result.error(0, "目录权限已存在");
                    }

                } else {
                    return Result.error(0, "目录地址已存在");
                }
            } else {
                return Result.error(0, "目录名字已存在");
            }
        } else {
            return Result.error(0, "有必填项为空");
        }
    }


    //添加菜单
    public Result AddMenu(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(2);
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return Result.success(1, "添加成功");
                        } else {
                            return Result.error(0, "添加失败,服务器异常");
                        }
                    } else {
                        return Result.error(0, "菜单权限已存在");
                    }

                } else {
                    return Result.error(0, "菜单地址已存在");
                }
            } else {

                return Result.error(0, "菜单名字已存在");
            }
        } else {
            return Result.error(0, "有必填项为空");
        }
    }

    //添加按钮

    public Result AddButton(MenuEntity menuEntity) {
        menuEntity.setId(UUID.randomUUID().toString());
        menuEntity.setType(3);
        if (!menuEntity.getUrl().equals("") && !menuEntity.getPermission().equals("") && !menuEntity.getName().equals("")) {
            if (null == menuDao.VerificationMenuName(menuEntity.getName())) {
                if (null == menuDao.VerificationMenuUrl(menuEntity.getUrl())) {
                    if (null == menuDao.VerificationMenuPermission(menuEntity.getPermission())) {
                        if (menuDao.AddMenu(menuEntity)) {
                            return Result.success(1, "添加成功");
                        } else {
                            return Result.error(0, "添加失败,服务器异常");
                        }
                    } else {
                        return Result.error(0, "按钮权限已存在");
                    }

                } else {
                    return Result.error(0, "按钮地址已存在");
                }
            } else {
                return Result.error(0, "按钮名字已存在");
            }
        } else {
            return Result.error(0, "有必填项为空");
        }
    }

    //删除目录，菜单，按钮
    public Result DeleteMenu(String id) {
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
                return Result.error(0, "目录删除失败");
            } else {
                return Result.success(1, "目录删除成功");
            }
        }

        //删除菜单
        if (menuEntity.getType() == 2) {
            if (menuDao.DeleteMenu(id)) {//删除按钮信息
                menuDao.DeleteMenuUser(id); //删除按钮和用户的绑定
                return Result.success(1, "菜单删除成功");
            } else {
                return Result.error(0, "菜单删除失败");
            }
        }

        //删除按钮----直接删除按钮信息和用户绑定的信息
        if (menuEntity.getType() == 3) {
            if (menuDao.DeleteMenu(id)) {//删除按钮信息
                menuDao.DeleteMenuUser(id); //删除按钮和用户的绑定
                return Result.success(1, "按钮删除成功");
            } else {
                return Result.error(0, "按钮删除失败");
            }
        } else {
            return Result.error(0, "服务器异常");
        }
    }


    //根据id查询菜单信息
    public MenuEntity IDFindResoucesinfo(String id) {
        MenuEntity menuEntity = menuDao.IDFindResoucesinfo(id);
        return menuEntity;
    }

    //修改菜单信息
    public Result UpdateMenus(MenuEntity menuEntity) throws Exception {
        int flag = 0;
        MenuEntity menuEntityname = menuDao.VerificationMenuName(menuEntity.getName());
        MenuEntity menuEntityurl = menuDao.VerificationMenuUrl(menuEntity.getUrl());


        if (null != menuEntityname) {
            if (menuEntityname.getId().equals(menuEntity.getId())) {

            } else {
                flag = 1;
                return Result.error(0, "菜单名已经存在");
            }
        }

        if (null != menuEntityurl) {
            if (menuEntityurl.getId().equals(menuEntity.getId())) {

            } else {
                flag = 1;
                return Result.error(0, "菜单地址已经存在");
            }
        }


        if (flag == 0) {
            if (menuDao.updateMenuinfo(menuEntity)) {
                return Result.error(1, "修改成功");
            } else {
                return Result.error(0, "修改失败");
            }

        } else {
            return Result.error(0, "服务器异常");
        }
    }

    public List<MenuEntity> FindAllsMenu() {
        List<MenuEntity> menuEntityList = menuDao.FindAllsMenu();
        return menuEntityList;
    }


}
