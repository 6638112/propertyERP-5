package com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.entity.BcInfoHasTGroupBuilding;

/**
 * 描述:(物业公司托收银行信息包含的小区) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcInfoHasTGroupBuildingBaseDao {
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> selectBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcInfoHasTGroupBuilding> selectBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的小区)信息
	 * @param id
	 * @return
	 */
	public BcInfoHasTGroupBuilding selectBcInfoHasTGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcInfoHasTGroupBuildingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业公司托收银行信息包含的小区)新增一条记录
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	public int insertBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding);
	
	/**
	 * 批量新增(物业公司托收银行信息包含的小区)信息
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
	 * 根据Id 批量删除(物业公司托收银行信息包含的小区)信息_逻辑删除
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
