package com.cnfantasia.server.domainbase.groupBuildingRegister.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

/**
 * 描述:(注册的小区信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingRegisterBaseDao {
	/**
	 * 根据条件查询(注册的小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingRegister> selectGroupBuildingRegisterByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(注册的小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GroupBuildingRegister> selectGroupBuildingRegisterByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(注册的小区信息)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingRegister selectGroupBuildingRegisterBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGroupBuildingRegisterCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(注册的小区信息)新增一条记录
	 * @param groupBuildingRegister
	 * @return
	 */
	public int insertGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister);
	
	/**
	 * 批量新增(注册的小区信息)信息
	 * @param groupBuildingRegisterList
	 * @return
	 */
	public int insertGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList);
	
	/**
	 * 更新(注册的小区信息)信息
	 * @param groupBuildingRegister
	 * @return
	 */
	public int updateGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister);
	
	/**
	 * 批量更新(注册的小区信息)信息
	 * @param groupBuildingRegisterList
	 * @return
	 */
	public int updateGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList);
	
	/**
	 * 根据序列号删除(注册的小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGroupBuildingRegisterLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(注册的小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGroupBuildingRegisterLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(注册的小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGroupBuildingRegister(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(注册的小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGroupBuildingRegisterBatch(List<java.math.BigInteger> idList);
	
	
}
