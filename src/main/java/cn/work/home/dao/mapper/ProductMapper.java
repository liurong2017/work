package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Product;
import cn.work.home.util.WorkMapper;
import cn.work.home.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends WorkMapper<Product> {

    public Product getByName(@Param("name") String name);

    public Product getById(@Param("id") Long id);

    public int updateStock(@Param("id") Long id,@Param("flag") String flag,@Param("stock") Integer stock);

    public List<Product> selectByCondition(Product product);
    public List<Product> getByIds(@Param("ids") String  ids);
}