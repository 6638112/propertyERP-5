/**   
* Filename:    EbuyProducConversionCodeDao.java   
* @version:    1.0  
* Create at:   2015年1月12日 上午6:55:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * Filename:    EbuyProducConversionCodeDao.java
 * @version:    1.0.0
 * Create at:   2015年1月12日 上午6:55:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月12日       shiyl             1.0             1.0 Version
 */
public class EbuyProducConversionCodeDao extends AbstractBaseDao implements IEbuyProducConversionCodeDao{

	@Override
	public List<EbuyProductConversionCode> selectConversionCodeNotUsed(Integer size,BigInteger productId,BigInteger productSpecId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("size", size);
		tmpMap.put("productId", productId);
		tmpMap.put("productSpecId", productSpecId);
		return sqlSession.selectList("producConversionCode.select_ConversionCode_NotUsed", tmpMap);
	}
	
	
	@Override
	public Integer updateConversionCodeAsIsUsed(BigInteger conversionCodeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("conversionCodeId", conversionCodeId);
		return sqlSession.update("producConversionCode.update_ConversionCode_As_IsUsed", tmpMap);
	}
	
	@Override
	public Integer updateConversionCodeAsIsLocked(BigInteger conversionCodeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("conversionCodeId", conversionCodeId);
		return sqlSession.update("producConversionCode.update_ConversionCode_As_IsLocked", tmpMap);
	}
	
	@Override
	public Integer updateConversionCodeAsNotLocked(BigInteger conversionCodeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("conversionCodeId", conversionCodeId);
		return sqlSession.update("producConversionCode.update_ConversionCode_As_NotLocked", tmpMap);
	}


	
	
}
