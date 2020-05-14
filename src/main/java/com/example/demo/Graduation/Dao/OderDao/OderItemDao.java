package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OderItemDao {
    //
    boolean AddOderItem(OderItemEntity oderItemEntity);

    //查询所有
    List<OderItemEntity> FindAllOderItemInfo(OderItemEntity oderItemEntity);


}
