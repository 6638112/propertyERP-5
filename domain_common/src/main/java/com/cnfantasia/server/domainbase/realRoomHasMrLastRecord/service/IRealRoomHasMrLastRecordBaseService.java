package com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;

/**
 * 描述:(房间最后一次抄表读数) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomHasMrLastRecordBaseService {
	
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> getRealRoomHasMrLastRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(房间最后一次抄表读数)信息
	 * @param id
	 * @return
	 */
	public RealRoomHasMrLastRecord getRealRoomHasMrLastRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomHasMrLastRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomHasMrLastRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(房间最后一次抄表读数)新增一条记录
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	public int insertRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord);
	/**
	 * 批量新增(房间最后一次抄表读数)
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	public int insertRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList);
	/**
	 * 更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	public int updateRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord);
	/**
	 * 批量更新(房间最后一次抄表读数)信息
	 * @param realRoomHasMrLastRecordList
	 * @return
	 */
	public int updateRealRoomHasMrLastRecordBatch(List<RealRoomHasMrLastRecord> realRoomHasMrLastRecordList);
	/**
	 * 根据序列号删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealRoomHasMrLastRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间最后一次抄表读数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealRoomHasMrLastRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间最后一次抄表读数)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealRoomHasMrLastRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间最后一次抄表读数)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealRoomHasMrLastRecordBatch(List<java.math.BigInteger> idList);
	
}
