package com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.dao.IKitchenCookbookTypeCollectBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.entity.KitchenCookbookTypeCollect;

/**
 * 描述:(菜谱类别收藏) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookTypeCollectBaseService implements IKitchenCookbookTypeCollectBaseService{
	
	private IKitchenCookbookTypeCollectBaseDao kitchenCookbookTypeCollectBaseDao;
	public void setKitchenCookbookTypeCollectBaseDao(IKitchenCookbookTypeCollectBaseDao kitchenCookbookTypeCollectBaseDao) {
		this.kitchenCookbookTypeCollectBaseDao = kitchenCookbookTypeCollectBaseDao;
	}
	/**
	 * 根据条件查询(菜谱类别收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap){
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(菜谱类别收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(菜谱类别收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(菜谱类别收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(菜谱类别收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeCollect getKitchenCookbookTypeCollectBySeqId(java.math.BigInteger id){
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeCollectCount(Map<String,Object> paramMap){
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeCollectCountDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectCount(paramMap,true);
	}
	/**
	 * 往(菜谱类别收藏)新增一条记录
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect){
		return kitchenCookbookTypeCollectBaseDao.insertKitchenCookbookTypeCollect(kitchenCookbookTypeCollect);
	}
	/**
	 * 批量新增(菜谱类别收藏)
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList){
		return kitchenCookbookTypeCollectBaseDao.insertKitchenCookbookTypeCollectBatch(kitchenCookbookTypeCollectList);
	}
	/**
	 * 更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect){
		return kitchenCookbookTypeCollectBaseDao.updateKitchenCookbookTypeCollect(kitchenCookbookTypeCollect);
	}
	/**
	 * 批量更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList){
		return kitchenCookbookTypeCollectBaseDao.updateKitchenCookbookTypeCollectBatch(kitchenCookbookTypeCollectList);
	}
	/**
	 * 根据序列号删除(菜谱类别收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeCollectLogic(java.math.BigInteger id){
		return kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollectLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(菜谱类别收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeCollectLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollectLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱类别收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeCollect(java.math.BigInteger id){
//		return kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollect(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeCollectBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollectBatch(idList);
//	}
	
}
