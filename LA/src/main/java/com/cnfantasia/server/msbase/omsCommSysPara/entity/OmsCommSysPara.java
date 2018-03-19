package com.cnfantasia.server.msbase.omsCommSysPara.entity;

import java.io.Serializable;
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
/**
 * 描述:(OMS系统参数表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class OmsCommSysPara implements Serializable{
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 参数码 */	private String sysParaCode;	/** 参数值 */	private String sysParaValue;	/** 参数描述 */	private String sysParaDesc;	/** 新增时间 */	private String sys0AddTime;	/** 更新时间 */	private String sys0UpdTime;	/** 删除时间 */	private String sys0DelTime;	/** 新增者 */	private BigInteger sys0AddUser;	/** 修改者 */	private BigInteger sys0UpdUser;	/** 删除者 */	private BigInteger sys0DelUser;	/** 记录状态 */	private Integer sys0DelState;
	public OmsCommSysPara(){
	}
	/**	 * 	 * @param id 	 * @param sysParaCode 参数码	 * @param sysParaValue 参数值	 * @param sysParaDesc 参数描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OmsCommSysPara(BigInteger id,String sysParaCode,String sysParaValue,String sysParaDesc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.sysParaCode = sysParaCode;		this.sysParaValue = sysParaValue;		this.sysParaDesc = sysParaDesc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("sysParaCode=").append(sysParaCode).append(";");		sbf.append("sysParaValue=").append(sysParaValue).append(";");		sbf.append("sysParaDesc=").append(sysParaDesc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getSysParaCode() {		return sysParaCode;	}	public void setSysParaCode(String sysParaCode) {		this.sysParaCode = sysParaCode;	}	public String getSysParaValue() {		return sysParaValue;	}	public void setSysParaValue(String sysParaValue) {		this.sysParaValue = sysParaValue;	}	public String getSysParaDesc() {		return sysParaDesc;	}	public void setSysParaDesc(String sysParaDesc) {		this.sysParaDesc = sysParaDesc;	}	public String getSys0AddTime() {		return sys0AddTime;	}	public void setSys0AddTime(String sys0AddTime) {		this.sys0AddTime = sys0AddTime;	}	public String getSys0UpdTime() {		return sys0UpdTime;	}	public void setSys0UpdTime(String sys0UpdTime) {		this.sys0UpdTime = sys0UpdTime;	}	public String getSys0DelTime() {		return sys0DelTime;	}	public void setSys0DelTime(String sys0DelTime) {		this.sys0DelTime = sys0DelTime;	}	public BigInteger getSys0AddUser() {		return sys0AddUser;	}	public void setSys0AddUser(BigInteger sys0AddUser) {		this.sys0AddUser = sys0AddUser;	}	public BigInteger getSys0UpdUser() {		return sys0UpdUser;	}	public void setSys0UpdUser(BigInteger sys0UpdUser) {		this.sys0UpdUser = sys0UpdUser;	}	public BigInteger getSys0DelUser() {		return sys0DelUser;	}	public void setSys0DelUser(BigInteger sys0DelUser) {		this.sys0DelUser = sys0DelUser;	}	public Integer getSys0DelState() {		return sys0DelState;	}	public void setSys0DelState(Integer sys0DelState) {		this.sys0DelState = sys0DelState;	}
	
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
		OmsCommSysPara other = (OmsCommSysPara) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
