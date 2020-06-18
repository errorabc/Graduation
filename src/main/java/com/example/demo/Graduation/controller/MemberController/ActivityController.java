package com.example.demo.Graduation.controller.MemberController;

import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.entity.Activity;
import com.example.demo.Graduation.entity.Result;
import com.example.demo.Graduation.service.MemberService.ActivityService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//活动管理
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    //查询
    @GetMapping(value = "")
    public String FinAllActivityInfo(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, @RequestParam(value = "name", defaultValue = "") String name) {
        PageInfo<Activity> pagelist = activityService.FinAllActivityInfo(pageNo, pageSize, name);
        model.addAttribute("activity", pagelist);
        model.addAttribute("name", name);
        return "Activity/activitylist";
    }

    //跳转到添加界面
    @GetMapping(value = "/GetAddActivity")
    public String GetAddActivity() {
        return "Activity/activityadd";
    }

    //停止活动
    @LogAop(value = "停止活动")
    @RequiresPermissions("activity:stop")
    @PostMapping(value = "/StopActivity")
    @ResponseBody
    public Result StopActivity(@RequestParam("id") String id) {
        Result result = activityService.StopActivity(id);
        return result;
    }

    //添加活动
    @LogAop("添加活动")
    @RequiresPermissions("activity:add")
    @PostMapping(value = "/AddActivity")
    @ResponseBody
    public Result AddActivity(Activity activity) throws Exception {
        Result result = activityService.AddActivity(activity);
        return result;
    }

    //删除活动
    @LogAop("删除活动")
    @RequiresPermissions("activity:delete")
    @PostMapping(value = "/DeleteActivity")
    @ResponseBody
    public Result DeleteActivity(@RequestParam("id") String id) {
        Result result = activityService.DeleteActivity(id);
        return result;
    }

    //跳转到活动详情界面
    @GetMapping(value = "/GetDetailsActivity")
    public String GetDetailsActivity(@Param("id") String id, Model model) {
        Activity activity = activityService.IdFindActivityInfo(id);
        model.addAttribute("activity", activity);
        return "Activity/activitydetail";
    }

    //跳转到修改活动界面
    @GetMapping(value = "/GetUpdateActivity")
    public String GetUpdateActivity(@Param("id") String id, Model model) {
        Activity activity = activityService.IdFindActivityInfo(id);
        model.addAttribute("activity", activity);
        return "Activity/activityupdate";
    }

    //修改活动
    @LogAop("修改活动")
    @RequiresPermissions("activity:update")
    @PostMapping(value = "/UpdateActivity")
    @ResponseBody
    public Result UpdateActivity(Activity activity) throws Exception {
        Result result = activityService.UpdateActivity(activity);
        return result;
    }

}
