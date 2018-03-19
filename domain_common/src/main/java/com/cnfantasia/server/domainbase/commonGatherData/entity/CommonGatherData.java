package com.cnfantasia.server.domainbase.commonGatherData.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(通用模块信息归集) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommonGatherData implements Serializable{
*/


public class CommonGatherData extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 目标类别 */	private Integer targetType;	/** 目标Id */	private BigInteger targetId;	/** 总点赞数 */	private Long totalSupportCount;	/** 总评论数 */	private Long totalCommentCount;	/** 总收藏数 */	private Long totalCollectCount;	/** 最近更新时间 */	private String lastUpdateTime;
	public CommonGatherData(){
	}
	public CommonGatherData(CommonGatherData arg){
		this.id = arg.getId();		this.targetType = arg.getTargetType();		this.targetId = arg.getTargetId();		this.totalSupportCount = arg.getTotalSupportCount();		this.totalCommentCount = arg.getTotalCommentCount();		this.totalCollectCount = arg.getTotalCollectCount();		this.lastUpdateTime = arg.getLastUpdateTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param targetType 目标类别	 * @param targetId 目标Id	 * @param totalSupportCount 总点赞数	 * @param totalCommentCount 总评论数	 * @param totalCollectCount 总收藏数	 * @param lastUpdateTime 最近更新时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommonGatherData(BigInteger id,Integer targetType,BigInteger targetId,Long totalSupportCount,Long totalCommentCount,Long totalCollectCount,String lastUpdateTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.targetType = targetType;		this.targetId = targetId;		this.totalSupportCount = totalSupportCount;		this.totalCommentCount = totalCommentCount;		this.totalCollectCount = totalCollectCount;		this.lastUpdateTime = lastUpdateTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("targetType=").append(targetType).append(";");		sbf.append("targetId=").append(targetId).append(";");		sbf.append("totalSupportCount=").append(totalSupportCount).append(";");		sbf.append("totalCommentCount=").append(totalCommentCount).append(";");		sbf.append("totalCollectCount=").append(totalCollectCount).append(";");		sbf.append("lastUpdateTime=").append(lastUpdateTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getTargetType() {		return targetType;	}	public void setTargetType(Integer targetType) {		this.targetType = targetType;	}	public BigInteger getTargetId() {		return targetId;	}	public void setTargetId(BigInteger targetId) {		this.targetId = targetId;	}	public Long getTotalSupportCount() {		return totalSupportCount;	}	public void setTotalSupportCount(Long totalSupportCount) {		this.totalSupportCount = totalSupportCount;	}	public Long getTotalCommentCount() {		return totalCommentCount;	}	public void setTotalCommentCount(Long totalCommentCount) {		this.totalCommentCount = totalCommentCount;	}	public Long getTotalCollectCount() {		return totalCollectCount;	}	public void setTotalCollectCount(Long totalCollectCount) {		this.totalCollectCount = totalCollectCount;	}	public String getLastUpdateTime() {		return lastUpdateTime;	}	public void setLastUpdateTime(String lastUpdateTime) {		this.lastUpdateTime = lastUpdateTime;	}
	
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
		CommonGatherData other = (CommonGatherData) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
