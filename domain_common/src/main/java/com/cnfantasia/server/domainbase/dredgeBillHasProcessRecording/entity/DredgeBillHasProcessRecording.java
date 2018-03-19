package com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(上门服务单流程记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBillHasProcessRecording implements Serializable{
*/


public class DredgeBillHasProcessRecording extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 1上门服务单，3物业公共报修单 */	private Integer billType;	/** f_bill_type=1时有值 */	private BigInteger tDredgeBillFId;	/** f_bill_type=2时有值 */	private BigInteger tPropertyRepairFId;	/** 流程描述 */	private String processDesc;	/** 流程图片，以逗号;分隔 */	private String processPics;	/** 新增流程记录的时间 */	private String recordingTime;
	public DredgeBillHasProcessRecording(){
	}
	public DredgeBillHasProcessRecording(DredgeBillHasProcessRecording arg){
		this.id = arg.getId();		this.billType = arg.getBillType();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.tPropertyRepairFId = arg.gettPropertyRepairFId();		this.processDesc = arg.getProcessDesc();		this.processPics = arg.getProcessPics();		this.recordingTime = arg.getRecordingTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param billType 1上门服务单，3物业公共报修单	 * @param tDredgeBillFId f_bill_type=1时有值	 * @param tPropertyRepairFId f_bill_type=2时有值	 * @param processDesc 流程描述	 * @param processPics 流程图片，以逗号;分隔	 * @param recordingTime 新增流程记录的时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeBillHasProcessRecording(BigInteger id,Integer billType,BigInteger tDredgeBillFId,BigInteger tPropertyRepairFId,String processDesc,String processPics,String recordingTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.billType = billType;		this.tDredgeBillFId = tDredgeBillFId;		this.tPropertyRepairFId = tPropertyRepairFId;		this.processDesc = processDesc;		this.processPics = processPics;		this.recordingTime = recordingTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("billType=").append(billType).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("tPropertyRepairFId=").append(tPropertyRepairFId).append(";");		sbf.append("processDesc=").append(processDesc).append(";");		sbf.append("processPics=").append(processPics).append(";");		sbf.append("recordingTime=").append(recordingTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getBillType() {		return billType;	}	public void setBillType(Integer billType) {		this.billType = billType;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public BigInteger gettPropertyRepairFId() {		return tPropertyRepairFId;	}	public void settPropertyRepairFId(BigInteger tPropertyRepairFId) {		this.tPropertyRepairFId = tPropertyRepairFId;	}	public String getProcessDesc() {		return processDesc;	}	public void setProcessDesc(String processDesc) {		this.processDesc = processDesc;	}	public String getProcessPics() {		return processPics;	}	public void setProcessPics(String processPics) {		this.processPics = processPics;	}	public String getRecordingTime() {		return recordingTime;	}	public void setRecordingTime(String recordingTime) {		this.recordingTime = recordingTime;	}
	
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
		DredgeBillHasProcessRecording other = (DredgeBillHasProcessRecording) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
