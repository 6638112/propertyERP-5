package com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业公司托收银行信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCompanyBankCollectionInfo implements Serializable{
*/


public class PropertyCompanyBankCollectionInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 物业公司ID */	private BigInteger tPropertyCompanyFId;	/** 托收范围={1 */	private Integer collectionRange;	/** 编号 */	private String no;	/** 协议号 */	private String contractNo;	/** 托收金融机构id */	private BigInteger tBankCollectionFinanceOrgFId;	/** 托收银行（金融机构）名称（冗余） */	private String bankName;	/** 银行账号 */	private String bankAccount;
	public PropertyCompanyBankCollectionInfo(){
	}
	public PropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo arg){
		this.id = arg.getId();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.collectionRange = arg.getCollectionRange();		this.no = arg.getNo();		this.contractNo = arg.getContractNo();		this.tBankCollectionFinanceOrgFId = arg.gettBankCollectionFinanceOrgFId();		this.bankName = arg.getBankName();		this.bankAccount = arg.getBankAccount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tPropertyCompanyFId 物业公司ID	 * @param collectionRange 托收范围={1	 * @param no 编号	 * @param contractNo 协议号	 * @param tBankCollectionFinanceOrgFId 托收金融机构id	 * @param bankName 托收银行（金融机构）名称（冗余）	 * @param bankAccount 银行账号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyCompanyBankCollectionInfo(BigInteger id,BigInteger tPropertyCompanyFId,Integer collectionRange,String no,String contractNo,BigInteger tBankCollectionFinanceOrgFId,String bankName,String bankAccount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.collectionRange = collectionRange;		this.no = no;		this.contractNo = contractNo;		this.tBankCollectionFinanceOrgFId = tBankCollectionFinanceOrgFId;		this.bankName = bankName;		this.bankAccount = bankAccount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("collectionRange=").append(collectionRange).append(";");		sbf.append("no=").append(no).append(";");		sbf.append("contractNo=").append(contractNo).append(";");		sbf.append("tBankCollectionFinanceOrgFId=").append(tBankCollectionFinanceOrgFId).append(";");		sbf.append("bankName=").append(bankName).append(";");		sbf.append("bankAccount=").append(bankAccount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}	public Integer getCollectionRange() {		return collectionRange;	}	public void setCollectionRange(Integer collectionRange) {		this.collectionRange = collectionRange;	}	public String getNo() {		return no;	}	public void setNo(String no) {		this.no = no;	}	public String getContractNo() {		return contractNo;	}	public void setContractNo(String contractNo) {		this.contractNo = contractNo;	}	public BigInteger gettBankCollectionFinanceOrgFId() {		return tBankCollectionFinanceOrgFId;	}	public void settBankCollectionFinanceOrgFId(BigInteger tBankCollectionFinanceOrgFId) {		this.tBankCollectionFinanceOrgFId = tBankCollectionFinanceOrgFId;	}	public String getBankName() {		return bankName;	}	public void setBankName(String bankName) {		this.bankName = bankName;	}	public String getBankAccount() {		return bankAccount;	}	public void setBankAccount(String bankAccount) {		this.bankAccount = bankAccount;	}
	
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
		PropertyCompanyBankCollectionInfo other = (PropertyCompanyBankCollectionInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
