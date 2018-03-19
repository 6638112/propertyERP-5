package com.cnfantasia.server.domainbase.openDoorLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;

/**
 * 描述:(车牌表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOpenDoorLogBaseDao {
	/**
	 * 根据条件查询(车牌表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OpenDoorLog> selectOpenDoorLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(车牌表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OpenDoorLog> selectOpenDoorLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	public OpenDoorLog selectOpenDoorLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(车牌表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOpenDoorLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(车牌表)新增一条记录
	 * @param openDoorLog
	 * @return
	 */
	public int insertOpenDoorLog(OpenDoorLog openDoorLog);
	
	/**
	 * 批量新增(车牌表)信息
	 * @param openDoorLogList
	 * @return
	 */
	public int insertOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList);
	
	/**
	 * 更新(车牌表)信息
	 * @param openDoorLog
	 * @return
	 */
	public int updateOpenDoorLog(OpenDoorLog openDoorLog);
	
	/**
	 * 批量更新(车牌表)信息
	 * @param openDoorLogList
	 * @return
	 */
	public int updateOpenDoorLogBatch(List<OpenDoorLog> openDoorLogList);
	
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOpenDoorLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOpenDoorLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOpenDoorLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOpenDoorLogBatch(List<java.math.BigInteger> idList);
	
	
}
