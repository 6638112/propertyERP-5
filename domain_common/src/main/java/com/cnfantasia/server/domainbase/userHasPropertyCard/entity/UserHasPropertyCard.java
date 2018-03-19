package com.cnfantasia.server.domainbase.userHasPropertyCard.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户购买物业代扣卡) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasPropertyCard implements Serializable{
*/


public class UserHasPropertyCard extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 卡内金额 */	private Long cardAmount;	/** 优惠金额 */	private Long discountAmt;	/** 用户实付金额 */	private Long realPayAmt;	/** 划扣后的余额 */	private Long balanceAmt;	/** 订单流水号 */	private String orderNo;	/**  */	private BigInteger tEbuyOrderFId;	/** 购买时间 */	private String buyTime;	/** 购买用户id */	private BigInteger tUserFId;	/**  */	private BigInteger tPropertyCardFId;
	public UserHasPropertyCard(){
	}
	public UserHasPropertyCard(UserHasPropertyCard arg){
		this.id = arg.getId();		this.cardAmount = arg.getCardAmount();		this.discountAmt = arg.getDiscountAmt();		this.realPayAmt = arg.getRealPayAmt();		this.balanceAmt = arg.getBalanceAmt();		this.orderNo = arg.getOrderNo();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.buyTime = arg.getBuyTime();		this.tUserFId = arg.gettUserFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tPropertyCardFId = arg.gettPropertyCardFId();
	}
	/**	 * 	 * @param id 	 * @param cardAmount 卡内金额	 * @param discountAmt 优惠金额	 * @param realPayAmt 用户实付金额	 * @param balanceAmt 划扣后的余额	 * @param orderNo 订单流水号	 * @param tEbuyOrderFId 	 * @param buyTime 购买时间	 * @param tUserFId 购买用户id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tPropertyCardFId 	 */
	public UserHasPropertyCard(BigInteger id,Long cardAmount,Long discountAmt,Long realPayAmt,Long balanceAmt,String orderNo,BigInteger tEbuyOrderFId,String buyTime,BigInteger tUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tPropertyCardFId){
		this.id = id;		this.cardAmount = cardAmount;		this.discountAmt = discountAmt;		this.realPayAmt = realPayAmt;		this.balanceAmt = balanceAmt;		this.orderNo = orderNo;		this.tEbuyOrderFId = tEbuyOrderFId;		this.buyTime = buyTime;		this.tUserFId = tUserFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tPropertyCardFId = tPropertyCardFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("cardAmount=").append(cardAmount).append(";");		sbf.append("discountAmt=").append(discountAmt).append(";");		sbf.append("realPayAmt=").append(realPayAmt).append(";");		sbf.append("balanceAmt=").append(balanceAmt).append(";");		sbf.append("orderNo=").append(orderNo).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("buyTime=").append(buyTime).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tPropertyCardFId=").append(tPropertyCardFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getCardAmount() {		return cardAmount;	}	public void setCardAmount(Long cardAmount) {		this.cardAmount = cardAmount;	}	public Long getDiscountAmt() {		return discountAmt;	}	public void setDiscountAmt(Long discountAmt) {		this.discountAmt = discountAmt;	}	public Long getRealPayAmt() {		return realPayAmt;	}	public void setRealPayAmt(Long realPayAmt) {		this.realPayAmt = realPayAmt;	}	public Long getBalanceAmt() {		return balanceAmt;	}	public void setBalanceAmt(Long balanceAmt) {		this.balanceAmt = balanceAmt;	}	public String getOrderNo() {		return orderNo;	}	public void setOrderNo(String orderNo) {		this.orderNo = orderNo;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public String getBuyTime() {		return buyTime;	}	public void setBuyTime(String buyTime) {		this.buyTime = buyTime;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettPropertyCardFId() {		return tPropertyCardFId;	}	public void settPropertyCardFId(BigInteger tPropertyCardFId) {		this.tPropertyCardFId = tPropertyCardFId;	}
	
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
		UserHasPropertyCard other = (UserHasPropertyCard) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
