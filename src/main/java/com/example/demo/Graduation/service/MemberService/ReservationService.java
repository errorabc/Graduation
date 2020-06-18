package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.ReservationDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.entity.Reservation;
import com.example.demo.Graduation.entity.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;

    //查询
    public PageInfo<Reservation> FinAllReservationinfo(int pageno, int pagesize, String name) {
        PageHelper.startPage(pageno, pagesize);
        List<Reservation> list = reservationDao.FinAllReservationinfo(name);
        PageInfo<Reservation> pagelist = new PageInfo<Reservation>(list);
        return pagelist;
    }


    //停止预告
    public Result StopReservation(String id) {
        if (reservationDao.UpdateReservationStatus(id)) {
            return Result.success(1, "关闭成功");
        } else {
            return Result.error(0, "关闭失败");
        }
    }

    //删除预告
    public Result DeleteReservation(String id) {
        Reservation reservation = reservationDao.IdFindReservation(id);
        if (reservation.getStatus() == 0) {
            return Result.error(0, "未被处理,不能被删除");
        } else {
            if (reservationDao.DeleteReservation(id)) {
                return Result.success(1, "删除成功");
            } else {
                return Result.error(0, "删除失败");
            }
        }
    }

    //ID查询预告信息
    public Reservation IdFindReservation(String id) {
        Reservation reservation = reservationDao.IdFindReservation(id);
        return reservation;
    }

    //添加预约
    public Result AddReservation(Reservation reservation, String time) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        reservation.setId(UUID.randomUUID().toString());
        reservation.setReservation_time(df.parse(time));
        if (reservationDao.AddReservation(reservation)) {
            return Result.success(1, "添加成功");
        } else {
            return Result.error(0, "添加失败");
        }
    }

    //修改
    public Result UpdateReservation(Reservation reservation) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Reservation re = reservationDao.IdFindReservation(reservation.getId());
        reservation.setReservation_time(df.parse(reservation.getTime()));
        if (null != re) {
            if (reservationDao.UpdateReservation(reservation)) {
                return Result.success(1, "修改成功");
            } else {
                return Result.error(0, "修改失败");
            }

        } else {
            return Result.error(0, "该预约不存在");
        }

    }

}
