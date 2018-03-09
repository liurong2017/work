package cn.work.home.service;

import cn.work.home.dao.mapper.CustomMapper;
import cn.work.home.dao.model.Classify;
import cn.work.home.dao.model.Custom;
import cn.work.home.vo.CustomVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Service
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

    @Transactional
    public Boolean add(Custom custom)throws Exception{


        List<Custom> old=customMapper.selectByCondition(custom);
        if(old!=null&&old.size()>0){
            throw new Exception("客户已经存在");
        }
        custom.setAddTime(new Date());
        int i=customMapper.insert(custom);
        return  i>0;
    }

    @Transactional
    public Boolean update(Custom custom)throws  Exception{
        Custom old=customMapper.getById(custom.getId());
        if(old==null){
            throw new Exception("商品不存在");
        }
        int i=  customMapper.updateByPrimaryKey(custom);
        return  i>0;
    }

    public List<Custom> getList(CustomVo customVo){
        PageHelper.startPage(customVo.getPageNo(),customVo.getPageSize());
        Custom search=new Custom();
        BeanUtils.copyProperties(customVo,search);
        return customMapper.selectByCondition(search);
    }

    public Custom getById(Long id){
        return customMapper.getById(id);
    }
}
