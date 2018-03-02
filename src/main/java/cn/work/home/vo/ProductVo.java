package cn.work.home.vo;

import cn.work.home.util.BaseSearchVo;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
public class ProductVo extends BaseSearchVo {

    private Long brandId;
    private Long classifyId;
    private String name;
    private Integer status;
    private Integer level;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
