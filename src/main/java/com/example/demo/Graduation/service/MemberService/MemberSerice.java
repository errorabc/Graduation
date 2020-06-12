package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.MemberDao;
import com.example.demo.Graduation.Dao.OderDao.OderDao;
import com.example.demo.Graduation.Dao.VipDao.VipDao;
import com.example.demo.Graduation.entity.*;
import com.example.demo.Graduation.service.OderService.OderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class MemberSerice {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private VipDao vipDao;
    @Autowired
    private OderDao oderDao;

    //查询
    public PageInfo<MemberEntity> GetMemberInfoList(int pageNo, int pageSize, String name) {
        PageHelper.startPage(pageNo, pageSize);
        List<MemberEntity> meberlist = memberDao.MemberAllInfo(name);
        PageInfo<MemberEntity> memberpagelist = new PageInfo<>(meberlist);
        return memberpagelist;
    }

    //添加会员
    public Result AddMember(MemberEntity memberEntity, String vipid) {
        VipinfoEntity vip = vipDao.IdFindVipInfo(vipid);
        memberEntity.setId(UUID.randomUUID().toString());
        memberEntity.setTotal_consumption(vip.getMiconsumption());
        if (memberDao.AddMember(memberEntity) && memberDao.AddMemberVip(UUID.randomUUID().toString(), vipid, memberEntity.getId())) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }


    //查询会员名是否存在
    public Result VerificationMemberName(String name) {
        MemberEntity memberEntity = memberDao.VerificationMemberName(name);
        if (null != memberEntity) {
            return Result.error(0, "已存在");
        } else {
            return Result.success(1, "可以使用");

        }
    }


    //删除会员信息
    public Result DeleteMember(String id) {
        MemberEntity memberEntity = memberDao.IdFIndMemberInfo(id);
        int flag = oderDao.FindMenberOder(memberEntity.getName());
        if (flag == 0) {
            if (memberDao.DeleteMemberVip(id)) {
                if (memberDao.DeleteMember(id)) {
                    return Result.success(1, "删除成功");
                } else {
                    return Result.error(0, "删除失败");
                }
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "该会员有未完成的订单，不可被删除");
        }
    }

    //修改会员信息
    public Result UpdateMember(MemberEntity memberEntity, String vipid) {
        MemberEntity member = memberDao.VerificationMemberName(memberEntity.getName().trim());
        VipinfoEntity vip = vipDao.IdFindVipInfo(vipid);
        if (null != member) {
            if (member.getTotal_consumption().compareTo(vip.getMiconsumption()) > -1) {
                if (member.getId().equals(memberEntity.getId())) {
                    if (memberDao.UpdateMember(memberEntity) && memberDao.UpdateMemberVip(vipid, memberEntity.getId())) {
                        return Result.success(1, "修改成功");
                    } else {
                        return Result.error(0, "修改失败");
                    }
                } else {
                    return Result.error(0, "该账号已存在");
                }
            } else {
                return Result.error(0, "消费额不够，无法提升会员等级");
            }
        } else {
            if (memberDao.UpdateMember(memberEntity) && memberDao.UpdateMemberVip(vipid, memberEntity.getId())) {
                return Result.success(1, "修改成功");
            } else {
                return Result.error(0, "修改失败");
            }
        }
    }


    //根据ID查询会员信息
    public MemberEntity IdFIndMemberInfo(String id) {
        MemberEntity member = memberDao.IdFIndMemberInfo(id);
        return member;
    }


    //查看会员数量
    public int MemberNumber() {
        return memberDao.MemberNumber();
    }

    //根据会员名称查询会员信息
    public VipinfoEntity NameFindMemberInfo(String name) {
        MemberEntity memberEntity = memberDao.NameFindMemberInfo(name);
        VipinfoEntity vipinfoEntity = new VipinfoEntity();
        if (null != memberEntity) {
            System.out.println("不为空");
            vipinfoEntity.setName(memberEntity.getVip().getName());
            vipinfoEntity.setDiscount(memberEntity.getVip().getDiscount());
            return vipinfoEntity;
        } else {
            System.out.println("为空");
            vipinfoEntity.setName("非会员");
            vipinfoEntity.setDiscount(100);
            return vipinfoEntity;
        }
    }


    //账户余额充值
    public Result Recharge(String id, String name, BigDecimal money) {
        MemberEntity memberEntity = memberDao.IdFIndMemberInfo(id);
        if (memberEntity.getName().equals(name)) {
            if (memberDao.Recharge(id, name, money.add(memberEntity.getBalance()))) {
                return Result.success(1, "充值成功");
            } else {
                return Result.error(0, "充值失败");
            }
        } else {
            return Result.error(0, "该会员不存在");
        }
    }


}

