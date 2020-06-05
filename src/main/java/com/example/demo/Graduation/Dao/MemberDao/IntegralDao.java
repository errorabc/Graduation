package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.IntegralExchange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IntegralDao {
    //查询
    List<IntegralExchange> FinAllIntegralInfo(IntegralExchange integralExchange);

    //添加积分兑换
    boolean AddIntegral(IntegralExchange integralExchange);

    //ID查询信息
    IntegralExchange IdFindIntegralExchangeInfo(@Param("id") String id);

    //补仓
    boolean UpdateNumber(@Param("id") String id, @Param("number") int number);

    //删除积分兑换信息
    boolean DeleteIntegral(@Param("id") String id);

    //
    boolean Exchange(@Param("id") String id, @Param("member_name") String member_name);

    //修改积分兑换信息
    boolean  UpdateIntegral(@Param("id") String id, @Param("type") String type,@Param("needredeem")int needredeem, @Param("name") String name);

}
