package com.cnfantasia.server.api.ebuy.entity;

/* import java.io.Serializable;*/

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyAdvertise implements Serializable{
*/


public class EbuyAdvertise implements Serializable {

	private static final long serialVersionUID = 6707741451728943589L;
	/**  */
	private Long id;
	/**  */
	private String code;
	
	private String tittle;
	
	private Integer type;
	/**  */
	private String picName;
	
	private String picUrl;
	/**  */
	private String linkUrl;
	
	private Date updTime;

	private String iosAddr;

	private String androidAddr;

	private String iosParam;

	private String androidParam;

	private Integer frequency;

	private String firstLine;
	private String secondLine;
	private String thirdLine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLinkUrl() {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		if (linkUrl != null && !"".equals(linkUrl) && !linkUrl.contains("http") && linkUrl.contains("/") && !linkUrl.contains("../")) {
			return sysParamManager.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + linkUrl;
		}
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getIosAddr() {
		return iosAddr;
	}

	public void setIosAddr(String iosAddr) {
		this.iosAddr = iosAddr;
	}

	public String getAndroidAddr() {
		return androidAddr;
	}

	public void setAndroidAddr(String androidAddr) {
		this.androidAddr = androidAddr;
	}

	public String getIosParam() {
		return iosParam;
	}

	public void setIosParam(String iosParam) {
		this.iosParam = iosParam;
	}

	public String getAndroidParam() {
		return androidParam;
	}

	public void setAndroidParam(String androidParam) {
		this.androidParam = androidParam;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getThirdLine() {
		return thirdLine;
	}

	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}
}
