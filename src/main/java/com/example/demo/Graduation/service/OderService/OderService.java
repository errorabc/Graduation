package com.example.demo.Graduation.service.OderService;

import com.example.demo.Graduation.Dao.MemberDao.MemberDao;
import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.OderDao.OderItemDao;
import com.example.demo.Graduation.Dao.PetDao.PetFoodDao;
import com.example.demo.Graduation.Dao.PetDao.PetJewelryDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.*;
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
    @Autowired
    private OderItemDao oderItemDao;
    @Autowired
    private PetFoodDao petFoodDao;
    @Autowired
    private PetJewelryDao petJewelryDao;


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
        oderEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
        MemberEntity memberEntity = memberDao.NameFindMemberInfo(oderEntity.getMember_name());
        if (null != memberEntity) {
            ///会员余额付款
            if (oderEntity.getPayment_type().equals("余额")) {//会员余额支付
                if (memberEntity.getBalance().compareTo(oderEntity.getFinal_payment()) == 1) {
                    if (oderDao.HandleOder(oderEntity)) {  //修改订单信息
                        if (memberDao.Recharge(memberEntity.getId(), oderEntity.getMember_name(), memberEntity.getBalance().subtract(oderEntity.getFinal_payment()))) {
                            int integral = oderEntity.getFinal_payment().intValue();//本次消费积分
                            if (memberDao.UpdateIntegral(memberEntity.getId(), oderEntity.getMember_name(), memberEntity.getIntegral() + integral)) {
                                if (memberDao.UpdateTotalConsumption(memberEntity.getId(), oderEntity.getMember_name(), memberEntity.getTotal_consumption().add(oderEntity.getFinal_payment()))) {
                                    return Result.success(1, "处理成功");
                                } else {
                                    return Result.error(0, "更新总消费总额失败");
                                }
                            } else {
                                return Result.error(0, "更新积分失败");
                            }
                        } else {
                            return Result.error(0, "更新余额失败");
                        }
                    } else {
                        return Result.error(0, "处理失败");
                    }
                } else {
                    return Result.error(0, "余额不足,请使用其他方法支付");
                }
            }
            ///会员非余额支付
            else {
                if (oderDao.HandleOder(oderEntity)) {  //修改订单信息
                    int integral = oderEntity.getFinal_payment().intValue();//本次消费积分
                    if (memberDao.UpdateIntegral(memberEntity.getId(), oderEntity.getMember_name(), memberEntity.getIntegral() + integral)) {//更新积分
                        if (memberDao.UpdateTotalConsumption(memberEntity.getId(), oderEntity.getMember_name(), memberEntity.getTotal_consumption().add(oderEntity.getFinal_payment()))) {//更新总余额
                            return Result.success(1, "处理成功");
                        } else {
                            return Result.error(0, "更新总消费总额失败");
                        }
                    } else {
                        return Result.error(0, "更新积分失败");
                    }
                } else {
                    return Result.error(0, "处理失败");
                }
            }
        } else {//非会员付款
            if (oderDao.HandleOder(oderEntity)) {
                return Result.success(1, "处理成功");
            } else {
                return Result.error(0, "处理失败");
            }
        }
    }

    //删除订单
    public Result DeleteOder(String oder_no) {
        OderEntity oderEntity = oderDao.IdFindOderInfo(oder_no);
        if (oderEntity.getStatus() == 1 || oderEntity.getStatus() == 3) {
            return Result.error(0, "已完成的订单不可被删除");
        } else {
            //删除未支付的订单，同时商品库存补充回去
            OderItemEntity oderItemEntity = oderItemDao.IdFindOderItemInfo(oder_no);
            if (oderItemEntity.getProduct_type().equals("宠物食品")) {
                PetfoodEntity petfoodEntity = petFoodDao.IdFindPetFoodInfo(oderItemEntity.getProduct_id());
                petFoodDao.UpdatePetFoodNumber(oderItemEntity.getProduct_id(), petfoodEntity.getFoodnumber() + oderItemEntity.getNumber());
            } else if (oderItemEntity.getProduct_type().equals("宠物饰品")) {
                PetjewelryEntity petjewelryEntity = petJewelryDao.IdFindPetjewelryInfo(oderItemEntity.getProduct_id());
                petJewelryDao.UpdatePetJewelryNumber(oderItemEntity.getProduct_id(), petjewelryEntity.getPetjewelrynumber() + oderItemEntity.getNumber());
            }
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
    }

    //查询未处理的订单数量
    public int FindUnprocessedOderNumber() {
        int unprocessedoder = oderDao.FindUnprocessedOderNumber();
        return unprocessedoder;
    }

    //申请退款
    public Result ReFund(OderEntity oderEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OderEntity oderEntity1 = oderDao.IdFindOderInfo(oderEntity.getOder_no());
        oderEntity.setStatus(3);
        oderEntity.setUpdate_time(DateTime.strToDateLong(df.format(new Date())));
        if (oderEntity.getRefundmoney().compareTo(oderEntity1.getFinal_payment()) == 1) {
            return Result.error(0, "退款金额不能超过最终支付金额");
        } else {
            if (oderDao.ApplyRefund(oderEntity)) {
                return Result.success(1, "退款成功");
            } else {
                return Result.error(0, "退款失败");
            }

        }
    }


}
