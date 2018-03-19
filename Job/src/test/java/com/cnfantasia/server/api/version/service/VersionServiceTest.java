/**   
* Filename:    VersionServiceTest.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午9:11:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;

/**
 * Filename:    VersionServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午9:11:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class VersionServiceTest extends BaseTest{
	
//	@Test
	public void testLastVersion(){
		IVersionService versionService = ctx.getBean("versionService", IVersionService.class);
		{
			AppVersionEntity appVersionEntity = versionService.getLastVersionInfo(new BigInteger("101"),new BigInteger("50000"));
			System.out.println(appVersionEntity.getVersion());
		}
		
		{
			AppVersionEntity appVersionEntity = versionService.getLastVersionInfo(new BigInteger("101"),new BigInteger("50001"));
			System.out.println(appVersionEntity.getVersion());
		}
		
	}
	
	
}
