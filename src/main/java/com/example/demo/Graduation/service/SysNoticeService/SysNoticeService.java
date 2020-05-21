package com.example.demo.Graduation.service.SysNoticeService;

import com.example.demo.Graduation.Dao.SysNoticeDao.SysNoticeDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.SysNoticeEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysNoticeService {

    @Autowired
    private SysNoticeDao sysNoticeDao;

    //查询公告
    public PageInfo<SysNoticeEntity> FindAllSysNoticeInfo(int PageNo, int PageSzie, String name) {
        PageHelper.startPage(PageNo, PageSzie);
        List<SysNoticeEntity> sysNoticeEntityList = sysNoticeDao.FindAllSysNoticeInfo(name);
        PageInfo<SysNoticeEntity> sysnoticepagelist = new PageInfo<SysNoticeEntity>(sysNoticeEntityList);
        return sysnoticepagelist;
    }


    //添加
    public Result AddSnotice(SysNoticeEntity sysNoticeEntity) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();//当前登录的用户
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        sysNoticeEntity.setId(UUID.randomUUID().toString());
        sysNoticeEntity.setUsername(username);
        sysNoticeEntity.setCreatetime(DateTime.strToDateLong(df.format(new Date())));
        if (sysNoticeDao.AddSnotice(sysNoticeEntity)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //Id查询公告信息
    public SysNoticeEntity IdFindSysNoticeInfo(String id) {
        SysNoticeEntity sysNoticeEntity = sysNoticeDao.IdFindSysNoticeInfo(id);
        return sysNoticeEntity;
    }


    //修改公告
    public Result UpdateNotice(SysNoticeEntity sysNoticeEntity) {
        if (sysNoticeDao.UpdateNotice(sysNoticeEntity)) {
            System.out.println(sysNoticeEntity.getContent());
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }
    }


    //删除公告
    public Result DeleteNotice(String id) {
        if (sysNoticeDao.DeleteNotice(id)) {
            return Result.success(1, "删除成功");
        } else {
            return Result.error(0, "删除失败");
        }
    }

    //停止公告
    public Result StopNotice(String id) {
        if (sysNoticeDao.StopNotice(id)) {
            return Result.success(1, "停止成功");
        } else {
            return Result.error(0, "停止失败");
        }
    }

    //启动公告
    public Result StarNotice(String id) {
        SysNoticeEntity sysNoticeEntity = sysNoticeDao.FindStarNotice(id);
        if (null != sysNoticeEntity) {
            return Result.error(0, "已经启动了一个公告,请先停止原来公告");
        } else {
            if (sysNoticeDao.StarNotice(id)) {
                return Result.success(1, "启动成功");
            } else {
                return Result.error(0, "启动失败");
            }
        }

    }


    //显示公告中的公告信息
    public SysNoticeEntity ShowNotice() {
        SysNoticeEntity sysNoticeEntity = sysNoticeDao.ShowNotice();
        return sysNoticeEntity;
    }


}
