package com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;

/**
 * 描述:(房间最后一次抄表读数) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomHasMrLastRecordBaseDao {
	/**
	 * 根据条件查询(房间最后一次抄表读数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> selectRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(房间最后一次抄表读数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoomHasMrLastRecord> selectRealRoomHasMrLastRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(房间最后一次抄表读数)信息
	 * @param id
	 * @return
	 */
	public RealRoomHasMrLastRecord selectRealRoomHasMrLastRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间最后一次抄表读数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRealRoomHasMrLastRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(房间最后一次抄表读数)新增一条记录
	 * @param realRoomHasMrLastRecord
	 * @return
	 */
	public int insertRealRoomHasMrLastRecord(RealRoomHasMrLastRecord realRoomHasMrLastRecord);
	
	/**
	 * 批量新增(房间最后一次抄表读数)信息
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
	 * 根据Id 批量删除(房间最后一次抄表读数)信息_逻辑删除
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
