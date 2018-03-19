package com.cnfantasia.server.domainbase.commUuid.entity;

/* import java.io.Serializable;*/
import java.lang.String;import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(唯一编号) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommUuid implements Serializable{
*/


public class CommUuid extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 表名 */	private String tableName;	/** 当前记录号 */	private BigInteger number;
	public CommUuid(){
	}
	public CommUuid(CommUuid arg){
		this.tableName = arg.getTableName();		this.number = arg.getNumber();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param tableName 表名	 * @param number 当前记录号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommUuid(String tableName,BigInteger number,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.tableName = tableName;		this.number = number;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("tableName=").append(tableName).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public String getTableName() {		return tableName;	}	public void setTableName(String tableName) {		this.tableName = tableName;	}	public BigInteger getNumber() {		return number;	}	public void setNumber(BigInteger number) {		this.number = number;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		CommUuid other = (CommUuid) obj;
		if (tableName == null) {			if (other.tableName != null)				return false;		} else if (!tableName.equals(other.tableName))			return false;
		return true;
	}
	
}
