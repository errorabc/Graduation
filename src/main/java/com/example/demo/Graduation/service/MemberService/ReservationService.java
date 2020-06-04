package com.example.demo.Graduation.service.MemberService;

import com.example.demo.Graduation.Dao.MemberDao.ReservationDao;
import com.example.demo.Graduation.entity.Reservation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
