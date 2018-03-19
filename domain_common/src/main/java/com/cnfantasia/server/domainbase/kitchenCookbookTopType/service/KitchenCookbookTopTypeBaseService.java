package com.cnfantasia.server.domainbase.kitchenCookbookTopType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookTopType.dao.IKitchenCookbookTopTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;

/**
 * 描述:(顶级类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookTopTypeBaseService implements IKitchenCookbookTopTypeBaseService{
	
	private IKitchenCookbookTopTypeBaseDao kitchenCookbookTopTypeBaseDao;
	public void setKitchenCookbookTopTypeBaseDao(IKitchenCookbookTopTypeBaseDao kitchenCookbookTopTypeBaseDao) {
		this.kitchenCookbookTopTypeBaseDao = kitchenCookbookTopTypeBaseDao;
	}
	/**
	 * 根据条件查询(顶级类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap){
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(顶级类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(顶级类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(顶级类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(顶级类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTopType getKitchenCookbookTopTypeBySeqId(java.math.BigInteger id){
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTopTypeCount(Map<String,Object> paramMap){
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTopTypeCountDim(Map<String,Object> paramMap){
		return kitchenCookbookTopTypeBaseDao.selectKitchenCookbookTopTypeCount(paramMap,true);
	}
	/**
	 * 往(顶级类别)新增一条记录
	 * @param kitchenCookbookTopType
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType){
		return kitchenCookbookTopTypeBaseDao.insertKitchenCookbookTopType(kitchenCookbookTopType);
	}
	/**
	 * 批量新增(顶级类别)
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList){
		return kitchenCookbookTopTypeBaseDao.insertKitchenCookbookTopTypeBatch(kitchenCookbookTopTypeList);
	}
	/**
	 * 更新(顶级类别)信息
	 * @param kitchenCookbookTopType
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType){
		return kitchenCookbookTopTypeBaseDao.updateKitchenCookbookTopType(kitchenCookbookTopType);
	}
	/**
	 * 批量更新(顶级类别)信息
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList){
		return kitchenCookbookTopTypeBaseDao.updateKitchenCookbookTopTypeBatch(kitchenCookbookTopTypeList);
	}
	/**
	 * 根据序列号删除(顶级类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTopTypeLogic(java.math.BigInteger id){
		return kitchenCookbookTopTypeBaseDao.deleteKitchenCookbookTopTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(顶级类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTopTypeLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookTopTypeBaseDao.deleteKitchenCookbookTopTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(顶级类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTopType(java.math.BigInteger id){
//		return kitchenCookbookTopTypeBaseDao.deleteKitchenCookbookTopType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(顶级类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTopTypeBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookTopTypeBaseDao.deleteKitchenCookbookTopTypeBatch(idList);
//	}
	
}
