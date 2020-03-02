package com.example.demo.Graduation.Dao.UserDao;

import com.example.demo.Graduation.entity.UserEntity.UserEntity;
import com.example.demo.Graduation.entity.UserRole.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    List<UserEntity> FindUserInfo(@Param("username") String username);

    UserEntity Finduserinfo2(@Param("username") String username);

    List<UserEntity> RoleFindUserinfo(@Param("rolename") String rolename);

    UserEntity UsernameFindUser(@Param("username") String username);

    boolean AddUserInfo(UserEntity userEntity);

    boolean AddUserRoleInfo(@Param("userid") String userid, @Param("roleid") String roleid);

    boolean SealUser(@Param("id") String id);

    boolean RelieveSealUser(@Param("id") String id);

    UserEntity UserIdFindUserinfo(@Param("id") String id);

    boolean DeleteUserInfo(@Param("id") String id);

    boolean UpdateUserRole(@Param("userid") String userid, @Param("roleid") String roleid);

    boolean UpdateUserPassword(@Param("password") String password, @Param("id") String id);

}
