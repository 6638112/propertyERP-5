package com.cnfantasia.server.domainbase.kitchenCookbook.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbook.dao.IKitchenCookbookBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * 描述:(厨房菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookBaseService implements IKitchenCookbookBaseService{
	
	private IKitchenCookbookBaseDao kitchenCookbookBaseDao;
	public void setKitchenCookbookBaseDao(IKitchenCookbookBaseDao kitchenCookbookBaseDao) {
		this.kitchenCookbookBaseDao = kitchenCookbookBaseDao;
	}
	/**
	 * 根据条件查询(厨房菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbook> getKitchenCookbookByCondition(Map<String,Object> paramMap){
		return kitchenCookbookBaseDao.selectKitchenCookbookByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(厨房菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbook> getKitchenCookbookByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookBaseDao.selectKitchenCookbookByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(厨房菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbook> getKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookBaseDao.selectKitchenCookbookByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(厨房菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbook> getKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookBaseDao.selectKitchenCookbookByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(厨房菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbook getKitchenCookbookBySeqId(java.math.BigInteger id){
		return kitchenCookbookBaseDao.selectKitchenCookbookBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCount(Map<String,Object> paramMap){
		return kitchenCookbookBaseDao.selectKitchenCookbookCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookCountDim(Map<String,Object> paramMap){
		return kitchenCookbookBaseDao.selectKitchenCookbookCount(paramMap,true);
	}
	/**
	 * 往(厨房菜谱)新增一条记录
	 * @param kitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbook(KitchenCookbook kitchenCookbook){
		return kitchenCookbookBaseDao.insertKitchenCookbook(kitchenCookbook);
	}
	/**
	 * 批量新增(厨房菜谱)
	 * @param kitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList){
		return kitchenCookbookBaseDao.insertKitchenCookbookBatch(kitchenCookbookList);
	}
	/**
	 * 更新(厨房菜谱)信息
	 * @param kitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbook(KitchenCookbook kitchenCookbook){
		return kitchenCookbookBaseDao.updateKitchenCookbook(kitchenCookbook);
	}
	/**
	 * 批量更新(厨房菜谱)信息
	 * @param kitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList){
		return kitchenCookbookBaseDao.updateKitchenCookbookBatch(kitchenCookbookList);
	}
	/**
	 * 根据序列号删除(厨房菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookLogic(java.math.BigInteger id){
		return kitchenCookbookBaseDao.deleteKitchenCookbookLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(厨房菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookBaseDao.deleteKitchenCookbookLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbook(java.math.BigInteger id){
//		return kitchenCookbookBaseDao.deleteKitchenCookbook(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookBaseDao.deleteKitchenCookbookBatch(idList);
//	}
	
}
