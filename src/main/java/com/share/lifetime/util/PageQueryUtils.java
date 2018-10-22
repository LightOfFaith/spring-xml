package com.share.lifetime.util;

import com.share.lifetime.domain.PageQuery;

public class PageQueryUtils {
	
	/**
	 * 首页
	 * 
	 * @param pageQuery
	 * @return
	 */
	public static PageQuery first(final PageQuery pageQuery) {
		return new PageQuery(1, pageQuery.getPageSize());
	}

	/**
	 * 上一页
	 * 
	 * @param pageQuery
	 * @return
	 */
	public static PageQuery previous(final PageQuery pageQuery) {
		return pageQuery.getPageNum() == 0 ? pageQuery
				: new PageQuery(pageQuery.getPageNum() - 1, pageQuery.getPageSize());
	}

	/**
	 * 下一页
	 * 
	 * @param pageQuery
	 * @return
	 */
	public static PageQuery next(final PageQuery pageQuery) {
		return new PageQuery(pageQuery.getPageNum() + 1, pageQuery.getPageSize());
	}

	/**
	 * 
	 * @param pageQuery
	 * @return 返回上一页或首页，如果当前页是首页就返回此页；反之，则返回上一页
	 */
	public PageQuery previousOrFirst(final PageQuery pageQuery) {
		return pageQuery.hasPrevious() ? previous(pageQuery) : first(pageQuery);
	}


}
