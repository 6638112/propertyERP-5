package com.cnfantasia.server.domainbase.propertyRepair.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业报修单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyRepair implements Serializable{
*/


public class PropertyRepair extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 报修单编号 */	private String number;	/** 报修地址 */	private String address;	/** 联系电话 */	private String tel;	/** 报修内容 */	private String repairContent;	/** 期望上门日期 */	private String expectDate;	/** 期望上门时间-开始 */	private String expectTimeBegin;	/** 期望上门时间-结束 */	private String expectTimeEnd;	/** 维修单状态 */	private Integer repaireState;	/** 取消时间 */	private String cancelTime;	/** 分配时间 */	private String asignTime;	/** 分配说明 */	private String asignDesc;	/** 结束（完成）时间 */	private String finishedTime;	/** 报修单图片，多张用分号分隔 */	private String picUrl;	/**  */	private BigInteger tGroupBuildingFId;	/**  */	private BigInteger tUserFId;	/**  */	private BigInteger tRoomFId;	/** 是否已内转外 0 否 1 是 */	private Integer isTransed;	/**  */	private BigInteger tPropertyRepairerFId;	/** 预计上门时间—开始 */	private String estimateDoorTimeBegin;	/** 预计上门时间—结束 */	private String estimateDoorTimeEnd;	/**  */	private BigInteger tPropertyRepairTypeFId;	/** 物业软件订单ID，-1表示未推，非-1表示单号 */	private String softwareBillId;	/** 结束说明 */	private String finishDesc;
	public PropertyRepair(){
	}
	public PropertyRepair(PropertyRepair arg){
		this.id = arg.getId();		this.number = arg.getNumber();		this.address = arg.getAddress();		this.tel = arg.getTel();		this.repairContent = arg.getRepairContent();		this.expectDate = arg.getExpectDate();		this.expectTimeBegin = arg.getExpectTimeBegin();		this.expectTimeEnd = arg.getExpectTimeEnd();		this.repaireState = arg.getRepaireState();		this.cancelTime = arg.getCancelTime();		this.asignTime = arg.getAsignTime();		this.asignDesc = arg.getAsignDesc();		this.finishedTime = arg.getFinishedTime();		this.picUrl = arg.getPicUrl();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.tUserFId = arg.gettUserFId();		this.tRoomFId = arg.gettRoomFId();		this.isTransed = arg.getIsTransed();		this.tPropertyRepairerFId = arg.gettPropertyRepairerFId();		this.estimateDoorTimeBegin = arg.getEstimateDoorTimeBegin();		this.estimateDoorTimeEnd = arg.getEstimateDoorTimeEnd();		this.tPropertyRepairTypeFId = arg.gettPropertyRepairTypeFId();		this.softwareBillId = arg.getSoftwareBillId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.finishDesc = arg.getFinishDesc();
	}
	/**	 * 	 * @param id 	 * @param number 报修单编号	 * @param address 报修地址	 * @param tel 联系电话	 * @param repairContent 报修内容	 * @param expectDate 期望上门日期	 * @param expectTimeBegin 期望上门时间-开始	 * @param expectTimeEnd 期望上门时间-结束	 * @param repaireState 维修单状态	 * @param cancelTime 取消时间	 * @param asignTime 分配时间	 * @param asignDesc 分配说明	 * @param finishedTime 结束（完成）时间	 * @param picUrl 报修单图片，多张用分号分隔	 * @param tGroupBuildingFId 	 * @param tUserFId 	 * @param tRoomFId 	 * @param isTransed 是否已内转外 0 否 1 是	 * @param tPropertyRepairerFId 	 * @param estimateDoorTimeBegin 预计上门时间—开始	 * @param estimateDoorTimeEnd 预计上门时间—结束	 * @param tPropertyRepairTypeFId 	 * @param softwareBillId 物业软件订单ID，-1表示未推，非-1表示单号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param finishDesc 结束说明	 */
	public PropertyRepair(BigInteger id,String number,String address,String tel,String repairContent,String expectDate,String expectTimeBegin,String expectTimeEnd,Integer repaireState,String cancelTime,String asignTime,String asignDesc,String finishedTime,String picUrl,BigInteger tGroupBuildingFId,BigInteger tUserFId,BigInteger tRoomFId,Integer isTransed,BigInteger tPropertyRepairerFId,String estimateDoorTimeBegin,String estimateDoorTimeEnd,BigInteger tPropertyRepairTypeFId,String softwareBillId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String finishDesc){
		this.id = id;		this.number = number;		this.address = address;		this.tel = tel;		this.repairContent = repairContent;		this.expectDate = expectDate;		this.expectTimeBegin = expectTimeBegin;		this.expectTimeEnd = expectTimeEnd;		this.repaireState = repaireState;		this.cancelTime = cancelTime;		this.asignTime = asignTime;		this.asignDesc = asignDesc;		this.finishedTime = finishedTime;		this.picUrl = picUrl;		this.tGroupBuildingFId = tGroupBuildingFId;		this.tUserFId = tUserFId;		this.tRoomFId = tRoomFId;		this.isTransed = isTransed;		this.tPropertyRepairerFId = tPropertyRepairerFId;		this.estimateDoorTimeBegin = estimateDoorTimeBegin;		this.estimateDoorTimeEnd = estimateDoorTimeEnd;		this.tPropertyRepairTypeFId = tPropertyRepairTypeFId;		this.softwareBillId = softwareBillId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.finishDesc = finishDesc;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("address=").append(address).append(";");		sbf.append("tel=").append(tel).append(";");		sbf.append("repairContent=").append(repairContent).append(";");		sbf.append("expectDate=").append(expectDate).append(";");		sbf.append("expectTimeBegin=").append(expectTimeBegin).append(";");		sbf.append("expectTimeEnd=").append(expectTimeEnd).append(";");		sbf.append("repaireState=").append(repaireState).append(";");		sbf.append("cancelTime=").append(cancelTime).append(";");		sbf.append("asignTime=").append(asignTime).append(";");		sbf.append("asignDesc=").append(asignDesc).append(";");		sbf.append("finishedTime=").append(finishedTime).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tRoomFId=").append(tRoomFId).append(";");		sbf.append("isTransed=").append(isTransed).append(";");		sbf.append("tPropertyRepairerFId=").append(tPropertyRepairerFId).append(";");		sbf.append("estimateDoorTimeBegin=").append(estimateDoorTimeBegin).append(";");		sbf.append("estimateDoorTimeEnd=").append(estimateDoorTimeEnd).append(";");		sbf.append("tPropertyRepairTypeFId=").append(tPropertyRepairTypeFId).append(";");		sbf.append("softwareBillId=").append(softwareBillId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("finishDesc=").append(finishDesc).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getNumber() {		return number;	}	public void setNumber(String number) {		this.number = number;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}	public String getTel() {		return tel;	}	public void setTel(String tel) {		this.tel = tel;	}	public String getRepairContent() {		return repairContent;	}	public void setRepairContent(String repairContent) {		this.repairContent = repairContent;	}	public String getExpectDate() {		return expectDate;	}	public void setExpectDate(String expectDate) {		this.expectDate = expectDate;	}	public String getExpectTimeBegin() {		return expectTimeBegin;	}	public void setExpectTimeBegin(String expectTimeBegin) {		this.expectTimeBegin = expectTimeBegin;	}	public String getExpectTimeEnd() {		return expectTimeEnd;	}	public void setExpectTimeEnd(String expectTimeEnd) {		this.expectTimeEnd = expectTimeEnd;	}	public Integer getRepaireState() {		return repaireState;	}	public void setRepaireState(Integer repaireState) {		this.repaireState = repaireState;	}	public String getCancelTime() {		return cancelTime;	}	public void setCancelTime(String cancelTime) {		this.cancelTime = cancelTime;	}	public String getAsignTime() {		return asignTime;	}	public void setAsignTime(String asignTime) {		this.asignTime = asignTime;	}	public String getAsignDesc() {		return asignDesc;	}	public void setAsignDesc(String asignDesc) {		this.asignDesc = asignDesc;	}	public String getFinishedTime() {		return finishedTime;	}	public void setFinishedTime(String finishedTime) {		this.finishedTime = finishedTime;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettRoomFId() {		return tRoomFId;	}	public void settRoomFId(BigInteger tRoomFId) {		this.tRoomFId = tRoomFId;	}	public Integer getIsTransed() {		return isTransed;	}	public void setIsTransed(Integer isTransed) {		this.isTransed = isTransed;	}	public BigInteger gettPropertyRepairerFId() {		return tPropertyRepairerFId;	}	public void settPropertyRepairerFId(BigInteger tPropertyRepairerFId) {		this.tPropertyRepairerFId = tPropertyRepairerFId;	}	public String getEstimateDoorTimeBegin() {		return estimateDoorTimeBegin;	}	public void setEstimateDoorTimeBegin(String estimateDoorTimeBegin) {		this.estimateDoorTimeBegin = estimateDoorTimeBegin;	}	public String getEstimateDoorTimeEnd() {		return estimateDoorTimeEnd;	}	public void setEstimateDoorTimeEnd(String estimateDoorTimeEnd) {		this.estimateDoorTimeEnd = estimateDoorTimeEnd;	}	public BigInteger gettPropertyRepairTypeFId() {		return tPropertyRepairTypeFId;	}	public void settPropertyRepairTypeFId(BigInteger tPropertyRepairTypeFId) {		this.tPropertyRepairTypeFId = tPropertyRepairTypeFId;	}	public String getSoftwareBillId() {		return softwareBillId;	}	public void setSoftwareBillId(String softwareBillId) {		this.softwareBillId = softwareBillId;	}	public String getFinishDesc() {		return finishDesc;	}	public void setFinishDesc(String finishDesc) {		this.finishDesc = finishDesc;	}
	
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
		PropertyRepair other = (PropertyRepair) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
