/**   
* Filename:    PlotpropertyServiceTest.java   
* @version:    1.0  
* Create at:   2014年8月14日 上午7:08:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.service;

import java.math.BigInteger;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.ms.revenue.task.PropertySubsidyAmoutAutoApplyCompany;

/**
 * Filename:    PlotpropertyServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年8月14日 上午7:08:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月14日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyServiceTest extends BaseTest{
	
//	@Test
//	public void getGroupBuildEntity(){
//		IPlotpropertyService plotpropertyService = ctx.getBean("plotpropertyService", IPlotpropertyService.class);
////		Object res = plotpropertyService.getGroupBuildingEntityById(new BigInteger("1"));
//		Object res = plotpropertyService.getGroupBuildingEntityById(new BigInteger("1"),new BigInteger("40001"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void getLastPayBill(){
//		IPlotpropertyService plotpropertyService = ctx.getBean("plotpropertyService", IPlotpropertyService.class);
//		Object res = plotpropertyService.getLastPayBill(new BigInteger("40001"));
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void getPayBillByMonth(){
//		IPlotpropertyService plotpropertyService = ctx.getBean("plotpropertyService", IPlotpropertyService.class);
//		Object res = plotpropertyService.getPayBillByMonth(new BigInteger("40001"), "201406");
//		System.out.println(JSON.toJSONString(res));
//	}
	
//	@Test
//	public void selectExistPayBillYearList(){
//		IPlotpropertyDao plotpropertyDao = ctx.getBean("plotpropertyDao", IPlotpropertyDao.class);
//		Object res = plotpropertyDao.selectExistPayBillYearList(new BigInteger("50036"), PlotpropertyDict.PayBill_IsPay.FALSE);
//		System.out.println(JSON.toJSONString(res));
//		Object res2 = plotpropertyDao.selectExistPayBillYearList(new BigInteger("50036"), PlotpropertyDict.PayBill_IsPay.TRUE);
//		System.out.println(JSON.toJSONString(res2));
//	}
	
//	@Test
//	public void getIsPayBillList(){
//		IPlotpropertyService plotpropertyService = ctx.getBean("plotpropertyService", IPlotpropertyService.class);
//		Object res = plotpropertyService.getIsPayBillList(new BigInteger("50036"), new PageModel(0,10));
//		System.out.println(JSON.toJSONString(res));
//		Object res2 = plotpropertyService.getNotPayBillList(new BigInteger("50036"));
//		System.out.println(JSON.toJSONString(res2));
//	}
	
//	@Test
//	public void getIsPayBillList(){
//		IPlotpropertyService plotpropertyService = ctx.getBean("plotpropertyService", IPlotpropertyService.class);
//		plotpropertyService.deletePlotpropertyOrderAndBackDiscountAll();
//	}
	
//	@Test
//	public void selectPayBillTypeListAll(){
//		IPlotpropertyCfgDao plotpropertyCfgDao = ctx.getBean("plotpropertyCfgDao", IPlotpropertyCfgDao.class);
//		BigInteger gbId = new BigInteger("10090155");
//		List<PayBillType> resList = plotpropertyCfgDao.selectPayBillTypeListAll(gbId);
//		System.out.println(JSON.toJSONString(resList));
//	}

	
//	@Test
//	public void selectPayBillTypeListAll(){
//		List<BigInteger> idList = new ArrayList<BigInteger>();
////		idList.add(new BigInteger("251370"));
////		idList.add(new BigInteger("251371"));
////		idList.add(new BigInteger("251372"));
////		idList.add(new BigInteger("251385"));
////		idList.add(new BigInteger("251386"));
////		idList.add(new BigInteger("251387"));
////		idList.add(new BigInteger("251388"));
////		idList.add(new BigInteger("251389"));
////		idList.add(new BigInteger("251390"));
////		idList.add(new BigInteger("251536"));
////		idList.add(new BigInteger("251537"));
////		idList.add(new BigInteger("251538"));
////		idList.add(new BigInteger("251539"));
//		idList.add(new BigInteger("251676"));
//		for(BigInteger paybillId:idList){
//			IPlotpropertyCfgService plotpropertyCfgService = ctx.getBean("plotpropertyCfgService", IPlotpropertyCfgService.class);
//			Object res = plotpropertyCfgService.generatePayBillSplit(paybillId);
//			System.out.println(JSON.toJSONString(res));
//		}
//	}
	
	@Test
	public void execPropertySubsidyAmoutAutoApplyCompanyTaskOne(){
		PropertySubsidyAmoutAutoApplyCompany propertySubsidyAmoutAutoApplyCompany = ctx.getBean("propertySubsidyAmoutAutoApplyCompany", PropertySubsidyAmoutAutoApplyCompany.class);
		propertySubsidyAmoutAutoApplyCompany.execTask();
		propertySubsidyAmoutAutoApplyCompany.execTaskOne(new BigInteger("1"));
		
//		RevenueTask revenueTask = ctx.getBean("revenueTask", RevenueTask.class);
//		revenueTask.synPropertySubsidyAmoutForPropertyCompany();
		
//		PropertySubsidyAmoutSynTask propertySubsidyAmoutSynTask = ctx.getBean("propertySubsidyAmoutSynTask", PropertySubsidyAmoutSynTask.class);
//		propertySubsidyAmoutSynTask.execTask();
////		propertySubsidyAmoutSynTask.synSignal(new RevenueRole(UserRole.PropertyCompany, new BigInteger("1")));//(UserRole.PropertyCompany, new BigInteger("1"), RevenueDict.RevenueProject.PropertySubsidyAmout);
	}

	
}
