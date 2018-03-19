/**   
 * Filename:    Location.java   
 * @version:    1.0  
 * Create at:   2014年11月26日 上午3:16:31   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月26日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.entity02;

import com.alibaba.fastjson.JSON;

/**
 * Filename: Location.java
 * 
 * @version: 1.0.0 Create at: 2014年11月26日 上午3:16:31 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月26日 shiyl 1.0 1.0 Version
 */
public class Location {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	private String lng;
	private String lat;

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Location(String lng, String lat) {
		super();
		this.lng = lng;
		this.lat = lat;
	}

	public Location() {
		
	}
}
