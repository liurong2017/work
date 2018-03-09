package cn.work.home.util;

/**
 * @Created by liur.
 * @Date: 2018/3/2
 * @Description:
 */
public class BaseSearchVo {

    private  int pageNo=1;
    private  int pageSize=20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
