package com.example.demo.Graduation.controller.PetController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PetFoodinfo")
public class PetFoodController {
    @RequestMapping(value = "/GetPetFood")
    public String GetPetFood() {


        return "";
    }
}
