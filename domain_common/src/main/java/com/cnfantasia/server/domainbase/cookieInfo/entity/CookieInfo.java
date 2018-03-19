package com.cnfantasia.server.domainbase.cookieInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(记录用户的Cookie) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CookieInfo implements Serializable{
*/


public class CookieInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private String hmsr;	/**  */	private String hmmd;	/**  */	private String hmpl;	/**  */	private String hmkw;	/**  */	private String hmci;	/** 申请注册物业公司的ID */	private BigInteger tPropertyCompanyFId;	/** 所对应后台管理账号id */	private BigInteger tOmsUserFId;
	public CookieInfo(){
	}
	public CookieInfo(CookieInfo arg){
		this.id = arg.getId();		this.hmsr = arg.getHmsr();		this.hmmd = arg.getHmmd();		this.hmpl = arg.getHmpl();		this.hmkw = arg.getHmkw();		this.hmci = arg.getHmci();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.tOmsUserFId = arg.gettOmsUserFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param hmsr 	 * @param hmmd 	 * @param hmpl 	 * @param hmkw 	 * @param hmci 	 * @param tPropertyCompanyFId 申请注册物业公司的ID	 * @param tOmsUserFId 所对应后台管理账号id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CookieInfo(BigInteger id,String hmsr,String hmmd,String hmpl,String hmkw,String hmci,BigInteger tPropertyCompanyFId,BigInteger tOmsUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.hmsr = hmsr;		this.hmmd = hmmd;		this.hmpl = hmpl;		this.hmkw = hmkw;		this.hmci = hmci;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.tOmsUserFId = tOmsUserFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("hmsr=").append(hmsr).append(";");		sbf.append("hmmd=").append(hmmd).append(";");		sbf.append("hmpl=").append(hmpl).append(";");		sbf.append("hmkw=").append(hmkw).append(";");		sbf.append("hmci=").append(hmci).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("tOmsUserFId=").append(tOmsUserFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getHmsr() {		return hmsr;	}	public void setHmsr(String hmsr) {		this.hmsr = hmsr;	}	public String getHmmd() {		return hmmd;	}	public void setHmmd(String hmmd) {		this.hmmd = hmmd;	}	public String getHmpl() {		return hmpl;	}	public void setHmpl(String hmpl) {		this.hmpl = hmpl;	}	public String getHmkw() {		return hmkw;	}	public void setHmkw(String hmkw) {		this.hmkw = hmkw;	}	public String getHmci() {		return hmci;	}	public void setHmci(String hmci) {		this.hmci = hmci;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}	public BigInteger gettOmsUserFId() {		return tOmsUserFId;	}	public void settOmsUserFId(BigInteger tOmsUserFId) {		this.tOmsUserFId = tOmsUserFId;	}
	
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
		CookieInfo other = (CookieInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
