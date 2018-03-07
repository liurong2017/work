package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Classify;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassifyMapper extends WorkMapper<Classify> {

    public Classify getByName(@Param("name") String name);
    public List<Classify> getLikeName(@Param("name") String name);
    public Classify getById(@Param("id") Long id );
}