package com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.dao.IEbuySupplyMerchantLicenceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.entity.EbuySupplyMerchantLicence;

/**
 * 描述:(供应商营业执照图片) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantLicenceBaseService implements IEbuySupplyMerchantLicenceBaseService{
	
	private IEbuySupplyMerchantLicenceBaseDao ebuySupplyMerchantLicenceBaseDao;
	public void setEbuySupplyMerchantLicenceBaseDao(IEbuySupplyMerchantLicenceBaseDao ebuySupplyMerchantLicenceBaseDao) {
		this.ebuySupplyMerchantLicenceBaseDao = ebuySupplyMerchantLicenceBaseDao;
	}
	/**
	 * 根据条件查询(供应商营业执照图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商营业执照图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商营业执照图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商营业执照图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> getEbuySupplyMerchantLicenceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商营业执照图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantLicence getEbuySupplyMerchantLicenceBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantLicenceCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantLicenceCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantLicenceBaseDao.selectEbuySupplyMerchantLicenceCount(paramMap,true);
	}
	/**
	 * 往(供应商营业执照图片)新增一条记录
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence){
		return ebuySupplyMerchantLicenceBaseDao.insertEbuySupplyMerchantLicence(ebuySupplyMerchantLicence);
	}
	/**
	 * 批量新增(供应商营业执照图片)
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList){
		return ebuySupplyMerchantLicenceBaseDao.insertEbuySupplyMerchantLicenceBatch(ebuySupplyMerchantLicenceList);
	}
	/**
	 * 更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence){
		return ebuySupplyMerchantLicenceBaseDao.updateEbuySupplyMerchantLicence(ebuySupplyMerchantLicence);
	}
	/**
	 * 批量更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList){
		return ebuySupplyMerchantLicenceBaseDao.updateEbuySupplyMerchantLicenceBatch(ebuySupplyMerchantLicenceList);
	}
	/**
	 * 根据序列号删除(供应商营业执照图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLicenceLogic(java.math.BigInteger id){
		return ebuySupplyMerchantLicenceBaseDao.deleteEbuySupplyMerchantLicenceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商营业执照图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLicenceLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantLicenceBaseDao.deleteEbuySupplyMerchantLicenceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商营业执照图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantLicence(java.math.BigInteger id){
//		return ebuySupplyMerchantLicenceBaseDao.deleteEbuySupplyMerchantLicence(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商营业执照图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantLicenceBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantLicenceBaseDao.deleteEbuySupplyMerchantLicenceBatch(idList);
//	}
	
}
