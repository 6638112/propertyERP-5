package com.cnfantasia.server.domainbase.livingFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.livingFeeItem.dao.ILivingFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeeItem.entity.LivingFeeItem;

/**
 * 描述:(生活缴费支持的项目类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LivingFeeItemBaseService implements ILivingFeeItemBaseService{
	
	private ILivingFeeItemBaseDao livingFeeItemBaseDao;
	public void setLivingFeeItemBaseDao(ILivingFeeItemBaseDao livingFeeItemBaseDao) {
		this.livingFeeItemBaseDao = livingFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(生活缴费支持的项目类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeeItem> getLivingFeeItemByCondition(Map<String,Object> paramMap){
		return livingFeeItemBaseDao.selectLivingFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(生活缴费支持的项目类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeeItem> getLivingFeeItemByConditionDim(Map<String,Object> paramMap){
		return livingFeeItemBaseDao.selectLivingFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(生活缴费支持的项目类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeeItem> getLivingFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeeItemBaseDao.selectLivingFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(生活缴费支持的项目类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeeItem> getLivingFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeeItemBaseDao.selectLivingFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(生活缴费支持的项目类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public LivingFeeItem getLivingFeeItemBySeqId(java.math.BigInteger id){
		return livingFeeItemBaseDao.selectLivingFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(生活缴费支持的项目类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeeItemCount(Map<String,Object> paramMap){
		return livingFeeItemBaseDao.selectLivingFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(生活缴费支持的项目类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeeItemCountDim(Map<String,Object> paramMap){
		return livingFeeItemBaseDao.selectLivingFeeItemCount(paramMap,true);
	}
	/**
	 * 往(生活缴费支持的项目类别)新增一条记录
	 * @param livingFeeItem
	 * @return
	 */
	@Override
	public int insertLivingFeeItem(LivingFeeItem livingFeeItem){
		return livingFeeItemBaseDao.insertLivingFeeItem(livingFeeItem);
	}
	/**
	 * 批量新增(生活缴费支持的项目类别)
	 * @param livingFeeItemList
	 * @return
	 */
	@Override
	public int insertLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList){
		return livingFeeItemBaseDao.insertLivingFeeItemBatch(livingFeeItemList);
	}
	/**
	 * 更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItem
	 * @return
	 */
	@Override
	public int updateLivingFeeItem(LivingFeeItem livingFeeItem){
		return livingFeeItemBaseDao.updateLivingFeeItem(livingFeeItem);
	}
	/**
	 * 批量更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItemList
	 * @return
	 */
	@Override
	public int updateLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList){
		return livingFeeItemBaseDao.updateLivingFeeItemBatch(livingFeeItemList);
	}
	/**
	 * 根据序列号删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeItemLogic(java.math.BigInteger id){
		return livingFeeItemBaseDao.deleteLivingFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return livingFeeItemBaseDao.deleteLivingFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(生活缴费支持的项目类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeItem(java.math.BigInteger id){
//		return livingFeeItemBaseDao.deleteLivingFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(生活缴费支持的项目类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeItemBatch(List<java.math.BigInteger> idList){
//		return livingFeeItemBaseDao.deleteLivingFeeItemBatch(idList);
//	}
	
}
