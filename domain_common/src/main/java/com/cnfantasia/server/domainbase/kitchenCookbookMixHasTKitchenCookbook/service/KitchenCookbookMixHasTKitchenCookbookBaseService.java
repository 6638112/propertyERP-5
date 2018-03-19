package com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.dao.IKitchenCookbookMixHasTKitchenCookbookBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.entity.KitchenCookbookMixHasTKitchenCookbook;

/**
 * 描述:(组合菜谱所包含的菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookMixHasTKitchenCookbookBaseService implements IKitchenCookbookMixHasTKitchenCookbookBaseService{
	
	private IKitchenCookbookMixHasTKitchenCookbookBaseDao kitchenCookbookMixHasTKitchenCookbookBaseDao;
	public void setKitchenCookbookMixHasTKitchenCookbookBaseDao(IKitchenCookbookMixHasTKitchenCookbookBaseDao kitchenCookbookMixHasTKitchenCookbookBaseDao) {
		this.kitchenCookbookMixHasTKitchenCookbookBaseDao = kitchenCookbookMixHasTKitchenCookbookBaseDao;
	}
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(组合菜谱所包含的菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookMixHasTKitchenCookbook getKitchenCookbookMixHasTKitchenCookbookBySeqId(java.math.BigInteger id){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookMixHasTKitchenCookbookCount(Map<String,Object> paramMap){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookMixHasTKitchenCookbookCountDim(Map<String,Object> paramMap){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.selectKitchenCookbookMixHasTKitchenCookbookCount(paramMap,true);
	}
	/**
	 * 往(组合菜谱所包含的菜谱)新增一条记录
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.insertKitchenCookbookMixHasTKitchenCookbook(kitchenCookbookMixHasTKitchenCookbook);
	}
	/**
	 * 批量新增(组合菜谱所包含的菜谱)
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.insertKitchenCookbookMixHasTKitchenCookbookBatch(kitchenCookbookMixHasTKitchenCookbookList);
	}
	/**
	 * 更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.updateKitchenCookbookMixHasTKitchenCookbook(kitchenCookbookMixHasTKitchenCookbook);
	}
	/**
	 * 批量更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.updateKitchenCookbookMixHasTKitchenCookbookBatch(kitchenCookbookMixHasTKitchenCookbookList);
	}
	/**
	 * 根据序列号删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogic(java.math.BigInteger id){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.deleteKitchenCookbookMixHasTKitchenCookbookLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookMixHasTKitchenCookbookBaseDao.deleteKitchenCookbookMixHasTKitchenCookbookLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(组合菜谱所包含的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMixHasTKitchenCookbook(java.math.BigInteger id){
//		return kitchenCookbookMixHasTKitchenCookbookBaseDao.deleteKitchenCookbookMixHasTKitchenCookbook(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(组合菜谱所包含的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMixHasTKitchenCookbookBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookMixHasTKitchenCookbookBaseDao.deleteKitchenCookbookMixHasTKitchenCookbookBatch(idList);
//	}
	
}
