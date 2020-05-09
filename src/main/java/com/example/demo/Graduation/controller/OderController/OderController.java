package com.example.demo.Graduation.controller.OderController;

import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.OderEntity;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.example.demo.Graduation.service.MemberService.MemberSerice;
import com.example.demo.Graduation.service.OderService.OderService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/oderinfo")
public class OderController {
    @Autowired
    private OderService oderService;
    @Autowired
    private MemberSerice memberSerice;


    @RequestMapping(value = "")
    public String OderList(Model model, OderEntity oderEntity, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<OderEntity> oderlist = oderService.FindAllOderInfo(oderEntity, pageNo, pageSize);
        model.addAttribute("oderlist", oderlist);
        model.addAttribute("sreachcondition", oderEntity);
        return "Oder/Oderlist";
    }


    //跳转到处理订单界面
    @GetMapping(value = "/GetHandleOder")
    public String GetHandleOder(@RequestParam("id") String id, Model model) {
        OderEntity oderEntity = oderService.IdFindOderInfo(id);
        VipinfoEntity vipinfoEntity = memberSerice.NameFindMemberInfo(oderEntity.getMember_name().trim());
        model.addAttribute("oder", oderEntity);
        model.addAttribute("vip", vipinfoEntity);
        System.out.println(vipinfoEntity.getName() + "啊啊啊");
        return "Oder/handleoder";
    }


}
