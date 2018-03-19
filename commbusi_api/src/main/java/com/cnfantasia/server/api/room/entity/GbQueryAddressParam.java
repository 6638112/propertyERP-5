/**   
* Filename:    GbQueryAddressParam.java   
* @version:    1.0  
* Create at:   2015年10月16日 下午4:03:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    GbQueryAddressParam.java
 * @version:    1.0.0
 * Create at:   2015年10月16日 下午4:03:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月16日       shiyl             1.0             1.0 Version
 */
public class GbQueryAddressParam {
	/**城市Id*/
	private BigInteger cityId;
	/**城市名称*/
	private String cityName;
	/**行政区Id*/
	private BigInteger blockId;
	
	/**百度定位地址*/
	private BaiduLocation baiduLocation;
	
	public GbQueryAddressParam(BigInteger cityId,String cityName,BigInteger blockId,BaiduLocation baiduLocation){
		this.cityId = cityId;
		this.cityName = cityName;
		this.blockId = blockId;
		this.baiduLocation = baiduLocation;
	}
	
	/**
	 * 是否有开启定位
	 * @return
	 */
	public boolean hasGps(){
		if(baiduLocation!=null&&baiduLocation.isAvaible()){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否包含城市信息
	 * @return
	 */
	public boolean hasCityInfo(){
		if(cityId!=null||blockId!=null||!StringUtils.isEmpty(cityName)){
			return true;
		}
		return false;
	}
	
	public BigInteger getCityId() {
		return cityId;
	}

	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public BigInteger getBlockId() {
		return blockId;
	}

	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}

	public BaiduLocation getBaiduLocation() {
		return baiduLocation;
	}

	public void setBaiduLocation(BaiduLocation baiduLocation) {
		this.baiduLocation = baiduLocation;
	}
	
	
}
