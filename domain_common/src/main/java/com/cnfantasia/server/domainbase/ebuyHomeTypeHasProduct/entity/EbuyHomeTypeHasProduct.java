package com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(首页分类名称关联产品分类) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyHomeTypeHasProduct implements Serializable{
*/


public class EbuyHomeTypeHasProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tHomeTypeId;	/**  */	private BigInteger tProductId;	/** 按降序排序 */	private Long sort;
	public EbuyHomeTypeHasProduct(){
	}
	public EbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct arg){
		this.id = arg.getId();		this.tHomeTypeId = arg.gettHomeTypeId();		this.tProductId = arg.gettProductId();		this.sort = arg.getSort();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tHomeTypeId 	 * @param tProductId 	 * @param sort 按降序排序	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public EbuyHomeTypeHasProduct(BigInteger id,BigInteger tHomeTypeId,BigInteger tProductId,Long sort,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tHomeTypeId = tHomeTypeId;		this.tProductId = tProductId;		this.sort = sort;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tHomeTypeId=").append(tHomeTypeId).append(";");		sbf.append("tProductId=").append(tProductId).append(";");		sbf.append("sort=").append(sort).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettHomeTypeId() {		return tHomeTypeId;	}	public void settHomeTypeId(BigInteger tHomeTypeId) {		this.tHomeTypeId = tHomeTypeId;	}	public BigInteger gettProductId() {		return tProductId;	}	public void settProductId(BigInteger tProductId) {		this.tProductId = tProductId;	}	public Long getSort() {		return sort;	}	public void setSort(Long sort) {		this.sort = sort;	}
	
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
		EbuyHomeTypeHasProduct other = (EbuyHomeTypeHasProduct) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
