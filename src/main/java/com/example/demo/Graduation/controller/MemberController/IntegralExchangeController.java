package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.IntegralExchange;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.IntegralExchangeService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

//积分兑换管理
@Controller
@RequestMapping(value = "/integralexchange")
public class IntegralExchangeController {
    @Autowired
    private IntegralExchangeService integralExchangeService;

    @GetMapping(value = "")
    public String GetIntegralExchange(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, IntegralExchange integralExchange) {
        PageInfo<IntegralExchange> integrallist = integralExchangeService.FinAllIntegralInfo(pageNo, pageSize, integralExchange);
        model.addAttribute("integral", integrallist);
        model.addAttribute("name", integralExchange.getName());
        model.addAttribute("type", integralExchange.getType());
        return "Integral/Integrallist";
    }

    //跳转到添加界面
    @GetMapping(value = "/GetAddIntegral")
    public String GetAddIntegral() {
        return "Integral/Integraladd";
    }

    //添加积分兑换
    @LogAop("添加积分兑换信息")
    @PostMapping(value = "/AddIntegral")
    @RequiresPermissions("integralexchange:add")
    @ResponseBody
    public Result AddIntegral(IntegralExchange integralExchange) {
        Result result = integralExchangeService.AddIntegral(integralExchange);
        return result;
    }

    //跳转到补仓界面
    @GetMapping(value = "GetAddNumber")
    public String GetAddNumber(@RequestParam("id") String id, Model model) {
        IntegralExchange integralExchange = integralExchangeService.IdFindIntegralExchangeInfo(id);
        model.addAttribute("integral", integralExchange);
        return "Integral/addnumber";
    }

    //跳转到减仓界面
    @GetMapping(value = "GetReduceNumber")
    public String GetReduceNumber(@RequestParam("id") String id, Model model) {
        IntegralExchange integralExchange = integralExchangeService.IdFindIntegralExchangeInfo(id);
        model.addAttribute("integral", integralExchange);
        return "Integral/reducenumber";
    }

    //跳转到兑换界面
    @GetMapping(value = "GetExchange")
    public String GetExchange(@RequestParam("id") String id, Model model) {
        IntegralExchange integralExchange = integralExchangeService.IdFindIntegralExchangeInfo(id);
        model.addAttribute("integral", integralExchange);
        return "Integral/exchange";
    }


    //补仓
    @LogAop("添加积分补仓")
    @PostMapping(value = "/AddNumber")
    @RequiresPermissions("integralexchange:addnumber")
    @ResponseBody
    public Result AddNumber(@RequestParam("id") String id, @RequestParam("addnumber") int addnumber) {
        Result result = integralExchangeService.AddNumber(id, addnumber);
        return result;
    }

    //减仓
    @LogAop("积分兑换减仓")
    @PostMapping(value = "/ReduceNumber")
    @RequiresPermissions("integralexchange:reducenumber")
    @ResponseBody
    public Result ReduceNumber(@RequestParam("id") String id, @RequestParam("reducenumber") int reducenumber) {
        Result result = integralExchangeService.ReduceNumber(id, reducenumber);
        return result;
    }


    //删除
    @LogAop("删除积分兑换信息")
    @PostMapping(value = "/DeleteIntegral")
    @RequiresPermissions("integralexchange:delete")
    @ResponseBody
    public Result DeleteIntegral(@RequestParam("id") String id) {
        Result result = integralExchangeService.DeleteIntegral(id);
        return result;
    }

    //积分兑换
    @LogAop(value = "兑换")
    @RequiresPermissions("integralexchange:exchange")
    @PostMapping(value = "/exchange")
    @ResponseBody
    public Result exchange(@RequestParam("id") String id, @RequestParam("member_name") String member_name, @RequestParam("number") int number) {
        Result result = integralExchangeService.Exchange(id, member_name, number);
        return result;
    }


    //跳转到修改界面
    @GetMapping(value = "/GetUpdateIntegral")
    public String GetUpdateIntegral(@RequestParam("id") String id, Model model) {
        IntegralExchange integralExchange = integralExchangeService.IdFindIntegralExchangeInfo(id);
        model.addAttribute("integral", integralExchange);
        return "Integral/Integralupdate";
    }

    //修改积分兑换
    @LogAop(value = "修改积分兑换信息")
    @RequiresPermissions("integralexchange:update")
    @PostMapping(value = "/UpdateIntegral")
    @ResponseBody
    public Result UpdateIntegral(@RequestParam("id") String id, @RequestParam("type") String type, @RequestParam("needredeem") int needredeem, @RequestParam("name") String name) {
        Result result = integralExchangeService.UpdateIntegral(id, type, needredeem, name);
        return result;
    }


}
