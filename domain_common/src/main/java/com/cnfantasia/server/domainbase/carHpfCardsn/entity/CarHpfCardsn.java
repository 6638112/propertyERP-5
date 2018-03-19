package com.cnfantasia.server.domainbase.carHpfCardsn.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(华鹏飞临停车cardsn) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarHpfCardsn implements Serializable{
*/


public class CarHpfCardsn extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private String tCarnum;
	/**  */
	private String cardsn;

	public CarHpfCardsn(){
	}
	public CarHpfCardsn(CarHpfCardsn arg){
		this.id = arg.getId();
		this.tCarnum = arg.gettCarnum();
		this.cardsn = arg.getCardsn();
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
	 * @param tCarnum 
	 * @param cardsn 
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public CarHpfCardsn(BigInteger id,String tCarnum,String cardsn,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tCarnum = tCarnum;
		this.cardsn = cardsn;
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
		sbf.append("tCarnum=").append(tCarnum).append(";");
		sbf.append("cardsn=").append(cardsn).append(";");
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
	public String gettCarnum() {
		return tCarnum;
	}
	public void settCarnum(String tCarnum) {
		this.tCarnum = tCarnum;
	}
	public String getCardsn() {
		return cardsn;
	}
	public void setCardsn(String cardsn) {
		this.cardsn = cardsn;
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
		CarHpfCardsn other = (CarHpfCardsn) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
