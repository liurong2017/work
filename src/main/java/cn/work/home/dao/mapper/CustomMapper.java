package cn.work.home.dao.mapper;

import cn.work.home.dao.model.Custom;
import cn.work.home.util.WorkMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomMapper extends WorkMapper<Custom> {

    public  Custom getByWechat(@Param("wechatName") String wechatName);
}