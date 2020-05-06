package com.example.demo.Graduation.service.OderService;

import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.OderDao.OderItemDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OderItemService {
    @Autowired
    private OderItemDao oderItemDao;
    @Autowired
    private OderDao oderDao;

    //添加子订单
    public Result AddOderItem(OderItemEntity oderItemEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if (oderItemDao.AddOderItem(oderItemEntity)) {//添加子订单
            OderEntity oderEntity = new OderEntity();
            oderEntity.setId(UUID.randomUUID().toString());
            oderEntity.setOder_no(oderItemEntity.getOder_no());
            oderEntity.setMember_name(oderItemEntity.getMember_name());
            oderEntity.setPayment(oderItemEntity.getTotal_price());
            oderEntity.setStatus(0);
            oderEntity.setPayment_type("现金");
            oderEntity.setPayment_time(DateTime.strToDateLong(df.format(new Date())));
            oderEntity.setCreate_time(DateTime.strToDateLong(df.format(new Date())));
            oderEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
            if (oderDao.AddOder(oderEntity)) {//添加总订单
                return Result.success(1, "订单添加成功");
            } else {
                return Result.error(0, "订单添加失败");
            }
        } else {
            return Result.error(0, "订单添加失败");
        }
    }

}
