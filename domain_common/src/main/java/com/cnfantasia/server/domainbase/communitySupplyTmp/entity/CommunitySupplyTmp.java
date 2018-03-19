package com.cnfantasia.server.domainbase.communitySupplyTmp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(店铺申请审核表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyTmp implements Serializable{
*/


public class CommunitySupplyTmp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 创建者Id */	private BigInteger createUserId;	/** 创建者门牌Id */	private BigInteger createRoomId;	/** 店铺类型 */	private BigInteger tCommunitySupplyTypeFId;	/** 正式店铺ID */	private BigInteger tCommunitySupplyFId;	/** 店铺名称 */	private String supplyName;	/** 行政区Id */	private BigInteger addressBlockId;	/** 物业小区Id */	private BigInteger groupBuildingId;	/** 详细地址描述 */	private String addressDetail;	/** 店铺电话 */	private String contectPhone;	/** 审核类型 */	private Integer auditType;	/** 审核状态 */	private Integer auditStatus;	/** 审核时间 */	private String auditTime;	/** 审核结果描述 */	private String auditDesc;	/** 商户电话 */	private String companyPhone;	/** 联系人 */	private String companyName;	/** 新增类型 */	private Integer addType;	/** 商家维护删除的店铺图片ID记录 */	private String delImgIds;	/** 商家维护删除的店铺电话ID记录 */	private String delContectIds;
	public CommunitySupplyTmp(){
	}
	public CommunitySupplyTmp(CommunitySupplyTmp arg){
		this.id = arg.getId();		this.createUserId = arg.getCreateUserId();		this.createRoomId = arg.getCreateRoomId();		this.tCommunitySupplyTypeFId = arg.gettCommunitySupplyTypeFId();		this.tCommunitySupplyFId = arg.gettCommunitySupplyFId();		this.supplyName = arg.getSupplyName();		this.addressBlockId = arg.getAddressBlockId();		this.groupBuildingId = arg.getGroupBuildingId();		this.addressDetail = arg.getAddressDetail();		this.contectPhone = arg.getContectPhone();		this.auditType = arg.getAuditType();		this.auditStatus = arg.getAuditStatus();		this.auditTime = arg.getAuditTime();		this.auditDesc = arg.getAuditDesc();		this.companyPhone = arg.getCompanyPhone();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.companyName = arg.getCompanyName();		this.addType = arg.getAddType();		this.delImgIds = arg.getDelImgIds();		this.delContectIds = arg.getDelContectIds();
	}
	/**	 * 	 * @param id 	 * @param createUserId 创建者Id	 * @param createRoomId 创建者门牌Id	 * @param tCommunitySupplyTypeFId 店铺类型	 * @param tCommunitySupplyFId 正式店铺ID	 * @param supplyName 店铺名称	 * @param addressBlockId 行政区Id	 * @param groupBuildingId 物业小区Id	 * @param addressDetail 详细地址描述	 * @param contectPhone 店铺电话	 * @param auditType 审核类型	 * @param auditStatus 审核状态	 * @param auditTime 审核时间	 * @param auditDesc 审核结果描述	 * @param companyPhone 商户电话	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param companyName 联系人	 * @param addType 新增类型	 * @param delImgIds 商家维护删除的店铺图片ID记录	 * @param delContectIds 商家维护删除的店铺电话ID记录	 */
	public CommunitySupplyTmp(BigInteger id,BigInteger createUserId,BigInteger createRoomId,BigInteger tCommunitySupplyTypeFId,BigInteger tCommunitySupplyFId,String supplyName,BigInteger addressBlockId,BigInteger groupBuildingId,String addressDetail,String contectPhone,Integer auditType,Integer auditStatus,String auditTime,String auditDesc,String companyPhone,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String companyName,Integer addType,String delImgIds,String delContectIds){
		this.id = id;		this.createUserId = createUserId;		this.createRoomId = createRoomId;		this.tCommunitySupplyTypeFId = tCommunitySupplyTypeFId;		this.tCommunitySupplyFId = tCommunitySupplyFId;		this.supplyName = supplyName;		this.addressBlockId = addressBlockId;		this.groupBuildingId = groupBuildingId;		this.addressDetail = addressDetail;		this.contectPhone = contectPhone;		this.auditType = auditType;		this.auditStatus = auditStatus;		this.auditTime = auditTime;		this.auditDesc = auditDesc;		this.companyPhone = companyPhone;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.companyName = companyName;		this.addType = addType;		this.delImgIds = delImgIds;		this.delContectIds = delContectIds;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("createUserId=").append(createUserId).append(";");		sbf.append("createRoomId=").append(createRoomId).append(";");		sbf.append("tCommunitySupplyTypeFId=").append(tCommunitySupplyTypeFId).append(";");		sbf.append("tCommunitySupplyFId=").append(tCommunitySupplyFId).append(";");		sbf.append("supplyName=").append(supplyName).append(";");		sbf.append("addressBlockId=").append(addressBlockId).append(";");		sbf.append("groupBuildingId=").append(groupBuildingId).append(";");		sbf.append("addressDetail=").append(addressDetail).append(";");		sbf.append("contectPhone=").append(contectPhone).append(";");		sbf.append("auditType=").append(auditType).append(";");		sbf.append("auditStatus=").append(auditStatus).append(";");		sbf.append("auditTime=").append(auditTime).append(";");		sbf.append("auditDesc=").append(auditDesc).append(";");		sbf.append("companyPhone=").append(companyPhone).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("companyName=").append(companyName).append(";");		sbf.append("addType=").append(addType).append(";");		sbf.append("delImgIds=").append(delImgIds).append(";");		sbf.append("delContectIds=").append(delContectIds).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getCreateUserId() {		return createUserId;	}	public void setCreateUserId(BigInteger createUserId) {		this.createUserId = createUserId;	}	public BigInteger getCreateRoomId() {		return createRoomId;	}	public void setCreateRoomId(BigInteger createRoomId) {		this.createRoomId = createRoomId;	}	public BigInteger gettCommunitySupplyTypeFId() {		return tCommunitySupplyTypeFId;	}	public void settCommunitySupplyTypeFId(BigInteger tCommunitySupplyTypeFId) {		this.tCommunitySupplyTypeFId = tCommunitySupplyTypeFId;	}	public BigInteger gettCommunitySupplyFId() {		return tCommunitySupplyFId;	}	public void settCommunitySupplyFId(BigInteger tCommunitySupplyFId) {		this.tCommunitySupplyFId = tCommunitySupplyFId;	}	public String getSupplyName() {		return supplyName;	}	public void setSupplyName(String supplyName) {		this.supplyName = supplyName;	}	public BigInteger getAddressBlockId() {		return addressBlockId;	}	public void setAddressBlockId(BigInteger addressBlockId) {		this.addressBlockId = addressBlockId;	}	public BigInteger getGroupBuildingId() {		return groupBuildingId;	}	public void setGroupBuildingId(BigInteger groupBuildingId) {		this.groupBuildingId = groupBuildingId;	}	public String getAddressDetail() {		return addressDetail;	}	public void setAddressDetail(String addressDetail) {		this.addressDetail = addressDetail;	}	public String getContectPhone() {		return contectPhone;	}	public void setContectPhone(String contectPhone) {		this.contectPhone = contectPhone;	}	public Integer getAuditType() {		return auditType;	}	public void setAuditType(Integer auditType) {		this.auditType = auditType;	}	public Integer getAuditStatus() {		return auditStatus;	}	public void setAuditStatus(Integer auditStatus) {		this.auditStatus = auditStatus;	}	public String getAuditTime() {		return auditTime;	}	public void setAuditTime(String auditTime) {		this.auditTime = auditTime;	}	public String getAuditDesc() {		return auditDesc;	}	public void setAuditDesc(String auditDesc) {		this.auditDesc = auditDesc;	}	public String getCompanyPhone() {		return companyPhone;	}	public void setCompanyPhone(String companyPhone) {		this.companyPhone = companyPhone;	}	public String getCompanyName() {		return companyName;	}	public void setCompanyName(String companyName) {		this.companyName = companyName;	}	public Integer getAddType() {		return addType;	}	public void setAddType(Integer addType) {		this.addType = addType;	}	public String getDelImgIds() {		return delImgIds;	}	public void setDelImgIds(String delImgIds) {		this.delImgIds = delImgIds;	}	public String getDelContectIds() {		return delContectIds;	}	public void setDelContectIds(String delContectIds) {		this.delContectIds = delContectIds;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommunitySupplyTmp other = (CommunitySupplyTmp) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
