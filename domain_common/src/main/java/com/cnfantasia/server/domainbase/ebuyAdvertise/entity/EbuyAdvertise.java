package com.cnfantasia.server.domainbase.ebuyAdvertise.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyAdvertise implements Serializable{
*/


public class EbuyAdvertise extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 编码，标识是哪一个广告栏，电商用EBUY_AD */	private String code;	/**  */	private String tittle;	/**  */	private Integer type;	/**  */	private String picName;	/**  */	private String linkUrl;	/** 非H5时ios地址 */	private String iosAddr;	/** 非H5时android地址 */	private String androidAddr;	/** 非H5时IOS跳转地址参数 */	private String iosParam;	/** 非H5时android跳转地址参数 */	private String androidParam;	/** 版本控制中最小版本 */	private Long version;	/** 版本控制中最大版本 */	private Long maxVersion;	/**  */	private String startTime;	/**  */	private String endTime;	/**  */	private Long order;	/** 弹框广告弹出频率 */	private Integer frequency;	/** 特殊说明 */	private String desc;
	public EbuyAdvertise(){
	}
	public EbuyAdvertise(EbuyAdvertise arg){
		this.id = arg.getId();		this.code = arg.getCode();		this.tittle = arg.getTittle();		this.type = arg.getType();		this.picName = arg.getPicName();		this.linkUrl = arg.getLinkUrl();		this.iosAddr = arg.getIosAddr();		this.androidAddr = arg.getAndroidAddr();		this.iosParam = arg.getIosParam();		this.androidParam = arg.getAndroidParam();		this.version = arg.getVersion();		this.maxVersion = arg.getMaxVersion();		this.startTime = arg.getStartTime();		this.endTime = arg.getEndTime();		this.order = arg.getOrder();		this.frequency = arg.getFrequency();		this.desc = arg.getDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param code 编码，标识是哪一个广告栏，电商用EBUY_AD	 * @param tittle 	 * @param type 	 * @param picName 	 * @param linkUrl 	 * @param iosAddr 非H5时ios地址	 * @param androidAddr 非H5时android地址	 * @param iosParam 非H5时IOS跳转地址参数	 * @param androidParam 非H5时android跳转地址参数	 * @param version 版本控制中最小版本	 * @param maxVersion 版本控制中最大版本	 * @param startTime 	 * @param endTime 	 * @param order 	 * @param frequency 弹框广告弹出频率	 * @param desc 特殊说明	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public EbuyAdvertise(BigInteger id,String code,String tittle,Integer type,String picName,String linkUrl,String iosAddr,String androidAddr,String iosParam,String androidParam,Long version,Long maxVersion,String startTime,String endTime,Long order,Integer frequency,String desc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.code = code;		this.tittle = tittle;		this.type = type;		this.picName = picName;		this.linkUrl = linkUrl;		this.iosAddr = iosAddr;		this.androidAddr = androidAddr;		this.iosParam = iosParam;		this.androidParam = androidParam;		this.version = version;		this.maxVersion = maxVersion;		this.startTime = startTime;		this.endTime = endTime;		this.order = order;		this.frequency = frequency;		this.desc = desc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("code=").append(code).append(";");		sbf.append("tittle=").append(tittle).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("picName=").append(picName).append(";");		sbf.append("linkUrl=").append(linkUrl).append(";");		sbf.append("iosAddr=").append(iosAddr).append(";");		sbf.append("androidAddr=").append(androidAddr).append(";");		sbf.append("iosParam=").append(iosParam).append(";");		sbf.append("androidParam=").append(androidParam).append(";");		sbf.append("version=").append(version).append(";");		sbf.append("maxVersion=").append(maxVersion).append(";");		sbf.append("startTime=").append(startTime).append(";");		sbf.append("endTime=").append(endTime).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("frequency=").append(frequency).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public String getTittle() {		return tittle;	}	public void setTittle(String tittle) {		this.tittle = tittle;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getPicName() {		return picName;	}	public void setPicName(String picName) {		this.picName = picName;	}	public String getLinkUrl() {		return linkUrl;	}	public void setLinkUrl(String linkUrl) {		this.linkUrl = linkUrl;	}	public String getIosAddr() {		return iosAddr;	}	public void setIosAddr(String iosAddr) {		this.iosAddr = iosAddr;	}	public String getAndroidAddr() {		return androidAddr;	}	public void setAndroidAddr(String androidAddr) {		this.androidAddr = androidAddr;	}	public String getIosParam() {		return iosParam;	}	public void setIosParam(String iosParam) {		this.iosParam = iosParam;	}	public String getAndroidParam() {		return androidParam;	}	public void setAndroidParam(String androidParam) {		this.androidParam = androidParam;	}	public Long getVersion() {		return version;	}	public void setVersion(Long version) {		this.version = version;	}	public Long getMaxVersion() {		return maxVersion;	}	public void setMaxVersion(Long maxVersion) {		this.maxVersion = maxVersion;	}	public String getStartTime() {		return startTime;	}	public void setStartTime(String startTime) {		this.startTime = startTime;	}	public String getEndTime() {		return endTime;	}	public void setEndTime(String endTime) {		this.endTime = endTime;	}	public Long getOrder() {		return order;	}	public void setOrder(Long order) {		this.order = order;	}	public Integer getFrequency() {		return frequency;	}	public void setFrequency(Integer frequency) {		this.frequency = frequency;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}
	
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
		EbuyAdvertise other = (EbuyAdvertise) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
