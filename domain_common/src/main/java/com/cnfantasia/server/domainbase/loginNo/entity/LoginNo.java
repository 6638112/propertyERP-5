package com.cnfantasia.server.domainbase.loginNo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户登录账号) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoginNo implements Serializable{
*/


public class LoginNo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账号 */	private String no;	/** 密码 */	private String password;	/** 账号类别 */	private Long type;	/** 校验级别 */	private Long valLevel;	/** 状态 */	private Long status;	/** 用户 */	private BigInteger tUserFId;	/** 密码备份 */	private String oldPwd;	/** 微信联合Id */	private String unionId;	/** 账号创建方式 */	private Integer createType;
	public LoginNo(){
	}
	public LoginNo(LoginNo arg){
		this.id = arg.getId();		this.no = arg.getNo();		this.password = arg.getPassword();		this.type = arg.getType();		this.valLevel = arg.getValLevel();		this.status = arg.getStatus();		this.tUserFId = arg.gettUserFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.oldPwd = arg.getOldPwd();		this.unionId = arg.getUnionId();		this.createType = arg.getCreateType();
	}
	/**	 * 	 * @param id 	 * @param no 账号	 * @param password 密码	 * @param type 账号类别	 * @param valLevel 校验级别	 * @param status 状态	 * @param tUserFId 用户	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param oldPwd 密码备份	 * @param unionId 微信联合Id	 * @param createType 账号创建方式	 */
	public LoginNo(BigInteger id,String no,String password,Long type,Long valLevel,Long status,BigInteger tUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String oldPwd,String unionId,Integer createType){
		this.id = id;		this.no = no;		this.password = password;		this.type = type;		this.valLevel = valLevel;		this.status = status;		this.tUserFId = tUserFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.oldPwd = oldPwd;		this.unionId = unionId;		this.createType = createType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("no=").append(no).append(";");		sbf.append("password=").append(password).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("valLevel=").append(valLevel).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("oldPwd=").append(oldPwd).append(";");		sbf.append("unionId=").append(unionId).append(";");		sbf.append("createType=").append(createType).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getNo() {		return no;	}	public void setNo(String no) {		this.no = no;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public Long getType() {		return type;	}	public void setType(Long type) {		this.type = type;	}	public Long getValLevel() {		return valLevel;	}	public void setValLevel(Long valLevel) {		this.valLevel = valLevel;	}	public Long getStatus() {		return status;	}	public void setStatus(Long status) {		this.status = status;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getOldPwd() {		return oldPwd;	}	public void setOldPwd(String oldPwd) {		this.oldPwd = oldPwd;	}	public String getUnionId() {		return unionId;	}	public void setUnionId(String unionId) {		this.unionId = unionId;	}	public Integer getCreateType() {		return createType;	}	public void setCreateType(Integer createType) {		this.createType = createType;	}
	
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
		LoginNo other = (LoginNo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
