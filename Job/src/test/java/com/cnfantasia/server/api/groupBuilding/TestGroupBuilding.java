/**   
* Filename:    TestGroupBuilding.java   
* @version:    1.0  
* Create at:   2015年2月6日 上午7:26:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.groupBuilding;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;

/**
 * Filename:    TestGroupBuilding.java
 * @version:    1.0.0
 * Create at:   2015年2月6日 上午7:26:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月6日       shiyl             1.0             1.0 Version
 */
public class TestGroupBuilding extends BaseTest{
	
	@Test
	public void test(){
		IAddressCityBaseService addressCityBaseService = ctx.getBean("addressCityBaseService", IAddressCityBaseService.class);
		String cityName = "哈密地区";
		AddressCity addressCityQry = new AddressCity();
		addressCityQry.setName(cityName);
		List<AddressCity> addressCityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.toMap(addressCityQry));
		System.out.println(JSON.toJSONString(addressCityList));
	}
	
}
