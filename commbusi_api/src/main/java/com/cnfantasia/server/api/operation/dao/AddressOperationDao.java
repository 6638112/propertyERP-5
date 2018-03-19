/**   
* Filename:    AddressOperationDao.java   
* @version:    1.0  
* Create at:   2015年7月1日 下午1:16:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.entity.CommunityAdsWithRela;
import com.cnfantasia.server.api.operation.entity.IRelationAble;
import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiUniqueCodeEntity;
import com.cnfantasia.server.api.operation.entity.OperationHomeSupplyTypeWithRela;
import com.cnfantasia.server.api.operation.util.OperationUtil;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * Filename:    AddressOperationDao.java
 * @version:    1.0.0
 * Create at:   2015年7月1日 下午1:16:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月1日       shiyl             1.0             1.0 Version
 */
public class AddressOperationDao extends AbstractBaseDao implements IAddressOperationDao{
	
	@Override
	public void cleanCache() {
		sqlSession.selectOne("operation.cleanCache");
	}
	
	@Override
	public MultiUniqueCodeEntity selectUniqueCodeList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId){
			//补齐各级别数据Id
			AddressIdEntity addressIdEntity = null;
			if(gbId!=null){
				addressIdEntity = sqlSession.selectOne("operation.select_addressIdEntity_ByGbId",gbId);
			}else if(blockId!=null){
				addressIdEntity = sqlSession.selectOne("operation.select_addressIdEntity_ByBlockId",blockId);
			}else if(cityId!=null){
				addressIdEntity = sqlSession.selectOne("operation.select_addressIdEntity_ByCityId",cityId);
			}else if(provinceId!=null){
				addressIdEntity = sqlSession.selectOne("operation.select_addressIdEntity_ByProvinceId",provinceId);
			}else if(countryId!=null){}
		return OperationUtil.generateUniqueCodeList(addressIdEntity);
	}

	@Override
	public AddressIdEntity getAddressIdEntity(String city, String block) {
		Map<String, Object> para = new HashMap<>();
		para.put("city", city);
		para.put("block", block);
		return sqlSession.selectOne("operation.getAddressIdEntity", para);
	}

	@Override
	public MultiSaIdEntity selectCodeIdMultiEntity(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId) {
		MultiUniqueCodeEntity ucEntity = selectUniqueCodeList(countryId, provinceId, cityId, blockId, gbId);
		//填充SaId数据
		MultiSaIdEntity msiEntity = new MultiSaIdEntity();
		if(ucEntity!=null){
			msiEntity.setCountrySaId(StringUtils.isEmpty(ucEntity.getCountryUniqueCode())?null:RedisCacheHandler.hgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid, ucEntity.getCountryUniqueCode()));
			msiEntity.setProvinceSaId(StringUtils.isEmpty(ucEntity.getProvinceUniqueCode())?null:RedisCacheHandler.hgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid, ucEntity.getProvinceUniqueCode()));
			msiEntity.setCitySaId(StringUtils.isEmpty(ucEntity.getCityUniqueCode())?null:RedisCacheHandler.hgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid, ucEntity.getCityUniqueCode()));
			msiEntity.setBlockSaId(StringUtils.isEmpty(ucEntity.getBlockUniqueCode())?null:RedisCacheHandler.hgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid, ucEntity.getBlockUniqueCode()));
			msiEntity.setGbSaId(StringUtils.isEmpty(ucEntity.getGbUniqueCode())?null:RedisCacheHandler.hgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid, ucEntity.getGbUniqueCode()));
		}
		return msiEntity;
	}
	
	@Override
	public List<BigInteger> selectCodeIdList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId) {
		MultiUniqueCodeEntity ucEntity = selectUniqueCodeList(countryId, provinceId, cityId, blockId, gbId);
		if(ucEntity == null){
			ucEntity = new MultiUniqueCodeEntity();
			ucEntity.setCountryUniqueCode("-1");
		}
		List<String> uniqueCodeList = ucEntity.getTotalList();//生成目标编码
		if(uniqueCodeList==null||uniqueCodeList.size()<=0){
			return null;
		}
		//根据目标编码获取到唯一IdList
//		return sqlSession.selectList("operation.select_CodeId_List", uniqueCodeList);
		return RedisCacheHandler.hmgetBigInteger(CacheConstant.ModelCode.hset_sa_codeid,uniqueCodeList.toArray(new String[uniqueCodeList.size()]));
	}
	
	@Override
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantList(List<BigInteger> codeIdList) {
		if(codeIdList==null||codeIdList.size()<=0){
			return null;
		}
		return sqlSession.selectList("operation.select_EbuySupplyMerchant_List", codeIdList);
	}

	@Override
	public List<OperationServiceArea> selectOperationServiceAreaAll() {
		return sqlSession.selectList("operation.select_OperationServiceArea_All");
	}

	@Override
	public List<CommunityAds> selectCommunityAdsList(List<BigInteger> codeIdList) {
		if(codeIdList==null||codeIdList.size()<=0){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("list", codeIdList);
		paramMap.put("version", HeaderManager.getVersionNum());
		List<CommunityAdsWithRela> tmpList = sqlSession.selectList("operation.select_CommunityAds_List2", paramMap);
		return handleList(tmpList,CommunityAds.class);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends IRelationAble,Q> List<Q> handleList(List<T> tmpList, Class<Q> clazz){
		if(tmpList==null||tmpList.size()<=0){
			return null;
		}
		List<Q> resList = new ArrayList<Q>();
		{
			Set<BigInteger> uncludeList = new HashSet<BigInteger>();
			Map<BigInteger,T> includeMap = new LinkedHashMap<BigInteger, T>();//保持顺序
			for(T tmpSignal:tmpList){
				if(OperationDict.OperationSaHasEbSupply_relation.UNCLUDE.compareTo(tmpSignal.getRelationValue())==0){
					uncludeList.add(tmpSignal.getUniqueId());
				}else{
					includeMap.put(tmpSignal.getUniqueId(), tmpSignal);
				}
			}
			for(Map.Entry<BigInteger,T> tmpEntry:includeMap.entrySet()){
				BigInteger key = tmpEntry.getKey();
				T value = tmpEntry.getValue();
				if(uncludeList.size()<=0||!uncludeList.contains(key)){//unclude优先
					resList.add((Q)value);
				}
			}
		}
		return resList;
	}

	@Override
	public List<OperationHomeSupplyType> selectOperationHomeSupplyTypeList(List<BigInteger> codeIdList) {
		if(codeIdList==null||codeIdList.size()<=0){
			return null;
		}
		Integer version = HeaderManager.getVersionNum();
		if(version==null){version = 1;}//syl-2015-12-9 13:21:48增加默认最低版本
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("version", version);
		paramMap.put("codeIdList", codeIdList);
		List<OperationHomeSupplyTypeWithRela> tmpList = sqlSession.selectList("operation.select_OperationHomeSupplyType_List", paramMap);
		return handleList(tmpList,OperationHomeSupplyType.class);
	}

	
}
