package com.example.demo.Graduation.Dao.RoleDao;

import com.example.demo.Graduation.entity.RoleEntity.RoleEntity;
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

}
