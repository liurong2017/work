package cn.work.home.vo;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Created by liur.
 * @Date: 2018/3/7
 * @Description:
 */
public class OrderVo  {

    private Integer cid;

    private String cname;

    private String address;

    private String source;

    private String status;

    private String remark;
    private String pList;

    public String getpList() {
        return pList;
    }

    public void setpList(String pList) {
        this.pList = pList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
