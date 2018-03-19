/**   
* Filename:    TestKitchenService02.java   
* @version:    1.0  
* Create at:   2014年9月15日 上午8:53:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestKitchenService02.java
 * @version:    1.0.0
 * Create at:   2014年9月15日 上午8:53:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月15日       shiyl             1.0             1.0 Version
 */
public class TestKitchenService02 extends BaseTest{
//		@Test
//		public void testDetail(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getCookbookDetail(new BigInteger("-1"), new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
		public void getMixCookbookListByCookbookId(){
			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
			Object res=kitchenService.getMixCookbookListByCookbookId(new BigInteger("-1"),new BigInteger("207"), new BigInteger("40001"));
			System.out.println(JSON.toJSONString(res));
		}
	
//		@Test
//		public void getRecommend(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getRecommend(new BigInteger("-1"), new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getCookbookTypeList(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getCookbookTypeList(new BigInteger("-1"), new PageModel(0,10),new BigInteger("40031"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getCookbookList(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getCookbookList(new BigInteger("2"), new PageModel(0,10), new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getCookbookRecommendByCount(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			List<BigInteger> ignoreList = new ArrayList<BigInteger>();
//			ignoreList.add(new BigInteger("207"));
////			Object res=kitchenService.getCookbookRecommendByCount(new BigInteger("-1"), null, 3, new BigInteger("40001"));
//			Object res=kitchenService.getCookbookRecommendByCount(new BigInteger("-1"), ignoreList, 3, new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getGodRecommend(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			List<BigInteger> ignoreList = new ArrayList<BigInteger>();
////			ignoreList.add(new BigInteger("207"));
//			Object res=kitchenService.getGodRecommend(new BigInteger("-1"), ignoreList, new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getLastCollectCookbookCurrDay(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getLastCollectCookbookCurrDay(new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getDefaultCollectCookbookCurrDay(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getDefaultCollectCookbookCurrDay(new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getCollectCookbookTypeList(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getCollectCookbookTypeList(new BigInteger("-1"), new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void getDefaultCollectCookbookTypeList(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getDefaultCollectCookbookTypeList(new BigInteger("-1"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
		public void submitAllCollectCookbookTypes(){
			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
			List<BigInteger> collectTypeIds = new ArrayList<BigInteger>();
			collectTypeIds.add(new BigInteger("1"));
			collectTypeIds.add(new BigInteger("4"));
			Object res=kitchenService.submitAllCollectCookbookTypes(new BigInteger("-1"), collectTypeIds, new BigInteger("40001"));
			System.out.println(JSON.toJSONString(res));
		}
	
//		@Test
//		public void doCollectCookbookType(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			kitchenService.doCollectCookbookType(new BigInteger("1"), new BigInteger("40001"));
//		}
	
//		@Test
//		public void cancelCollectCookbookType(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			kitchenService.cancelCollectCookbookType(new BigInteger("1"), new BigInteger("40001"));
//		}
	
//		@Test
//		public void getCollectCookbookListCurrWeek(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res=kitchenService.getCollectCookbookListCurrWeek(new BigInteger("40001"));
//			System.out.println(JSON.toJSONString(res));
//		}
	
//		@Test
//		public void doCollectCookbook(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			kitchenService.doCollectCookbook(new BigInteger("201"), new BigInteger("40001"));
//		}
	
//		@Test
//		public void cancelCollectCookbook(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			kitchenService.cancelCollectCookbook(new BigInteger("201"), new BigInteger("40001"));
////			kitchenService.cancelCollectCookbook(new BigInteger("206"), new BigInteger("40001"));
////			kitchenService.cancelCollectCookbook(new BigInteger("207"), new BigInteger("40001"));
//		}
	
//		@Test
//		public void getCollectCookbookListHistory(){
//			IKitchenService kitchenService = ctx.getBean("kitchenService", IKitchenService.class);
//			Object res = kitchenService.getCollectCookbookListHistory(new BigInteger("40001"), new PageModel(0, 10));
//			System.out.println(res);
//		}
}
