package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.entity.Redeeminfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/integralexchange")
public class IntegralExchangeController {

    @GetMapping(value = "")
    public String GetIntegralExchange(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Redeeminfo redeeminfo) {
        return "Integral/Integrallist";
    }
}
