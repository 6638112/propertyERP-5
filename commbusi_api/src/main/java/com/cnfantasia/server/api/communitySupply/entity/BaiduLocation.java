/**   
* Filename:    BaiduLocation.java   
* @version:    1.0  
* Create at:   2015年1月8日 上午6:33:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import java.io.Serializable;

/**
 * Filename:    BaiduLocation.java
 * @version:    1.0.0
 * Create at:   2015年1月8日 上午6:33:54
 * Description:百度坐标
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月8日       shiyl             1.0             1.0 Version
 */
public class BaiduLocation implements Serializable{
	private static final long serialVersionUID = 1L;
	/**经度*/
	private Double longitude;
	/**纬度*/
	private Double latitude;
	
	public BaiduLocation(){
	}
	
	public BaiduLocation(Double longitude,Double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 当前数据是否可用
	 * @return
	 */
	public boolean isAvaible(){
		if(longitude!=null&&latitude!=null&&longitude!=0&&latitude!=0){
			return true;
		}else{
			return false;
		}
	}
	
	
}
