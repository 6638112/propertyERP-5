package com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(自提点表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyFightSupplyMerchant implements Serializable{
*/


public class EbuyFightSupplyMerchant extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 拼购自提点名称 */	private String name;	/** 拼购自提点联系电话 */	private String tel;	/** 配送点描述 */	private String desc;	/** 自提点地址 */	private String delivAddress;	/** 自提点支持的配送方式 */	private BigInteger tEbuyDeliveryMethodFId;
	public EbuyFightSupplyMerchant(){
	}
	public EbuyFightSupplyMerchant(EbuyFightSupplyMerchant arg){
		this.id = arg.getId();		this.name = arg.getName();		this.tel = arg.getTel();		this.desc = arg.getDesc();		this.delivAddress = arg.getDelivAddress();		this.tEbuyDeliveryMethodFId = arg.gettEbuyDeliveryMethodFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 拼购自提点名称	 * @param tel 拼购自提点联系电话	 * @param desc 配送点描述	 * @param delivAddress 自提点地址	 * @param tEbuyDeliveryMethodFId 自提点支持的配送方式	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyFightSupplyMerchant(BigInteger id,String name,String tel,String desc,String delivAddress,BigInteger tEbuyDeliveryMethodFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.tel = tel;		this.desc = desc;		this.delivAddress = delivAddress;		this.tEbuyDeliveryMethodFId = tEbuyDeliveryMethodFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("tel=").append(tel).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("delivAddress=").append(delivAddress).append(";");		sbf.append("tEbuyDeliveryMethodFId=").append(tEbuyDeliveryMethodFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getTel() {		return tel;	}	public void setTel(String tel) {		this.tel = tel;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getDelivAddress() {		return delivAddress;	}	public void setDelivAddress(String delivAddress) {		this.delivAddress = delivAddress;	}	public BigInteger gettEbuyDeliveryMethodFId() {		return tEbuyDeliveryMethodFId;	}	public void settEbuyDeliveryMethodFId(BigInteger tEbuyDeliveryMethodFId) {		this.tEbuyDeliveryMethodFId = tEbuyDeliveryMethodFId;	}
	
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
		EbuyFightSupplyMerchant other = (EbuyFightSupplyMerchant) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
