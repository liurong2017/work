package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Orderform;
import cn.work.home.util.WorkMapper;
import cn.work.home.vo.OrderShowVo;
import cn.work.home.vo.OrderformVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderformMapper extends WorkMapper<Orderform> {

    public Orderform getById(@Param("id") Long id);

    public  int updateExpress(@Param("id") Long id,@Param("express") String express);

    public List<OrderShowVo> selectOrder(OrderformVo orderformVo);
}