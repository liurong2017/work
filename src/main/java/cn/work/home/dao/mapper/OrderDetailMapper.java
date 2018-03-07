package cn.work.home.dao.mapper;

import cn.work.home.dao.model.OrderDetail;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends WorkMapper<OrderDetail> {

    public List<OrderDetail> getByOrderId(@Param("oid") Long id);
}