/**   
* Filename:    PrizeRuleManager.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午4:00:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleConstant;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleDict;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleDict.ConfigType;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.prizeRuleConfig.entity.PrizeRuleConfig;

/**
 * Filename:    PrizeRuleManager.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午4:00:17
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleManager implements IPrizeRuleManager{
	
	/**抽奖规则参数map缓存*/
	private Map<String,String> paramMap;
	/**折扣区间配置信息*/
	private Map<BigInteger,PrizeRuleGenerateAreaEntity> prizeAreaMap;
	/**用在线时间对应抽奖概率配置信息*/
	private List<PrizeRuleDrawOnlinedaysEntity> prizeRuleDrawOnlinedaysEntityList;
	/**不同配置对应的抽奖规则*/
	private Map<ConfigType,List<PrizeRuleGenerateConfigEntity>> prizeRuleGenerateEntityMapList;
	
	private IPrizeRuleService prizeRuleService;
	public void setPrizeRuleService(IPrizeRuleService prizeRuleService) {
		this.prizeRuleService = prizeRuleService;
	}
	
	
	@Override
	public void init() {
		//抽奖规则参数
		{
			paramMap = new HashMap<String, String>();
			List<PrizeRuleConfig> prizeRuleConfigList = prizeRuleService.getPrizeRuleConfigList();
			if(prizeRuleConfigList!=null&&prizeRuleConfigList.size()>0){
				for(PrizeRuleConfig tmpPrizeRuleConfig:prizeRuleConfigList){
					paramMap.put(tmpPrizeRuleConfig.getParamKey(), tmpPrizeRuleConfig.getParamValue());
				}
			}
		}
		//抽奖区间配置
		{
			prizeAreaMap = new HashMap<BigInteger, PrizeRuleGenerateAreaEntity>();
			List<PrizeRuleGenerateAreaEntity>  prizeAreaList= prizeRuleService.getPrizeRuleGenerateAreaList();
			if(prizeAreaList!=null&&prizeAreaList.size()>0){
				for(PrizeRuleGenerateAreaEntity tmpArea:prizeAreaList){
					prizeAreaMap.put(tmpArea.getId(), tmpArea);
				}
			}
		}
		//用在线时间对应抽奖概率配置信息
		{
			prizeRuleDrawOnlinedaysEntityList = new ArrayList<PrizeRuleDrawOnlinedaysEntity>();
			List<PrizeRuleDrawOnlinedaysEntity> tmpList = prizeRuleService.getprizeRuleDrawOnlinedaysList();
			if(tmpList!=null&&tmpList.size()>0){
				for(PrizeRuleDrawOnlinedaysEntity tmpAA:tmpList){
					prizeRuleDrawOnlinedaysEntityList.add(tmpAA);
				}
			}
		}
		
		{//不同配置对应的抽奖规则
			List<PrizeRuleGenerateConfigEntity> tmpList = prizeRuleService.getPrizeRuleGenerateUsercountList();
			if(tmpList!=null&&tmpList.size()>0){
				prizeRuleGenerateEntityMapList = null;//syl-add-2015-3-25 10:56:47  重新加载的时候 需要清空数据
				if(prizeRuleGenerateEntityMapList==null){
					prizeRuleGenerateEntityMapList = new HashMap<PrizeRuleDict.ConfigType, List<PrizeRuleGenerateConfigEntity>>();
				}
				for(PrizeRuleGenerateConfigEntity tmpAA:tmpList){
					if(ConfigType.Time_Sign.getValue().compareTo(tmpAA.getConfigType())==0){//月份 签约
						if(prizeRuleGenerateEntityMapList.get(ConfigType.Time_Sign)==null){
							prizeRuleGenerateEntityMapList.put(ConfigType.Time_Sign, new ArrayList<PrizeRuleGenerateConfigEntity>());
						}
						prizeRuleGenerateEntityMapList.get(ConfigType.Time_Sign).add(tmpAA);
						
					}else if(ConfigType.Time_UnSign.getValue().compareTo(tmpAA.getConfigType())==0){//月份 非签约
						if(prizeRuleGenerateEntityMapList.get(ConfigType.Time_UnSign)==null){
							prizeRuleGenerateEntityMapList.put(ConfigType.Time_UnSign, new ArrayList<PrizeRuleGenerateConfigEntity>());
						}
						prizeRuleGenerateEntityMapList.get(ConfigType.Time_UnSign).add(tmpAA);
						
					}else if(ConfigType.UserCount_Sign.getValue().compareTo(tmpAA.getConfigType())==0){//用户数 签约
						if(prizeRuleGenerateEntityMapList.get(ConfigType.UserCount_Sign)==null){
							prizeRuleGenerateEntityMapList.put(ConfigType.UserCount_Sign, new ArrayList<PrizeRuleGenerateConfigEntity>());
						}
						prizeRuleGenerateEntityMapList.get(ConfigType.UserCount_Sign).add(tmpAA);
						
					}else if(ConfigType.UserCount_UnSign.getValue().compareTo(tmpAA.getConfigType())==0){//用户数 非签约
						if(prizeRuleGenerateEntityMapList.get(ConfigType.UserCount_UnSign)==null){
							prizeRuleGenerateEntityMapList.put(ConfigType.UserCount_UnSign, new ArrayList<PrizeRuleGenerateConfigEntity>());
						}
						prizeRuleGenerateEntityMapList.get(ConfigType.UserCount_UnSign).add(tmpAA);
					}
				}
			}
		}
		
	}
	
	@Override
	public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateTimeConfig(String dateTime,ConfigType configType) {
		List<PrizeRuleGenerateConfigEntity> tmpList = prizeRuleGenerateEntityMapList.get(configType);
		if(tmpList!=null){
			for(PrizeRuleGenerateConfigEntity tmpRule:tmpList){
				if(tmpRule.fetchIsContain(dateTime)){
					return tmpRule;
				}
			}
		}
		throw new BusinessRuntimeException("PrizeRuleManager.getPrizeRuleGenerateTimeConfig.config.isnull",new Object[]{dateTime,configType});
	}
	
//	@Override
//	public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateUsercountConfig(Long userTotalCount,ConfigType configType) {
//		List<PrizeRuleGenerateConfigEntity> tmpList = prizeRuleGenerateEntityMapList.get(configType);
//		for(PrizeRuleGenerateConfigEntity tmpRule:tmpList){
//			if(tmpRule.fetchIsContain(userTotalCount)){
//				return tmpRule;
//			}
//		}
//		throw new BusinessRuntimeException("PrizeRuleManager.getPrizeRuleGenerateUsercountConfig.config.isnull",new Object[]{userTotalCount,configType});
//	}
	
	@Override
	public String getParaValue(String paraCode) {
		return paramMap.get(paraCode);
	}

	@Override
	public void updParaValue() {
		init();
	}
	
	@Override
	public PrizeRuleGenerateAreaEntity getPrizeAreaAConfig() {
		BigInteger prizeAreaId = new BigInteger(this.getParaValue(PrizeRuleConstant.PRIZE_AREA_A_ID)) ;
		return fetchPrizeAreaConfig(prizeAreaId);
	}
	
	@Override
	public PrizeRuleGenerateAreaEntity getPrizeAreaBConfig() {
		BigInteger prizeAreaId = new BigInteger(this.getParaValue(PrizeRuleConstant.PRIZE_AREA_B_ID)) ;
		return fetchPrizeAreaConfig(prizeAreaId);
	}

	@Override
	public PrizeRuleGenerateAreaEntity getPrizeAreaCConfig() {
		BigInteger prizeAreaId = new BigInteger(this.getParaValue(PrizeRuleConstant.PRIZE_AREA_C_ID)) ;
		return fetchPrizeAreaConfig(prizeAreaId);
	}

	@Override
	public PrizeRuleGenerateAreaEntity getPrizeAreaDConfig() {
		BigInteger prizeAreaId = new BigInteger(this.getParaValue(PrizeRuleConstant.PRIZE_AREA_D_ID)) ;
		return fetchPrizeAreaConfig(prizeAreaId);
	}
	
	@Override
	public PrizeRuleGenerateAreaEntity getPrizeAreaEConfig() {
		BigInteger prizeAreaId = new BigInteger(this.getParaValue(PrizeRuleConstant.PRIZE_AREA_E_ID)) ;
		return fetchPrizeAreaConfig(prizeAreaId);
	}
	
	private PrizeRuleGenerateAreaEntity fetchPrizeAreaConfig(BigInteger prizeAreaId){
		PrizeRuleGenerateAreaEntity resArea = prizeAreaMap.get(prizeAreaId);
		if(resArea==null){
			throw new BusinessRuntimeException("PrizeRuleManager.getPrizeAreaConfig.isnull",new Object[]{prizeAreaId});
		}
		return resArea;
	}

	@Override
	public PrizeRuleDrawOnlinedaysEntity getPrizeRuleDrawOnlinedays(Long onlineDays) {
		for(PrizeRuleDrawOnlinedaysEntity tmpPrizeRuleDrawOnlinedaysEntity:prizeRuleDrawOnlinedaysEntityList){
			if(tmpPrizeRuleDrawOnlinedaysEntity.fetchIsContain(onlineDays)){
				return tmpPrizeRuleDrawOnlinedaysEntity;
			}
		}
		throw new BusinessRuntimeException("PrizeRuleManager.getPrizeRuleDrawOnlinedays.config.isnull",new Object[]{onlineDays});
	}
	
//	@Override
//	public DiscountValueEntity getUnPrizeDiscount() {
//		Long unPrizeDisCount = Long.valueOf(this.getParaValue(PrizeRuleConstant.UN_PRIZE_DISCOUNT));
//		DiscountValueEntity discountValueEntity = new DiscountValueEntity(unPrizeDisCount);
//		return discountValueEntity;
//	}
//
//	@Override
//	public DiscountValueEntity getPrizeDiscountMax() {
//		Long unPrizeDisCount = Long.valueOf(this.getParaValue(PrizeRuleConstant.PRIZE_DISCOUNT_MAX));
//		DiscountValueEntity discountValueEntity = new DiscountValueEntity(unPrizeDisCount);
//		return discountValueEntity;
//	}
	
	@Override
	public  DiscountValueEntity getUnPrizeDiscount() {
		Long unPrizeDisCount = Long.valueOf(this.getParaValue(PrizeRuleConstant.UN_PRIZE_DISCOUNT));
		DiscountValueEntity discountValueEntity = new DiscountValueEntity(unPrizeDisCount);
		return discountValueEntity;
	}
	@Override
	public  DiscountValueEntity getPrizeDiscountMax() {
		Long unPrizeDisCount = Long.valueOf(this.getParaValue(PrizeRuleConstant.PRIZE_DISCOUNT_MAX));
		DiscountValueEntity discountValueEntity = new DiscountValueEntity(unPrizeDisCount);
		return discountValueEntity;
	}
	@Override
	public  int getMaxPrizeCount(){
		Integer maxPrizeCount = Integer.valueOf(this.getParaValue(PrizeRuleConstant.MAX_PRIZE_COUNT_DAY));
		return maxPrizeCount;
	}
	@Override
	public  Long getRuleA1(){
		Long rualA1 = Long.valueOf(this.getParaValue(PrizeRuleConstant.PRIZE_CONVERT_RULE_A1));
		return rualA1;
	}
	@Override
	public  Long getRuleD(){
		Long rualD = Long.valueOf(this.getParaValue(PrizeRuleConstant.PRIZE_CONVERT_RULE_D));
		return rualD;
	}

	@Override
	public DiscountValueEntity getPrizeLastCheckDiscount() {
		Long unPrizeDisCount = Long.valueOf(this.getParaValue(PrizeRuleConstant.PRIZE_LASTCHECK_DISCOUNT));
		DiscountValueEntity discountValueEntity = new DiscountValueEntity(unPrizeDisCount);
		return discountValueEntity;
	}

}
