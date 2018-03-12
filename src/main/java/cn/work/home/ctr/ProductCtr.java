package cn.work.home.ctr;

import cn.work.home.base.BaseController;
import cn.work.home.dao.model.Custom;
import cn.work.home.dao.model.Product;
import cn.work.home.service.ProductService;
import cn.work.home.util.PageResult;
import cn.work.home.util.ResultCode;
import cn.work.home.vo.CustomVo;
import cn.work.home.vo.ProductListVo;
import cn.work.home.vo.ProductVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
@Controller
@RequestMapping(value = "/product")
public class ProductCtr extends BaseController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap add(Product product){
        try {
            Boolean result= productService.add(product);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getList(ProductVo productVo){
        List<ProductListVo> list=productService.getList(productVo);
        PageInfo<ProductListVo> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult(productVo.getPageSize(),productVo.getPageNo(), pageInfo.getList(),pageInfo.getPages(), pageInfo.getTotal(), null);
        return retResult("200","200",pageResult);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap update(ProductVo product){
        try {
            Boolean result= productService.update(product);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/updateStock",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap updateStock(
            @RequestParam(value = "id")
            Long id,
            @RequestParam(value = "flag")
            String flag,
            @RequestParam(value = "stock")
            Integer stock,
            @RequestParam(value = "cost")
            BigDecimal cost,
            @RequestParam(value = "remark")
            String remark
            ){
        try {
            Boolean result= productService.updateStock(id,flag,stock,cost,remark);
            return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return retResult(ResultCode.ERROR_CODE,ResultCode.ERROR_MSG,e.getMessage());
        }
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public  ModelMap getById(Long id){
        return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,productService.getById(id));
    }

    @RequestMapping(value = "/getByIds",method = RequestMethod.GET)
    @ResponseBody
    public  ModelMap getByIds(String ids){
        return retResult(ResultCode.SUCCESS_CODE,ResultCode.SUCCESS_MSG,productService.getByIds(ids));
    }
}
