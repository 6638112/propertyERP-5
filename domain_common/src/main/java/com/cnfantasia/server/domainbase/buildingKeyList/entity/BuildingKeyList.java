package com.cnfantasia.server.domainbase.buildingKeyList.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(开通门禁的小区楼栋列表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BuildingKeyList implements Serializable{
*/


public class BuildingKeyList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 楼栋Id */
	private BigInteger tBuildingFId;
	/** 小区Id */
	private BigInteger tGroupBuildingFId;
	/** 楼栋密钥(一个楼栋k可以两个) */
	private String tBuildingKeyStr;
	/** 远程开门开关 */
	private Integer isOpenRemote;
	/** 走进开门开关 */
	private Integer isOpenLocal;
	/** 芝麻门禁对应的第三方小区id */
	private String tGbId;
	/** 门禁状态 */
	private Integer status;
	/** 供应商类型 */
	private Integer type;
	/** 门禁名字，如“1栋1单元停车场” */
	private String doorName;

	public BuildingKeyList(){
	}
	public BuildingKeyList(BuildingKeyList arg){
		this.id = arg.getId();
		this.tBuildingFId = arg.gettBuildingFId();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.tBuildingKeyStr = arg.gettBuildingKeyStr();
		this.isOpenRemote = arg.getIsOpenRemote();
		this.isOpenLocal = arg.getIsOpenLocal();
		this.tGbId = arg.gettGbId();
		this.status = arg.getStatus();
		this.type = arg.getType();
		this.doorName = arg.getDoorName();
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
	 * @param tBuildingFId 楼栋Id
	 * @param tGroupBuildingFId 小区Id
	 * @param tBuildingKeyStr 楼栋密钥(一个楼栋k可以两个)
	 * @param isOpenRemote 远程开门开关
	 * @param isOpenLocal 走进开门开关
	 * @param tGbId 芝麻门禁对应的第三方小区id
	 * @param status 门禁状态
	 * @param type 供应商类型
	 * @param doorName 门禁名字，如“1栋1单元停车场”
	 * @param sys0AddTime 申请时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public BuildingKeyList(BigInteger id,BigInteger tBuildingFId,BigInteger tGroupBuildingFId,String tBuildingKeyStr,Integer isOpenRemote,Integer isOpenLocal,String tGbId,Integer status,Integer type,String doorName,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tBuildingFId = tBuildingFId;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.tBuildingKeyStr = tBuildingKeyStr;
		this.isOpenRemote = isOpenRemote;
		this.isOpenLocal = isOpenLocal;
		this.tGbId = tGbId;
		this.status = status;
		this.type = type;
		this.doorName = doorName;
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
		sbf.append("tBuildingFId=").append(tBuildingFId).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("tBuildingKeyStr=").append(tBuildingKeyStr).append(";");
		sbf.append("isOpenRemote=").append(isOpenRemote).append(";");
		sbf.append("isOpenLocal=").append(isOpenLocal).append(";");
		sbf.append("tGbId=").append(tGbId).append(";");
		sbf.append("status=").append(status).append(";");
		sbf.append("type=").append(type).append(";");
		sbf.append("doorName=").append(doorName).append(";");
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
	public BigInteger gettBuildingFId() {
		return tBuildingFId;
	}
	public void settBuildingFId(BigInteger tBuildingFId) {
		this.tBuildingFId = tBuildingFId;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public String gettBuildingKeyStr() {
		return tBuildingKeyStr;
	}
	public void settBuildingKeyStr(String tBuildingKeyStr) {
		this.tBuildingKeyStr = tBuildingKeyStr;
	}
	public Integer getIsOpenRemote() {
		return isOpenRemote;
	}
	public void setIsOpenRemote(Integer isOpenRemote) {
		this.isOpenRemote = isOpenRemote;
	}
	public Integer getIsOpenLocal() {
		return isOpenLocal;
	}
	public void setIsOpenLocal(Integer isOpenLocal) {
		this.isOpenLocal = isOpenLocal;
	}
	public String gettGbId() {
		return tGbId;
	}
	public void settGbId(String tGbId) {
		this.tGbId = tGbId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDoorName() {
		return doorName;
	}
	public void setDoorName(String doorName) {
		this.doorName = doorName;
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
		BuildingKeyList other = (BuildingKeyList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
