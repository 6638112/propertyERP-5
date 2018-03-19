package com.cnfantasia.server.domainbase.groupBuildingRegister.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupBuildingRegister.dao.IGroupBuildingRegisterBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

/**
 * 描述:(注册的小区信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingRegisterBaseService implements IGroupBuildingRegisterBaseService{
	
	private IGroupBuildingRegisterBaseDao groupBuildingRegisterBaseDao;
	public void setGroupBuildingRegisterBaseDao(IGroupBuildingRegisterBaseDao groupBuildingRegisterBaseDao) {
		this.groupBuildingRegisterBaseDao = groupBuildingRegisterBaseDao;
	}
	/**
	 * 根据条件查询(注册的小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> getGroupBuildingRegisterByCondition(Map<String,Object> paramMap){
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(注册的小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> getGroupBuildingRegisterByConditionDim(Map<String,Object> paramMap){
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(注册的小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> getGroupBuildingRegisterByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(注册的小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> getGroupBuildingRegisterByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(注册的小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingRegister getGroupBuildingRegisterBySeqId(java.math.BigInteger id){
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingRegisterCount(Map<String,Object> paramMap){
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupBuildingRegisterCountDim(Map<String,Object> paramMap){
		return groupBuildingRegisterBaseDao.selectGroupBuildingRegisterCount(paramMap,true);
	}
	/**
	 * 往(注册的小区信息)新增一条记录
	 * @param groupBuildingRegister
	 * @return
	 */
	@Override
	public int insertGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister){
		return groupBuildingRegisterBaseDao.insertGroupBuildingRegister(groupBuildingRegister);
	}
	/**
	 * 批量新增(注册的小区信息)
	 * @param groupBuildingRegisterList
	 * @return
	 */
	@Override
	public int insertGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList){
		return groupBuildingRegisterBaseDao.insertGroupBuildingRegisterBatch(groupBuildingRegisterList);
	}
	/**
	 * 更新(注册的小区信息)信息
	 * @param groupBuildingRegister
	 * @return
	 */
	@Override
	public int updateGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister){
		return groupBuildingRegisterBaseDao.updateGroupBuildingRegister(groupBuildingRegister);
	}
	/**
	 * 批量更新(注册的小区信息)信息
	 * @param groupBuildingRegisterList
	 * @return
	 */
	@Override
	public int updateGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList){
		return groupBuildingRegisterBaseDao.updateGroupBuildingRegisterBatch(groupBuildingRegisterList);
	}
	/**
	 * 根据序列号删除(注册的小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingRegisterLogic(java.math.BigInteger id){
		return groupBuildingRegisterBaseDao.deleteGroupBuildingRegisterLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(注册的小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingRegisterLogicBatch(List<java.math.BigInteger> idList){
		return groupBuildingRegisterBaseDao.deleteGroupBuildingRegisterLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(注册的小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingRegister(java.math.BigInteger id){
//		return groupBuildingRegisterBaseDao.deleteGroupBuildingRegister(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(注册的小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingRegisterBatch(List<java.math.BigInteger> idList){
//		return groupBuildingRegisterBaseDao.deleteGroupBuildingRegisterBatch(idList);
//	}
	
}
