package com.cnfantasia.server.domainbase.commUuid.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commUuid.dao.ICommUuidBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;

/**
 * 描述:(唯一编号) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommUuidBaseService implements ICommUuidBaseService{
	
	private ICommUuidBaseDao commUuidBaseDao;
	public void setCommUuidBaseDao(ICommUuidBaseDao commUuidBaseDao) {
		this.commUuidBaseDao = commUuidBaseDao;
	}
	/**
	 * 根据条件查询(唯一编号)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommUuid> getCommUuidByCondition(Map<String,Object> paramMap){
		return commUuidBaseDao.selectCommUuidByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(唯一编号)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommUuid> getCommUuidByConditionDim(Map<String,Object> paramMap){
		return commUuidBaseDao.selectCommUuidByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(唯一编号)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommUuid> getCommUuidByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commUuidBaseDao.selectCommUuidByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(唯一编号)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommUuid> getCommUuidByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commUuidBaseDao.selectCommUuidByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(唯一编号)信息
	 * @param tableName
	 * @return
	 */
	@Override
	public CommUuid getCommUuidBySeqId(java.lang.String tableName){
		return commUuidBaseDao.selectCommUuidBySeqId(tableName);
	}
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommUuidCount(Map<String,Object> paramMap){
		return commUuidBaseDao.selectCommUuidCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommUuidCountDim(Map<String,Object> paramMap){
		return commUuidBaseDao.selectCommUuidCount(paramMap,true);
	}
	/**
	 * 往(唯一编号)新增一条记录
	 * @param commUuid
	 * @return
	 */
	@Override
	public int insertCommUuid(CommUuid commUuid){
		return commUuidBaseDao.insertCommUuid(commUuid);
	}
	/**
	 * 批量新增(唯一编号)
	 * @param commUuidList
	 * @return
	 */
	@Override
	public int insertCommUuidBatch(List<CommUuid> commUuidList){
		return commUuidBaseDao.insertCommUuidBatch(commUuidList);
	}
	/**
	 * 更新(唯一编号)信息
	 * @param commUuid
	 * @return
	 */
	@Override
	public int updateCommUuid(CommUuid commUuid){
		return commUuidBaseDao.updateCommUuid(commUuid);
	}
	/**
	 * 批量更新(唯一编号)信息
	 * @param commUuidList
	 * @return
	 */
	@Override
	public int updateCommUuidBatch(List<CommUuid> commUuidList){
		return commUuidBaseDao.updateCommUuidBatch(commUuidList);
	}
	/**
	 * 根据序列号删除(唯一编号)信息_逻辑删除
	 * @param tableName
	 * @return
	 */
	
	@Override
	public int deleteCommUuidLogic(java.lang.String tableName){
		return commUuidBaseDao.deleteCommUuidLogic(tableName);
	}
	
	/**
	 * 根据序列号批量删除(唯一编号)信息_逻辑删除
	 * @param tableNameList
	 * @return
	 */
	
	@Override
	public int deleteCommUuidLogicBatch(List<java.lang.String> tableNameList){
		return commUuidBaseDao.deleteCommUuidLogicBatch(tableNameList);
	}
	
//	/**
//	 * 根据序列号删除(唯一编号)信息
//	 * @param tableName
//	 * @return
//	 */
//	@Override
//	public int deleteCommUuid(java.lang.String tableName){
//		return commUuidBaseDao.deleteCommUuid(tableName);
//	}
//	
//	/**
//	 * 根据序列号批量删除(唯一编号)信息
//	 * @param tableNameList
//	 * @return
//	 */
//	@Override
//	public int deleteCommUuidBatch(List<java.lang.String> tableNameList){
//		return commUuidBaseDao.deleteCommUuidBatch(tableNameList);
//	}
	
}
