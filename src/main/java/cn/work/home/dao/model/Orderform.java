package cn.work.home.dao.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class Orderform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private Long pid;

    private String cname;

    private String pname;

    private BigDecimal pprice;

    private BigDecimal sprice;

    private BigDecimal tprice;

    private Integer num;

    @Column(name = "totalPrice")
    private BigDecimal totalprice;

    private String address;

    private String source;

    @Column(name = "add_time")
    private Date addTime;

    private String status;

    private String remark;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return cid
     */
    public Long getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return pprice
     */
    public BigDecimal getPprice() {
        return pprice;
    }

    /**
     * @param pprice
     */
    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    /**
     * @return sprice
     */
    public BigDecimal getSprice() {
        return sprice;
    }

    /**
     * @param sprice
     */
    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    /**
     * @return tprice
     */
    public BigDecimal getTprice() {
        return tprice;
    }

    /**
     * @param tprice
     */
    public void setTprice(BigDecimal tprice) {
        this.tprice = tprice;
    }

    /**
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return totalPrice
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}