package cn.work.home.service;

import cn.work.home.dao.mapper.BrandMapper;
import cn.work.home.dao.model.Brand;
import cn.work.home.vo.BrandVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/1
 * @Description:
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    public Brand getById(Long id){
        return  brandMapper.getById(id);
    }

    public Brand getByName(String name){

        return  brandMapper.getOneByName(name);
    }

    public List<Brand> getList(BrandVo brandVo){
        PageHelper.startPage(brandVo.getPageNo(),brandVo.getPageSize());
        if(brandVo.getName()!=null){
            return  brandMapper.getListByName(brandVo.getName());
        }
        return brandMapper.selectAll();


    }

    @Transactional
    public Boolean addBrand(Brand brand)throws  Exception{
        Brand old=brandMapper.getOneByName(brand.getName());
        if(old!=null){
            throw new Exception("品牌已经存在");
        }
       int i=  brandMapper.insertSelective(brand);
       return  i>0;
    }
    @Transactional
    public Boolean updateBrand(Brand brand)throws  Exception{
        Brand old=brandMapper.getById(brand.getId());
        if(old==null){
            throw new Exception("品牌不存在");
        }
       int i= brandMapper.updateByPrimaryKey(brand);
       return i>0;
    }

    public List<Brand> getAll(){
        return brandMapper.selectAll();
    }
}
