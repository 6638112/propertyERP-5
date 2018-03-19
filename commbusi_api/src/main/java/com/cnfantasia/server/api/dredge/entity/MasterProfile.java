package com.cnfantasia.server.api.dredge.entity;

import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 师傅的个人信息
 * @author wenfq
 *
 */
public class MasterProfile {
	long id;
	String name;
	String headImgUrl;
	List<String> blockNames;
	List<String> dredgeTypeNames;
	int certificateStatus;
	String auditDesc;
	String tel;
	String personalDesc;
	double level;//评分等级
	
	/**
	 * 是否是物业的师傅，0不是，1是
	 */
	int isPropertyMaster = 0;

	
	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public int getIsPropertyMaster() {
		return isPropertyMaster;
	}

	public void setIsPropertyMaster(int isPropertyMaster) {
		this.isPropertyMaster = isPropertyMaster;
	}

	public String getPersonalDesc() {
		return personalDesc;
	}

	public void setPersonalDesc(String personalDesc) {
		this.personalDesc = personalDesc;
	}

	public List<String> getDredgeTypeNames() {
		return dredgeTypeNames;
	}

	public void setDredgeTypeNames(List<String> dredgeTypeNames) {
		this.dredgeTypeNames = dredgeTypeNames;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImgUrl() {
		if (headImgUrl != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.USER_IMAGE_PROFILE_CONFIG, headImgUrl, null);
		} else {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, DredgeConstant.DredgeMasterDefaultHeadImg, null);
		}
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public List<String> getBlockNames() {
		return blockNames;
	}

	public void setBlockNames(List<String> blockNames) {
		this.blockNames = blockNames;
	}

	public int getCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(int certificateStatus) {
		this.certificateStatus = certificateStatus;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
