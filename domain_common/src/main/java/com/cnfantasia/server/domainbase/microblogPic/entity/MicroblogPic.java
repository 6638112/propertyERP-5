package com.cnfantasia.server.domainbase.microblogPic.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微博图片信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MicroblogPic implements Serializable{
*/


public class MicroblogPic extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 图片地址 */	private String url;	/** 图片名称 */	private String name;	/** 图片描述 */	private String desc;	/** 图片上传者 */	private BigInteger uploader;	/** 所属微博 */	private BigInteger tMicroblogContentFId;
	public MicroblogPic(){
	}
	public MicroblogPic(MicroblogPic arg){
		this.id = arg.getId();		this.url = arg.getUrl();		this.name = arg.getName();		this.desc = arg.getDesc();		this.uploader = arg.getUploader();		this.tMicroblogContentFId = arg.gettMicroblogContentFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param url 图片地址	 * @param name 图片名称	 * @param desc 图片描述	 * @param uploader 图片上传者	 * @param tMicroblogContentFId 所属微博	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MicroblogPic(BigInteger id,String url,String name,String desc,BigInteger uploader,BigInteger tMicroblogContentFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.url = url;		this.name = name;		this.desc = desc;		this.uploader = uploader;		this.tMicroblogContentFId = tMicroblogContentFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("url=").append(url).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("uploader=").append(uploader).append(";");		sbf.append("tMicroblogContentFId=").append(tMicroblogContentFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUrl() {		return url;	}	public void setUrl(String url) {		this.url = url;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public BigInteger getUploader() {		return uploader;	}	public void setUploader(BigInteger uploader) {		this.uploader = uploader;	}	public BigInteger gettMicroblogContentFId() {		return tMicroblogContentFId;	}	public void settMicroblogContentFId(BigInteger tMicroblogContentFId) {		this.tMicroblogContentFId = tMicroblogContentFId;	}
	
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
		MicroblogPic other = (MicroblogPic) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
