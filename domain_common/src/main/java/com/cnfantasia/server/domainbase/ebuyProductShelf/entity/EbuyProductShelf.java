package com.cnfantasia.server.domainbase.ebuyProductShelf.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(货架商品表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductShelf implements Serializable{
*/


public class EbuyProductShelf extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tEbuyProductId;
	/**  */
	private BigInteger tEbuyProductTypeId;
	/** 货架价格 */
	private Long price;
	/** 货架价格 */
	private Long priceDiscount;
	/** 货架上排序 */
	private Long sort;
	/** 上架状态 */
	private Integer upShelfState;
	/** 申请时间 */
	private String applyTime;
	/** 审批时间 */
	private String handTime;
	/**  */
	private BigInteger tSalesPromotionId;

	public EbuyProductShelf(){
	}
	public EbuyProductShelf(EbuyProductShelf arg){
		this.id = arg.getId();
		this.tEbuyProductId = arg.gettEbuyProductId();
		this.tEbuyProductTypeId = arg.gettEbuyProductTypeId();
		this.price = arg.getPrice();
		this.priceDiscount = arg.getPriceDiscount();
		this.sort = arg.getSort();
		this.upShelfState = arg.getUpShelfState();
		this.applyTime = arg.getApplyTime();
		this.handTime = arg.getHandTime();
		this.tSalesPromotionId = arg.gettSalesPromotionId();
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
	 * @param tEbuyProductId 
	 * @param tEbuyProductTypeId 
	 * @param price 货架价格
	 * @param priceDiscount 货架价格
	 * @param sort 货架上排序
	 * @param upShelfState 上架状态
	 * @param applyTime 申请时间
	 * @param handTime 审批时间
	 * @param tSalesPromotionId 
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public EbuyProductShelf(BigInteger id,BigInteger tEbuyProductId,BigInteger tEbuyProductTypeId,Long price,Long priceDiscount,Long sort,Integer upShelfState,String applyTime,String handTime,BigInteger tSalesPromotionId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tEbuyProductId = tEbuyProductId;
		this.tEbuyProductTypeId = tEbuyProductTypeId;
		this.price = price;
		this.priceDiscount = priceDiscount;
		this.sort = sort;
		this.upShelfState = upShelfState;
		this.applyTime = applyTime;
		this.handTime = handTime;
		this.tSalesPromotionId = tSalesPromotionId;
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
		sbf.append("tEbuyProductId=").append(tEbuyProductId).append(";");
		sbf.append("tEbuyProductTypeId=").append(tEbuyProductTypeId).append(";");
		sbf.append("price=").append(price).append(";");
		sbf.append("priceDiscount=").append(priceDiscount).append(";");
		sbf.append("sort=").append(sort).append(";");
		sbf.append("upShelfState=").append(upShelfState).append(";");
		sbf.append("applyTime=").append(applyTime).append(";");
		sbf.append("handTime=").append(handTime).append(";");
		sbf.append("tSalesPromotionId=").append(tSalesPromotionId).append(";");
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
	public BigInteger gettEbuyProductId() {
		return tEbuyProductId;
	}
	public void settEbuyProductId(BigInteger tEbuyProductId) {
		this.tEbuyProductId = tEbuyProductId;
	}
	public BigInteger gettEbuyProductTypeId() {
		return tEbuyProductTypeId;
	}
	public void settEbuyProductTypeId(BigInteger tEbuyProductTypeId) {
		this.tEbuyProductTypeId = tEbuyProductTypeId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPriceDiscount() {
		return priceDiscount;
	}
	public void setPriceDiscount(Long priceDiscount) {
		this.priceDiscount = priceDiscount;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public Integer getUpShelfState() {
		return upShelfState;
	}
	public void setUpShelfState(Integer upShelfState) {
		this.upShelfState = upShelfState;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getHandTime() {
		return handTime;
	}
	public void setHandTime(String handTime) {
		this.handTime = handTime;
	}
	public BigInteger gettSalesPromotionId() {
		return tSalesPromotionId;
	}
	public void settSalesPromotionId(BigInteger tSalesPromotionId) {
		this.tSalesPromotionId = tSalesPromotionId;
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
		EbuyProductShelf other = (EbuyProductShelf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
