package com.example.demo.Graduation.Dao.VipDao;

import com.example.demo.Graduation.entity.VipinfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VipDao {

    List<VipinfoEntity> FindAllVipInfo(@Param("name") String name);

    VipinfoEntity VerificationVipName(@Param("name") String name);

    boolean AddVip(VipinfoEntity vipinfoEntity);

    VipinfoEntity IdFindVipInfo(@Param("id") String id);

    boolean UpdateVip(VipinfoEntity vipinfoEntity);

    //查询VIP的会员的关联
    int IdFindVipMemberinfo(@Param("vipid") String vipid);

    //删除VIP信息
    boolean DeleteVip(@Param("id") String id);


}
