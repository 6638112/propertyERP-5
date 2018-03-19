package com.cnfantasia.server.domainbase.commUserSession.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户登录session表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommUserSession implements Serializable{
*/


public class CommUserSession extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 渠道信息 */	private Integer subChannel;	/**  */	private String sessionKey;	/** 创建时间 */	private String createTime;	/** 账号 */	private String accNo;	/** 账号类别 */	private Long accType;	/** 系统自动生成的sessionId */	private String autoSessionId;
	public CommUserSession(){
	}
	public CommUserSession(CommUserSession arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.subChannel = arg.getSubChannel();		this.sessionKey = arg.getSessionKey();		this.createTime = arg.getCreateTime();		this.accNo = arg.getAccNo();		this.accType = arg.getAccType();		this.autoSessionId = arg.getAutoSessionId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param subChannel 渠道信息	 * @param sessionKey 	 * @param createTime 创建时间	 * @param accNo 账号	 * @param accType 账号类别	 * @param autoSessionId 系统自动生成的sessionId	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommUserSession(BigInteger id,BigInteger userId,Integer subChannel,String sessionKey,String createTime,String accNo,Long accType,String autoSessionId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.subChannel = subChannel;		this.sessionKey = sessionKey;		this.createTime = createTime;		this.accNo = accNo;		this.accType = accType;		this.autoSessionId = autoSessionId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("subChannel=").append(subChannel).append(";");		sbf.append("sessionKey=").append(sessionKey).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("accNo=").append(accNo).append(";");		sbf.append("accType=").append(accType).append(";");		sbf.append("autoSessionId=").append(autoSessionId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public Integer getSubChannel() {		return subChannel;	}	public void setSubChannel(Integer subChannel) {		this.subChannel = subChannel;	}	public String getSessionKey() {		return sessionKey;	}	public void setSessionKey(String sessionKey) {		this.sessionKey = sessionKey;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getAccNo() {		return accNo;	}	public void setAccNo(String accNo) {		this.accNo = accNo;	}	public Long getAccType() {		return accType;	}	public void setAccType(Long accType) {		this.accType = accType;	}	public String getAutoSessionId() {		return autoSessionId;	}	public void setAutoSessionId(String autoSessionId) {		this.autoSessionId = autoSessionId;	}
	
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
		CommUserSession other = (CommUserSession) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
