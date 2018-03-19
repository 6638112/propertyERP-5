package com.cnfantasia.server.domainbase.bcGroupBuildingCycle.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;

/**
 * 描述:(托收账期记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcGroupBuildingCycleBaseDao {
	/**
	 * 根据条件查询(托收账期记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcGroupBuildingCycle> selectBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(托收账期记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcGroupBuildingCycle> selectBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(托收账期记录)信息
	 * @param id
	 * @return
	 */
	public BcGroupBuildingCycle selectBcGroupBuildingCycleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcGroupBuildingCycleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(托收账期记录)新增一条记录
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	public int insertBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle);
	
	/**
	 * 批量新增(托收账期记录)信息
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	public int insertBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList);
	
	/**
	 * 更新(托收账期记录)信息
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	public int updateBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle);
	
	/**
	 * 批量更新(托收账期记录)信息
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	public int updateBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList);
	
	/**
	 * 根据序列号删除(托收账期记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcGroupBuildingCycleLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(托收账期记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcGroupBuildingCycleLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(托收账期记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcGroupBuildingCycle(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(托收账期记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcGroupBuildingCycleBatch(List<java.math.BigInteger> idList);
	
	
}
