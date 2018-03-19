package com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修价格配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeTypePriceConfig implements Serializable{
*/


public class DredgeTypePriceConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 维修类别 */	private BigInteger tDredgeTypeFId;	/** 维修二级类别 */	private BigInteger tDredgeType2ndFId;	/** 维修二级类别 */	private BigInteger tAddressCityFId;	/** 管理处id */	private BigInteger tPropertyManagementFId;	/** 预估价格(分) */	private Long estinatePrice;	/** 原价 */	private Long originalPrice;	/** 活动价 */	private Long discountPrice;	/** 没二级类时一级维修价格是写的文字 */	private String desc;	/** 价格说明(一级二级都用这个) */	private String priceDesc;
	public DredgeTypePriceConfig(){
	}
	public DredgeTypePriceConfig(DredgeTypePriceConfig arg){
		this.id = arg.getId();		this.tDredgeTypeFId = arg.gettDredgeTypeFId();		this.tDredgeType2ndFId = arg.gettDredgeType2ndFId();		this.tAddressCityFId = arg.gettAddressCityFId();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();		this.estinatePrice = arg.getEstinatePrice();		this.originalPrice = arg.getOriginalPrice();		this.discountPrice = arg.getDiscountPrice();		this.desc = arg.getDesc();		this.priceDesc = arg.getPriceDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tDredgeTypeFId 维修类别	 * @param tDredgeType2ndFId 维修二级类别	 * @param tAddressCityFId 维修二级类别	 * @param tPropertyManagementFId 管理处id	 * @param estinatePrice 预估价格(分)	 * @param originalPrice 原价	 * @param discountPrice 活动价	 * @param desc 没二级类时一级维修价格是写的文字	 * @param priceDesc 价格说明(一级二级都用这个)	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeTypePriceConfig(BigInteger id,BigInteger tDredgeTypeFId,BigInteger tDredgeType2ndFId,BigInteger tAddressCityFId,BigInteger tPropertyManagementFId,Long estinatePrice,Long originalPrice,Long discountPrice,String desc,String priceDesc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tDredgeTypeFId = tDredgeTypeFId;		this.tDredgeType2ndFId = tDredgeType2ndFId;		this.tAddressCityFId = tAddressCityFId;		this.tPropertyManagementFId = tPropertyManagementFId;		this.estinatePrice = estinatePrice;		this.originalPrice = originalPrice;		this.discountPrice = discountPrice;		this.desc = desc;		this.priceDesc = priceDesc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeTypeFId=").append(tDredgeTypeFId).append(";");		sbf.append("tDredgeType2ndFId=").append(tDredgeType2ndFId).append(";");		sbf.append("tAddressCityFId=").append(tAddressCityFId).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		sbf.append("estinatePrice=").append(estinatePrice).append(";");		sbf.append("originalPrice=").append(originalPrice).append(";");		sbf.append("discountPrice=").append(discountPrice).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("priceDesc=").append(priceDesc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeTypeFId() {		return tDredgeTypeFId;	}	public void settDredgeTypeFId(BigInteger tDredgeTypeFId) {		this.tDredgeTypeFId = tDredgeTypeFId;	}	public BigInteger gettDredgeType2ndFId() {		return tDredgeType2ndFId;	}	public void settDredgeType2ndFId(BigInteger tDredgeType2ndFId) {		this.tDredgeType2ndFId = tDredgeType2ndFId;	}	public BigInteger gettAddressCityFId() {		return tAddressCityFId;	}	public void settAddressCityFId(BigInteger tAddressCityFId) {		this.tAddressCityFId = tAddressCityFId;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}	public Long getEstinatePrice() {		return estinatePrice;	}	public void setEstinatePrice(Long estinatePrice) {		this.estinatePrice = estinatePrice;	}	public Long getOriginalPrice() {		return originalPrice;	}	public void setOriginalPrice(Long originalPrice) {		this.originalPrice = originalPrice;	}	public Long getDiscountPrice() {		return discountPrice;	}	public void setDiscountPrice(Long discountPrice) {		this.discountPrice = discountPrice;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getPriceDesc() {		return priceDesc;	}	public void setPriceDesc(String priceDesc) {		this.priceDesc = priceDesc;	}
	
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
		DredgeTypePriceConfig other = (DredgeTypePriceConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
