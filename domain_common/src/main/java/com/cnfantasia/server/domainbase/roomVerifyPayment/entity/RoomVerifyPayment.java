package com.cnfantasia.server.domainbase.roomVerifyPayment.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(门牌验证缴费情况查询表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class RoomVerifyPayment implements Serializable{
/* */

/* 
public class RoomVerifyPayment extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger tRealRoomId;
	/** 省 */
	private BigInteger tAddressProvinceId;
	/** 省（冗余） */
	private String province;
	/** 市 */
	private BigInteger tAddressCityId;
	/** 市（冗余） */
	private String city;
	/** 小区 */
	private BigInteger tGroupBuildingId;
	/** 小区（冗余） */
	private String gbName;
	/** 楼栋 */
	private BigInteger tBuildingId;
	/** 楼栋（冗余） */
	private String buildingName;
	/** 单元 */
	private String unit;
	/** 房间号 */
	private String roomNum;
	/** 注册状态 */
	private Integer registerState;
	/** 缴费状态 */
	private Integer payState;
	/** 缴费时间 */
	private String payTime;

	public RoomVerifyPayment(){
	}
	public RoomVerifyPayment(RoomVerifyPayment arg){
		this.id = arg.getId();
		this.tRealRoomId = arg.gettRealRoomId();
		this.tAddressProvinceId = arg.gettAddressProvinceId();
		this.province = arg.getProvince();
		this.tAddressCityId = arg.gettAddressCityId();
		this.city = arg.getCity();
		this.tGroupBuildingId = arg.gettGroupBuildingId();
		this.gbName = arg.getGbName();
		this.tBuildingId = arg.gettBuildingId();
		this.buildingName = arg.getBuildingName();
		this.unit = arg.getUnit();
		this.roomNum = arg.getRoomNum();
		this.registerState = arg.getRegisterState();
		this.payState = arg.getPayState();
		this.payTime = arg.getPayTime();

	}
	/**
	 * 
	 * @param id 
	 * @param tRealRoomId 
	 * @param tAddressProvinceId 省
	 * @param province 省（冗余）
	 * @param tAddressCityId 市
	 * @param city 市（冗余）
	 * @param tGroupBuildingId 小区
	 * @param gbName 小区（冗余）
	 * @param tBuildingId 楼栋
	 * @param buildingName 楼栋（冗余）
	 * @param unit 单元
	 * @param roomNum 房间号
	 * @param registerState 注册状态
	 * @param payState 缴费状态
	 * @param payTime 缴费时间
	 */

	public RoomVerifyPayment(BigInteger id,BigInteger tRealRoomId,BigInteger tAddressProvinceId,String province,BigInteger tAddressCityId,String city,BigInteger tGroupBuildingId,String gbName,BigInteger tBuildingId,String buildingName,String unit,String roomNum,Integer registerState,Integer payState,String payTime){
		this.id = id;
		this.tRealRoomId = tRealRoomId;
		this.tAddressProvinceId = tAddressProvinceId;
		this.province = province;
		this.tAddressCityId = tAddressCityId;
		this.city = city;
		this.tGroupBuildingId = tGroupBuildingId;
		this.gbName = gbName;
		this.tBuildingId = tBuildingId;
		this.buildingName = buildingName;
		this.unit = unit;
		this.roomNum = roomNum;
		this.registerState = registerState;
		this.payState = payState;
		this.payTime = payTime;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");
		sbf.append("tAddressProvinceId=").append(tAddressProvinceId).append(";");
		sbf.append("province=").append(province).append(";");
		sbf.append("tAddressCityId=").append(tAddressCityId).append(";");
		sbf.append("city=").append(city).append(";");
		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");
		sbf.append("gbName=").append(gbName).append(";");
		sbf.append("tBuildingId=").append(tBuildingId).append(";");
		sbf.append("buildingName=").append(buildingName).append(";");
		sbf.append("unit=").append(unit).append(";");
		sbf.append("roomNum=").append(roomNum).append(";");
		sbf.append("registerState=").append(registerState).append(";");
		sbf.append("payState=").append(payState).append(";");
		sbf.append("payTime=").append(payTime).append(";");
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
	public BigInteger gettAddressProvinceId() {
		return tAddressProvinceId;
	}
	public void settAddressProvinceId(BigInteger tAddressProvinceId) {
		this.tAddressProvinceId = tAddressProvinceId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public BigInteger gettAddressCityId() {
		return tAddressCityId;
	}
	public void settAddressCityId(BigInteger tAddressCityId) {
		this.tAddressCityId = tAddressCityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Integer getRegisterState() {
		return registerState;
	}
	public void setRegisterState(Integer registerState) {
		this.registerState = registerState;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
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
		RoomVerifyPayment other = (RoomVerifyPayment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
