package com.cnfantasia.server.domainbase.channelPartner.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(渠道合伙人) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ChannelPartner implements Serializable{
*/


public class ChannelPartner extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 姓名 */	private String name;	/** 职位描述 */	private String positiondesc;	/** 公司名称 */	private String companyName;	/** 手机号 */	private String phone;	/** 邀请码 */	private String inviteCode;	/**  */	private String email;	/** 开户行 */	private String bankName;	/** 账户名称 */	private String bankAccountName;	/** 银行卡号 */	private String bankCardNo;	/** 开卡支行 */	private String bankBranch;	/** 支行所在省 */	private String bankProvince;	/** 支行所在市 */	private String bankCity;	/** 补贴结算日 */	private Integer revenueDate;	/** 身份证号 */	private String idNumber;	/** 代理类型 */	private String partnertype;	/**  */	private BigInteger tOmsUserFId;	/** 营业执照 */	private String businessLicense;
	public ChannelPartner(){
	}
	public ChannelPartner(ChannelPartner arg){
		this.id = arg.getId();		this.name = arg.getName();		this.positiondesc = arg.getPositiondesc();		this.companyName = arg.getCompanyName();		this.phone = arg.getPhone();		this.inviteCode = arg.getInviteCode();		this.email = arg.getEmail();		this.bankName = arg.getBankName();		this.bankAccountName = arg.getBankAccountName();		this.bankCardNo = arg.getBankCardNo();		this.bankBranch = arg.getBankBranch();		this.bankProvince = arg.getBankProvince();		this.bankCity = arg.getBankCity();		this.revenueDate = arg.getRevenueDate();		this.idNumber = arg.getIdNumber();		this.partnertype = arg.getPartnertype();		this.tOmsUserFId = arg.gettOmsUserFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.businessLicense = arg.getBusinessLicense();
	}
	/**	 * 	 * @param id 	 * @param name 姓名	 * @param positiondesc 职位描述	 * @param companyName 公司名称	 * @param phone 手机号	 * @param inviteCode 邀请码	 * @param email 	 * @param bankName 开户行	 * @param bankAccountName 账户名称	 * @param bankCardNo 银行卡号	 * @param bankBranch 开卡支行	 * @param bankProvince 支行所在省	 * @param bankCity 支行所在市	 * @param revenueDate 补贴结算日	 * @param idNumber 身份证号	 * @param partnertype 代理类型	 * @param tOmsUserFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param businessLicense 营业执照	 */
	public ChannelPartner(BigInteger id,String name,String positiondesc,String companyName,String phone,String inviteCode,String email,String bankName,String bankAccountName,String bankCardNo,String bankBranch,String bankProvince,String bankCity,Integer revenueDate,String idNumber,String partnertype,BigInteger tOmsUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String businessLicense){
		this.id = id;		this.name = name;		this.positiondesc = positiondesc;		this.companyName = companyName;		this.phone = phone;		this.inviteCode = inviteCode;		this.email = email;		this.bankName = bankName;		this.bankAccountName = bankAccountName;		this.bankCardNo = bankCardNo;		this.bankBranch = bankBranch;		this.bankProvince = bankProvince;		this.bankCity = bankCity;		this.revenueDate = revenueDate;		this.idNumber = idNumber;		this.partnertype = partnertype;		this.tOmsUserFId = tOmsUserFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.businessLicense = businessLicense;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("positiondesc=").append(positiondesc).append(";");		sbf.append("companyName=").append(companyName).append(";");		sbf.append("phone=").append(phone).append(";");		sbf.append("inviteCode=").append(inviteCode).append(";");		sbf.append("email=").append(email).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankAccountName=").append(bankAccountName).append(";");		sbf.append("bankCardNo=").append(bankCardNo).append(";");		sbf.append("bankBranch=").append(bankBranch).append(";");		sbf.append("bankProvince=").append(bankProvince).append(";");		sbf.append("bankCity=").append(bankCity).append(";");		sbf.append("revenueDate=").append(revenueDate).append(";");		sbf.append("idNumber=").append(idNumber).append(";");		sbf.append("partnertype=").append(partnertype).append(";");		sbf.append("tOmsUserFId=").append(tOmsUserFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("businessLicense=").append(businessLicense).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPositiondesc() {		return positiondesc;	}	public void setPositiondesc(String positiondesc) {		this.positiondesc = positiondesc;	}	public String getCompanyName() {		return companyName;	}	public void setCompanyName(String companyName) {		this.companyName = companyName;	}	public String getPhone() {		return phone;	}	public void setPhone(String phone) {		this.phone = phone;	}	public String getInviteCode() {		return inviteCode;	}	public void setInviteCode(String inviteCode) {		this.inviteCode = inviteCode;	}	public String getEmail() {		return email;	}	public void setEmail(String email) {		this.email = email;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankAccountName() {		return bankAccountName;	}	public void setBankAccountName(String bankAccountName) {		this.bankAccountName = bankAccountName;	}	public String getBankCardNo() {		return bankCardNo;	}	public void setBankCardNo(String bankCardNo) {		this.bankCardNo = bankCardNo;	}	public String getBankBranch() {		return bankBranch;	}	public void setBankBranch(String bankBranch) {		this.bankBranch = bankBranch;	}	public String getBankProvince() {		return bankProvince;	}	public void setBankProvince(String bankProvince) {		this.bankProvince = bankProvince;	}	public String getBankCity() {		return bankCity;	}	public void setBankCity(String bankCity) {		this.bankCity = bankCity;	}	public Integer getRevenueDate() {		return revenueDate;	}	public void setRevenueDate(Integer revenueDate) {		this.revenueDate = revenueDate;	}	public String getIdNumber() {		return idNumber;	}	public void setIdNumber(String idNumber) {		this.idNumber = idNumber;	}	public String getPartnertype() {		return partnertype;	}	public void setPartnertype(String partnertype) {		this.partnertype = partnertype;	}	public BigInteger gettOmsUserFId() {		return tOmsUserFId;	}	public void settOmsUserFId(BigInteger tOmsUserFId) {		this.tOmsUserFId = tOmsUserFId;	}	public String getBusinessLicense() {		return businessLicense;	}	public void setBusinessLicense(String businessLicense) {		this.businessLicense = businessLicense;	}
	
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
		ChannelPartner other = (ChannelPartner) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
