package com.cnfantasia.server.domainbase.lotteryDrawRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(幸运抽奖记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LotteryDrawRecord implements Serializable{
*/


public class LotteryDrawRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 奖品id（不中奖使用默认奖品id） */	private BigInteger tLotteryDrawProductId;	/** 用户id */	private BigInteger tUserId;	/** 抽奖时间 */	private String lotteryDrawTime;	/** 中奖状态（1中奖，2未中奖） */	private Integer status;
	public LotteryDrawRecord(){
	}
	public LotteryDrawRecord(LotteryDrawRecord arg){
		this.id = arg.getId();		this.tLotteryDrawProductId = arg.gettLotteryDrawProductId();		this.tUserId = arg.gettUserId();		this.lotteryDrawTime = arg.getLotteryDrawTime();		this.status = arg.getStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tLotteryDrawProductId 奖品id（不中奖使用默认奖品id）	 * @param tUserId 用户id	 * @param lotteryDrawTime 抽奖时间	 * @param status 中奖状态（1中奖，2未中奖）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public LotteryDrawRecord(BigInteger id,BigInteger tLotteryDrawProductId,BigInteger tUserId,String lotteryDrawTime,Integer status,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tLotteryDrawProductId = tLotteryDrawProductId;		this.tUserId = tUserId;		this.lotteryDrawTime = lotteryDrawTime;		this.status = status;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tLotteryDrawProductId=").append(tLotteryDrawProductId).append(";");		sbf.append("tUserId=").append(tUserId).append(";");		sbf.append("lotteryDrawTime=").append(lotteryDrawTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettLotteryDrawProductId() {		return tLotteryDrawProductId;	}	public void settLotteryDrawProductId(BigInteger tLotteryDrawProductId) {		this.tLotteryDrawProductId = tLotteryDrawProductId;	}	public BigInteger gettUserId() {		return tUserId;	}	public void settUserId(BigInteger tUserId) {		this.tUserId = tUserId;	}	public String getLotteryDrawTime() {		return lotteryDrawTime;	}	public void setLotteryDrawTime(String lotteryDrawTime) {		this.lotteryDrawTime = lotteryDrawTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}
	
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
		LotteryDrawRecord other = (LotteryDrawRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
