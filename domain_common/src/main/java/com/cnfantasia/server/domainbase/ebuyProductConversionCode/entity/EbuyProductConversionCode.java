package com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品兑换码表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductConversionCode implements Serializable{
*/


public class EbuyProductConversionCode extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 商品Id */	private BigInteger ebuyProductId;	/** 产品规格Id */	private BigInteger tEbuyProductSpecFId;	/** 兑换码状态 */	private Integer status;	/** 过期时间 */	private String outTime;	/** 兑换码值 */	private String value;
	public EbuyProductConversionCode(){
	}
	public EbuyProductConversionCode(EbuyProductConversionCode arg){
		this.id = arg.getId();		this.ebuyProductId = arg.getEbuyProductId();		this.tEbuyProductSpecFId = arg.gettEbuyProductSpecFId();		this.status = arg.getStatus();		this.outTime = arg.getOutTime();		this.value = arg.getValue();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param ebuyProductId 商品Id	 * @param tEbuyProductSpecFId 产品规格Id	 * @param status 兑换码状态	 * @param outTime 过期时间	 * @param value 兑换码值	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyProductConversionCode(BigInteger id,BigInteger ebuyProductId,BigInteger tEbuyProductSpecFId,Integer status,String outTime,String value,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.ebuyProductId = ebuyProductId;		this.tEbuyProductSpecFId = tEbuyProductSpecFId;		this.status = status;		this.outTime = outTime;		this.value = value;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("ebuyProductId=").append(ebuyProductId).append(";");		sbf.append("tEbuyProductSpecFId=").append(tEbuyProductSpecFId).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("outTime=").append(outTime).append(";");		sbf.append("value=").append(value).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getEbuyProductId() {		return ebuyProductId;	}	public void setEbuyProductId(BigInteger ebuyProductId) {		this.ebuyProductId = ebuyProductId;	}	public BigInteger gettEbuyProductSpecFId() {		return tEbuyProductSpecFId;	}	public void settEbuyProductSpecFId(BigInteger tEbuyProductSpecFId) {		this.tEbuyProductSpecFId = tEbuyProductSpecFId;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public String getOutTime() {		return outTime;	}	public void setOutTime(String outTime) {		this.outTime = outTime;	}	public String getValue() {		return value;	}	public void setValue(String value) {		this.value = value;	}
	
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
		EbuyProductConversionCode other = (EbuyProductConversionCode) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
