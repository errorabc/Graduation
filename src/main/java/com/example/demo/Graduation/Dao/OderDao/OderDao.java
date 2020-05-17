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


    //处理订单
    boolean HandleOder(OderEntity oderEntity);


    //删除总订单
    boolean DeleteOder(@Param("oder_no") String oder_no);

    //删除子订单
    boolean Delete_OderItem(@Param("oder_no") String oder_no);

    //查询未处理的订单数量
    int FindUnprocessedOderNumber();
}
