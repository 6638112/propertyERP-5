package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.utils.MybatisSpecialCharFilterUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.revenueTaskRecord.dao.IRevenueTaskRecordBaseDao;
import com.cnfantasia.server.domainbase.revenueTaskRecord.entity.RevenueTaskRecord;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractSynTask implements ISynTask{
	private Log logger = LogFactory.getLog(getClass());
	private IRevenueTaskRecordBaseDao revenueTaskRecordBaseDao;
	public void setRevenueTaskRecordBaseDao(IRevenueTaskRecordBaseDao revenueTaskRecordBaseDao) {
		this.revenueTaskRecordBaseDao = revenueTaskRecordBaseDao;
	}
	
	private ICommonLockDao commonLockDao;
	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	protected Integer execTaskOne(UserRole role, BigInteger roleId,Integer projectType){
		commonLockDao.getLock(getLock());
		Integer resCount = 0;
		RevenueRole revenueRole = new RevenueRole(role, roleId);
		List<RevenueTaskRecord> toAddList = new ArrayList<RevenueTaskRecord>();
		String startTime = ApplicationContextBothUtil.getDualDao().getNowTime();
		{
			Integer succCount = 0;
			Long failedCount = 0L;
			RevenueTaskRecord record = new RevenueTaskRecord();
			try {
				succCount = synSignal(revenueRole);
				if(succCount==null||succCount<=0){return resCount;}
			} catch (Exception e) {
				logger.error("SynTask.execTask failed,projectType is:"+projectType+",roleType is:"+role.getCode()+",roleId is:"+roleId, e); 
				String excepStackInfo = ExceptionParseUtil.parseExceptionMessage(e);
				record.setExcepStackInfo(MybatisSpecialCharFilterUtil.filterDollarStr(excepStackInfo));
			}finally{
				record.setFailedCount(failedCount);
				record.setGoalType(projectType);
				record.setMiniRoleId(roleId);
				record.setMiniRoleType(role.getCode());
				record.setStartTime(startTime);
				record.setSuccCount(succCount+0L);
				toAddList.add(record);
			}
		}
		String finishTime = ApplicationContextBothUtil.getDualDao().getNowTime();
		String lastSynTime = finishTime;
		if(toAddList!=null&&toAddList.size()>0){
			List<BigInteger> toAddIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_task_record, toAddList.size());
			for(int i=0;i<toAddList.size();i++){
				RevenueTaskRecord record = toAddList.get(i);
				record.setFinishTime(finishTime);
				record.setId(toAddIdList.get(i));
				record.setLastSynTime(lastSynTime);
			}
			resCount += revenueTaskRecordBaseDao.insertRevenueTaskRecordBatch(toAddList);
		}
		return resCount;
	}
	
	@Override
	public Integer execTask(){
		Integer resCount = 0;
		Integer projectType = getProjectType();
		UserRole role = getUserRole();
		//查询所有包含开启配置项的物业公司Id列表
		List<BigInteger> roleIdList = getRoleIdList(projectType,role);
		if(roleIdList!=null&&roleIdList.size()>0){
			for(BigInteger roleId:roleIdList){
				resCount += execTaskOne(role, roleId, projectType);
			}
		}
		logger.info("execute updateRevnueSignalAmountToSettled start-----------" + resCount);
		IRevenueDao revenueDao = (IRevenueDao) CnfantasiaCommbusiUtil.getBeanManager("revenueDao");
		resCount += revenueDao.updateRevnueSignalAmountToSettled();
		logger.info("execute updateRevnueSignalAmountToSettled end-----------" + resCount);

		return resCount;
	}

	public abstract UserRole getUserRole();
	public abstract List<BigInteger> getRoleIdList(Integer projectType,UserRole role);
	public abstract Integer synSignal(RevenueRole revenueRole);
	public abstract Integer getProjectType();
	public abstract String getLock();
}
