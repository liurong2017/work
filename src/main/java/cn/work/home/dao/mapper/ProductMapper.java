package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Product;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper extends WorkMapper<Product> {

    public Product getByName(@Param("name") String name);

    public int updateStock(@Param("id") Long id,@Param("flag") String flag,@Param("stock") Integer stock);
}