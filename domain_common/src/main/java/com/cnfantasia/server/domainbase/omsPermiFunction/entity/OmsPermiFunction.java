package com.cnfantasia.server.domainbase.omsPermiFunction.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(OMS功能表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OmsPermiFunction implements Serializable{
*/


public class OmsPermiFunction extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 功能名称 */	private String name;	/** 功能描述 */	private String desc;	/** 功能入口 */	private String action;	/** 参数信息 */	private String param;	/** 功能状态 */	private Integer status;	/** 是否为菜单 */	private Integer menuFlag;	/** 功能级次，默认为根级（1级） */	private Integer level;	/** 父级功能菜单ID */	private BigInteger parnetid;	/** 编码 */	private String number;	/** 长编码，= 父级长编码!本级编码，用于菜单排序 */	private String longnumber;	/** 是否为叶子 */	private Integer isleaf;
	public OmsPermiFunction(){
	}
	public OmsPermiFunction(OmsPermiFunction arg){
		this.id = arg.getId();		this.name = arg.getName();		this.desc = arg.getDesc();		this.action = arg.getAction();		this.param = arg.getParam();		this.status = arg.getStatus();		this.menuFlag = arg.getMenuFlag();		this.level = arg.getLevel();		this.parnetid = arg.getParnetid();		this.number = arg.getNumber();		this.longnumber = arg.getLongnumber();		this.isleaf = arg.getIsleaf();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 功能名称	 * @param desc 功能描述	 * @param action 功能入口	 * @param param 参数信息	 * @param status 功能状态	 * @param menuFlag 是否为菜单	 * @param level 功能级次，默认为根级（1级）	 * @param parnetid 父级功能菜单ID	 * @param number 编码	 * @param longnumber 长编码，= 父级长编码!本级编码，用于菜单排序	 * @param isleaf 是否为叶子	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OmsPermiFunction(BigInteger id,String name,String desc,String action,String param,Integer status,Integer menuFlag,Integer level,BigInteger parnetid,String number,String longnumber,Integer isleaf,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.desc = desc;		this.action = action;		this.param = param;		this.status = status;		this.menuFlag = menuFlag;		this.level = level;		this.parnetid = parnetid;		this.number = number;		this.longnumber = longnumber;		this.isleaf = isleaf;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("action=").append(action).append(";");		sbf.append("param=").append(param).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("menuFlag=").append(menuFlag).append(";");		sbf.append("level=").append(level).append(";");		sbf.append("parnetid=").append(parnetid).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("longnumber=").append(longnumber).append(";");		sbf.append("isleaf=").append(isleaf).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getAction() {		return action;	}	public void setAction(String action) {		this.action = action;	}	public String getParam() {		return param;	}	public void setParam(String param) {		this.param = param;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getMenuFlag() {		return menuFlag;	}	public void setMenuFlag(Integer menuFlag) {		this.menuFlag = menuFlag;	}	public Integer getLevel() {		return level;	}	public void setLevel(Integer level) {		this.level = level;	}	public BigInteger getParnetid() {		return parnetid;	}	public void setParnetid(BigInteger parnetid) {		this.parnetid = parnetid;	}	public String getNumber() {		return number;	}	public void setNumber(String number) {		this.number = number;	}	public String getLongnumber() {		return longnumber;	}	public void setLongnumber(String longnumber) {		this.longnumber = longnumber;	}	public Integer getIsleaf() {		return isleaf;	}	public void setIsleaf(Integer isleaf) {		this.isleaf = isleaf;	}
	
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
		OmsPermiFunction other = (OmsPermiFunction) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
