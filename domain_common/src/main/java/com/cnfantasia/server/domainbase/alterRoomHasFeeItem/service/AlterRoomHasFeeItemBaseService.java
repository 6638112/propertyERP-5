package com.cnfantasia.server.domainbase.alterRoomHasFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.alterRoomHasFeeItem.dao.IAlterRoomHasFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;

/**
 * 描述:(房间收费项表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AlterRoomHasFeeItemBaseService implements IAlterRoomHasFeeItemBaseService{
	
	private IAlterRoomHasFeeItemBaseDao alterRoomHasFeeItemBaseDao;
	public void setAlterRoomHasFeeItemBaseDao(IAlterRoomHasFeeItemBaseDao alterRoomHasFeeItemBaseDao) {
		this.alterRoomHasFeeItemBaseDao = alterRoomHasFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(房间收费项表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByCondition(Map<String,Object> paramMap){
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间收费项表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByConditionDim(Map<String,Object> paramMap){
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间收费项表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间收费项表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间收费项表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterRoomHasFeeItem getAlterRoomHasFeeItemBySeqId(java.math.BigInteger id){
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间收费项表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterRoomHasFeeItemCount(Map<String,Object> paramMap){
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间收费项表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterRoomHasFeeItemCountDim(Map<String,Object> paramMap){
		return alterRoomHasFeeItemBaseDao.selectAlterRoomHasFeeItemCount(paramMap,true);
	}
	/**
	 * 往(房间收费项表)新增一条记录
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	@Override
	public int insertAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem){
		return alterRoomHasFeeItemBaseDao.insertAlterRoomHasFeeItem(alterRoomHasFeeItem);
	}
	/**
	 * 批量新增(房间收费项表)
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	@Override
	public int insertAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList){
		return alterRoomHasFeeItemBaseDao.insertAlterRoomHasFeeItemBatch(alterRoomHasFeeItemList);
	}
	/**
	 * 更新(房间收费项表)信息
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	@Override
	public int updateAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem){
		return alterRoomHasFeeItemBaseDao.updateAlterRoomHasFeeItem(alterRoomHasFeeItem);
	}
	/**
	 * 批量更新(房间收费项表)信息
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	@Override
	public int updateAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList){
		return alterRoomHasFeeItemBaseDao.updateAlterRoomHasFeeItemBatch(alterRoomHasFeeItemList);
	}
	/**
	 * 根据序列号删除(房间收费项表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterRoomHasFeeItemLogic(java.math.BigInteger id){
		return alterRoomHasFeeItemBaseDao.deleteAlterRoomHasFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间收费项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterRoomHasFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return alterRoomHasFeeItemBaseDao.deleteAlterRoomHasFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间收费项表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterRoomHasFeeItem(java.math.BigInteger id){
//		return alterRoomHasFeeItemBaseDao.deleteAlterRoomHasFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间收费项表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterRoomHasFeeItemBatch(List<java.math.BigInteger> idList){
//		return alterRoomHasFeeItemBaseDao.deleteAlterRoomHasFeeItemBatch(idList);
//	}
	
}
