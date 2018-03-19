package com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.dao.IOmsUserHasTEbuySupplyMerchantBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.entity.OmsUserHasTEbuySupplyMerchant;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserHasTEbuySupplyMerchantBaseService implements IOmsUserHasTEbuySupplyMerchantBaseService{
	
	private IOmsUserHasTEbuySupplyMerchantBaseDao omsUserHasTEbuySupplyMerchantBaseDao;
	public void setOmsUserHasTEbuySupplyMerchantBaseDao(IOmsUserHasTEbuySupplyMerchantBaseDao omsUserHasTEbuySupplyMerchantBaseDao) {
		this.omsUserHasTEbuySupplyMerchantBaseDao = omsUserHasTEbuySupplyMerchantBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByCondition(Map<String,Object> paramMap){
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap){
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserHasTEbuySupplyMerchant getOmsUserHasTEbuySupplyMerchantBySeqId(java.math.BigInteger id){
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasTEbuySupplyMerchantCount(Map<String,Object> paramMap){
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasTEbuySupplyMerchantCountDim(Map<String,Object> paramMap){
		return omsUserHasTEbuySupplyMerchantBaseDao.selectOmsUserHasTEbuySupplyMerchantCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	@Override
	public int insertOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant){
		return omsUserHasTEbuySupplyMerchantBaseDao.insertOmsUserHasTEbuySupplyMerchant(omsUserHasTEbuySupplyMerchant);
	}
	/**
	 * 批量新增()
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	@Override
	public int insertOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList){
		return omsUserHasTEbuySupplyMerchantBaseDao.insertOmsUserHasTEbuySupplyMerchantBatch(omsUserHasTEbuySupplyMerchantList);
	}
	/**
	 * 更新()信息
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	@Override
	public int updateOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant){
		return omsUserHasTEbuySupplyMerchantBaseDao.updateOmsUserHasTEbuySupplyMerchant(omsUserHasTEbuySupplyMerchant);
	}
	/**
	 * 批量更新()信息
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	@Override
	public int updateOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList){
		return omsUserHasTEbuySupplyMerchantBaseDao.updateOmsUserHasTEbuySupplyMerchantBatch(omsUserHasTEbuySupplyMerchantList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTEbuySupplyMerchantLogic(java.math.BigInteger id){
		return omsUserHasTEbuySupplyMerchantBaseDao.deleteOmsUserHasTEbuySupplyMerchantLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList){
		return omsUserHasTEbuySupplyMerchantBaseDao.deleteOmsUserHasTEbuySupplyMerchantLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTEbuySupplyMerchant(java.math.BigInteger id){
//		return omsUserHasTEbuySupplyMerchantBaseDao.deleteOmsUserHasTEbuySupplyMerchant(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTEbuySupplyMerchantBatch(List<java.math.BigInteger> idList){
//		return omsUserHasTEbuySupplyMerchantBaseDao.deleteOmsUserHasTEbuySupplyMerchantBatch(idList);
//	}
	
}
