package com.cnfantasia.server.domainbase.tmpFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.tmpFeeItem.dao.ITmpFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

/**
 * 描述:(临时收费项) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class TmpFeeItemBaseService implements ITmpFeeItemBaseService{
	
	private ITmpFeeItemBaseDao tmpFeeItemBaseDao;
	public void setTmpFeeItemBaseDao(ITmpFeeItemBaseDao tmpFeeItemBaseDao) {
		this.tmpFeeItemBaseDao = tmpFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(临时收费项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<TmpFeeItem> getTmpFeeItemByCondition(Map<String,Object> paramMap){
		return tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(临时收费项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<TmpFeeItem> getTmpFeeItemByConditionDim(Map<String,Object> paramMap){
		return tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(临时收费项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<TmpFeeItem> getTmpFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(临时收费项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<TmpFeeItem> getTmpFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(临时收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public TmpFeeItem getTmpFeeItemBySeqId(java.math.BigInteger id){
		return tmpFeeItemBaseDao.selectTmpFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getTmpFeeItemCount(Map<String,Object> paramMap){
		return tmpFeeItemBaseDao.selectTmpFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getTmpFeeItemCountDim(Map<String,Object> paramMap){
		return tmpFeeItemBaseDao.selectTmpFeeItemCount(paramMap,true);
	}
	/**
	 * 往(临时收费项)新增一条记录
	 * @param tmpFeeItem
	 * @return
	 */
	@Override
	public int insertTmpFeeItem(TmpFeeItem tmpFeeItem){
		return tmpFeeItemBaseDao.insertTmpFeeItem(tmpFeeItem);
	}
	/**
	 * 批量新增(临时收费项)
	 * @param tmpFeeItemList
	 * @return
	 */
	@Override
	public int insertTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList){
		return tmpFeeItemBaseDao.insertTmpFeeItemBatch(tmpFeeItemList);
	}
	/**
	 * 更新(临时收费项)信息
	 * @param tmpFeeItem
	 * @return
	 */
	@Override
	public int updateTmpFeeItem(TmpFeeItem tmpFeeItem){
		return tmpFeeItemBaseDao.updateTmpFeeItem(tmpFeeItem);
	}
	/**
	 * 批量更新(临时收费项)信息
	 * @param tmpFeeItemList
	 * @return
	 */
	@Override
	public int updateTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList){
		return tmpFeeItemBaseDao.updateTmpFeeItemBatch(tmpFeeItemList);
	}
	/**
	 * 根据序列号删除(临时收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteTmpFeeItemLogic(java.math.BigInteger id){
		return tmpFeeItemBaseDao.deleteTmpFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(临时收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteTmpFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return tmpFeeItemBaseDao.deleteTmpFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(临时收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteTmpFeeItem(java.math.BigInteger id){
//		return tmpFeeItemBaseDao.deleteTmpFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteTmpFeeItemBatch(List<java.math.BigInteger> idList){
//		return tmpFeeItemBaseDao.deleteTmpFeeItemBatch(idList);
//	}
	
}
