package com.cnfantasia.server.domainbase.ebuyInvoice.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(发票) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyInvoice implements Serializable{
*/


public class EbuyInvoice extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 是否为默认发票 */	private Integer isdefault;	/** 发票抬头（公司名称） */	private String companyName;	/** 商品所属类别名称 */	private String productTypeName;	/**  */	private BigInteger tUserFId;	/**  */	private BigInteger tRoomFId;
	public EbuyInvoice(){
	}
	public EbuyInvoice(EbuyInvoice arg){
		this.id = arg.getId();		this.isdefault = arg.getIsdefault();		this.companyName = arg.getCompanyName();		this.productTypeName = arg.getProductTypeName();		this.tUserFId = arg.gettUserFId();		this.tRoomFId = arg.gettRoomFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param isdefault 是否为默认发票	 * @param companyName 发票抬头（公司名称）	 * @param productTypeName 商品所属类别名称	 * @param tUserFId 	 * @param tRoomFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyInvoice(BigInteger id,Integer isdefault,String companyName,String productTypeName,BigInteger tUserFId,BigInteger tRoomFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.isdefault = isdefault;		this.companyName = companyName;		this.productTypeName = productTypeName;		this.tUserFId = tUserFId;		this.tRoomFId = tRoomFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("isdefault=").append(isdefault).append(";");		sbf.append("companyName=").append(companyName).append(";");		sbf.append("productTypeName=").append(productTypeName).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tRoomFId=").append(tRoomFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getIsdefault() {		return isdefault;	}	public void setIsdefault(Integer isdefault) {		this.isdefault = isdefault;	}	public String getCompanyName() {		return companyName;	}	public void setCompanyName(String companyName) {		this.companyName = companyName;	}	public String getProductTypeName() {		return productTypeName;	}	public void setProductTypeName(String productTypeName) {		this.productTypeName = productTypeName;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettRoomFId() {		return tRoomFId;	}	public void settRoomFId(BigInteger tRoomFId) {		this.tRoomFId = tRoomFId;	}
	
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
		EbuyInvoice other = (EbuyInvoice) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
