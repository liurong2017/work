package cn.work.home.vo;

import cn.work.home.dao.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Created by liur.
 * @Date: 2018/3/12
 * @Description:
 */
public class ProductListVo extends Product implements Serializable {

    private BigDecimal totalCost;
    private BigDecimal totalIncome;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
