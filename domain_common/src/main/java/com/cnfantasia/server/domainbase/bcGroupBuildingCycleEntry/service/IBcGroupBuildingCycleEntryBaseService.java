package com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;

/**
 * 描述:(银行托收账期明细) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcGroupBuildingCycleEntryBaseService {
	
	/**
	 * 根据条件查询(银行托收账期明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(银行托收账期明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(银行托收账期明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(银行托收账期明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(银行托收账期明细)信息
	 * @param id
	 * @return
	 */
	public BcGroupBuildingCycleEntry getBcGroupBuildingCycleEntryBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(银行托收账期明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcGroupBuildingCycleEntryCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(银行托收账期明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcGroupBuildingCycleEntryCountDim(Map<String,Object> paramMap);
	/**
	 * 往(银行托收账期明细)新增一条记录
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	public int insertBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry);
	/**
	 * 批量新增(银行托收账期明细)
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	public int insertBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList);
	/**
	 * 更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	public int updateBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry);
	/**
	 * 批量更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	public int updateBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList);
	/**
	 * 根据序列号删除(银行托收账期明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcGroupBuildingCycleEntryLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(银行托收账期明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcGroupBuildingCycleEntryLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(银行托收账期明细)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcGroupBuildingCycleEntry(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(银行托收账期明细)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcGroupBuildingCycleEntryBatch(List<java.math.BigInteger> idList);
	
}
