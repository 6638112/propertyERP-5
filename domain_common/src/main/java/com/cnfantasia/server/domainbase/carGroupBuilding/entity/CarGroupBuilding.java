package com.cnfantasia.server.domainbase.carGroupBuilding.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;
import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小区车禁配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarGroupBuilding implements Serializable{
*/


public class CarGroupBuilding extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区id */	private BigInteger tGroupBuildingId;	/** x小区的业务编号 */	private String tradeCode;	/** 用户名 */
	private String account;
	/** 密码 */
	private String pwd;
	/** 小区对应的推送url */	private String pushUrl;
	/** 总停车位数 */
	private Long parkingTotal;
	/** 当前停车位数 */
	private Long parkingCrnt;
	public CarGroupBuilding(){
	}
	public CarGroupBuilding(CarGroupBuilding arg){
		this.id = arg.getId();		this.tGroupBuildingId = arg.gettGroupBuildingId();		this.tradeCode = arg.getTradeCode();		this.account = arg.getAccount();
		this.pwd = arg.getPwd();
		this.pushUrl = arg.getPushUrl();		this.parkingTotal = arg.getParkingTotal();
		this.parkingCrnt = arg.getParkingCrnt();
		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGroupBuildingId 小区id	 * @param tradeCode x小区的业务编号	 * @param account 用户名
	 * @param pwd 密码
	 * @param pushUrl 小区对应的推送url	 * @param parkingTotal 总停车位数
	 * @param parkingCrnt 当前停车位数
	 * @param sys0AddTime 购买时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CarGroupBuilding(BigInteger id,BigInteger tGroupBuildingId,String tradeCode,String account,String pwd,String pushUrl,Long parkingTotal,Long parkingCrnt,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGroupBuildingId = tGroupBuildingId;		this.tradeCode = tradeCode;		this.account = account;
		this.pwd = pwd;
		this.pushUrl = pushUrl;		this.parkingTotal = parkingTotal;
		this.parkingCrnt = parkingCrnt;
		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");		sbf.append("tradeCode=").append(tradeCode).append(";");		sbf.append("account=").append(account).append(";");
		sbf.append("pwd=").append(pwd).append(";");
		sbf.append("pushUrl=").append(pushUrl).append(";");		sbf.append("parkingTotal=").append(parkingTotal).append(";");
		sbf.append("parkingCrnt=").append(parkingCrnt).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGroupBuildingId() {		return tGroupBuildingId;	}	public void settGroupBuildingId(BigInteger tGroupBuildingId) {		this.tGroupBuildingId = tGroupBuildingId;	}	public String getTradeCode() {		return tradeCode;	}	public void setTradeCode(String tradeCode) {		this.tradeCode = tradeCode;	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	public String getPushUrl() {		return pushUrl;	}	public void setPushUrl(String pushUrl) {		this.pushUrl = pushUrl;	}
	public Long getParkingTotal() {
		return parkingTotal;
	}
	public void setParkingTotal(Long parkingTotal) {
		this.parkingTotal = parkingTotal;
	}
	public Long getParkingCrnt() {
		return parkingCrnt;
	}
	public void setParkingCrnt(Long parkingCrnt) {
		this.parkingCrnt = parkingCrnt;
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
		CarGroupBuilding other = (CarGroupBuilding) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
