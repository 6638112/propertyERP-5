package com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.entity.BcInfoHasTGroupBuilding;

/**
 * 描述:(物业公司托收银行信息包含的小区) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcInfoHasTGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的小区)信息
	 * @param id
	 * @return
	 */
	public BcInfoHasTGroupBuilding getBcInfoHasTGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcInfoHasTGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcInfoHasTGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业公司托收银行信息包含的小区)新增一条记录
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	public int insertBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding);
	/**
	 * 批量新增(物业公司托收银行信息包含的小区)
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	public int insertBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList);
	/**
	 * 更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	public int updateBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding);
	/**
	 * 批量更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	public int updateBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList);
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcInfoHasTGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcInfoHasTGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcInfoHasTGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的小区)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcInfoHasTGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
