package cn.work.home.vo;

import cn.work.home.dao.model.Brand;
import cn.work.home.util.BaseSearchVo;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
public class BrandVo extends BaseSearchVo{
    private String name;

    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
