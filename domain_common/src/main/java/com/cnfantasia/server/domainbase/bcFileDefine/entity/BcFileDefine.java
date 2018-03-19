package com.cnfantasia.server.domainbase.bcFileDefine.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(出盘回盘文件格式定义) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BcFileDefine implements Serializable{
*/


public class BcFileDefine extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 排序字段 */	private Integer orderSeq;	/** 字段描述 */	private String fieldName;	/** 所占宽度 */	private Long width;	/** 对齐方式={"right" */	private String alignment;	/** 补全字符 */	private String fillChar;	/** 是否汇总文件列={0明细文件，1汇总文件} */	private Integer issumColumn;	/**  */	private BigInteger tBcFinanceOrgFId;
	public BcFileDefine(){
	}
	public BcFileDefine(BcFileDefine arg){
		this.id = arg.getId();		this.orderSeq = arg.getOrderSeq();		this.fieldName = arg.getFieldName();		this.width = arg.getWidth();		this.alignment = arg.getAlignment();		this.fillChar = arg.getFillChar();		this.issumColumn = arg.getIssumColumn();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tBcFinanceOrgFId = arg.gettBcFinanceOrgFId();
	}
	/**	 * 	 * @param id 	 * @param orderSeq 排序字段	 * @param fieldName 字段描述	 * @param width 所占宽度	 * @param alignment 对齐方式={"right"	 * @param fillChar 补全字符	 * @param issumColumn 是否汇总文件列={0明细文件，1汇总文件}	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tBcFinanceOrgFId 	 */
	public BcFileDefine(BigInteger id,Integer orderSeq,String fieldName,Long width,String alignment,String fillChar,Integer issumColumn,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tBcFinanceOrgFId){
		this.id = id;		this.orderSeq = orderSeq;		this.fieldName = fieldName;		this.width = width;		this.alignment = alignment;		this.fillChar = fillChar;		this.issumColumn = issumColumn;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tBcFinanceOrgFId = tBcFinanceOrgFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("orderSeq=").append(orderSeq).append(";");		sbf.append("fieldName=").append(fieldName).append(";");		sbf.append("width=").append(width).append(";");		sbf.append("alignment=").append(alignment).append(";");		sbf.append("fillChar=").append(fillChar).append(";");		sbf.append("issumColumn=").append(issumColumn).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tBcFinanceOrgFId=").append(tBcFinanceOrgFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getOrderSeq() {		return orderSeq;	}	public void setOrderSeq(Integer orderSeq) {		this.orderSeq = orderSeq;	}	public String getFieldName() {		return fieldName;	}	public void setFieldName(String fieldName) {		this.fieldName = fieldName;	}	public Long getWidth() {		return width;	}	public void setWidth(Long width) {		this.width = width;	}	public String getAlignment() {		return alignment;	}	public void setAlignment(String alignment) {		this.alignment = alignment;	}	public String getFillChar() {		return fillChar;	}	public void setFillChar(String fillChar) {		this.fillChar = fillChar;	}	public Integer getIssumColumn() {		return issumColumn;	}	public void setIssumColumn(Integer issumColumn) {		this.issumColumn = issumColumn;	}	public BigInteger gettBcFinanceOrgFId() {		return tBcFinanceOrgFId;	}	public void settBcFinanceOrgFId(BigInteger tBcFinanceOrgFId) {		this.tBcFinanceOrgFId = tBcFinanceOrgFId;	}
	
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
		BcFileDefine other = (BcFileDefine) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
