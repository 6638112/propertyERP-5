package com.cnfantasia.server.domainbase.ebuyProductFightPic.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(拼购产品列表图片信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductFightPic implements Serializable{
*/


public class EbuyProductFightPic extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 大图地址 */	private String urlBig;	/** 小图地址 */	private String urlMini;	/**  */	private BigInteger tEbuyProductFId;
	public EbuyProductFightPic(){
	}
	public EbuyProductFightPic(EbuyProductFightPic arg){
		this.id = arg.getId();		this.urlBig = arg.getUrlBig();		this.urlMini = arg.getUrlMini();		this.tEbuyProductFId = arg.gettEbuyProductFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param urlBig 大图地址	 * @param urlMini 小图地址	 * @param tEbuyProductFId 	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public EbuyProductFightPic(BigInteger id,String urlBig,String urlMini,BigInteger tEbuyProductFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.urlBig = urlBig;		this.urlMini = urlMini;		this.tEbuyProductFId = tEbuyProductFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("urlBig=").append(urlBig).append(";");		sbf.append("urlMini=").append(urlMini).append(";");		sbf.append("tEbuyProductFId=").append(tEbuyProductFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getUrlBig() {		return urlBig;	}	public void setUrlBig(String urlBig) {		this.urlBig = urlBig;	}	public String getUrlMini() {		return urlMini;	}	public void setUrlMini(String urlMini) {		this.urlMini = urlMini;	}	public BigInteger gettEbuyProductFId() {		return tEbuyProductFId;	}	public void settEbuyProductFId(BigInteger tEbuyProductFId) {		this.tEbuyProductFId = tEbuyProductFId;	}
	
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
		EbuyProductFightPic other = (EbuyProductFightPic) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
