package com.cnfantasia.server.domainbase.kitchenCookbookStep.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookStep.dao.IKitchenCookbookStepBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;

/**
 * 描述:(亨饪步骤信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookStepBaseService implements IKitchenCookbookStepBaseService{
	
	private IKitchenCookbookStepBaseDao kitchenCookbookStepBaseDao;
	public void setKitchenCookbookStepBaseDao(IKitchenCookbookStepBaseDao kitchenCookbookStepBaseDao) {
		this.kitchenCookbookStepBaseDao = kitchenCookbookStepBaseDao;
	}
	/**
	 * 根据条件查询(亨饪步骤信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> getKitchenCookbookStepByCondition(Map<String,Object> paramMap){
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(亨饪步骤信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> getKitchenCookbookStepByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(亨饪步骤信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> getKitchenCookbookStepByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(亨饪步骤信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> getKitchenCookbookStepByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(亨饪步骤信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookStep getKitchenCookbookStepBySeqId(java.math.BigInteger id){
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(亨饪步骤信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookStepCount(Map<String,Object> paramMap){
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(亨饪步骤信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookStepCountDim(Map<String,Object> paramMap){
		return kitchenCookbookStepBaseDao.selectKitchenCookbookStepCount(paramMap,true);
	}
	/**
	 * 往(亨饪步骤信息)新增一条记录
	 * @param kitchenCookbookStep
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep){
		return kitchenCookbookStepBaseDao.insertKitchenCookbookStep(kitchenCookbookStep);
	}
	/**
	 * 批量新增(亨饪步骤信息)
	 * @param kitchenCookbookStepList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList){
		return kitchenCookbookStepBaseDao.insertKitchenCookbookStepBatch(kitchenCookbookStepList);
	}
	/**
	 * 更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStep
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep){
		return kitchenCookbookStepBaseDao.updateKitchenCookbookStep(kitchenCookbookStep);
	}
	/**
	 * 批量更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStepList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList){
		return kitchenCookbookStepBaseDao.updateKitchenCookbookStepBatch(kitchenCookbookStepList);
	}
	/**
	 * 根据序列号删除(亨饪步骤信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepLogic(java.math.BigInteger id){
		return kitchenCookbookStepBaseDao.deleteKitchenCookbookStepLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(亨饪步骤信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookStepBaseDao.deleteKitchenCookbookStepLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(亨饪步骤信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStep(java.math.BigInteger id){
//		return kitchenCookbookStepBaseDao.deleteKitchenCookbookStep(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(亨饪步骤信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookStepBaseDao.deleteKitchenCookbookStepBatch(idList);
//	}
	
}
