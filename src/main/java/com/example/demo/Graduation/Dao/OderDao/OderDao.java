package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OderDao {
    //添加总订单
    boolean AddOder(OderEntity oderEntity);

    //查询总订单
    List<OderEntity> FindAllOderInfo(OderEntity oderEntity);


    //ID查询订单信息
    OderEntity IdFindOderInfo(@Param("oder_no") String oder_no);
}
