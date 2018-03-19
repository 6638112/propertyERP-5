/**   
* Filename:    TestCrud.java   
* @version:    1.0  
* Create at:   2014年6月24日 上午2:31:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.mybatis;

import java.math.BigInteger;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;

/**
 * Filename:    TestCrud.java
 * @version:    1.0.0
 * Create at:   2014年6月24日 上午2:31:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月24日       shiyl             1.0             1.0 Version
 */
public class TestCrud extends BaseTest{
//	@Test
//	public void insert(){
//		IAppBaseInfoBaseService appBaseInfoBaseService = ctx.getBean("appBaseInfoBaseService", IAppBaseInfoBaseService.class);
//		/*{
//			AppBaseInfo appBaseInfo = new AppBaseInfo();
//			appBaseInfo.setId(new BigInteger("57"));
//			appBaseInfo.setName("abc");
//			appBaseInfo.setDownUrl("ljljljl");
//			appBaseInfo.setDesc("ooo");
//			int res = appBaseInfoBaseService.insertAppBaseInfo(MapConverter.toMap(appBaseInfo));
//			System.out.println(res);
//		}*/
//		{
//			int res = appBaseInfoBaseService.deleteAppBaseInfo("57");
//			System.out.println(res);
//		}
//	}
//	@Test
	public void insert(){
		IPropertyManagementBaseService propertyManagementBaseService  = ctx.getBean("propertyManagementBaseService", IPropertyManagementBaseService.class);
		/*{
			PropertyManagement propertyManagement = new PropertyManagement();
			propertyManagement.setId(new BigInteger("8"));
			int res = propertyManagementBaseService.insertPropertyManagement(MapConverter.toMap(propertyManagement));
			System.out.println(res);
		}*/
//		{
//			PropertyManagement propertyManagement = new PropertyManagement();
//			propertyManagement.setId(new BigInteger("8"));
//			propertyManagementBaseService.updatePropertyManagement(MapConverter.toMap(propertyManagement));
//		}
		{
			propertyManagementBaseService.deletePropertyManagementLogic(new BigInteger("8"));
		}
	}
}
