/**
 * 
 */
package com.cnfantasia.server.ms.notice.dto;

import java.io.Serializable;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月5日下午4:04:09
 */
public class DataValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6838436901777610192L;
	
	private String list;
	
	private Integer count;
	
	private Boolean isLast;
	
	private String name;
	
	private String address;
	
	private String tel;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the list
	 */
	public String getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(String list) {
		this.list = list;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the isLast
	 */
	public Boolean getIsLast() {
		return isLast;
	}

	/**
	 * @param isLast the isLast to set
	 */
	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}
	
}
