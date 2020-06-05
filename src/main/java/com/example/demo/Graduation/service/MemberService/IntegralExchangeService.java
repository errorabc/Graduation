package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.ExchageRecordDao;
import com.example.demo.Graduation.Dao.MemberDao.IntegralDao;
import com.example.demo.Graduation.Dao.MemberDao.MemberDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.Exchangerecord;
import com.example.demo.Graduation.entity.IntegralExchange;
import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IntegralExchangeService {
    @Autowired
    private IntegralDao integralDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private ExchageRecordDao exchageRecordDao;

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


    //积分兑换
    public Result Exchange(String id, String member_name, int number) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        IntegralExchange integralExchange = integralDao.IdFindIntegralExchangeInfo(id);
        MemberEntity memberEntity = memberDao.NameFindMemberInfo(member_name.trim());
        if (null != memberEntity) {//判断是否为会员
            if (number > integralExchange.getNumber()) {//判断库存数量
                return Result.error(0, "库存不足");
            } else {
                if ((number * integralExchange.getNeedredeem()) > memberEntity.getIntegral()) {//判断积分是否充足
                    return Result.error(0, "积分不足");
                } else {
                    //扣除积分
                    memberDao.UpdateIntegral(id, member_name, memberEntity.getIntegral() - (number * integralExchange.getNeedredeem()));
                    Exchangerecord exchangerecord = new Exchangerecord();
                    exchangerecord.setId(UUID.randomUUID().toString());
                    exchangerecord.setMembername(member_name);
                    exchangerecord.setNumber(number);
                    exchangerecord.setProduct_id(integralExchange.getId());
                    exchangerecord.setProduct_name(integralExchange.getName());
                    exchangerecord.setProduct_type(integralExchange.getType());
                    exchangerecord.setRedeem_time(DateTime.strToDateLong(df.format(new Date())));
                    exchangerecord.setRedeemintegral(number * integralExchange.getNeedredeem());
                    //保存兑换记录
                    exchageRecordDao.AddExchageRecord(exchangerecord);
                    //减少库存
                    integralDao.UpdateNumber(id, integralExchange.getNumber() - number);
                    return Result.success(1, "兑换成功");
                }

            }
        } else {
            return Result.error(0, "非会员不能兑换");
        }

    }

    //修改积分兑换
    public Result UpdateIntegral(String id, String type, int needredeem, String name) {

        if (integralDao.UpdateIntegral(id, type, needredeem, name)) {
            return Result.success(1, "修改成功");
        } else {
            return Result.error(0, "修改失败");
        }

    }

}
