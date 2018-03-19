/**   
* Filename:    CommunitySupplyServiceTest.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午9:14:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    CommunitySupplyServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午9:14:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyServiceTest extends BaseTest{
	
//	@Test
//	public void getCommunitySupplyDetail(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.getCommunitySupplyDetail(new BigInteger("2"),new BigInteger("40033"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void searchCommunitySupplyList(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.searchCommunitySupplyList(new BigInteger("100"), "", new PageModel(0,2),new BigInteger("40033"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void getCommentsLabelListBySupplyType(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.getCommentsLabelListBySupplyType(new BigInteger("200"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void getCommunitySupplyTypeList(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.getCommunitySupplyTypeList(new BigInteger("100"), 2, new PageModel(0,10));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void addCallCount(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.addCallCount(new BigInteger("1"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void getCommunitySupplyDetail(){
//		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
//		Object res = communitySupplyService.getCommunitySupplyTypeBySupplyTypeId(new BigInteger("100"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
	public void getCommunitySupplyTypeList(){
		ICommunitySupplyService communitySupplyService = ctx.getBean("communitySupplyService", ICommunitySupplyService.class);
		Object res = communitySupplyService.searchCommunitySupplyList(new BigInteger("200"), "红", new PageModel(0, 10), new BigInteger("40001"),null);
		System.out.println(JSON.toJSONString(res));
	}
}
