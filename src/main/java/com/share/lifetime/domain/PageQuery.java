package com.share.lifetime.domain;

public class PageQuery extends AbstractQuery {

	private static final long serialVersionUID = 1L;

	private int pageNum;
	private int pageSize;

	public PageQuery(int pageNum, int pageSize) {
		if (pageNum < 0) {
			throw new IllegalArgumentException("Page index must not be less than or equal to zero!");
		}

		if (pageSize < 1) {
			throw new IllegalArgumentException("Page size must not be less than one!");
		}
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 页码
	 * @return
	 */
	public int getPageOffset() {
		return (pageNum - 1) * pageSize;
	}

	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean hasPrevious() {
		return pageNum > 1;
	}

}
