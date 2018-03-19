package com.cnfantasia.server.commbusi.microblogQueue.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.microblog.constant.MicroblogConstant;
import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.cache.McVisitRecordCache;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueDict;
import com.cnfantasia.server.commbusi.microblogQueue.dao.IMicroblogQueueDao;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GbIdAndPayDistance;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsRowDataEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsShellEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeDymPriority;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeSendCount;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.commbusi.microblogQueue.util.MicroblogQueueUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.microblogContent.dao.IMicroblogContentBaseDao;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogGbHasQueue.dao.IMicroblogGbHasQueueBaseDao;
import com.cnfantasia.server.domainbase.microblogGbHasQueue.entity.MicroblogGbHasQueue;
import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * Redis缓存每个小区当天推送的消息类别，消息数，若无则重新从DB加载
 * Redis的缓存用于获取各小区消息类别的优先级
 * @author shiyl
 *
 */
public class MicroblogQueueTask implements IMicroblogQueueTask{
	private Log logger = LogFactory.getLog(getClass());
	
	private IMicroblogQueueDao microblogQueueDao;
	public void setMicroblogQueueDao(IMicroblogQueueDao microblogQueueDao) {
		this.microblogQueueDao = microblogQueueDao;
	}
	
	private IMicroblogGbHasQueueBaseDao microblogGbHasQueueBaseDao;
	public void setMicroblogGbHasQueueBaseDao(IMicroblogGbHasQueueBaseDao microblogGbHasQueueBaseDao) {
		this.microblogGbHasQueueBaseDao = microblogGbHasQueueBaseDao;
	}
	
	private IMicroblogContentBaseDao microblogContentBaseDao;
	public void setMicroblogContentBaseDao(IMicroblogContentBaseDao microblogContentBaseDao) {
		this.microblogContentBaseDao = microblogContentBaseDao;
	}
	
	private IAddressOperationService addressOperationService;
	public void setAddressOperationService(IAddressOperationService addressOperationService) {
		this.addressOperationService = addressOperationService;
	}
	
	/**
	 * 判断当天是否为某些小区(可缴纳物业费)上个月物业缴费结束的下一天若是，则将这些小区的上个月的缴费喜报信息创建存入待发送队列中
	 * 每天执行检查一次
	 */
	@Override
	public void executePropertyGoodNewsPush(){
		logger.info("MicroblogQueueTask executePropertyGoodNewsPush start...");
		//1.查询可用的小区列表
		List<GBWithPropMonthYear> canPayGBList = microblogQueueDao.selectCanPayGbYesterdayEndList(MicroblogQueueDict.MQ_Level.Code_40);
		//2.逐个取小区的喜报数据，若无则跳过。
		if(canPayGBList!=null&&canPayGBList.size()>0){
			for(GBWithPropMonthYear gbInfo:canPayGBList){
				if(DefaultGbUtil.checkIsDefaultGb(gbInfo.getGbId())){continue;}
				Long totalPayUserCount = microblogQueueDao.selectPayUserCount(gbInfo.getGbId(), gbInfo.getPropPayDateBegin(), gbInfo.getPropPayDateEnd());
				if(totalPayUserCount==null||totalPayUserCount<=0){continue;}
				List<PropertyGoodNewsRowDataEntity> rowDataList = microblogQueueDao.selectGoodNewsDataList(gbInfo.getGbId(), gbInfo.getPropYear(), gbInfo.getPropMonth(), gbInfo.getPropPayDateBegin(), gbInfo.getPropPayDateEnd());
				if(rowDataList==null||rowDataList.size()<=0){continue;}//3.有，则取出数据
				PropertyGoodNewsShellEntity shellEntity = new PropertyGoodNewsShellEntity(gbInfo, rowDataList,totalPayUserCount);
				if(shellEntity!=null){
					ApplicationContextBothUtil.getMicroblogQueueService().goodNews(gbInfo.getGbId(), shellEntity);
				}
				
			}
		}
		logger.info("MicroblogQueueTask executePropertyGoodNewsPush finished...");
	}
	
	/**
	 * 定时获取队列最新的消息同步到街坊消息
	 */
	@Override
	public void excutePushTask(){
		logger.debug("MicroblogQueueTask excutePushTask start.");
		try{
			Integer count = this.callMQPush2MicroblogContent();
			logger.info("MicroblogQueueTask excutePushTask, callMQPush2MicroblogContent count is:"+count);
		}catch(Exception e){
			logger.error("MicroblogQueueTask excutePushTask callMQPush2MicroblogContent error.",e);
		}
		logger.debug("MicroblogQueueTask excutePushTask finished.");
	}
	
	/**
	 * 定时获取队列最新的消息同步到街坊消息
	 */
	@Override
	public void excutePushTaskDbGbId(){
		logger.debug("MicroblogQueueTask excutePushTaskDbGbId start.");
		try{
			Integer count = this.callMQPush2MicroblogContentDbGbId();
			logger.info("MicroblogQueueTask excutePushTaskDbGbId, callMQPush2MicroblogContent count is:"+count);
		}catch(Exception e){
			logger.error("MicroblogQueueTask excutePushTaskDbGbId callMQPush2MicroblogContent error.",e);
		}
		logger.debug("MicroblogQueueTask excutePushTaskDbGbId finished.");
	}
	
	@Override
	public Integer callMQPush2MicroblogContentDbGbId() {
		int resCount = 0;
		List<BigInteger> gbIdList = microblogQueueDao.selectCanPushForGbToday();
		if(gbIdList!=null&&gbIdList.size()>0){
			for(BigInteger tmpGbId:gbIdList){
				if(McVisitRecordCache.checkIfPushNow(tmpGbId)){
					int succ = callMQPush2MicroblogContent(tmpGbId);
					resCount+=succ;
				}
			}
		}
		return resCount;
	}
	
	@Override
	public Integer callMQPush2MicroblogContent() {
		int resCount = 0;
		Set<BigInteger> gbIdList = McVisitRecordCache.getIsVisitGbList();//缓存加载 Redis缓存到客户端，TODO 定时同步到数据库
		if(gbIdList!=null&&gbIdList.size()>0){
			for(BigInteger tmpGbId:gbIdList){
				if(McVisitRecordCache.checkIfPushNow(tmpGbId)){
					int succ = callMQPush2MicroblogContent(tmpGbId);
					resCount+=succ;
				}
			}
		}
		return resCount;
	}
	
	
	@Override
	public Integer callMQPush2MicroblogContent(BigInteger gbId) {
		int resCount = 0;
		MicroblogQueue topMicroblogQueue = selectTopMicroblogQueueToday(gbId);
		if(topMicroblogQueue!=null){
			boolean resBool = false;
			BigInteger queueId = topMicroblogQueue.getId();
			if(MicroblogQueueUtil.isOnlyForGB(topMicroblogQueue)){//标记只到小区的为可推送，到其它服务范围的下次可能还会继续用到，所以不标记
				resBool = microblogQueueDao.markMicroblogQueueAsPushAble(queueId);
			}else{//服务范围的默认true
				resBool = true;
			}
			if(resBool){
				resCount = transferMQ2MicroblogContent(queueId, gbId);
			}
		}else{//目前暂无数据,设置为已推送,节约内存
			McVisitRecordCache.initVisitCount(gbId);//设置为已推送
		}
		return resCount;
	}
	
	
	/**
	 * 根据小区Id查询最优先的一个消息
	 * @param gbId
	 * @return
	 */
	private MicroblogQueue selectTopMicroblogQueueToday(BigInteger gbId){
		//1.根据小区Id获取 全量的type及优先级
		Set<MicroblogPushType> srcTypeList = microblogQueueDao.selectMicroblogPushTypeAllList(gbId);
		Map<String,Long> cacheSendCountToday = new HashMap<String, Long>();
		{//获取各个类别当天已发送消息的次数
			List<TypeCodeSendCount> codeCountList = microblogQueueDao.selectTypeCodeSendCountByGbId(gbId);
			if(codeCountList!=null&&codeCountList.size()>0){
				for(TypeCodeSendCount tmpAA:codeCountList){
					cacheSendCountToday.put(tmpAA.getCode(), tmpAA.getSendCount());
				}
			}
		}
		List<TypeCodeDymPriority> priorityList = new ArrayList<TypeCodeDymPriority>();
		if(srcTypeList!=null&&srcTypeList.size()>0){
			for(MicroblogPushType tmpPushType:srcTypeList){
				String typeCode = tmpPushType.getCode();
				Long defaultPriority = tmpPushType.getPriority();
				Long sendCount = cacheSendCountToday.get(typeCode);
				priorityList.add(TypeCodeDymPriority.newInstance(typeCode, defaultPriority, sendCount));
			}
		}
		//2.查询优先级最高的一个
		MicroblogQueue resMicroblogQueue = null;
		if(priorityList!=null&&priorityList.size()>0){
			List<BigInteger> saIdList = fetchSaIdList(gbId);
			resMicroblogQueue = microblogQueueDao.selectTopMicroblogQueueToday(gbId, saIdList, priorityList);
		}
		return resMicroblogQueue;
	}
	
	
	/**
	 * 将队列信息转移到指定的小区 
	 * 实现事务隔离 防止数据重复推送的问题
	 * @param mqDetail
	 * @param gbId
	 * @return 返回新增的微博记录数
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,timeout=60)
	public int transferMQ2MicroblogContent(BigInteger queueId,BigInteger gbId){
		MicroblogQueue mqDetail = microblogQueueDao.selectMicroblogQueueForUpdate(queueId, gbId);
		if(mqDetail==null){return 0;}
		microblogQueueDao.selectGbIdForUpdate(gbId);//锁定小区表
		if(!McVisitRecordCache.checkIfPushNow(gbId)){return 0;}
		Integer existCount = microblogQueueDao.selectExsitGbHasQueueCount(queueId, gbId);
		if(existCount==null||existCount<=0){//已存在关系,则直接返回
			//新增关系
			MicroblogGbHasQueue toAddMGHQ = new MicroblogGbHasQueue();
			toAddMGHQ.setBlogQueueId(queueId);
			toAddMGHQ.setCreateTime(ApplicationContextBothUtil.getCurrentTime());
			toAddMGHQ.setGbId(gbId);
			toAddMGHQ.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_microblog_gb_has_queue));
			toAddMGHQ.setLevelCode(mqDetail.getLevelCode());
			Integer addMGHQCount = null;
			try {
				addMGHQCount = microblogGbHasQueueBaseDao.insertMicroblogGbHasQueue(toAddMGHQ);
			} catch (Exception e) {
				logger.error("microblogGbHasQueueBaseDao.insertMicroblogGbHasQueue cause exception,queueId is:"+queueId+",gbId is:"+gbId, e);
			}
			if(addMGHQCount!=null&&addMGHQCount>0){//新增微博内容信息
				MicroblogContent toAddMC = new MicroblogContent();
				String nowTime = ApplicationContextBothUtil.getCurrentTime();
				String linkDetailJson = mqDetail.getLinkDetailJson();
				String text = mqDetail.getText();
				BigInteger tGroupBuildingFId = gbId;
				BigInteger tMicroblogQueueFId = queueId;
				Long showType = mqDetail.getLevelCode();//!!showType取值来源于LevelCode
				Integer sourceType = mqDetail.getSourceType();
				BigInteger tMicroblogTypeFId = MicroblogConstant.DEFAULT_MICROBLOG_TYPEID;
				BigInteger userId = mqDetail.getUserId();
				toAddMC.setCreateTime(nowTime);
				toAddMC.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_microblog_content));
				toAddMC.setLastUpdateTime(nowTime);
				toAddMC.setLinkDetailJson(linkDetailJson);
				toAddMC.setShowType(showType);
				toAddMC.setSourceType(sourceType);
				toAddMC.setText(text);
				toAddMC.settGroupBuildingFId(tGroupBuildingFId);
				toAddMC.settMicroblogQueueFId(tMicroblogQueueFId);
				toAddMC.settMicroblogTypeFId(tMicroblogTypeFId);
				toAddMC.setTotalCommentCount(0L);
				toAddMC.setTotalSupportCount(0L);
				toAddMC.setUserId(userId);
				int resAddCount = microblogContentBaseDao.insertMicroblogContent(toAddMC);
				if(resAddCount>0){
					//McVisitRecordCache.addPushCount(tmpGbId);//增加推送次数标记
					McVisitRecordCache.makrPushCountAsFull(gbId);//标记等待下次再推送
					McVisitRecordCache.initVisitCount(gbId);//设置为已推送
				}
				return resAddCount;
			}
		}
		return 0;
	}
	
	private List<BigInteger> fetchSaIdList(BigInteger gbId){
		MultiSaIdEntity multiSaIdEntity = addressOperationService.getCodeIdMultiEntity(null, null, null, null, gbId);
		List<BigInteger> saIdList = multiSaIdEntity.getTotalList();
		return saIdList;
	}
	
	/**
	 * 给物业缴费提前三天提醒但是未推送街坊消息的小区推送缴费的博客消息
	 */
	public void executePropertyPayRemindPush() {
		List<GbIdAndPayDistance> gbList = microblogQueueDao.selectPayBillGroupBuildingListForMQ(MicroblogQueueDict.MQ_Level.Code_20);
		for (GbIdAndPayDistance tmpGb : gbList) {
			if(!DefaultGbUtil.checkIsDefaultGb(tmpGb.getGbId())){
				ApplicationContextBothUtil.getMicroblogQueueService().propertyPayRemind(tmpGb.getDayCountForPayEnd2Now(), tmpGb.getGbId());
			}
		}
	}

	/**
	 * 给未推送物业全面签约的小区推送缴费的博客消息
	 */
//	public void executeGroupBuildIsSignPush(){
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("mqSourceType", MicroblogQueueDict.Source_Type.PC);
//		param.put("mqLevelCode", MicroblogQueueDict.MQ_Level.Code_10);
//		List<GroupBuildingEntity> gbList = microblogQueueDao.selectSignGroupBuildingListForMQ(param);
//		for (GroupBuildingEntity groupBuilding : gbList) {
//			ApplicationContextBothUtil.getMicroblogQueueService().groupBuildIsSign(groupBuilding.getId(), groupBuilding.getName());
//		}
//	}
	
	
}
