package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Brand;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandMapper extends WorkMapper<Brand> {

    public Brand getOneByName(@Param("name") String name);
    public List<Brand> getListByName(@Param("name") String name);
    public Brand getById(@Param("id")Long id);
}