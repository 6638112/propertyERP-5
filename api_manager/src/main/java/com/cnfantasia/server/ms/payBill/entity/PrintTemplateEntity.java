package com.cnfantasia.server.ms.payBill.entity;

import java.math.BigInteger;

public class PrintTemplateEntity {
	private BigInteger id;
	private Integer serviceState;
	private String templateContent;
	private String code;;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getServiceState() {
		return serviceState;
	}

	public void setServiceState(Integer serviceState) {
		this.serviceState = serviceState;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
