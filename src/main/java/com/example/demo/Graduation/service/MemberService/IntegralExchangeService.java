package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.IntegralDao;
import com.example.demo.Graduation.entity.IntegralExchange;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IntegralExchangeService {
    @Autowired
    private IntegralDao integralDao;

    //查询
    public PageInfo<IntegralExchange> FinAllIntegralInfo(int pageNo, int pageSize, IntegralExchange integralExchange) {
        PageHelper.startPage(pageNo, pageSize);
        List<IntegralExchange> list = integralDao.FinAllIntegralInfo(integralExchange);
        PageInfo<IntegralExchange> listpage = new PageInfo<IntegralExchange>(list);
        return listpage;
    }

    //添加兑换信息
    public Result AddIntegral(IntegralExchange integralExchange) {
        integralExchange.setId(UUID.randomUUID().toString());
        if (integralDao.AddIntegral(integralExchange)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //id查询积分信息
    public IntegralExchange IdFindIntegralExchangeInfo(String id) {
        IntegralExchange integralExchange = integralDao.IdFindIntegralExchangeInfo(id);
        return integralExchange;
    }

    //补仓
    public Result AddNumber(String id, int addnumber) {
        IntegralExchange integralExchange = integralDao.IdFindIntegralExchangeInfo(id);
        if (integralDao.UpdateNumber(id, integralExchange.getNumber() + addnumber)) {
            return Result.success(1, "补仓成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //减仓
    public Result ReduceNumber(String id, int addnumber) {
        IntegralExchange integralExchange = integralDao.IdFindIntegralExchangeInfo(id);
        if (addnumber > integralExchange.getNumber()) {
            return Result.error(0, "超过库存数量");
        } else {
            if (integralDao.UpdateNumber(id, integralExchange.getNumber() - addnumber)) {
                return Result.success(1, "减仓成功");
            } else {
                return Result.error(0, "减仓失败");
            }
        }
    }

    // 积分兑换


    //删除
    public Result DeleteIntegral(String id) {
        IntegralExchange integralExchange = integralDao.IdFindIntegralExchangeInfo(id);
        if (integralExchange.getNumber() == 0) {
            if (integralDao.DeleteIntegral(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");

            }
        } else {
            return Result.error(0, "库存没清空,无法删除");
        }
    }
}
