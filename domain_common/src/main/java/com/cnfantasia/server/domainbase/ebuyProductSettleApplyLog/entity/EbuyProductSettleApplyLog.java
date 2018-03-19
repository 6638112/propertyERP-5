package com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;

/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(购销模式结算申请表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class EbuyProductSettleApplyLog implements Serializable{
/* */

/* 
public class EbuyProductSettleApplyLog extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tRevenueApplyFId;
	/**  */
	private BigInteger tEbuyDeliveryOrderFId;
	/** 提交时间 */
	private String addTime;

	public EbuyProductSettleApplyLog(){
	}
	public EbuyProductSettleApplyLog(EbuyProductSettleApplyLog arg){
		this.id = arg.getId();
		this.tRevenueApplyFId = arg.gettRevenueApplyFId();
		this.tEbuyDeliveryOrderFId = arg.gettEbuyDeliveryOrderFId();
		this.addTime = arg.getAddTime();

	}
	/**
	 * 
	 * @param id 
	 * @param tRevenueApplyFId 
	 * @param tEbuyDeliveryOrderFId 
	 * @param addTime 提交时间
	 */

	public EbuyProductSettleApplyLog(BigInteger id,BigInteger tRevenueApplyFId,BigInteger tEbuyDeliveryOrderFId,String addTime){
		this.id = id;
		this.tRevenueApplyFId = tRevenueApplyFId;
		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;
		this.addTime = addTime;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tRevenueApplyFId=").append(tRevenueApplyFId).append(";");
		sbf.append("tEbuyDeliveryOrderFId=").append(tEbuyDeliveryOrderFId).append(";");
		sbf.append("addTime=").append(addTime).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettRevenueApplyFId() {
		return tRevenueApplyFId;
	}
	public void settRevenueApplyFId(BigInteger tRevenueApplyFId) {
		this.tRevenueApplyFId = tRevenueApplyFId;
	}
	public BigInteger gettEbuyDeliveryOrderFId() {
		return tEbuyDeliveryOrderFId;
	}
	public void settEbuyDeliveryOrderFId(BigInteger tEbuyDeliveryOrderFId) {
		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
		EbuyProductSettleApplyLog other = (EbuyProductSettleApplyLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
