package com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;

/**
 * 描述:(上门服务单流程记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillHasProcessRecordingBaseDao {
	/**
	 * 根据条件查询(上门服务单流程记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> selectDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(上门服务单流程记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> selectDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(上门服务单流程记录)信息
	 * @param id
	 * @return
	 */
	public DredgeBillHasProcessRecording selectDredgeBillHasProcessRecordingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeBillHasProcessRecordingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(上门服务单流程记录)新增一条记录
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	public int insertDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording);
	
	/**
	 * 批量新增(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	public int insertDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList);
	
	/**
	 * 更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	public int updateDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording);
	
	/**
	 * 批量更新(上门服务单流程记录)信息
	 * @param dredgeBillHasProcessRecordingList
	 * @return
	 */
	public int updateDredgeBillHasProcessRecordingBatch(List<DredgeBillHasProcessRecording> dredgeBillHasProcessRecordingList);
	
	/**
	 * 根据序列号删除(上门服务单流程记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillHasProcessRecordingLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(上门服务单流程记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillHasProcessRecordingLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(上门服务单流程记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillHasProcessRecording(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(上门服务单流程记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillHasProcessRecordingBatch(List<java.math.BigInteger> idList);
	
	
}
