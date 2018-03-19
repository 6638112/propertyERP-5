package com.cnfantasia.server.ms.pub.pagination;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

/**
 * displaytag的分页接口PaginatedList实现
 * 
 * @author wenfq 2014-12-05
 * @param <T>
 * 
 */
public class PaginatedListImpl<T> implements PaginatedList {

	/**
	 * 每页的列表
	 */
	private List<T> list;


	/**
	 * 当前页码
	 */
	private int pageNumber = 1;


	/**
	 * 每页记录数 page size
	 */
	private int objectsPerPage = 15;


	/**
	 * 总记录数
	 */
	private int fullListSize = 0;

	private String sortCriterion;

	private SortOrderEnum sortDirection;

	private String searchId;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public int getFullListSize() {
		return fullListSize;
	}

	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
}
