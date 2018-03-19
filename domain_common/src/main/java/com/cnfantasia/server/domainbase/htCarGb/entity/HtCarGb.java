package com.cnfantasia.server.domainbase.htCarGb.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class HtCarGb implements Serializable{
*/


public class HtCarGb extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tGroupBuildingFId;
	/** 华庭车禁对应的停车场编号 */
	private String tParkid;
	/** 编码，用于区分第三方 */
	private String code;
	/** 管理公司id */
	private String comid;
	/** 用于停车场排序 */
	private BigInteger order;
	/** 停车场名称 */
	private String plotName;

	public HtCarGb(){
	}
	public HtCarGb(HtCarGb arg){
		this.id = arg.getId();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.tParkid = arg.gettParkid();
		this.code = arg.getCode();
		this.comid = arg.getComid();
		this.order = arg.getOrder();
		this.plotName = arg.getPlotName();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tGroupBuildingFId 
	 * @param tParkid 华庭车禁对应的停车场编号
	 * @param code 编码，用于区分第三方
	 * @param comid 管理公司id
	 * @param order 用于停车场排序
	 * @param plotName 停车场名称
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public HtCarGb(BigInteger id,BigInteger tGroupBuildingFId,String tParkid,String code,String comid,BigInteger order,String plotName,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.tParkid = tParkid;
		this.code = code;
		this.comid = comid;
		this.order = order;
		this.plotName = plotName;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("tParkid=").append(tParkid).append(";");
		sbf.append("code=").append(code).append(";");
		sbf.append("comid=").append(comid).append(";");
		sbf.append("order=").append(order).append(";");
		sbf.append("plotName=").append(plotName).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public String gettParkid() {
		return tParkid;
	}
	public void settParkid(String tParkid) {
		this.tParkid = tParkid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public BigInteger getOrder() {
		return order;
	}
	public void setOrder(BigInteger order) {
		this.order = order;
	}
	public String getPlotName() {
		return plotName;
	}
	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	
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
		HtCarGb other = (HtCarGb) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
