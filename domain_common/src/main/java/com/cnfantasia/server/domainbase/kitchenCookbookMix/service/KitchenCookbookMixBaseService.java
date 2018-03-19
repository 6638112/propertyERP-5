package com.cnfantasia.server.domainbase.kitchenCookbookMix.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookMix.dao.IKitchenCookbookMixBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookMix.entity.KitchenCookbookMix;

/**
 * 描述:(厨房组合菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookMixBaseService implements IKitchenCookbookMixBaseService{
	
	private IKitchenCookbookMixBaseDao kitchenCookbookMixBaseDao;
	public void setKitchenCookbookMixBaseDao(IKitchenCookbookMixBaseDao kitchenCookbookMixBaseDao) {
		this.kitchenCookbookMixBaseDao = kitchenCookbookMixBaseDao;
	}
	/**
	 * 根据条件查询(厨房组合菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookMix> getKitchenCookbookMixByCondition(Map<String,Object> paramMap){
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(厨房组合菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookMix> getKitchenCookbookMixByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(厨房组合菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookMix> getKitchenCookbookMixByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(厨房组合菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookMix> getKitchenCookbookMixByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(厨房组合菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookMix getKitchenCookbookMixBySeqId(java.math.BigInteger id){
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(厨房组合菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookMixCount(Map<String,Object> paramMap){
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(厨房组合菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookMixCountDim(Map<String,Object> paramMap){
		return kitchenCookbookMixBaseDao.selectKitchenCookbookMixCount(paramMap,true);
	}
	/**
	 * 往(厨房组合菜谱)新增一条记录
	 * @param kitchenCookbookMix
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMix(KitchenCookbookMix kitchenCookbookMix){
		return kitchenCookbookMixBaseDao.insertKitchenCookbookMix(kitchenCookbookMix);
	}
	/**
	 * 批量新增(厨房组合菜谱)
	 * @param kitchenCookbookMixList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMixBatch(List<KitchenCookbookMix> kitchenCookbookMixList){
		return kitchenCookbookMixBaseDao.insertKitchenCookbookMixBatch(kitchenCookbookMixList);
	}
	/**
	 * 更新(厨房组合菜谱)信息
	 * @param kitchenCookbookMix
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMix(KitchenCookbookMix kitchenCookbookMix){
		return kitchenCookbookMixBaseDao.updateKitchenCookbookMix(kitchenCookbookMix);
	}
	/**
	 * 批量更新(厨房组合菜谱)信息
	 * @param kitchenCookbookMixList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMixBatch(List<KitchenCookbookMix> kitchenCookbookMixList){
		return kitchenCookbookMixBaseDao.updateKitchenCookbookMixBatch(kitchenCookbookMixList);
	}
	/**
	 * 根据序列号删除(厨房组合菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixLogic(java.math.BigInteger id){
		return kitchenCookbookMixBaseDao.deleteKitchenCookbookMixLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(厨房组合菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookMixBaseDao.deleteKitchenCookbookMixLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(厨房组合菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMix(java.math.BigInteger id){
//		return kitchenCookbookMixBaseDao.deleteKitchenCookbookMix(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房组合菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMixBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookMixBaseDao.deleteKitchenCookbookMixBatch(idList);
//	}
	
}
