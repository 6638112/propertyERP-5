package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.cnfantasia.server.api.fileServer.service.FileServerService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

public class DredgeProductView extends DredgeProduct{

	private static final long serialVersionUID = 1137913055331715050L;
	
	BigInteger cstId;
	String cstName;
	
	BigInteger dtId;
	String dtName;
	
	BigInteger dt2Id;
	String dt2Name;
	
	BigInteger ssId;
	String ssName;
	
	List<DredgeProductSpecification> prdtSpecList = new ArrayList<DredgeProductSpecification>();
	/**
	 * product pictures
	 */
	List<String> prdtPics = new ArrayList<String>();
	/**
	 *  introduce pictures
	 */
	List<String> introducePics = new ArrayList<String>();
	
	public BigInteger getCstId() {
		return cstId;
	}
	public void setCstId(BigInteger cstId) {
		this.cstId = cstId;
	}
	public String getCstName() {
		return cstName;
	}
	public void setCstName(String cstName) {
		this.cstName = cstName;
	}
	public BigInteger getDtId() {
		return dtId;
	}
	public void setDtId(BigInteger dtId) {
		this.dtId = dtId;
	}
	public String getDtName() {
		return dtName;
	}
	public void setDtName(String dtName) {
		this.dtName = dtName;
	}
	public String getDt2Name() {
		return dt2Name;
	}
	public void setDt2Name(String dt2Name) {
		this.dt2Name = dt2Name;
	}
	public String getSsName() {
		return ssName;
	}
	public void setSsName(String ssName) {
		this.ssName = ssName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigInteger getDt2Id() {
		return dt2Id;
	}
	public void setDt2Id(BigInteger dt2Id) {
		this.dt2Id = dt2Id;
	}
	public BigInteger getSsId() {
		return ssId;
	}
	public void setSsId(BigInteger ssId) {
		this.ssId = ssId;
	}
	public List<DredgeProductSpecification> getPrdtSpecList() {
		return prdtSpecList;
	}
	public void setPrdtSpecList(List<DredgeProductSpecification> prdtSpecList) {
		this.prdtSpecList = prdtSpecList;
	}
	public List<String> getPrdtPics() {
		if(StringUtils.isEmpty(this.getProductPic()))
			return prdtPics;
		
		String iconBasePath = "dredgePic/";
		FileServerService fileServerService = (FileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");

		String[] prdtPics2 = this.getProductPic().split(";");
		for(int i = 0; i < prdtPics2.length; i++){
			prdtPics.add(fileServerService.getAccessUrl(iconBasePath + prdtPics2[i], this));
		}
		
		return prdtPics;
	}
	public void setPrdtPics(List<String> prdtPics) {
		this.prdtPics = prdtPics;
	}
	
	public List<String> getIntroducePics() {
		if(StringUtils.isEmpty(this.getIntroducePic()))
			return introducePics;
		
		String iconBasePath = "dredgePic/";
		FileServerService fileServerService = (FileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");

		String[] prdtPics2 = this.getIntroducePic().split(";");
		for(int i = 0; i < prdtPics2.length; i++){
			introducePics.add(fileServerService.getAccessUrl(iconBasePath + prdtPics2[i], this));
		}
		
		return introducePics;
	}
	public void setIntroducePics(List<String> introducePics) {
		this.introducePics = introducePics;
	}
	
	
	public String getSharePicUrl() {
		if(StringUtils.isEmpty(this.getSharePic()))
			return "";
		
		String iconBasePath = "dredgePic/";
		FileServerService fileServerService = (FileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");

		return fileServerService.getAccessUrl(iconBasePath + getSharePic(), this);
	}
	
	public String getSharePushPicUrl() {
		if(StringUtils.isEmpty(this.getSharePushPic()))
			return "";
		
		String iconBasePath = "dredgePic/";
		FileServerService fileServerService = (FileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");

		return fileServerService.getAccessUrl(iconBasePath + getSharePushPic(), this);
	}
}
