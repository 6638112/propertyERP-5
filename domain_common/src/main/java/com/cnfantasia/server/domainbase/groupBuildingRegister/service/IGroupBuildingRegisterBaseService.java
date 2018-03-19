package com.cnfantasia.server.domainbase.groupBuildingRegister.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

/**
 * 描述:(注册的小区信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingRegisterBaseService {
	
	/**
	 * 根据条件查询(注册的小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingRegister> getGroupBuildingRegisterByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(注册的小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GroupBuildingRegister> getGroupBuildingRegisterByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(注册的小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingRegister> getGroupBuildingRegisterByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(注册的小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GroupBuildingRegister> getGroupBuildingRegisterByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(注册的小区信息)信息
	 * @param id
	 * @return
	 */
	public GroupBuildingRegister getGroupBuildingRegisterBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingRegisterCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGroupBuildingRegisterCountDim(Map<String,Object> paramMap);
	/**
	 * 往(注册的小区信息)新增一条记录
	 * @param groupBuildingRegister
	 * @return
	 */
	public int insertGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister);
	/**
	 * 批量新增(注册的小区信息)
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
	 * 根据序列号批量删除(注册的小区信息)信息_逻辑删除
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
