package com.example.demo.Graduation.controller.OderController;

import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.service.OderService.OderItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/salesrecord")
public class SaleSrecordController {
    @Autowired
    private OderItemService oderItemService;

    @GetMapping(value = "")
    public String FindAllOderItemInfo(Model model, OderItemEntity oderItemEntity, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<OderItemEntity> salelist = oderItemService.FindAllOderItemInfo(pageNo, pageSize, oderItemEntity);
        model.addAttribute("salelist", salelist);
        model.addAttribute("sreachcondition", oderItemEntity);
        return "SaleSrecord/salesrecord";
    }


}
