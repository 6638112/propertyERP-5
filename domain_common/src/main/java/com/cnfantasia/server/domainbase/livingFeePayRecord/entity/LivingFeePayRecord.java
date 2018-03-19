package com.cnfantasia.server.domainbase.livingFeePayRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(生活缴费记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LivingFeePayRecord implements Serializable{
*/


public class LivingFeePayRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 充值对象 */	private String chargeObject;	/** 小区名称 */	private String groupBuildingName;	/** 门牌号 */	private String roomNumuber;	/** 地址=小区名+门牌号 */	private String address;	/** 缴费时间 */	private String payTime;	/** 代收金额 */	private Long amount;	/** 充值余额 */	private Long amountBalance;	/** 订单状态 */	private Integer status;	/** 联系电话 */	private String linkTel;	/** 姓名(宽带充值) */	private String name;	/** 身份证（宽带充值） */	private String cardId;	/** 运营商ID */	private BigInteger tSpId;	/** 缴的哪个项目 */	private BigInteger tLivingFeeItemFId;	/** 关联的订单 */	private BigInteger tEbuyOrderFId;	/**  */	private BigInteger tUserFId;
	public LivingFeePayRecord(){
	}
	public LivingFeePayRecord(LivingFeePayRecord arg){
		this.id = arg.getId();		this.chargeObject = arg.getChargeObject();		this.groupBuildingName = arg.getGroupBuildingName();		this.roomNumuber = arg.getRoomNumuber();		this.address = arg.getAddress();		this.payTime = arg.getPayTime();		this.amount = arg.getAmount();		this.amountBalance = arg.getAmountBalance();		this.status = arg.getStatus();		this.linkTel = arg.getLinkTel();		this.name = arg.getName();		this.cardId = arg.getCardId();		this.tSpId = arg.gettSpId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tLivingFeeItemFId = arg.gettLivingFeeItemFId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tUserFId = arg.gettUserFId();
	}
	/**	 * 	 * @param id 	 * @param chargeObject 充值对象	 * @param groupBuildingName 小区名称	 * @param roomNumuber 门牌号	 * @param address 地址=小区名+门牌号	 * @param payTime 缴费时间	 * @param amount 代收金额	 * @param amountBalance 充值余额	 * @param status 订单状态	 * @param linkTel 联系电话	 * @param name 姓名(宽带充值)	 * @param cardId 身份证（宽带充值）	 * @param tSpId 运营商ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tLivingFeeItemFId 缴的哪个项目	 * @param tEbuyOrderFId 关联的订单	 * @param tUserFId 	 */
	public LivingFeePayRecord(BigInteger id,String chargeObject,String groupBuildingName,String roomNumuber,String address,String payTime,Long amount,Long amountBalance,Integer status,String linkTel,String name,String cardId,BigInteger tSpId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tLivingFeeItemFId,BigInteger tEbuyOrderFId,BigInteger tUserFId){
		this.id = id;		this.chargeObject = chargeObject;		this.groupBuildingName = groupBuildingName;		this.roomNumuber = roomNumuber;		this.address = address;		this.payTime = payTime;		this.amount = amount;		this.amountBalance = amountBalance;		this.status = status;		this.linkTel = linkTel;		this.name = name;		this.cardId = cardId;		this.tSpId = tSpId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tLivingFeeItemFId = tLivingFeeItemFId;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tUserFId = tUserFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("chargeObject=").append(chargeObject).append(";");		sbf.append("groupBuildingName=").append(groupBuildingName).append(";");		sbf.append("roomNumuber=").append(roomNumuber).append(";");		sbf.append("address=").append(address).append(";");		sbf.append("payTime=").append(payTime).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("amountBalance=").append(amountBalance).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("linkTel=").append(linkTel).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("cardId=").append(cardId).append(";");		sbf.append("tSpId=").append(tSpId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tLivingFeeItemFId=").append(tLivingFeeItemFId).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getChargeObject() {		return chargeObject;	}	public void setChargeObject(String chargeObject) {		this.chargeObject = chargeObject;	}	public String getGroupBuildingName() {		return groupBuildingName;	}	public void setGroupBuildingName(String groupBuildingName) {		this.groupBuildingName = groupBuildingName;	}	public String getRoomNumuber() {		return roomNumuber;	}	public void setRoomNumuber(String roomNumuber) {		this.roomNumuber = roomNumuber;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}	public String getPayTime() {		return payTime;	}	public void setPayTime(String payTime) {		this.payTime = payTime;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public Long getAmountBalance() {		return amountBalance;	}	public void setAmountBalance(Long amountBalance) {		this.amountBalance = amountBalance;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public String getLinkTel() {		return linkTel;	}	public void setLinkTel(String linkTel) {		this.linkTel = linkTel;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getCardId() {		return cardId;	}	public void setCardId(String cardId) {		this.cardId = cardId;	}	public BigInteger gettSpId() {		return tSpId;	}	public void settSpId(BigInteger tSpId) {		this.tSpId = tSpId;	}	public BigInteger gettLivingFeeItemFId() {		return tLivingFeeItemFId;	}	public void settLivingFeeItemFId(BigInteger tLivingFeeItemFId) {		this.tLivingFeeItemFId = tLivingFeeItemFId;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}
	
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
		LivingFeePayRecord other = (LivingFeePayRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
