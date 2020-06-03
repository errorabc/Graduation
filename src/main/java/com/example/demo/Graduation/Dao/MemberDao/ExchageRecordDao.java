package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.Exchangerecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//积分兑换记录
@Mapper
public interface ExchageRecordDao {

    boolean AddExchageRecord(Exchangerecord exchangerecord);

    List<Exchangerecord> FindAllExchageRecordInfo(@Param("membername")String membername);
}
