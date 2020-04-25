package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.MemberDao;
import com.example.demo.Graduation.Dao.VipDao.VipDao;
import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Service
public class MemberSerice {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private VipDao vipDao;

    //查询
    public PageInfo<MemberEntity> GetMemberInfoList(int pageNo, int pageSize, String name) {
        PageHelper.startPage(pageNo, pageSize);
        List<MemberEntity> meberlist = memberDao.MemberAllInfo(name);
        PageInfo<MemberEntity> memberpagelist = new PageInfo<>(meberlist);
        return memberpagelist;
    }

    //添加会员
    public Result AddMember(MemberEntity memberEntity, String vipid) {
        memberEntity.setId(UUID.randomUUID().toString());
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
        if (memberDao.DeleteMemberVip(id)) {
            if (memberDao.DeleteMember(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        } else {
            return Result.error(0, "删除失败");
        }
    }

    //修改会员信息
    public Result UpdateMember(MemberEntity memberEntity, String vipid) {
        MemberEntity member = memberDao.VerificationMemberName(memberEntity.getName().trim());
        if (null != member) {
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

}
