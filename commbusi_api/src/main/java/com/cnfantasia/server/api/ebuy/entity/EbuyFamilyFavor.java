package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

public class EbuyFamilyFavor implements Serializable {

	private static final long serialVersionUID = 3424400636247545719L;

	private Long id;
	
	private Boolean isAdmin;
	
	private String huaId;
	
	private String imgprofile;
	
	private String sex;
	
	private boolean hasNext;
	
	private List<EbuyProdForLst> ebuyProdForLstList;
	
	private Date updTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getImgprofile() {
		if(imgprofile != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.USER_IMAGE_PROFILE_CONFIG, imgprofile, updTime);
		}
		return imgprofile;
	}

	public void setImgprofile(String imgprofile) {
		this.imgprofile = imgprofile;
	}

	public String getHuaId() {
		return huaId;
	}

	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	public String getSex() {
		return sex;
	}
	
	public String getSexStr() {
		if("0".equals(sex)) {
			return "男";
		} else if("1".equals(sex)) {
			return "女";
		} else {
			return "未知";
		}
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<EbuyProdForLst> getEbuyProdForLstList() {
//		if(ebuyProdForLstList.size() > 4) {
//			hasNext = true;
//			ebuyProdForLstList = ebuyProdForLstList.subList(0, 4);
//		}
		return ebuyProdForLstList;
	}

	public void setEbuyProdForLstList(List<EbuyProdForLst> ebuyProdForLstList) {
		this.ebuyProdForLstList = ebuyProdForLstList;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

}
