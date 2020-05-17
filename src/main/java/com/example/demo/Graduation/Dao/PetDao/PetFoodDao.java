package com.example.demo.Graduation.Dao.PetDao;

import com.example.demo.Graduation.entity.PetfoodEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//宠物寄养管理
@Mapper
public interface PetFoodDao {

    //查询
    List<PetfoodEntity> FindAllPetFoodInfo(@Param("foodname") String foodname, @Param("foodtype") String foodtype);

    //查询食品名称是否重复
    PetfoodEntity VerificationPetFoodName(@Param("foodname") String foodname);

    //添加食品
    boolean AddPetFood(PetfoodEntity petfoodEntity);

    //删除食品
    boolean DeletePetFood(@Param("id") String id);

    //根据ID查询食品信息
    PetfoodEntity IdFindPetFoodInfo(@Param("id") String id);

    //修改库存
    boolean UpdatePetFoodNumber(@Param("id") String id, @Param("foodnumber") int foodnumber);

    //修改宠物食品信息
    boolean UpdatePetFood(PetfoodEntity petfoodEntity);

    //查询宠物食品种类的数量
    int PetFoodNumber();


}
