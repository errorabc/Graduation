package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.ExchageRecordDao;
import com.example.demo.Graduation.entity.Exchangerecord;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRecordService {
    @Autowired
    private ExchageRecordDao exchageRecordDao;

    //查询
    public PageInfo<Exchangerecord> FindAllExchageRecordInfo(int pageNo, int pageSize, String membername) {
        PageHelper.startPage(pageNo, pageSize);
        List<Exchangerecord> list = exchageRecordDao.FindAllExchageRecordInfo(membername);
        PageInfo<Exchangerecord> pagelist = new PageInfo<Exchangerecord>(list);
        return pagelist;
    }
}
