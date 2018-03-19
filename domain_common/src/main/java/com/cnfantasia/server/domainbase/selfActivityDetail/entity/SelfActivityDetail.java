package com.cnfantasia.server.domainbase.selfActivityDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(自定义活动详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class SelfActivityDetail implements Serializable{
*/


public class SelfActivityDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tSelfActivityFId;	/** 类型 */	private Integer jumpType;	/** 跳转参数,如商品id、h5链接地址 */	private String jumpParam;	/** 图片地址 */	private String picUrl;
	public SelfActivityDetail(){
	}
	public SelfActivityDetail(SelfActivityDetail arg){
		this.id = arg.getId();		this.tSelfActivityFId = arg.gettSelfActivityFId();		this.jumpType = arg.getJumpType();		this.jumpParam = arg.getJumpParam();		this.picUrl = arg.getPicUrl();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tSelfActivityFId 	 * @param jumpType 类型	 * @param jumpParam 跳转参数,如商品id、h5链接地址	 * @param picUrl 图片地址	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public SelfActivityDetail(BigInteger id,BigInteger tSelfActivityFId,Integer jumpType,String jumpParam,String picUrl,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tSelfActivityFId = tSelfActivityFId;		this.jumpType = jumpType;		this.jumpParam = jumpParam;		this.picUrl = picUrl;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tSelfActivityFId=").append(tSelfActivityFId).append(";");		sbf.append("jumpType=").append(jumpType).append(";");		sbf.append("jumpParam=").append(jumpParam).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettSelfActivityFId() {		return tSelfActivityFId;	}	public void settSelfActivityFId(BigInteger tSelfActivityFId) {		this.tSelfActivityFId = tSelfActivityFId;	}	public Integer getJumpType() {		return jumpType;	}	public void setJumpType(Integer jumpType) {		this.jumpType = jumpType;	}	public String getJumpParam() {		return jumpParam;	}	public void setJumpParam(String jumpParam) {		this.jumpParam = jumpParam;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}
	
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
		SelfActivityDetail other = (SelfActivityDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
