package com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.dao.IKitchenCookbookCollectIslikeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.entity.KitchenCookbookCollectIslike;

/**
 * 描述:(家庭成员是否喜欢吃对应菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookCollectIslikeBaseService implements IKitchenCookbookCollectIslikeBaseService{
	
	private IKitchenCookbookCollectIslikeBaseDao kitchenCookbookCollectIslikeBaseDao;
	public void setKitchenCookbookCollectIslikeBaseDao(IKitchenCookbookCollectIslikeBaseDao kitchenCookbookCollectIslikeBaseDao) {
		this.kitchenCookbookCollectIslikeBaseDao = kitchenCookbookCollectIslikeBaseDao;
	}
	/**
	 * 根据条件查询(家庭成员是否喜欢吃对应菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap){
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭成员是否喜欢吃对应菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭成员是否喜欢吃对应菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭成员是否喜欢吃对应菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭成员是否喜欢吃对应菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookCollectIslike getKitchenCookbookCollectIslikeBySeqId(java.math.BigInteger id){
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭成员是否喜欢吃对应菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCollectIslikeCount(Map<String,Object> paramMap){
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭成员是否喜欢吃对应菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCollectIslikeCountDim(Map<String,Object> paramMap){
		return kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeCount(paramMap,true);
	}
	/**
	 * 往(家庭成员是否喜欢吃对应菜谱)新增一条记录
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike){
		return kitchenCookbookCollectIslikeBaseDao.insertKitchenCookbookCollectIslike(kitchenCookbookCollectIslike);
	}
	/**
	 * 批量新增(家庭成员是否喜欢吃对应菜谱)
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList){
		return kitchenCookbookCollectIslikeBaseDao.insertKitchenCookbookCollectIslikeBatch(kitchenCookbookCollectIslikeList);
	}
	/**
	 * 更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike){
		return kitchenCookbookCollectIslikeBaseDao.updateKitchenCookbookCollectIslike(kitchenCookbookCollectIslike);
	}
	/**
	 * 批量更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList){
		return kitchenCookbookCollectIslikeBaseDao.updateKitchenCookbookCollectIslikeBatch(kitchenCookbookCollectIslikeList);
	}
	/**
	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectIslikeLogic(java.math.BigInteger id){
		return kitchenCookbookCollectIslikeBaseDao.deleteKitchenCookbookCollectIslikeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectIslikeLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookCollectIslikeBaseDao.deleteKitchenCookbookCollectIslikeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectIslike(java.math.BigInteger id){
//		return kitchenCookbookCollectIslikeBaseDao.deleteKitchenCookbookCollectIslike(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectIslikeBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookCollectIslikeBaseDao.deleteKitchenCookbookCollectIslikeBatch(idList);
//	}
	
}
