package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.PetDao.PetFoodDao;
import com.example.demo.Graduation.Dao.PetDao.PetJewelryDao;
import com.example.demo.Graduation.entity.PetfoodEntity;
import com.example.demo.Graduation.entity.PetjewelryEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//宠物饰品管理
@Service
public class PetJewelryService {
    @Autowired
    private PetJewelryDao petJewelryDao;

    //查询
    public PageInfo<PetjewelryEntity> FindAllPetFoodInfo(int PageNo, int PageSzie, PetjewelryEntity petjewelryEntity) {
        PageHelper.startPage(PageNo, PageSzie);
        List<PetjewelryEntity> petjewelrylist = petJewelryDao.FindAllPetFoodInfo(petjewelryEntity.getPetjewelryname(), petjewelryEntity.getPetjewelrytype());
        PageInfo<PetjewelryEntity> petjewelrypagelist = new PageInfo<>(petjewelrylist);
        return petjewelrypagelist;
    }

    //添加
    public Result AdddPetFood(PetjewelryEntity petjewelryEntity) {
        petjewelryEntity.setId(UUID.randomUUID().toString());
        if (petJewelryDao.AdddPetJewelry(petjewelryEntity)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //查询名字是否重复
    public Result VerificationPetJewelryName(String name) {
        PetjewelryEntity petjewelryEntity = petJewelryDao.VerificationPetJewelryName(name);
        if (null != petjewelryEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");
        }
    }

    //删除
    public Result DeletePetjewelry(String id) {
        if (petJewelryDao.DeletePetjewelry(id)) {
            return Result.success(1, "删除成功");
        } else {
            return Result.error(0, "删除失败");
        }
    }

    //ID查询饰品信息
    public PetjewelryEntity IdFindPetjewelryInfo(String id) {
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        return petjewelryEntity;
    }

    //添加库存
    public Result IncreaseStock(String id, int IncreasNumber) {
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        if (petJewelryDao.UpdatePetJewelryNumber(id, petjewelryEntity.getPetjewelrynumber() + IncreasNumber)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }


    //减少库存
    public Result ReduceStock(String id, int IncreasNumber) {
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        if (petjewelryEntity.getPetjewelrynumber() - IncreasNumber < 0) {
            return Result.error(0, "超过库存量");
        } else {
            if (petJewelryDao.UpdatePetJewelryNumber(id, petjewelryEntity.getPetjewelrynumber() - IncreasNumber)) {
                return Result.success(1, "减少库存成功");
            } else {
                return Result.error(0, "减少库存失败");
            }
        }

    }

}
