package com.cnfantasia.server.domainbase.ebuyProductSpec.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品价格规格表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductSpec implements Serializable{
*/


public class EbuyProductSpec extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyProductFId;	/** 商品归类 */	private Integer pointType;	/** 价格 */	private Long price;	/** 折扣价 */	private Long priceDiscount;	/** 积分价 */	private Long pricePoint;	/** 积分折扣价 */	private Long priceDiscountPoint;	/** 话费金额 */	private String phoneAmount;	/** 代理商类别,如电信 */	private String phoneAgentType;	/** 尺寸 */	private String size;	/** 颜色 */	private String colour;	/** 库存数 */	private BigInteger leftCount;	/** 销量 */	private BigInteger selNum;	/** 基本图片地址 */	private String picBase;
	public EbuyProductSpec(){
	}
	public EbuyProductSpec(EbuyProductSpec arg){
		this.id = arg.getId();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.pointType = arg.getPointType();		this.price = arg.getPrice();		this.priceDiscount = arg.getPriceDiscount();		this.pricePoint = arg.getPricePoint();		this.priceDiscountPoint = arg.getPriceDiscountPoint();		this.phoneAmount = arg.getPhoneAmount();		this.phoneAgentType = arg.getPhoneAgentType();		this.size = arg.getSize();		this.colour = arg.getColour();		this.leftCount = arg.getLeftCount();		this.selNum = arg.getSelNum();		this.picBase = arg.getPicBase();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyProductFId 	 * @param pointType 商品归类	 * @param price 价格	 * @param priceDiscount 折扣价	 * @param pricePoint 积分价	 * @param priceDiscountPoint 积分折扣价	 * @param phoneAmount 话费金额	 * @param phoneAgentType 代理商类别,如电信	 * @param size 尺寸	 * @param colour 颜色	 * @param leftCount 库存数	 * @param selNum 销量	 * @param picBase 基本图片地址	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyProductSpec(BigInteger id,BigInteger tEbuyProductFId,Integer pointType,Long price,Long priceDiscount,Long pricePoint,Long priceDiscountPoint,String phoneAmount,String phoneAgentType,String size,String colour,BigInteger leftCount,BigInteger selNum,String picBase,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyProductFId = tEbuyProductFId;		this.pointType = pointType;		this.price = price;		this.priceDiscount = priceDiscount;		this.pricePoint = pricePoint;		this.priceDiscountPoint = priceDiscountPoint;		this.phoneAmount = phoneAmount;		this.phoneAgentType = phoneAgentType;		this.size = size;		this.colour = colour;		this.leftCount = leftCount;		this.selNum = selNum;		this.picBase = picBase;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("pointType=").append(pointType).append(";");		sbf.append("price=").append(price).append(";");		sbf.append("priceDiscount=").append(priceDiscount).append(";");		sbf.append("pricePoint=").append(pricePoint).append(";");		sbf.append("priceDiscountPoint=").append(priceDiscountPoint).append(";");		sbf.append("phoneAmount=").append(phoneAmount).append(";");		sbf.append("phoneAgentType=").append(phoneAgentType).append(";");		sbf.append("size=").append(size).append(";");		sbf.append("colour=").append(colour).append(";");		sbf.append("leftCount=").append(leftCount).append(";");		sbf.append("selNum=").append(selNum).append(";");		sbf.append("picBase=").append(picBase).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public Integer getPointType() {		return pointType;	}	public void setPointType(Integer pointType) {		this.pointType = pointType;	}	public Long getPrice() {		return price;	}	public void setPrice(Long price) {		this.price = price;	}	public Long getPriceDiscount() {		return priceDiscount;	}	public void setPriceDiscount(Long priceDiscount) {		this.priceDiscount = priceDiscount;	}	public Long getPricePoint() {		return pricePoint;	}	public void setPricePoint(Long pricePoint) {		this.pricePoint = pricePoint;	}	public Long getPriceDiscountPoint() {		return priceDiscountPoint;	}	public void setPriceDiscountPoint(Long priceDiscountPoint) {		this.priceDiscountPoint = priceDiscountPoint;	}	public String getPhoneAmount() {		return phoneAmount;	}	public void setPhoneAmount(String phoneAmount) {		this.phoneAmount = phoneAmount;	}	public String getPhoneAgentType() {		return phoneAgentType;	}	public void setPhoneAgentType(String phoneAgentType) {		this.phoneAgentType = phoneAgentType;	}	public String getSize() {		return size;	}	public void setSize(String size) {		this.size = size;	}	public String getColour() {		return colour;	}	public void setColour(String colour) {		this.colour = colour;	}	public BigInteger getLeftCount() {		return leftCount;	}	public void setLeftCount(BigInteger leftCount) {		this.leftCount = leftCount;	}	public BigInteger getSelNum() {		return selNum;	}	public void setSelNum(BigInteger selNum) {		this.selNum = selNum;	}	public String getPicBase() {		return picBase;	}	public void setPicBase(String picBase) {		this.picBase = picBase;	}
	
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
		EbuyProductSpec other = (EbuyProductSpec) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
