package com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Double;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(楼下店师傅收益明细补充表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RevenueSignalAmountEbuy implements Serializable{
*/


public class RevenueSignalAmountEbuy extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 单个提款项的收益参数信息信息ID */	private BigInteger tRevenueSignalAmountId;	/** 优惠金额，商户不可见 */	private Double amountDiscount;	/** 运单实付，商户不可见 */	private Double amountReal;	/** 运单总额 */	private Double amountTotal;	/** 订单实付，商户不可见 */	private Double amountOrderReal;	/** 退款金额 */	private Double amountRefund;	/** 运单ID */	private BigInteger tOrderDeliverId;	/** 买家用户ID */	private BigInteger tUserId;	/** 花号 */	private String huaId;	/** 小区ID */	private BigInteger tGroupBuildingId;	/** 小区名字 */	private String groupBuildingName;	/** 支付方式 */	private Integer payMethod;	/** 物业公司ID */	private BigInteger tPcId;	/** 物业公司名称 */	private String pcName;	/** 代理公司ID */	private BigInteger tAgentId;	/** 代理公司名称 */	private String agentName;	/** 小区名称 */	private String gbName;	/** 支付流水号 */	private String flowNo;	/** 订单支付时间 */	private String payTm;	/** 维修类型名称 */	private String dredgeServiceName;	/** 平台+物业+代理总收益 */	private Double amoutDeduct;
	public RevenueSignalAmountEbuy(){
	}
	public RevenueSignalAmountEbuy(RevenueSignalAmountEbuy arg){
		this.id = arg.getId();		this.tRevenueSignalAmountId = arg.gettRevenueSignalAmountId();		this.amountDiscount = arg.getAmountDiscount();		this.amountReal = arg.getAmountReal();		this.amountTotal = arg.getAmountTotal();		this.amountOrderReal = arg.getAmountOrderReal();		this.amountRefund = arg.getAmountRefund();		this.tOrderDeliverId = arg.gettOrderDeliverId();		this.tUserId = arg.gettUserId();		this.huaId = arg.getHuaId();		this.tGroupBuildingId = arg.gettGroupBuildingId();		this.groupBuildingName = arg.getGroupBuildingName();		this.payMethod = arg.getPayMethod();		this.tPcId = arg.gettPcId();		this.pcName = arg.getPcName();		this.tAgentId = arg.gettAgentId();		this.agentName = arg.getAgentName();		this.gbName = arg.getGbName();		this.flowNo = arg.getFlowNo();		this.payTm = arg.getPayTm();		this.dredgeServiceName = arg.getDredgeServiceName();		this.amoutDeduct = arg.getAmoutDeduct();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRevenueSignalAmountId 单个提款项的收益参数信息信息ID	 * @param amountDiscount 优惠金额，商户不可见	 * @param amountReal 运单实付，商户不可见	 * @param amountTotal 运单总额	 * @param amountOrderReal 订单实付，商户不可见	 * @param amountRefund 退款金额	 * @param tOrderDeliverId 运单ID	 * @param tUserId 买家用户ID	 * @param huaId 花号	 * @param tGroupBuildingId 小区ID	 * @param groupBuildingName 小区名字	 * @param payMethod 支付方式	 * @param tPcId 物业公司ID	 * @param pcName 物业公司名称	 * @param tAgentId 代理公司ID	 * @param agentName 代理公司名称	 * @param gbName 小区名称	 * @param flowNo 支付流水号	 * @param payTm 订单支付时间	 * @param dredgeServiceName 维修类型名称	 * @param amoutDeduct 平台+物业+代理总收益	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RevenueSignalAmountEbuy(BigInteger id,BigInteger tRevenueSignalAmountId,Double amountDiscount,Double amountReal,Double amountTotal,Double amountOrderReal,Double amountRefund,BigInteger tOrderDeliverId,BigInteger tUserId,String huaId,BigInteger tGroupBuildingId,String groupBuildingName,Integer payMethod,BigInteger tPcId,String pcName,BigInteger tAgentId,String agentName,String gbName,String flowNo,String payTm,String dredgeServiceName,Double amoutDeduct,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRevenueSignalAmountId = tRevenueSignalAmountId;		this.amountDiscount = amountDiscount;		this.amountReal = amountReal;		this.amountTotal = amountTotal;		this.amountOrderReal = amountOrderReal;		this.amountRefund = amountRefund;		this.tOrderDeliverId = tOrderDeliverId;		this.tUserId = tUserId;		this.huaId = huaId;		this.tGroupBuildingId = tGroupBuildingId;		this.groupBuildingName = groupBuildingName;		this.payMethod = payMethod;		this.tPcId = tPcId;		this.pcName = pcName;		this.tAgentId = tAgentId;		this.agentName = agentName;		this.gbName = gbName;		this.flowNo = flowNo;		this.payTm = payTm;		this.dredgeServiceName = dredgeServiceName;		this.amoutDeduct = amoutDeduct;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRevenueSignalAmountId=").append(tRevenueSignalAmountId).append(";");		sbf.append("amountDiscount=").append(amountDiscount).append(";");		sbf.append("amountReal=").append(amountReal).append(";");		sbf.append("amountTotal=").append(amountTotal).append(";");		sbf.append("amountOrderReal=").append(amountOrderReal).append(";");		sbf.append("amountRefund=").append(amountRefund).append(";");		sbf.append("tOrderDeliverId=").append(tOrderDeliverId).append(";");		sbf.append("tUserId=").append(tUserId).append(";");		sbf.append("huaId=").append(huaId).append(";");		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");		sbf.append("groupBuildingName=").append(groupBuildingName).append(";");		sbf.append("payMethod=").append(payMethod).append(";");		sbf.append("tPcId=").append(tPcId).append(";");		sbf.append("pcName=").append(pcName).append(";");		sbf.append("tAgentId=").append(tAgentId).append(";");		sbf.append("agentName=").append(agentName).append(";");		sbf.append("gbName=").append(gbName).append(";");		sbf.append("flowNo=").append(flowNo).append(";");		sbf.append("payTm=").append(payTm).append(";");		sbf.append("dredgeServiceName=").append(dredgeServiceName).append(";");		sbf.append("amoutDeduct=").append(amoutDeduct).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRevenueSignalAmountId() {		return tRevenueSignalAmountId;	}	public void settRevenueSignalAmountId(BigInteger tRevenueSignalAmountId) {		this.tRevenueSignalAmountId = tRevenueSignalAmountId;	}	public Double getAmountDiscount() {		return amountDiscount;	}	public void setAmountDiscount(Double amountDiscount) {		this.amountDiscount = amountDiscount;	}	public Double getAmountReal() {		return amountReal;	}	public void setAmountReal(Double amountReal) {		this.amountReal = amountReal;	}	public Double getAmountTotal() {		return amountTotal;	}	public void setAmountTotal(Double amountTotal) {		this.amountTotal = amountTotal;	}	public Double getAmountOrderReal() {		return amountOrderReal;	}	public void setAmountOrderReal(Double amountOrderReal) {		this.amountOrderReal = amountOrderReal;	}	public Double getAmountRefund() {		return amountRefund;	}	public void setAmountRefund(Double amountRefund) {		this.amountRefund = amountRefund;	}	public BigInteger gettOrderDeliverId() {		return tOrderDeliverId;	}	public void settOrderDeliverId(BigInteger tOrderDeliverId) {		this.tOrderDeliverId = tOrderDeliverId;	}	public BigInteger gettUserId() {		return tUserId;	}	public void settUserId(BigInteger tUserId) {		this.tUserId = tUserId;	}	public String getHuaId() {		return huaId;	}	public void setHuaId(String huaId) {		this.huaId = huaId;	}	public BigInteger gettGroupBuildingId() {		return tGroupBuildingId;	}	public void settGroupBuildingId(BigInteger tGroupBuildingId) {		this.tGroupBuildingId = tGroupBuildingId;	}	public String getGroupBuildingName() {		return groupBuildingName;	}	public void setGroupBuildingName(String groupBuildingName) {		this.groupBuildingName = groupBuildingName;	}	public Integer getPayMethod() {		return payMethod;	}	public void setPayMethod(Integer payMethod) {		this.payMethod = payMethod;	}	public BigInteger gettPcId() {		return tPcId;	}	public void settPcId(BigInteger tPcId) {		this.tPcId = tPcId;	}	public String getPcName() {		return pcName;	}	public void setPcName(String pcName) {		this.pcName = pcName;	}	public BigInteger gettAgentId() {		return tAgentId;	}	public void settAgentId(BigInteger tAgentId) {		this.tAgentId = tAgentId;	}	public String getAgentName() {		return agentName;	}	public void setAgentName(String agentName) {		this.agentName = agentName;	}	public String getGbName() {		return gbName;	}	public void setGbName(String gbName) {		this.gbName = gbName;	}	public String getFlowNo() {		return flowNo;	}	public void setFlowNo(String flowNo) {		this.flowNo = flowNo;	}	public String getPayTm() {		return payTm;	}	public void setPayTm(String payTm) {		this.payTm = payTm;	}	public String getDredgeServiceName() {		return dredgeServiceName;	}	public void setDredgeServiceName(String dredgeServiceName) {		this.dredgeServiceName = dredgeServiceName;	}	public Double getAmoutDeduct() {
		if(amoutDeduct == null) {
			return 0.0;
		}		return amoutDeduct;	}	public void setAmoutDeduct(Double amoutDeduct) {		this.amoutDeduct = amoutDeduct;	}
	
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
		RevenueSignalAmountEbuy other = (RevenueSignalAmountEbuy) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
