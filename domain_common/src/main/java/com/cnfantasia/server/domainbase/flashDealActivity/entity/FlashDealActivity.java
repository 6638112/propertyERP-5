package com.cnfantasia.server.domainbase.flashDealActivity.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(秒杀活动表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FlashDealActivity implements Serializable{
*/


public class FlashDealActivity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键ID */	private BigInteger id;	/** 活动标题 */	private String title;	/** 活动显示的图片(多图分号隔开) */	private String activityPic;	/** 活动开始时间 */	private String activityStartTime;	/** 活动结束时间 */	private String activityEndTime;	/** 关联的商品，可以不填，作提示可以购买用 */	private BigInteger tEbuyProductFId;	/** 活动价格（分） */	private Long activityPrice;	/** 活动物品件数 */	private Long prizeCount;	/** 活动内容介绍 */	private String introduceContent;	/** 活动规则 */	private String introduceRule;	/** 是否已抽奖（0 */	private Integer isSettle;
	public FlashDealActivity(){
	}
	public FlashDealActivity(FlashDealActivity arg){
		this.id = arg.getId();		this.title = arg.getTitle();		this.activityPic = arg.getActivityPic();		this.activityStartTime = arg.getActivityStartTime();		this.activityEndTime = arg.getActivityEndTime();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.activityPrice = arg.getActivityPrice();		this.prizeCount = arg.getPrizeCount();		this.introduceContent = arg.getIntroduceContent();		this.introduceRule = arg.getIntroduceRule();		this.isSettle = arg.getIsSettle();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 主键ID	 * @param title 活动标题	 * @param activityPic 活动显示的图片(多图分号隔开)	 * @param activityStartTime 活动开始时间	 * @param activityEndTime 活动结束时间	 * @param tEbuyProductFId 关联的商品，可以不填，作提示可以购买用	 * @param activityPrice 活动价格（分）	 * @param prizeCount 活动物品件数	 * @param introduceContent 活动内容介绍	 * @param introduceRule 活动规则	 * @param isSettle 是否已抽奖（0	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public FlashDealActivity(BigInteger id,String title,String activityPic,String activityStartTime,String activityEndTime,BigInteger tEbuyProductFId,Long activityPrice,Long prizeCount,String introduceContent,String introduceRule,Integer isSettle,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.title = title;		this.activityPic = activityPic;		this.activityStartTime = activityStartTime;		this.activityEndTime = activityEndTime;		this.tEbuyProductFId = tEbuyProductFId;		this.activityPrice = activityPrice;		this.prizeCount = prizeCount;		this.introduceContent = introduceContent;		this.introduceRule = introduceRule;		this.isSettle = isSettle;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("activityPic=").append(activityPic).append(";");		sbf.append("activityStartTime=").append(activityStartTime).append(";");		sbf.append("activityEndTime=").append(activityEndTime).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("activityPrice=").append(activityPrice).append(";");		sbf.append("prizeCount=").append(prizeCount).append(";");		sbf.append("introduceContent=").append(introduceContent).append(";");		sbf.append("introduceRule=").append(introduceRule).append(";");		sbf.append("isSettle=").append(isSettle).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getActivityPic() {		return activityPic;	}	public void setActivityPic(String activityPic) {		this.activityPic = activityPic;	}	public String getActivityStartTime() {		return activityStartTime;	}	public void setActivityStartTime(String activityStartTime) {		this.activityStartTime = activityStartTime;	}	public String getActivityEndTime() {		return activityEndTime;	}	public void setActivityEndTime(String activityEndTime) {		this.activityEndTime = activityEndTime;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public Long getActivityPrice() {		return activityPrice;	}	public void setActivityPrice(Long activityPrice) {		this.activityPrice = activityPrice;	}	public Long getPrizeCount() {		return prizeCount;	}	public void setPrizeCount(Long prizeCount) {		this.prizeCount = prizeCount;	}	public String getIntroduceContent() {		return introduceContent;	}	public void setIntroduceContent(String introduceContent) {		this.introduceContent = introduceContent;	}	public String getIntroduceRule() {		return introduceRule;	}	public void setIntroduceRule(String introduceRule) {		this.introduceRule = introduceRule;	}	public Integer getIsSettle() {		return isSettle;	}	public void setIsSettle(Integer isSettle) {		this.isSettle = isSettle;	}
	
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
		FlashDealActivity other = (FlashDealActivity) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
