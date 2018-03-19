/**   
* Filename:    InitCityAlpha.java   
* @version:    1.0  
* Create at:   2014年5月28日 上午6:45:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月28日    shiyl      1.0         1.0 Version   
*/
package test;

import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;

/**
 * Filename:    InitCityAlpha.java
 * @version:    1.0.0
 * Create at:   2014年5月28日 上午6:45:27
 * Description: 根据市的名称获取对应拼音的首字母，并将其更新到数据接口
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月28日       shiyl             1.0             1.0 Version
 */
public class InitCityAlpha extends BaseTest{
	@org.junit.Test
	public void insertAlpha() throws BadHanyuPinyinOutputFormatCombination{
		//获取城市列表
		IAddressCityBaseService addressCityBaseService = ctx.getBean("addressCityBaseService", IAddressCityBaseService.class);
		List<AddressCity> list = addressCityBaseService.getAddressCityByCondition(null);
		//遍历，逐个设置取值
		for(AddressCity addressCity:list){
//			ChineseCharToEn cte = new ChineseCharToEn();
//			String alpha = cte.getFirstLetter(addressCity.getName()).toUpperCase();
			String alpha = getFirstPinyin(addressCity.getName()).toUpperCase();
			addressCity.setAlpha(alpha);
			System.out.println(addressCity.getName()+":"+alpha);
		}
		//更新数据
		for(AddressCity addressCity:list){
			addressCityBaseService.updateAddressCity(addressCity);
		}
	}
	
	public static String getFirstPinyin(String data) throws BadHanyuPinyinOutputFormatCombination{
		return PinyinUtil.hanyuToPinyinFirst(data).substring(0, 1);
	}
	
}
