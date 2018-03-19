package com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(配送方式) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyDeliveryMethod implements Serializable{
*/


public class EbuyDeliveryMethod extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/** 运费 */	private Long fee;	/** 最快时间(分钟) */	private Long fastTime;	/** 描述 */	private String desc;	/** 商品总价起始金额(分) */	private Long priceAmountStart;	/** 商品总价截止金额(分) */	private Long priceAmountEnd;	/** 配送类型 */	private Integer type;
	public EbuyDeliveryMethod(){
	}
	public EbuyDeliveryMethod(EbuyDeliveryMethod arg){
		this.id = arg.getId();		this.name = arg.getName();		this.fee = arg.getFee();		this.fastTime = arg.getFastTime();		this.desc = arg.getDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.priceAmountStart = arg.getPriceAmountStart();		this.priceAmountEnd = arg.getPriceAmountEnd();		this.type = arg.getType();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param fee 运费	 * @param fastTime 最快时间(分钟)	 * @param desc 描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param priceAmountStart 商品总价起始金额(分)	 * @param priceAmountEnd 商品总价截止金额(分)	 * @param type 配送类型	 */
	public EbuyDeliveryMethod(BigInteger id,String name,Long fee,Long fastTime,String desc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Long priceAmountStart,Long priceAmountEnd,Integer type){
		this.id = id;		this.name = name;		this.fee = fee;		this.fastTime = fastTime;		this.desc = desc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.priceAmountStart = priceAmountStart;		this.priceAmountEnd = priceAmountEnd;		this.type = type;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("fee=").append(fee).append(";");		sbf.append("fastTime=").append(fastTime).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("priceAmountStart=").append(priceAmountStart).append(";");		sbf.append("priceAmountEnd=").append(priceAmountEnd).append(";");		sbf.append("type=").append(type).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Long getFee() {		return fee;	}	public void setFee(Long fee) {		this.fee = fee;	}	public Long getFastTime() {		return fastTime;	}	public void setFastTime(Long fastTime) {		this.fastTime = fastTime;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Long getPriceAmountStart() {		return priceAmountStart;	}	public void setPriceAmountStart(Long priceAmountStart) {		this.priceAmountStart = priceAmountStart;	}	public Long getPriceAmountEnd() {		return priceAmountEnd;	}	public void setPriceAmountEnd(Long priceAmountEnd) {		this.priceAmountEnd = priceAmountEnd;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}
	
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
		EbuyDeliveryMethod other = (EbuyDeliveryMethod) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
