package com.cnfantasia.server.domainbase.msPrizeGift.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Double;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(奖品详情表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MsPrizeGift implements Serializable{
*/


public class MsPrizeGift extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 奖项记录Id */	private BigInteger prizeOptionId;	/** 金额,数量 */	private Double number;	/** 单位 */	private String unit;	/** 兑换状态 */	private Integer convertStatus;	/** 已分配时间 */	private String convertTime;	/** 兑换人Id */	private BigInteger convertUserId;	/** 兑换人当前门牌Id */	private BigInteger convertRoomId;	/** 所属活动Id */	private BigInteger prizeActId;
	public MsPrizeGift(){
	}
	public MsPrizeGift(MsPrizeGift arg){
		this.id = arg.getId();		this.prizeOptionId = arg.getPrizeOptionId();		this.number = arg.getNumber();		this.unit = arg.getUnit();		this.convertStatus = arg.getConvertStatus();		this.convertTime = arg.getConvertTime();		this.convertUserId = arg.getConvertUserId();		this.convertRoomId = arg.getConvertRoomId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.prizeActId = arg.getPrizeActId();
	}
	/**	 * 	 * @param id 	 * @param prizeOptionId 奖项记录Id	 * @param number 金额,数量	 * @param unit 单位	 * @param convertStatus 兑换状态	 * @param convertTime 已分配时间	 * @param convertUserId 兑换人Id	 * @param convertRoomId 兑换人当前门牌Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param prizeActId 所属活动Id	 */
	public MsPrizeGift(BigInteger id,BigInteger prizeOptionId,Double number,String unit,Integer convertStatus,String convertTime,BigInteger convertUserId,BigInteger convertRoomId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger prizeActId){
		this.id = id;		this.prizeOptionId = prizeOptionId;		this.number = number;		this.unit = unit;		this.convertStatus = convertStatus;		this.convertTime = convertTime;		this.convertUserId = convertUserId;		this.convertRoomId = convertRoomId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.prizeActId = prizeActId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("prizeOptionId=").append(prizeOptionId).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("unit=").append(unit).append(";");		sbf.append("convertStatus=").append(convertStatus).append(";");		sbf.append("convertTime=").append(convertTime).append(";");		sbf.append("convertUserId=").append(convertUserId).append(";");		sbf.append("convertRoomId=").append(convertRoomId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("prizeActId=").append(prizeActId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPrizeOptionId() {		return prizeOptionId;	}	public void setPrizeOptionId(BigInteger prizeOptionId) {		this.prizeOptionId = prizeOptionId;	}	public Double getNumber() {		return number;	}	public void setNumber(Double number) {		this.number = number;	}	public String getUnit() {		return unit;	}	public void setUnit(String unit) {		this.unit = unit;	}	public Integer getConvertStatus() {		return convertStatus;	}	public void setConvertStatus(Integer convertStatus) {		this.convertStatus = convertStatus;	}	public String getConvertTime() {		return convertTime;	}	public void setConvertTime(String convertTime) {		this.convertTime = convertTime;	}	public BigInteger getConvertUserId() {		return convertUserId;	}	public void setConvertUserId(BigInteger convertUserId) {		this.convertUserId = convertUserId;	}	public BigInteger getConvertRoomId() {		return convertRoomId;	}	public void setConvertRoomId(BigInteger convertRoomId) {		this.convertRoomId = convertRoomId;	}	public BigInteger getPrizeActId() {		return prizeActId;	}	public void setPrizeActId(BigInteger prizeActId) {		this.prizeActId = prizeActId;	}
	
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
		MsPrizeGift other = (MsPrizeGift) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
