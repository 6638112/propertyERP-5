/**   
* Filename:    MicroblogServiceTest.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午3:41:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.microblog.service;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    MicroblogServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午3:41:06
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public class MicroblogServiceTest extends BaseTest{
//	public static void main(String[] args) {
//		String time = RelativeDateFormat.format(0L);
//		System.out.println(time);
//	}
//	@Test
//	public void testList(){
//		IMicroblogService microblogService = ctx.getBean("microblogService", IMicroblogService.class);
////		IDualDao dualDao = ctx.getBean("dualDao", IDualDao.class);
//		PageModel pageModel = new PageModel(0, 100);
//		BigInteger userId = new BigInteger("40001");
////		List<MicroblogContentEntity> resList = microblogService.getMicroblogList(new BigInteger("0"), pageModel, userId);
//		List<MicroblogContentEntity> resList = microblogService.getMicroblogList(new BigInteger("0"), pageModel, userId);
//		System.out.println(resList.size());
//		System.out.println(pageModel.isLast);
//		System.out.println(JSON.toJSONString(resList));
//		
////		for(MicroblogContentEntity tmpObj:resList){
////			String nowTime = dualDao.getNowTime();
////			Long nowTimeLong = DateUtil.timeToLong(nowTime);
////			String time = RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpObj.getCreateTime()));
////			System.out.println(time);
////		}
//	}
	
//	@Test
//	public void testMicroblogDetail(){
//		IMicroblogService microblogService = ctx.getBean("microblogService", IMicroblogService.class);
//		BigInteger userId = new BigInteger("40001");
//		MicroblogContentEntity resList = microblogService.getMicroblogDetail(new BigInteger("101"), userId);
//		System.out.println(JSON.toJSONString(resList));
//	}
	
//	@Test
//	public void testTypeList(){
//		IMicroblogService microblogService = ctx.getBean("microblogService", IMicroblogService.class);
//		PageModel pageModel = new PageModel(0, 10);
//		List<MicroblogType> resList = microblogService.getMicroblogTypeList(pageModel);
//		System.out.println(JSON.toJSONString(resList));
//	}
//	@Test
//	public void testPostMicroblog(){
//		IMicroblogService microblogService = ctx.getBean("microblogService", IMicroblogService.class);
//		BigInteger userId = new BigInteger("40001");
//		String text="家有桌子，要换了。";
//		BigInteger typeId = new BigInteger("1");
//		List<String> picList = new ArrayList<String>();
//		picList.add("/a.jpg");
//		microblogService.postMicroblog(userId, text, typeId, picList);
////		System.out.println(JSON.toJSONString(resList));
//	}
	
//	@Test
//	public void testTypeList(){
//		IMicroblogService microblogService = ctx.getBean("microblogService", IMicroblogService.class);
//		String res = microblogService.fetchAllMicroblogLastUpdTime(-1);
//		System.out.println(res);
//	}
	
//	@Test
//	public void testTypeList(){
//		IMicroblogQueueTask microblogQueueTask = ctx.getBean("microblogQueueTask", IMicroblogQueueTask.class);
//		microblogQueueTask.callMQPush2MicroblogContentDbGbId();
//	}
	
//	@Test
//	public void testTypeList(){
//		System.out.println("==========================");
//		IMicroblogQueueDao microblogQueueDao = ctx.getBean("microblogQueueDao", IMicroblogQueueDao.class);
//		Object res = microblogQueueDao.selectCanPayGbYesterdayEndList(MicroblogQueueDict.MQ_Level.Code_40);
//		System.out.println(JSON.toJSONString(res));
//		Object res2 = microblogQueueDao.selectCanPayGbYesterdayEndForUpdate(new BigInteger("1"), MicroblogQueueDict.MQ_Level.Code_40);
//		System.out.println(JSON.toJSONString(res2));
//		Object res3 = microblogQueueDao.selectPayBillGroupBuildingListForMQ(MicroblogQueueDict.MQ_Level.Code_20);
//		System.out.println(JSON.toJSONString(res3));
//	}
	
//	@Test
//	public void testTypeList(){
//		System.out.println(McVisitRecordCache.checkIfPushNow(new BigInteger("12346")));
//		System.out.println(McVisitRecordCache.checkIfPushNow(new BigInteger("12346")));
//		McVisitRecordCache.makrPushCountAsFull(new BigInteger("12346"));
//		System.out.println(McVisitRecordCache.checkIfPushNow(new BigInteger("12346")));
//	}
	
//	@Test
//	public void testTypeList(){
//		IMicroblogQueueService microblogQueueService = ctx.getBean("microblogQueueService", IMicroblogQueueService.class);
//		
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_10));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_20));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_30));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_40));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_60));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_70));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_80));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_90));
//		System.out.println(microblogQueueService.checkCanPush(MicroblogQueueDict.MQ_Level.Code_150));
//		System.out.println(microblogQueueService.checkCanPush(Long.valueOf("160")));
//		System.out.println(microblogQueueService.checkCanPush(Long.valueOf("12")));
//	}
	
//	@Test
//	public void testTypeList(){
//		IMicroblogQueueTask microblogQueueTask = ctx.getBean("microblogQueueTask", IMicroblogQueueTask.class);
//		microblogQueueTask.callMQPush2MicroblogContent(new BigInteger("3299"));
//	}
	
}
