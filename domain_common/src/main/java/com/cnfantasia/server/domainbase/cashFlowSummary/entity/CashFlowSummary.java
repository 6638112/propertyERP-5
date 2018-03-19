package com.cnfantasia.server.domainbase.cashFlowSummary.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(现金流量汇总表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CashFlowSummary implements Serializable{
*/


public class CashFlowSummary extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tRealRoomId;
	/** 所在省 */
	private String provinceName;
	/** 所在市 */
	private String cityName;
	/** 小区id */
	private BigInteger tGroupBuildingId;
	/** 小区名称 */
	private String gbName;
	/** 楼栋id */
	private BigInteger tBuildingId;
	/** 楼栋名称 */
	private String buildingName;
	/** 单元名称 */
	private String unit;
	/** 房间号 */
	private String roomNum;
	/** 金额（单位 */
	private Long amount;
	/**  */
	private String srcBillId;
	/** 业务类型 */
	private Integer billType;
	/** 业务时间 */
	private String billTime;

	public CashFlowSummary(){
	}
	public CashFlowSummary(CashFlowSummary arg){
		this.id = arg.getId();
		this.tRealRoomId = arg.gettRealRoomId();
		this.provinceName = arg.getProvinceName();
		this.cityName = arg.getCityName();
		this.tGroupBuildingId = arg.gettGroupBuildingId();
		this.gbName = arg.getGbName();
		this.tBuildingId = arg.gettBuildingId();
		this.buildingName = arg.getBuildingName();
		this.unit = arg.getUnit();
		this.roomNum = arg.getRoomNum();
		this.amount = arg.getAmount();
		this.srcBillId = arg.getSrcBillId();
		this.billType = arg.getBillType();
		this.billTime = arg.getBillTime();
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
	 * @param tRealRoomId 
	 * @param provinceName 所在省
	 * @param cityName 所在市
	 * @param tGroupBuildingId 小区id
	 * @param gbName 小区名称
	 * @param tBuildingId 楼栋id
	 * @param buildingName 楼栋名称
	 * @param unit 单元名称
	 * @param roomNum 房间号
	 * @param amount 金额（单位
	 * @param srcBillId 
	 * @param billType 业务类型
	 * @param billTime 业务时间
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public CashFlowSummary(BigInteger id,BigInteger tRealRoomId,String provinceName,String cityName,BigInteger tGroupBuildingId,String gbName,BigInteger tBuildingId,String buildingName,String unit,String roomNum,Long amount,String srcBillId,Integer billType,String billTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tRealRoomId = tRealRoomId;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.tGroupBuildingId = tGroupBuildingId;
		this.gbName = gbName;
		this.tBuildingId = tBuildingId;
		this.buildingName = buildingName;
		this.unit = unit;
		this.roomNum = roomNum;
		this.amount = amount;
		this.srcBillId = srcBillId;
		this.billType = billType;
		this.billTime = billTime;
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
		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");
		sbf.append("provinceName=").append(provinceName).append(";");
		sbf.append("cityName=").append(cityName).append(";");
		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");
		sbf.append("gbName=").append(gbName).append(";");
		sbf.append("tBuildingId=").append(tBuildingId).append(";");
		sbf.append("buildingName=").append(buildingName).append(";");
		sbf.append("unit=").append(unit).append(";");
		sbf.append("roomNum=").append(roomNum).append(";");
		sbf.append("amount=").append(amount).append(";");
		sbf.append("srcBillId=").append(srcBillId).append(";");
		sbf.append("billType=").append(billType).append(";");
		sbf.append("billTime=").append(billTime).append(";");
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
	public BigInteger gettRealRoomId() {
		return tRealRoomId;
	}
	public void settRealRoomId(BigInteger tRealRoomId) {
		this.tRealRoomId = tRealRoomId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public BigInteger gettGroupBuildingId() {
		return tGroupBuildingId;
	}
	public void settGroupBuildingId(BigInteger tGroupBuildingId) {
		this.tGroupBuildingId = tGroupBuildingId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public BigInteger gettBuildingId() {
		return tBuildingId;
	}
	public void settBuildingId(BigInteger tBuildingId) {
		this.tBuildingId = tBuildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getSrcBillId() {
		return srcBillId;
	}
	public void setSrcBillId(String srcBillId) {
		this.srcBillId = srcBillId;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
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
		CashFlowSummary other = (CashFlowSummary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
