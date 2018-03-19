package com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.dao.IKitchenCookbookAddressRelationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.entity.KitchenCookbookAddressRelation;

/**
 * 描述:(菜谱地址关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookAddressRelationBaseService implements IKitchenCookbookAddressRelationBaseService{
	
	private IKitchenCookbookAddressRelationBaseDao kitchenCookbookAddressRelationBaseDao;
	public void setKitchenCookbookAddressRelationBaseDao(IKitchenCookbookAddressRelationBaseDao kitchenCookbookAddressRelationBaseDao) {
		this.kitchenCookbookAddressRelationBaseDao = kitchenCookbookAddressRelationBaseDao;
	}
	/**
	 * 根据条件查询(菜谱地址关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> getKitchenCookbookAddressRelationByCondition(Map<String,Object> paramMap){
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(菜谱地址关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> getKitchenCookbookAddressRelationByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(菜谱地址关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> getKitchenCookbookAddressRelationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(菜谱地址关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> getKitchenCookbookAddressRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(菜谱地址关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookAddressRelation getKitchenCookbookAddressRelationBySeqId(java.math.BigInteger id){
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱地址关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookAddressRelationCount(Map<String,Object> paramMap){
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(菜谱地址关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookAddressRelationCountDim(Map<String,Object> paramMap){
		return kitchenCookbookAddressRelationBaseDao.selectKitchenCookbookAddressRelationCount(paramMap,true);
	}
	/**
	 * 往(菜谱地址关系表)新增一条记录
	 * @param kitchenCookbookAddressRelation
	 * @return
	 */
	@Override
	public int insertKitchenCookbookAddressRelation(KitchenCookbookAddressRelation kitchenCookbookAddressRelation){
		return kitchenCookbookAddressRelationBaseDao.insertKitchenCookbookAddressRelation(kitchenCookbookAddressRelation);
	}
	/**
	 * 批量新增(菜谱地址关系表)
	 * @param kitchenCookbookAddressRelationList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookAddressRelationBatch(List<KitchenCookbookAddressRelation> kitchenCookbookAddressRelationList){
		return kitchenCookbookAddressRelationBaseDao.insertKitchenCookbookAddressRelationBatch(kitchenCookbookAddressRelationList);
	}
	/**
	 * 更新(菜谱地址关系表)信息
	 * @param kitchenCookbookAddressRelation
	 * @return
	 */
	@Override
	public int updateKitchenCookbookAddressRelation(KitchenCookbookAddressRelation kitchenCookbookAddressRelation){
		return kitchenCookbookAddressRelationBaseDao.updateKitchenCookbookAddressRelation(kitchenCookbookAddressRelation);
	}
	/**
	 * 批量更新(菜谱地址关系表)信息
	 * @param kitchenCookbookAddressRelationList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookAddressRelationBatch(List<KitchenCookbookAddressRelation> kitchenCookbookAddressRelationList){
		return kitchenCookbookAddressRelationBaseDao.updateKitchenCookbookAddressRelationBatch(kitchenCookbookAddressRelationList);
	}
	/**
	 * 根据序列号删除(菜谱地址关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookAddressRelationLogic(java.math.BigInteger id){
		return kitchenCookbookAddressRelationBaseDao.deleteKitchenCookbookAddressRelationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(菜谱地址关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookAddressRelationLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookAddressRelationBaseDao.deleteKitchenCookbookAddressRelationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱地址关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookAddressRelation(java.math.BigInteger id){
//		return kitchenCookbookAddressRelationBaseDao.deleteKitchenCookbookAddressRelation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱地址关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookAddressRelationBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookAddressRelationBaseDao.deleteKitchenCookbookAddressRelationBatch(idList);
//	}
	
}
