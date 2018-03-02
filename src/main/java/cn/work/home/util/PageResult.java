package cn.work.home.util;

import java.util.List;

/**
 * 通用分页数据返回参数格式
 * @Title PageResult
 * @Description
 * 	  注:MC之前PageResult带业务参数，请不要混用
 * @author FangzhouXu
 * @date 2017年12月21日下午3:47:58
 */
public class PageResult {
	/**
	 * 一页显示多少条
	 */
	private int pageSize;

	/**
	 * 当前是第几页
	 */
	private int pageNo;

	/**
	 * 数据结果集
	 */
	private List records;

	/**
	 * 总页数
	 */
	private int pageTotal;

	/**
	 * 总条数
	 */
	private long totalRecords;

	/**
	 * 排序
	 */
	private String sort;
	
	public PageResult() {
		super();
	}
	
	public PageResult(int pageSize, int pageNo, List records, int pageTotal,
                      long totalRecords, String sort) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.records = records;
		this.pageTotal = pageTotal;
		this.totalRecords = totalRecords;
		this.sort = sort;
	}



	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
