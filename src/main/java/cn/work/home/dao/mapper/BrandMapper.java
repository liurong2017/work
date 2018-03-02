package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Brand;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper extends WorkMapper<Brand> {

    public Brand getByName(@Param("name") String name);
}