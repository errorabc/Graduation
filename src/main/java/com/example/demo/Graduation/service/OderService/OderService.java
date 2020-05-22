package com.example.demo.Graduation.service.OderService;

import com.example.demo.Graduation.Dao.MemberDao.MemberDao;
import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OderService {
    @Autowired
    private OderDao oderDao;
    @Autowired
    private MemberDao memberDao;

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


    //处理订单
    public Result HandleOder(OderEntity oderEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        oderEntity.setStatus(1);
        oderEntity.setPayment_time(DateTime.strToDateLong(df.format(new Date())));
        if (oderEntity.getPayment_type().equals("余额")) {//会员支付
            MemberEntity memberEntity = memberDao.NameFindMemberInfo(oderEntity.getMember_name());
            if (memberEntity.getBalance().compareTo(oderEntity.getFinal_payment()) == 1) {
                if (oderDao.HandleOder(oderEntity)) {
                    return Result.success(1, "处理成功");
                } else {
                    return Result.error(0, "处理失败");
                }
            } else {
                return Result.error(0, "余额不足,请使用其他方法支付");
            }
        } else {        //非会员支付
            if (oderDao.HandleOder(oderEntity)) {
                return Result.success(1, "处理成功");
            } else {
                return Result.error(0, "处理失败");
            }
        }

    }

    //删除订单
    public Result DeleteOder(String oder_no) {
        if (oderDao.DeleteOder(oder_no)) {
            if (oderDao.Delete_OderItem(oder_no)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "删除失败");
        }
    }

    //查询未处理的订单数量
    public int FindUnprocessedOderNumber() {
        int unprocessedoder = oderDao.FindUnprocessedOderNumber();
        return unprocessedoder;
    }


}
