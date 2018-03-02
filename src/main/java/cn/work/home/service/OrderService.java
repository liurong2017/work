package cn.work.home.service;

import cn.work.home.dao.mapper.CustomMapper;
import cn.work.home.dao.mapper.OrderformMapper;
import cn.work.home.dao.mapper.ProductMapper;
import cn.work.home.dao.model.Orderform;
import cn.work.home.vo.OrderformVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Service
public class OrderService {

    @Autowired
    private OrderformMapper orderformMapper;

    @Autowired
    private CustomMapper customMapper;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public Boolean add(Orderform orderform)throws Exception{
        int i=orderformMapper.insert(orderform);
        return  i>0;
    }

    @Transactional
    public Boolean update(Orderform orderform)throws  Exception{
        Orderform old=orderformMapper.selectByPrimaryKey(orderform.getId());
        if(old==null){
            throw new Exception("订单不存在");
        }
        int i=  orderformMapper.updateByPrimaryKey(orderform);
        return  i>0;
    }

    public List<Orderform> getList(OrderformVo orderformVo){
        PageHelper.startPage(orderformVo.getPageNo(),orderformVo.getPageSize());
        Orderform search=new Orderform();
        BeanUtils.copyProperties(orderformVo,search);
        return orderformMapper.select(search);
    }


}
