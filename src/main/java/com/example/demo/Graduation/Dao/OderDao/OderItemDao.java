package com.example.demo.Graduation.Dao.OderDao;

import com.example.demo.Graduation.entity.OderItemEntity;
import com.example.demo.Graduation.entity.ProducttypeSaleEchatrs;
import com.example.demo.Graduation.entity.SaleEchartsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OderItemDao {
    //
    boolean AddOderItem(OderItemEntity oderItemEntity);

    //查询所有
    List<OderItemEntity> FindAllOderItemInfo(OderItemEntity oderItemEntity);


    int SaleMumber(@Param("product_type")String product_type,@Param("startime")String startime,@Param("endtime")String endtime);

    BigDecimal SaleMoney(@Param("product_type")String product_type,@Param("startime")String startime,@Param("endtime")String endtime);

    List<ProducttypeSaleEchatrs>   ProducttypeFindSaleEchatrsTime(@Param("product_type")String product_type,@Param("startime")String startime,@Param("endtime")String endtime);

    OderItemEntity IdFindOderItemInfo(@Param("oder_no")String oder_no);





}
