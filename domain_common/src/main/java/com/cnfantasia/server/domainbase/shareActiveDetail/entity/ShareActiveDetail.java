package com.cnfantasia.server.domainbase.shareActiveDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(分享活动详情表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ShareActiveDetail implements Serializable{
*/


public class ShareActiveDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 参与者姓名 */	private String unionid;	/** 参与者ID */	private BigInteger participatorId;	/** 参与者姓名 */	private String participatorName;	/** 参与时间 */	private String participateTime;	/** 参与者头像 */	private String headpicUrl;	/** 参与者的留言 */	private String message;	/** 是否活动发起人 */	private Integer isAdmin;	/** 抽到的金额 */	private Long cashAmt;	/**  */	private BigInteger tShareActiveFId;	/** 惊喜类型 */	private Integer type;	/** 兑换码 */	private String exchcode;
	public ShareActiveDetail(){
	}
	/**	 * 	 * @param id 	 * @param unionid 参与者姓名	 * @param participatorId 参与者ID	 * @param participatorName 参与者姓名	 * @param participateTime 参与时间	 * @param headpicUrl 参与者头像	 * @param message 参与者的留言	 * @param isAdmin 是否活动发起人	 * @param cashAmt 抽到的金额	 * @param tShareActiveFId 	 * @param type 惊喜类型	 * @param exchcode 兑换码	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public ShareActiveDetail(BigInteger id,String unionid,BigInteger participatorId,String participatorName,String participateTime,String headpicUrl,String message,Integer isAdmin,Long cashAmt,BigInteger tShareActiveFId,Integer type,String exchcode,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.unionid = unionid;		this.participatorId = participatorId;		this.participatorName = participatorName;		this.participateTime = participateTime;		this.headpicUrl = headpicUrl;		this.message = message;		this.isAdmin = isAdmin;		this.cashAmt = cashAmt;		this.tShareActiveFId = tShareActiveFId;		this.type = type;		this.exchcode = exchcode;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("unionid=").append(unionid).append(";");		sbf.append("participatorId=").append(participatorId).append(";");		sbf.append("participatorName=").append(participatorName).append(";");		sbf.append("participateTime=").append(participateTime).append(";");		sbf.append("headpicUrl=").append(headpicUrl).append(";");		sbf.append("message=").append(message).append(";");		sbf.append("isAdmin=").append(isAdmin).append(";");		sbf.append("cashAmt=").append(cashAmt).append(";");		sbf.append("tShareActiveFId=").append(tShareActiveFId).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("exchcode=").append(exchcode).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUnionid() {		return unionid;	}	public void setUnionid(String unionid) {		this.unionid = unionid;	}	public BigInteger getParticipatorId() {		return participatorId;	}	public void setParticipatorId(BigInteger participatorId) {		this.participatorId = participatorId;	}	public String getParticipatorName() {		return participatorName;	}	public void setParticipatorName(String participatorName) {		this.participatorName = participatorName;	}	public String getParticipateTime() {		return participateTime;	}	public void setParticipateTime(String participateTime) {		this.participateTime = participateTime;	}	public String getHeadpicUrl() {		return headpicUrl;	}	public void setHeadpicUrl(String headpicUrl) {		this.headpicUrl = headpicUrl;	}	public String getMessage() {		return message;	}	public void setMessage(String message) {		this.message = message;	}	public Integer getIsAdmin() {		return isAdmin;	}	public void setIsAdmin(Integer isAdmin) {		this.isAdmin = isAdmin;	}	public Long getCashAmt() {		return cashAmt;	}	public void setCashAmt(Long cashAmt) {		this.cashAmt = cashAmt;	}	public BigInteger gettShareActiveFId() {		return tShareActiveFId;	}	public void settShareActiveFId(BigInteger tShareActiveFId) {		this.tShareActiveFId = tShareActiveFId;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getExchcode() {		return exchcode;	}	public void setExchcode(String exchcode) {		this.exchcode = exchcode;	}
	
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
		ShareActiveDetail other = (ShareActiveDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
