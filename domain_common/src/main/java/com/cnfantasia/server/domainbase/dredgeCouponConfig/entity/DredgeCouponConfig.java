package com.cnfantasia.server.domainbase.dredgeCouponConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修券使用配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeCouponConfig implements Serializable{
*/


public class DredgeCouponConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** id */	private BigInteger id;	/** 消费券ID */	private BigInteger couponId;	/** 支持程度 */	private Integer supportLevel;	/** 维修大类ID, supportType=2时用 */	private BigInteger communitySupplyTypeId;	/** 维修子类ID, supportType=3时用 */	private BigInteger dredgeTypeId;	/** 优惠费用类型 */	private Integer couponFeeType;
	public DredgeCouponConfig(){
	}
	public DredgeCouponConfig(DredgeCouponConfig arg){
		this.id = arg.getId();		this.couponId = arg.getCouponId();		this.supportLevel = arg.getSupportLevel();		this.communitySupplyTypeId = arg.getCommunitySupplyTypeId();		this.dredgeTypeId = arg.getDredgeTypeId();		this.couponFeeType = arg.getCouponFeeType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id id	 * @param couponId 消费券ID	 * @param supportLevel 支持程度	 * @param communitySupplyTypeId 维修大类ID, supportType=2时用	 * @param dredgeTypeId 维修子类ID, supportType=3时用	 * @param couponFeeType 优惠费用类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeCouponConfig(BigInteger id,BigInteger couponId,Integer supportLevel,BigInteger communitySupplyTypeId,BigInteger dredgeTypeId,Integer couponFeeType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.couponId = couponId;		this.supportLevel = supportLevel;		this.communitySupplyTypeId = communitySupplyTypeId;		this.dredgeTypeId = dredgeTypeId;		this.couponFeeType = couponFeeType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("couponId=").append(couponId).append(";");		sbf.append("supportLevel=").append(supportLevel).append(";");		sbf.append("communitySupplyTypeId=").append(communitySupplyTypeId).append(";");		sbf.append("dredgeTypeId=").append(dredgeTypeId).append(";");		sbf.append("couponFeeType=").append(couponFeeType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getCouponId() {		return couponId;	}	public void setCouponId(BigInteger couponId) {		this.couponId = couponId;	}	public Integer getSupportLevel() {		return supportLevel;	}	public void setSupportLevel(Integer supportLevel) {		this.supportLevel = supportLevel;	}	public BigInteger getCommunitySupplyTypeId() {		return communitySupplyTypeId;	}	public void setCommunitySupplyTypeId(BigInteger communitySupplyTypeId) {		this.communitySupplyTypeId = communitySupplyTypeId;	}	public BigInteger getDredgeTypeId() {		return dredgeTypeId;	}	public void setDredgeTypeId(BigInteger dredgeTypeId) {		this.dredgeTypeId = dredgeTypeId;	}	public Integer getCouponFeeType() {		return couponFeeType;	}	public void setCouponFeeType(Integer couponFeeType) {		this.couponFeeType = couponFeeType;	}
	
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
		DredgeCouponConfig other = (DredgeCouponConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
