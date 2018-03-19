/**   
* Filename:    PrizeActivityService.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午7:56:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict.MsPrizeActivity_ActivityStatus;
import com.cnfantasia.server.commbusi.prizeActivity.dao.IPrizeActivityDao;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActHasOptParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActOptionParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActHasOptEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActivityEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.TimeRange;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.msPrizeActHasOpt.dao.IMsPrizeActHasOptBaseDao;
import com.cnfantasia.server.domainbase.msPrizeActHasOpt.entity.MsPrizeActHasOpt;
import com.cnfantasia.server.domainbase.msPrizeActivity.dao.IMsPrizeActivityBaseDao;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;


/**
 * Filename:    PrizeActivityService.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午7:56:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public class PrizeActivityService implements IPrizeActivityService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPrizeActivityDao prizeActivityDao;
	public void setPrizeActivityDao(IPrizeActivityDao prizeActivityDao) {
		this.prizeActivityDao = prizeActivityDao;
	}
	private IMsPrizeActivityBaseDao msPrizeActivityBaseDao;
	public void setMsPrizeActivityBaseDao(
			IMsPrizeActivityBaseDao msPrizeActivityBaseDao) {
		this.msPrizeActivityBaseDao = msPrizeActivityBaseDao;
	}
	
	private IMsPrizeActHasOptBaseDao msPrizeActHasOptBaseDao;
	public void setMsPrizeActHasOptBaseDao(
			IMsPrizeActHasOptBaseDao msPrizeActHasOptBaseDao) {
		this.msPrizeActHasOptBaseDao = msPrizeActHasOptBaseDao;
	}

	/**
	 * 校验活动范围时间是否冲突
	 * @return false不冲突 true有时间冲突
	 */
	private boolean valiTimeConflict(String startTime, String endTime){
		TimeRange nowRange = new TimeRange(startTime, endTime, DateUtil.formatMinutes.get());
		List<TimeRange> existList = prizeActivityDao.selectPrizeActivityTimeRangeList();
		if(existList==null||existList.size()<=0){return false;}
		for(TimeRange tmp:existList){
			if(TimeRange.hasIntersection(nowRange, tmp)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<MsPrizeActivity> getMsPrizeActivityList(String name,
			Integer activityStatus, String startTime, String endTime,
			PageModel pageModel) {
		return prizeActivityDao.selectMsPrizeActivityList(name,activityStatus,startTime,endTime,pageModel);
	}

	@Override
	public MsPrizeActivityEntity getMsPrizeActivityDetail(BigInteger actId) {
		return prizeActivityDao.selectMsPrizeActivityDetail(actId);
	}
	
	/**
	 * 校验是否可编辑或者删除
	 * @param actId
	 */
	private void validateCanUpdOrDel(BigInteger actId){
		Integer actQryStatus = prizeActivityDao.selectMsPrizeActivityQryStatus(actId);
		if(actQryStatus!=null&&
				(actQryStatus.compareTo(PrizeActivityDict.Qry_ActivityStatus.CLOSED)==0 || actQryStatus.compareTo(PrizeActivityDict.Qry_ActivityStatus.OPEN)==0)
		){
		}else{
			throw new BusinessRuntimeException("PrizeActivityService.validateCanUpdOrDel.statusFailed");
		}
	}
	
	@Override
	public void deleteMsPrizeActivity(BigInteger actId) {
		validateCanUpdOrDel(actId);//校验
		Integer count = prizeActivityDao.deleteMsPrizeActivityWithRelaLogic(actId);
		if(count==null||count<=0){
			throw new BusinessRuntimeException("PrizeActivityService.deleteMsPrizeActivity.count0");
		}
	}

	@Override
	public void addMsPrizeActivity(BigInteger userId,String name, String startTime,
			String endTime, String shareText, RequestFileEntity shareIconImage,
			List<ActOptionParam> actOptionParamList, Integer activityStatus) {
		if(!MsPrizeActivity_ActivityStatus.isExist(activityStatus)){activityStatus = MsPrizeActivity_ActivityStatus.getDefault();}
		{
			boolean timeIsConflict = valiTimeConflict(startTime, endTime);
			if(timeIsConflict){throw new BusinessRuntimeException("PrizeActivityService.addMsPrizeActivity.timeConflict");}
		}
		MsPrizeActivity toAddPriAct = new MsPrizeActivity();
		toAddPriAct.setActivityStatus(activityStatus);
		toAddPriAct.setEndTime(endTime);
		toAddPriAct.setLastUpdTime(ApplicationContextBothUtil.getCurrentTime());
		toAddPriAct.setName(name);
		toAddPriAct.setShareText(shareText);
		toAddPriAct.setStartTime(startTime);
		BigInteger prizeActId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_activity);
		toAddPriAct.setId(prizeActId);
		if(shareIconImage!=null&&shareIconImage.getFileContent()!=null){//生成文件名 prizeActId+userId
			String shareIcon = new StringBuffer().append(prizeActId)
					.append("_").append(userId).append(DateUtil.getCurrSysTimeStr())
					.append(".").append(shareIconImage.getFileType()).toString();
			String actBasePath = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.PrizeActivity_Icon_BasePath);
			String serverPath = actBasePath+shareIcon;
			try {
				ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, shareIconImage.getFileContent());
			} catch (IOException e) {
				logger.info("upload MsPrizeActivity file cause exception:"+e.getMessage(), e);
				throw new BusinessRuntimeException("PrizeActivityService.addMsPrizeActivity.uploadIcon.error",new Object[]{shareIconImage.getFileName()});
			}
			toAddPriAct.setShareIcon(shareIcon);
		}
		Integer addCount =  msPrizeActivityBaseDao.insertMsPrizeActivity(toAddPriAct);
		if(addCount==null||addCount<=0){
			throw new BusinessRuntimeException("PrizeActivityService.addMsPrizeActivity.count0");
		}
		if(actOptionParamList!=null&&actOptionParamList.size()>0){
			List<MsPrizeActHasOpt> relaList = new ArrayList<MsPrizeActHasOpt>();
			List<BigInteger> pahoIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_act_has_opt,actOptionParamList.size());
			for(int i=0;i<actOptionParamList.size();i++){
				ActOptionParam tmpP = actOptionParamList.get(i);
				MsPrizeActHasOpt tmpPAHO = new MsPrizeActHasOpt();
				tmpPAHO.setDayMaxCount(tmpP.getDayMaxCount());
				tmpPAHO.setId(pahoIdList.get(i));
				tmpPAHO.setPrizeActId(prizeActId);
				tmpPAHO.setPrizeOptionId(tmpP.getPrizeOptionId());
				tmpPAHO.setTotalMaxCount(tmpP.getTotalMaxCount());
				relaList.add(tmpPAHO);
			}
			msPrizeActHasOptBaseDao.insertMsPrizeActHasOptBatch(relaList);
		}
	}

	@Override
	public List<MsPrizeActHasOptEntity> getActHasOptList(BigInteger actId) {
		return prizeActivityDao.selectActHasOptList(actId);
	}
	
	@Override
	public Integer getLeftPrizeOptionCount(BigInteger optId) {
		return prizeActivityDao.selectLeftPrizeOptionCount(optId);
	}

	@Override
	public void updateMsPrizeActivity(BigInteger prizeActId,BigInteger userId,String name,String startTime,String endTime,String shareText
			,RequestFileEntity shareIconImage,List<ActOptionParam> actOptionParamList,Integer activityStatus,List<ActHasOptParam> actHasOptParamList) {
		validateCanUpdOrDel(prizeActId);//前提条件校验 已结束的活动 不能编辑，不能删除
		if(prizeActId==null){throw new BusinessRuntimeException("PrizeActivityService.updateMsPrizeActivity.actIdNull");}
		if(!MsPrizeActivity_ActivityStatus.isExist(activityStatus)){activityStatus = MsPrizeActivity_ActivityStatus.getDefault();}
		{//更新活动基本信息,包含图片
			MsPrizeActivity toUpdPriAct = new MsPrizeActivity();
			toUpdPriAct.setActivityStatus(activityStatus);
			toUpdPriAct.setEndTime(endTime);
			toUpdPriAct.setLastUpdTime(ApplicationContextBothUtil.getCurrentTime());
			toUpdPriAct.setName(name);
			toUpdPriAct.setShareText(shareText);
			toUpdPriAct.setStartTime(startTime);
			toUpdPriAct.setId(prizeActId);
			if(shareIconImage!=null&&shareIconImage.getFileContent()!=null){//生成文件名 prizeActId+userId
				String shareIcon = new StringBuffer().append(prizeActId)
						.append("_").append(userId).append(DateUtil.getCurrSysTimeStr())
						.append(".").append(shareIconImage.getFileType()).toString();
				String actBasePath = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.PrizeActivity_Icon_BasePath);
				String serverPath = actBasePath+shareIcon;
				try {
					ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, shareIconImage.getFileContent());
				} catch (IOException e) {
					logger.info("upload MsPrizeActivity file cause exception:"+e.getMessage(), e);
					throw new BusinessRuntimeException("PrizeActivityService.updateMsPrizeActivity.uploadIcon.error",new Object[]{shareIconImage.getFileName()});
				}
				toUpdPriAct.setShareIcon(shareIcon);
			}
			Integer addCount =  msPrizeActivityBaseDao.updateMsPrizeActivity(toUpdPriAct);
			if(addCount==null||addCount<=0){
				throw new BusinessRuntimeException("PrizeActivityService.updateMsPrizeActivity.count0");
			}
		}
		{//已存在的奖项 删除or更新
			List<MsPrizeActHasOptEntity> oldRelaList = prizeActivityDao.selectActHasOptList(prizeActId);//已存在的
			if(actHasOptParamList==null){actHasOptParamList = new ArrayList<ActHasOptParam>();}//客户端提交的
			List<BigInteger> toDelIdList = new ArrayList<BigInteger>();
			List<MsPrizeActHasOpt> toUpdList = new ArrayList<MsPrizeActHasOpt>();
			for(MsPrizeActHasOptEntity oldRelaTmp:oldRelaList){
				boolean canUpd = false;
				for(ActHasOptParam cliRela:actHasOptParamList){
					if(oldRelaTmp.getId().compareTo(cliRela.getActHasOptId())==0){
						canUpd = true;
						MsPrizeActHasOpt relaUpd = new MsPrizeActHasOpt();
						relaUpd.setId(oldRelaTmp.getId());
						relaUpd.setDayMaxCount(cliRela.getDayMaxCount());
						relaUpd.setTotalMaxCount(cliRela.getTotalMaxCount());
						toUpdList.add(relaUpd);
						break;
					}
				}
				if(!canUpd){//不能更新，则删除
					toDelIdList.add(oldRelaTmp.getId());
				}
			}
			if(toDelIdList!=null&&toDelIdList.size()>0){msPrizeActHasOptBaseDao.deleteMsPrizeActHasOptLogicBatch(toDelIdList);}
			if(toUpdList!=null&&toUpdList.size()>0){msPrizeActHasOptBaseDao.updateMsPrizeActHasOptBatch(toUpdList);}
		}
		{//不存在的奖项关系 新增
			if(actOptionParamList!=null&&actOptionParamList.size()>0){
				List<MsPrizeActHasOpt> relaList = new ArrayList<MsPrizeActHasOpt>();
				List<BigInteger> pahoIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_act_has_opt,actOptionParamList.size());
				for(int i=0;i<actOptionParamList.size();i++){
					ActOptionParam tmpP = actOptionParamList.get(i);
					MsPrizeActHasOpt tmpPAHO = new MsPrizeActHasOpt();
					tmpPAHO.setDayMaxCount(tmpP.getDayMaxCount());
					tmpPAHO.setId(pahoIdList.get(i));
					tmpPAHO.setPrizeActId(prizeActId);
					tmpPAHO.setPrizeOptionId(tmpP.getPrizeOptionId());
					tmpPAHO.setTotalMaxCount(tmpP.getTotalMaxCount());
					relaList.add(tmpPAHO);
				}
				msPrizeActHasOptBaseDao.insertMsPrizeActHasOptBatch(relaList);
			}
		}
		
	}

	@Override
	public List<MsPrizeOption> getMsPrizeOptionAvalList(String actStartTime,String actEndTime,BigInteger ignoreActId) {
		return prizeActivityDao.selectMsPrizeOptionAvalList(actStartTime,actEndTime,ignoreActId);
	}

	@Override
	public MsPrizeActivity getMsPrizeActivityCurrDoing() {
		return prizeActivityDao.selectMsPrizeActivityCurrDoing();
	}

}
