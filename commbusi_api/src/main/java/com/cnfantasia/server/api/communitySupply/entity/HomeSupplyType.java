/**   
* Filename:    HomeSupplyType.java   
* @version:    1.0  
* Create at:   2015年8月17日 上午10:13:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.entity.OperationHomeSupplyTypeEntity;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * Filename:    HomeSupplyType.java
 * @version:    1.0.0
 * Create at:   2015年8月17日 上午10:13:31
 * Description:首页商家类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月17日       shiyl             1.0             1.0 Version
 */
public class HomeSupplyType {
	/**类别Id*/
	private BigInteger id;
	/**类别名称*/
	private String name;
	/**类别图标地址*/
	private String iconName;
	/**顶级父类Id*/
	private BigInteger topParentId;
	/**是否超链接 true是false否*/
	private Boolean isLink;
	/**链接地址 isLink为true时有效*/
	private String linkUrl;
	/**是否为内部跳转 true是false否*/
	private Boolean isAppUrl;
	/**下级页面数据 isAppUrl为true时有效*/
	private List<HomeSupplyType> subList;
	/**是否火热开启 true是false否*/
	private Boolean isHot;
	
	/**编码*/
	private String code;
	/**描述*/
	private String desc;
	
	private Integer propfeeCanpay;
	
	private Integer verifyStatus;
	
	/**最近更新时间*/
	private String lastUpdTime;
	
	/**关联商家类别表的Id*/
	private BigInteger supplyTypeId;
	public BigInteger getSupplyTypeId() {
		return supplyTypeId;
	}
	public void setSupplyTypeId(BigInteger supplyTypeId) {
		this.supplyTypeId = supplyTypeId;
	}
	
	public static HomeSupplyType newInstanceForPayBillType(PayBillType srcType,BigInteger topParentId,String code){
		List<HomeSupplyType> subList = null;
		BigInteger id = null;
		String name = srcType.getName();
		String iconName = srcType.getIcon();//TODO ..共用文件服务器路径
		boolean isLink = false;
		String linkUrl = "";
		boolean isAppUrl = true;
		boolean isHot = false;
		String lastUpdTime = srcType.getLastUpdTime();
		String desc = "";
		HomeSupplyType homeSupplyType = new HomeSupplyType(id, name, iconName, topParentId, isLink, linkUrl, isAppUrl, subList, isHot, lastUpdTime, code, desc);
		homeSupplyType.setSupplyTypeId(srcType.getId());//
		return homeSupplyType;
	}
	
	public static HomeSupplyType newInstanceForOperationHomeSupplyType(OperationHomeSupplyTypeEntity srcType){
		String code = srcType.getCode();
		Boolean isLink = null;
		Boolean isAppUrl = null;
		BigInteger topParentId = null;
		if(OperationDict.OperationHomeSupplyType_DataType.common_Type.compareTo(srcType.getDataType())==0){
			topParentId = srcType.fetchTopParentId();
			isAppUrl = true;//syl-add-2015-8-19 18:26:21
		}else if(OperationDict.OperationHomeSupplyType_DataType.isLink.compareTo(srcType.getDataType())==0){
			isLink = true;
		}else if(OperationDict.OperationHomeSupplyType_DataType.innerAppUrl.compareTo(srcType.getDataType())==0){
			isAppUrl = true;
		}
		
		String desc = srcType.getDesc();
		String iconName = srcType.getIconName();
		BigInteger id = srcType.getId();
		
		Boolean isHot = null;
		if(OperationDict.OperationHomeSupplyType_IsHot.TRUE.compareTo(srcType.getIsHot())==0){
			isHot = true;
		}else if(OperationDict.OperationHomeSupplyType_IsHot.FALSE.compareTo(srcType.getIsHot())==0){
			isHot = false;
		}
		
		String lastUpdTime = srcType.getLastUpdTime();
		String linkUrl = srcType.getLinkUrl();
		String name = srcType.getName();
//	this.aa = srcType.getDataLevel();
//	this.aa = srcType.getOrder();
//	this.aa = srcType.getParentId();
//	this.aa = srcType.getHomePlace();
//	this.aa = srcType.getSupplyTypeId();
		List<HomeSupplyType> subList = null;
		HomeSupplyType homeSupplyType = new HomeSupplyType(id, name, iconName, topParentId, isLink, linkUrl, isAppUrl, subList, isHot, lastUpdTime, code, desc);
		homeSupplyType.setSupplyTypeId(srcType.getSupplyTypeId());//syl-add-2015-8-20 18:10:03 兼容维修保修数据关联的页面跳转
		return homeSupplyType;
	}
	
//	/**商家类别*/
//	public static HomeSupplyType newInstanceForSupplyType(CommunitySupplyType communitySupplyType,CommunitySupplyType topCommunitySupplyType){
//		//BigInteger id = communitySupplyType.getId();
//		String name = communitySupplyType.getName();
//		String iconName = CommunitySupplyTypeUtil.getIconName(topCommunitySupplyType);
//		BigInteger topParentId = topCommunitySupplyType==null?null:topCommunitySupplyType.getId();
//		String lastUpdTime = communitySupplyType.getSys0UpdTime();
//		HomeSupplyType res = new HomeSupplyType(id, name, iconName, topParentId, null, null, null, null, null,lastUpdTime,null,null);
//		return res;
//	}
	
//	/**商家类别*/
//	public static HomeSupplyType newInstanceForSupplyType(BigInteger id,String name,String iconName,BigInteger topParentId,String lastUpdTime){
//		HomeSupplyType res = new HomeSupplyType(id, name, iconName, topParentId, null, null, null, null, null,lastUpdTime,null,null);
//		return res;
//	}
	
	
	/**仅超链接,例如O2O跳转*/
	public static HomeSupplyType newInstanceForHrefLink(BigInteger id,String name,String iconName,String linkUrl,String lastUpdTime){
		HomeSupplyType res = new HomeSupplyType(id, name, iconName, null, null, linkUrl, null, null, null,lastUpdTime,null,null);
		return res;
	}
	
//	/**仅App内部跳转*/
//	public static HomeSupplyType newInstanceForAppUrl(BigInteger id,String name,String iconName,List<HomeSupplyType> subList,Boolean isHot,String lastUpdTime,String code,String desc){
//		Boolean isAppUrl = true;
//		HomeSupplyType res = new HomeSupplyType(id, name, iconName, null, null, null, isAppUrl, subList, isHot,lastUpdTime,code,desc);
//		return res;
//	}
	
	private HomeSupplyType(BigInteger id,String name,String iconName,BigInteger topParentId,Boolean isLink,String linkUrl,Boolean isAppUrl,List<HomeSupplyType> subList,Boolean isHot,String lastUpdTime,String code,String desc){
		this.id = id;
		this.name = name;
		this.iconName = iconName;
		this.topParentId = topParentId;
		this.isLink = isLink;
		this.linkUrl = linkUrl;
		this.isAppUrl = isAppUrl;
		this.subList = subList;
		this.isHot = isHot;
		this.lastUpdTime = lastUpdTime;
		this.code = code;
		this.desc = desc;
		if(!StringUtils.isEmpty(linkUrl)){//是否超链接
			if(isLink==null){isLink=true;}
			if(isAppUrl==null){isAppUrl=false;}
		}
		isLink=(isLink==null)?false:isLink;//是否超链接 
		isAppUrl=(isAppUrl==null)?false:isAppUrl;//是否为内部跳转
		isHot=(isHot==null)?false:isHot;//是否火热开启 
	}
	
	public BigInteger getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getIconName() {
		return iconName;
	}
	public BigInteger getTopParentId() {
		return topParentId;
	}
	public Boolean getIsLink() {
		return isLink;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public Boolean getIsAppUrl() {
		return isAppUrl;
	}
	public List<HomeSupplyType> getSubList() {
		return subList;
	}
	public Boolean getIsHot() {
		return isHot;
	}
	public String getLastUpdTime() {
		return lastUpdTime;
	}
	public String getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	public Integer getPropfeeCanpay() {
		return propfeeCanpay;
	}
	
	public void setPropfeeCanpay(Integer propfeeCanpay) {
		this.propfeeCanpay = propfeeCanpay;
	}
	
	public Integer getVerifyStatus() {
		return verifyStatus;
	}
	
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	
	public void addSubData(HomeSupplyType subType){
		List<HomeSupplyType> subList = getSubList();
		if(subList==null){
			subList = new ArrayList<HomeSupplyType>();
		}
		subList.add(subType);
		setSubList(subList);
	}
	private void setSubList(List<HomeSupplyType> subList) {
		this.subList = subList;
	}
	
}
