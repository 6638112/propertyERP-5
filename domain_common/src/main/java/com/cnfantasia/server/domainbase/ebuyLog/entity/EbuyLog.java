package com.cnfantasia.server.domainbase.ebuyLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(扫二维码进商品详情日志表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyLog implements Serializable{
*/


public class EbuyLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 商品Id */	private BigInteger productId;	/**  */	private BigInteger userId;	/**  */	private String utmSource;	/**  */	private String utmMedium;	/**  */	private String utmContent;	/**  */	private String utmCampaingn;
	public EbuyLog(){
	}
	public EbuyLog(EbuyLog arg){
		this.id = arg.getId();		this.productId = arg.getProductId();		this.userId = arg.getUserId();		this.utmSource = arg.getUtmSource();		this.utmMedium = arg.getUtmMedium();		this.utmContent = arg.getUtmContent();		this.utmCampaingn = arg.getUtmCampaingn();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param productId 商品Id	 * @param userId 	 * @param utmSource 	 * @param utmMedium 	 * @param utmContent 	 * @param utmCampaingn 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyLog(BigInteger id,BigInteger productId,BigInteger userId,String utmSource,String utmMedium,String utmContent,String utmCampaingn,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.productId = productId;		this.userId = userId;		this.utmSource = utmSource;		this.utmMedium = utmMedium;		this.utmContent = utmContent;		this.utmCampaingn = utmCampaingn;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("productId=").append(productId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("utmSource=").append(utmSource).append(";");		sbf.append("utmMedium=").append(utmMedium).append(";");		sbf.append("utmContent=").append(utmContent).append(";");		sbf.append("utmCampaingn=").append(utmCampaingn).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getProductId() {		return productId;	}	public void setProductId(BigInteger productId) {		this.productId = productId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getUtmSource() {		return utmSource;	}	public void setUtmSource(String utmSource) {		this.utmSource = utmSource;	}	public String getUtmMedium() {		return utmMedium;	}	public void setUtmMedium(String utmMedium) {		this.utmMedium = utmMedium;	}	public String getUtmContent() {		return utmContent;	}	public void setUtmContent(String utmContent) {		this.utmContent = utmContent;	}	public String getUtmCampaingn() {		return utmCampaingn;	}	public void setUtmCampaingn(String utmCampaingn) {		this.utmCampaingn = utmCampaingn;	}
	
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
		EbuyLog other = (EbuyLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
