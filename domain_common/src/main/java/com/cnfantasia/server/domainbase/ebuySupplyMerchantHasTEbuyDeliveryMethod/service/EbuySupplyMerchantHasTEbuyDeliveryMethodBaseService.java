package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.dao.IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;

/**
 * 描述:(供应商支持的配送方式) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantHasTEbuyDeliveryMethodBaseService implements IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseService{
	
	private IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao;
	public void setEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao(IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao) {
		this.ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao = ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao;
	}
	/**
	 * 根据条件查询(供应商支持的配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商支持的配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> getEbuySupplyMerchantHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商支持的配送方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasTEbuyDeliveryMethod getEbuySupplyMerchantHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasTEbuyDeliveryMethodCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.selectEbuySupplyMerchantHasTEbuyDeliveryMethodCount(paramMap,true);
	}
	/**
	 * 往(供应商支持的配送方式)新增一条记录
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量新增(供应商支持的配送方式)
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.insertEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	}
	/**
	 * 更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.updateEbuySupplyMerchantHasTEbuyDeliveryMethod(ebuySupplyMerchantHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.updateEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	}
	/**
	 * 根据序列号删除(供应商支持的配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogic(java.math.BigInteger id){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商支持的配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商支持的配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethod(java.math.BigInteger id){
//		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.deleteEbuySupplyMerchantHasTEbuyDeliveryMethod(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商支持的配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantHasTEbuyDeliveryMethodBaseDao.deleteEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(idList);
//	}
	
}
