package com.cnfantasia.server.api.access.entity;

import java.math.BigInteger;

/**
 * 解放区停车场信息
 * 
 * @author liyulin
 * @version 1.0 2017年9月20日 下午5:24:41
 */
public class JfqPlotEntity {

	/** 停车场编号（小区id） */
	private BigInteger plotId;
	/** 停车场名字 */
	private String plotName;
	/** 编码，用于区分第三方 */
	private String code;

	public BigInteger getPlotId() {
		return plotId;
	}

	public void setPlotId(BigInteger plotId) {
		this.plotId = plotId;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
