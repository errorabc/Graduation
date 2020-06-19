package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.entity.Exchangerecord;
import com.example.demo.Graduation.service.MemberService.ExchangeRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//兑换记录
@Controller
@RequestMapping(value = "/exchangerecord")
public class ExchangeRecordController {
    @Autowired
    private ExchangeRecordService exchangeRecordService;

    @GetMapping(value = "")
    public String GetExchangeRecordList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "membername",defaultValue = "") String membername) {
        PageInfo<Exchangerecord> pagelist = exchangeRecordService.FindAllExchageRecordInfo(pageNo, pageSize, membername);
        model.addAttribute("exrecord", pagelist);
        model.addAttribute("membername", membername);
        return "ExchageRecord/exchangerecordlist";
    }
}
