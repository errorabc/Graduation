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

}
