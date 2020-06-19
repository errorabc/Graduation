package com.example.demo.Graduation.controller.OderController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.*;
import com.example.demo.Graduation.service.MemberService.ActivityService;
import com.example.demo.Graduation.service.MemberService.MemberSerice;
import com.example.demo.Graduation.service.OderService.OderService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;
//订单管理
@Controller
@RequestMapping(value = "/oderinfo")
public class OderController {
    @Autowired
    private OderService oderService;
    @Autowired
    private MemberSerice memberSerice;
    @Autowired
    private ActivityService activityService;


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
        Activity activity=activityService.NameFindActivityInfo(oderEntity.getActivityname());
        model.addAttribute("oder", oderEntity);
        model.addAttribute("vip", vipinfoEntity);
        model.addAttribute("activity", activity);
        return "Oder/handleoder";
    }


    //处理订单
    @LogAop("处理订单")
    @RequiresPermissions("oderinfo:handleoder")
    @PostMapping(value = "/HandleOder")
    @ResponseBody
    public Result HandleOder(OderEntity oderEntity) {
        Result result = oderService.HandleOder(oderEntity);
        return result;
    }

    //跳转到批量处理订单界面
    @GetMapping(value = "/GetHandleMultipleOder")
    public String GetHandleMultipleOder(@RequestParam("datas") Vector datas) {
        return "Oder/handlemultipleoder";
    }

    //跳转到详情
    @GetMapping(value = "/GetDetailsOder")
    public String GetDetailsOder(@RequestParam("id") String id, Model model) {
        OderEntity oderEntity = oderService.IdFindOderInfo(id);
        VipinfoEntity vipinfoEntity = memberSerice.NameFindMemberInfo(oderEntity.getMember_name().trim());
        Activity activity=activityService.NameFindActivityInfo(oderEntity.getActivityname());
        model.addAttribute("oder", oderEntity);
        model.addAttribute("vip", vipinfoEntity);
        model.addAttribute("activity", activity);
        return "Oder/detailsoder";
    }


    //删除订单
    @LogAop("删除订单")
    @RequiresPermissions("oderinfo:delete")
    @PostMapping(value = "/DeleteOder")
    @ResponseBody
    public Result DeleteOder(@RequestParam("oder_no") String oder_no) {
        Result result = oderService.DeleteOder(oder_no);
        return result;
    }


    //跳转到退款申请界面
    @GetMapping(value = "/GetReFund")
    public String GetReFund(@RequestParam("oder_no") String oder_no, Model model) {
        OderEntity oderEntity = oderService.IdFindOderInfo(oder_no);
        VipinfoEntity vipinfoEntity = memberSerice.NameFindMemberInfo(oderEntity.getMember_name().trim());
        model.addAttribute("oder", oderEntity);
        model.addAttribute("vip", vipinfoEntity);
        return "Oder/refund";
    }

    //退款申请
    @LogAop("退款申请")
    @RequiresPermissions("oderinfo:refund")
    @PostMapping(value = "/ReFund")
    @ResponseBody
    public Result ReFund(OderEntity oderEntity) {
        Result result = oderService.ReFund(oderEntity);
        return result;
    }
}
