/**   
* Filename:    AddressOperationService.java   
* @version:    1.0  
* Create at:   2015年7月1日 下午1:12:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.communitySupplyType.CommunitySupplyTypeDataReload;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.dao.IAddressOperationDao;
import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.entity.OperationHomeSupplyTypeEntity;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * Filename:    AddressOperationService.java
 * @version:    1.0.0
 * Create at:   2015年7月1日 下午1:12:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月1日       shiyl             1.0             1.0 Version
 */
public class AddressOperationService implements IAddressOperationService{
	private IAddressOperationDao addressOperationDao;
	public void setAddressOperationDao(IAddressOperationDao addressOperationDao) {
		this.addressOperationDao = addressOperationDao;
	}
	
	@Override
	public List<BigInteger> getCodeIdList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId) {
		return addressOperationDao.selectCodeIdList(countryId, provinceId, cityId, blockId, gbId);
	}
	
	/**
	 * demo
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantList(BigInteger countryId, BigInteger provinceId,
			BigInteger cityId, BigInteger blockId, BigInteger gbId) {
		List<BigInteger> codeIdList = getCodeIdList(countryId, provinceId, cityId, blockId, gbId);
		return addressOperationDao.selectEbuySupplyMerchantList(codeIdList);
	}
	
	/**
	 * demo
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantListByGbId(BigInteger gbId) {
		List<BigInteger> codeIdList = getCodeIdList(null, null, null, null, gbId);
		return addressOperationDao.selectEbuySupplyMerchantList(codeIdList);
	}

	
	@Override
	public List<CommunityAds> getCommunityAdsListByGbId(BigInteger gbId) {
		List<BigInteger> codeIdList = getCodeIdList(null, null, null, null, gbId);
		return addressOperationDao.selectCommunityAdsList(codeIdList);
	}

	@Override
	public List<OperationHomeSupplyTypeEntity> getOperationHomeSupplyTypeList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId) {
			//获取codeIdList
			List<BigInteger> codeIdList = getCodeIdList(countryId, provinceId, cityId, blockId, gbId);
			//数据库取数
			List<OperationHomeSupplyType> baseDataList = addressOperationDao.selectOperationHomeSupplyTypeList(codeIdList);
			//结果处理
			List<OperationHomeSupplyTypeEntity> resList = new ArrayList<OperationHomeSupplyTypeEntity>();
			if(baseDataList!=null&&baseDataList.size()>0){
				for(OperationHomeSupplyType baseData:baseDataList){
					CommunitySupplyType communitySupplyType = null;
					if(OperationDict.OperationHomeSupplyType_DataType.common_Type.compareTo(baseData.getDataType())==0
							&&baseData.getSupplyTypeId()!=null){//从Redis缓存中取数据
						BigInteger supplyTypeId = baseData.getSupplyTypeId();
						if(supplyTypeId==null){//错误数据跳过处理
							continue;
						}
						CommunitySupplyTypeDataReload.reloadIfNotExist();
						String existJsonData = RedisCacheHandler.hget(CacheConstant.ModelCode.hset_commSupplyType_info, supplyTypeId+"");
						communitySupplyType = JSON.parseObject(existJsonData, CommunitySupplyType.class);
					}
					resList.add(new OperationHomeSupplyTypeEntity(baseData, communitySupplyType));
				}
			}
			return resList;
	}

	@Override
	public AddressIdEntity getAddressIdEntity(String city, String block) {
		return addressOperationDao.getAddressIdEntity(city, block);
	}

	@Override
	public List<OperationHomeSupplyTypeEntity> getOperationHomeSupplyTypeListByGbId(BigInteger gbId) {
//		//获取codeIdList
//		List<BigInteger> codeIdList = getCodeIdList(null, null, null, null, gbId);
		return getOperationHomeSupplyTypeList(null, null, null, null, gbId);
	}
	
	
	@Override
	public MultiSaIdEntity getCodeIdMultiEntity(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId) {
		return addressOperationDao.selectCodeIdMultiEntity(countryId, provinceId, cityId, blockId, gbId);
	}
	
	
}
