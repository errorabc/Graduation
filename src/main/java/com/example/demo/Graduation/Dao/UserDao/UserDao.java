package com.example.demo.Graduation.Dao.UserDao;

import com.example.demo.Graduation.entity.RoleEntity;
import com.example.demo.Graduation.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    UserEntity FindUserInfo(@Param("username") String username);

    RoleEntity Finduserinfo2(@Param("username") String username);

    List<UserEntity> UserNameFindUserInfo(@Param("username") String username, @Param("rolename") String rolename, @Param("name") String name);

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
