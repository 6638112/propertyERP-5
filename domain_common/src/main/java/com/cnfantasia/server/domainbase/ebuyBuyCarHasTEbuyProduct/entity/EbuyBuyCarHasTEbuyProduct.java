package com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyBuyCarHasTEbuyProduct implements Serializable{
*/


public class EbuyBuyCarHasTEbuyProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 购物车Id */	private BigInteger tEbuyBuyCarFId;	/** 商品Id */	private BigInteger tEbuyProductFId;	/** 商品规格Id */	private BigInteger tEbuyProductSpecFId;	/** 商品数量 */	private Long productQty;	/** 加入购物车的时间 */	private String createTime;
	public EbuyBuyCarHasTEbuyProduct(){
	}
	public EbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct arg){
		this.id = arg.getId();		this.tEbuyBuyCarFId = arg.gettEbuyBuyCarFId();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.tEbuyProductSpecFId = arg.gettEbuyProductSpecFId();		this.productQty = arg.getProductQty();		this.createTime = arg.getCreateTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyBuyCarFId 购物车Id	 * @param tEbuyProductFId 商品Id	 * @param tEbuyProductSpecFId 商品规格Id	 * @param productQty 商品数量	 * @param createTime 加入购物车的时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyBuyCarHasTEbuyProduct(BigInteger id,BigInteger tEbuyBuyCarFId,BigInteger tEbuyProductFId,BigInteger tEbuyProductSpecFId,Long productQty,String createTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyBuyCarFId = tEbuyBuyCarFId;		this.tEbuyProductFId = tEbuyProductFId;		this.tEbuyProductSpecFId = tEbuyProductSpecFId;		this.productQty = productQty;		this.createTime = createTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyBuyCarFId=").append(tEbuyBuyCarFId).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("tEbuyProductSpecFId=").append(tEbuyProductSpecFId).append(";");		sbf.append("productQty=").append(productQty).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyBuyCarFId() {		return tEbuyBuyCarFId;	}	public void settEbuyBuyCarFId(BigInteger tEbuyBuyCarFId) {		this.tEbuyBuyCarFId = tEbuyBuyCarFId;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public BigInteger gettEbuyProductSpecFId() {		return tEbuyProductSpecFId;	}	public void settEbuyProductSpecFId(BigInteger tEbuyProductSpecFId) {		this.tEbuyProductSpecFId = tEbuyProductSpecFId;	}	public Long getProductQty() {		return productQty;	}	public void setProductQty(Long productQty) {		this.productQty = productQty;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}
	
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
		EbuyBuyCarHasTEbuyProduct other = (EbuyBuyCarHasTEbuyProduct) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
