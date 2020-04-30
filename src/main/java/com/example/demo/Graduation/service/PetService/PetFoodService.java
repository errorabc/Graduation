package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.PetDao.PetFoodDao;
import com.example.demo.Graduation.entity.PetfoodEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//宠物食物管理
@Service
public class PetFoodService {
    @Autowired
    private PetFoodDao petFoodDao;

    //查询
    public PageInfo<PetfoodEntity> FindAllPetFoodInfo(int PageNo, int PageSzie, PetfoodEntity petfoodEntity) {
        PageHelper.startPage(PageNo, PageSzie);
        List<PetfoodEntity> petfoodlist = petFoodDao.FindAllPetFoodInfo(petfoodEntity.getFoodname(), petfoodEntity.getFoodtype());
        PageInfo<PetfoodEntity> petfoodpagelist = new PageInfo<>(petfoodlist);
        return petfoodpagelist;
    }

    //查询食品名称是否重复
    public Result VerificationPetFoodName(String name) {
        PetfoodEntity petfoodEntity = petFoodDao.VerificationPetFoodName(name);
        if (null != petfoodEntity) {
            return Result.error(0, "已经存在");
        } else {
            return Result.success(1, "可以使用");
        }
    }

    //添加食品信息
    public Result AddPetFood(PetfoodEntity petfoodEntity) {
        petfoodEntity.setId(UUID.randomUUID().toString());
        if (petFoodDao.AddPetFood(petfoodEntity)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //删除食品信息
    public Result DeletePetFood(String id) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        if (petfoodEntity.getFoodnumber() == 0) {
            if (petFoodDao.DeletePetFood(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "库存没有清空为0,无法删除");
        }
    }

    //根绝ID查询食品信息
    public PetfoodEntity IdFindPetFoodInfo(String id) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        return petfoodEntity;
    }

    //添加库存
    public Result IncreaseStock(String id, int IncreasNumber) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        int number = petfoodEntity.getFoodnumber() + IncreasNumber;
        if (petFoodDao.UpdatePetFoodNumber(id, number)) {
            return Result.success(1, "添加库存成功");
        } else {
            return Result.error(0, "添加库存失败");
        }
    }


    //减少库存
    public Result ReduceStock(String id, int number) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        if (petfoodEntity.getFoodnumber() - number < 0) {
            return Result.error(0, "超过库存量");
        } else {
            int foodnumber = petfoodEntity.getFoodnumber() - number;
            if (petFoodDao.UpdatePetFoodNumber(id, foodnumber)) {
                return Result.success(1, "减少库存成功");
            } else {
                return Result.error(0, "减少库存失败");
            }
        }
    }


    //报废库存
    public Result Scrap(String id, int number) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        if (petfoodEntity.getFoodnumber() - number < 0) {
            return Result.error(0, "超过库存量");
        } else {
            int foodnumber = petfoodEntity.getFoodnumber() - number;
            if (petFoodDao.UpdatePetFoodNumber(id, foodnumber)) {  //减少库存
                //添加到报废仓库
                return Result.success(1, "减少库存成功");
            } else {
                return Result.error(0, "减少库存失败");
            }
        }
    }

    //修改食品信息
    public Result UpdatePetFood(PetfoodEntity petfoodEntity) {
        if (petFoodDao.UpdatePetFood(petfoodEntity)) {
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }
    }

}
