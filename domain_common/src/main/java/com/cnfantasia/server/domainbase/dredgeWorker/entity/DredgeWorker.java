package com.cnfantasia.server.domainbase.dredgeWorker.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(疏通师傅) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeWorker implements Serializable{
*/


public class DredgeWorker extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 身份证号 */	private String idNo;	/** 真实姓名 */	private String realName;	/** 身份证正面 */	private String idNumberPic1;	/** 身份证反面 */	private String idNumberPic2;	/** 个人描述 */	private String personalDesc;	/** 记录状态 */	private Integer auditStatus;	/** 评价得分 */	private Double level;	/** 师傅等级 */	private Integer workerLevel;	/** 审核结果描述 */	private String auditDesc;	/** 师傅的解放区账号 */	private BigInteger tUserFId;	/** 创建方式 */	private Integer createType;	/** 师傅属于哪个服务商 */	private BigInteger tServiceSupplierFId;
	public DredgeWorker(){
	}
	public DredgeWorker(DredgeWorker arg){
		this.id = arg.getId();		this.idNo = arg.getIdNo();		this.realName = arg.getRealName();		this.idNumberPic1 = arg.getIdNumberPic1();		this.idNumberPic2 = arg.getIdNumberPic2();		this.personalDesc = arg.getPersonalDesc();		this.auditStatus = arg.getAuditStatus();		this.level = arg.getLevel();		this.workerLevel = arg.getWorkerLevel();		this.auditDesc = arg.getAuditDesc();		this.tUserFId = arg.gettUserFId();		this.createType = arg.getCreateType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tServiceSupplierFId = arg.gettServiceSupplierFId();
	}
	/**	 * 	 * @param id 	 * @param idNo 身份证号	 * @param realName 真实姓名	 * @param idNumberPic1 身份证正面	 * @param idNumberPic2 身份证反面	 * @param personalDesc 个人描述	 * @param auditStatus 记录状态	 * @param level 评价得分	 * @param workerLevel 师傅等级	 * @param auditDesc 审核结果描述	 * @param tUserFId 师傅的解放区账号	 * @param createType 创建方式	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tServiceSupplierFId 师傅属于哪个服务商	 */
	public DredgeWorker(BigInteger id,String idNo,String realName,String idNumberPic1,String idNumberPic2,String personalDesc,Integer auditStatus,Double level,Integer workerLevel,String auditDesc,BigInteger tUserFId,Integer createType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tServiceSupplierFId){
		this.id = id;		this.idNo = idNo;		this.realName = realName;		this.idNumberPic1 = idNumberPic1;		this.idNumberPic2 = idNumberPic2;		this.personalDesc = personalDesc;		this.auditStatus = auditStatus;		this.level = level;		this.workerLevel = workerLevel;		this.auditDesc = auditDesc;		this.tUserFId = tUserFId;		this.createType = createType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tServiceSupplierFId = tServiceSupplierFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("idNo=").append(idNo).append(";");		sbf.append("realName=").append(realName).append(";");		sbf.append("idNumberPic1=").append(idNumberPic1).append(";");		sbf.append("idNumberPic2=").append(idNumberPic2).append(";");		sbf.append("personalDesc=").append(personalDesc).append(";");		sbf.append("auditStatus=").append(auditStatus).append(";");		sbf.append("level=").append(level).append(";");		sbf.append("workerLevel=").append(workerLevel).append(";");		sbf.append("auditDesc=").append(auditDesc).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("createType=").append(createType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tServiceSupplierFId=").append(tServiceSupplierFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getIdNo() {		return idNo;	}	public void setIdNo(String idNo) {		this.idNo = idNo;	}	public String getRealName() {		return realName;	}	public void setRealName(String realName) {		this.realName = realName;	}	public String getIdNumberPic1() {		return idNumberPic1;	}	public void setIdNumberPic1(String idNumberPic1) {		this.idNumberPic1 = idNumberPic1;	}	public String getIdNumberPic2() {		return idNumberPic2;	}	public void setIdNumberPic2(String idNumberPic2) {		this.idNumberPic2 = idNumberPic2;	}	public String getPersonalDesc() {		return personalDesc;	}	public void setPersonalDesc(String personalDesc) {		this.personalDesc = personalDesc;	}	public Integer getAuditStatus() {		return auditStatus;	}	public void setAuditStatus(Integer auditStatus) {		this.auditStatus = auditStatus;	}	public Double getLevel() {		return level;	}	public void setLevel(Double level) {		this.level = level;	}	public Integer getWorkerLevel() {		return workerLevel;	}	public void setWorkerLevel(Integer workerLevel) {		this.workerLevel = workerLevel;	}	public String getAuditDesc() {		return auditDesc;	}	public void setAuditDesc(String auditDesc) {		this.auditDesc = auditDesc;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public Integer getCreateType() {		return createType;	}	public void setCreateType(Integer createType) {		this.createType = createType;	}	public BigInteger gettServiceSupplierFId() {		return tServiceSupplierFId;	}	public void settServiceSupplierFId(BigInteger tServiceSupplierFId) {		this.tServiceSupplierFId = tServiceSupplierFId;	}
	
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
		DredgeWorker other = (DredgeWorker) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
