package com.example.demo.Graduation.Dao.MenuDao;

import com.example.demo.Graduation.entity.MenuEntity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MenuDao {

    List<MenuEntity> FindMenusType1(@Param("rolename") String rolename, @Param("userid") String userid);

    List<MenuEntity> FindMenusParentId(@Param("username") String username);

    List<MenuEntity> UserNameFindPerssiom(@Param("username") String username, @Param("rolename") String rolename);

    List<MenuEntity> RoleUsernameFindButton(@Param("username") String username, @Param("id") String id);

    MenuEntity Menu2NameFindID(@Param("name") String name);

    List<MenuEntity> FindAllMenu();

    boolean DeleteMenu(@Param("id") String id);

    boolean DeleteMenuUser(@Param("id") String id);

    MenuEntity VerificationMenuName(@Param("name") String name);

    MenuEntity VerificationMenuUrl(@Param("url") String url);

    MenuEntity VerificationMenuPermission(@Param("permission") String permission);

    boolean AddMenu(MenuEntity menuEntity);

    MenuEntity IDFindResoucesinfo(@Param("id") String id);

    List<MenuEntity> ParentIdFindResoucesinfo(@Param("id") String id);

}
