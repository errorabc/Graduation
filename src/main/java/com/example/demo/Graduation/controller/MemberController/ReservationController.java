package com.example.demo.Graduation.controller.MemberController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {
    @GetMapping(value = "")
    public String GetReservationlist(){
        return "Reservation/reservationlist";
    }


}
