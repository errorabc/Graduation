package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationDao {
    //查询
    List<Reservation> FinAllReservationinfo(@Param("reservation_name") String reservation_name);

}
