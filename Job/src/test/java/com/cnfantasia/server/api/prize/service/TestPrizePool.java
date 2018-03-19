///**   
//* Filename:    TestPrizePool.java   
//* @version:    1.0  
//* Create at:   2015年3月31日 上午6:12:36   
//* Description:  
//*   
//* Modification History:   
//* Date        Author      Version     Description   
//* ----------------------------------------------------------------- 
//* 2015年3月31日    shiyl      1.0         1.0 Version   
//*/
//package com.cnfantasia.server.api.prize.service;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.alibaba.fastjson.JSON;
//import com.cnfantasia.server.api.BaseTest;
//import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
//import com.cnfantasia.server.apibase.user.dao.IUserBaseDao;
//import com.cnfantasia.server.apibase.user.entity.User;
//import com.cnfantasia.server.apibase.userTmp.dao.IUserTmpBaseDao;
//import com.cnfantasia.server.apibase.userTmp.entity.UserTmp;
//
///**
// * Filename:    TestPrizePool.java
// * @version:    1.0.0
// * Create at:   2015年3月31日 上午6:12:36
// * Description:
// *
// * Modification History:
// * Date           Author           Version           Description
// * ------------------------------------------------------------------
// * 2015年3月31日       shiyl             1.0             1.0 Version
// */
//public class TestPrizePool extends BaseTest{
//	
////	@Test
////	public void testUpdBatch(){
////		IUserTmpBaseDao userTmpBaseDao = ctx.getBean("userTmpBaseDao", IUserTmpBaseDao.class);
////		//查询所有临时用户列表
////		List<UserTmp> userTmpList = userTmpBaseDao.selectUserTmpByCondition(null, true);
////		int res = userTmpBaseDao.updateUserTmpBatch(userTmpList);
////		System.out.println(res);
////	}
//	
//	@Test
//	public void testDoPrize() throws InterruptedException, IOException{
//		IPrizePoolFactory prizePoolFactory = ctx.getBean("prizePoolFactory", IPrizePoolFactory.class);
//		IUserBaseDao userBaseDao = ctx.getBean("userBaseDao", IUserBaseDao.class);
//		IUserTmpBaseDao userTmpBaseDao = ctx.getBean("userTmpBaseDao", IUserTmpBaseDao.class);
//		String inputStr = "start";
//		while(inputStr.equals("start")){
//		//查询所有的注册用户列表
//			List<User> regUserList = userBaseDao.selectUserByCondition(null, true);
//			System.out.println("当前已注册用户数为："+regUserList.size()+"个");
//			//查询所有临时用户列表
//			List<UserTmp> userTmpList = userTmpBaseDao.selectUserTmpByCondition(null, true);
//			System.out.println("当前临时用户数为："+userTmpList.size()+"个");
//			Thread.sleep(3000);
//			for(User tmpUser:regUserList){
//				BigInteger userId = tmpUser.getId();
//				BigInteger userTmpId = null;
//				doPrize(prizePoolFactory, userId, userTmpId,1);
//			}
//			for(UserTmp tmpUserTmp:userTmpList){
//				BigInteger userId = null;
//				BigInteger userTmpId = tmpUserTmp.getId();
//				doPrize(prizePoolFactory, userId, userTmpId,1);
//			}
//			System.out.println("Ok.....");
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			inputStr = br.readLine();
//		}
//		System.out.println("All end..");
//	}
//	
//	private void doPrize(IPrizePoolFactory prizePoolFactory,BigInteger userId,BigInteger userTmpId,int count){
//		for(int i=0;i<count;i++){
//			try {
//				Date startTime = new Date();
//				PrizeResultDiscountEntity res = prizePoolFactory.doPrize(userId, -1, -1, userTmpId);
//				Date endTime = new Date();
//				System.out.println(JSON.toJSONString(res)+"耗时:"+(endTime.getTime()-startTime.getTime())+"毫秒");
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//		
//	}
//	
//	
//	
//}
