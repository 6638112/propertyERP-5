/**
 * Copyright (C) 2015 Shenzhen virgo network technology Co., Ltd. All rights reserved. 
 *
 * @Date:2015年10月20日 下午2:46:47
 *
 * @Version V1.0
 */
package com.cnfantasia.server.common.utils.excel.domain;

import java.util.List;

/**
 * @ClassName:ExcelDTO
 * @Date:2016年08月01日 上午10:04:22
 * @Description:(excel数据集合DTO)
 *
 */
public class ExcelDTO {
	/**
	 * 标题
	 */
	private List<String> titles;
	/**
	 * 参数
	 */
	private List<String> properties;
	
	/**
	 * 导入时，记录需要导出数据所在的列数
	 */
	private List<Integer> indexs;
	
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<String> getProperties() {
		return properties;
	}
	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
	public List<Integer> getIndexs() {
		return indexs;
	}
	public void setIndexs(List<Integer> indexs) {
		this.indexs = indexs;
	}
}
