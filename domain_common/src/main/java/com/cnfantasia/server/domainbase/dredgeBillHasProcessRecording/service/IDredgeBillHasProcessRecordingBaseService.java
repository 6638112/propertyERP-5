package com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;

/**
 * 描述:(上门服务单流程记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillHasProcessRecordingBaseService {
	
	/**
	 * 根据条件查询(上门服务单流程记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(上门服务单流程记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(上门服务单流程记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(上门服务单流程记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasProcessRecording> getDredgeBillHasProcessRecordingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(上门服务单流程记录)信息
	 * @param id
	 * @return
	 */
	public DredgeBillHasProcessRecording getDredgeBillHasProcessRecordingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasProcessRecordingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(上门服务单流程记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasProcessRecordingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(上门服务单流程记录)新增一条记录
	 * @param dredgeBillHasProcessRecording
	 * @return
	 */
	public int insertDredgeBillHasProcessRecording(DredgeBillHasProcessRecording dredgeBillHasProcessRecording);
	/**
	 * 批量新增(上门服务单流程记录)
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
	 * 根据序列号批量删除(上门服务单流程记录)信息_逻辑删除
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
