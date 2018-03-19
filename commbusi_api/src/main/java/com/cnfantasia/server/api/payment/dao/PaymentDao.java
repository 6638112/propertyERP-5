/**   
* Filename:    PaymentDao.java   
* @version:    1.0  
* Create at:   2015年10月14日 下午4:29:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

/**
 * Filename:    PaymentDao.java
 * @version:    1.0.0
 * Create at:   2015年10月14日 下午4:29:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月14日       shiyl             1.0             1.0 Version
 */
public class PaymentDao extends AbstractBaseDao implements IPaymentDao{
	
	@Override
	public Integer upadteOrderIsClientPayByOrderNo(String billNo) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderNo", billNo);
		return sqlSession.update("payment.upadte_Order_IsClientPay_ByOrderNo", tmpMap);
	}

	@Override
	public Integer selectOrderPayStatus(BigInteger orderId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		return sqlSession.selectOne("payment.select_Order_PayStatus", tmpMap);
	}
	
}
