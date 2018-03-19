package com.cnfantasia.server.domainbase.loanProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(借贷产品表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoanProduct implements Serializable{
*/


public class LoanProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tLoanTypeFId;
	/**  */
	private BigInteger tLoanThirdFId;
	/** 图片地址 */
	private String picUrl;
	/** 名称 */
	private String name;
	/** 描述 */
	private String desc;
	/** 贷款额度描述 */
	private String maxLoanDesc;
	/** 利息描述 */
	private String rateDesc;
	/** 有效期 */
	private String expire;
	/** 第三方跳转地址 */
	private String thirdUrl;
	/** 排序id */
	private BigInteger order;

	public LoanProduct(){
	}
	public LoanProduct(LoanProduct arg){
		this.id = arg.getId();
		this.tLoanTypeFId = arg.gettLoanTypeFId();
		this.tLoanThirdFId = arg.gettLoanThirdFId();
		this.picUrl = arg.getPicUrl();
		this.name = arg.getName();
		this.desc = arg.getDesc();
		this.maxLoanDesc = arg.getMaxLoanDesc();
		this.rateDesc = arg.getRateDesc();
		this.expire = arg.getExpire();
		this.thirdUrl = arg.getThirdUrl();
		this.order = arg.getOrder();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tLoanTypeFId 
	 * @param tLoanThirdFId 
	 * @param picUrl 图片地址
	 * @param name 名称
	 * @param desc 描述
	 * @param maxLoanDesc 贷款额度描述
	 * @param rateDesc 利息描述
	 * @param expire 有效期
	 * @param thirdUrl 第三方跳转地址
	 * @param order 排序id
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public LoanProduct(BigInteger id,BigInteger tLoanTypeFId,BigInteger tLoanThirdFId,String picUrl,String name,String desc,String maxLoanDesc,String rateDesc,String expire,String thirdUrl,BigInteger order,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tLoanTypeFId = tLoanTypeFId;
		this.tLoanThirdFId = tLoanThirdFId;
		this.picUrl = picUrl;
		this.name = name;
		this.desc = desc;
		this.maxLoanDesc = maxLoanDesc;
		this.rateDesc = rateDesc;
		this.expire = expire;
		this.thirdUrl = thirdUrl;
		this.order = order;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tLoanTypeFId=").append(tLoanTypeFId).append(";");
		sbf.append("tLoanThirdFId=").append(tLoanThirdFId).append(";");
		sbf.append("picUrl=").append(picUrl).append(";");
		sbf.append("name=").append(name).append(";");
		sbf.append("desc=").append(desc).append(";");
		sbf.append("maxLoanDesc=").append(maxLoanDesc).append(";");
		sbf.append("rateDesc=").append(rateDesc).append(";");
		sbf.append("expire=").append(expire).append(";");
		sbf.append("thirdUrl=").append(thirdUrl).append(";");
		sbf.append("order=").append(order).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettLoanTypeFId() {
		return tLoanTypeFId;
	}
	public void settLoanTypeFId(BigInteger tLoanTypeFId) {
		this.tLoanTypeFId = tLoanTypeFId;
	}
	public BigInteger gettLoanThirdFId() {
		return tLoanThirdFId;
	}
	public void settLoanThirdFId(BigInteger tLoanThirdFId) {
		this.tLoanThirdFId = tLoanThirdFId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMaxLoanDesc() {
		return maxLoanDesc;
	}
	public void setMaxLoanDesc(String maxLoanDesc) {
		this.maxLoanDesc = maxLoanDesc;
	}
	public String getRateDesc() {
		return rateDesc;
	}
	public void setRateDesc(String rateDesc) {
		this.rateDesc = rateDesc;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getThirdUrl() {
		return thirdUrl;
	}
	public void setThirdUrl(String thirdUrl) {
		this.thirdUrl = thirdUrl;
	}
	public BigInteger getOrder() {
		return order;
	}
	public void setOrder(BigInteger order) {
		this.order = order;
	}

	
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
		LoanProduct other = (LoanProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
