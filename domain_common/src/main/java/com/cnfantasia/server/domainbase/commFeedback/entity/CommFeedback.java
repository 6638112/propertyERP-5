package com.cnfantasia.server.domainbase.commFeedback.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(意见反馈表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommFeedback implements Serializable{
*/


public class CommFeedback extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 意见详细内容 */	private String detail;	/** 联系方式 */	private String contect;	/** 反馈者Id */	private BigInteger userId;	/**  */	private String createTime;	/**  */	private BigInteger tCommFeedbackLabelFId;
	public CommFeedback(){
	}
	public CommFeedback(CommFeedback arg){
		this.id = arg.getId();		this.detail = arg.getDetail();		this.contect = arg.getContect();		this.userId = arg.getUserId();		this.createTime = arg.getCreateTime();		this.tCommFeedbackLabelFId = arg.gettCommFeedbackLabelFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param detail 意见详细内容	 * @param contect 联系方式	 * @param userId 反馈者Id	 * @param createTime 	 * @param tCommFeedbackLabelFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommFeedback(BigInteger id,String detail,String contect,BigInteger userId,String createTime,BigInteger tCommFeedbackLabelFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.detail = detail;		this.contect = contect;		this.userId = userId;		this.createTime = createTime;		this.tCommFeedbackLabelFId = tCommFeedbackLabelFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("detail=").append(detail).append(";");		sbf.append("contect=").append(contect).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("tCommFeedbackLabelFId=").append(tCommFeedbackLabelFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getDetail() {		return detail;	}	public void setDetail(String detail) {		this.detail = detail;	}	public String getContect() {		return contect;	}	public void setContect(String contect) {		this.contect = contect;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public BigInteger gettCommFeedbackLabelFId() {		return tCommFeedbackLabelFId;	}	public void settCommFeedbackLabelFId(BigInteger tCommFeedbackLabelFId) {		this.tCommFeedbackLabelFId = tCommFeedbackLabelFId;	}
	
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
		CommFeedback other = (CommFeedback) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
