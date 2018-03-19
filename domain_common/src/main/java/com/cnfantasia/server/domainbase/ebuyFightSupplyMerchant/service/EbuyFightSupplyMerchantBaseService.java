package com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.dao.IEbuyFightSupplyMerchantBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

/**
 * 描述:(自提点表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyFightSupplyMerchantBaseService implements IEbuyFightSupplyMerchantBaseService{
	
	private IEbuyFightSupplyMerchantBaseDao ebuyFightSupplyMerchantBaseDao;
	public void setEbuyFightSupplyMerchantBaseDao(IEbuyFightSupplyMerchantBaseDao ebuyFightSupplyMerchantBaseDao) {
		this.ebuyFightSupplyMerchantBaseDao = ebuyFightSupplyMerchantBaseDao;
	}
	/**
	 * 根据条件查询(自提点表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> getEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap){
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(自提点表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> getEbuyFightSupplyMerchantByConditionDim(Map<String,Object> paramMap){
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(自提点表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> getEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(自提点表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> getEbuyFightSupplyMerchantByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(自提点表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightSupplyMerchant getEbuyFightSupplyMerchantBySeqId(java.math.BigInteger id){
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(自提点表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightSupplyMerchantCount(Map<String,Object> paramMap){
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(自提点表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightSupplyMerchantCountDim(Map<String,Object> paramMap){
		return ebuyFightSupplyMerchantBaseDao.selectEbuyFightSupplyMerchantCount(paramMap,true);
	}
	/**
	 * 往(自提点表)新增一条记录
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	@Override
	public int insertEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant){
		return ebuyFightSupplyMerchantBaseDao.insertEbuyFightSupplyMerchant(ebuyFightSupplyMerchant);
	}
	/**
	 * 批量新增(自提点表)
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	@Override
	public int insertEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList){
		return ebuyFightSupplyMerchantBaseDao.insertEbuyFightSupplyMerchantBatch(ebuyFightSupplyMerchantList);
	}
	/**
	 * 更新(自提点表)信息
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	@Override
	public int updateEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant){
		return ebuyFightSupplyMerchantBaseDao.updateEbuyFightSupplyMerchant(ebuyFightSupplyMerchant);
	}
	/**
	 * 批量更新(自提点表)信息
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	@Override
	public int updateEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList){
		return ebuyFightSupplyMerchantBaseDao.updateEbuyFightSupplyMerchantBatch(ebuyFightSupplyMerchantList);
	}
	/**
	 * 根据序列号删除(自提点表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightSupplyMerchantLogic(java.math.BigInteger id){
		return ebuyFightSupplyMerchantBaseDao.deleteEbuyFightSupplyMerchantLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(自提点表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightSupplyMerchantLogicBatch(List<java.math.BigInteger> idList){
		return ebuyFightSupplyMerchantBaseDao.deleteEbuyFightSupplyMerchantLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(自提点表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightSupplyMerchant(java.math.BigInteger id){
//		return ebuyFightSupplyMerchantBaseDao.deleteEbuyFightSupplyMerchant(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自提点表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightSupplyMerchantBatch(List<java.math.BigInteger> idList){
//		return ebuyFightSupplyMerchantBaseDao.deleteEbuyFightSupplyMerchantBatch(idList);
//	}
	
}
