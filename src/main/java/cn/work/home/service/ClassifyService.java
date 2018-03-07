package cn.work.home.service;

import cn.work.home.dao.mapper.ClassifyMapper;
import cn.work.home.dao.model.Brand;
import cn.work.home.dao.model.Classify;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Service
public class ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;


    @Transactional
    public Boolean add(Classify classify)throws  Exception{
        Classify old=classifyMapper.getByName(classify.getName());
        if(old!=null){
            throw new Exception("分类已经存在");
        }
        int i=  classifyMapper.insertSelective(classify);
        return  i>0;
    }

    @Transactional
    public Boolean update(Classify classify)throws  Exception{
        Classify old=classifyMapper.getById(classify.getId());
        if(old==null){
            throw new Exception("分类不存在");
        }
        int i=  classifyMapper.updateByPrimaryKey(classify);
        return  i>0;
    }

    public List<Classify> getList(String name,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        if(!StringUtils.isEmpty(name)){
            return  classifyMapper.getLikeName(name);
        }
        return classifyMapper.selectAll();
    }

    public List<Classify> getAll(){
        return classifyMapper.selectAll();
    }
    public Classify getById(Long id){
        return classifyMapper.getById(id);
    }
}
