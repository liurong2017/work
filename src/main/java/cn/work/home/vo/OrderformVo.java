package cn.work.home.vo;

import cn.work.home.util.BaseSearchVo;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
public class OrderformVo extends BaseSearchVo {

    private String cname;
    private String pname;
    private String source;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
