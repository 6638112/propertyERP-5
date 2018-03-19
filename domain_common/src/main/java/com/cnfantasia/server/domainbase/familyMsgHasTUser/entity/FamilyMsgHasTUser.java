package com.cnfantasia.server.domainbase.familyMsgHasTUser.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(家庭留言艾特的用户列表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FamilyMsgHasTUser implements Serializable{
*/


public class FamilyMsgHasTUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 留言板记录Id */	private BigInteger tFamilyMsgFId;	/** 用户Id */	private BigInteger tUserFId;	/**  */	private String huaId;	/** 昵称 */	private String nickName;	/** 真实名称 */	private String realName;	/** 性别 */	private String sex;	/** 头像地址 */	private String imgprofile;	/** 签名 */	private String signature;
	public FamilyMsgHasTUser(){
	}
	public FamilyMsgHasTUser(FamilyMsgHasTUser arg){
		this.id = arg.getId();		this.tFamilyMsgFId = arg.gettFamilyMsgFId();		this.tUserFId = arg.gettUserFId();		this.huaId = arg.getHuaId();		this.nickName = arg.getNickName();		this.realName = arg.getRealName();		this.sex = arg.getSex();		this.imgprofile = arg.getImgprofile();		this.signature = arg.getSignature();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tFamilyMsgFId 留言板记录Id	 * @param tUserFId 用户Id	 * @param huaId 	 * @param nickName 昵称	 * @param realName 真实名称	 * @param sex 性别	 * @param imgprofile 头像地址	 * @param signature 签名	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public FamilyMsgHasTUser(BigInteger id,BigInteger tFamilyMsgFId,BigInteger tUserFId,String huaId,String nickName,String realName,String sex,String imgprofile,String signature,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tFamilyMsgFId = tFamilyMsgFId;		this.tUserFId = tUserFId;		this.huaId = huaId;		this.nickName = nickName;		this.realName = realName;		this.sex = sex;		this.imgprofile = imgprofile;		this.signature = signature;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tFamilyMsgFId=").append(tFamilyMsgFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("huaId=").append(huaId).append(";");		sbf.append("nickName=").append(nickName).append(";");		sbf.append("realName=").append(realName).append(";");		sbf.append("sex=").append(sex).append(";");		sbf.append("imgprofile=").append(imgprofile).append(";");		sbf.append("signature=").append(signature).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettFamilyMsgFId() {		return tFamilyMsgFId;	}	public void settFamilyMsgFId(BigInteger tFamilyMsgFId) {		this.tFamilyMsgFId = tFamilyMsgFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getHuaId() {		return huaId;	}	public void setHuaId(String huaId) {		this.huaId = huaId;	}	public String getNickName() {		return nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public String getRealName() {		return realName;	}	public void setRealName(String realName) {		this.realName = realName;	}	public String getSex() {		return sex;	}	public void setSex(String sex) {		this.sex = sex;	}	public String getImgprofile() {		return imgprofile;	}	public void setImgprofile(String imgprofile) {		this.imgprofile = imgprofile;	}	public String getSignature() {		return signature;	}	public void setSignature(String signature) {		this.signature = signature;	}
	
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
		FamilyMsgHasTUser other = (FamilyMsgHasTUser) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
