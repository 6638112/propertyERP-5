/**   
* Filename:    PushConfig.java   
* @version:    1.0  
* Create at:   2015年10月21日 下午4:52:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush;

/**
 * Filename:    PushConfig.java
 * @version:    1.0.0
 * Create at:   2015年10月21日 下午4:52:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月21日       shiyl             1.0             1.0 Version
 */
public class PushConfig {
	/**
	 * 设备类型，取值范围为：1～5 
	 * 1：浏览器设备；
	 * 2：PC设备；
	 * 3：Andriod设备；
	 * 4：iOS设备；
	 * 5：Windows Phone设备； 
	 */
	private String deviceType;
	private String baiduUserId;
	private String baiduChannelId; 
	
	public PushConfig(int channelSubId,String baiduUserId,String baiduChannelId){
		this.baiduChannelId = baiduChannelId;
		this.baiduUserId = baiduUserId;
		this.deviceType = channelSubId==1?"3":(channelSubId==2?"4":"");//1安卓,2ios
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getBaiduUserId() {
		return baiduUserId;
	}

	public String getBaiduChannelId() {
		return baiduChannelId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baiduChannelId == null) ? 0 : baiduChannelId.hashCode());
		result = prime * result + ((baiduUserId == null) ? 0 : baiduUserId.hashCode());
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
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
		PushConfig other = (PushConfig) obj;
		if (baiduChannelId == null) {
			if (other.baiduChannelId != null)
				return false;
		} else if (!baiduChannelId.equals(other.baiduChannelId))
			return false;
		if (baiduUserId == null) {
			if (other.baiduUserId != null)
				return false;
		} else if (!baiduUserId.equals(other.baiduUserId))
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		return true;
	}
	
	
}
