package com.example.demo.Graduation.Dao.MemberDao;

import com.example.demo.Graduation.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationDao {
    //查询
    List<Reservation> FinAllReservationinfo(@Param("reservation_name") String reservation_name);

    //关闭预告
    boolean UpdateReservationStatus(@Param("id") String id);

    //删除预告
    boolean DeleteReservation(@Param("id") String id);

    //ID查询预约信息
    Reservation IdFindReservation(@Param("id") String id);


    //添加
    boolean AddReservation(Reservation reservation);

    //修改
    boolean UpdateReservation(Reservation reservation);

}
