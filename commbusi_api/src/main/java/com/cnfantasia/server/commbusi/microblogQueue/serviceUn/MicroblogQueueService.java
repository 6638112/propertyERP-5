package com.cnfantasia.server.commbusi.microblogQueue.serviceUn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.util.UserShowNameUtil;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueDict;
import com.cnfantasia.server.commbusi.microblogQueue.dao.IMicroblogQueueDao;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsShellEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.SimpleAddressInfo;
import com.cnfantasia.server.commbusi.microblogQueue.queue.MicroblogQueuePool;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;

public class MicroblogQueueService implements IMicroblogQueueService {

	private Log logger = LogFactory.getLog(getClass());

	private IMicroblogQueueDao microblogQueueDao;
	public void setMicroblogQueueDao(IMicroblogQueueDao microblogQueueDao) {
		this.microblogQueueDao = microblogQueueDao;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	@Override
	public boolean checkCanPush(Long levelCode){
		if(levelCode==null){return false;}
		List<String> supportTypes = new ArrayList<String>();
		String typesStr = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MicroQueue_PushType);
		if(!StringUtils.isEmpty(typesStr)){
			String[] typesArr = typesStr.split(";");
			if(typesArr!=null&&typesArr.length>0){
				for(String tmpType:typesArr){
					if(!StringUtils.isEmpty(tmpType)){
						supportTypes.add(tmpType.trim());
					}
				}
			}
		}
		if(supportTypes==null||supportTypes.size()<=0){return true;}
		if(supportTypes.contains(levelCode+"")){return true;}
		return false;
	}
	
	private void addActivitySimple(String text, int sourceType, int isTiming,
			BigInteger groupbuildingId, Long levelCode) {
		MicroblogQueuePool.addActivity(ApplicationContextBothUtil
				.getMicroblogQueueFactory().createMicroblogQueueByGB(text,
						sourceType, isTiming, groupbuildingId, levelCode));
	}

//	private void addActivityForDetailJson(String text, int sourceType,
//			int isTiming, BigInteger groupbuildingId, Long levelCode,
//			String linkDetailJson) {
//		MicroblogQueuePool.addActivity(ApplicationContextBothUtil
//				.getMicroblogQueueFactory().createMicroblogQueueByGB(text,
//						sourceType, isTiming, groupbuildingId, levelCode,
//						linkDetailJson));
//	}
	
//	private void addActivityForAddressCity(String text, int sourceType, int isTiming, BigInteger addressCityId,
//			Long levelCode){
//		MicroblogQueuePool.addActivity(ApplicationContextBothUtil
//				.getMicroblogQueueFactory().createMicroblogQueueByAddressCity(text, sourceType, isTiming, addressCityId, levelCode));
//	}
	
	@Override
	public void saveMQForQueueService(MicroblogQueue signal) {
		if(signal!=null){
			try {
				BigInteger userId = null;
				ApplicationContextBothUtil.newStandard(signal,
						SEQConstants.t_microblog_queue, userId);
				this.microblogQueueDao.insertMicroblogQueue(signal);
			} catch (Exception e) {
				logger.error("MicroblogQueueService.saveMQForQueueService(MicroblogQueue) cause exception",e);
			}
		}
	}
	
	@Override
	@Transactional
	public void saveMQForQueueService(List<MicroblogQueue> mqList) {
		try {
			if (null != mqList && mqList.size() > 0) {
				BigInteger userId = null;
				ApplicationContextBothUtil.newStandardList(mqList,
						SEQConstants.t_microblog_queue, userId);
				this.microblogQueueDao.insertMicroblogQueueBatch(mqList);
			}
		} catch (Exception e) {
			logger.error("MicroblogQueueService.saveMQForQueueService(List<MicroblogQueue>) cause exception",e);
		}
	}

	@Override
	public void joinGroupBuilding(BigInteger userId, BigInteger gbId,
			String gbName,BigInteger roomId) {//upd-151015
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_150)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		String showName = UserShowNameUtil.getUserShowName(userBaseDao.selectUserBySeqId(userId));
		SimpleAddressInfo addInfo = microblogQueueDao.selectSimpleAddressInfoByRoomId(roomId);
		
//		String text = MessageSourceUtil.getMessage("both.jfpush.joinGroupBuilding",
//				new Object[] { userId+"", gbName });
		String text = MessageSourceUtil.getMessage("both.jfpush.joinGroupBuilding",
				new Object[] { addInfo.getBuildName(), addInfo.fetchRoomNumMask(),showName,gbName });
		addActivitySimple(text, MicroblogQueueDict.Source_Type.USER,
				MicroblogQueueDict.Timing.YES, gbId,
				MicroblogQueueDict.MQ_Level.Code_150);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,timeout=60)
	public void goodNews(BigInteger gbId,
			PropertyGoodNewsShellEntity shellEntity) {
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_40)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		if(shellEntity.getTotalSavedMoney() != null && shellEntity.getTotalSavedMoney() >= 0) {
			String text = MessageSourceUtil.getMessage(
					"both.jfpush.goodNews",
					new Object[] { shellEntity.fetchPropMonth(),
							shellEntity.fetchGbName(),
							shellEntity.getTotalPayUserCount(),
							PriceUtil.div100(shellEntity.getTotalSavedMoney()) });// 版本判断处理
			String detailJson = JSON.toJSONString(shellEntity);// 注意get方法
			MicroblogQueue tmpMicroblogQueue = ApplicationContextBothUtil.getMicroblogQueueFactory().createMicroblogQueueByGB(text, MicroblogQueueDict.Source_Type.PC,
					MicroblogQueueDict.Timing.YES, gbId,
					MicroblogQueueDict.MQ_Level.Code_40, detailJson);
			//去重
			BigInteger gbIdForUpdate = microblogQueueDao.selectNotExistMQGbIdForUpdate(gbId,MicroblogQueueDict.MQ_Level.Code_40);
			if(gbIdForUpdate!=null){
				saveMQForQueueService(tmpMicroblogQueue);
			}
		}
	}

	@Override
	public void userRoomValidate(String userId, BigInteger gbId,BigInteger roomId) {//upd-151015
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_60)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		String showName = UserShowNameUtil.getUserShowName(userBaseDao.selectUserBySeqId(new BigInteger(userId)));
		SimpleAddressInfo addInfo = microblogQueueDao.selectSimpleAddressInfoByRoomId(roomId);
		
//		String text = MessageSourceUtil.getMessage("both.jfpush.userRoomValidate"
//				, new Object[] { userId+"" });
//		String text = MessageSourceUtil.getMessage("both.jfpush.userRoomValidate"
//				, new Object[] { addInfo.getBuildName(),addInfo.fetchRoomNumMask(),showName });
		
		String text = MessageSourceUtil.getMessage("both.jfpush.joinGroupBuilding",
				new Object[] { addInfo.getBuildName(), addInfo.fetchRoomNumMask(),showName, addInfo.getGbName() });
		
		addActivitySimple(text, MicroblogQueueDict.Source_Type.USER,
				MicroblogQueueDict.Timing.YES, gbId,
				MicroblogQueueDict.MQ_Level.Code_60);
	}

	@Override
	public void groupBuildIsSign(BigInteger gbId, String gbName) {
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_10)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		String text = MessageSourceUtil.getMessage(
				"both.jfpush.groupBuildIsSign",
				new Object[] { gbName });
		addActivitySimple(text, MicroblogQueueDict.Source_Type.PC, MicroblogQueueDict.Timing.YES,//不作及时消息
				gbId, MicroblogQueueDict.MQ_Level.Code_10);
	}

	@Override
	public void discountZero(BigInteger userId,BigInteger userHasRoomFId) {//upd-151015
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_30)){return;}
		SimpleAddressInfo addInfo = microblogQueueDao.selectSimpleAddressInfoByUserHasRoomId(userHasRoomFId);
		String showName = UserShowNameUtil.getUserShowName(userBaseDao.selectUserBySeqId(userId));
		
		BigInteger addressCityId = addInfo.getAddressCityId();
//		String text = MessageSourceUtil.getMessage("both.jfpush.discountZero",
//				new Object[] { addInfo.getGbName(), userId+"" });
		String text = MessageSourceUtil.getMessage("both.jfpush.discountZero",
				new Object[] { addInfo.getBlockName(), addInfo.getGbName(),addInfo.getBuildName(),addInfo.fetchRoomNumMask(),showName });
		MicroblogQueue tmpMicroblogQueue = ApplicationContextBothUtil.getMicroblogQueueFactory().createMicroblogQueueByAddressCity(text, MicroblogQueueDict.Source_Type.JFQ, MicroblogQueueDict.Timing.YES, addressCityId, MicroblogQueueDict.MQ_Level.Code_30);
		saveMQForQueueService(tmpMicroblogQueue);
	}


	@Override
	public void propertyNotice(String msgId, String title, String content,
			String pushTime, String[] gbIds) {
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_70)){return;}
		if(gbIds.length>0){
			String text = content;// HtmlTagFilterUtil.removeHtmlTagInfo(content);//移除html标签
			for (String groupbuildingId : gbIds) {
				if(DefaultGbUtil.checkIsDefaultGb(new BigInteger(groupbuildingId))){return;}
				addActivitySimple(text, 
					MicroblogQueueDict.Source_Type.PC, MicroblogQueueDict.Timing.YES, //不作及时消息
					new BigInteger(groupbuildingId), MicroblogQueueDict.MQ_Level.Code_70);
			}
		}
	}

	@Override
	public void hbPayFree(BigInteger userId, BigInteger gbId) {//upd-151015
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_80)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		SimpleAddressInfo addInfo = microblogQueueDao.selectSimpleAddressInfoByUserDefaultRoomId(userId);
		String showName = UserShowNameUtil.getUserShowName(userBaseDao.selectUserBySeqId(userId));
		
//		String text = MessageSourceUtil.getMessage("both.jfpush.hbPayFree",
//				new Object[] { userId+"" });
		String text = MessageSourceUtil.getMessage("both.jfpush.hbPayFree",
				new Object[] { addInfo.getBuildName(),addInfo.fetchRoomNumMask(),showName });
		addActivitySimple(text, MicroblogQueueDict.Source_Type.JFQ,  MicroblogQueueDict.Timing.YES, gbId, MicroblogQueueDict.MQ_Level.Code_80);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,timeout=60)
	public void propertyPayRemind(int leftDay, BigInteger gbId) {
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_20)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		String text = MessageSourceUtil.getMessage("both.jfpush.propertyPayRemind",
				new Object[] { leftDay });
		MicroblogQueue tmpMicroblogQueue = ApplicationContextBothUtil.getMicroblogQueueFactory().createMicroblogQueueByGB(text, MicroblogQueueDict.Source_Type.JFQ, MicroblogQueueDict.Timing.YES, //不作及时消息
				gbId, MicroblogQueueDict.MQ_Level.Code_20);
		//去重
		BigInteger gbIdForUpdate = microblogQueueDao.selectNotExistMQGbIdForUpdate(gbId,MicroblogQueueDict.MQ_Level.Code_20);
		if(gbIdForUpdate!=null){
			saveMQForQueueService(tmpMicroblogQueue);
		}
	}

	@Override
	public void groupBuildLeastDiscount(BigInteger userId, BigInteger gbId,
			String gbName, Double discount,BigInteger userHasRoomFId) {//upd-151015
		if(!checkCanPush(MicroblogQueueDict.MQ_Level.Code_90)){return;}
		if(DefaultGbUtil.checkIsDefaultGb(gbId)){return;}
		SimpleAddressInfo addInfo = microblogQueueDao.selectSimpleAddressInfoByUserHasRoomId(userHasRoomFId);
		String showName = UserShowNameUtil.getUserShowName(userBaseDao.selectUserBySeqId(userId));
		
//		String text = MessageSourceUtil.getMessage("both.jfpush.groupBuildLeastDiscount",
//				new Object[] { gbName,userId+"",discount });
		String text = MessageSourceUtil.getMessage("both.jfpush.groupBuildLeastDiscount",
				new Object[] { addInfo.getBuildName(),addInfo.fetchRoomNumMask(),showName,discount });
		
//		addActivitySimple(text, MicroblogQueueDict.Source_Type.JFQ, MicroblogQueueDict.Timing.YES, gbId, MicroblogQueueDict.MQ_Level.Code_90);
		MicroblogQueue tmpMicroblogQueue = ApplicationContextBothUtil
				.getMicroblogQueueFactory().createMicroblogQueueByGB(text, MicroblogQueueDict.Source_Type.JFQ, MicroblogQueueDict.Timing.YES, gbId, MicroblogQueueDict.MQ_Level.Code_90);
		saveMQForQueueService(tmpMicroblogQueue);
	}

}
