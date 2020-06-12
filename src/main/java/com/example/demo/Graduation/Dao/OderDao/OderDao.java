package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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


    //今日销售金额
    BigDecimal ToDaySales(@Param("starttime") String starttime, @Param("endtime") String endtime);

    //申请退款
    boolean ApplyRefund(OderEntity oderEntity);


    //查询商品是否在未处理订单中
    List<OderEntity> ProductIdFindOderInfo(@Param("product_id") String product_id);

    //查询会员拥有未完成的订单
    int FindMenberOder(@Param("member_name") String member_name);
}
