package com.example.demo.Graduation.service.LogService;

import com.example.demo.Graduation.Dao.LogDao.LogDao;
import com.example.demo.Graduation.entity.LogEntity.LogEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;

    public PageInfo<LogEntity> FindAllSysLog(int PageNo, int PageSzie) {
        PageHelper.startPage(PageNo, PageSzie);
        List<LogEntity> logEntityList = logDao.FindAllSysLog();
        PageInfo<LogEntity> pagelist = new PageInfo<LogEntity>(logEntityList);
        return pagelist;
    }

    public PageInfo FindSysLog(int PageNo, int PageSzie, String starttime, String endtime, String username) {
        PageHelper.startPage(PageNo, PageSzie);
        List<LogEntity> logEntityList = logDao.FindSysLog(starttime, endtime, username);
        PageInfo<LogEntity> pagelist = new PageInfo<LogEntity>(logEntityList);
        return pagelist;
    }

    public LogEntity IdFindLog(String id) {
        LogEntity logEntity = logDao.IdFindLog(id);
        return logEntity;
    }
}
