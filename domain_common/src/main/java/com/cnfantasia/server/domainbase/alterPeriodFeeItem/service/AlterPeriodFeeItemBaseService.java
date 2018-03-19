package com.cnfantasia.server.domainbase.alterPeriodFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.alterPeriodFeeItem.dao.IAlterPeriodFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;

/**
 * 描述:(选择周期收费项) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AlterPeriodFeeItemBaseService implements IAlterPeriodFeeItemBaseService{
	
	private IAlterPeriodFeeItemBaseDao alterPeriodFeeItemBaseDao;
	public void setAlterPeriodFeeItemBaseDao(IAlterPeriodFeeItemBaseDao alterPeriodFeeItemBaseDao) {
		this.alterPeriodFeeItemBaseDao = alterPeriodFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(选择周期收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> getAlterPeriodFeeItemByCondition(Map<String,Object> paramMap){
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(选择周期收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> getAlterPeriodFeeItemByConditionDim(Map<String,Object> paramMap){
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(选择周期收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> getAlterPeriodFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(选择周期收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> getAlterPeriodFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(选择周期收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodFeeItem getAlterPeriodFeeItemBySeqId(java.math.BigInteger id){
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodFeeItemCount(Map<String,Object> paramMap){
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(选择周期收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodFeeItemCountDim(Map<String,Object> paramMap){
		return alterPeriodFeeItemBaseDao.selectAlterPeriodFeeItemCount(paramMap,true);
	}
	/**
	 * 往(选择周期收费项)新增一条记录
	 * @param alterPeriodFeeItem
	 * @return
	 */
	@Override
	public int insertAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem){
		return alterPeriodFeeItemBaseDao.insertAlterPeriodFeeItem(alterPeriodFeeItem);
	}
	/**
	 * 批量新增(选择周期收费项)
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	@Override
	public int insertAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList){
		return alterPeriodFeeItemBaseDao.insertAlterPeriodFeeItemBatch(alterPeriodFeeItemList);
	}
	/**
	 * 更新(选择周期收费项)信息
	 * @param alterPeriodFeeItem
	 * @return
	 */
	@Override
	public int updateAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem){
		return alterPeriodFeeItemBaseDao.updateAlterPeriodFeeItem(alterPeriodFeeItem);
	}
	/**
	 * 批量更新(选择周期收费项)信息
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	@Override
	public int updateAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList){
		return alterPeriodFeeItemBaseDao.updateAlterPeriodFeeItemBatch(alterPeriodFeeItemList);
	}
	/**
	 * 根据序列号删除(选择周期收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodFeeItemLogic(java.math.BigInteger id){
		return alterPeriodFeeItemBaseDao.deleteAlterPeriodFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(选择周期收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return alterPeriodFeeItemBaseDao.deleteAlterPeriodFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodFeeItem(java.math.BigInteger id){
//		return alterPeriodFeeItemBaseDao.deleteAlterPeriodFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodFeeItemBatch(List<java.math.BigInteger> idList){
//		return alterPeriodFeeItemBaseDao.deleteAlterPeriodFeeItemBatch(idList);
//	}
	
}
