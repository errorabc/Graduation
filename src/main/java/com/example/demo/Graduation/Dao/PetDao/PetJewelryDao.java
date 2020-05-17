package com.example.demo.Graduation.Dao.PetDao;

import com.example.demo.Graduation.entity.PetjewelryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//宠物饰品管理
@Mapper
public interface PetJewelryDao {

    List<PetjewelryEntity> FindAllPetFoodInfo(@Param("petjewelryname") String petjewelryname, @Param("petjewelrytype") String petjewelrytype);

    //添加
    boolean AdddPetJewelry(PetjewelryEntity petjewelryEntity);

    //查询饰品名字是否重复
    PetjewelryEntity VerificationPetJewelryName(@Param("petjewelryname") String petjewelryname);

    //删除
    boolean DeletePetjewelry(@Param("id") String id);


    //ID查询饰品信息
    PetjewelryEntity IdFindPetjewelryInfo(@Param("id") String id);


    //修改库存-入库，出库，报废
    boolean UpdatePetJewelryNumber(@Param("id") String id, @Param("petjewelrynumber") int petjewelrynumber);


    //修改
    boolean UpdatePetjewelry(PetjewelryEntity petjewelryEntity);

    //查询饰品种类的数量
    int PetjewelryNumber();

}
