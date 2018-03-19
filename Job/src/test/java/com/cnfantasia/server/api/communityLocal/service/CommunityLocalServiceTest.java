/**   
* Filename:    CommunityLocalServiceTest.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午3:41:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communityLocal.service;

import java.math.BigInteger;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.communityLocal.entity.CommunityForumContentEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    CommunityLocalServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午3:41:06
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public class CommunityLocalServiceTest extends BaseTest{
//	public static void main(String[] args) {
//		String time = RelativeDateFormat.format(0L);
//		System.out.println(time);
//	}
//	@Test
	public void testList(){
		ICommunityLocalService communityLocalService = ctx.getBean("communityLocalService", ICommunityLocalService.class);
//		IDualDao dualDao = ctx.getBean("dualDao", IDualDao.class);
		PageModel pageModel = new PageModel(0, 100);
		BigInteger userId = new BigInteger("40002");
		List<CommunityForumContentEntity> resList = communityLocalService.getForumList(new BigInteger("101"), pageModel, userId);
		System.out.println(resList.size());
		System.out.println(pageModel.isLast);
		System.out.println(JSON.toJSONString(resList));
		
//		for(CommunityForumContentEntity tmpObj:resList){
//			String nowTime = dualDao.getNowTime();
//			Long nowTimeLong = DateUtil.timeToLong(nowTime);
//			String time = RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpObj.getCreateTime()));
//			System.out.println(time);
//		}
	}
	
//	@Test
//	public void testMicroblogDetail(){
//		ICommunityLocalService communityLocalService = ctx.getBean("communityLocalService", ICommunityLocalService.class);
//		BigInteger userId = new BigInteger("40001");
//		CommunityForumContentEntity resList = communityLocalService.getForumDetail(new BigInteger("50001"), userId);
//		System.out.println(JSON.toJSONString(resList));
//	}
	
//	@Test
//	public void testTypeList(){
//		ICommunityLocalService communityLocalService = ctx.getBean("communityLocalService", ICommunityLocalService.class);
//		PageModel pageModel = new PageModel(0, 10);
//		List<CommunityForumType> resList = communityLocalService.getCommunityForumTypeList(pageModel);
//		System.out.println(JSON.toJSONString(resList));
//	}
//	@Test
//	public void testPostMicroblog(){
//		ICommunityLocalService communityLocalService = ctx.getBean("communityLocalService", ICommunityLocalService.class);
//		BigInteger userId = new BigInteger("40001");
//		String text="家有桌子，要换了。";
//		BigInteger typeId = new BigInteger("101");
//		communityLocalService.postForum(userId, text, typeId, null);
////		System.out.println(JSON.toJSONString(resList));
//	}
	
}
