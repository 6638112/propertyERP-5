package com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(折扣抽奖规则-用户在线时间) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PrizeRuleDrawOnlinedays implements Serializable{
*/


public class PrizeRuleDrawOnlinedays extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 起始天数 */	private Long startDays;	/** 结束天数 */	private Long endDays;	/** A折扣区间所占百分比,7600表示76% */	private Long areaAPercent;	/** B折扣区间所占百分比 */	private Long areaBPercent;	/** C折扣区间所占百分比 */	private Long areaCPercent;	/** D折扣区间所占百分比 */	private Long areaDPercent;	/** E折扣区间所占百分比 */	private Long areaEPercent;	/** 折扣分配描述 */	private String desc;
	public PrizeRuleDrawOnlinedays(){
	}
	public PrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays arg){
		this.id = arg.getId();		this.startDays = arg.getStartDays();		this.endDays = arg.getEndDays();		this.areaAPercent = arg.getAreaAPercent();		this.areaBPercent = arg.getAreaBPercent();		this.areaCPercent = arg.getAreaCPercent();		this.areaDPercent = arg.getAreaDPercent();		this.areaEPercent = arg.getAreaEPercent();		this.desc = arg.getDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param startDays 起始天数	 * @param endDays 结束天数	 * @param areaAPercent A折扣区间所占百分比,7600表示76%	 * @param areaBPercent B折扣区间所占百分比	 * @param areaCPercent C折扣区间所占百分比	 * @param areaDPercent D折扣区间所占百分比	 * @param areaEPercent E折扣区间所占百分比	 * @param desc 折扣分配描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PrizeRuleDrawOnlinedays(BigInteger id,Long startDays,Long endDays,Long areaAPercent,Long areaBPercent,Long areaCPercent,Long areaDPercent,Long areaEPercent,String desc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.startDays = startDays;		this.endDays = endDays;		this.areaAPercent = areaAPercent;		this.areaBPercent = areaBPercent;		this.areaCPercent = areaCPercent;		this.areaDPercent = areaDPercent;		this.areaEPercent = areaEPercent;		this.desc = desc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("startDays=").append(startDays).append(";");		sbf.append("endDays=").append(endDays).append(";");		sbf.append("areaAPercent=").append(areaAPercent).append(";");		sbf.append("areaBPercent=").append(areaBPercent).append(";");		sbf.append("areaCPercent=").append(areaCPercent).append(";");		sbf.append("areaDPercent=").append(areaDPercent).append(";");		sbf.append("areaEPercent=").append(areaEPercent).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getStartDays() {		return startDays;	}	public void setStartDays(Long startDays) {		this.startDays = startDays;	}	public Long getEndDays() {		return endDays;	}	public void setEndDays(Long endDays) {		this.endDays = endDays;	}	public Long getAreaAPercent() {		return areaAPercent;	}	public void setAreaAPercent(Long areaAPercent) {		this.areaAPercent = areaAPercent;	}	public Long getAreaBPercent() {		return areaBPercent;	}	public void setAreaBPercent(Long areaBPercent) {		this.areaBPercent = areaBPercent;	}	public Long getAreaCPercent() {		return areaCPercent;	}	public void setAreaCPercent(Long areaCPercent) {		this.areaCPercent = areaCPercent;	}	public Long getAreaDPercent() {		return areaDPercent;	}	public void setAreaDPercent(Long areaDPercent) {		this.areaDPercent = areaDPercent;	}	public Long getAreaEPercent() {		return areaEPercent;	}	public void setAreaEPercent(Long areaEPercent) {		this.areaEPercent = areaEPercent;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}
	
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
		PrizeRuleDrawOnlinedays other = (PrizeRuleDrawOnlinedays) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
