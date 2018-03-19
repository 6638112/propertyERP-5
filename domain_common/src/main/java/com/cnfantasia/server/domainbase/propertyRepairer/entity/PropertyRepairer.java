package com.cnfantasia.server.domainbase.propertyRepairer.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(管理处维修工) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyRepairer implements Serializable{
*/


public class PropertyRepairer extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 身份证号 */	private String idNo;	/** 身份证正面 */	private String idNumberPic1;	/** 身份证反面 */	private String idNumberPic2;	/** 维修工电话 */	private String tel;	/** 名称 */	private String name;	/** 头像 */	private String headimgurl;	/**  */	private BigInteger tPropertyManagementFId;	/**  */	private BigInteger tPropertyRepairTypeFId;	/** 对应的解放区用户 */	private BigInteger tUserFId;
	public PropertyRepairer(){
	}
	public PropertyRepairer(PropertyRepairer arg){
		this.id = arg.getId();		this.idNo = arg.getIdNo();		this.idNumberPic1 = arg.getIdNumberPic1();		this.idNumberPic2 = arg.getIdNumberPic2();		this.tel = arg.getTel();		this.name = arg.getName();		this.headimgurl = arg.getHeadimgurl();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();		this.tPropertyRepairTypeFId = arg.gettPropertyRepairTypeFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tUserFId = arg.gettUserFId();
	}
	/**	 * 	 * @param id 	 * @param idNo 身份证号	 * @param idNumberPic1 身份证正面	 * @param idNumberPic2 身份证反面	 * @param tel 维修工电话	 * @param name 名称	 * @param headimgurl 头像	 * @param tPropertyManagementFId 	 * @param tPropertyRepairTypeFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tUserFId 对应的解放区用户	 */
	public PropertyRepairer(BigInteger id,String idNo,String idNumberPic1,String idNumberPic2,String tel,String name,String headimgurl,BigInteger tPropertyManagementFId,BigInteger tPropertyRepairTypeFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tUserFId){
		this.id = id;		this.idNo = idNo;		this.idNumberPic1 = idNumberPic1;		this.idNumberPic2 = idNumberPic2;		this.tel = tel;		this.name = name;		this.headimgurl = headimgurl;		this.tPropertyManagementFId = tPropertyManagementFId;		this.tPropertyRepairTypeFId = tPropertyRepairTypeFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tUserFId = tUserFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("idNo=").append(idNo).append(";");		sbf.append("idNumberPic1=").append(idNumberPic1).append(";");		sbf.append("idNumberPic2=").append(idNumberPic2).append(";");		sbf.append("tel=").append(tel).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("headimgurl=").append(headimgurl).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		sbf.append("tPropertyRepairTypeFId=").append(tPropertyRepairTypeFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getIdNo() {		return idNo;	}	public void setIdNo(String idNo) {		this.idNo = idNo;	}	public String getIdNumberPic1() {		return idNumberPic1;	}	public void setIdNumberPic1(String idNumberPic1) {		this.idNumberPic1 = idNumberPic1;	}	public String getIdNumberPic2() {		return idNumberPic2;	}	public void setIdNumberPic2(String idNumberPic2) {		this.idNumberPic2 = idNumberPic2;	}	public String getTel() {		return tel;	}	public void setTel(String tel) {		this.tel = tel;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getHeadimgurl() {		return headimgurl;	}	public void setHeadimgurl(String headimgurl) {		this.headimgurl = headimgurl;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}	public BigInteger gettPropertyRepairTypeFId() {		return tPropertyRepairTypeFId;	}	public void settPropertyRepairTypeFId(BigInteger tPropertyRepairTypeFId) {		this.tPropertyRepairTypeFId = tPropertyRepairTypeFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}
	
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
		PropertyRepairer other = (PropertyRepairer) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
