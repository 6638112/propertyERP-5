package com.cnfantasia.server.domainbase.revenueGeneration.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class RevenueGeneration implements Serializable{
/* */

/* 
public class RevenueGeneration extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/** 主键，不自动生成。和目标收益表的主键相等 */
	public RevenueGeneration(){
	}
	public RevenueGeneration(RevenueGeneration arg){
		this.id = arg.getId();
	}
	/**
	public RevenueGeneration(BigInteger id,Integer type,Integer revenueStatus,String revenueTm){
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
		RevenueGeneration other = (RevenueGeneration) obj;
		if (id == null) {
		return true;
	}
	
}