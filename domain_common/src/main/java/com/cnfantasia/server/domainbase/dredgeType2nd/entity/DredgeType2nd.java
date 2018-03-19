package com.cnfantasia.server.domainbase.dredgeType2nd.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(疏通二级类型) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeType2nd implements Serializable{
*/


public class DredgeType2nd extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属一级分类 */	private BigInteger tDredgeTypeFId;	/** 类型名称 */	private String name;	/** 图标 */	private String picUrl;	/** 预估价格 */	private Long estinatePrice;	/** 原价 */	private Long originalPrice;	/** 活动价 */	private Long discountPrice;	/** 描述 */	private String desc;	/** 价格说明 */	private String priceDesc;
	public DredgeType2nd(){
	}
	public DredgeType2nd(DredgeType2nd arg){
		this.id = arg.getId();		this.tDredgeTypeFId = arg.gettDredgeTypeFId();		this.name = arg.getName();		this.picUrl = arg.getPicUrl();		this.estinatePrice = arg.getEstinatePrice();		this.originalPrice = arg.getOriginalPrice();		this.discountPrice = arg.getDiscountPrice();		this.desc = arg.getDesc();		this.priceDesc = arg.getPriceDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tDredgeTypeFId 所属一级分类	 * @param name 类型名称	 * @param picUrl 图标	 * @param estinatePrice 预估价格	 * @param originalPrice 原价	 * @param discountPrice 活动价	 * @param desc 描述	 * @param priceDesc 价格说明	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeType2nd(BigInteger id,BigInteger tDredgeTypeFId,String name,String picUrl,Long estinatePrice,Long originalPrice,Long discountPrice,String desc,String priceDesc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tDredgeTypeFId = tDredgeTypeFId;		this.name = name;		this.picUrl = picUrl;		this.estinatePrice = estinatePrice;		this.originalPrice = originalPrice;		this.discountPrice = discountPrice;		this.desc = desc;		this.priceDesc = priceDesc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeTypeFId=").append(tDredgeTypeFId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("estinatePrice=").append(estinatePrice).append(";");		sbf.append("originalPrice=").append(originalPrice).append(";");		sbf.append("discountPrice=").append(discountPrice).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("priceDesc=").append(priceDesc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeTypeFId() {		return tDredgeTypeFId;	}	public void settDredgeTypeFId(BigInteger tDredgeTypeFId) {		this.tDredgeTypeFId = tDredgeTypeFId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public Long getEstinatePrice() {		return estinatePrice;	}	public void setEstinatePrice(Long estinatePrice) {		this.estinatePrice = estinatePrice;	}	public Long getOriginalPrice() {		return originalPrice;	}	public void setOriginalPrice(Long originalPrice) {		this.originalPrice = originalPrice;	}	public Long getDiscountPrice() {		return discountPrice;	}	public void setDiscountPrice(Long discountPrice) {		this.discountPrice = discountPrice;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getPriceDesc() {		return priceDesc;	}	public void setPriceDesc(String priceDesc) {		this.priceDesc = priceDesc;	}
	
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
		DredgeType2nd other = (DredgeType2nd) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
