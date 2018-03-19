package com.cnfantasia.server.domainbase.payBillType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 描述:(账单类型基础信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayBillTypeBaseService implements IPayBillTypeBaseService{
	
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	public void setPayBillTypeBaseDao(IPayBillTypeBaseDao payBillTypeBaseDao) {
		this.payBillTypeBaseDao = payBillTypeBaseDao;
	}
	/**
	 * 根据条件查询(账单类型基础信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillType> getPayBillTypeByCondition(Map<String,Object> paramMap){
		return payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单类型基础信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillType> getPayBillTypeByConditionDim(Map<String,Object> paramMap){
		return payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单类型基础信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillType> getPayBillTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单类型基础信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillType> getPayBillTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单类型基础信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillType getPayBillTypeBySeqId(java.math.BigInteger id){
		return payBillTypeBaseDao.selectPayBillTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillTypeCount(Map<String,Object> paramMap){
		return payBillTypeBaseDao.selectPayBillTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillTypeCountDim(Map<String,Object> paramMap){
		return payBillTypeBaseDao.selectPayBillTypeCount(paramMap,true);
	}
	/**
	 * 往(账单类型基础信息)新增一条记录
	 * @param payBillType
	 * @return
	 */
	@Override
	public int insertPayBillType(PayBillType payBillType){
		return payBillTypeBaseDao.insertPayBillType(payBillType);
	}
	/**
	 * 批量新增(账单类型基础信息)
	 * @param payBillTypeList
	 * @return
	 */
	@Override
	public int insertPayBillTypeBatch(List<PayBillType> payBillTypeList){
		return payBillTypeBaseDao.insertPayBillTypeBatch(payBillTypeList);
	}
	/**
	 * 更新(账单类型基础信息)信息
	 * @param payBillType
	 * @return
	 */
	@Override
	public int updatePayBillType(PayBillType payBillType){
		return payBillTypeBaseDao.updatePayBillType(payBillType);
	}
	/**
	 * 批量更新(账单类型基础信息)信息
	 * @param payBillTypeList
	 * @return
	 */
	@Override
	public int updatePayBillTypeBatch(List<PayBillType> payBillTypeList){
		return payBillTypeBaseDao.updatePayBillTypeBatch(payBillTypeList);
	}
	/**
	 * 根据序列号删除(账单类型基础信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillTypeLogic(java.math.BigInteger id){
		return payBillTypeBaseDao.deletePayBillTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(账单类型基础信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillTypeLogicBatch(List<java.math.BigInteger> idList){
		return payBillTypeBaseDao.deletePayBillTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(账单类型基础信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillType(java.math.BigInteger id){
//		return payBillTypeBaseDao.deletePayBillType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单类型基础信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTypeBatch(List<java.math.BigInteger> idList){
//		return payBillTypeBaseDao.deletePayBillTypeBatch(idList);
//	}
	
}
