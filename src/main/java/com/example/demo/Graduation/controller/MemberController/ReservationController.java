package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.entity.Reservation;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.ReservationService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //关闭预约
    @PostMapping(value = "/StopReservation")
    @ResponseBody
    public Result StopReservation(@RequestParam("id") String id) {
        Result result = reservationService.StopReservation(id);
        return result;
    }

    //删除
    @PostMapping(value = "/DeleteReservation")
    @ResponseBody
    public Result DeleteReservation(@RequestParam("id") String id) {
        Result result = reservationService.DeleteReservation(id);
        return result;
    }

    //跳转到修改界面
    @GetMapping(value = "/GetUpdateReservation")
    public String GetUpdateReservation(@RequestParam("id") String id, Model model) {
        Reservation reservation = reservationService.IdFindReservation(id);
        model.addAttribute("reservation", reservation);
        return "Reservation/reservationupdate";
    }


    //跳转到增加界面
    @GetMapping(value = "/GetAddReservation")
    public String GetAddReservation() {
        return "Reservation/reservationadd";
    }

    //添加预约
    @PostMapping(value = "/AddReservation")
    @ResponseBody
    public Result AddReservation(Reservation reservation,@RequestParam("time")String time) throws Exception {
        System.out.println("aaaaaaa");
        Result result = reservationService.AddReservation(reservation,time);
        return result;
    }

}
