package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.MemberDao.ActivityDao;
import com.example.demo.Graduation.Dao.PetDao.PetFoodDao;
import com.example.demo.Graduation.Dao.PetDao.PetJewelryDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.*;
import com.example.demo.Graduation.service.OderService.OderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//宠物饰品管理
@Service
public class PetJewelryService {
    @Autowired
    private PetJewelryDao petJewelryDao;
    @Autowired
    private OderItemService oderItemService;
    @Autowired
    private ActivityDao activityDao;

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
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        if (petjewelryEntity.getPetjewelrynumber() == 0) {
            if (petJewelryDao.DeletePetjewelry(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "库存没有清空为0,无法删除");
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
    public Result ReduceStock(String id, int IncreasNumber, String member_name,String activityid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        if (petjewelryEntity.getPetjewelrynumber() - IncreasNumber < 0) {
            return Result.error(0, "超过库存量");
        } else {
            if (petJewelryDao.UpdatePetJewelryNumber(id, petjewelryEntity.getPetjewelrynumber() - IncreasNumber)) {
                OderItemEntity oderItemEntity = new OderItemEntity();
                oderItemEntity.setId(UUID.randomUUID().toString());
                oderItemEntity.setOder_no("sp-" + UUID.randomUUID().toString());
                oderItemEntity.setMember_name(member_name);
                oderItemEntity.setProduct_id(id);
                oderItemEntity.setProduct_name(petjewelryEntity.getPetjewelryname());
                oderItemEntity.setProduct_type("宠物饰品");
                oderItemEntity.setCurrent_oder_price(petjewelryEntity.getPetjewelryprice());
                oderItemEntity.setNumber(IncreasNumber);
                oderItemEntity.setTotal_price(petjewelryEntity.getPetjewelryprice().multiply(new BigDecimal(IncreasNumber)));
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
    public Result Scrap(String id, int IncreasNumber) {
        PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(id);
        if (petjewelryEntity.getPetjewelrynumber() - IncreasNumber < 0) {
            return Result.error(0, "超过库存量");
        } else {
            if (petJewelryDao.UpdatePetJewelryNumber(id, petjewelryEntity.getPetjewelrynumber() - IncreasNumber)) {
                return Result.success(1, "报废成功");
            } else {
                return Result.error(0, "报废失败");
            }
        }

    }

    //修改饰品信息
    public Result UpdatePetjewelry(PetjewelryEntity petjewelryEntity) {
        if (petJewelryDao.UpdatePetjewelry(petjewelryEntity)) {
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }
    }

    //查询饰品种类的数量
    public int PetjewelryNumber() {
        int flag=petJewelryDao.PetjewelryNumber();
        return flag;
    }
}
