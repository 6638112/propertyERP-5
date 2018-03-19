package com.cnfantasia.server.domainbase.loanBuyLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(借贷购买记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoanBuyLog implements Serializable{
*/


public class LoanBuyLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tUserFId;
	/** 订单号 */
	private String orderNo;
	/** 城市 */
	private String city;
	/** 小区 */
	private String gbName;
	/** 楼栋 */
	private String buildingName;
	/** 房间号 */
	private String roomNum;
	/** 购买人姓名 */
	private String name;
	/** 手机号 */
	private String mobile;
	/** 身份证 */
	private String idCard;
	/** 产品类型 */
	private BigInteger tLoanProductFId;
	/** 产品类型（冗余） */
	private String productType;
	/** 借贷时间 */
	private String loanDate;
	/** 借贷金额（单位 */
	private Double loanAmount;

	public LoanBuyLog(){
	}
	public LoanBuyLog(LoanBuyLog arg){
		this.id = arg.getId();
		this.tUserFId = arg.gettUserFId();
		this.orderNo = arg.getOrderNo();
		this.city = arg.getCity();
		this.gbName = arg.getGbName();
		this.buildingName = arg.getBuildingName();
		this.roomNum = arg.getRoomNum();
		this.name = arg.getName();
		this.mobile = arg.getMobile();
		this.idCard = arg.getIdCard();
		this.tLoanProductFId = arg.gettLoanProductFId();
		this.productType = arg.getProductType();
		this.loanDate = arg.getLoanDate();
		this.loanAmount = arg.getLoanAmount();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tUserFId 
	 * @param orderNo 订单号
	 * @param city 城市
	 * @param gbName 小区
	 * @param buildingName 楼栋
	 * @param roomNum 房间号
	 * @param name 购买人姓名
	 * @param mobile 手机号
	 * @param idCard 身份证
	 * @param tLoanProductFId 产品类型
	 * @param productType 产品类型（冗余）
	 * @param loanDate 借贷时间
	 * @param loanAmount 借贷金额（单位
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public LoanBuyLog(BigInteger id,BigInteger tUserFId,String orderNo,String city,String gbName,String buildingName,String roomNum,String name,String mobile,String idCard,BigInteger tLoanProductFId,String productType,String loanDate,Double loanAmount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tUserFId = tUserFId;
		this.orderNo = orderNo;
		this.city = city;
		this.gbName = gbName;
		this.buildingName = buildingName;
		this.roomNum = roomNum;
		this.name = name;
		this.mobile = mobile;
		this.idCard = idCard;
		this.tLoanProductFId = tLoanProductFId;
		this.productType = productType;
		this.loanDate = loanDate;
		this.loanAmount = loanAmount;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tUserFId=").append(tUserFId).append(";");
		sbf.append("orderNo=").append(orderNo).append(";");
		sbf.append("city=").append(city).append(";");
		sbf.append("gbName=").append(gbName).append(";");
		sbf.append("buildingName=").append(buildingName).append(";");
		sbf.append("roomNum=").append(roomNum).append(";");
		sbf.append("name=").append(name).append(";");
		sbf.append("mobile=").append(mobile).append(";");
		sbf.append("idCard=").append(idCard).append(";");
		sbf.append("tLoanProductFId=").append(tLoanProductFId).append(";");
		sbf.append("productType=").append(productType).append(";");
		sbf.append("loanDate=").append(loanDate).append(";");
		sbf.append("loanAmount=").append(loanAmount).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettUserFId() {
		return tUserFId;
	}
	public void settUserFId(BigInteger tUserFId) {
		this.tUserFId = tUserFId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public BigInteger gettLoanProductFId() {
		return tLoanProductFId;
	}
	public void settLoanProductFId(BigInteger tLoanProductFId) {
		this.tLoanProductFId = tLoanProductFId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
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
		LoanBuyLog other = (LoanBuyLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
