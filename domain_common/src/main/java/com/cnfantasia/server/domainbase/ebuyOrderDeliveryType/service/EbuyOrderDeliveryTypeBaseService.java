package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.dao.IEbuyOrderDeliveryTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;

/**
 * 描述:(订单的配送设置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderDeliveryTypeBaseService implements IEbuyOrderDeliveryTypeBaseService{
	
	private IEbuyOrderDeliveryTypeBaseDao ebuyOrderDeliveryTypeBaseDao;
	public void setEbuyOrderDeliveryTypeBaseDao(IEbuyOrderDeliveryTypeBaseDao ebuyOrderDeliveryTypeBaseDao) {
		this.ebuyOrderDeliveryTypeBaseDao = ebuyOrderDeliveryTypeBaseDao;
	}
	/**
	 * 根据条件查询(订单的配送设置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap){
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单的配送设置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单的配送设置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单的配送设置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> getEbuyOrderDeliveryTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单的配送设置)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderDeliveryType getEbuyOrderDeliveryTypeBySeqId(java.math.BigInteger id){
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderDeliveryTypeCount(Map<String,Object> paramMap){
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderDeliveryTypeCountDim(Map<String,Object> paramMap){
		return ebuyOrderDeliveryTypeBaseDao.selectEbuyOrderDeliveryTypeCount(paramMap,true);
	}
	/**
	 * 往(订单的配送设置)新增一条记录
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	@Override
	public int insertEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType){
		return ebuyOrderDeliveryTypeBaseDao.insertEbuyOrderDeliveryType(ebuyOrderDeliveryType);
	}
	/**
	 * 批量新增(订单的配送设置)
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	@Override
	public int insertEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList){
		return ebuyOrderDeliveryTypeBaseDao.insertEbuyOrderDeliveryTypeBatch(ebuyOrderDeliveryTypeList);
	}
	/**
	 * 更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	@Override
	public int updateEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType){
		return ebuyOrderDeliveryTypeBaseDao.updateEbuyOrderDeliveryType(ebuyOrderDeliveryType);
	}
	/**
	 * 批量更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	@Override
	public int updateEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList){
		return ebuyOrderDeliveryTypeBaseDao.updateEbuyOrderDeliveryTypeBatch(ebuyOrderDeliveryTypeList);
	}
	/**
	 * 根据序列号删除(订单的配送设置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyOrderDeliveryTypeLogic(java.math.BigInteger id){
		return ebuyOrderDeliveryTypeBaseDao.deleteEbuyOrderDeliveryTypeLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(订单的配送设置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyOrderDeliveryTypeLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderDeliveryTypeBaseDao.deleteEbuyOrderDeliveryTypeLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(订单的配送设置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderDeliveryType(java.math.BigInteger id){
//		return ebuyOrderDeliveryTypeBaseDao.deleteEbuyOrderDeliveryType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单的配送设置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderDeliveryTypeBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderDeliveryTypeBaseDao.deleteEbuyOrderDeliveryTypeBatch(idList);
//	}
	
}
