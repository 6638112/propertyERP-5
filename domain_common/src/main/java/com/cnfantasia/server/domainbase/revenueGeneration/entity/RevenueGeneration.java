package com.cnfantasia.server.domainbase.revenueGeneration.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.Integer;
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
	/** 主键，不自动生成。和目标收益表的主键相等 */	private BigInteger id;	/** 收益类型 */	private Integer type;	/** 收益明细生成状态 */	private Integer revenueStatus;	/** 生成收益的时间 */	private String revenueTm;
	public RevenueGeneration(){
	}
	public RevenueGeneration(RevenueGeneration arg){
		this.id = arg.getId();		this.type = arg.getType();		this.revenueStatus = arg.getRevenueStatus();		this.revenueTm = arg.getRevenueTm();
	}
	/**	 * 	 * @param id 主键，不自动生成。和目标收益表的主键相等	 * @param type 收益类型	 * @param revenueStatus 收益明细生成状态	 * @param revenueTm 生成收益的时间	 */
	public RevenueGeneration(BigInteger id,Integer type,Integer revenueStatus,String revenueTm){
		this.id = id;		this.type = type;		this.revenueStatus = revenueStatus;		this.revenueTm = revenueTm;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("revenueStatus=").append(revenueStatus).append(";");		sbf.append("revenueTm=").append(revenueTm).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public Integer getRevenueStatus() {		return revenueStatus;	}	public void setRevenueStatus(Integer revenueStatus) {		this.revenueStatus = revenueStatus;	}	public String getRevenueTm() {		return revenueTm;	}	public void setRevenueTm(String revenueTm) {		this.revenueTm = revenueTm;	}
	
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
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
