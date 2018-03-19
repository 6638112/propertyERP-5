package com.cnfantasia.server.api.plotproperty.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(账单优惠用户关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class PaybillUserPreferEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	/**  */
	private String sys0AddTime;
	public PaybillUserPreferEntity(){
	}
	public PaybillUserPreferEntity(PaybillUserPreferEntity arg){
		this.id = arg.getId();
	}
	/**
	public PaybillUserPreferEntity(BigInteger id,BigInteger paybillId,BigInteger userId,Long prefer,String preferName,Integer isGetPrefer,String sys0AddTime){
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
	}
	
	public BigInteger getId() {
	
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
		PaybillUserPreferEntity other = (PaybillUserPreferEntity) obj;
		if (id == null) {
		return true;
	}
	public String getSys0AddTime() {
		return sys0AddTime;
	}
	public void setSys0AddTime(String sys0AddTime) {
		this.sys0AddTime = sys0AddTime;
	}
	
}