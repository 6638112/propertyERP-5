package com.cnfantasia.server.domainbase.realroomSoftwareFee.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(查询的物业账单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RealroomSoftwareFee implements Serializable{
*/


public class RealroomSoftwareFee extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 房间ID */	private BigInteger tRealRoomFId;	/** 数据请求日期，当天有效 */	private String dataReqDate;	/** 返回的数据，组装成Json存储 */	private String feeData;	/** 支付成功后通知物业软件次数，只通过三次，等于99表示已通知成功 */	private Integer notifyCount;
	public RealroomSoftwareFee(){
	}
	public RealroomSoftwareFee(RealroomSoftwareFee arg){
		this.id = arg.getId();		this.tRealRoomFId = arg.gettRealRoomFId();		this.dataReqDate = arg.getDataReqDate();		this.feeData = arg.getFeeData();		this.notifyCount = arg.getNotifyCount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRealRoomFId 房间ID	 * @param dataReqDate 数据请求日期，当天有效	 * @param feeData 返回的数据，组装成Json存储	 * @param notifyCount 支付成功后通知物业软件次数，只通过三次，等于99表示已通知成功	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RealroomSoftwareFee(BigInteger id,BigInteger tRealRoomFId,String dataReqDate,String feeData,Integer notifyCount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRealRoomFId = tRealRoomFId;		this.dataReqDate = dataReqDate;		this.feeData = feeData;		this.notifyCount = notifyCount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRealRoomFId=").append(tRealRoomFId).append(";");		sbf.append("dataReqDate=").append(dataReqDate).append(";");		sbf.append("feeData=").append(feeData).append(";");		sbf.append("notifyCount=").append(notifyCount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRealRoomFId() {		return tRealRoomFId;	}	public void settRealRoomFId(BigInteger tRealRoomFId) {		this.tRealRoomFId = tRealRoomFId;	}	public String getDataReqDate() {		return dataReqDate;	}	public void setDataReqDate(String dataReqDate) {		this.dataReqDate = dataReqDate;	}	public String getFeeData() {		return feeData;	}	public void setFeeData(String feeData) {		this.feeData = feeData;	}	public Integer getNotifyCount() {		return notifyCount;	}	public void setNotifyCount(Integer notifyCount) {		this.notifyCount = notifyCount;	}
	
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
		RealroomSoftwareFee other = (RealroomSoftwareFee) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
