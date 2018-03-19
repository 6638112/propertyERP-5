/**   
* Filename:    AddressCityHotEntity.java   
* @version:    1.0  
* Create at:   2015年2月3日 上午5:26:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCityHot.entity.AddressCityHot;

/**
 * Filename:    AddressCityHotEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月3日 上午5:26:34
 * Description: 热门城市信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月3日       shiyl             1.0             1.0 Version
 */
public class AddressCityHotEntity extends AddressCityHot{
	private static final long serialVersionUID = 1L;
	
	/**城市信息*/
	private AddressCity addressCity;
	public AddressCity getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(AddressCity addressCity) {
		this.addressCity = addressCity;
	}
	
	
}
