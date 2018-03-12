package cn.work.home.service;

import cn.work.home.dao.mapper.ProductMapper;
import cn.work.home.dao.mapper.ReportMapper;
import cn.work.home.dao.model.Product;
import cn.work.home.dao.model.Report;
import cn.work.home.vo.ProductListVo;
import cn.work.home.vo.ProductVo;
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
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Transactional
    public Boolean add(Product product)throws Exception{
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date now=new Date();

        Product old=productMapper.getByName(product.getName());
        if(old!=null){
            throw new Exception("商品已经存在");
        }
        product.setAddTime(now);
        int i=productMapper.insert(product);
        //添加进货成本
       BigDecimal cost= product.getCost();
        Report report=new Report();
        report.setPid(product.getId());
        report.setTime(now);
        report.setType("0");//支出
        report.setPrice(cost);
        report.setContent(simpleDateFormat.format(now)+"添加"+product.getName()+"进货成本");
        reportMapper.insert(report);
        return  i>0;
    }

    @Transactional
    public Boolean update(ProductVo product)throws  Exception{
        Product old=productMapper.getById(product.getId());
        if(old==null){
            throw new Exception("商品不存在");
        }
        Product upd=new Product();
        BeanUtils.copyProperties(product,upd);
        if(product.getFlag()!=null&&product.getStock()!=null){
            if(product.getFlag().equals("1")){
                upd.setStock(old.getStock()+product.getStock());
            }else if(product.getFlag().equals("0")){
                upd.setStock(old.getStock()-product.getStock());
            }
        }
        int i= productMapper.updateByPrimaryKeySelective(upd);
        return  i>0;
    }

    public List<ProductListVo> getList(ProductVo productVo){
        PageHelper.startPage(productVo.getPageNo(),productVo.getPageSize());
        Product search=new Product();
        BeanUtils.copyProperties(productVo,search);
        return productMapper.selectByCondition(search);
    }

    @Transactional
    public Boolean updateStock(Long id,String flag,Integer stock,BigDecimal cost,String remark)throws  Exception{
        Product old=productMapper.getById(id);
        if(old==null){
            throw new Exception("商品不存在");
        }
        int i=productMapper.updateStock(id,flag,stock);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date now=new Date();
        Report report=new Report();
        report.setPid(old.getId());
        report.setTime(now);
        report.setType("0");//支出
        report.setPrice(cost);
        report.setContent(simpleDateFormat.format(now)+"添加"+old.getName()+"进货成本");
        report.setRemark(remark);
        reportMapper.insert(report);
        return  i>0;
    }


    public Product getById(Long id){
        return productMapper.getById(id);
    }
    public List<Product> getByIds(String ids){
        return productMapper.getByIds(ids);
    }
}
