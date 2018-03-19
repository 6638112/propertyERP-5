package com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.dao.IKitchenCookbookTypeHasTKitchenCookbookBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity.KitchenCookbookTypeHasTKitchenCookbook;

/**
 * 描述:(菜谱类别与菜谱关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookTypeHasTKitchenCookbookBaseService implements IKitchenCookbookTypeHasTKitchenCookbookBaseService{
	
	private IKitchenCookbookTypeHasTKitchenCookbookBaseDao kitchenCookbookTypeHasTKitchenCookbookBaseDao;
	public void setKitchenCookbookTypeHasTKitchenCookbookBaseDao(IKitchenCookbookTypeHasTKitchenCookbookBaseDao kitchenCookbookTypeHasTKitchenCookbookBaseDao) {
		this.kitchenCookbookTypeHasTKitchenCookbookBaseDao = kitchenCookbookTypeHasTKitchenCookbookBaseDao;
	}
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(菜谱类别与菜谱关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeHasTKitchenCookbook getKitchenCookbookTypeHasTKitchenCookbookBySeqId(java.math.BigInteger id){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeHasTKitchenCookbookCount(Map<String,Object> paramMap){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeHasTKitchenCookbookCountDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.selectKitchenCookbookTypeHasTKitchenCookbookCount(paramMap,true);
	}
	/**
	 * 往(菜谱类别与菜谱关系表)新增一条记录
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.insertKitchenCookbookTypeHasTKitchenCookbook(kitchenCookbookTypeHasTKitchenCookbook);
	}
	/**
	 * 批量新增(菜谱类别与菜谱关系表)
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.insertKitchenCookbookTypeHasTKitchenCookbookBatch(kitchenCookbookTypeHasTKitchenCookbookList);
	}
	/**
	 * 更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.updateKitchenCookbookTypeHasTKitchenCookbook(kitchenCookbookTypeHasTKitchenCookbook);
	}
	/**
	 * 批量更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.updateKitchenCookbookTypeHasTKitchenCookbookBatch(kitchenCookbookTypeHasTKitchenCookbookList);
	}
	/**
	 * 根据序列号删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogic(java.math.BigInteger id){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.deleteKitchenCookbookTypeHasTKitchenCookbookLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.deleteKitchenCookbookTypeHasTKitchenCookbookLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱类别与菜谱关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeHasTKitchenCookbook(java.math.BigInteger id){
//		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.deleteKitchenCookbookTypeHasTKitchenCookbook(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别与菜谱关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeHasTKitchenCookbookBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookTypeHasTKitchenCookbookBaseDao.deleteKitchenCookbookTypeHasTKitchenCookbookBatch(idList);
//	}
	
}
