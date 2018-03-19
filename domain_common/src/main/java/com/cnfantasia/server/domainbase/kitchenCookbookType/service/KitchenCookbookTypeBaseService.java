package com.cnfantasia.server.domainbase.kitchenCookbookType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenCookbookType.dao.IKitchenCookbookTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;

/**
 * 描述:(厨房菜谱分类) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenCookbookTypeBaseService implements IKitchenCookbookTypeBaseService{
	
	private IKitchenCookbookTypeBaseDao kitchenCookbookTypeBaseDao;
	public void setKitchenCookbookTypeBaseDao(IKitchenCookbookTypeBaseDao kitchenCookbookTypeBaseDao) {
		this.kitchenCookbookTypeBaseDao = kitchenCookbookTypeBaseDao;
	}
	/**
	 * 根据条件查询(厨房菜谱分类)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> getKitchenCookbookTypeByCondition(Map<String,Object> paramMap){
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(厨房菜谱分类)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> getKitchenCookbookTypeByConditionDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(厨房菜谱分类)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> getKitchenCookbookTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(厨房菜谱分类)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> getKitchenCookbookTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(厨房菜谱分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookType getKitchenCookbookTypeBySeqId(java.math.BigInteger id){
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeCount(Map<String,Object> paramMap){
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenCookbookTypeCountDim(Map<String,Object> paramMap){
		return kitchenCookbookTypeBaseDao.selectKitchenCookbookTypeCount(paramMap,true);
	}
	/**
	 * 往(厨房菜谱分类)新增一条记录
	 * @param kitchenCookbookType
	 * @return
	 */
	@Override
	public int insertKitchenCookbookType(KitchenCookbookType kitchenCookbookType){
		return kitchenCookbookTypeBaseDao.insertKitchenCookbookType(kitchenCookbookType);
	}
	/**
	 * 批量新增(厨房菜谱分类)
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList){
		return kitchenCookbookTypeBaseDao.insertKitchenCookbookTypeBatch(kitchenCookbookTypeList);
	}
	/**
	 * 更新(厨房菜谱分类)信息
	 * @param kitchenCookbookType
	 * @return
	 */
	@Override
	public int updateKitchenCookbookType(KitchenCookbookType kitchenCookbookType){
		return kitchenCookbookTypeBaseDao.updateKitchenCookbookType(kitchenCookbookType);
	}
	/**
	 * 批量更新(厨房菜谱分类)信息
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList){
		return kitchenCookbookTypeBaseDao.updateKitchenCookbookTypeBatch(kitchenCookbookTypeList);
	}
	/**
	 * 根据序列号删除(厨房菜谱分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeLogic(java.math.BigInteger id){
		return kitchenCookbookTypeBaseDao.deleteKitchenCookbookTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(厨房菜谱分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeLogicBatch(List<java.math.BigInteger> idList){
		return kitchenCookbookTypeBaseDao.deleteKitchenCookbookTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookType(java.math.BigInteger id){
//		return kitchenCookbookTypeBaseDao.deleteKitchenCookbookType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeBatch(List<java.math.BigInteger> idList){
//		return kitchenCookbookTypeBaseDao.deleteKitchenCookbookTypeBatch(idList);
//	}
	
}
