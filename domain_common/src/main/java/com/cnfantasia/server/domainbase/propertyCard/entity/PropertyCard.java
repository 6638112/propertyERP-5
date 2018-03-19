package com.cnfantasia.server.domainbase.propertyCard.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业代扣卡) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCard implements Serializable{
*/


public class PropertyCard extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 卡内金额 */	private Long cardAmount;	/** 优惠金额 */	private Long discountAmt;	/** 用户实付金额 */	private Long realPayAmt;	/** 出售状态 */	private Integer sellState;	/** 优惠方案（1,2,3） */	private Integer discountType;
	public PropertyCard(){
	}
	public PropertyCard(PropertyCard arg){
		this.id = arg.getId();		this.cardAmount = arg.getCardAmount();		this.discountAmt = arg.getDiscountAmt();		this.realPayAmt = arg.getRealPayAmt();		this.sellState = arg.getSellState();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.discountType = arg.getDiscountType();
	}
	/**	 * 	 * @param id 	 * @param cardAmount 卡内金额	 * @param discountAmt 优惠金额	 * @param realPayAmt 用户实付金额	 * @param sellState 出售状态	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param discountType 优惠方案（1,2,3）	 */
	public PropertyCard(BigInteger id,Long cardAmount,Long discountAmt,Long realPayAmt,Integer sellState,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer discountType){
		this.id = id;		this.cardAmount = cardAmount;		this.discountAmt = discountAmt;		this.realPayAmt = realPayAmt;		this.sellState = sellState;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.discountType = discountType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("cardAmount=").append(cardAmount).append(";");		sbf.append("discountAmt=").append(discountAmt).append(";");		sbf.append("realPayAmt=").append(realPayAmt).append(";");		sbf.append("sellState=").append(sellState).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("discountType=").append(discountType).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getCardAmount() {		return cardAmount;	}	public void setCardAmount(Long cardAmount) {		this.cardAmount = cardAmount;	}	public Long getDiscountAmt() {		return discountAmt;	}	public void setDiscountAmt(Long discountAmt) {		this.discountAmt = discountAmt;	}	public Long getRealPayAmt() {		return realPayAmt;	}	public void setRealPayAmt(Long realPayAmt) {		this.realPayAmt = realPayAmt;	}	public Integer getSellState() {		return sellState;	}	public void setSellState(Integer sellState) {		this.sellState = sellState;	}	public Integer getDiscountType() {		return discountType;	}	public void setDiscountType(Integer discountType) {		this.discountType = discountType;	}
	
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
		PropertyCard other = (PropertyCard) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
