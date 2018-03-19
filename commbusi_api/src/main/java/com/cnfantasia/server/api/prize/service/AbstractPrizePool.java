/**   
* Filename:    AbstractPrizePool.java   
* @version:    1.0  
* Create at:   2015年1月19日 上午6:20:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.prize.dao.IPrizeDao;
import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prize.entity.MultiPrizeArea;
import com.cnfantasia.server.api.prize.entity.PrizeArea;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.util.PrizeAreaInitUtil;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleConstant;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * Filename:    AbstractPrizePool.java
 * @version:    1.0.0
 * Create at:   2015年1月19日 上午6:20:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月19日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractPrizePool implements IPrizePool{
	private Log logger = LogFactory.getLog(getClass());
	
//	private static final DiscountValueEntity[] defaultDiscountArr = new DiscountValueEntity[]{new DiscountValueEntity(99L,10L),new DiscountValueEntity(98L,10L),new DiscountValueEntity(97L,10L),new DiscountValueEntity(96L,10L),new DiscountValueEntity(95L,10L),};
	private static final int defaultLength = 100;
	private static final DiscountValueEntity[] defaultDiscountArr = new DiscountValueEntity[defaultLength];
	static{
		for(int i=0;i<defaultLength;i++){//初始化随机奖池
			defaultDiscountArr[i] = new DiscountValueEntity((long)i,10L);
		}
	}
	protected MultiPrizeArea prizes;
	protected PrizeArea prizeAreaA;
	protected PrizeArea prizeAreaB;
	protected PrizeArea prizeAreaC;
	protected PrizeArea prizeAreaD;
	protected PrizeArea prizeAreaE;
	/**缓存当前使用的抽奖规则*/
	protected PrizeRuleGenerateConfigEntity prizeRuleGenerateConfig;
	
	protected IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	protected IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	protected ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
	protected IPrizeDao prizeDao;
	public void setPrizeDao(IPrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}
	
	@Override
	public void init() {
		initPrizeRuleGenerateConfig();
		initDiscount();
	}
	
	/**
	 * 查询当前月份已经抽签的折扣
	 * @return
	 */
	protected abstract List<PrizeRecord> getPrizeRecordCurrMonth();
	
	protected void initDiscount(){
		List<Double> doneCountTotalList = new ArrayList<Double>();
		{//查询已抽奖品
			List<PrizeRecord> donePrizeRecList = getPrizeRecordCurrMonth();
			if(donePrizeRecList!=null){
				for(PrizeRecord tmp:donePrizeRecList){
					if(tmp==null||tmp.getDiscount()==null){continue;}
					doneCountTotalList.add(tmp.getDiscount());
				}
			}
		}
		//查询总的奖项数量
		Long totalCount = getPrizeCountTotal();
		{//初始折扣A区间信息
			PrizeRuleGenerateAreaEntity areaConfig = prizeRuleManager.getPrizeAreaAConfig();
			Double percent = prizeRuleGenerateConfig.fetchAreaAPercentDiv10000().doubleValue();		
			prizeAreaA = PrizeAreaInitUtil.generatePrizeArea(areaConfig, totalCount, percent, doneCountTotalList);
		}
		{//初始折扣B区间信息
			PrizeRuleGenerateAreaEntity areaConfig = prizeRuleManager.getPrizeAreaBConfig();
			Double percent = prizeRuleGenerateConfig.fetchAreaBPercentDiv10000().doubleValue();		
			prizeAreaB = PrizeAreaInitUtil.generatePrizeArea(areaConfig, totalCount, percent, doneCountTotalList);
		}
		{//初始折扣C区间信息
			PrizeRuleGenerateAreaEntity areaConfig = prizeRuleManager.getPrizeAreaCConfig();
			Double percent = prizeRuleGenerateConfig.fetchAreaCPercentDiv10000().doubleValue();		
			prizeAreaC = PrizeAreaInitUtil.generatePrizeArea(areaConfig, totalCount, percent, doneCountTotalList);
		}
		{//初始折扣D区间信息
			PrizeRuleGenerateAreaEntity areaConfig = prizeRuleManager.getPrizeAreaDConfig();
			Double percent = prizeRuleGenerateConfig.fetchAreaDPercentDiv10000().doubleValue();		
			prizeAreaD = PrizeAreaInitUtil.generatePrizeArea(areaConfig, totalCount, percent, doneCountTotalList);
		}
		{//初始折扣E区间信息
			PrizeRuleGenerateAreaEntity areaConfig = prizeRuleManager.getPrizeAreaEConfig();
			Double percent = prizeRuleGenerateConfig.fetchAreaEPercentDiv10000().doubleValue();		
			prizeAreaE = PrizeAreaInitUtil.generatePrizeArea(areaConfig, totalCount, percent, doneCountTotalList);
		}
		
		{//设置奖品池
			prizes=new MultiPrizeArea();
			prizes.append(prizeAreaA, 0, prizeAreaA.getPrizeInitCount());
			prizes.append(prizeAreaB, 0, prizeAreaB.getPrizeInitCount());
			prizes.append(prizeAreaC, 0, prizeAreaC.getPrizeInitCount());
			prizes.append(prizeAreaD, 0, prizeAreaD.getPrizeInitCount());
			prizes.append(prizeAreaE, 0, prizeAreaE.getPrizeInitCount());
		}
		
		if(logger.isInfoEnabled()){
			logger.info("The prizepool "+getClass()+" init info is:");
			logger.info("left count is :"+prizes.getTotalCount());
			logger.info("MultiPrizeArea show Data ------start------");
			String showStr = prizes.showData();
			logger.info(showStr);
			logger.info("MultiPrizeArea show Data ------end------");
		}
		
	}
	
	@Override
	public void reloadConfig() {
		init();//syl-add-2015-3-25 10:40:55
//			initDiscount();//syl-del-2015-3-25 10:41:06
	}

	@Override
	public PrizeRuleGenerateConfigEntity getCurrPrizeRuleGenerateConfig() {
		if(prizeRuleGenerateConfig==null){
			initPrizeRuleGenerateConfig();
		}
		return prizeRuleGenerateConfig;
	}
	
	@Override
	public MultiPrizeArea getMultiPrizeArea() {
		return prizes;
	}

	@Override
	public synchronized int insertPrizeData(List<DiscountValueEntity> allDiscountValueList) {
		if(allDiscountValueList==null||allDiscountValueList.size()<=0){return 0;}
		int totalSize = 0;
		List<DiscountValueEntity> toAddListA = new ArrayList<DiscountValueEntity>();
		List<DiscountValueEntity> toAddListB = new ArrayList<DiscountValueEntity>();
		List<DiscountValueEntity> toAddListC = new ArrayList<DiscountValueEntity>();
		List<DiscountValueEntity> toAddListD = new ArrayList<DiscountValueEntity>();
		List<DiscountValueEntity> toAddListE = new ArrayList<DiscountValueEntity>();
		for(int i=0;i<allDiscountValueList.size();i++){
			DiscountValueEntity tmpDiscount = allDiscountValueList.get(i);
			if(tmpDiscount.equals(DiscountValueEntity.unPrizedEntity)){
				continue;//如果是未中奖的折扣则跳过处理
			}
			if(prizeAreaA.isInArea(tmpDiscount)){toAddListA.add(tmpDiscount);continue;}
			if(prizeAreaB.isInArea(tmpDiscount)){toAddListB.add(tmpDiscount);continue;}
			if(prizeAreaC.isInArea(tmpDiscount)){toAddListC.add(tmpDiscount);continue;}
			if(prizeAreaD.isInArea(tmpDiscount)){toAddListD.add(tmpDiscount);continue;}
			if(prizeAreaE.isInArea(tmpDiscount)){toAddListE.add(tmpDiscount);continue;}
		}
		if(toAddListA!=null&&toAddListA.size()>0){totalSize+=prizeAreaA.insertDiscount(toAddListA);}
		if(toAddListB!=null&&toAddListB.size()>0){totalSize+=prizeAreaB.insertDiscount(toAddListB);}
		if(toAddListC!=null&&toAddListC.size()>0){totalSize+=prizeAreaC.insertDiscount(toAddListC);}
		if(toAddListD!=null&&toAddListD.size()>0){totalSize+=prizeAreaD.insertDiscount(toAddListD);}
		if(toAddListE!=null&&toAddListE.size()>0){totalSize+=prizeAreaE.insertDiscount(toAddListE);}
		return totalSize;
	}

	/**
	 * 抽奖过程描述：
	 * 1.构造一个临时的奖品池（包含各类折扣的中奖概率，根据用户登录时间设定）
	 * 2.用户在此奖池中抽奖，若中奖则继续
	 * 3.中奖后，用户从临时的奖池中找到对应的prizeArea,然后在prizeArea中执行抽奖
	 * 4.返回在prizeArea中获取的折扣
	 * 所有折扣抽完时的数据状况是：prizes保存有所有prizeArea信息，prizeArea中的所有折扣为未中奖的取值。
	 */
	@Override
	public synchronized PrizeResultDiscountEntity doPrize(Boolean firstPrize,int onLineDays,int totalPrizeTimes,PrizeRecord currLeastPrizeRecord){
		MultiPrizeArea tmpPrizes = new MultiPrizeArea();
		if(firstPrize){
			PrizeRuleDrawOnlinedaysEntity onlineConfig =prizeRuleManager.getPrizeRuleDrawOnlinedays(onLineDays+0L);
			tmpPrizes.append(this.prizeAreaD, 0, (int)(this.prizeAreaD.getPrizeInitCount()*onlineConfig.fetchDPercentDiv10000().doubleValue()));
			if(this.prizeAreaD.getLeftCount()<=0){
				tmpPrizes.append(this.prizeAreaE, 0, (int)(this.prizeAreaE.getPrizeInitCount()*onlineConfig.fetchEPercentDiv10000().doubleValue()));
			}
			totalPrizeTimes = tmpPrizes.getTotalCount();//设定总数确保中奖
		}else{
			PrizeRuleDrawOnlinedaysEntity onlineConfig =prizeRuleManager.getPrizeRuleDrawOnlinedays(onLineDays+0L);
			tmpPrizes.append(this.prizeAreaA, 0, (int)(this.prizeAreaA.getPrizeInitCount()*onlineConfig.fetchAPercentDiv10000().doubleValue()));
			tmpPrizes.append(this.prizeAreaB, 0, (int)(this.prizeAreaB.getPrizeInitCount()*onlineConfig.fetchBPercentDiv10000().doubleValue()));
			tmpPrizes.append(this.prizeAreaC, 0, (int)(this.prizeAreaC.getPrizeInitCount()*onlineConfig.fetchCPercentDiv10000().doubleValue()));
			tmpPrizes.append(this.prizeAreaD, 0, (int)(this.prizeAreaD.getPrizeInitCount()*onlineConfig.fetchDPercentDiv10000().doubleValue()));
			tmpPrizes.append(this.prizeAreaE, 0, (int)(this.prizeAreaE.getPrizeInitCount()*onlineConfig.fetchEPercentDiv10000().doubleValue()));
		}
		int tmpPrizeRes =  (int)(Math.random()*totalPrizeTimes);
		int multiPrizeAreaCount = tmpPrizes.getTotalCount();
		DiscountValueEntity discountValueRes = null;
		Boolean isTmpGenerate = null;
		if(tmpPrizeRes>=multiPrizeAreaCount){
			discountValueRes = DiscountValueEntity.unPrizedEntity;
		}else{
			DiscountValueEntity currDiscountRes = tmpPrizes.getDiscount(tmpPrizeRes);
			discountValueRes = currDiscountRes;
		}
		if(discountValueRes.equals(DiscountValueEntity.unPrizedEntity)){
			DiscountValueEntity prizeLastCheckDiscount = prizeRuleManager.getPrizeLastCheckDiscount();
			Double value9_5 = prizeLastCheckDiscount.doubleValue();
			Integer value95 = prizeLastCheckDiscount.multiplyValue(10L);
			Integer randomIndex = null;
			if(currLeastPrizeRecord!=null&&currLeastPrizeRecord.getDiscount()!=null){
				DiscountValueEntity currLeast = new DiscountValueEntity((long)(currLeastPrizeRecord.getDiscount()*PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM),PrizeRuleConstant.PRIZE_DISCOUNT_DIVNUM);
				if(currLeast.doubleValue().compareTo(Double.valueOf(value9_5))>0){//查询用户当前最低折扣X，如果X大于9.5  9.5-9.9
					randomIndex = new Random().nextInt(100-value95)+value95;
				}else{//如果X小于等于9.5  X-9.9 之间随机
					int startIndex = (int)((currLeast.doubleValue()+0.1)*10);
					randomIndex = new Random().nextInt(100-startIndex)+startIndex;
				}
			}else{//为空则从9.5-9.9取值
				randomIndex = new Random().nextInt(100-value95)+value95;
			}
			discountValueRes = defaultDiscountArr[randomIndex];
			isTmpGenerate = true;
		}else{
			isTmpGenerate = false;
		}
		PrizeResultDiscountEntity resEntity = new PrizeResultDiscountEntity(discountValueRes, isTmpGenerate);
		return resEntity;
	}
	
}
