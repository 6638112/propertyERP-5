/**   
* Filename:    EbuyProducConversionCodeService.java   
* @version:    1.0  
* Create at:   2015年1月12日 上午4:23:59   
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

import com.cnfantasia.server.api.ebuy.dao.IEbuyProducConversionCodeDao;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * Filename:    EbuyProducConversionCodeService.java
 * @version:    1.0.0
 * Create at:   2015年1月12日 上午4:23:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月12日       shiyl             1.0             1.0 Version
 */
public class EbuyProducConversionCodeService implements IEbuyProducConversionCodeService{
	private IEbuyProducConversionCodeDao ebuyProducConversionCodeDao;
	public void setEbuyProducConversionCodeDao(IEbuyProducConversionCodeDao ebuyProducConversionCodeDao) {
		this.ebuyProducConversionCodeDao = ebuyProducConversionCodeDao;
	}
	
	
	@Override
	public List<EbuyProductConversionCode> fetchConversionCode(BigInteger productId,BigInteger productSpecId,Integer size) {
		List<EbuyProductConversionCode> resCodeList =  ebuyProducConversionCodeDao.selectConversionCodeNotUsed(size, productId, productSpecId);
		if(resCodeList==null){
			throw new BusinessRuntimeException("ProducConversionCodeService.fetchConversionCode.resNull");
		}else if(resCodeList.size()!=size){
			throw new BusinessRuntimeException("ProducConversionCodeService.fetchConversionCode.resNotEnough",new Object[]{resCodeList.size()});
		}
		return resCodeList;
	}

	@Override
	public EbuyProductConversionCode fetchConversionCode(BigInteger productId,BigInteger productSpecId) {
		EbuyProductConversionCode resCode = null;
		Integer size = 1;
		List<EbuyProductConversionCode> tmpList = fetchConversionCode(productId, productSpecId, size);
		if(tmpList!=null&&tmpList.size()>0){
			resCode = tmpList.get(0);
		}
		if(resCode==null){
			throw new BusinessRuntimeException("ProducConversionCodeService.fetchConversionCode.resNull");
		}
		return resCode;
	}
	
	@Override
	public EbuyProductConversionCode fetchConversionCodeAndMarkAsLocked(BigInteger productId, BigInteger productSpecId) {
		EbuyProductConversionCode tmpCode = fetchConversionCode(productId, productSpecId);
		markConversionCodeAsIsLocked(tmpCode.getId());
		return tmpCode;
	}
	
	@Override
	public void markConversionCodeAsIsUsed(BigInteger conversionCodeId) {
		Integer resCount =  ebuyProducConversionCodeDao.updateConversionCodeAsIsUsed(conversionCodeId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("ProducConversionCodeService.markConversionCodeAsIsUsed.failed");
		}
	}

	@Override
	public void markConversionCodeAsIsLocked(BigInteger conversionCodeId) {
		Integer resCount =  ebuyProducConversionCodeDao.updateConversionCodeAsIsLocked(conversionCodeId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("ProducConversionCodeService.markConversionCodeAsIsLocked.failed");
		}
	}

	@Override
	public void markConversionCodeAsNotLocked(BigInteger conversionCodeId) {
		Integer resCount =  ebuyProducConversionCodeDao.updateConversionCodeAsNotLocked(conversionCodeId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("ProducConversionCodeService.markConversionCodeAsNotLocked.failed");
		}
	}
	
}
