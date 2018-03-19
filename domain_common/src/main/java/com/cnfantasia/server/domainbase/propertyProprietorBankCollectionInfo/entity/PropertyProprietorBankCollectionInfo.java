package com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(业主银行托收信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyProprietorBankCollectionInfo implements Serializable{
*/


public class PropertyProprietorBankCollectionInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tRealRoomFId;	/** 小区id */	private BigInteger gbId;	/** 小区名称（冗余） */	private String gbName;	/** 楼栋号（冗余） */	private String buildingName;	/** 单元号（冗余） */	private String unitName;	/** 房号（冗余） */	private String rrNum;	/** 房间编码，出盘时用来标识房间的唯一标识 */	private String roomNo;	/** 业主开卡账号 */	private String bankAccount;	/**  */	private BigInteger tBasedataBankFId;	/** 业主开卡银行（冗余） */	private String bankName;	/** 开卡人姓名 */	private String bankOwnerName;
	public PropertyProprietorBankCollectionInfo(){
	}
	public PropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo arg){
		this.id = arg.getId();		this.tRealRoomFId = arg.gettRealRoomFId();		this.gbId = arg.getGbId();		this.gbName = arg.getGbName();		this.buildingName = arg.getBuildingName();		this.unitName = arg.getUnitName();		this.rrNum = arg.getRrNum();		this.roomNo = arg.getRoomNo();		this.bankAccount = arg.getBankAccount();		this.tBasedataBankFId = arg.gettBasedataBankFId();		this.bankName = arg.getBankName();		this.bankOwnerName = arg.getBankOwnerName();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRealRoomFId 	 * @param gbId 小区id	 * @param gbName 小区名称（冗余）	 * @param buildingName 楼栋号（冗余）	 * @param unitName 单元号（冗余）	 * @param rrNum 房号（冗余）	 * @param roomNo 房间编码，出盘时用来标识房间的唯一标识	 * @param bankAccount 业主开卡账号	 * @param tBasedataBankFId 	 * @param bankName 业主开卡银行（冗余）	 * @param bankOwnerName 开卡人姓名	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyProprietorBankCollectionInfo(BigInteger id,BigInteger tRealRoomFId,BigInteger gbId,String gbName,String buildingName,String unitName,String rrNum,String roomNo,String bankAccount,BigInteger tBasedataBankFId,String bankName,String bankOwnerName,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRealRoomFId = tRealRoomFId;		this.gbId = gbId;		this.gbName = gbName;		this.buildingName = buildingName;		this.unitName = unitName;		this.rrNum = rrNum;		this.roomNo = roomNo;		this.bankAccount = bankAccount;		this.tBasedataBankFId = tBasedataBankFId;		this.bankName = bankName;		this.bankOwnerName = bankOwnerName;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRealRoomFId=").append(tRealRoomFId).append(";");		sbf.append("gbId=").append(gbId).append(";");		sbf.append("gbName=").append(gbName).append(";");		sbf.append("buildingName=").append(buildingName).append(";");		sbf.append("unitName=").append(unitName).append(";");		sbf.append("rrNum=").append(rrNum).append(";");		sbf.append("roomNo=").append(roomNo).append(";");		sbf.append("bankAccount=").append(bankAccount).append(";");		sbf.append("tBasedataBankFId=").append(tBasedataBankFId).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankOwnerName=").append(bankOwnerName).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRealRoomFId() {		return tRealRoomFId;	}	public void settRealRoomFId(BigInteger tRealRoomFId) {		this.tRealRoomFId = tRealRoomFId;	}	public BigInteger getGbId() {		return gbId;	}	public void setGbId(BigInteger gbId) {		this.gbId = gbId;	}	public String getGbName() {		return gbName;	}	public void setGbName(String gbName) {		this.gbName = gbName;	}	public String getBuildingName() {		return buildingName;	}	public void setBuildingName(String buildingName) {		this.buildingName = buildingName;	}	public String getUnitName() {		return unitName;	}	public void setUnitName(String unitName) {		this.unitName = unitName;	}	public String getRrNum() {		return rrNum;	}	public void setRrNum(String rrNum) {		this.rrNum = rrNum;	}	public String getRoomNo() {		return roomNo;	}	public void setRoomNo(String roomNo) {		this.roomNo = roomNo;	}	public String getBankAccount() {		return bankAccount;	}	public void setBankAccount(String bankAccount) {		this.bankAccount = bankAccount;	}	public BigInteger gettBasedataBankFId() {		return tBasedataBankFId;	}	public void settBasedataBankFId(BigInteger tBasedataBankFId) {		this.tBasedataBankFId = tBasedataBankFId;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankOwnerName() {		return bankOwnerName;	}	public void setBankOwnerName(String bankOwnerName) {		this.bankOwnerName = bankOwnerName;	}
	
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
		PropertyProprietorBankCollectionInfo other = (PropertyProprietorBankCollectionInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
