package com.cnfantasia.server.domainbase.buildingKeyList.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.buildingKeyList.entity.BuildingKeyList;

/**
 * 描述:(开通门禁的小区楼栋列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBuildingKeyListBaseService {
	
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BuildingKeyList> getBuildingKeyListByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BuildingKeyList> getBuildingKeyListByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BuildingKeyList> getBuildingKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BuildingKeyList> getBuildingKeyListByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(开通门禁的小区楼栋列表)信息
	 * @param id
	 * @return
	 */
	public BuildingKeyList getBuildingKeyListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingKeyListCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBuildingKeyListCountDim(Map<String,Object> paramMap);
	/**
	 * 往(开通门禁的小区楼栋列表)新增一条记录
	 * @param buildingKeyList
	 * @return
	 */
	public int insertBuildingKeyList(BuildingKeyList buildingKeyList);
	/**
	 * 批量新增(开通门禁的小区楼栋列表)
	 * @param buildingKeyListList
	 * @return
	 */
	public int insertBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList);
	/**
	 * 更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyList
	 * @return
	 */
	public int updateBuildingKeyList(BuildingKeyList buildingKeyList);
	/**
	 * 批量更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyListList
	 * @return
	 */
	public int updateBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList);
	/**
	 * 根据序列号删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBuildingKeyListLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBuildingKeyListLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(开通门禁的小区楼栋列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBuildingKeyList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(开通门禁的小区楼栋列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBuildingKeyListBatch(List<java.math.BigInteger> idList);
	
}
