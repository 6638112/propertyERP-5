/**   
* Filename:    MultiDbTest.java   
* @version:    1.0  
* Create at:   2014年6月15日 上午10:30:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.multidb;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    MultiDbTest.java
 * @version:    1.0.0
 * Create at:   2014年6月15日 上午10:30:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月15日       shiyl             1.0             1.0 Version
 */
public class MultiDbTest extends BaseTest{
	
////	@Test
//	public void fetchData(){
////		new Thread(new Runnable() {
////			@Override
////			public void run() {
////				
////			}
////		}).start();
//	//查询用户行为表
//		/*{
//			IOriginAppUpdateBaseService originAppUpdateBaseService=ctx.getBean("originAppUpdateBaseService", IOriginAppUpdateBaseService.class);
//			DataSourceContextHolder.setDbType("dataSourceOrigin");
//			List<OriginAppUpdate> originAppUpdateList = originAppUpdateBaseService.getOriginAppUpdateByCondition(null);
//			System.out.println(JSON.toJSONString(originAppUpdateList));
//			DataSourceContextHolder.setDbType(null);
//		}*/
//		IOriginAppUpdateBaseService originAppUpdateBaseService=ctx.getBean("originAppUpdateBaseService", IOriginAppUpdateBaseService.class);
//		
//		OriginAppUpdate originAppUpdate = new OriginAppUpdate();
//		originAppUpdate.setAppId(535L);
//		 OriginDbOperateInvoker.invokeDb(originAppUpdateBaseService,"insertOriginAppUpdate",new Object[]{MapConverter.toMap(originAppUpdate)});
//		List<OriginAppUpdate> originAppUpdateList = OriginDbOperateInvoker.invokeDb(originAppUpdateBaseService, "getOriginAppUpdateByCondition", new Object[]{null});
//		System.out.println(JSON.toJSONString(originAppUpdateList));
////		//查询日志记录表
////		ILoginLogBaseService loginLogBaseService = ctx.getBean("loginLogBaseService", ILoginLogBaseService.class);
////		List<LoginLog> loginLogList = loginLogBaseService.getLoginLogByCondition(null);
////		System.out.println(JSON.toJSONString(loginLogList));
//		
//	}
	
}
