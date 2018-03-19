package com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户在限时促销购买情况表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasTLimitBuyActivity implements Serializable{
*/


public class UserHasTLimitBuyActivity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键ID */	private BigInteger id;	/**  */	private BigInteger tLimitBuyActivityFId;	/** 关联的商品 */	private BigInteger tEbuyProductFId;	/** 关联的订单 */	private BigInteger tEbuyOrderFId;	/** 购买数量 */	private Long productCount;	/**  */	private BigInteger tUserFId;
	public UserHasTLimitBuyActivity(){
	}
	public UserHasTLimitBuyActivity(UserHasTLimitBuyActivity arg){
		this.id = arg.getId();		this.tLimitBuyActivityFId = arg.gettLimitBuyActivityFId();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.productCount = arg.getProductCount();		this.tUserFId = arg.gettUserFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 主键ID	 * @param tLimitBuyActivityFId 	 * @param tEbuyProductFId 关联的商品	 * @param tEbuyOrderFId 关联的订单	 * @param productCount 购买数量	 * @param tUserFId 	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public UserHasTLimitBuyActivity(BigInteger id,BigInteger tLimitBuyActivityFId,BigInteger tEbuyProductFId,BigInteger tEbuyOrderFId,Long productCount,BigInteger tUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tLimitBuyActivityFId = tLimitBuyActivityFId;		this.tEbuyProductFId = tEbuyProductFId;		this.tEbuyOrderFId = tEbuyOrderFId;		this.productCount = productCount;		this.tUserFId = tUserFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tLimitBuyActivityFId=").append(tLimitBuyActivityFId).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("productCount=").append(productCount).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettLimitBuyActivityFId() {		return tLimitBuyActivityFId;	}	public void settLimitBuyActivityFId(BigInteger tLimitBuyActivityFId) {		this.tLimitBuyActivityFId = tLimitBuyActivityFId;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public Long getProductCount() {		return productCount;	}	public void setProductCount(Long productCount) {		this.productCount = productCount;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}
	
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
		UserHasTLimitBuyActivity other = (UserHasTLimitBuyActivity) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
