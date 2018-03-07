package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Orderform;
import cn.work.home.service.OrderService;
import cn.work.home.util.BaseSearchVo;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.OrderShowVo;
import cn.work.home.vo.OrderVo;
import cn.work.home.vo.OrderformVo;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/order")
public class OrderCtr extends BaseController {

    @Autowired
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(OrderCtr.class);


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(OrderVo orderVo){
        try {
            Boolean result= orderService.add(orderVo);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(OrderformVo orderformVo){
        List<OrderShowVo> list=orderService.getList(orderformVo);
        PageInfo<OrderShowVo> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(orderformVo.getPageSize(),orderformVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getById(Long id){
        return retResult("200","200",orderService.getbyId(id));
    }



    @RequestMapping(value = "/updateExpress",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap updateExpress(Long id,String express ){
        try {
            Boolean result= orderService.updateExpress(id,express);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/returnOrder",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap returnOrder(Long id,String remark ){
        try {
            Boolean result= orderService.returnOrder(id,remark);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }
}
