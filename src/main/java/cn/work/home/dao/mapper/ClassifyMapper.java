package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Classify;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClassifyMapper extends WorkMapper<Classify> {

    public Classify getByName(@Param("name") String name);
}