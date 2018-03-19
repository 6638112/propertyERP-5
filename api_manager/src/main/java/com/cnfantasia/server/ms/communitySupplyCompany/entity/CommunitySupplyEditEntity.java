package com.cnfantasia.server.ms.communitySupplyCompany.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

public class CommunitySupplyEditEntity extends CommunitySupply {
	private static final long serialVersionUID = 1L;
	/**商铺类别*/
	private String supplyType;
	/**商铺名称*/
	private String supplyName;
	/**省*/
	private String provinceName;
	/**市*/
	private String cityName;
	/**区*/
	private String blockName;
	/**区ID*/
	private BigInteger addressBlockId;
	/**联系人【认领人】*/
	private String belongUserName;
	/**店主电话【认领记录的电话】*/
	private String belongUserPhone;
	/**所属商户公司*/
	private BigInteger companyId;
	private String companyName;
	/**服务小区名称*/
	private String groupBuildingName;
	/**编辑的临时数据ID*/
	private BigInteger tmpId;
	/**编辑的临时电话*/
	private String tmpContectPhone;
	/**单据审核状态*/
	private Integer auditStatus;
	/**单据审核描述*/
	private String auditDesc;
	/**编辑的明细地址*/
	private String editAddressDetail;
	
	/**
	 * 商家店铺有多个联系方式
	 * */
	private List<CommunitySupplyContect> csContects = new ArrayList<CommunitySupplyContect>(); 
	
	public List<CommunitySupplyContect> getCsContects() {
		return csContects;
	}
	public void setCsContects(List<CommunitySupplyContect> csContects) {
		this.csContects = csContects;
	}
	public String getSupplyType() {
		return supplyType;
	}
	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public BigInteger getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getGroupBuildingName() {
		return groupBuildingName;
	}
	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public BigInteger getAddressBlockId() {
		return addressBlockId;
	}
	public void setAddressBlockId(BigInteger addressBlockId) {
		this.addressBlockId = addressBlockId;
	}
	public String getBelongUserName() {
		return belongUserName;
	}
	public void setBelongUserName(String belongUserName) {
		this.belongUserName = belongUserName;
	}
	public String getBelongUserPhone() {
		return belongUserPhone;
	}
	public void setBelongUserPhone(String belongUserPhone) {
		this.belongUserPhone = belongUserPhone;
	}
	public BigInteger getTmpId() {
		return tmpId;
	}
	public void setTmpId(BigInteger tmpId) {
		this.tmpId = tmpId;
	}
	public String getTmpContectPhone() {
		return tmpContectPhone;
	}
	public void setTmpContectPhone(String tmpContectPhone) {
		this.tmpContectPhone = tmpContectPhone;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	public String getAddress(){
		return this.getProvinceName()+this.getCityName()+this.getBlockName()+getEditAddressDetail();
	}
	public String getEditAddressDetail() {
		return editAddressDetail;
	}
	public void setEditAddressDetail(String editAddressDetail) {
		this.editAddressDetail = editAddressDetail;
	}
	public String getCompanyPhone(){//获取店铺的电话
		if(!this.getCsContects().isEmpty()){
			return this.getCsContects().get(0).getContectInfo();
		}else if(StringUtils.isNotEmpty(this.getTmpContectPhone())){
			return this.getTmpContectPhone();
		}
		return "";
	}
}
