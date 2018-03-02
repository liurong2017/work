package cn.work.home.service;

import cn.work.home.dao.mapper.ProductMapper;
import cn.work.home.dao.model.Product;
import cn.work.home.vo.ProductVo;
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
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public Boolean add(Product product)throws Exception{
        Product old=productMapper.getByName(product.getName());
        if(old!=null){
            throw new Exception("商品已经存在");
        }
        int i=productMapper.insert(product);
        return  i>0;
    }

    @Transactional
    public Boolean update(Product product)throws  Exception{
        Product old=productMapper.selectByPrimaryKey(product.getId());
        if(old==null){
            throw new Exception("商品不存在");
        }
        int i=  productMapper.updateByPrimaryKey(product);
        return  i>0;
    }

    public List<Product> getList(ProductVo productVo){
        PageHelper.startPage(productVo.getPageNo(),productVo.getPageSize());
        Product search=new Product();
        BeanUtils.copyProperties(productVo,search);
        return productMapper.select(search);
    }

    @Transactional
    public Boolean updateStock(Long id,String flag,Integer stock)throws  Exception{
        Product old=productMapper.selectByPrimaryKey(id);
        if(old==null){
            throw new Exception("商品不存在");
        }
        int i=productMapper.updateStock(id,flag,stock);
        return  i>0;
    }

}
