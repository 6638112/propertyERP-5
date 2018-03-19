package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;

/**
 * 停车场信息
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 下午4:37:07
 */
public class PlotInfo {
	/** 停车场名称 */
	private String name;
	/** 停车场地址 */
	private String address;
	/** 纬度 */
	private BigDecimal latitude;
	/** 经度 */
	private BigDecimal longitude;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

}
