package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.math.BigDecimal;
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

    //删除会员和VIP的关联
    boolean DeleteMemberVip(@Param("id") String id);

    //根据会员ID查询会员信息

    MemberEntity IdFIndMemberInfo(@Param("id") String id);

    //修改会员信息
    boolean UpdateMember(MemberEntity memberEntity);

    //修改会员和VIP关联
    boolean UpdateMemberVip(@Param("vipid") String vipid, @Param("memberid") String memberid);

    //查看会员数量
    int MemberNumber();

    //根据会员名称查询会员信息
    MemberEntity NameFindMemberInfo(@Param("name") String name);


    //会员余额充值
    boolean Recharge(@Param("id") String id, @Param("name") String name, @Param("money") BigDecimal money);

    //修改积分
    boolean UpdateIntegral(@Param("id") String id, @Param("name") String name, @Param("integral") int integral);

    //修改总消费
    boolean UpdateTotalConsumption(@Param("id") String id, @Param("name") String name, @Param("money") BigDecimal money);



}
