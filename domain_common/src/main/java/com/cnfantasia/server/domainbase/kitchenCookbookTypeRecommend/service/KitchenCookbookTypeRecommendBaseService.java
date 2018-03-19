package com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.dao.IKitchenCookbookTypeRecommendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.entity.KitchenCookbookTypeRecommend;

/**
 * 描述:(厨房菜品类别推荐) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookTypeRecommendBaseService implements IKitchenCookbookTypeRecommendBaseService{
	
	private IKitchenCookbookTypeRecommendBaseDao kitchenCookbookTypeRecommendBaseDao;
	public void setKitchenCookbookTypeRecommendBaseDao(IKitchenCookbookTypeRecommendBaseDao kitchenCookbookTypeRecommendBaseDao) {
		this.kitchenCookbookTypeRecommendBaseDao = kitchenCookbookTypeRecommendBaseDao;
	}
	/**
	 * 根据条件查询(厨房菜品类别推荐)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> getKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap){
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(厨房菜品类别推荐)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> getKitchenCookbookTypeRecommendByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(厨房菜品类别推荐)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> getKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(厨房菜品类别推荐)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> getKitchenCookbookTypeRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(厨房菜品类别推荐)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeRecommend getKitchenCookbookTypeRecommendBySeqId(java.math.BigInteger id){
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜品类别推荐)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeRecommendCount(Map<String,Object> paramMap){
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜品类别推荐)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeRecommendCountDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeRecommendBaseDao.selectKitchenCookbookTypeRecommendCount(paramMap,true);
	}
	/**
	 * 往(厨房菜品类别推荐)新增一条记录
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend){
		return kitchenCookbookTypeRecommendBaseDao.insertKitchenCookbookTypeRecommend(kitchenCookbookTypeRecommend);
	}
	/**
	 * 批量新增(厨房菜品类别推荐)
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList){
		return kitchenCookbookTypeRecommendBaseDao.insertKitchenCookbookTypeRecommendBatch(kitchenCookbookTypeRecommendList);
	}
	/**
	 * 更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend){
		return kitchenCookbookTypeRecommendBaseDao.updateKitchenCookbookTypeRecommend(kitchenCookbookTypeRecommend);
	}
	/**
	 * 批量更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList){
		return kitchenCookbookTypeRecommendBaseDao.updateKitchenCookbookTypeRecommendBatch(kitchenCookbookTypeRecommendList);
	}
	/**
	 * 根据序列号删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeRecommendLogic(java.math.BigInteger id){
		return kitchenCookbookTypeRecommendBaseDao.deleteKitchenCookbookTypeRecommendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeRecommendLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookTypeRecommendBaseDao.deleteKitchenCookbookTypeRecommendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜品类别推荐)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeRecommend(java.math.BigInteger id){
//		return kitchenCookbookTypeRecommendBaseDao.deleteKitchenCookbookTypeRecommend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜品类别推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeRecommendBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookTypeRecommendBaseDao.deleteKitchenCookbookTypeRecommendBatch(idList);
//	}
	
}
