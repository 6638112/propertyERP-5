package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

/**
 * 新增服务商品参数
 * @author wenfq 2017-05-09
 */
public class ProductAddNewParamter {
	BigInteger id;
	String prdtName;
	String desc;
	BigInteger dt2Id;
	BigInteger ssId;
	/**
	 * "1":"服务前付款","2":"服务后付款"}
	 */
	int payType;
	/**
	 *  product specification
	 */
	List<DredgeProductSpecification> prdtSpecList = new ArrayList<DredgeProductSpecification>();
	/**
	 * product pictures
	 */
	String prdtPics;
	/**
	 *  introduce pictures
	 */
	String introducePics;
	/**
	 * sell range, e.g. 1 all country, or 2 city, or 3 group building
	 */
	int sellRange;
	
	List<BigInteger> cityIds;
	List<BigInteger> blockIds;
	List<BigInteger> gbIds;
	
	String sharePic;
	/**
	 * 分享好友标题
	 */
	String shareFridendTitle;
	/**
	 * 分享朋友圈标题
	 */
	String shareTimelineTitle;
	
	/**
	 * 分享推送图片
	 */
	String sharePushPic;
	/**
	 * 分享内容
	 */
	String shareContent;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigInteger getDt2Id() {
		return dt2Id;
	}
	public void setDt2Id(BigInteger dt2Id) {
		this.dt2Id = dt2Id;
	}
	public BigInteger getSsId() {
		return ssId;
	}
	public void setSsId(BigInteger ssId) {
		this.ssId = ssId;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	public List<DredgeProductSpecification> getPrdtSpecList() {
		return prdtSpecList;
	}
	public void setPrdtSpecList(List<DredgeProductSpecification> prdtSpecList) {
		this.prdtSpecList = prdtSpecList;
	}
	public String getPrdtPics() {
		return prdtPics;
	}
	public void setPrdtPics(String prdtPics) {
		this.prdtPics = prdtPics;
	}
	public String getIntroducePics() {
		return introducePics;
	}
	public void setIntroducePics(String introducePics) {
		this.introducePics = introducePics;
	}
	public int getSellRange() {
		return sellRange;
	}
	public void setSellRange(int sellRange) {
		this.sellRange = sellRange;
	}
	
	public String getSharePic() {
		return sharePic;
	}
	public void setSharePic(String sharePic) {
		this.sharePic = sharePic;
	}
	public String getShareFridendTitle() {
		return shareFridendTitle;
	}
	public void setShareFridendTitle(String shareFridendTitle) {
		this.shareFridendTitle = shareFridendTitle;
	}
	public String getShareTimelineTitle() {
		return shareTimelineTitle;
	}
	public void setShareTimelineTitle(String shareTimelineTitle) {
		this.shareTimelineTitle = shareTimelineTitle;
	}
	public String getSharePushPic() {
		return sharePushPic;
	}
	public void setSharePushPic(String sharePushPic) {
		this.sharePushPic = sharePushPic;
	}
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public List<BigInteger> getBlockIds() {
		return blockIds;
	}
	public void setBlockIds(List<BigInteger> blockIds) {
		this.blockIds = blockIds;
	}
	public List<BigInteger> getCityIds() {
		return cityIds;
	}
	public void setCityIds(List<BigInteger> cityIds) {
		this.cityIds = cityIds;
	}
	public List<BigInteger> getGbIds() {
		return gbIds;
	}
	public void setGbIds(List<BigInteger> gbIds) {
		this.gbIds = gbIds;
	}
}
