package com.cnfantasia.server.domainbase.msPrizeGiftCode.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(抽奖奖品编码表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MsPrizeGiftCode implements Serializable{
*/


public class MsPrizeGiftCode extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属奖品Id */	private BigInteger priGiftId;	/** 标识码,用于作唯一性校验 */	private String uqMark;	/** 兑换码对应名称 */	private String codeName;	/** 价值码,例如兑换码 */	private String valueCode;
	public MsPrizeGiftCode(){
	}
	public MsPrizeGiftCode(MsPrizeGiftCode arg){
		this.id = arg.getId();		this.priGiftId = arg.getPriGiftId();		this.uqMark = arg.getUqMark();		this.codeName = arg.getCodeName();		this.valueCode = arg.getValueCode();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param priGiftId 所属奖品Id	 * @param uqMark 标识码,用于作唯一性校验	 * @param codeName 兑换码对应名称	 * @param valueCode 价值码,例如兑换码	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MsPrizeGiftCode(BigInteger id,BigInteger priGiftId,String uqMark,String codeName,String valueCode,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.priGiftId = priGiftId;		this.uqMark = uqMark;		this.codeName = codeName;		this.valueCode = valueCode;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("priGiftId=").append(priGiftId).append(";");		sbf.append("uqMark=").append(uqMark).append(";");		sbf.append("codeName=").append(codeName).append(";");		sbf.append("valueCode=").append(valueCode).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPriGiftId() {		return priGiftId;	}	public void setPriGiftId(BigInteger priGiftId) {		this.priGiftId = priGiftId;	}	public String getUqMark() {		return uqMark;	}	public void setUqMark(String uqMark) {		this.uqMark = uqMark;	}	public String getCodeName() {		return codeName;	}	public void setCodeName(String codeName) {		this.codeName = codeName;	}	public String getValueCode() {		return valueCode;	}	public void setValueCode(String valueCode) {		this.valueCode = valueCode;	}
	
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
		MsPrizeGiftCode other = (MsPrizeGiftCode) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
