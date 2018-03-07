package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Report;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportMapper extends WorkMapper<Report> {

    public  void deleteByOidAndPid(@Param("oid") Long oid , @Param("pid") Long pid);
}