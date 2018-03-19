package com.cnfantasia.server.domainbase.ebuyPayRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(支付记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyPayRecord implements Serializable{
*/


public class EbuyPayRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderFId;	/** 付款状态={"1" */	private Integer payStatus;	/** 支付方式 */	private Integer payMethod;	/** 支付账号 */	private String payAccount;	/** 支付流水号 */	private String flowNo;	/** 订单编号 */	private String orderNo;	/** 支付金额 */	private Long payAmount;	/** 支付描述 */	private String payDesc;	/** 支付时间 */	private String payTime;	/** 支付结果信息 */	private String payResultInfo;	/** 支付失败描述信息 */	private String payErrInfo;	/** 创建人 */	private BigInteger creater;
	public EbuyPayRecord(){
	}
	public EbuyPayRecord(EbuyPayRecord arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.payStatus = arg.getPayStatus();		this.payMethod = arg.getPayMethod();		this.payAccount = arg.getPayAccount();		this.flowNo = arg.getFlowNo();		this.orderNo = arg.getOrderNo();		this.payAmount = arg.getPayAmount();		this.payDesc = arg.getPayDesc();		this.payTime = arg.getPayTime();		this.payResultInfo = arg.getPayResultInfo();		this.payErrInfo = arg.getPayErrInfo();		this.creater = arg.getCreater();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 	 * @param payStatus 付款状态={"1"	 * @param payMethod 支付方式	 * @param payAccount 支付账号	 * @param flowNo 支付流水号	 * @param orderNo 订单编号	 * @param payAmount 支付金额	 * @param payDesc 支付描述	 * @param payTime 支付时间	 * @param payResultInfo 支付结果信息	 * @param payErrInfo 支付失败描述信息	 * @param creater 创建人	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyPayRecord(BigInteger id,BigInteger tEbuyOrderFId,Integer payStatus,Integer payMethod,String payAccount,String flowNo,String orderNo,Long payAmount,String payDesc,String payTime,String payResultInfo,String payErrInfo,BigInteger creater,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.payStatus = payStatus;		this.payMethod = payMethod;		this.payAccount = payAccount;		this.flowNo = flowNo;		this.orderNo = orderNo;		this.payAmount = payAmount;		this.payDesc = payDesc;		this.payTime = payTime;		this.payResultInfo = payResultInfo;		this.payErrInfo = payErrInfo;		this.creater = creater;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("payStatus=").append(payStatus).append(";");		sbf.append("payMethod=").append(payMethod).append(";");		sbf.append("payAccount=").append(payAccount).append(";");		sbf.append("flowNo=").append(flowNo).append(";");		sbf.append("orderNo=").append(orderNo).append(";");		sbf.append("payAmount=").append(payAmount).append(";");		sbf.append("payDesc=").append(payDesc).append(";");		sbf.append("payTime=").append(payTime).append(";");		sbf.append("payResultInfo=").append(payResultInfo).append(";");		sbf.append("payErrInfo=").append(payErrInfo).append(";");		sbf.append("creater=").append(creater).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public Integer getPayStatus() {		return payStatus;	}	public void setPayStatus(Integer payStatus) {		this.payStatus = payStatus;	}	public Integer getPayMethod() {		return payMethod;	}	public void setPayMethod(Integer payMethod) {		this.payMethod = payMethod;	}	public String getPayAccount() {		return payAccount;	}	public void setPayAccount(String payAccount) {		this.payAccount = payAccount;	}	public String getFlowNo() {		return flowNo;	}	public void setFlowNo(String flowNo) {		this.flowNo = flowNo;	}	public String getOrderNo() {		return orderNo;	}	public void setOrderNo(String orderNo) {		this.orderNo = orderNo;	}	public Long getPayAmount() {		return payAmount;	}	public void setPayAmount(Long payAmount) {		this.payAmount = payAmount;	}	public String getPayDesc() {		return payDesc;	}	public void setPayDesc(String payDesc) {		this.payDesc = payDesc;	}	public String getPayTime() {		return payTime;	}	public void setPayTime(String payTime) {		this.payTime = payTime;	}	public String getPayResultInfo() {		return payResultInfo;	}	public void setPayResultInfo(String payResultInfo) {		this.payResultInfo = payResultInfo;	}	public String getPayErrInfo() {		return payErrInfo;	}	public void setPayErrInfo(String payErrInfo) {		this.payErrInfo = payErrInfo;	}	public BigInteger getCreater() {		return creater;	}	public void setCreater(BigInteger creater) {		this.creater = creater;	}
	
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
		EbuyPayRecord other = (EbuyPayRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
