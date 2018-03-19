package com.cnfantasia.server.domainbase.communityPinyipinPic.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(拼一拼图片列表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityPinyipinPic implements Serializable{
*/


public class CommunityPinyipinPic extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tCommunityPinyipinContentFId;	/** 名称 */	private String name;	/** 描述 */	private String desc;	/** 图片地址 */	private String picUrl;	/** 图片上传者 */	private BigInteger uploader;
	public CommunityPinyipinPic(){
	}
	public CommunityPinyipinPic(CommunityPinyipinPic arg){
		this.id = arg.getId();		this.tCommunityPinyipinContentFId = arg.gettCommunityPinyipinContentFId();		this.name = arg.getName();		this.desc = arg.getDesc();		this.picUrl = arg.getPicUrl();		this.uploader = arg.getUploader();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tCommunityPinyipinContentFId 	 * @param name 名称	 * @param desc 描述	 * @param picUrl 图片地址	 * @param uploader 图片上传者	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityPinyipinPic(BigInteger id,BigInteger tCommunityPinyipinContentFId,String name,String desc,String picUrl,BigInteger uploader,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tCommunityPinyipinContentFId = tCommunityPinyipinContentFId;		this.name = name;		this.desc = desc;		this.picUrl = picUrl;		this.uploader = uploader;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tCommunityPinyipinContentFId=").append(tCommunityPinyipinContentFId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("uploader=").append(uploader).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettCommunityPinyipinContentFId() {		return tCommunityPinyipinContentFId;	}	public void settCommunityPinyipinContentFId(BigInteger tCommunityPinyipinContentFId) {		this.tCommunityPinyipinContentFId = tCommunityPinyipinContentFId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public BigInteger getUploader() {		return uploader;	}	public void setUploader(BigInteger uploader) {		this.uploader = uploader;	}
	
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
		CommunityPinyipinPic other = (CommunityPinyipinPic) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
