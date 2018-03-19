/**
 * 
 */
package com.cnfantasia.server.ms.notice.dto;

import java.io.Serializable;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月5日下午3:54:33
 */
public class MessageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2319494780151762046L;

	private String id;
	
	private String type;
	
	private String title;
	
	private String content;
	
	private String time;
	
	private String delState;
	
	private String creater;
	
	private String picUrl;
	
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the delState
	 */
	public String getDelState() {
		return delState;
	}

	/**
	 * @param delState the delState to set
	 */
	public void setDelState(String delState) {
		this.delState = delState;
	}

	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * @param creater the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
