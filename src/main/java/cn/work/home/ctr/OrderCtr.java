package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Orderform;
import cn.work.home.service.OrderService;
import cn.work.home.util.BaseSearchVo;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.OrderformVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Controller
public class OrderCtr extends BaseController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(Orderform orderform){
        try {
            Boolean result= orderService.add(orderform);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(OrderformVo orderformVo){
        List<Orderform> list=orderService.getList(orderformVo);
        PageInfo<Orderform> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(orderformVo.getPageSize(),orderformVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap update(Orderform orderform){
        try {
            Boolean result= orderService.update(orderform);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }
}
