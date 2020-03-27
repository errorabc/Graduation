package com.example.demo.Graduation.service.VipService;

import com.example.demo.Graduation.Dao.VipDao.VipDao;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VipService {
    @Autowired
    private VipDao vipDao;

    //查询所有VIP的信息
    public PageInfo<VipinfoEntity> FindAllVipInfo(int PageNo, int PageSzie, String name) {
        PageHelper.startPage(PageNo, PageSzie);
        List<VipinfoEntity> viplist = vipDao.FindAllVipInfo(name);
        PageInfo<VipinfoEntity> vipinfoEntityPageInfo = new PageInfo<VipinfoEntity>(viplist);
        return vipinfoEntityPageInfo;
    }

    //验证VIP名字是否重复
    public Result VerificationVipName(String name) {
        if (name.equals("")) {
            return Result.error(0, "VIP名字为空");
        } else {
            VipinfoEntity vipinfoEntity = vipDao.VerificationVipName(name);
            if (null != vipinfoEntity) {
                return Result.error(0, "VIP名字重复");
            } else {
                return Result.success(1, "可以使用");
            }
        }
    }

    //添加VIP
    public Result AddVip(VipinfoEntity vipinfoEntity) {
        vipinfoEntity.setId(UUID.randomUUID().toString());
        if (null != vipinfoEntity) {
            if (vipDao.AddVip(vipinfoEntity)) {
                return Result.success(1, "添加成功");
            } else {
                return Result.error(0, "添加失败");
            }
        } else {
            return Result.error(0, "VIP信息为空,提交无效");
        }
    }

    //根据ID查询VIP信息
    public VipinfoEntity IdFindVipInfo(String id) {
        VipinfoEntity vipinfoEntity = vipDao.IdFindVipInfo(id);
        return vipinfoEntity;
    }
}
