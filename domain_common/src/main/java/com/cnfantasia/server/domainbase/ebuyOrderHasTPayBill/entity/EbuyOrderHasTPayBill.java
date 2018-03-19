package com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Double;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderHasTPayBill implements Serializable{
*/


public class EbuyOrderHasTPayBill extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderFId;	/**  */	private BigInteger tPayBillFId;	/** 使用的物业折扣,为空表示不使用折扣 */	private Double discount;	/** 中奖记录Id */	private BigInteger prizeRecordId;	/** 中奖记录对应的用户门牌Id */	private BigInteger prizeUserRoomId;
	public EbuyOrderHasTPayBill(){
	}
	public EbuyOrderHasTPayBill(EbuyOrderHasTPayBill arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tPayBillFId = arg.gettPayBillFId();		this.discount = arg.getDiscount();		this.prizeRecordId = arg.getPrizeRecordId();		this.prizeUserRoomId = arg.getPrizeUserRoomId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 	 * @param tPayBillFId 	 * @param discount 使用的物业折扣,为空表示不使用折扣	 * @param prizeRecordId 中奖记录Id	 * @param prizeUserRoomId 中奖记录对应的用户门牌Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyOrderHasTPayBill(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tPayBillFId,Double discount,BigInteger prizeRecordId,BigInteger prizeUserRoomId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tPayBillFId = tPayBillFId;		this.discount = discount;		this.prizeRecordId = prizeRecordId;		this.prizeUserRoomId = prizeUserRoomId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");		sbf.append("discount=").append(discount).append(";");		sbf.append("prizeRecordId=").append(prizeRecordId).append(";");		sbf.append("prizeUserRoomId=").append(prizeUserRoomId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettPayBillFId() {		return tPayBillFId;	}	public void settPayBillFId(BigInteger tPayBillFId) {		this.tPayBillFId = tPayBillFId;	}	public Double getDiscount() {		return discount;	}	public void setDiscount(Double discount) {		this.discount = discount;	}	public BigInteger getPrizeRecordId() {		return prizeRecordId;	}	public void setPrizeRecordId(BigInteger prizeRecordId) {		this.prizeRecordId = prizeRecordId;	}	public BigInteger getPrizeUserRoomId() {		return prizeUserRoomId;	}	public void setPrizeUserRoomId(BigInteger prizeUserRoomId) {		this.prizeUserRoomId = prizeUserRoomId;	}
	
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
		EbuyOrderHasTPayBill other = (EbuyOrderHasTPayBill) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
