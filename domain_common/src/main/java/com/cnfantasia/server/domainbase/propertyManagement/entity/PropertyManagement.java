package com.cnfantasia.server.domainbase.propertyManagement.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业管理) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyManagement implements Serializable{
*/


public class PropertyManagement extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 管理处电话 */	private String tel;	/** 名称 */	private String name;	/**  */	private BigInteger tPropertyCompanyFId;	/** 负责人姓名 */	private String personChargeName;	/** 负责人联系方式 */	private String personChargeTel;	/** 是否开启收款权限(0关闭，1开启) */	private Integer isOpenReceivables;	/** 临时存放 */	private Integer isOpenReveivablesEdit;	/** 银行卡号 */	private String accountNo;	/** 开户行 */	private String bankName;	/** 开户人姓名/账户名称 */	private String accountName;	/** 开户支行 */	private String bankBranch;	/** 支行所在省 */	private String bankProvince;	/** 支行所在市 */	private String bankCity;	/** 修改状态 */	private Integer isEdit;	/** 审核结果 */	private String editResult;	/** 自动结算日（每月几号） */	private Integer settlementDay;
	public PropertyManagement(){
	}
	public PropertyManagement(PropertyManagement arg){
		this.id = arg.getId();		this.tel = arg.getTel();		this.name = arg.getName();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.personChargeName = arg.getPersonChargeName();		this.personChargeTel = arg.getPersonChargeTel();		this.isOpenReceivables = arg.getIsOpenReceivables();		this.isOpenReveivablesEdit = arg.getIsOpenReveivablesEdit();		this.accountNo = arg.getAccountNo();		this.bankName = arg.getBankName();		this.accountName = arg.getAccountName();		this.bankBranch = arg.getBankBranch();		this.bankProvince = arg.getBankProvince();		this.bankCity = arg.getBankCity();		this.isEdit = arg.getIsEdit();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.editResult = arg.getEditResult();		this.settlementDay = arg.getSettlementDay();
	}
	/**	 * 	 * @param id 	 * @param tel 管理处电话	 * @param name 名称	 * @param tPropertyCompanyFId 	 * @param personChargeName 负责人姓名	 * @param personChargeTel 负责人联系方式	 * @param isOpenReceivables 是否开启收款权限(0关闭，1开启)	 * @param isOpenReveivablesEdit 临时存放	 * @param accountNo 银行卡号	 * @param bankName 开户行	 * @param accountName 开户人姓名/账户名称	 * @param bankBranch 开户支行	 * @param bankProvince 支行所在省	 * @param bankCity 支行所在市	 * @param isEdit 修改状态	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param editResult 审核结果	 * @param settlementDay 自动结算日（每月几号）	 */
	public PropertyManagement(BigInteger id,String tel,String name,BigInteger tPropertyCompanyFId,String personChargeName,String personChargeTel,Integer isOpenReceivables,Integer isOpenReveivablesEdit,String accountNo,String bankName,String accountName,String bankBranch,String bankProvince,String bankCity,Integer isEdit,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String editResult,Integer settlementDay){
		this.id = id;		this.tel = tel;		this.name = name;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.personChargeName = personChargeName;		this.personChargeTel = personChargeTel;		this.isOpenReceivables = isOpenReceivables;		this.isOpenReveivablesEdit = isOpenReveivablesEdit;		this.accountNo = accountNo;		this.bankName = bankName;		this.accountName = accountName;		this.bankBranch = bankBranch;		this.bankProvince = bankProvince;		this.bankCity = bankCity;		this.isEdit = isEdit;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.editResult = editResult;		this.settlementDay = settlementDay;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tel=").append(tel).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("personChargeName=").append(personChargeName).append(";");		sbf.append("personChargeTel=").append(personChargeTel).append(";");		sbf.append("isOpenReceivables=").append(isOpenReceivables).append(";");		sbf.append("isOpenReveivablesEdit=").append(isOpenReveivablesEdit).append(";");		sbf.append("accountNo=").append(accountNo).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("accountName=").append(accountName).append(";");		sbf.append("bankBranch=").append(bankBranch).append(";");		sbf.append("bankProvince=").append(bankProvince).append(";");		sbf.append("bankCity=").append(bankCity).append(";");		sbf.append("isEdit=").append(isEdit).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("editResult=").append(editResult).append(";");		sbf.append("settlementDay=").append(settlementDay).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTel() {		return tel;	}	public void setTel(String tel) {		this.tel = tel;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}	public String getPersonChargeName() {		return personChargeName;	}	public void setPersonChargeName(String personChargeName) {		this.personChargeName = personChargeName;	}	public String getPersonChargeTel() {		return personChargeTel;	}	public void setPersonChargeTel(String personChargeTel) {		this.personChargeTel = personChargeTel;	}	public Integer getIsOpenReceivables() {		return isOpenReceivables;	}	public void setIsOpenReceivables(Integer isOpenReceivables) {		this.isOpenReceivables = isOpenReceivables;	}	public Integer getIsOpenReveivablesEdit() {		return isOpenReveivablesEdit;	}	public void setIsOpenReveivablesEdit(Integer isOpenReveivablesEdit) {		this.isOpenReveivablesEdit = isOpenReveivablesEdit;	}	public String getAccountNo() {		return accountNo;	}	public void setAccountNo(String accountNo) {		this.accountNo = accountNo;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getAccountName() {		return accountName;	}	public void setAccountName(String accountName) {		this.accountName = accountName;	}	public String getBankBranch() {		return bankBranch;	}	public void setBankBranch(String bankBranch) {		this.bankBranch = bankBranch;	}	public String getBankProvince() {		return bankProvince;	}	public void setBankProvince(String bankProvince) {		this.bankProvince = bankProvince;	}	public String getBankCity() {		return bankCity;	}	public void setBankCity(String bankCity) {		this.bankCity = bankCity;	}	public Integer getIsEdit() {		return isEdit;	}	public void setIsEdit(Integer isEdit) {		this.isEdit = isEdit;	}	public String getEditResult() {		return editResult;	}	public void setEditResult(String editResult) {		this.editResult = editResult;	}	public Integer getSettlementDay() {		return settlementDay;	}	public void setSettlementDay(Integer settlementDay) {		this.settlementDay = settlementDay;	}
	
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
		PropertyManagement other = (PropertyManagement) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
