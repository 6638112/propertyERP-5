package com.cnfantasia.server.domainbase.prizeRuleGenerateArea.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(折扣生成规则-折扣区间分配因素) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PrizeRuleGenerateArea implements Serializable{
*/


public class PrizeRuleGenerateArea extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 区间名称 */	private String name;	/** 折扣开始区间,包含起始项,5600表示5.6折 */	private Long startDiscount;	/** 折扣结束区间,不包含结束项 */	private Long endDiscount;	/** 区间描述 */	private String desc;
	public PrizeRuleGenerateArea(){
	}
	public PrizeRuleGenerateArea(PrizeRuleGenerateArea arg){
		this.id = arg.getId();		this.name = arg.getName();		this.startDiscount = arg.getStartDiscount();		this.endDiscount = arg.getEndDiscount();		this.desc = arg.getDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 区间名称	 * @param startDiscount 折扣开始区间,包含起始项,5600表示5.6折	 * @param endDiscount 折扣结束区间,不包含结束项	 * @param desc 区间描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PrizeRuleGenerateArea(BigInteger id,String name,Long startDiscount,Long endDiscount,String desc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.startDiscount = startDiscount;		this.endDiscount = endDiscount;		this.desc = desc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("startDiscount=").append(startDiscount).append(";");		sbf.append("endDiscount=").append(endDiscount).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Long getStartDiscount() {		return startDiscount;	}	public void setStartDiscount(Long startDiscount) {		this.startDiscount = startDiscount;	}	public Long getEndDiscount() {		return endDiscount;	}	public void setEndDiscount(Long endDiscount) {		this.endDiscount = endDiscount;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}
	
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
		PrizeRuleGenerateArea other = (PrizeRuleGenerateArea) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
