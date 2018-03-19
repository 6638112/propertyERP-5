package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigInteger;

/**
 * 打印配置列表
 * 
 * @author liyulin
 * @version 1.0 2017年5月4日 上午11:37:15
 */
public class PrintConfigList {
	private BigInteger id;
	private String name;
	private String templateContent;
	private boolean config;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public boolean isConfig() {
		return config;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}

}
