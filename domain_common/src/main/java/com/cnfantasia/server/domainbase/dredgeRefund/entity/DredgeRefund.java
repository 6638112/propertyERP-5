package com.cnfantasia.server.domainbase.dredgeRefund.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(到家服务退款申请) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeRefund implements Serializable{
*/


public class DredgeRefund extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 到家单ID */	private BigInteger tDredgeBillFId;	/** 申请退款时维修单状态 */	private Integer dredgeBillStatus;	/** 退款原因 */	private String refundReason;	/** 备注 */	private String note;	/** 退款现金（分） */	private Long refundAmount;	/** 退款粮票 */	private Long refundCouponAmount;	/** 状态 1 申请中 2 退款成功 3 退款失败 */	private Integer refundStatus;	/** 退款方式 1 全额退款 2 部分退款 */	private Integer refundType;	/** 审核说明 */	private String auditReason;
	public DredgeRefund(){
	}
	public DredgeRefund(DredgeRefund arg){
		this.id = arg.getId();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.dredgeBillStatus = arg.getDredgeBillStatus();		this.refundReason = arg.getRefundReason();		this.note = arg.getNote();		this.refundAmount = arg.getRefundAmount();		this.refundCouponAmount = arg.getRefundCouponAmount();		this.refundStatus = arg.getRefundStatus();		this.refundType = arg.getRefundType();		this.auditReason = arg.getAuditReason();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tDredgeBillFId 到家单ID	 * @param dredgeBillStatus 申请退款时维修单状态	 * @param refundReason 退款原因	 * @param note 备注	 * @param refundAmount 退款现金（分）	 * @param refundCouponAmount 退款粮票	 * @param refundStatus 状态 1 申请中 2 退款成功 3 退款失败	 * @param refundType 退款方式 1 全额退款 2 部分退款	 * @param auditReason 审核说明	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeRefund(BigInteger id,BigInteger tDredgeBillFId,Integer dredgeBillStatus,String refundReason,String note,Long refundAmount,Long refundCouponAmount,Integer refundStatus,Integer refundType,String auditReason,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tDredgeBillFId = tDredgeBillFId;		this.dredgeBillStatus = dredgeBillStatus;		this.refundReason = refundReason;		this.note = note;		this.refundAmount = refundAmount;		this.refundCouponAmount = refundCouponAmount;		this.refundStatus = refundStatus;		this.refundType = refundType;		this.auditReason = auditReason;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("dredgeBillStatus=").append(dredgeBillStatus).append(";");		sbf.append("refundReason=").append(refundReason).append(";");		sbf.append("note=").append(note).append(";");		sbf.append("refundAmount=").append(refundAmount).append(";");		sbf.append("refundCouponAmount=").append(refundCouponAmount).append(";");		sbf.append("refundStatus=").append(refundStatus).append(";");		sbf.append("refundType=").append(refundType).append(";");		sbf.append("auditReason=").append(auditReason).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public Integer getDredgeBillStatus() {		return dredgeBillStatus;	}	public void setDredgeBillStatus(Integer dredgeBillStatus) {		this.dredgeBillStatus = dredgeBillStatus;	}	public String getRefundReason() {		return refundReason;	}	public void setRefundReason(String refundReason) {		this.refundReason = refundReason;	}	public String getNote() {		return note;	}	public void setNote(String note) {		this.note = note;	}	public Long getRefundAmount() {		return refundAmount;	}	public void setRefundAmount(Long refundAmount) {		this.refundAmount = refundAmount;	}	public Long getRefundCouponAmount() {		return refundCouponAmount;	}	public void setRefundCouponAmount(Long refundCouponAmount) {		this.refundCouponAmount = refundCouponAmount;	}	public Integer getRefundStatus() {		return refundStatus;	}	public void setRefundStatus(Integer refundStatus) {		this.refundStatus = refundStatus;	}	public Integer getRefundType() {		return refundType;	}	public void setRefundType(Integer refundType) {		this.refundType = refundType;	}	public String getAuditReason() {		return auditReason;	}	public void setAuditReason(String auditReason) {		this.auditReason = auditReason;	}
	
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
		DredgeRefund other = (DredgeRefund) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
