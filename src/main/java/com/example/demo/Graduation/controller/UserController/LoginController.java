package com.example.demo.Graduation.controller.UserController;

import com.example.demo.Graduation.Dao.UserDao.UserDao;
import com.example.demo.Graduation.entity.MenuEntity;
import com.example.demo.Graduation.entity.JstreeVO;
import com.example.demo.Graduation.service.MenuService.MenuService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDao userDao;
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
            if (subject.isAuthenticated()) {
                logger.info("登录成功");
                return "redirect:/index";
            } else {
                token.clear();
                return "login";
            }
        } catch (Exception e) {
            logger.trace("登录失败");
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/QueryJsTree", method = RequestMethod.GET)
    public List<JstreeVO> QueryJsTree() {
        List<JstreeVO> list = new ArrayList<JstreeVO>();
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId("1");
        jstreeVO.setText("菜单1");
        jstreeVO.setParent("#");
        list.add(jstreeVO);
        JstreeVO jstreeVO2 = new JstreeVO();
        jstreeVO2.setId("2");
        jstreeVO2.setText("菜单2");
        jstreeVO2.setParent("#");
        list.add(jstreeVO2);
        JstreeVO jstreeVO3 = new JstreeVO();
        jstreeVO3.setId("3");
        jstreeVO3.setText("菜单1-1");
        jstreeVO3.setParent("1");
        list.add(jstreeVO3);
        JstreeVO jstreeVO4 = new JstreeVO();
        jstreeVO4.setId("4");
        jstreeVO4.setText("菜单2-1");
        jstreeVO4.setParent("2");
        list.add(jstreeVO4);
        return list;
    }

    //注销
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
            System.out.println(menuEntityList1.size() + "一级菜单");
            System.out.println(menuEntityList2.size() + "二级菜单");
            model.addAttribute("menus", menuEntityList1);
            model.addAttribute("menus2", menuEntityList3);
            model.addAttribute("username", LoginUsername);
            return "index";
        } else {
            return "login";
        }

    }


}
