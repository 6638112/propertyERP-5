package com.cnfantasia.server.domainbase.golfGroupDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(高尔夫组团详细表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GolfGroupDetail implements Serializable{
*/


public class GolfGroupDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 属于哪个团 */	private BigInteger tGolfGroupFId;	/** 团员的openid */	private String openid;	/**  */	private Integer isAdmin;	/**  */	private String phone;	/** 昵称 */	private String nickname;	/** 团员姓名 */	private String realName;	/** 头像的URL */	private String headpicUrl;
	public GolfGroupDetail(){
	}
	public GolfGroupDetail(GolfGroupDetail arg){
		this.id = arg.getId();		this.tGolfGroupFId = arg.gettGolfGroupFId();		this.openid = arg.getOpenid();		this.isAdmin = arg.getIsAdmin();		this.phone = arg.getPhone();		this.nickname = arg.getNickname();		this.realName = arg.getRealName();		this.headpicUrl = arg.getHeadpicUrl();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGolfGroupFId 属于哪个团	 * @param openid 团员的openid	 * @param isAdmin 	 * @param phone 	 * @param nickname 昵称	 * @param realName 团员姓名	 * @param headpicUrl 头像的URL	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public GolfGroupDetail(BigInteger id,BigInteger tGolfGroupFId,String openid,Integer isAdmin,String phone,String nickname,String realName,String headpicUrl,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGolfGroupFId = tGolfGroupFId;		this.openid = openid;		this.isAdmin = isAdmin;		this.phone = phone;		this.nickname = nickname;		this.realName = realName;		this.headpicUrl = headpicUrl;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGolfGroupFId=").append(tGolfGroupFId).append(";");		sbf.append("openid=").append(openid).append(";");		sbf.append("isAdmin=").append(isAdmin).append(";");		sbf.append("phone=").append(phone).append(";");		sbf.append("nickname=").append(nickname).append(";");		sbf.append("realName=").append(realName).append(";");		sbf.append("headpicUrl=").append(headpicUrl).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGolfGroupFId() {		return tGolfGroupFId;	}	public void settGolfGroupFId(BigInteger tGolfGroupFId) {		this.tGolfGroupFId = tGolfGroupFId;	}	public String getOpenid() {		return openid;	}	public void setOpenid(String openid) {		this.openid = openid;	}	public Integer getIsAdmin() {		return isAdmin;	}	public void setIsAdmin(Integer isAdmin) {		this.isAdmin = isAdmin;	}	public String getPhone() {		return phone;	}	public void setPhone(String phone) {		this.phone = phone;	}	public String getNickname() {		return nickname;	}	public void setNickname(String nickname) {		this.nickname = nickname;	}	public String getRealName() {		return realName;	}	public void setRealName(String realName) {		this.realName = realName;	}	public String getHeadpicUrl() {		return headpicUrl;	}	public void setHeadpicUrl(String headpicUrl) {		this.headpicUrl = headpicUrl;	}
	
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
		GolfGroupDetail other = (GolfGroupDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
