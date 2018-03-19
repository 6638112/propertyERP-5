package com.cnfantasia.server.domainbase.messageView.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(消息推送的视图配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MessageView implements Serializable{
*/


public class MessageView extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 消息类别 */	private Integer typeId;	/**  */	private String desc;	/** 安卓跳转页面 */	private String androidView;	/** IOS跳转页面,改为记录IOS消息类型 */	private String iosView;
	public MessageView(){
	}
	public MessageView(MessageView arg){
		this.id = arg.getId();		this.typeId = arg.getTypeId();		this.desc = arg.getDesc();		this.androidView = arg.getAndroidView();		this.iosView = arg.getIosView();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param typeId 消息类别	 * @param desc 	 * @param androidView 安卓跳转页面	 * @param iosView IOS跳转页面,改为记录IOS消息类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MessageView(BigInteger id,Integer typeId,String desc,String androidView,String iosView,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.typeId = typeId;		this.desc = desc;		this.androidView = androidView;		this.iosView = iosView;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("typeId=").append(typeId).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("androidView=").append(androidView).append(";");		sbf.append("iosView=").append(iosView).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getTypeId() {		return typeId;	}	public void setTypeId(Integer typeId) {		this.typeId = typeId;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getAndroidView() {		return androidView;	}	public void setAndroidView(String androidView) {		this.androidView = androidView;	}	public String getIosView() {		return iosView;	}	public void setIosView(String iosView) {		this.iosView = iosView;	}
	
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
		MessageView other = (MessageView) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
