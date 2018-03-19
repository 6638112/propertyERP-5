/**   
* Filename:    PointService.java   
* @version:    1.0  
* Create at:   2014年12月29日 上午8:36:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.dao.IPointDao;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.pointCostRecord.dao.IPointCostRecordBaseDao;
import com.cnfantasia.server.domainbase.pointCostRecord.entity.PointCostRecord;
import com.cnfantasia.server.domainbase.pointData.dao.IPointDataBaseDao;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.pointSourceRecord.dao.IPointSourceRecordBaseDao;
import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    PointService.java
 * @version:    1.0.0
 * Create at:   2014年12月29日 上午8:36:53
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月29日       shiyl             1.0             1.0 Version
 */
public class PointService implements IPointService{
	private IPointDao pointDao;
	public void setPointDao(IPointDao pointDao) {
		this.pointDao = pointDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private IPointSourceRecordBaseDao pointSourceRecordBaseDao;
	public void setPointSourceRecordBaseDao(IPointSourceRecordBaseDao pointSourceRecordBaseDao) {
		this.pointSourceRecordBaseDao = pointSourceRecordBaseDao;
	}
	
	private IPointCostRecordBaseDao pointCostRecordBaseDao;
	public void setPointCostRecordBaseDao(IPointCostRecordBaseDao pointCostRecordBaseDao) {
		this.pointCostRecordBaseDao = pointCostRecordBaseDao;
	}
	
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}
	
	private IPointDataBaseDao pointDataBaseDao;
	public void setPointDataBaseDao(IPointDataBaseDao pointDataBaseDao) {
		this.pointDataBaseDao = pointDataBaseDao;
	}
	
	@Override
	public PointData addPoint(BigInteger userId, Integer type, Long value,Long dayCount) {
		if(value==null||value<0){
			throw new BusinessRuntimeException("pointService.addPoint.value.illegal");
		}
		//是否有记录，不存在则新增
		{
			PointData currPointData = commonPointService.getPointDataByUserId(userId);//执行查询，若不存在则会新增一条记录
			if(currPointData==null){
				PointData pointDataAdd = new PointData();
				BigInteger pointDataAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_point_data);
				pointDataAdd.setId(pointDataAddId);
				pointDataAdd.setPointValue(0L);
				pointDataAdd.settUserFId(userId);
				Integer resCount = pointDataBaseDao.insertPointData(pointDataAdd);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("CommonPointService.insertPointData.failed");
				}
			}
		}
		
		//更新积分
		{
			Integer resCount = pointDao.addPointData(userId, value);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("pointService.addPoint.failed");
			}
		}
		//增加积分新增记录
		{
			PointSourceRecord pointSourceRecord = new PointSourceRecord();
			BigInteger pointSourceRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_point_source_record);
			User currUser = userBaseDao.selectUserBySeqId(userId);
			String nowTime = dualDao.getNowTime();
			pointSourceRecord.setDesc(null);
			pointSourceRecord.setType(type);
			pointSourceRecord.setFromTime(nowTime);
			pointSourceRecord.setId(pointSourceRecordAddId);
			pointSourceRecord.setRoomId(currUser.getDefaultRoomId());
			pointSourceRecord.setUserId(userId);
			pointSourceRecord.setValue(value);
			pointSourceRecord.setSignDayCount(dayCount);
			Integer resCount = pointSourceRecordBaseDao.insertPointSourceRecord(pointSourceRecord);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("pointService.addPoint.insertPointSourceRecord.failed");
			}
		}
		return commonPointService.getPointDataByUserId(userId);
	}

	@Override
	public PointData costPoint(BigInteger userId, Integer type, Long value) {
		if(value==null||value<0){
			throw new BusinessRuntimeException("pointService.costPoint.value.illegal");
		}
		PointData pointData = commonPointService.getPointDataByUserId(userId);
		if(pointData==null||pointData.getPointValue().compareTo(value)<0){
			throw new BusinessRuntimeException("pointService.costPoint.leftValue.insufficient");
		}
		//扣除积分
		{
			Integer resCount = pointDao.costPointData(userId, value);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("pointService.costPoint.failed");
			}
		}
		//增加消费记录
		{
			PointCostRecord pointCostRecordAdd = new PointCostRecord();
			BigInteger pointCostRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_point_cost_record);
			User currUser = userBaseDao.selectUserBySeqId(userId);
			String nowTime = dualDao.getNowTime();
			pointCostRecordAdd.setCostDesc("");
			pointCostRecordAdd.setCostTime(nowTime);
			pointCostRecordAdd.setCostType(type);
			pointCostRecordAdd.setId(pointCostRecordAddId);
			pointCostRecordAdd.setRoomId(currUser.getDefaultRoomId());
			pointCostRecordAdd.setUserId(userId);
			pointCostRecordAdd.setValue(value);
			Integer resCount = pointCostRecordBaseDao.insertPointCostRecord(pointCostRecordAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("pointService.costPoint.insertPointCostRecord.failed");
			}
		}
		return commonPointService.getPointDataByUserId(userId);
	}

	@Override
	public List<PointSourceRecord> qryPointSourceList(BigInteger userId,PageModel pageModel) {
		if(StringUtils.isEmpty(userId)){return null;}
		PointSourceRecord pointSourceRecordQry = new PointSourceRecord();
		pointSourceRecordQry.setUserId(userId);
		return pointSourceRecordBaseDao.selectPointSourceRecordByCondition(MapConverter.toMap(pointSourceRecordQry),pageModel, false);
	}

	@Override
	public List<PointCostRecord> qryPointCostList(BigInteger userId,PageModel pageModel) {
		if(StringUtils.isEmpty(userId)){return null;}
		PointCostRecord pointCostRecordQry = new PointCostRecord();
		pointCostRecordQry.setUserId(userId);
		return pointCostRecordBaseDao.selectPointCostRecordByCondition(MapConverter.toMap(pointCostRecordQry), pageModel, false);
	}

	@Override
	public void checkAndAddPoint(BigInteger userId, Integer type) {
		Long toAddValue = null;
		Long signDayCount = null;
		PointSourceRecord lastPointSourceRecord = pointDao.selectLastPointSourceRecord(userId, type);
		if(lastPointSourceRecord==null){//为空
			if(type.compareTo(PointConstant.PointSourceType.Sign)==0){//签到特殊处理
				signDayCount = 1L;
				toAddValue = PointConstant.getPointValueBySignDays(signDayCount);
			}else{
				toAddValue = PointConstant.getPointValueByPointSourceType(type);
			}
		}else{//不为空
			String nowTime = dualDao.getNowTime();
			String fromTime = lastPointSourceRecord.getFromTime();
			Integer resNum = DateUtil.daysBetween(fromTime, nowTime);
			if(type.compareTo(PointConstant.PointSourceType.FirstSetNickName)==0){//首次设置昵称
				
			}else if(type.compareTo(PointConstant.PointSourceType.FirstSetUserImage)==0){//首次设置头像
				
			}else if(type.compareTo(PointConstant.PointSourceType.PublishExchange)==0){//当天首次发布换一换
				if(resNum<0){//最近一条记录是昨天及以前
					toAddValue = PointConstant.getPointValueByPointSourceType(type);
				}
			}else if(type.compareTo(PointConstant.PointSourceType.Share)==0){//当天首次分享
				if(resNum<0){//最近一条记录是昨天及以前
					toAddValue = PointConstant.getPointValueByPointSourceType(type);
				}
			}else if(type.compareTo(PointConstant.PointSourceType.Sign)==0){//签到
				if(resNum<0){//最近一条记录是昨天及以前
					if(resNum==-1){//昨天签到
						signDayCount = lastPointSourceRecord.getSignDayCount()+1;//签到持续天数+1
					}else{//昨天之前签到
						signDayCount = 1L;//恢复为第一天签到
					}
					toAddValue = PointConstant.getPointValueBySignDays(signDayCount);
				}
			}else{
				throw new BusinessRuntimeException("pointService.checkAndAddPoint.unknownType");
			}
		}
		if(toAddValue!=null){
			addPoint(userId, type, toAddValue,signDayCount);
		}
	}
	
//	public static void main(String[] args) throws ParseException {
//		System.out.println(DateUtil.daysBetween("2015-1-5 20:17:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2015-1-4 20:18:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2015-1-4 10:17:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2015-1-6 20:17:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2015-1-7 20:17:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2015-1-1 20:17:05", "2015-1-5 20:17:10"));
//		System.out.println(DateUtil.daysBetween("2014-1-1 20:17:05", "2015-1-5 20:17:10"));
//	}
	
}
