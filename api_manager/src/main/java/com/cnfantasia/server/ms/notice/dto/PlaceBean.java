/**
 * 
 */
package com.cnfantasia.server.ms.notice.dto;

import java.io.Serializable;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月6日上午11:46:26
 */
public class PlaceBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3500998414134793658L;

	private String id;
	
	private String name;

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

}
