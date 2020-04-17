package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

@Mapper
public interface MemberDao {

    //查询会员信息
    List<MemberEntity> MemberAllInfo(@Param("name") String name);

    //添加会员
    boolean AddMember(MemberEntity memberEntity);

    //添加会员与VIP信息关联

    boolean AddMemberVip(@Param("id") String id, @Param("vipid") String vipid, @Param("memberid") String memberid);

    //查询会员名是否存在

    MemberEntity VerificationMemberName(@Param("name") String name);

    //删除会员信息
    boolean DeleteMember(@Param("id") String id);

    //删除会员
    boolean DeleteMemberVip(@Param("id") String id);

    //根据会员ID查询会员信息

    MemberEntity IdFIndMemberInfo(@Param("id")String id);

}
