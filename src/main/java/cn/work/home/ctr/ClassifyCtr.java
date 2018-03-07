package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Brand;
import cn.work.home.dao.model.Classify;
import cn.work.home.service.ClassifyService;
import cn.work.home.util.BaseSearchVo;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.BrandVo;
import com.github.pagehelper.PageInfo;
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
 * @Date: 2018/3/2
 * @Description:
 */
@Controller
@RequestMapping(value = "/classify")
public class ClassifyCtr extends BaseController {

    @Autowired
    private ClassifyService classifyService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(Classify classify){
        try {
            Boolean result= classifyService.add(classify);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap update(Classify classify){
        try {
            Boolean result= classifyService.update(classify);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(
            @RequestParam(value = "name",required = false)
            String name, BaseSearchVo searchVo){
        List<Classify> list=classifyService.getList(name,searchVo.getPageNo(),searchVo.getPageSize());
        PageInfo<Classify> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(searchVo.getPageSize(),searchVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getAll(){
        List<Classify> list=classifyService.getAll();
        return retResult("200","200",list);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getById(Long id){
        return retResult("200","200",classifyService.getById(id));
    }

}
