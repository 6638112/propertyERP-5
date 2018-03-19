package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.dao.IEbuyProductHasTEbuyDeliveryMethodBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.entity.EbuyProductHasTEbuyDeliveryMethod;

/**
 * 描述:(商品支持的配送方式) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductHasTEbuyDeliveryMethodBaseService implements IEbuyProductHasTEbuyDeliveryMethodBaseService{
	
	private IEbuyProductHasTEbuyDeliveryMethodBaseDao ebuyProductHasTEbuyDeliveryMethodBaseDao;
	public void setEbuyProductHasTEbuyDeliveryMethodBaseDao(IEbuyProductHasTEbuyDeliveryMethodBaseDao ebuyProductHasTEbuyDeliveryMethodBaseDao) {
		this.ebuyProductHasTEbuyDeliveryMethodBaseDao = ebuyProductHasTEbuyDeliveryMethodBaseDao;
	}
	/**
	 * 根据条件查询(商品支持的配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品支持的配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品支持的配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品支持的配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品支持的配送方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductHasTEbuyDeliveryMethod getEbuyProductHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品支持的配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品支持的配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductHasTEbuyDeliveryMethodCountDim(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.selectEbuyProductHasTEbuyDeliveryMethodCount(paramMap,true);
	}
	/**
	 * 往(商品支持的配送方式)新增一条记录
	 * @param ebuyProductHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyDeliveryMethod(EbuyProductHasTEbuyDeliveryMethod ebuyProductHasTEbuyDeliveryMethod){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.insertEbuyProductHasTEbuyDeliveryMethod(ebuyProductHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量新增(商品支持的配送方式)
	 * @param ebuyProductHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyDeliveryMethodBatch(List<EbuyProductHasTEbuyDeliveryMethod> ebuyProductHasTEbuyDeliveryMethodList){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.insertEbuyProductHasTEbuyDeliveryMethodBatch(ebuyProductHasTEbuyDeliveryMethodList);
	}
	/**
	 * 更新(商品支持的配送方式)信息
	 * @param ebuyProductHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyDeliveryMethod(EbuyProductHasTEbuyDeliveryMethod ebuyProductHasTEbuyDeliveryMethod){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.updateEbuyProductHasTEbuyDeliveryMethod(ebuyProductHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量更新(商品支持的配送方式)信息
	 * @param ebuyProductHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyDeliveryMethodBatch(List<EbuyProductHasTEbuyDeliveryMethod> ebuyProductHasTEbuyDeliveryMethodList){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.updateEbuyProductHasTEbuyDeliveryMethodBatch(ebuyProductHasTEbuyDeliveryMethodList);
	}
	/**
	 * 根据序列号删除(商品支持的配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyDeliveryMethodLogic(java.math.BigInteger id){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.deleteEbuyProductHasTEbuyDeliveryMethodLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品支持的配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductHasTEbuyDeliveryMethodBaseDao.deleteEbuyProductHasTEbuyDeliveryMethodLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品支持的配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyDeliveryMethod(java.math.BigInteger id){
//		return ebuyProductHasTEbuyDeliveryMethodBaseDao.deleteEbuyProductHasTEbuyDeliveryMethod(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品支持的配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList){
//		return ebuyProductHasTEbuyDeliveryMethodBaseDao.deleteEbuyProductHasTEbuyDeliveryMethodBatch(idList);
//	}
	
}
