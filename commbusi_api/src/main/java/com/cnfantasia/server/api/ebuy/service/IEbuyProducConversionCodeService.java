/**   
* Filename:    IEbuyProducConversionCodeService.java   
* @version:    1.0  
* Create at:   2015年1月12日 上午4:23:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * Filename:    IEbuyProducConversionCodeService.java
 * @version:    1.0.0
 * Create at:   2015年1月12日 上午4:23:44
 * Description:兑换码服务类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月12日       shiyl             1.0             1.0 Version
 */
public interface IEbuyProducConversionCodeService {
	
	/**
	 * 获取未使用的兑换码
	 * @param size
	 * @return
	 */
	public List<EbuyProductConversionCode> fetchConversionCode(BigInteger productId,BigInteger productSpecId,Integer size);
	public EbuyProductConversionCode fetchConversionCode(BigInteger productId,BigInteger productSpecId);
	public EbuyProductConversionCode fetchConversionCodeAndMarkAsLocked(BigInteger productId,BigInteger productSpecId);
	
	/**
	 * 未使用的兑换码【"1":"未兑换","2":"已锁定"】标记为已使用
	 * @param conversionCodeId
	 * @return
	 */
	public void markConversionCodeAsIsUsed(BigInteger conversionCodeId);
	
	/**
	 * 未使用的兑换码【"1":"未兑换"】标记为已锁定
	 * @param conversionCodeId
	 * @return
	 */
	public void markConversionCodeAsIsLocked(BigInteger conversionCodeId);
	
	/**
	 * 已锁定的兑换码【"2":"已锁定"】标记为未使用
	 * @param conversionCodeId
	 */
	public void markConversionCodeAsNotLocked(BigInteger conversionCodeId);
	
}
