package com.cnfantasia.server.domainbase.operationMsgPushConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(运营消息推送配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OperationMsgPushConfig implements Serializable{
*/


public class OperationMsgPushConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 唯一编号 */	private String number;	/** IOS所有参数 */	private String iosParam;	/** andoid所需参数 */	private String androidParam;	/** huawei所需参数 */	private String huaweiParam;	/** 跳转描述 */	private String jumpDescription;
	public OperationMsgPushConfig(){
	}
	public OperationMsgPushConfig(OperationMsgPushConfig arg){
		this.id = arg.getId();		this.number = arg.getNumber();		this.iosParam = arg.getIosParam();		this.androidParam = arg.getAndroidParam();		this.huaweiParam = arg.getHuaweiParam();		this.jumpDescription = arg.getJumpDescription();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param number 唯一编号	 * @param iosParam IOS所有参数	 * @param androidParam andoid所需参数	 * @param huaweiParam huawei所需参数	 * @param jumpDescription 跳转描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OperationMsgPushConfig(BigInteger id,String number,String iosParam,String androidParam,String huaweiParam,String jumpDescription,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.number = number;		this.iosParam = iosParam;		this.androidParam = androidParam;		this.huaweiParam = huaweiParam;		this.jumpDescription = jumpDescription;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("iosParam=").append(iosParam).append(";");		sbf.append("androidParam=").append(androidParam).append(";");		sbf.append("huaweiParam=").append(huaweiParam).append(";");		sbf.append("jumpDescription=").append(jumpDescription).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getNumber() {		return number;	}	public void setNumber(String number) {		this.number = number;	}	public String getIosParam() {		return iosParam;	}	public void setIosParam(String iosParam) {		this.iosParam = iosParam;	}	public String getAndroidParam() {		return androidParam;	}	public void setAndroidParam(String androidParam) {		this.androidParam = androidParam;	}	public String getHuaweiParam() {		return huaweiParam;	}	public void setHuaweiParam(String huaweiParam) {		this.huaweiParam = huaweiParam;	}	public String getJumpDescription() {		return jumpDescription;	}	public void setJumpDescription(String jumpDescription) {		this.jumpDescription = jumpDescription;	}
	
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
		OperationMsgPushConfig other = (OperationMsgPushConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
