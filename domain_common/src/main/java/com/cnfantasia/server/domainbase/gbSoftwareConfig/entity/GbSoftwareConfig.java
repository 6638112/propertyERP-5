package com.cnfantasia.server.domainbase.gbSoftwareConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小区物业软件配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GbSoftwareConfig implements Serializable{
*/


public class GbSoftwareConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区ID */	private BigInteger gbId;	/** 物业软件的小区ID */	private String softwareGbId;	/** 物业软件访问地址 */	private String ipAddress;	/** 哪个物业软件（1 */	private Integer softwareType;	/** 数据库代码（极致用） */	private String databaseCode;	/** rsa加密公钥 */	private String rsaPublicKey;	/** rsa加密私钥 */	private String rsaPrivateKey;	/** 实现了必要接口的class */	private String serviceClassName;	/** 不可缴费开始日期 */	private Integer cannotConnectStartDate;	/** 不可缴费结束日期 */	private Integer cannotConnectEndDate;	/** 配置是否有效 */	private Integer isValid;	/** 公共维修默认分配师傅ID */	private BigInteger defaultRepairerId;
	public GbSoftwareConfig(){
	}
	public GbSoftwareConfig(GbSoftwareConfig arg){
		this.id = arg.getId();		this.gbId = arg.getGbId();		this.softwareGbId = arg.getSoftwareGbId();		this.ipAddress = arg.getIpAddress();		this.softwareType = arg.getSoftwareType();		this.databaseCode = arg.getDatabaseCode();		this.rsaPublicKey = arg.getRsaPublicKey();		this.rsaPrivateKey = arg.getRsaPrivateKey();		this.serviceClassName = arg.getServiceClassName();		this.cannotConnectStartDate = arg.getCannotConnectStartDate();		this.cannotConnectEndDate = arg.getCannotConnectEndDate();		this.isValid = arg.getIsValid();		this.defaultRepairerId = arg.getDefaultRepairerId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param gbId 小区ID	 * @param softwareGbId 物业软件的小区ID	 * @param ipAddress 物业软件访问地址	 * @param softwareType 哪个物业软件（1	 * @param databaseCode 数据库代码（极致用）	 * @param rsaPublicKey rsa加密公钥	 * @param rsaPrivateKey rsa加密私钥	 * @param serviceClassName 实现了必要接口的class	 * @param cannotConnectStartDate 不可缴费开始日期	 * @param cannotConnectEndDate 不可缴费结束日期	 * @param isValid 配置是否有效	 * @param defaultRepairerId 公共维修默认分配师傅ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public GbSoftwareConfig(BigInteger id,BigInteger gbId,String softwareGbId,String ipAddress,Integer softwareType,String databaseCode,String rsaPublicKey,String rsaPrivateKey,String serviceClassName,Integer cannotConnectStartDate,Integer cannotConnectEndDate,Integer isValid,BigInteger defaultRepairerId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.gbId = gbId;		this.softwareGbId = softwareGbId;		this.ipAddress = ipAddress;		this.softwareType = softwareType;		this.databaseCode = databaseCode;		this.rsaPublicKey = rsaPublicKey;		this.rsaPrivateKey = rsaPrivateKey;		this.serviceClassName = serviceClassName;		this.cannotConnectStartDate = cannotConnectStartDate;		this.cannotConnectEndDate = cannotConnectEndDate;		this.isValid = isValid;		this.defaultRepairerId = defaultRepairerId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("gbId=").append(gbId).append(";");		sbf.append("softwareGbId=").append(softwareGbId).append(";");		sbf.append("ipAddress=").append(ipAddress).append(";");		sbf.append("softwareType=").append(softwareType).append(";");		sbf.append("databaseCode=").append(databaseCode).append(";");		sbf.append("rsaPublicKey=").append(rsaPublicKey).append(";");		sbf.append("rsaPrivateKey=").append(rsaPrivateKey).append(";");		sbf.append("serviceClassName=").append(serviceClassName).append(";");		sbf.append("cannotConnectStartDate=").append(cannotConnectStartDate).append(";");		sbf.append("cannotConnectEndDate=").append(cannotConnectEndDate).append(";");		sbf.append("isValid=").append(isValid).append(";");		sbf.append("defaultRepairerId=").append(defaultRepairerId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getGbId() {		return gbId;	}	public void setGbId(BigInteger gbId) {		this.gbId = gbId;	}	public String getSoftwareGbId() {		return softwareGbId;	}	public void setSoftwareGbId(String softwareGbId) {		this.softwareGbId = softwareGbId;	}	public String getIpAddress() {		return ipAddress;	}	public void setIpAddress(String ipAddress) {		this.ipAddress = ipAddress;	}	public Integer getSoftwareType() {		return softwareType;	}	public void setSoftwareType(Integer softwareType) {		this.softwareType = softwareType;	}	public String getDatabaseCode() {		return databaseCode;	}	public void setDatabaseCode(String databaseCode) {		this.databaseCode = databaseCode;	}	public String getRsaPublicKey() {		return rsaPublicKey;	}	public void setRsaPublicKey(String rsaPublicKey) {		this.rsaPublicKey = rsaPublicKey;	}	public String getRsaPrivateKey() {		return rsaPrivateKey;	}	public void setRsaPrivateKey(String rsaPrivateKey) {		this.rsaPrivateKey = rsaPrivateKey;	}	public String getServiceClassName() {		return serviceClassName;	}	public void setServiceClassName(String serviceClassName) {		this.serviceClassName = serviceClassName;	}	public Integer getCannotConnectStartDate() {		return cannotConnectStartDate;	}	public void setCannotConnectStartDate(Integer cannotConnectStartDate) {		this.cannotConnectStartDate = cannotConnectStartDate;	}	public Integer getCannotConnectEndDate() {		return cannotConnectEndDate;	}	public void setCannotConnectEndDate(Integer cannotConnectEndDate) {		this.cannotConnectEndDate = cannotConnectEndDate;	}	public Integer getIsValid() {		return isValid;	}	public void setIsValid(Integer isValid) {		this.isValid = isValid;	}	public BigInteger getDefaultRepairerId() {		return defaultRepairerId;	}	public void setDefaultRepairerId(BigInteger defaultRepairerId) {		this.defaultRepairerId = defaultRepairerId;	}
	
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
		GbSoftwareConfig other = (GbSoftwareConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
