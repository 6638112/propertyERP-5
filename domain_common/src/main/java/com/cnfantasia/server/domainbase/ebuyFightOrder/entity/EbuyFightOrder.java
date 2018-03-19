package com.cnfantasia.server.domainbase.ebuyFightOrder.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(拼购详情订单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyFightOrder implements Serializable{
*/


public class EbuyFightOrder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 拼购订单id */	private BigInteger tEbuyOrderFId;	/** 收货区域 */	private String delivAddressArea;	/** 拼购商品表id */	private BigInteger tEbuyProductFightGroupsFId;	/** 下单时间 */	private String buyTime;	/** 拼购状态 */	private Integer status;	/** 商品归类 */	private Integer pointType;
	public EbuyFightOrder(){
	}
	public EbuyFightOrder(EbuyFightOrder arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.delivAddressArea = arg.getDelivAddressArea();		this.tEbuyProductFightGroupsFId = arg.gettEbuyProductFightGroupsFId();		this.buyTime = arg.getBuyTime();		this.status = arg.getStatus();		this.pointType = arg.getPointType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 拼购订单id	 * @param delivAddressArea 收货区域	 * @param tEbuyProductFightGroupsFId 拼购商品表id	 * @param buyTime 下单时间	 * @param status 拼购状态	 * @param pointType 商品归类	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 删除状态	 */
	public EbuyFightOrder(BigInteger id,BigInteger tEbuyOrderFId,String delivAddressArea,BigInteger tEbuyProductFightGroupsFId,String buyTime,Integer status,Integer pointType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.delivAddressArea = delivAddressArea;		this.tEbuyProductFightGroupsFId = tEbuyProductFightGroupsFId;		this.buyTime = buyTime;		this.status = status;		this.pointType = pointType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("delivAddressArea=").append(delivAddressArea).append(";");		sbf.append("tEbuyProductFightGroupsFId=").append(tEbuyProductFightGroupsFId).append(";");		sbf.append("buyTime=").append(buyTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("pointType=").append(pointType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public String getDelivAddressArea() {		return delivAddressArea;	}	public void setDelivAddressArea(String delivAddressArea) {		this.delivAddressArea = delivAddressArea;	}	public BigInteger gettEbuyProductFightGroupsFId() {		return tEbuyProductFightGroupsFId;	}	public void settEbuyProductFightGroupsFId(BigInteger tEbuyProductFightGroupsFId) {		this.tEbuyProductFightGroupsFId = tEbuyProductFightGroupsFId;	}	public String getBuyTime() {		return buyTime;	}	public void setBuyTime(String buyTime) {		this.buyTime = buyTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getPointType() {		return pointType;	}	public void setPointType(Integer pointType) {		this.pointType = pointType;	}
	
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
		EbuyFightOrder other = (EbuyFightOrder) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
