package com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小区滞纳金配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GroupBuildingCalculateLatefeeRule implements Serializable{
*/


public class GroupBuildingCalculateLatefeeRule extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tGbId;	/** 计算方式（1按月计算，2按天计算） */	private Integer calculateType;	/** 按月计算的天数 */	private Long calculateDaysByMonth;	/** 开始计算时间(每月几号 */	private Long calculateStart;	/** 滞纳金率利 */	private Double calculateRate;
	public GroupBuildingCalculateLatefeeRule(){
	}
	public GroupBuildingCalculateLatefeeRule(GroupBuildingCalculateLatefeeRule arg){
		this.id = arg.getId();		this.tGbId = arg.gettGbId();		this.calculateType = arg.getCalculateType();		this.calculateDaysByMonth = arg.getCalculateDaysByMonth();		this.calculateStart = arg.getCalculateStart();		this.calculateRate = arg.getCalculateRate();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGbId 	 * @param calculateType 计算方式（1按月计算，2按天计算）	 * @param calculateDaysByMonth 按月计算的天数	 * @param calculateStart 开始计算时间(每月几号	 * @param calculateRate 滞纳金率利	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public GroupBuildingCalculateLatefeeRule(BigInteger id,BigInteger tGbId,Integer calculateType,Long calculateDaysByMonth,Long calculateStart,Double calculateRate,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGbId = tGbId;		this.calculateType = calculateType;		this.calculateDaysByMonth = calculateDaysByMonth;		this.calculateStart = calculateStart;		this.calculateRate = calculateRate;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGbId=").append(tGbId).append(";");		sbf.append("calculateType=").append(calculateType).append(";");		sbf.append("calculateDaysByMonth=").append(calculateDaysByMonth).append(";");		sbf.append("calculateStart=").append(calculateStart).append(";");		sbf.append("calculateRate=").append(calculateRate).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGbId() {		return tGbId;	}	public void settGbId(BigInteger tGbId) {		this.tGbId = tGbId;	}	public Integer getCalculateType() {		return calculateType;	}	public void setCalculateType(Integer calculateType) {		this.calculateType = calculateType;	}	public Long getCalculateDaysByMonth() {		return calculateDaysByMonth;	}	public void setCalculateDaysByMonth(Long calculateDaysByMonth) {		this.calculateDaysByMonth = calculateDaysByMonth;	}	public Long getCalculateStart() {		return calculateStart;	}	public void setCalculateStart(Long calculateStart) {		this.calculateStart = calculateStart;	}	public Double getCalculateRate() {		return calculateRate;	}	public void setCalculateRate(Double calculateRate) {		this.calculateRate = calculateRate;	}
	
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
		GroupBuildingCalculateLatefeeRule other = (GroupBuildingCalculateLatefeeRule) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
