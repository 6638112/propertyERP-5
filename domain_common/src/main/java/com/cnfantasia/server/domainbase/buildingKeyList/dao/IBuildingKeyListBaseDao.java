package com.cnfantasia.server.domainbase.buildingKeyList.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.buildingKeyList.entity.BuildingKeyList;

/**
 * 描述:(开通门禁的小区楼栋列表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBuildingKeyListBaseDao {
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BuildingKeyList> selectBuildingKeyListByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BuildingKeyList> selectBuildingKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(开通门禁的小区楼栋列表)信息
	 * @param id
	 * @return
	 */
	public BuildingKeyList selectBuildingKeyListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBuildingKeyListCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(开通门禁的小区楼栋列表)新增一条记录
	 * @param buildingKeyList
	 * @return
	 */
	public int insertBuildingKeyList(BuildingKeyList buildingKeyList);
	
	/**
	 * 批量新增(开通门禁的小区楼栋列表)信息
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
	 * 根据Id 批量删除(开通门禁的小区楼栋列表)信息_逻辑删除
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
