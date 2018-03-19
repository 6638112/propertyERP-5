package com.cnfantasia.server.domainbase.ebuyProductTemp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品表，临时存储从外部抓取的数据) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductTemp implements Serializable{
*/


public class EbuyProductTemp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 供应商Id */	private BigInteger tSupplyMerchantFId;	/** 商品名称 */	private String name;	/** 商品名称缩写 */	private String nameMini;	/** 价格 */	private Long price;	/** 折扣后价格 */	private Long priceDiscount;	/** 创建时间 */	private String createTime;	/** 产品外观图片 */	private String picBase;	/** 产品外观小图 */	private String picBaseMini;	/** 计价单位 */	private String priceUnit;	/** 规格 */	private String specification;	/** 产品描述 */	private String desc;	/** 产品状态 */	private Integer status;	/** 是否同步 */	private Integer isSync;	/** 上架时间 */	private String upShelfTime;	/** 下架时间 */	private String downShelfTime;	/** 源ID，从哪个网站上抓的，就取那里的ID */	private BigInteger srcId;	/** 从哪里抓取，比如”海吉星“，”中粮“，”依谷网“ */	private String fromWhere;	/** 源产品类型ID，从哪个网站上抓的，就取那里的产品类型ID */	private BigInteger srcProductTypeId;	/** 商品分类 */	private BigInteger tEbuyProductTypeFId;	/** 库存数 */	private BigInteger leftCount;
	public EbuyProductTemp(){
	}
	public EbuyProductTemp(EbuyProductTemp arg){
		this.id = arg.getId();		this.tSupplyMerchantFId = arg.gettSupplyMerchantFId();		this.name = arg.getName();		this.nameMini = arg.getNameMini();		this.price = arg.getPrice();		this.priceDiscount = arg.getPriceDiscount();		this.createTime = arg.getCreateTime();		this.picBase = arg.getPicBase();		this.picBaseMini = arg.getPicBaseMini();		this.priceUnit = arg.getPriceUnit();		this.specification = arg.getSpecification();		this.desc = arg.getDesc();		this.status = arg.getStatus();		this.isSync = arg.getIsSync();		this.upShelfTime = arg.getUpShelfTime();		this.downShelfTime = arg.getDownShelfTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.srcId = arg.getSrcId();		this.fromWhere = arg.getFromWhere();		this.srcProductTypeId = arg.getSrcProductTypeId();		this.tEbuyProductTypeFId = arg.gettEbuyProductTypeFId();		this.leftCount = arg.getLeftCount();
	}
	/**	 * 	 * @param id 	 * @param tSupplyMerchantFId 供应商Id	 * @param name 商品名称	 * @param nameMini 商品名称缩写	 * @param price 价格	 * @param priceDiscount 折扣后价格	 * @param createTime 创建时间	 * @param picBase 产品外观图片	 * @param picBaseMini 产品外观小图	 * @param priceUnit 计价单位	 * @param specification 规格	 * @param desc 产品描述	 * @param status 产品状态	 * @param isSync 是否同步	 * @param upShelfTime 上架时间	 * @param downShelfTime 下架时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param srcId 源ID，从哪个网站上抓的，就取那里的ID	 * @param fromWhere 从哪里抓取，比如”海吉星“，”中粮“，”依谷网“	 * @param srcProductTypeId 源产品类型ID，从哪个网站上抓的，就取那里的产品类型ID	 * @param tEbuyProductTypeFId 商品分类	 * @param leftCount 库存数	 */
	public EbuyProductTemp(BigInteger id,BigInteger tSupplyMerchantFId,String name,String nameMini,Long price,Long priceDiscount,String createTime,String picBase,String picBaseMini,String priceUnit,String specification,String desc,Integer status,Integer isSync,String upShelfTime,String downShelfTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger srcId,String fromWhere,BigInteger srcProductTypeId,BigInteger tEbuyProductTypeFId,BigInteger leftCount){
		this.id = id;		this.tSupplyMerchantFId = tSupplyMerchantFId;		this.name = name;		this.nameMini = nameMini;		this.price = price;		this.priceDiscount = priceDiscount;		this.createTime = createTime;		this.picBase = picBase;		this.picBaseMini = picBaseMini;		this.priceUnit = priceUnit;		this.specification = specification;		this.desc = desc;		this.status = status;		this.isSync = isSync;		this.upShelfTime = upShelfTime;		this.downShelfTime = downShelfTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.srcId = srcId;		this.fromWhere = fromWhere;		this.srcProductTypeId = srcProductTypeId;		this.tEbuyProductTypeFId = tEbuyProductTypeFId;		this.leftCount = leftCount;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tSupplyMerchantFId=").append(tSupplyMerchantFId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("nameMini=").append(nameMini).append(";");		sbf.append("price=").append(price).append(";");		sbf.append("priceDiscount=").append(priceDiscount).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("picBase=").append(picBase).append(";");		sbf.append("picBaseMini=").append(picBaseMini).append(";");		sbf.append("priceUnit=").append(priceUnit).append(";");		sbf.append("specification=").append(specification).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("isSync=").append(isSync).append(";");		sbf.append("upShelfTime=").append(upShelfTime).append(";");		sbf.append("downShelfTime=").append(downShelfTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("srcId=").append(srcId).append(";");		sbf.append("fromWhere=").append(fromWhere).append(";");		sbf.append("srcProductTypeId=").append(srcProductTypeId).append(";");		sbf.append("tEbuyProductTypeFId=").append(tEbuyProductTypeFId).append(";");		sbf.append("leftCount=").append(leftCount).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettSupplyMerchantFId() {		return tSupplyMerchantFId;	}	public void settSupplyMerchantFId(BigInteger tSupplyMerchantFId) {		this.tSupplyMerchantFId = tSupplyMerchantFId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getNameMini() {		return nameMini;	}	public void setNameMini(String nameMini) {		this.nameMini = nameMini;	}	public Long getPrice() {		return price;	}	public void setPrice(Long price) {		this.price = price;	}	public Long getPriceDiscount() {		return priceDiscount;	}	public void setPriceDiscount(Long priceDiscount) {		this.priceDiscount = priceDiscount;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getPicBase() {		return picBase;	}	public void setPicBase(String picBase) {		this.picBase = picBase;	}	public String getPicBaseMini() {		return picBaseMini;	}	public void setPicBaseMini(String picBaseMini) {		this.picBaseMini = picBaseMini;	}	public String getPriceUnit() {		return priceUnit;	}	public void setPriceUnit(String priceUnit) {		this.priceUnit = priceUnit;	}	public String getSpecification() {		return specification;	}	public void setSpecification(String specification) {		this.specification = specification;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getIsSync() {		return isSync;	}	public void setIsSync(Integer isSync) {		this.isSync = isSync;	}	public String getUpShelfTime() {		return upShelfTime;	}	public void setUpShelfTime(String upShelfTime) {		this.upShelfTime = upShelfTime;	}	public String getDownShelfTime() {		return downShelfTime;	}	public void setDownShelfTime(String downShelfTime) {		this.downShelfTime = downShelfTime;	}	public BigInteger getSrcId() {		return srcId;	}	public void setSrcId(BigInteger srcId) {		this.srcId = srcId;	}	public String getFromWhere() {		return fromWhere;	}	public void setFromWhere(String fromWhere) {		this.fromWhere = fromWhere;	}	public BigInteger getSrcProductTypeId() {		return srcProductTypeId;	}	public void setSrcProductTypeId(BigInteger srcProductTypeId) {		this.srcProductTypeId = srcProductTypeId;	}	public BigInteger gettEbuyProductTypeFId() {		return tEbuyProductTypeFId;	}	public void settEbuyProductTypeFId(BigInteger tEbuyProductTypeFId) {		this.tEbuyProductTypeFId = tEbuyProductTypeFId;	}	public BigInteger getLeftCount() {		return leftCount;	}	public void setLeftCount(BigInteger leftCount) {		this.leftCount = leftCount;	}
	
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
		EbuyProductTemp other = (EbuyProductTemp) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
