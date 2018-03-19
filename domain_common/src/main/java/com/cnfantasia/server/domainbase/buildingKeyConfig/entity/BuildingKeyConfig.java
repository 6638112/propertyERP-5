package com.cnfantasia.server.domainbase.buildingKeyConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Integer;

import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(门禁认证选项显示配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BuildingKeyConfig implements Serializable{
*/


public class BuildingKeyConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 小区id */
	private BigInteger tGroupBuildingFId;
	/** 左侧名称 */
	private String label;
	/** 右侧id */
	private String inputId;
	/** 类型。如text、radio、img */
	private Integer inputType;
	/** 正则表达式 */
	private String inputPattern;
	/** 提示信息 */
	private String inputHint;
	/** radio的value与label，json数组格式。如[{value */
	private String inputEnum;
	/** radio的value与label，Map数组对象 */
	private List<Map> enumList;
	/** 默认值 */
	private String inputDefault;
	/** 顺序号 */
	private BigInteger order;
	/** 是否必填 */
	private Integer inputRequired;

	public BuildingKeyConfig(){
	}
	public BuildingKeyConfig(BuildingKeyConfig arg){
		this.id = arg.getId();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.label = arg.getLabel();
		this.inputId = arg.getInputId();
		this.inputType = arg.getInputType();
		this.inputPattern = arg.getInputPattern();
		this.inputHint = arg.getInputHint();
		this.inputEnum = arg.getInputEnum();
		this.inputDefault = arg.getInputDefault();
		this.order = arg.getOrder();
		this.inputRequired = arg.getInputRequired();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tGroupBuildingFId 小区id
	 * @param label 左侧名称
	 * @param inputId 右侧id
	 * @param inputType 类型。如text、radio、img
	 * @param inputPattern 正则表达式
	 * @param inputHint 提示信息
	 * @param inputEnum radio的value与label，json数组格式。如[{value
	 * @param inputDefault 默认值
	 * @param order 顺序号
	 * @param inputRequired 是否必填
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 修改时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public BuildingKeyConfig(BigInteger id,BigInteger tGroupBuildingFId,String label,String inputId,Integer inputType,String inputPattern,String inputHint,String inputEnum,String inputDefault,BigInteger order,Integer inputRequired,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.label = label;
		this.inputId = inputId;
		this.inputType = inputType;
		this.inputPattern = inputPattern;
		this.inputHint = inputHint;
		this.inputEnum = inputEnum;
		this.inputDefault = inputDefault;
		this.order = order;
		this.inputRequired = inputRequired;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("label=").append(label).append(";");
		sbf.append("inputId=").append(inputId).append(";");
		sbf.append("inputType=").append(inputType).append(";");
		sbf.append("inputPattern=").append(inputPattern).append(";");
		sbf.append("inputHint=").append(inputHint).append(";");
		sbf.append("inputEnum=").append(inputEnum).append(";");
		sbf.append("inputDefault=").append(inputDefault).append(";");
		sbf.append("order=").append(order).append(";");
		sbf.append("inputRequired=").append(inputRequired).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public List<Map> getEnumList() {
		return enumList;
	}
	public void setEnumList(List<Map> enumList) {
		this.enumList = enumList;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getInputId() {
		return inputId;
	}
	public void setInputId(String inputId) {
		this.inputId = inputId;
	}
	public Integer getInputType() {
		return inputType;
	}
	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
	public String getInputPattern() {
		return inputPattern;
	}
	public void setInputPattern(String inputPattern) {
		this.inputPattern = inputPattern;
	}
	public String getInputHint() {
		return inputHint;
	}
	public void setInputHint(String inputHint) {
		this.inputHint = inputHint;
	}
	public String getInputEnum() {
		return inputEnum;
	}
	public void setInputEnum(String inputEnum) {
		this.inputEnum = inputEnum;
	}
	public String getInputDefault() {
		return inputDefault;
	}
	public void setInputDefault(String inputDefault) {
		this.inputDefault = inputDefault;
	}
	public BigInteger getOrder() {
		return order;
	}
	public void setOrder(BigInteger order) {
		this.order = order;
	}
	public Integer getInputRequired() {
		return inputRequired;
	}
	public void setInputRequired(Integer inputRequired) {
		this.inputRequired = inputRequired;
	}

	
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
		BuildingKeyConfig other = (BuildingKeyConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
