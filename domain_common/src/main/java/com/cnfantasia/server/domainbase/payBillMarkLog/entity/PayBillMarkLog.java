package com.cnfantasia.server.domainbase.payBillMarkLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业缴费账单标记日志) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayBillMarkLog implements Serializable{
*/


public class PayBillMarkLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tPayBillFId;
	/**  */
	private BigInteger tRealRoomFId;
	/**  */
	private String mobile;

	public PayBillMarkLog(){
	}
	public PayBillMarkLog(PayBillMarkLog arg){
		this.id = arg.getId();
		this.tPayBillFId = arg.gettPayBillFId();
		this.tRealRoomFId = arg.gettRealRoomFId();
		this.mobile = arg.getMobile();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tPayBillFId 
	 * @param tRealRoomFId 
	 * @param mobile 
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 
	 */

	public PayBillMarkLog(BigInteger id,BigInteger tPayBillFId,BigInteger tRealRoomFId,String mobile,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tPayBillFId = tPayBillFId;
		this.tRealRoomFId = tRealRoomFId;
		this.mobile = mobile;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");
		sbf.append("tRealRoomFId=").append(tRealRoomFId).append(";");
		sbf.append("mobile=").append(mobile).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettPayBillFId() {
		return tPayBillFId;
	}
	public void settPayBillFId(BigInteger tPayBillFId) {
		this.tPayBillFId = tPayBillFId;
	}
	public BigInteger gettRealRoomFId() {
		return tRealRoomFId;
	}
	public void settRealRoomFId(BigInteger tRealRoomFId) {
		this.tRealRoomFId = tRealRoomFId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
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
		PayBillMarkLog other = (PayBillMarkLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
