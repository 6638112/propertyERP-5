package com.cnfantasia.server.domainbase.encyptLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(加密日志记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EncyptLog implements Serializable{
*/


public class EncyptLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 待加密字符串 */	private String srcStr;	/** 加密类型 */	private Integer signType;	/** 客户端上传的加密结果 */	private String signParam;	/** 服务端加密结果 */	private String signServer;	/** 错误信息 */	private String errInfo;
	public EncyptLog(){
	}
	public EncyptLog(EncyptLog arg){
		this.id = arg.getId();		this.srcStr = arg.getSrcStr();		this.signType = arg.getSignType();		this.signParam = arg.getSignParam();		this.signServer = arg.getSignServer();		this.errInfo = arg.getErrInfo();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param srcStr 待加密字符串	 * @param signType 加密类型	 * @param signParam 客户端上传的加密结果	 * @param signServer 服务端加密结果	 * @param errInfo 错误信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EncyptLog(BigInteger id,String srcStr,Integer signType,String signParam,String signServer,String errInfo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.srcStr = srcStr;		this.signType = signType;		this.signParam = signParam;		this.signServer = signServer;		this.errInfo = errInfo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("srcStr=").append(srcStr).append(";");		sbf.append("signType=").append(signType).append(";");		sbf.append("signParam=").append(signParam).append(";");		sbf.append("signServer=").append(signServer).append(";");		sbf.append("errInfo=").append(errInfo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getSrcStr() {		return srcStr;	}	public void setSrcStr(String srcStr) {		this.srcStr = srcStr;	}	public Integer getSignType() {		return signType;	}	public void setSignType(Integer signType) {		this.signType = signType;	}	public String getSignParam() {		return signParam;	}	public void setSignParam(String signParam) {		this.signParam = signParam;	}	public String getSignServer() {		return signServer;	}	public void setSignServer(String signServer) {		this.signServer = signServer;	}	public String getErrInfo() {		return errInfo;	}	public void setErrInfo(String errInfo) {		this.errInfo = errInfo;	}
	
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
		EncyptLog other = (EncyptLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
