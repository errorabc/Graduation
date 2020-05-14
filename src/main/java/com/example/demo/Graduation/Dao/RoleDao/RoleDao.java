package com.example.demo.Graduation.Dao.RoleDao;

import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.RoleResourcesEntity;
import com.example.demo.Graduation.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {

    List<RoleEntity> DifferentRoleFindRoleInfo(@Param("rolename") String rolename);

    //根据角色的名称查询角色的信息
    RoleEntity RoleNameFindRoleInfo(@Param("rolename") String rolename);

    boolean DeleteUserRole(@Param("id") String id);

    List<RoleEntity> FindRoleInfo(@Param("name") String name);

    boolean AddRoleinfo(RoleEntity roleEntity);

    RoleEntity IdFindRoleInfo(@Param("id") String id);


    boolean UpdateRoleInfo(RoleEntity roleEntity);

    boolean DeleteRoleinfo(@Param("id") String id);

    List<RoleEntity> RoleIdFindUserRole(@Param("id") String id);

    boolean AddRoleResources(@Param("roleid") String roleid, @Param("resourcesid") String resourcesid);

    List<RoleResourcesEntity> FindRoleAllResources(@Param("roleid") String roleid);

    boolean DeleteRoleResources(@Param("roleid") String roleid);


}
