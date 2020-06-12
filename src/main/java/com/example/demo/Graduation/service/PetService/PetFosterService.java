package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.PetDao.PetFosterDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.entity.PetfosterEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.OderService.OderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//宠物寄养管理
@Service
public class PetFosterService {
    @Autowired
    private PetFosterDao petFosterDao;
    @Autowired
    private OderItemService oderItemService;
    @Autowired
    private OderDao oderDao;


    //查询
    public PageInfo<PetfosterEntity> FindAllInfo(int PageNo, int PageSzie, String feedername, String feederphone) {
        PageHelper.startPage(PageNo, PageSzie);
        List<PetfosterEntity> petfosterList = petFosterDao.FindAllInfo(feedername, feederphone);
        PageInfo<PetfosterEntity> pagelist = new PageInfo<>(petfosterList);
        return pagelist;
    }

    //添加寄养
    public Result AddPetFoster(PetfosterEntity petfosterEntity) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String id = UUID.randomUUID().toString();
        petfosterEntity.setId(id);
        petfosterEntity.setFosterstartime(new Date());
        if (petFosterDao.AddPetFoster(petfosterEntity)) {
            ///生成子订单
            if (petfosterEntity.getOderstatus() == 0) {
                OderItemEntity oderItemEntity = new OderItemEntity();
                oderItemEntity.setId(UUID.randomUUID().toString());
                oderItemEntity.setOder_no("jy-" + UUID.randomUUID().toString());
                oderItemEntity.setMember_name(petfosterEntity.getFeedername());
                oderItemEntity.setProduct_id(id);
                oderItemEntity.setProduct_name(petfosterEntity.getFeedername() + " 的宠物寄养");
                oderItemEntity.setProduct_type("宠物寄养");
                oderItemEntity.setCurrent_oder_price(petfosterEntity.getFosterprice());
                oderItemEntity.setNumber(1);
                oderItemEntity.setTotal_price(petfosterEntity.getFosterprice().multiply(new BigDecimal((int) 1)));
                oderItemEntity.setCreate_time(DateTime.strToDateLong(df.format(new Date())));
                oderItemEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
                Result result = oderItemService.AddOderItem(oderItemEntity);//添加子订单
            }
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //根据ID查询信息
    public PetfosterEntity IdFindPetFosterinfo(String id) {
        PetfosterEntity petfosterEntity = petFosterDao.IdFindPetFosterinfo(id);
        return petfosterEntity;
    }

    //删除
    public Result DeletePetFoster(String id) {
        List<OderEntity> oderlist = oderDao.ProductIdFindOderInfo(id);
        if (oderlist.size() > 0) {
            return Result.error(0, "此商品在未处理订单中,不可被删除");
        } else {
            if (petFosterDao.DeletePetFoster(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        }
    }


    //修改寄养信息
    public Result UpdatePetFoster(PetfosterEntity petfosterEntity) {
        PetfosterEntity petfosterEntity1 = petFosterDao.IdFindPetFosterinfo(petfosterEntity.getId());
        if (petfosterEntity.getState() == 1) {
            return Result.error(0, "寄养结束不可在修改信息");
        } else {
            if (petFosterDao.UpdatePetFoster(petfosterEntity)) {
                return Result.success(1, "修改成功");
            } else {
                return Result.error(0, "修改失败");
            }
        }
    }


    //结束寄养
    public Result StopFoster(String id) {
        if (petFosterDao.StopFoster(id)) {
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }

    }

    //查询正在寄养的宠物数量
    public int FindUnfinishedPetFosterMumber() {
        int mumber = petFosterDao.FindUnfinishedPetFosterMumber();
        return mumber;
    }

}
