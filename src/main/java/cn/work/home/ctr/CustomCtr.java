package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Brand;
import cn.work.home.dao.model.Classify;
import cn.work.home.dao.model.Custom;
import cn.work.home.service.CustomService;
import cn.work.home.util.BaseSearchVo;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.CustomVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.invoke.MethodType;
import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Controller
@RequestMapping(value = "/custom")
public class CustomCtr extends BaseController {

    @Autowired
    private CustomService customService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(Custom custom){
        try {
            Boolean result= customService.add(custom);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(
            CustomVo customVo){
        List<Custom> list=customService.getList(customVo);
        PageInfo<Custom> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(customVo.getPageSize(),customVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap updateBrand(Custom custom){
        try {
            Boolean result= customService.update(custom);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }
}
