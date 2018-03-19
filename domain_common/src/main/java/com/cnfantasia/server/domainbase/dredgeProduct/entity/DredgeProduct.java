package com.cnfantasia.server.domainbase.dredgeProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修服务商品表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeProduct implements Serializable{
*/


public class DredgeProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public DredgeProduct(){
	}
	public DredgeProduct(DredgeProduct arg){
		this.id = arg.getId();
	}
	/**
	public DredgeProduct(BigInteger id,String name,String desc,String productPic,String introducePic,BigInteger dredgeType2ndId,Integer payType,Integer status,BigInteger serviceSupplierId,String sharePic,String sharePushPic,String shareFriendTitle,String shareCycleTitle,String shareContent,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
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
		DredgeProduct other = (DredgeProduct) obj;
		if (id == null) {
		return true;
	}
	
}