package com.cnfantasia.server.domainbase.msPrizeActHasOpt.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(抽奖活动与奖项关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MsPrizeActHasOpt implements Serializable{
*/


public class MsPrizeActHasOpt extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属活动id */	private BigInteger prizeActId;	/** 奖项记录Id */	private BigInteger prizeOptionId;	/** 总奖品额度 */	private Long totalMaxCount;	/** 每天最大派奖数 */	private Long dayMaxCount;
	public MsPrizeActHasOpt(){
	}
	public MsPrizeActHasOpt(MsPrizeActHasOpt arg){
		this.id = arg.getId();		this.prizeActId = arg.getPrizeActId();		this.prizeOptionId = arg.getPrizeOptionId();		this.totalMaxCount = arg.getTotalMaxCount();		this.dayMaxCount = arg.getDayMaxCount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param prizeActId 所属活动id	 * @param prizeOptionId 奖项记录Id	 * @param totalMaxCount 总奖品额度	 * @param dayMaxCount 每天最大派奖数	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MsPrizeActHasOpt(BigInteger id,BigInteger prizeActId,BigInteger prizeOptionId,Long totalMaxCount,Long dayMaxCount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.prizeActId = prizeActId;		this.prizeOptionId = prizeOptionId;		this.totalMaxCount = totalMaxCount;		this.dayMaxCount = dayMaxCount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("prizeActId=").append(prizeActId).append(";");		sbf.append("prizeOptionId=").append(prizeOptionId).append(";");		sbf.append("totalMaxCount=").append(totalMaxCount).append(";");		sbf.append("dayMaxCount=").append(dayMaxCount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPrizeActId() {		return prizeActId;	}	public void setPrizeActId(BigInteger prizeActId) {		this.prizeActId = prizeActId;	}	public BigInteger getPrizeOptionId() {		return prizeOptionId;	}	public void setPrizeOptionId(BigInteger prizeOptionId) {		this.prizeOptionId = prizeOptionId;	}	public Long getTotalMaxCount() {		return totalMaxCount;	}	public void setTotalMaxCount(Long totalMaxCount) {		this.totalMaxCount = totalMaxCount;	}	public Long getDayMaxCount() {		return dayMaxCount;	}	public void setDayMaxCount(Long dayMaxCount) {		this.dayMaxCount = dayMaxCount;	}
	
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
		MsPrizeActHasOpt other = (MsPrizeActHasOpt) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
