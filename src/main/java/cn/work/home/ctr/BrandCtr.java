package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Brand;
import cn.work.home.service.BrandService;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.BrandVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/1
 * @Description:
 */
@Controller
@RequestMapping(value = "/brand")
public class BrandCtr extends BaseController {


    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getById(Long id){
        return retResult("200","200",brandService.getById(id));
    }

    @RequestMapping(value = "/getByName",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getByName(String name){
        return retResult("200","200",brandService.getByName(name));
    }

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(
            BrandVo brandVo){
        List<Brand> list=brandService.getList(brandVo);
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(brandVo.getPageSize(),brandVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getAll(){
        List<Brand> list=brandService.getAll();
        return retResult("200","200",list);
    }

    @RequestMapping(value = "/addBrand",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap addBrand(Brand brand){
        try {
            Boolean result= brandService.addBrand(brand);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }
    @RequestMapping(value = "/updateBrand",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap updateBrand(Brand brand){
        try {
            Boolean result= brandService.updateBrand(brand);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }



}
