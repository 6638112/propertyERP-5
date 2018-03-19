package com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Double;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(解放区体验店商品类型信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class JfqStoreProductTypeInfo implements Serializable{
*/


public class JfqStoreProductTypeInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 体验店商品类型名称 */	private String name;	/**  */	private BigInteger tEbuyProductTypeFId;	/** 售价比率 */	private Double sellPriceRate;
	public JfqStoreProductTypeInfo(){
	}
	public JfqStoreProductTypeInfo(JfqStoreProductTypeInfo arg){
		this.id = arg.getId();		this.name = arg.getName();		this.tEbuyProductTypeFId = arg.gettEbuyProductTypeFId();		this.sellPriceRate = arg.getSellPriceRate();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 体验店商品类型名称	 * @param tEbuyProductTypeFId 	 * @param sellPriceRate 售价比率	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public JfqStoreProductTypeInfo(BigInteger id,String name,BigInteger tEbuyProductTypeFId,Double sellPriceRate,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.tEbuyProductTypeFId = tEbuyProductTypeFId;		this.sellPriceRate = sellPriceRate;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("tEbuyProductTypeFId=").append(tEbuyProductTypeFId).append(";");		sbf.append("sellPriceRate=").append(sellPriceRate).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public BigInteger gettEbuyProductTypeFId() {		return tEbuyProductTypeFId;	}	public void settEbuyProductTypeFId(BigInteger tEbuyProductTypeFId) {		this.tEbuyProductTypeFId = tEbuyProductTypeFId;	}	public Double getSellPriceRate() {		return sellPriceRate;	}	public void setSellPriceRate(Double sellPriceRate) {		this.sellPriceRate = sellPriceRate;	}
	
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
		JfqStoreProductTypeInfo other = (JfqStoreProductTypeInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
