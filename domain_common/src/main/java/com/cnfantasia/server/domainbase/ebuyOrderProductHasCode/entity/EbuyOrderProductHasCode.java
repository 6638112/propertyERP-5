package com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品订单关系所包含的兑换码) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderProductHasCode implements Serializable{
*/


public class EbuyOrderProductHasCode extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderHasTEbuyProductFId;	/**  */	private BigInteger tEbuyProductConversionCodeFId;	/** 兑换码取值 */	private String codeValue;	/** 兑换码过期时间 */	private String outTime;
	public EbuyOrderProductHasCode(){
	}
	public EbuyOrderProductHasCode(EbuyOrderProductHasCode arg){
		this.id = arg.getId();		this.tEbuyOrderHasTEbuyProductFId = arg.gettEbuyOrderHasTEbuyProductFId();		this.tEbuyProductConversionCodeFId = arg.gettEbuyProductConversionCodeFId();		this.codeValue = arg.getCodeValue();		this.outTime = arg.getOutTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderHasTEbuyProductFId 	 * @param tEbuyProductConversionCodeFId 	 * @param codeValue 兑换码取值	 * @param outTime 兑换码过期时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyOrderProductHasCode(BigInteger id,BigInteger tEbuyOrderHasTEbuyProductFId,BigInteger tEbuyProductConversionCodeFId,String codeValue,String outTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderHasTEbuyProductFId = tEbuyOrderHasTEbuyProductFId;		this.tEbuyProductConversionCodeFId = tEbuyProductConversionCodeFId;		this.codeValue = codeValue;		this.outTime = outTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderHasTEbuyProductFId=").append(tEbuyOrderHasTEbuyProductFId).append(";");		sbf.append("tEbuyProductConversionCodeFId=").append(tEbuyProductConversionCodeFId).append(";");		sbf.append("codeValue=").append(codeValue).append(";");		sbf.append("outTime=").append(outTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderHasTEbuyProductFId() {		return tEbuyOrderHasTEbuyProductFId;	}	public void settEbuyOrderHasTEbuyProductFId(BigInteger tEbuyOrderHasTEbuyProductFId) {		this.tEbuyOrderHasTEbuyProductFId = tEbuyOrderHasTEbuyProductFId;	}	public BigInteger gettEbuyProductConversionCodeFId() {		return tEbuyProductConversionCodeFId;	}	public void settEbuyProductConversionCodeFId(BigInteger tEbuyProductConversionCodeFId) {		this.tEbuyProductConversionCodeFId = tEbuyProductConversionCodeFId;	}	public String getCodeValue() {		return codeValue;	}	public void setCodeValue(String codeValue) {		this.codeValue = codeValue;	}	public String getOutTime() {		return outTime;	}	public void setOutTime(String outTime) {		this.outTime = outTime;	}
	
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
		EbuyOrderProductHasCode other = (EbuyOrderProductHasCode) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
