package cn.work.home.base;

import org.springframework.ui.ModelMap;

public class BaseController {



	/**
	 * 返回前台通用接口规范
	 * <功能详细描述>
	 * @author LimingWang
	 * @param code
	 * @param msg
	 * @param content
	 * @return
	 * @date 2017年3月14日 下午7:56:03
	 */
	public ModelMap retResult(String code, String msg, Object content){
		ModelMap modelMap = new ModelMap();
		modelMap.put("code", code);
		modelMap.put("msg", msg);
		modelMap.put("result", content);
		return modelMap;
	}
	
	public ModelMap retResult(boolean flag, String msg, Object content){
		ModelMap modelMap = new ModelMap();
		modelMap.put("status", flag);
		modelMap.put("msg", msg);
		modelMap.put("result", content);
		return modelMap;
	}

}
