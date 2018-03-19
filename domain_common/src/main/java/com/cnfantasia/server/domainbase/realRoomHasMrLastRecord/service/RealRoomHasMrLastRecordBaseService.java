package com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao.IRealRoomHasMrLastRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;

/**
 * 描述:(房间最后一次抄表读数) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealRoomHasMrLastRecordBaseService implements IRealRoomHasMrLastRecordBaseService{
	
	private IRealRoomHasMrLastRecordBaseDao realRoomHasMrLastRecordBaseDao;
	public void setRealRoomHasMrLastRecordBaseDao(IRealRoomHasMrLastRecordBaseDao realRoomHasMrLastRecordBaseDao) {
		this.realRoomHasMrLastRecordBaseDao = realRoomHasMrLastRecordBaseDao;
	}
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap){
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByConditionDim(Map<String,Object> paramMap){
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间最后一次抄表读数)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoomHasMrLastRecord getRealRoomHasMrLastRecordBySeqId(java.math.BigInteger id){
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasMrLastRecordCount(Map<String,Object> paramMap){
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasMrLastRecordCountDim(Map<String,Object> paramMap){
		return realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordCount(paramMap,true);
	}
	/**
	 * 往(房间最后一次抄表读数)新增一条记录
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	@Override
	public int insertRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord){
		return realRoomHasMrLastRecordBaseDao.insertRealRoomHasMrLastRecord(realRoomHasMrLastRecord);
	}
	/**
	 * 批量新增(房间最后一次抄表读数)
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	@Override
	public int insertRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList){
		return realRoomHasMrLastRecordBaseDao.insertRealRoomHasMrLastRecordBatch(realRoomHasMrLastRecordList);
	}
	/**
	 * 更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	@Override
	public int updateRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord){
		return realRoomHasMrLastRecordBaseDao.updateRealRoomHasMrLastRecord(realRoomHasMrLastRecord);
	}
	/**
	 * 批量更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	@Override
	public int updateRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList){
		return realRoomHasMrLastRecordBaseDao.updateRealRoomHasMrLastRecordBatch(realRoomHasMrLastRecordList);
	}
	/**
	 * 根据序列号删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasMrLastRecordLogic(java.math.BigInteger id){
		return realRoomHasMrLastRecordBaseDao.deleteRealRoomHasMrLastRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasMrLastRecordLogicBatch(List<java.math.BigInteger> idList){
		return realRoomHasMrLastRecordBaseDao.deleteRealRoomHasMrLastRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间最后一次抄表读数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasMrLastRecord(java.math.BigInteger id){
//		return realRoomHasMrLastRecordBaseDao.deleteRealRoomHasMrLastRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间最后一次抄表读数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasMrLastRecordBatch(List<java.math.BigInteger> idList){
//		return realRoomHasMrLastRecordBaseDao.deleteRealRoomHasMrLastRecordBatch(idList);
//	}
	
}
