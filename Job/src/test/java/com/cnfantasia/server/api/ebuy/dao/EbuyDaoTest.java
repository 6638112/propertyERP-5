/**   
* Filename:    EbuyDaoTest.java   
* @version:    1.0  
* Create at:   2014年6月9日 上午7:27:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;

/**
 * Filename:    EbuyDaoTest.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 上午7:27:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuyDaoTest extends BaseTest{
	
	@Test
	public void testOrderList(){
		IEbuyDao ebuyDao = ctx.getBean("ebuyDao", IEbuyDao.class);
		BigInteger userId = new BigInteger("50000");
		Integer userType = 1;
		List<ProductIdQtyEntity> productIdQtyList = new ArrayList<ProductIdQtyEntity>();
		productIdQtyList.add(new ProductIdQtyEntity(new BigInteger("11"), null, new BigInteger("22"), null));
		productIdQtyList.add(new ProductIdQtyEntity(new BigInteger("177"), null, null, null));
		Integer pointType = 2;
		Object res = ebuyDao.selectBuyCarPartDetailByProdIds(userId, userType, productIdQtyList, pointType,null);
		System.out.println(JSON.toJSONString(res));
	}
}
