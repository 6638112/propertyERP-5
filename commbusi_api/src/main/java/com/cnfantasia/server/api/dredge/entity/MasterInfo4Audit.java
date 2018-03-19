package com.cnfantasia.server.api.dredge.entity;

import java.util.List;

import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 师傅信息，解放区审核用
 * @author wenfq
 *
 */
public class MasterInfo4Audit {
	long dwId;
	long huaId;
	String registTime;
	String mobile;
	String realName;
	String idNumber;//身份证号
	String idPic1;
	String idPic2;
	String personalDesc;
	int auditStatus;
	String auditDesc;
	
	List<String> blockNames;
	List<String> dredgeTypeNames;
	
	Integer starLevel;//星级
	
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public long getDwId() {
		return dwId;
	}
	public void setDwId(long dwId) {
		this.dwId = dwId;
	}
	public long getHuaId() {
		return huaId;
	}
	public void setHuaId(long huaId) {
		this.huaId = huaId;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdPic1() {
		return OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) + "/dredgePic/" + this.idPic1;
//		return idPic1;
	}
	public void setIdPic1(String idPic1) {
		this.idPic1 = idPic1;
	}
	public String getIdPic2() {
		return OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) + "/dredgePic/" + this.idPic2;
	}
	public void setIdPic2(String idPic2) {
		this.idPic2 = idPic2;
	}
	public String getPersonalDesc() {
		return personalDesc;
	}
	public void setPersonalDesc(String personalDesc) {
		this.personalDesc = personalDesc;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public List<String> getBlockNames() {
		return blockNames;
	}
	public void setBlockNames(List<String> blockNames) {
		this.blockNames = blockNames;
	}
	public List<String> getDredgeTypeNames() {
		return dredgeTypeNames;
	}
	public void setDredgeTypeNames(List<String> dredgeTypeNames) {
		this.dredgeTypeNames = dredgeTypeNames;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}
