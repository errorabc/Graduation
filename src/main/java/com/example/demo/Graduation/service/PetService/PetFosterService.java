package com.example.demo.Graduation.service.PetService;

import com.example.demo.Graduation.Dao.PetDao.PetFosterDao;
import com.example.demo.Graduation.entity.PetfosterEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//宠物寄养管理
@Service
public class PetFosterService {
    @Autowired
    private PetFosterDao petFosterDao;

    //查询
    public PageInfo<PetfosterEntity> FindAllInfo(int PageNo, int PageSzie, String feedername, String feederphone) {
        PageHelper.startPage(PageNo, PageSzie);
        List<PetfosterEntity> petfosterList = petFosterDao.FindAllInfo(feedername, feederphone);
        PageInfo<PetfosterEntity> pagelist = new PageInfo<>(petfosterList);
        return pagelist;
    }

    //添加寄养
    public Result AddPetFoster(PetfosterEntity petfosterEntity) {
        petfosterEntity.setId(UUID.randomUUID().toString());
        petfosterEntity.setFosterstartime(new Date());
        if (petFosterDao.AddPetFoster(petfosterEntity)) {
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
        if (petFosterDao.DeletePetFoster(id)) {
            return Result.success(1, "删除成功");
        } else {
            return Result.error(0, "删除失败");
        }
    }


    //修改
    public Result UpdatePetFoster(PetfosterEntity petfosterEntity) {
        if (petFosterDao.UpdatePetFoster(petfosterEntity)) {
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }
    }
}
