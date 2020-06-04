package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.entity.Reservation;
import com.example.demo.Graduation.service.MemberService.ReservationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//预约管理
@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "")
    public String GetReservationlist(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "name", defaultValue = "") String name) {
        PageInfo<Reservation> pagelist = reservationService.FinAllReservationinfo(pageNo, pageSize, name);
        model.addAttribute("reservation", pagelist);
        model.addAttribute("name", name);
        return "Reservation/reservationlist";
    }


}
