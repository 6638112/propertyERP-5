package com.cnfantasia.server.domainbase.dredgeProduct.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修服务商品表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeProduct implements Serializable{
*/


public class DredgeProduct extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/** 描述 */	private String desc;	/** 商品图，分号隔开 */	private String productPic;	/** 介绍图，分号隔开 */	private String introducePic;	/** 二级维修类型ID */	private BigInteger dredgeType2ndId;	/** 运营数据类型 */	private Integer payType;	/** 状态，1上架2下架 */	private Integer status;	/** 服务供应商 */	private BigInteger serviceSupplierId;	/** 分享图片 */	private String sharePic;	/** 分享推送图片 */	private String sharePushPic;	/** 分享朋友标题 */	private String shareFriendTitle;	/** 分享朋友圈标题 */	private String shareCycleTitle;	/** 分享内容 */	private String shareContent;
	public DredgeProduct(){
	}
	public DredgeProduct(DredgeProduct arg){
		this.id = arg.getId();		this.name = arg.getName();		this.desc = arg.getDesc();		this.productPic = arg.getProductPic();		this.introducePic = arg.getIntroducePic();		this.dredgeType2ndId = arg.getDredgeType2ndId();		this.payType = arg.getPayType();		this.status = arg.getStatus();		this.serviceSupplierId = arg.getServiceSupplierId();		this.sharePic = arg.getSharePic();		this.sharePushPic = arg.getSharePushPic();		this.shareFriendTitle = arg.getShareFriendTitle();		this.shareCycleTitle = arg.getShareCycleTitle();		this.shareContent = arg.getShareContent();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param desc 描述	 * @param productPic 商品图，分号隔开	 * @param introducePic 介绍图，分号隔开	 * @param dredgeType2ndId 二级维修类型ID	 * @param payType 运营数据类型	 * @param status 状态，1上架2下架	 * @param serviceSupplierId 服务供应商	 * @param sharePic 分享图片	 * @param sharePushPic 分享推送图片	 * @param shareFriendTitle 分享朋友标题	 * @param shareCycleTitle 分享朋友圈标题	 * @param shareContent 分享内容	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeProduct(BigInteger id,String name,String desc,String productPic,String introducePic,BigInteger dredgeType2ndId,Integer payType,Integer status,BigInteger serviceSupplierId,String sharePic,String sharePushPic,String shareFriendTitle,String shareCycleTitle,String shareContent,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.desc = desc;		this.productPic = productPic;		this.introducePic = introducePic;		this.dredgeType2ndId = dredgeType2ndId;		this.payType = payType;		this.status = status;		this.serviceSupplierId = serviceSupplierId;		this.sharePic = sharePic;		this.sharePushPic = sharePushPic;		this.shareFriendTitle = shareFriendTitle;		this.shareCycleTitle = shareCycleTitle;		this.shareContent = shareContent;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("productPic=").append(productPic).append(";");		sbf.append("introducePic=").append(introducePic).append(";");		sbf.append("dredgeType2ndId=").append(dredgeType2ndId).append(";");		sbf.append("payType=").append(payType).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("serviceSupplierId=").append(serviceSupplierId).append(";");		sbf.append("sharePic=").append(sharePic).append(";");		sbf.append("sharePushPic=").append(sharePushPic).append(";");		sbf.append("shareFriendTitle=").append(shareFriendTitle).append(";");		sbf.append("shareCycleTitle=").append(shareCycleTitle).append(";");		sbf.append("shareContent=").append(shareContent).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getProductPic() {		return productPic;	}	public void setProductPic(String productPic) {		this.productPic = productPic;	}	public String getIntroducePic() {		return introducePic;	}	public void setIntroducePic(String introducePic) {		this.introducePic = introducePic;	}	public BigInteger getDredgeType2ndId() {		return dredgeType2ndId;	}	public void setDredgeType2ndId(BigInteger dredgeType2ndId) {		this.dredgeType2ndId = dredgeType2ndId;	}	public Integer getPayType() {		return payType;	}	public void setPayType(Integer payType) {		this.payType = payType;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public BigInteger getServiceSupplierId() {		return serviceSupplierId;	}	public void setServiceSupplierId(BigInteger serviceSupplierId) {		this.serviceSupplierId = serviceSupplierId;	}	public String getSharePic() {		return sharePic;	}	public void setSharePic(String sharePic) {		this.sharePic = sharePic;	}	public String getSharePushPic() {		return sharePushPic;	}	public void setSharePushPic(String sharePushPic) {		this.sharePushPic = sharePushPic;	}	public String getShareFriendTitle() {		return shareFriendTitle;	}	public void setShareFriendTitle(String shareFriendTitle) {		this.shareFriendTitle = shareFriendTitle;	}	public String getShareCycleTitle() {		return shareCycleTitle;	}	public void setShareCycleTitle(String shareCycleTitle) {		this.shareCycleTitle = shareCycleTitle;	}	public String getShareContent() {		return shareContent;	}	public void setShareContent(String shareContent) {		this.shareContent = shareContent;	}
	
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
		DredgeProduct other = (DredgeProduct) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
