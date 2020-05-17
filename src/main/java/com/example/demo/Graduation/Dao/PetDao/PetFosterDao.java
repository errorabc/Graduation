package com.example.demo.Graduation.Dao.PetDao;

import com.example.demo.Graduation.entity.PetfosterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//宠物食品管理
@Mapper
public interface PetFosterDao {
    //查询
    List<PetfosterEntity> FindAllInfo(@Param("feedername") String feedername, @Param("feederphone") String feederphone);

    //添加
    boolean AddPetFoster(PetfosterEntity petfosterEntity);

    //根据ID查询信息
    PetfosterEntity IdFindPetFosterinfo(@Param("id") String id);

    //删除
    boolean DeletePetFoster(@Param("id") String id);

    //修改
    boolean UpdatePetFoster(PetfosterEntity petfosterEntity);

    //结束寄养
    boolean StopFoster(@Param("id") String id);

    //查询正在寄养的宠物数量
    int FindUnfinishedPetFosterMumber();
}
