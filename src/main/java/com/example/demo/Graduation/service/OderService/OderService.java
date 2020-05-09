package com.example.demo.Graduation.service.OderService;

import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OderService {
    @Autowired
    private OderDao oderDao;

    //添加总订单与子订单
    public PageInfo<OderEntity> FindAllOderInfo(OderEntity oderEntity, int PageNo, int PageSzie) {
        PageHelper.startPage(PageNo, PageSzie);
        List<OderEntity> oderlist = oderDao.FindAllOderInfo(oderEntity);
        PageInfo<OderEntity> oderpagelist = new PageInfo<>(oderlist);
        return oderpagelist;
    }

    //ID查询订单信息
    public OderEntity IdFindOderInfo(String id) {
        OderEntity oderEntity = oderDao.IdFindOderInfo(id);
        return oderEntity;
    }
}
