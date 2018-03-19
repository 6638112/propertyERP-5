package com.cnfantasia.server.domainbase.prizeRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Double;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(中奖记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PrizeRecord implements Serializable{
*/


public class PrizeRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 抽奖时间 */	private String prizeTime;	/** 有效时间 */	private String endTime;	/** 折扣 */	private Double discount;	/** 用户与门牌 */	private BigInteger tUserHasTRoomFId;	/** 使用状态 */	private Integer status;	/** 折扣已使用 */	private Integer usedType;	/** 兑换的金额 */	private Long savedMoney;	/** 使用的时间 */	private String usedTime;	/** 折扣退回奖池状态 */	private Integer backPoolStatus;	/** 是否为调整记录 */	private Integer adjustType;	/** 来源奖池类型 */	private Integer poolType;
	public PrizeRecord(){
	}
	public PrizeRecord(PrizeRecord arg){
		this.id = arg.getId();		this.prizeTime = arg.getPrizeTime();		this.endTime = arg.getEndTime();		this.discount = arg.getDiscount();		this.tUserHasTRoomFId = arg.gettUserHasTRoomFId();		this.status = arg.getStatus();		this.usedType = arg.getUsedType();		this.savedMoney = arg.getSavedMoney();		this.usedTime = arg.getUsedTime();		this.backPoolStatus = arg.getBackPoolStatus();		this.adjustType = arg.getAdjustType();		this.poolType = arg.getPoolType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param prizeTime 抽奖时间	 * @param endTime 有效时间	 * @param discount 折扣	 * @param tUserHasTRoomFId 用户与门牌	 * @param status 使用状态	 * @param usedType 折扣已使用	 * @param savedMoney 兑换的金额	 * @param usedTime 使用的时间	 * @param backPoolStatus 折扣退回奖池状态	 * @param adjustType 是否为调整记录	 * @param poolType 来源奖池类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PrizeRecord(BigInteger id,String prizeTime,String endTime,Double discount,BigInteger tUserHasTRoomFId,Integer status,Integer usedType,Long savedMoney,String usedTime,Integer backPoolStatus,Integer adjustType,Integer poolType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.prizeTime = prizeTime;		this.endTime = endTime;		this.discount = discount;		this.tUserHasTRoomFId = tUserHasTRoomFId;		this.status = status;		this.usedType = usedType;		this.savedMoney = savedMoney;		this.usedTime = usedTime;		this.backPoolStatus = backPoolStatus;		this.adjustType = adjustType;		this.poolType = poolType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("prizeTime=").append(prizeTime).append(";");		sbf.append("endTime=").append(endTime).append(";");		sbf.append("discount=").append(discount).append(";");		sbf.append("tUserHasTRoomFId=").append(tUserHasTRoomFId).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("usedType=").append(usedType).append(";");		sbf.append("savedMoney=").append(savedMoney).append(";");		sbf.append("usedTime=").append(usedTime).append(";");		sbf.append("backPoolStatus=").append(backPoolStatus).append(";");		sbf.append("adjustType=").append(adjustType).append(";");		sbf.append("poolType=").append(poolType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPrizeTime() {		return prizeTime;	}	public void setPrizeTime(String prizeTime) {		this.prizeTime = prizeTime;	}	public String getEndTime() {		return endTime;	}	public void setEndTime(String endTime) {		this.endTime = endTime;	}	public Double getDiscount() {		return discount;	}	public void setDiscount(Double discount) {		this.discount = discount;	}	public BigInteger gettUserHasTRoomFId() {		return tUserHasTRoomFId;	}	public void settUserHasTRoomFId(BigInteger tUserHasTRoomFId) {		this.tUserHasTRoomFId = tUserHasTRoomFId;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getUsedType() {		return usedType;	}	public void setUsedType(Integer usedType) {		this.usedType = usedType;	}	public Long getSavedMoney() {		return savedMoney;	}	public void setSavedMoney(Long savedMoney) {		this.savedMoney = savedMoney;	}	public String getUsedTime() {		return usedTime;	}	public void setUsedTime(String usedTime) {		this.usedTime = usedTime;	}	public Integer getBackPoolStatus() {		return backPoolStatus;	}	public void setBackPoolStatus(Integer backPoolStatus) {		this.backPoolStatus = backPoolStatus;	}	public Integer getAdjustType() {		return adjustType;	}	public void setAdjustType(Integer adjustType) {		this.adjustType = adjustType;	}	public Integer getPoolType() {		return poolType;	}	public void setPoolType(Integer poolType) {		this.poolType = poolType;	}
	
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
		PrizeRecord other = (PrizeRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
