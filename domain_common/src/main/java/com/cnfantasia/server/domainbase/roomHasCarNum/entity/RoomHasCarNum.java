package com.cnfantasia.server.domainbase.roomHasCarNum.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Integer;
import java.lang.String;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(未认证人士的绑定表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RoomHasCarNum implements Serializable{
*/


public class RoomHasCarNum extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 房间id(已不用) */
	private BigInteger tRealRoomId;
	/** 用户id */
	private BigInteger tUserId;
	/** 车牌表id */
	private BigInteger tCarNumListFId;
	/** 状态 */
	private Integer status;
	/** 真实姓名 */
	private String realName;
	/** 手机号 */
	private String cellphone;
	/** 身份证号 */
	private String idCardNo;
	/** 解除绑定状态 */
	private Integer isRelieve;

	public RoomHasCarNum(){
	}
	public RoomHasCarNum(RoomHasCarNum arg){
		this.id = arg.getId();
		this.tRealRoomId = arg.gettRealRoomId();
		this.tUserId = arg.gettUserId();
		this.tCarNumListFId = arg.gettCarNumListFId();
		this.status = arg.getStatus();
		this.realName = arg.getRealName();
		this.cellphone = arg.getCellphone();
		this.idCardNo = arg.getIdCardNo();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.isRelieve = arg.getIsRelieve();

	}
	/**
	 * 
	 * @param id 
	 * @param tRealRoomId 房间id(已不用)
	 * @param tUserId 用户id
	 * @param tCarNumListFId 车牌表id
	 * @param status 状态
	 * @param realName 真实姓名
	 * @param cellphone 手机号
	 * @param idCardNo 身份证号
	 * @param sys0AddTime 申请时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param isRelieve 解除绑定状态
	 */

	public RoomHasCarNum(BigInteger id,BigInteger tRealRoomId,BigInteger tUserId,BigInteger tCarNumListFId,Integer status,String realName,String cellphone,String idCardNo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer isRelieve){
		this.id = id;
		this.tRealRoomId = tRealRoomId;
		this.tUserId = tUserId;
		this.tCarNumListFId = tCarNumListFId;
		this.status = status;
		this.realName = realName;
		this.cellphone = cellphone;
		this.idCardNo = idCardNo;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.isRelieve = isRelieve;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");
		sbf.append("tUserId=").append(tUserId).append(";");
		sbf.append("tCarNumListFId=").append(tCarNumListFId).append(";");
		sbf.append("status=").append(status).append(";");
		sbf.append("realName=").append(realName).append(";");
		sbf.append("cellphone=").append(cellphone).append(";");
		sbf.append("idCardNo=").append(idCardNo).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("isRelieve=").append(isRelieve).append(";");
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
	public BigInteger gettUserId() {
		return tUserId;
	}
	public void settUserId(BigInteger tUserId) {
		this.tUserId = tUserId;
	}
	public BigInteger gettCarNumListFId() {
		return tCarNumListFId;
	}
	public void settCarNumListFId(BigInteger tCarNumListFId) {
		this.tCarNumListFId = tCarNumListFId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public Integer getIsRelieve() {
		return isRelieve;
	}
	public void setIsRelieve(Integer isRelieve) {
		this.isRelieve = isRelieve;
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
		RoomHasCarNum other = (RoomHasCarNum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
