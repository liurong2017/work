package cn.work.home.vo;

import cn.work.home.util.BaseSearchVo;

import javax.persistence.Column;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
public class CustomVo extends BaseSearchVo {
    private String name;
    private String wechatName;
    private String wechatCode;
    private String mobile;
    private String source;
    private String sex;
    private Integer level;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
