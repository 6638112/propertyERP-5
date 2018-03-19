package com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(选择周期数据详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AlterPeriodDataDetail implements Serializable{
*/


public class AlterPeriodDataDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 真实房间id */	private BigInteger tRealRoomId;	/** 物业费起始时间 */	private String billMonthStart;	/** 滞纳金起算时间 */	private String latefeeStart;	/** 滞纳金金额（分） */	private Long latefeeAmount;	/** 选择周期配置id */	private BigInteger tAlterPeriodCfgId;
	public AlterPeriodDataDetail(){
	}
	public AlterPeriodDataDetail(AlterPeriodDataDetail arg){
		this.id = arg.getId();		this.tRealRoomId = arg.gettRealRoomId();		this.billMonthStart = arg.getBillMonthStart();		this.latefeeStart = arg.getLatefeeStart();		this.latefeeAmount = arg.getLatefeeAmount();		this.tAlterPeriodCfgId = arg.gettAlterPeriodCfgId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRealRoomId 真实房间id	 * @param billMonthStart 物业费起始时间	 * @param latefeeStart 滞纳金起算时间	 * @param latefeeAmount 滞纳金金额（分）	 * @param tAlterPeriodCfgId 选择周期配置id	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public AlterPeriodDataDetail(BigInteger id,BigInteger tRealRoomId,String billMonthStart,String latefeeStart,Long latefeeAmount,BigInteger tAlterPeriodCfgId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRealRoomId = tRealRoomId;		this.billMonthStart = billMonthStart;		this.latefeeStart = latefeeStart;		this.latefeeAmount = latefeeAmount;		this.tAlterPeriodCfgId = tAlterPeriodCfgId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("latefeeStart=").append(latefeeStart).append(";");		sbf.append("latefeeAmount=").append(latefeeAmount).append(";");		sbf.append("tAlterPeriodCfgId=").append(tAlterPeriodCfgId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getLatefeeStart() {		return latefeeStart;	}	public void setLatefeeStart(String latefeeStart) {		this.latefeeStart = latefeeStart;	}	public Long getLatefeeAmount() {		return latefeeAmount;	}	public void setLatefeeAmount(Long latefeeAmount) {		this.latefeeAmount = latefeeAmount;	}	public BigInteger gettAlterPeriodCfgId() {		return tAlterPeriodCfgId;	}	public void settAlterPeriodCfgId(BigInteger tAlterPeriodCfgId) {		this.tAlterPeriodCfgId = tAlterPeriodCfgId;	}
	
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
		AlterPeriodDataDetail other = (AlterPeriodDataDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
