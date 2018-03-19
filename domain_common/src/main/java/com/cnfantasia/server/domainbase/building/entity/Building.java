package com.cnfantasia.server.domainbase.building.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(建筑单元) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class Building implements Serializable{
*/


public class Building extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属小区 */	private BigInteger tGroupBuildingFId;	/** 楼栋名称 */	private String name;	/** 楼栋名称(数字部分) */	private String nameDigitOrder;	/** 楼栋名称(字符部分) */	private String nameCharOrder;	/** 审核状态 */	private Integer checkStatus;	/** 临时待审核小区Id */	private BigInteger tGroupBuildingFIdTmp;	/**  */	private String code;	/** 创建人 */	private BigInteger creater;	/** 数据来源类型 */	private Integer sourceType;	/** 门禁状态 */	private Integer keyStatus;
	public Building(){
	}
	public Building(Building arg){
		this.id = arg.getId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.name = arg.getName();		this.nameDigitOrder = arg.getNameDigitOrder();		this.nameCharOrder = arg.getNameCharOrder();		this.checkStatus = arg.getCheckStatus();		this.tGroupBuildingFIdTmp = arg.gettGroupBuildingFIdTmp();		this.code = arg.getCode();		this.creater = arg.getCreater();		this.sourceType = arg.getSourceType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.keyStatus = arg.getKeyStatus();
	}
	/**	 * 	 * @param id 	 * @param tGroupBuildingFId 所属小区	 * @param name 楼栋名称	 * @param nameDigitOrder 楼栋名称(数字部分)	 * @param nameCharOrder 楼栋名称(字符部分)	 * @param checkStatus 审核状态	 * @param tGroupBuildingFIdTmp 临时待审核小区Id	 * @param code 	 * @param creater 创建人	 * @param sourceType 数据来源类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param keyStatus 门禁状态	 */
	public Building(BigInteger id,BigInteger tGroupBuildingFId,String name,String nameDigitOrder,String nameCharOrder,Integer checkStatus,BigInteger tGroupBuildingFIdTmp,String code,BigInteger creater,Integer sourceType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer keyStatus){
		this.id = id;		this.tGroupBuildingFId = tGroupBuildingFId;		this.name = name;		this.nameDigitOrder = nameDigitOrder;		this.nameCharOrder = nameCharOrder;		this.checkStatus = checkStatus;		this.tGroupBuildingFIdTmp = tGroupBuildingFIdTmp;		this.code = code;		this.creater = creater;		this.sourceType = sourceType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.keyStatus = keyStatus;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("nameDigitOrder=").append(nameDigitOrder).append(";");		sbf.append("nameCharOrder=").append(nameCharOrder).append(";");		sbf.append("checkStatus=").append(checkStatus).append(";");		sbf.append("tGroupBuildingFIdTmp=").append(tGroupBuildingFIdTmp).append(";");		sbf.append("code=").append(code).append(";");		sbf.append("creater=").append(creater).append(";");		sbf.append("sourceType=").append(sourceType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("keyStatus=").append(keyStatus).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getNameDigitOrder() {		return nameDigitOrder;	}	public void setNameDigitOrder(String nameDigitOrder) {		this.nameDigitOrder = nameDigitOrder;	}	public String getNameCharOrder() {		return nameCharOrder;	}	public void setNameCharOrder(String nameCharOrder) {		this.nameCharOrder = nameCharOrder;	}	public Integer getCheckStatus() {		return checkStatus;	}	public void setCheckStatus(Integer checkStatus) {		this.checkStatus = checkStatus;	}	public BigInteger gettGroupBuildingFIdTmp() {		return tGroupBuildingFIdTmp;	}	public void settGroupBuildingFIdTmp(BigInteger tGroupBuildingFIdTmp) {		this.tGroupBuildingFIdTmp = tGroupBuildingFIdTmp;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public BigInteger getCreater() {		return creater;	}	public void setCreater(BigInteger creater) {		this.creater = creater;	}	public Integer getSourceType() {		return sourceType;	}	public void setSourceType(Integer sourceType) {		this.sourceType = sourceType;	}	public Integer getKeyStatus() {		return keyStatus;	}	public void setKeyStatus(Integer keyStatus) {		this.keyStatus = keyStatus;	}
	
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
		Building other = (Building) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
