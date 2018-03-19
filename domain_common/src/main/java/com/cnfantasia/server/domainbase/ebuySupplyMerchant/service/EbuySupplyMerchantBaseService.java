package com.cnfantasia.server.domainbase.ebuySupplyMerchant.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchant.dao.IEbuySupplyMerchantBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 描述:(供应商) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantBaseService implements IEbuySupplyMerchantBaseService{
	
	private IEbuySupplyMerchantBaseDao ebuySupplyMerchantBaseDao;
	public void setEbuySupplyMerchantBaseDao(IEbuySupplyMerchantBaseDao ebuySupplyMerchantBaseDao) {
		this.ebuySupplyMerchantBaseDao = ebuySupplyMerchantBaseDao;
	}
	/**
	 * 根据条件查询(供应商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> getEbuySupplyMerchantByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchant getEbuySupplyMerchantBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantCount(paramMap,true);
	}
	/**
	 * 往(供应商)新增一条记录
	 * @param ebuySupplyMerchant
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant){
		return ebuySupplyMerchantBaseDao.insertEbuySupplyMerchant(ebuySupplyMerchant);
	}
	/**
	 * 批量新增(供应商)
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList){
		return ebuySupplyMerchantBaseDao.insertEbuySupplyMerchantBatch(ebuySupplyMerchantList);
	}
	/**
	 * 更新(供应商)信息
	 * @param ebuySupplyMerchant
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant){
		return ebuySupplyMerchantBaseDao.updateEbuySupplyMerchant(ebuySupplyMerchant);
	}
	/**
	 * 批量更新(供应商)信息
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList){
		return ebuySupplyMerchantBaseDao.updateEbuySupplyMerchantBatch(ebuySupplyMerchantList);
	}
	/**
	 * 根据序列号删除(供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLogic(java.math.BigInteger id){
		return ebuySupplyMerchantBaseDao.deleteEbuySupplyMerchantLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantBaseDao.deleteEbuySupplyMerchantLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchant(java.math.BigInteger id){
//		return ebuySupplyMerchantBaseDao.deleteEbuySupplyMerchant(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantBaseDao.deleteEbuySupplyMerchantBatch(idList);
//	}
	
}
