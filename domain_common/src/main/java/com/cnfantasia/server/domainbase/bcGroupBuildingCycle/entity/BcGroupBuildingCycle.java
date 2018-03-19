package com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(托收账期记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BcGroupBuildingCycle implements Serializable{
*/


public class BcGroupBuildingCycle extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 明细出盘文件URL */	private String detailFileUrl;	/** sum出盘文件URL */	private String sumFileUrl;	/**  */	private BigInteger tPropertyCompanyBankCollectionInfoFId;	/** 小区名称（冗余）,用,分隔 */	private String gbNames;	/** 账单开始日期（冗余） */	private String billMonthStart;	/** 账单截止日期（冗余） */	private String billMonthEnd;	/** 账单名称（冗余） */	private String payBillTypeName;	/** 确认回盘时间 */	private String confirmTime;	/** 回盘状态={0未回盘,1已回盘} */	private Integer rebackStatus;	/**  */	private BigInteger tBankCollectionDateFId;
	public BcGroupBuildingCycle(){
	}
	public BcGroupBuildingCycle(BcGroupBuildingCycle arg){
		this.id = arg.getId();		this.detailFileUrl = arg.getDetailFileUrl();		this.sumFileUrl = arg.getSumFileUrl();		this.tPropertyCompanyBankCollectionInfoFId = arg.gettPropertyCompanyBankCollectionInfoFId();		this.gbNames = arg.getGbNames();		this.billMonthStart = arg.getBillMonthStart();		this.billMonthEnd = arg.getBillMonthEnd();		this.payBillTypeName = arg.getPayBillTypeName();		this.confirmTime = arg.getConfirmTime();		this.rebackStatus = arg.getRebackStatus();		this.tBankCollectionDateFId = arg.gettBankCollectionDateFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param detailFileUrl 明细出盘文件URL	 * @param sumFileUrl sum出盘文件URL	 * @param tPropertyCompanyBankCollectionInfoFId 	 * @param gbNames 小区名称（冗余）,用,分隔	 * @param billMonthStart 账单开始日期（冗余）	 * @param billMonthEnd 账单截止日期（冗余）	 * @param payBillTypeName 账单名称（冗余）	 * @param confirmTime 确认回盘时间	 * @param rebackStatus 回盘状态={0未回盘,1已回盘}	 * @param tBankCollectionDateFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public BcGroupBuildingCycle(BigInteger id,String detailFileUrl,String sumFileUrl,BigInteger tPropertyCompanyBankCollectionInfoFId,String gbNames,String billMonthStart,String billMonthEnd,String payBillTypeName,String confirmTime,Integer rebackStatus,BigInteger tBankCollectionDateFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.detailFileUrl = detailFileUrl;		this.sumFileUrl = sumFileUrl;		this.tPropertyCompanyBankCollectionInfoFId = tPropertyCompanyBankCollectionInfoFId;		this.gbNames = gbNames;		this.billMonthStart = billMonthStart;		this.billMonthEnd = billMonthEnd;		this.payBillTypeName = payBillTypeName;		this.confirmTime = confirmTime;		this.rebackStatus = rebackStatus;		this.tBankCollectionDateFId = tBankCollectionDateFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("detailFileUrl=").append(detailFileUrl).append(";");		sbf.append("sumFileUrl=").append(sumFileUrl).append(";");		sbf.append("tPropertyCompanyBankCollectionInfoFId=").append(tPropertyCompanyBankCollectionInfoFId).append(";");		sbf.append("gbNames=").append(gbNames).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("billMonthEnd=").append(billMonthEnd).append(";");		sbf.append("payBillTypeName=").append(payBillTypeName).append(";");		sbf.append("confirmTime=").append(confirmTime).append(";");		sbf.append("rebackStatus=").append(rebackStatus).append(";");		sbf.append("tBankCollectionDateFId=").append(tBankCollectionDateFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getDetailFileUrl() {		return detailFileUrl;	}	public void setDetailFileUrl(String detailFileUrl) {		this.detailFileUrl = detailFileUrl;	}	public String getSumFileUrl() {		return sumFileUrl;	}	public void setSumFileUrl(String sumFileUrl) {		this.sumFileUrl = sumFileUrl;	}	public BigInteger gettPropertyCompanyBankCollectionInfoFId() {		return tPropertyCompanyBankCollectionInfoFId;	}	public void settPropertyCompanyBankCollectionInfoFId(BigInteger tPropertyCompanyBankCollectionInfoFId) {		this.tPropertyCompanyBankCollectionInfoFId = tPropertyCompanyBankCollectionInfoFId;	}	public String getGbNames() {		return gbNames;	}	public void setGbNames(String gbNames) {		this.gbNames = gbNames;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getBillMonthEnd() {		return billMonthEnd;	}	public void setBillMonthEnd(String billMonthEnd) {		this.billMonthEnd = billMonthEnd;	}	public String getPayBillTypeName() {		return payBillTypeName;	}	public void setPayBillTypeName(String payBillTypeName) {		this.payBillTypeName = payBillTypeName;	}	public String getConfirmTime() {		return confirmTime;	}	public void setConfirmTime(String confirmTime) {		this.confirmTime = confirmTime;	}	public Integer getRebackStatus() {		return rebackStatus;	}	public void setRebackStatus(Integer rebackStatus) {		this.rebackStatus = rebackStatus;	}	public BigInteger gettBankCollectionDateFId() {		return tBankCollectionDateFId;	}	public void settBankCollectionDateFId(BigInteger tBankCollectionDateFId) {		this.tBankCollectionDateFId = tBankCollectionDateFId;	}
	
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
		BcGroupBuildingCycle other = (BcGroupBuildingCycle) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
