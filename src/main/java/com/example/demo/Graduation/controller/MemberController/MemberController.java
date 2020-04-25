package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.entity.MemberEntity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.entity.VipinfoEntity;
import com.example.demo.Graduation.service.MemberService.MemberSerice;
import com.example.demo.Graduation.service.MenuService.MenuService;
import com.example.demo.Graduation.service.VipService.VipService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @Autowired
    private MemberSerice memberSerice;
    @Autowired
    private VipService vipService;


    //查询
    @RequestMapping(value = "")
    public String GetMemberInfoList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, MemberEntity memberEntity) {
        PageInfo<MemberEntity> memberlist = memberSerice.GetMemberInfoList(pageNo, pageSize, memberEntity.getName());
        model.addAttribute("memberlist", memberlist);
        model.addAttribute("membername", memberEntity.getName());
        return "Member/memberlist";
    }

    //跳转到添加界面
    @GetMapping(value = "/GetMemberAdd")
    public String GetMemberAdd(Model model) {
        List<VipinfoEntity> viplist = vipService.FindAllVip();
        model.addAttribute("viplist", viplist);
        return "Member/memberadd";
    }

    //跳转到修改界面
    @GetMapping(value = "/GetMemberUpdate")
    public String GetMemberUpdate(Model model, @RequestParam("id") String id) {
        MemberEntity memberEntity = memberSerice.IdFIndMemberInfo(id);
        List<VipinfoEntity> viplist = vipService.FindAllVip();
        model.addAttribute("viplist", viplist);
        model.addAttribute("member", memberEntity);
        return "Member/memberupdate";
    }


    //添加会员
    @PostMapping(value = "/AddMember")
    @ResponseBody
    public Result AddMember(MemberEntity memberEntity, @RequestParam("vipid") String vipid) {
        Result result = memberSerice.AddMember(memberEntity, vipid);
        return result;
    }

    //查询会员名是否存在
    @PostMapping(value = "/VerificationMemberName")
    @ResponseBody
    public Result VerificationMemberName(@RequestParam("name") String name) {
        Result result = memberSerice.VerificationMemberName(name);
        return result;
    }

    //删除会员信息
    @PostMapping(value = "/DeleteMember")
    @ResponseBody
    public Result DeleteMember(@Param("id") String id) {
        Result result = memberSerice.DeleteMember(id);
        return result;
    }

    //修改会员信息
    @PostMapping(value = "/UpdateMember")
    @ResponseBody
    public Result UpdateMember(MemberEntity memberEntity, @RequestParam("vipid") String vipid) {
        Result result = memberSerice.UpdateMember(memberEntity, vipid);
        return result;
    }

}
