package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.ActivityDao;
import com.example.demo.Graduation.entity.Activity;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    ActivityDao activityDao;

    //查询
    public PageInfo<Activity> FinAllActivityInfo(int pageno, int pagesize, String name) {
        PageHelper.startPage(pageno, pagesize);
        List<Activity> list = activityDao.FinAllActivityInfo(name);
        PageInfo<Activity> pagelist = new PageInfo<Activity>(list);
        return pagelist;
    }

    //停止活动
    public Result StopActivity(String id) {
        if (activityDao.UpdateActivityStatus(id)) {
            return Result.success(1, "停止成功");
        } else {
            return Result.error(0, "停止失败");
        }
    }

    //添加活动
    public Result AddActivity(Activity activity) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        activity.setId(UUID.randomUUID().toString());
        activity.setActivity_starttime(df.parse(activity.getStarttime()));
        activity.setActivity_endtime(df.parse(activity.getEndtime()));
        Activity activity1 = activityDao.NameFindActivityInfo(activity.getActivity_name().trim());
        if (activity.getActivity_endtime().getTime() - activity.getActivity_starttime().getTime() < 0) {
            return Result.error(0, "结束时间不得小于开始时间");
        } else {
            if (null != activity1) {
                return Result.error(0, "改活动名已存在");
            } else {
                if (activityDao.AddActivity(activity)) {
                    return Result.success(1, "添加成功");
                } else {
                    return Result.error(0, "添加失败");
                }

            }
        }
    }


    //删除活动
    public Result DeleteActivity(String id) {
        Activity activity = activityDao.IdFindActivityInfo(id);
        if (activity.getActivity_status() == 0) {
            return Result.error(0, "活动未结束,不允许被删除");
        } else {
            if (activityDao.DeleteActivity(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        }
    }

    //ID查询信息
    public Activity IdFindActivityInfo(String id) {
        Activity activity = activityDao.IdFindActivityInfo(id);
        return activity;
    }

    //name查询信息
    public Activity NameFindActivityInfo(String id) {
        Activity activity = activityDao.NameFindActivityInfo(id);
        return activity;
    }


    //修改
    public Result UpdateActivity(Activity activity) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Activity activityName = activityDao.IdFindActivityInfo(activity.getActivity_name().trim());
        activity.setActivity_starttime(df.parse(activity.getStarttime()));
        activity.setActivity_endtime(df.parse(activity.getEndtime()));
        if (activity.getActivity_endtime().getTime() - activity.getActivity_starttime().getTime() < 0) {
            return Result.error(0, "结束时间不得小于开始时间");
        } else {
            if (null != activityName) {
                //继续修改
                if (activityName.getId().equals(activity.getId())) {
                    if (activityDao.UpdateActivity(activity)) {
                        return Result.success(1, "修改成功");
                    } else {
                        return Result.error(0, "修改失败");
                    }
                } else {
                    return Result.error(0, "活动已存在");
                }
            } else {
                if (activityDao.UpdateActivity(activity)) {
                    return Result.success(1, "修改成功");
                } else {
                    return Result.error(0, "修改失败");
                }
            }
        }

    }

    //查询当前时间内的活动信息
    public List<Activity> FindUnderwayActivity() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        List<Activity> list = activityDao.FindUnderwayActivity(df.format(new Date()));
        return list;
    }




}
