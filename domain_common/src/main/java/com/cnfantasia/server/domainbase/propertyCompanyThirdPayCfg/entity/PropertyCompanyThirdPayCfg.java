package com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业公司/管理处/自有平台的支付宝信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCompanyThirdPayCfg implements Serializable{
*/


public class PropertyCompanyThirdPayCfg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 物业公司ID */	private BigInteger tPcId;	/** 管理处ID */	private BigInteger tPmId;	/** 支付开启还是关闭（1开启，2关闭） */	private Integer openStatus;	/** 商户账号 */	private String appid;	/** 商户公钥 */	private String publicKey;	/** 商户私钥 */	private String privateKey;	/** 支付宝公钥 */	private String aliPublicKey;
	public PropertyCompanyThirdPayCfg(){
	}
	public PropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg arg){
		this.id = arg.getId();		this.tPcId = arg.gettPcId();		this.tPmId = arg.gettPmId();		this.openStatus = arg.getOpenStatus();		this.appid = arg.getAppid();		this.publicKey = arg.getPublicKey();		this.privateKey = arg.getPrivateKey();		this.aliPublicKey = arg.getAliPublicKey();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tPcId 物业公司ID	 * @param tPmId 管理处ID	 * @param openStatus 支付开启还是关闭（1开启，2关闭）	 * @param appid 商户账号	 * @param publicKey 商户公钥	 * @param privateKey 商户私钥	 * @param aliPublicKey 支付宝公钥	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 修改时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyCompanyThirdPayCfg(BigInteger id,BigInteger tPcId,BigInteger tPmId,Integer openStatus,String appid,String publicKey,String privateKey,String aliPublicKey,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tPcId = tPcId;		this.tPmId = tPmId;		this.openStatus = openStatus;		this.appid = appid;		this.publicKey = publicKey;		this.privateKey = privateKey;		this.aliPublicKey = aliPublicKey;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tPcId=").append(tPcId).append(";");		sbf.append("tPmId=").append(tPmId).append(";");		sbf.append("openStatus=").append(openStatus).append(";");		sbf.append("appid=").append(appid).append(";");		sbf.append("publicKey=").append(publicKey).append(";");		sbf.append("privateKey=").append(privateKey).append(";");		sbf.append("aliPublicKey=").append(aliPublicKey).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettPcId() {		return tPcId;	}	public void settPcId(BigInteger tPcId) {		this.tPcId = tPcId;	}	public BigInteger gettPmId() {		return tPmId;	}	public void settPmId(BigInteger tPmId) {		this.tPmId = tPmId;	}	public Integer getOpenStatus() {		return openStatus;	}	public void setOpenStatus(Integer openStatus) {		this.openStatus = openStatus;	}	public String getAppid() {		return appid;	}	public void setAppid(String appid) {		this.appid = appid;	}	public String getPublicKey() {		return publicKey;	}	public void setPublicKey(String publicKey) {		this.publicKey = publicKey;	}	public String getPrivateKey() {		return privateKey;	}	public void setPrivateKey(String privateKey) {		this.privateKey = privateKey;	}	public String getAliPublicKey() {		return aliPublicKey;	}	public void setAliPublicKey(String aliPublicKey) {		this.aliPublicKey = aliPublicKey;	}
	
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
		PropertyCompanyThirdPayCfg other = (PropertyCompanyThirdPayCfg) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
