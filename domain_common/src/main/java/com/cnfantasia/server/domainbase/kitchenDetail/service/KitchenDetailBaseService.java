package com.cnfantasia.server.domainbase.kitchenDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.kitchenDetail.dao.IKitchenDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * 描述:(厨房菜谱详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class KitchenDetailBaseService implements IKitchenDetailBaseService{
	
	private IKitchenDetailBaseDao kitchenDetailBaseDao;
	public void setKitchenDetailBaseDao(IKitchenDetailBaseDao kitchenDetailBaseDao) {
		this.kitchenDetailBaseDao = kitchenDetailBaseDao;
	}
	/**
	 * 根据条件查询(厨房菜谱详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenDetail> getKitchenDetailByCondition(Map<String,Object> paramMap){
		return kitchenDetailBaseDao.selectKitchenDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(厨房菜谱详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<KitchenDetail> getKitchenDetailByConditionDim(Map<String,Object> paramMap){
		return kitchenDetailBaseDao.selectKitchenDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(厨房菜谱详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenDetail> getKitchenDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenDetailBaseDao.selectKitchenDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(厨房菜谱详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<KitchenDetail> getKitchenDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return kitchenDetailBaseDao.selectKitchenDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(厨房菜谱详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenDetail getKitchenDetailBySeqId(java.math.BigInteger id){
		return kitchenDetailBaseDao.selectKitchenDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenDetailCount(Map<String,Object> paramMap){
		return kitchenDetailBaseDao.selectKitchenDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getKitchenDetailCountDim(Map<String,Object> paramMap){
		return kitchenDetailBaseDao.selectKitchenDetailCount(paramMap,true);
	}
	/**
	 * 往(厨房菜谱详情)新增一条记录
	 * @param kitchenDetail
	 * @return
	 */
	@Override
	public int insertKitchenDetail(KitchenDetail kitchenDetail){
		return kitchenDetailBaseDao.insertKitchenDetail(kitchenDetail);
	}
	/**
	 * 批量新增(厨房菜谱详情)
	 * @param kitchenDetailList
	 * @return
	 */
	@Override
	public int insertKitchenDetailBatch(List<KitchenDetail> kitchenDetailList){
		return kitchenDetailBaseDao.insertKitchenDetailBatch(kitchenDetailList);
	}
	/**
	 * 更新(厨房菜谱详情)信息
	 * @param kitchenDetail
	 * @return
	 */
	@Override
	public int updateKitchenDetail(KitchenDetail kitchenDetail){
		return kitchenDetailBaseDao.updateKitchenDetail(kitchenDetail);
	}
	/**
	 * 批量更新(厨房菜谱详情)信息
	 * @param kitchenDetailList
	 * @return
	 */
	@Override
	public int updateKitchenDetailBatch(List<KitchenDetail> kitchenDetailList){
		return kitchenDetailBaseDao.updateKitchenDetailBatch(kitchenDetailList);
	}
	/**
	 * 根据序列号删除(厨房菜谱详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenDetailLogic(java.math.BigInteger id){
		return kitchenDetailBaseDao.deleteKitchenDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(厨房菜谱详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenDetailLogicBatch(List<java.math.BigInteger> idList){
		return kitchenDetailBaseDao.deleteKitchenDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenDetail(java.math.BigInteger id){
//		return kitchenDetailBaseDao.deleteKitchenDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenDetailBatch(List<java.math.BigInteger> idList){
//		return kitchenDetailBaseDao.deleteKitchenDetailBatch(idList);
//	}
	
}
