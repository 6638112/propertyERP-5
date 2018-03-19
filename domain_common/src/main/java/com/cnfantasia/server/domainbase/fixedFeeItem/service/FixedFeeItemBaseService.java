package com.cnfantasia.server.domainbase.fixedFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.fixedFeeItem.dao.IFixedFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;

/**
 * 描述:(固定收费项) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FixedFeeItemBaseService implements IFixedFeeItemBaseService{
	
	private IFixedFeeItemBaseDao fixedFeeItemBaseDao;
	public void setFixedFeeItemBaseDao(IFixedFeeItemBaseDao fixedFeeItemBaseDao) {
		this.fixedFeeItemBaseDao = fixedFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(固定收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItem> getFixedFeeItemByCondition(Map<String,Object> paramMap){
		return fixedFeeItemBaseDao.selectFixedFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(固定收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItem> getFixedFeeItemByConditionDim(Map<String,Object> paramMap){
		return fixedFeeItemBaseDao.selectFixedFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(固定收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItem> getFixedFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemBaseDao.selectFixedFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(固定收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItem> getFixedFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemBaseDao.selectFixedFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(固定收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public FixedFeeItem getFixedFeeItemBySeqId(java.math.BigInteger id){
		return fixedFeeItemBaseDao.selectFixedFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemCount(Map<String,Object> paramMap){
		return fixedFeeItemBaseDao.selectFixedFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemCountDim(Map<String,Object> paramMap){
		return fixedFeeItemBaseDao.selectFixedFeeItemCount(paramMap,true);
	}
	/**
	 * 往(固定收费项)新增一条记录
	 * @param fixedFeeItem
	 * @return
	 */
	@Override
	public int insertFixedFeeItem(FixedFeeItem fixedFeeItem){
		return fixedFeeItemBaseDao.insertFixedFeeItem(fixedFeeItem);
	}
	/**
	 * 批量新增(固定收费项)
	 * @param fixedFeeItemList
	 * @return
	 */
	@Override
	public int insertFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList){
		return fixedFeeItemBaseDao.insertFixedFeeItemBatch(fixedFeeItemList);
	}
	/**
	 * 更新(固定收费项)信息
	 * @param fixedFeeItem
	 * @return
	 */
	@Override
	public int updateFixedFeeItem(FixedFeeItem fixedFeeItem){
		return fixedFeeItemBaseDao.updateFixedFeeItem(fixedFeeItem);
	}
	/**
	 * 批量更新(固定收费项)信息
	 * @param fixedFeeItemList
	 * @return
	 */
	@Override
	public int updateFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList){
		return fixedFeeItemBaseDao.updateFixedFeeItemBatch(fixedFeeItemList);
	}
	/**
	 * 根据序列号删除(固定收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemLogic(java.math.BigInteger id){
		return fixedFeeItemBaseDao.deleteFixedFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(固定收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return fixedFeeItemBaseDao.deleteFixedFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(固定收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItem(java.math.BigInteger id){
//		return fixedFeeItemBaseDao.deleteFixedFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(固定收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemBatch(List<java.math.BigInteger> idList){
//		return fixedFeeItemBaseDao.deleteFixedFeeItemBatch(idList);
//	}
	
}
