package com.example.demo.Graduation.service.VipService;

import com.example.demo.Graduation.Dao.VipDao.VipDao;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
