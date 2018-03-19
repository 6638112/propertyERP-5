package com.cnfantasia.server.domainbase.microblogPushType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(街坊消息推送类别表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MicroblogPushType implements Serializable{
*/


public class MicroblogPushType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/** 唯一编码 */	private String code;	/** 初始优先级,数据越小优先级越高 */	private Long priority;	/** 有效时间长度,小时 */	private Long validityHours;	/** 每天最多消息数 */	private Long count;	/** 描述信息 */	private String desc;
	public MicroblogPushType(){
	}
	public MicroblogPushType(MicroblogPushType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.code = arg.getCode();		this.priority = arg.getPriority();		this.validityHours = arg.getValidityHours();		this.count = arg.getCount();		this.desc = arg.getDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param code 唯一编码	 * @param priority 初始优先级,数据越小优先级越高	 * @param validityHours 有效时间长度,小时	 * @param count 每天最多消息数	 * @param desc 描述信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MicroblogPushType(BigInteger id,String name,String code,Long priority,Long validityHours,Long count,String desc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.code = code;		this.priority = priority;		this.validityHours = validityHours;		this.count = count;		this.desc = desc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("code=").append(code).append(";");		sbf.append("priority=").append(priority).append(";");		sbf.append("validityHours=").append(validityHours).append(";");		sbf.append("count=").append(count).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public Long getPriority() {		return priority;	}	public void setPriority(Long priority) {		this.priority = priority;	}	public Long getValidityHours() {		return validityHours;	}	public void setValidityHours(Long validityHours) {		this.validityHours = validityHours;	}	public Long getCount() {		return count;	}	public void setCount(Long count) {		this.count = count;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}
	
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
		MicroblogPushType other = (MicroblogPushType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
