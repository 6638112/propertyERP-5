package com.cnfantasia.server.api.ebuy.service;

import com.cnfantasia.server.api.ebuy.dao.IEbuyAdvertiseDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertiseWithRela;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.entity.IRelationAble;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.*;

public class EbuyAdvertiseService implements IEbuyAdvertiseService {
	
	private static Log logger = LogFactory.getLog(EbuyAdvertiseService.class);
	
	private IEbuyAdvertiseDao ebuyAdvertiseDao;
	

	public List<EbuyAdvertise> getEbuyAdvertiseList(String code, Integer version) {
		return ebuyAdvertiseDao.getEbuyAdvertiseList(code, version);
	}
	
	public List<EbuyAdvertise> getEbuyAdvertiseList(Map<String, Object> paramMap) {
		List<EbuyAdvertiseWithRela> adList = ebuyAdvertiseDao.getEbuyAdvertiseList(paramMap);
		return handleList(adList, EbuyAdvertise.class);
	}

	@Override
	public EbuyAdvertise getMainPromoteAdvertise(Map paramMap) {
		return ebuyAdvertiseDao.getMainPromoteAdvertise(paramMap);
	}
	
	/**
	 * 查询有拼购商品的自提点
	 * 
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> queryFightGroupProductZiti(){
		return ebuyAdvertiseDao.queryFightGroupProductZiti();
	}
	
	/**
	 * 查询有推广商品的banner
	 * 
	 * @return
	 */
	@Override
	public List<BigInteger> queryPromoteProductAdIds(){
		return ebuyAdvertiseDao.queryPromoteProductAdIds();
	}

	private static <T extends IRelationAble,Q> List<Q> handleList(List<T> tmpList, Class<Q> clazz){
		try {
			if(tmpList==null||tmpList.size()<=0){
				return new ArrayList<Q>();
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
						Q valueQ = clazz.newInstance();
						BeanUtils.copyProperties(value, valueQ);
						resList.add(valueQ);
					}
				}
			}
			return resList;
		} catch(Exception e) {
			logger.debug(e.getMessage(), e);
			return null;
		}
	}


	public void setEbuyAdvertiseDao(IEbuyAdvertiseDao ebuyAdvertiseDao) {
		this.ebuyAdvertiseDao = ebuyAdvertiseDao;
	}
	
}
