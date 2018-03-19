/**   
* Filename:    TestCommUuidService.java   
* @version:    1.0  
* Create at:   2014年10月13日 上午6:50:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commUuid;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    TestCommUuidService.java
 * @version:    1.0.0
 * Create at:   2014年10月13日 上午6:50:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月13日       shiyl             1.0             1.0 Version
 */
public class TestCommUuidService extends BaseTest{
//	@Test
//	public void testSynch(){
//		final ICommUuidService commUuidService = ctx.getBean("commUuidService", ICommUuidService.class);
//		System.out.println("start..");
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true){
//						System.out.println("A"+commUuidService.getNextUuidBigInteger(SEQConstants.t_comm_user_session));
//					}
//				}
//			}).start();
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true){
//						System.out.println("B"+commUuidService.getNextUuidBigInteger(SEQConstants.t_comm_user_session));
//					}
//				}
//			}).start();
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true){
//						System.out.println("C"+commUuidService.getNextUuidBigInteger(SEQConstants.t_comm_user_session));
//					}
//				}
//			}).start();
//		System.out.println("end..");
//	}
//	private void testA(String name,IUuidManager uuidManager,Map<BigInteger,String> existList){
//		BigInteger tmp = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_user_session);
//		if(existList.keySet().contains(tmp)){
//			System.out.println(name+"---"+tmp +" is exist,cause error,src is "+existList.get(tmp));
//		}else{
//			existList.put(tmp,name);
//			System.out.println(name+"is "+tmp);
//		}
//	}
//	private void testB(String name,ICommUuidService commUuidService,Map<BigInteger,String> existList){
////		try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		BigInteger tmp = commUuidService.getNextUuidBigInteger(SEQConstants.t_comm_user_session);
//		synchronized (TestCommUuidService.class) {
//			if(existList.keySet().contains(tmp)){
//				System.out.println(name+"---"+tmp +" is exist,cause error,src is "+existList.get(tmp));
//			}else{
//				existList.put(tmp,name);
//				System.out.println(name+"is "+tmp);
//			}
//		}
//		
//	}
//	@Test
//	public void testSynch02(){
//		final IUuidManager uuidManager = ctx.getBean("uuidManager", IUuidManager.class);
//		final ICommUuidService commUuidService = ctx.getBean("commUuidService", ICommUuidService.class);
//		final Map<BigInteger,String> existList = new ConcurrentHashMap<BigInteger, String>();
//		testB(Thread.currentThread().getId()+"",commUuidService, existList);
//		System.out.println("start..");
//		for(int i=0;i<100;i++){
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true){
////						testA(Thread.currentThread().getId()+"",uuidManager, existList);
//						testB(Thread.currentThread().getId()+"",commUuidService, existList);
//						System.out.println("123");
//					}
//				}
//			}).start();
//		}
//			System.out.println("end..");
//	}
	
}
