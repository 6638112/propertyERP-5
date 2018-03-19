package com.cnfantasia.server.business.pub.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 描述:分页查询信息Model
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class PageModel {
	/**分页的相关key值*/
	public class PageKey{
		public static final String LIST = "list";
		public static final String HAS_NEXT = "hasNext";
		public static final String COUNT = "count";
		/** parameter参数的起始页号*/
		public static final String PAGE = "page";
		/** parameter参数的每页显示数*/
		public static final String PAGE_NUM = "pageNum";
	}
	private Log logger = LogFactory.getLog(getClass());
	private static final String perfix = "_";// 定义特殊的前缀,避免与业务传参的key值相同。
	public static final String BEGIN = perfix + "begin";
	public static final String LENGTH = perfix + "length";
	public static final String IS_COUNT = perfix + "isCount";
	public static final String COUNT = perfix + "count";
	public static final String TOTAL_PAGE = perfix + "totalPage";
	public static final String CURRENT_PAGE = perfix + "currentPage";
	public static final String IS_FIRST = perfix + "isFirst";
	public static final String IS_LAST = perfix + "isLast";
	public static final String SIZE = perfix + "size";

	/** 分页查询条件，返回记录的起始记录号 */
	public Integer begin;

	/** 分页查询条件，返回记录数长度 */
	public Integer length;

	/** 分页查询条件，是否统计总行数 */
	public Boolean isCount;

	/** 分页状态信息，总记录数 */
	public Integer count;

	/** 分页状态信息，总页数 */
	public Integer totalPage;

	/** 分页状态信息，当前页号 */
	public Integer currentPage;

	/** 分页状态信息，是否首页 */
	public Boolean isFirst;

	/** 分页状态信息，是否尾页 */
	public Boolean isLast;

	/** 分页状态信息，当前页记录数 */
	public Integer size;

	/**
	 * 初始化pageModel
	 * 
	 * @param pageMap
	 */
	public PageModel(Map<String, Object> pageMap) {
		if (pageMap == null)
			return;
		try {
			this.begin = Integer.parseInt(pageMap.get(BEGIN).toString());
		} catch (Exception e) {
		}
		try {
			this.length = Integer.parseInt(pageMap.get(LENGTH).toString());
		} catch (Exception e) {
		}
		try {
			this.isCount = Boolean.parseBoolean(pageMap.get(IS_COUNT).toString());
		} catch (Exception e) {
		}
		try {
			this.count = Integer.parseInt(pageMap.get(COUNT).toString());
		} catch (Exception e) {
		}
		try {
			this.totalPage = Integer.parseInt(pageMap.get(TOTAL_PAGE).toString());
		} catch (Exception e) {
		}
		try {
			this.currentPage = Integer.parseInt(pageMap.get(CURRENT_PAGE).toString());
		} catch (Exception e) {
		}
		try {
			this.isFirst = Boolean.parseBoolean(pageMap.get(IS_FIRST).toString());
		} catch (Exception e) {
		}
		try {
			this.isLast = Boolean.parseBoolean(pageMap.get(IS_LAST).toString());
		} catch (Exception e) {
		}
		try {
			this.size = Integer.parseInt(pageMap.get(SIZE).toString());
		} catch (Exception e) {
		}
	}
	/**
	 * 
	 * @param begin 起始编号
	 * @param length 长度
	 */
	public PageModel(Integer begin,Integer length){{
		this.begin = begin;
		this.length = length;
		this.currentPage = this.begin / this.length + (this.begin % this.length > 0 ? 1 : 0);
	}
		
	}
	/**
	 * 重写toString方法
	 */
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("begin:");
		sbf.append(begin);
		sbf.append(",");
		sbf.append("length:");
		sbf.append(length);
		sbf.append(",");
		sbf.append("isCount:");
		sbf.append(isCount);
		sbf.append(",");
		sbf.append("count:");
		sbf.append(count);
		sbf.append(",");
		sbf.append("totalPage:");
		sbf.append(totalPage);
		sbf.append(",");
		sbf.append("currentPage:");
		sbf.append(currentPage);
		sbf.append(",");
		sbf.append("isFirst:");
		sbf.append(isFirst);
		sbf.append(",");
		sbf.append("isLast:");
		sbf.append(isLast);
		sbf.append(",");
		sbf.append("size:");
		sbf.append(size);
		sbf.append(".");
		return sbf.toString();
	}

	/**************** 有关分页的工具方法 ****************/
	/**
	 * 将PageModel转换为HashMap
	 * 
	 * @return
	 */
	public HashMap<String, Object> toMap() {
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put(BEGIN, begin);
		pageMap.put(LENGTH, length);
		pageMap.put(IS_COUNT, isCount);
		pageMap.put(COUNT, count);
		pageMap.put(TOTAL_PAGE, totalPage);
		pageMap.put(CURRENT_PAGE, currentPage);
		pageMap.put(IS_FIRST, isFirst);
		pageMap.put(IS_LAST, isLast);
		pageMap.put(SIZE, size);
		return pageMap;
	}

	/**
	 * 根据传入的信息刷新Model
	 * 
	 * @param size
	 * @param totalCount
	 */
	public void freshPageModel(Integer size, Integer totalCount) {
		if (this.length == null || this.begin == null) {
			logger.debug("分页信息不完整，不能更新。");
			return;
		}
		this.size = size;
		if (totalCount != null) {
			this.count = totalCount;
			this.totalPage = this.count / this.length + (this.count % this.length > 0 ? 1 : 0);
			this.currentPage = this.begin / this.length + (this.begin % this.length > 0 ? 1 : 0);
			if (this.currentPage > this.totalPage) {
				this.currentPage = this.totalPage;
			}
			this.isFirst = this.currentPage <= 0 ? true : false;
			this.isLast = (this.currentPage + 1) >= this.totalPage ? true : false;
		}
		// TODO 空异常处理 上面代码还需要检查。。
	}

	/**
	 * 返回所有参数的名字的集合数组
	 * 
	 * @return
	 */
	public static String[] getAllNamesArry() {
		String[] arry = { BEGIN, LENGTH, IS_COUNT, COUNT, TOTAL_PAGE, CURRENT_PAGE, IS_FIRST, IS_LAST, SIZE };
		return arry;
	}

	/**
	 * 临时添加的跟业务相关的工具方法
	 * 
	 * @return
	 */
	public PageModel validate(Integer totalCount) {
		if (begin == null || begin < 0) {
			begin = 0;
		}
		if (length == null || length <= 0) {
			length = 10;
		}
//		// 加入了默认业务信息
//		if(length>100){//分页最多一次查询100条
//		  logger.info("请求分页的每页记录数"+length+"大于100条,默认限定最大为100条.");
//		  length=100;
//		}
		if (totalCount != null && begin >= totalCount) {
			int tail = totalCount % length;
			tail = tail == 0 ? length : tail;
			begin = totalCount - tail;
			if (begin < 0) {
				begin = 0;
			}
		}
		return this;
	}
	
	//get set 方法
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
		this.currentPage = this.begin / this.length + (this.begin % this.length > 0 ? 1 : 0);
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
