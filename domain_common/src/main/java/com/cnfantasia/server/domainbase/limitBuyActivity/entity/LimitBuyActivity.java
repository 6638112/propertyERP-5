package com.cnfantasia.server.domainbase.limitBuyActivity.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(限时促销) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LimitBuyActivity implements Serializable{
*/


public class LimitBuyActivity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键ID */	private BigInteger id;	/** 活动标题 */	private String title;	/** 活动开始时间 */	private String activityStartTime;	/** 活动结束时间 */	private String activityEndTime;	/** 关联的商品 */	private BigInteger tEbuyProductFId;	/** 活动价格（分） */	private Long activityPrice;	/** 原销售价格（分） */	private Long priceDiscount;	/** 每人最大购买数量，-1表示不限 */	private Long maxCanBuy;	/** 促销库存 */	private Long leftCount;	/** 1 */	private Integer positionType;
	public LimitBuyActivity(){
	}
	public LimitBuyActivity(LimitBuyActivity arg){
		this.id = arg.getId();		this.title = arg.getTitle();		this.activityStartTime = arg.getActivityStartTime();		this.activityEndTime = arg.getActivityEndTime();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.activityPrice = arg.getActivityPrice();		this.priceDiscount = arg.getPriceDiscount();		this.maxCanBuy = arg.getMaxCanBuy();		this.leftCount = arg.getLeftCount();		this.positionType = arg.getPositionType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 主键ID	 * @param title 活动标题	 * @param activityStartTime 活动开始时间	 * @param activityEndTime 活动结束时间	 * @param tEbuyProductFId 关联的商品	 * @param activityPrice 活动价格（分）	 * @param priceDiscount 原销售价格（分）	 * @param maxCanBuy 每人最大购买数量，-1表示不限	 * @param leftCount 促销库存	 * @param positionType 1	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public LimitBuyActivity(BigInteger id,String title,String activityStartTime,String activityEndTime,BigInteger tEbuyProductFId,Long activityPrice,Long priceDiscount,Long maxCanBuy,Long leftCount,Integer positionType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.title = title;		this.activityStartTime = activityStartTime;		this.activityEndTime = activityEndTime;		this.tEbuyProductFId = tEbuyProductFId;		this.activityPrice = activityPrice;		this.priceDiscount = priceDiscount;		this.maxCanBuy = maxCanBuy;		this.leftCount = leftCount;		this.positionType = positionType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("activityStartTime=").append(activityStartTime).append(";");		sbf.append("activityEndTime=").append(activityEndTime).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("activityPrice=").append(activityPrice).append(";");		sbf.append("priceDiscount=").append(priceDiscount).append(";");		sbf.append("maxCanBuy=").append(maxCanBuy).append(";");		sbf.append("leftCount=").append(leftCount).append(";");		sbf.append("positionType=").append(positionType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getActivityStartTime() {		return activityStartTime;	}	public void setActivityStartTime(String activityStartTime) {		this.activityStartTime = activityStartTime;	}	public String getActivityEndTime() {		return activityEndTime;	}	public void setActivityEndTime(String activityEndTime) {		this.activityEndTime = activityEndTime;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public Long getActivityPrice() {		return activityPrice;	}	public void setActivityPrice(Long activityPrice) {		this.activityPrice = activityPrice;	}	public Long getPriceDiscount() {		return priceDiscount;	}	public void setPriceDiscount(Long priceDiscount) {		this.priceDiscount = priceDiscount;	}	public Long getMaxCanBuy() {		return maxCanBuy;	}	public void setMaxCanBuy(Long maxCanBuy) {		this.maxCanBuy = maxCanBuy;	}	public Long getLeftCount() {		return leftCount;	}	public void setLeftCount(Long leftCount) {		this.leftCount = leftCount;	}	public Integer getPositionType() {		return positionType;	}	public void setPositionType(Integer positionType) {		this.positionType = positionType;	}
	
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
		LimitBuyActivity other = (LimitBuyActivity) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
