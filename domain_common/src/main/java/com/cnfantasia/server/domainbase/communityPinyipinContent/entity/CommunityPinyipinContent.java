package com.cnfantasia.server.domainbase.communityPinyipinContent.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(拼一拼内容表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityPinyipinContent implements Serializable{
*/


public class CommunityPinyipinContent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 创建者 */	private BigInteger userId;	/** 拼单创建时间 */	private String createTime;	/** 标题 */	private String title;	/** 商品描述 */	private String desc;	/** 市场价 */	private Long marketPrice;	/** 拼单价 */	private Long realPrice;	/** 计价单位 */	private String priceUnit;	/** 目标数量 */	private Long goalCount;	/** 配送方式 */	private Integer sendType;	/** 自提地点 */	private String selfAddress;	/** 截止日期 */	private String endDate;	/** 配送日期 */	private String sendDate;	/** 联系方式 */	private String contectInfo;	/** 发货清单状态 */	private Integer deliveryStatus;	/**  */	private BigInteger tGroupBuildingFId;
	public CommunityPinyipinContent(){
	}
	public CommunityPinyipinContent(CommunityPinyipinContent arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.createTime = arg.getCreateTime();		this.title = arg.getTitle();		this.desc = arg.getDesc();		this.marketPrice = arg.getMarketPrice();		this.realPrice = arg.getRealPrice();		this.priceUnit = arg.getPriceUnit();		this.goalCount = arg.getGoalCount();		this.sendType = arg.getSendType();		this.selfAddress = arg.getSelfAddress();		this.endDate = arg.getEndDate();		this.sendDate = arg.getSendDate();		this.contectInfo = arg.getContectInfo();		this.deliveryStatus = arg.getDeliveryStatus();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 创建者	 * @param createTime 拼单创建时间	 * @param title 标题	 * @param desc 商品描述	 * @param marketPrice 市场价	 * @param realPrice 拼单价	 * @param priceUnit 计价单位	 * @param goalCount 目标数量	 * @param sendType 配送方式	 * @param selfAddress 自提地点	 * @param endDate 截止日期	 * @param sendDate 配送日期	 * @param contectInfo 联系方式	 * @param deliveryStatus 发货清单状态	 * @param tGroupBuildingFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityPinyipinContent(BigInteger id,BigInteger userId,String createTime,String title,String desc,Long marketPrice,Long realPrice,String priceUnit,Long goalCount,Integer sendType,String selfAddress,String endDate,String sendDate,String contectInfo,Integer deliveryStatus,BigInteger tGroupBuildingFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.createTime = createTime;		this.title = title;		this.desc = desc;		this.marketPrice = marketPrice;		this.realPrice = realPrice;		this.priceUnit = priceUnit;		this.goalCount = goalCount;		this.sendType = sendType;		this.selfAddress = selfAddress;		this.endDate = endDate;		this.sendDate = sendDate;		this.contectInfo = contectInfo;		this.deliveryStatus = deliveryStatus;		this.tGroupBuildingFId = tGroupBuildingFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("marketPrice=").append(marketPrice).append(";");		sbf.append("realPrice=").append(realPrice).append(";");		sbf.append("priceUnit=").append(priceUnit).append(";");		sbf.append("goalCount=").append(goalCount).append(";");		sbf.append("sendType=").append(sendType).append(";");		sbf.append("selfAddress=").append(selfAddress).append(";");		sbf.append("endDate=").append(endDate).append(";");		sbf.append("sendDate=").append(sendDate).append(";");		sbf.append("contectInfo=").append(contectInfo).append(";");		sbf.append("deliveryStatus=").append(deliveryStatus).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Long getMarketPrice() {		return marketPrice;	}	public void setMarketPrice(Long marketPrice) {		this.marketPrice = marketPrice;	}	public Long getRealPrice() {		return realPrice;	}	public void setRealPrice(Long realPrice) {		this.realPrice = realPrice;	}	public String getPriceUnit() {		return priceUnit;	}	public void setPriceUnit(String priceUnit) {		this.priceUnit = priceUnit;	}	public Long getGoalCount() {		return goalCount;	}	public void setGoalCount(Long goalCount) {		this.goalCount = goalCount;	}	public Integer getSendType() {		return sendType;	}	public void setSendType(Integer sendType) {		this.sendType = sendType;	}	public String getSelfAddress() {		return selfAddress;	}	public void setSelfAddress(String selfAddress) {		this.selfAddress = selfAddress;	}	public String getEndDate() {		return endDate;	}	public void setEndDate(String endDate) {		this.endDate = endDate;	}	public String getSendDate() {		return sendDate;	}	public void setSendDate(String sendDate) {		this.sendDate = sendDate;	}	public String getContectInfo() {		return contectInfo;	}	public void setContectInfo(String contectInfo) {		this.contectInfo = contectInfo;	}	public Integer getDeliveryStatus() {		return deliveryStatus;	}	public void setDeliveryStatus(Integer deliveryStatus) {		this.deliveryStatus = deliveryStatus;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}
	
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
		CommunityPinyipinContent other = (CommunityPinyipinContent) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
