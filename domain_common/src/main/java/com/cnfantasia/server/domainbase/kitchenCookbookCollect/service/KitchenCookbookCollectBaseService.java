package com.cnfantasia.server.domainbase.kitchenCookbookCollect.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookCollect.dao.IKitchenCookbookCollectBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookCollect.entity.KitchenCookbookCollect;

/**
 * 描述:(菜谱收藏) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookCollectBaseService implements IKitchenCookbookCollectBaseService{
	
	private IKitchenCookbookCollectBaseDao kitchenCookbookCollectBaseDao;
	public void setKitchenCookbookCollectBaseDao(IKitchenCookbookCollectBaseDao kitchenCookbookCollectBaseDao) {
		this.kitchenCookbookCollectBaseDao = kitchenCookbookCollectBaseDao;
	}
	/**
	 * 根据条件查询(菜谱收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByCondition(Map<String,Object> paramMap){
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(菜谱收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(菜谱收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(菜谱收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(菜谱收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookCollect getKitchenCookbookCollectBySeqId(java.math.BigInteger id){
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCollectCount(Map<String,Object> paramMap){
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(菜谱收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCollectCountDim(Map<String,Object> paramMap){
		return kitchenCookbookCollectBaseDao.selectKitchenCookbookCollectCount(paramMap,true);
	}
	/**
	 * 往(菜谱收藏)新增一条记录
	 * @param kitchenCookbookCollect
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect){
		return kitchenCookbookCollectBaseDao.insertKitchenCookbookCollect(kitchenCookbookCollect);
	}
	/**
	 * 批量新增(菜谱收藏)
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList){
		return kitchenCookbookCollectBaseDao.insertKitchenCookbookCollectBatch(kitchenCookbookCollectList);
	}
	/**
	 * 更新(菜谱收藏)信息
	 * @param kitchenCookbookCollect
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect){
		return kitchenCookbookCollectBaseDao.updateKitchenCookbookCollect(kitchenCookbookCollect);
	}
	/**
	 * 批量更新(菜谱收藏)信息
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList){
		return kitchenCookbookCollectBaseDao.updateKitchenCookbookCollectBatch(kitchenCookbookCollectList);
	}
	/**
	 * 根据序列号删除(菜谱收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectLogic(java.math.BigInteger id){
		return kitchenCookbookCollectBaseDao.deleteKitchenCookbookCollectLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(菜谱收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookCollectBaseDao.deleteKitchenCookbookCollectLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollect(java.math.BigInteger id){
//		return kitchenCookbookCollectBaseDao.deleteKitchenCookbookCollect(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookCollectBaseDao.deleteKitchenCookbookCollectBatch(idList);
//	}
	
}
