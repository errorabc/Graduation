package com.example.demo.Graduation.service.OderService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.OderDao.OderItemDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
            oderEntity.setStatus(10);
            oderEntity.setPayment_type("");
            oderEntity.setOder_type(oderItemEntity.getProduct_type());
            oderEntity.setPayment_time(DateTime.strToDateLong(df.format(new Date())));
            oderEntity.setCreate_time(DateTime.strToDateLong(df.format(new Date())));
            oderEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
            oderEntity.setActivityname(oderItemEntity.getActivityname());
            if (oderDao.AddOder(oderEntity)) {//添加总订单
                return Result.success(1, "订单添加成功");
            } else {
                return Result.error(0, "订单添加失败");
            }
        } else {
            return Result.error(0, "订单添加失败");
        }
    }

    //查询
    public PageInfo<OderItemEntity> FindAllOderItemInfo(int PageNo, int PageSzie, OderItemEntity oderItemEntity) {
        PageHelper.startPage(PageNo, PageSzie);
        List<OderItemEntity> oderlist = oderItemDao.FindAllOderItemInfo(oderItemEntity);
        PageInfo<OderItemEntity> oderpagelist = new PageInfo<OderItemEntity>(oderlist);
        return oderpagelist;
    }

    public JSONArray SaleEcharts(SaleEchartsEntity saleEchartsEntity) {
        if (saleEchartsEntity.getProduct_type().equals("all")) {
            JSONObject petjy = new JSONObject();
            petjy.put("name", "宠物寄养");
            petjy.put("mumber", oderItemDao.SaleMumber("宠物寄养", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));
            petjy.put("momey", oderItemDao.SaleMoney("宠物寄养", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));


            JSONObject petfood = new JSONObject();
            petfood.put("name", "宠物食品");
            petfood.put("mumber", oderItemDao.SaleMumber("宠物食品", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));
            petfood.put("momey", oderItemDao.SaleMoney("宠物食品", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));


            JSONObject petsp = new JSONObject();
            petsp.put("name", "宠物饰品");
            petsp.put("mumber", oderItemDao.SaleMumber("宠物饰品", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));
            petsp.put("momey", oderItemDao.SaleMoney("宠物饰品", saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime()));

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(petjy);
            jsonArray.add(petfood);
            jsonArray.add(petsp);
            return jsonArray;
        } else {
            JSONArray jsonArray = new JSONArray();
            List<ProducttypeSaleEchatrs> producttypeSaleEchatrs = oderItemDao.ProducttypeFindSaleEchatrsTime(saleEchartsEntity.getProduct_type(), saleEchartsEntity.getStartime(), saleEchartsEntity.getEndtime());
            for (ProducttypeSaleEchatrs item : producttypeSaleEchatrs) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", item.getPayment_time());
                jsonObject.put("money", item.getFinal_payment());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
    }


    //查询今日的销售金额
    public BigDecimal ToDaySales() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String starttime = format.format(new Date());
        String endtime = format.format(new Date());
        starttime = starttime + " " + "00:00:00";
        endtime = endtime + " " + "23:59:59";
        BigDecimal todaymoney = oderDao.ToDaySales(starttime, endtime);
        return todaymoney;
    }



}
