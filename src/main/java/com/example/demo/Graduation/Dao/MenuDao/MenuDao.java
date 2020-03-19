package com.example.demo.Graduation.Dao.MenuDao;

import com.example.demo.Graduation.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface MenuDao {

    List<MenuEntity> FindMenusType1(@Param("roleid") String roleid);

    List<MenuEntity> FindMenusType2(@Param("roleid") String roleid);


    List<MenuEntity> FindMenusType11();

    List<MenuEntity> FindMenusType22();


    List<MenuEntity> UserNameFindPerssiom(@Param("roleid") String roleid);

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

    boolean updateMenuinfo(MenuEntity menuEntity);

    List<MenuEntity> FindAllsMenu();

}
