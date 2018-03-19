/**
 * 
 */
package com.cnfantasia.server.api.notice.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月4日上午10:10:36
 */
public class NoticeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8016395038078691186L;
	
	private BigInteger id;
	
	private String title;
	
	private Date noticeDate = new Date();
	
	private String content;
	
	private String picPath;
	
	private String province;
	
	private String city;
	
	private String block;
	
	private String groupbuilding;
	
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * @return the groupbuilding
	 */
	public String getGroupbuilding() {
		return groupbuilding;
	}

	/**
	 * @param groupbuilding the groupbuilding to set
	 */
	public void setGroupbuilding(String groupbuilding) {
		this.groupbuilding = groupbuilding;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the noticeDate
	 */
	public Date getNoticeDate() {
		return noticeDate;
	}

	/**
	 * @param noticeDate the noticeDate to set
	 */
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}

	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	

}
