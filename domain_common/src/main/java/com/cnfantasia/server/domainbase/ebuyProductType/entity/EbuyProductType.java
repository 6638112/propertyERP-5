package com.cnfantasia.server.domainbase.ebuyProductType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品类别) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductType implements Serializable{
*/


public class EbuyProductType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 类别名称 */	private String typeName;	/** 商品类别归类 */	private Integer pointType;	/** 商品归类 */	private Long wlappType;	/** 排序 */	private Long order;
	public EbuyProductType(){
	}
	public EbuyProductType(EbuyProductType arg){
		this.id = arg.getId();		this.typeName = arg.getTypeName();		this.pointType = arg.getPointType();		this.wlappType = arg.getWlappType();		this.order = arg.getOrder();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param typeName 类别名称	 * @param pointType 商品类别归类	 * @param wlappType 商品归类	 * @param order 排序	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyProductType(BigInteger id,String typeName,Integer pointType,Long wlappType,Long order,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.typeName = typeName;		this.pointType = pointType;		this.wlappType = wlappType;		this.order = order;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("typeName=").append(typeName).append(";");		sbf.append("pointType=").append(pointType).append(";");		sbf.append("wlappType=").append(wlappType).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTypeName() {		return typeName;	}	public void setTypeName(String typeName) {		this.typeName = typeName;	}	public Integer getPointType() {		return pointType;	}	public void setPointType(Integer pointType) {		this.pointType = pointType;	}	public Long getWlappType() {		return wlappType;	}	public void setWlappType(Long wlappType) {		this.wlappType = wlappType;	}	public Long getOrder() {		return order;	}	public void setOrder(Long order) {		this.order = order;	}
	
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
		EbuyProductType other = (EbuyProductType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
