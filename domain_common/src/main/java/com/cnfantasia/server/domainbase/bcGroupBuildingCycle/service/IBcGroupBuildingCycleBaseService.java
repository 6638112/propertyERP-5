package com.cnfantasia.server.domainbase.bcGroupBuildingCycle.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;

/**
 * 描述:(托收账期记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcGroupBuildingCycleBaseService {
	
	/**
	 * 根据条件查询(托收账期记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(托收账期记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(托收账期记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(托收账期记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(托收账期记录)信息
	 * @param id
	 * @return
	 */
	public BcGroupBuildingCycle getBcGroupBuildingCycleBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcGroupBuildingCycleCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcGroupBuildingCycleCountDim(Map<String,Object> paramMap);
	/**
	 * 往(托收账期记录)新增一条记录
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	public int insertBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle);
	/**
	 * 批量新增(托收账期记录)
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
	 * 根据序列号批量删除(托收账期记录)信息_逻辑删除
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
