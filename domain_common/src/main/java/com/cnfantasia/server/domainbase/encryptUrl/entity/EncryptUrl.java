package com.cnfantasia.server.domainbase.encryptUrl.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(加密url) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EncryptUrl implements Serializable{
*/


public class EncryptUrl extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 加密的url */	private String url;	/** 是否校验全部字段 */	private Integer allColumn;	/** 开始版本 */	private Long ver;	/** 最大版本 */	private Long verEnd;	/** 加密行为 */	private Integer signType;
	public EncryptUrl(){
	}
	public EncryptUrl(EncryptUrl arg){
		this.id = arg.getId();		this.url = arg.getUrl();		this.allColumn = arg.getAllColumn();		this.ver = arg.getVer();		this.verEnd = arg.getVerEnd();		this.signType = arg.getSignType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param url 加密的url	 * @param allColumn 是否校验全部字段	 * @param ver 开始版本	 * @param verEnd 最大版本	 * @param signType 加密行为	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EncryptUrl(BigInteger id,String url,Integer allColumn,Long ver,Long verEnd,Integer signType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.url = url;		this.allColumn = allColumn;		this.ver = ver;		this.verEnd = verEnd;		this.signType = signType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("url=").append(url).append(";");		sbf.append("allColumn=").append(allColumn).append(";");		sbf.append("ver=").append(ver).append(";");		sbf.append("verEnd=").append(verEnd).append(";");		sbf.append("signType=").append(signType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUrl() {		return url;	}	public void setUrl(String url) {		this.url = url;	}	public Integer getAllColumn() {		return allColumn;	}	public void setAllColumn(Integer allColumn) {		this.allColumn = allColumn;	}	public Long getVer() {		return ver;	}	public void setVer(Long ver) {		this.ver = ver;	}	public Long getVerEnd() {		return verEnd;	}	public void setVerEnd(Long verEnd) {		this.verEnd = verEnd;	}	public Integer getSignType() {		return signType;	}	public void setSignType(Integer signType) {		this.signType = signType;	}
	
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
		EncryptUrl other = (EncryptUrl) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
