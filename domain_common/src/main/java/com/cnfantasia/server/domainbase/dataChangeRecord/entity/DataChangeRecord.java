package com.cnfantasia.server.domainbase.dataChangeRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(数据变更记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DataChangeRecord implements Serializable{
*/


public class DataChangeRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键Id */	private BigInteger id;	/** 表名 */	private String tableName;	/** 数据的ID */	private BigInteger rowId;	/** 变更操作描述 */	private String operationDesc;	/** 变更部分原数据 */	private String dataFrom;	/** 变更部分变后数据 */	private String dataTo;
	public DataChangeRecord(){
	}
	public DataChangeRecord(DataChangeRecord arg){
		this.id = arg.getId();		this.tableName = arg.getTableName();		this.rowId = arg.getRowId();		this.operationDesc = arg.getOperationDesc();		this.dataFrom = arg.getDataFrom();		this.dataTo = arg.getDataTo();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 主键Id	 * @param tableName 表名	 * @param rowId 数据的ID	 * @param operationDesc 变更操作描述	 * @param dataFrom 变更部分原数据	 * @param dataTo 变更部分变后数据	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 更新者	 * @param sys0DelUser 删除者	 * @param sys0DelState 	 */
	public DataChangeRecord(BigInteger id,String tableName,BigInteger rowId,String operationDesc,String dataFrom,String dataTo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tableName = tableName;		this.rowId = rowId;		this.operationDesc = operationDesc;		this.dataFrom = dataFrom;		this.dataTo = dataTo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tableName=").append(tableName).append(";");		sbf.append("rowId=").append(rowId).append(";");		sbf.append("operationDesc=").append(operationDesc).append(";");		sbf.append("dataFrom=").append(dataFrom).append(";");		sbf.append("dataTo=").append(dataTo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTableName() {		return tableName;	}	public void setTableName(String tableName) {		this.tableName = tableName;	}	public BigInteger getRowId() {		return rowId;	}	public void setRowId(BigInteger rowId) {		this.rowId = rowId;	}	public String getOperationDesc() {		return operationDesc;	}	public void setOperationDesc(String operationDesc) {		this.operationDesc = operationDesc;	}	public String getDataFrom() {		return dataFrom;	}	public void setDataFrom(String dataFrom) {		this.dataFrom = dataFrom;	}	public String getDataTo() {		return dataTo;	}	public void setDataTo(String dataTo) {		this.dataTo = dataTo;	}
	
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
		DataChangeRecord other = (DataChangeRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
