package com.cnfantasia.server.domainbase.ebuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao.IEbuyDeliveryMethodBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;

/**
 * 描述:(配送方式) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryMethodBaseService implements IEbuyDeliveryMethodBaseService{
	
	private IEbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao;
	public void setEbuyDeliveryMethodBaseDao(IEbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao) {
		this.ebuyDeliveryMethodBaseDao = ebuyDeliveryMethodBaseDao;
	}
	/**
	 * 根据条件查询(配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(配送方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryMethod getEbuyDeliveryMethodBySeqId(java.math.BigInteger id){
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryMethodCount(Map<String,Object> paramMap){
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryMethodCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodCount(paramMap,true);
	}
	/**
	 * 往(配送方式)新增一条记录
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod){
		return ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethod(ebuyDeliveryMethod);
	}
	/**
	 * 批量新增(配送方式)
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList){
		return ebuyDeliveryMethodBaseDao.insertEbuyDeliveryMethodBatch(ebuyDeliveryMethodList);
	}
	/**
	 * 更新(配送方式)信息
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod){
		return ebuyDeliveryMethodBaseDao.updateEbuyDeliveryMethod(ebuyDeliveryMethod);
	}
	/**
	 * 批量更新(配送方式)信息
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList){
		return ebuyDeliveryMethodBaseDao.updateEbuyDeliveryMethodBatch(ebuyDeliveryMethodList);
	}
	/**
	 * 根据序列号删除(配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryMethodLogic(java.math.BigInteger id){
		return ebuyDeliveryMethodBaseDao.deleteEbuyDeliveryMethodLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryMethodBaseDao.deleteEbuyDeliveryMethodLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryMethod(java.math.BigInteger id){
//		return ebuyDeliveryMethodBaseDao.deleteEbuyDeliveryMethod(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryMethodBaseDao.deleteEbuyDeliveryMethodBatch(idList);
//	}
	
}
