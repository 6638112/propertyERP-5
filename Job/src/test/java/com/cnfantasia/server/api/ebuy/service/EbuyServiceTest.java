/**   
* Filename:    EbuyServiceTest.java   
* @version:    1.0  
* Create at:   2014年6月9日 上午7:27:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.service;

import com.cnfantasia.server.api.BaseTest;

/**
 * Filename:    EbuyServiceTest.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 上午7:27:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuyServiceTest extends BaseTest{ 
//	@Test
//	public void testProductList(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		PageModel pageModel = new PageModel(0, 2);
//		BigInteger productTypeId = new BigInteger("1");
//		List<EbuyProductEntity> resList = ebuyService.getProductList(productTypeId, pageModel);
//		System.out.println(resList.size());
//		System.out.println(JSON.toJSONString(resList));
//	}
//	@Test
//	public void testOrderList(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		PageModel pageModel = new PageModel(0, 1000);
//		BigInteger userId = new BigInteger("40017");
//		List<EbuyOrderEntity> resList = ebuyService.getOrderList(userId, pageModel);
//		System.out.println(resList.size());
//		System.out.println(JSON.toJSONString(resList));
//	}
//	@Test
//	public void addDeliveryAddress(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		ebuyService.addDeliveryAddress(new BigInteger("34"), DictConstants.DELIVERY_ADDRESS_TYPE.HANDLE_ADDRESS, "test01", "12345669", 1, null, null, "一个测试地址", "广东省深圳市");
//	}
//	@Test
//	public void deleteDeliveryAddress(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		ebuyService.deleteDeliveryAddress(new BigInteger("20002"), new BigInteger("34"));
//	}
//	@Test
//	public void submitOrder(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		EbuyOrderEntity ebuyOrderEntity=ebuyService.submitOrder(new BigInteger("34"), new BigInteger("1"), new BigInteger("2"), 3, new BigInteger("1"));
//		System.out.println(JSON.toJSONString(ebuyOrderEntity));
//	}
//	@Test
//	public void getDeliveryAddressDetail(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		JSON.toJSONString(ebuyService.getDeliveryAddressDetail(new BigInteger("1"), new BigInteger("34")));
//	}
	
//	@Test
//	public void qryDeliveryAddressDetailDefault(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		EbuyDeliveryAddressEntity res=ebuyService.qryDeliveryAddressDetailDefault(new BigInteger("40006"));
//		System.out.println(JSON.toJSONString(res));
//	}
//	@Test
//	public void updateDeliveryAddress(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		EbuyDeliveryAddressEntity updres=ebuyService.updateDeliveryAddress(new BigInteger("10004"), new BigInteger("34"), 2, null, null, 1, null, null, null, null);
////		ebuyService.updateDeliveryAddress(new BigInteger("1"), new BigInteger("34"), 1, null, null, 1, null, null, null, null);
////		EbuyDeliveryAddressEntity res=ebuyService.qryDeliveryAddressDetailDefault(new BigInteger("34"));
//		System.out.println(JSON.toJSONString(updres));
//	}
//	@Test
//	public void setDefaultDeliveryAddress(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		ebuyService.setDefaultDeliveryAddress(new BigInteger("1"), new BigInteger("34"));
//	}
	
//	@Test
//	public void submitOrderMultiGroupByMerchant(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		List<ProductIdQtyEntity> productIdQtyList = new ArrayList<ProductIdQtyEntity>();
//		productIdQtyList.add(new ProductIdQtyEntity(new BigInteger("201"), 1L));
//		productIdQtyList.add(new ProductIdQtyEntity(new BigInteger("202"), 2L));
//		productIdQtyList.add(new ProductIdQtyEntity(new BigInteger("203"), 2L));
//		ebuyService.submitOrderMultiGroupByMerchant(new BigInteger("40006"), new BigInteger("40002"), productIdQtyList, null);
//	}
	
//	@Test
//	public void testOrderList(){
//		IEbuyService ebuyService = ctx.getBean("ebuyService", IEbuyService.class);
//		String lastUpdTime = ebuyService.fetchAllProductLastUpdTime(EbuyDict.PointType.EBUY_PRODUCT,null);
//		System.out.println(lastUpdTime);
//	}
	
}
