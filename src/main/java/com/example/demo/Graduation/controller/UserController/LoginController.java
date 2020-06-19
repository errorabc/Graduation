package com.example.demo.Graduation.controller.UserController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.service.MemberService.MemberSerice;
import com.example.demo.Graduation.service.MenuService.MenuService;

import com.example.demo.Graduation.service.OderService.OderItemService;
import com.example.demo.Graduation.service.OderService.OderService;
import com.example.demo.Graduation.service.PetService.PetFoodService;
import com.example.demo.Graduation.service.PetService.PetFosterService;
import com.example.demo.Graduation.service.PetService.PetJewelryService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//登陆、注销
@Controller
public class LoginController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OderService oderService;
    @Autowired
    private MemberSerice memberSerice;
    @Autowired
    private PetFoodService petFoodService;
    @Autowired
    private PetFosterService petFosterService;
    @Autowired
    private PetJewelryService petJewelryService;
    @Autowired
    private OderItemService oderItemService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    //跳转到登录界面
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @PostMapping(value = "/loginin")
    public String loginin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (LockedAccountException e) {
            model.addAttribute("msg", "该账户已被锁定");
            return "login";
        }
    }

    //注销
    @LogAop("/注销")
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    //系统主界面
    @RequestMapping(value = "/index")
    public String index(Model model) {
        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//已经登录的用户
        if (null != LoginUsername) {
            List<MenuEntity> menuEntityList1 = menuService.FindMenusType1(LoginUsername);  //获取一级菜单
            List<MenuEntity> menuEntityList2 = null;
            List<MenuEntity> menuEntityList3 = new ArrayList<MenuEntity>();
            menuEntityList2 = menuService.FindMenusType2(LoginUsername);  //查询二级菜单
            for (MenuEntity p2 : menuEntityList2) {
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setName(p2.getName());
                menuEntity.setId(p2.getId());
                menuEntity.setParent_id(p2.getParent_id());
                menuEntity.setUrl(p2.getUrl());
                menuEntityList3.add(menuEntity);
            }
            model.addAttribute("menus", menuEntityList1);
            model.addAttribute("menus2", menuEntityList3);
            model.addAttribute("username", LoginUsername);
            return "index";
        } else {
            return "login";
        }
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut() {
        return "kickout";
    }


    //获取系统桌面信息
    @GetMapping(value = "/systemstate")
    @ResponseBody
    public Map sysinfo() {
        int unprocessedoder = oderService.FindUnprocessedOderNumber();   //未处理的订单数量
        int membernumber = memberSerice.MemberNumber();//会员数量
        int petfoodmumber = petFoodService.PetFoodNumber();//宠物食品
        int petfostermumber = petFosterService.FindUnfinishedPetFosterMumber();//未完成的宠物寄养
        int petjewelrymumber = petJewelryService.PetjewelryNumber();//宠物饰品
        BigDecimal toDaysales = oderItemService.ToDaySales();//今日销售
        Map<String, String> maplist = new HashMap<String, String>();
        maplist.put("unprocessedoder", String.valueOf(unprocessedoder));
        maplist.put("membernumber", String.valueOf(membernumber));
        maplist.put("petfoodmumber", String.valueOf(petfoodmumber));
        maplist.put("petfostermumber", String.valueOf(petfostermumber));
        maplist.put("petjewelrymumber", String.valueOf(petjewelrymumber));
        maplist.put("todaysales", String.valueOf(toDaysales));
        return maplist;
    }
}
