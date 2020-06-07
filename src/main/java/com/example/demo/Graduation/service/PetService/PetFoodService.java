package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.MemberDao.ActivityDao;
import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.PetDao.PetFoodDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.*;
import com.example.demo.Graduation.service.OderService.OderItemService;
import com.example.demo.Graduation.service.OderService.OderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//宠物食物管理
@Service
public class PetFoodService {
    @Autowired
    private PetFoodDao petFoodDao;
    @Autowired
    private OderItemService oderItemService;
    @Autowired
    private OderDao oderDao;
    @Autowired
    private ActivityDao activityDao;

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
        String id = UUID.randomUUID().toString();
        petfoodEntity.setId(id);
        if (petFoodDao.AddPetFood(petfoodEntity)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //删除食品信息
    public Result DeletePetFood(String id) {
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        List<OderEntity> oderlist = oderDao.ProductIdFindOderInfo(id);
        if (oderlist.size() > 0) {
            return Result.error(0, "此商品在未处理订单中,不可被删除");
        } else {
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
    public Result ReduceStock(String id, int number, String member_name, String activityid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(id);
        if (petfoodEntity.getFoodnumber() - number < 0) {
            return Result.error(0, "超过库存量");
        } else {
            int foodnumber = petfoodEntity.getFoodnumber() - number;
            if (petFoodDao.UpdatePetFoodNumber(id, foodnumber)) {
                OderItemEntity oderItemEntity = new OderItemEntity();
                oderItemEntity.setId(UUID.randomUUID().toString());
                oderItemEntity.setOder_no("fd-" + UUID.randomUUID().toString());
                oderItemEntity.setMember_name(member_name);
                oderItemEntity.setProduct_id(id);
                oderItemEntity.setProduct_name(petfoodEntity.getFoodname());
                oderItemEntity.setProduct_type("宠物食品");
                oderItemEntity.setCurrent_oder_price(petfoodEntity.getFoodprice());
                oderItemEntity.setNumber(number);
                oderItemEntity.setTotal_price(petfoodEntity.getFoodprice().multiply(new BigDecimal(number)));
                oderItemEntity.setCreate_time(DateTime.strToDateLong(df.format(new Date())));
                oderItemEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
                if (activityid.equals("不参加活动")) {
                    oderItemEntity.setActivityname("不参与活动");
                } else {
                    Activity activity = activityDao.IdFindActivityInfo(activityid);
                    oderItemEntity.setActivityname(activity.getActivity_name());
                }
                Result result = oderItemService.AddOderItem(oderItemEntity);//添加子订单
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


    //查询宠物食品种类的数量
    public int PetFoodNumber() {
        int mumber = petFoodDao.PetFoodNumber();
        return mumber;
    }

}
