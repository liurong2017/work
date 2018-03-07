package cn.work.home.service;

import cn.work.home.dao.mapper.*;
import cn.work.home.dao.model.OrderDetail;
import cn.work.home.dao.model.Orderform;
import cn.work.home.dao.model.Product;
import cn.work.home.dao.model.Report;
import cn.work.home.vo.OrderShowVo;
import cn.work.home.vo.OrderVo;
import cn.work.home.vo.OrderformVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Transactional
    public Boolean add(OrderVo OrderVo)throws Exception{
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date now=new Date();
        Orderform orderform=new Orderform();
        orderform.setType("1");//收入
        BeanUtils.copyProperties(OrderVo,orderform);
        orderform.setAddTime(now);
        orderform.setStatus("1");//确认订单
        int i=orderformMapper.insert(orderform);
        //添加一条收入记录
        String pList=OrderVo.getpList();
        String[] list=pList.split("@");
        for (String s : list) {
          if(s!=null&&s.length()>0&&s.indexOf(",")>-1){
                String[] items=s.split(",");
              OrderDetail detail=new OrderDetail();
              detail.setPid(Long.valueOf(items[0]));
              detail.setOid(orderform.getId());
              detail.setNum(Integer.valueOf(items[1]));
              detail.setTotalPrice(new BigDecimal(items[2]));
              orderDetailMapper.insert(detail);
              //减少库存
              productMapper.updateStock(Long.valueOf(items[0]),"0",Integer.valueOf(items[1]));
              //增加成本

              Report report=new Report();
              report.setPid(Long.valueOf(items[0]));
              report.setOid(orderform.getId());
              report.setTime(now);
              report.setType("1");//收入
              report.setPrice(new BigDecimal(items[2]));
              report.setContent(simpleDateFormat.format(now)+"销售"+productMapper.getById(Long.valueOf(items[0])).getName()+"收入；订单id："+orderform.getId());
              reportMapper.insert(report);
          }
        }
        return  i>0;
    }

    @Transactional
    public Boolean update(Orderform orderform)throws  Exception{
        Orderform old=orderformMapper.getById(orderform.getId());
        if(old==null){
            throw new Exception("订单不存在");
        }
        int i=  orderformMapper.updateByPrimaryKey(orderform);
        return  i>0;
    }

    public List<OrderShowVo> getList(OrderformVo orderformVo){
        PageHelper.startPage(orderformVo.getPageNo(),orderformVo.getPageSize());
        return orderformMapper.selectOrder(orderformVo);
    }

    public Orderform getbyId(Long id){
        return orderformMapper.getById(id);
    }

    @Transactional
    public  Boolean updateExpress(Long id,String express)throws Exception{
        Orderform old=orderformMapper.getById(id);
        if(old==null){
            throw new Exception("订单不存在");
        }
        Orderform upd=new Orderform();
        upd.setId(old.getId());
        upd.setStatus("2");
        upd.setExpress(express);

        int i=  orderformMapper.updateByPrimaryKeySelective(upd);
        return  i>0;
    }


    @Transactional
    public  Boolean returnOrder(Long id,String remark)throws Exception{
        Orderform old=orderformMapper.getById(id);
        if(old==null){
            throw new Exception("订单不存在");
        }
        //返回库存
        List<OrderDetail> list= orderDetailMapper.getByOrderId(id);
        if(list!=null&&list.size()>0){
            
            for (OrderDetail detail : list) {
                Product product=productMapper.getById(detail.getPid());
                Product upd=new Product();
                upd.setStock(product.getStock()+detail.getNum());
                upd.setId(product.getId());
                productMapper.updateByPrimaryKeySelective(upd);
                //删除收入记录
                reportMapper.deleteByOidAndPid(id,detail.getPid());
            }
        }
        Orderform upd=new Orderform();
        upd.setId(old.getId());
        upd.setStatus("0");
        upd.setRemark(remark);
        int i=  orderformMapper.updateByPrimaryKeySelective(upd);
        return  i>0;
    }

}
