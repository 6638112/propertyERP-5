package com.cnfantasia.server.domainbase.kitchenCookbookStepTips.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookStepTips.dao.IKitchenCookbookStepTipsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;

/**
 * 描述:(菜谱步骤tips信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookStepTipsBaseService implements IKitchenCookbookStepTipsBaseService{
	
	private IKitchenCookbookStepTipsBaseDao kitchenCookbookStepTipsBaseDao;
	public void setKitchenCookbookStepTipsBaseDao(IKitchenCookbookStepTipsBaseDao kitchenCookbookStepTipsBaseDao) {
		this.kitchenCookbookStepTipsBaseDao = kitchenCookbookStepTipsBaseDao;
	}
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap){
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(菜谱步骤tips信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookStepTips getKitchenCookbookStepTipsBySeqId(java.math.BigInteger id){
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookStepTipsCount(Map<String,Object> paramMap){
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookStepTipsCountDim(Map<String,Object> paramMap){
		return kitchenCookbookStepTipsBaseDao.selectKitchenCookbookStepTipsCount(paramMap,true);
	}
	/**
	 * 往(菜谱步骤tips信息)新增一条记录
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips){
		return kitchenCookbookStepTipsBaseDao.insertKitchenCookbookStepTips(kitchenCookbookStepTips);
	}
	/**
	 * 批量新增(菜谱步骤tips信息)
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList){
		return kitchenCookbookStepTipsBaseDao.insertKitchenCookbookStepTipsBatch(kitchenCookbookStepTipsList);
	}
	/**
	 * 更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips){
		return kitchenCookbookStepTipsBaseDao.updateKitchenCookbookStepTips(kitchenCookbookStepTips);
	}
	/**
	 * 批量更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList){
		return kitchenCookbookStepTipsBaseDao.updateKitchenCookbookStepTipsBatch(kitchenCookbookStepTipsList);
	}
	/**
	 * 根据序列号删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepTipsLogic(java.math.BigInteger id){
		return kitchenCookbookStepTipsBaseDao.deleteKitchenCookbookStepTipsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepTipsLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookStepTipsBaseDao.deleteKitchenCookbookStepTipsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱步骤tips信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepTips(java.math.BigInteger id){
//		return kitchenCookbookStepTipsBaseDao.deleteKitchenCookbookStepTips(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱步骤tips信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepTipsBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookStepTipsBaseDao.deleteKitchenCookbookStepTipsBatch(idList);
//	}
	
}
