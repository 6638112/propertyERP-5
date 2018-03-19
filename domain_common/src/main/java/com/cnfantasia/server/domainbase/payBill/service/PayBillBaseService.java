package com.cnfantasia.server.domainbase.payBill.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 描述:(物业账单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayBillBaseService implements IPayBillBaseService{
	
	private IPayBillBaseDao payBillBaseDao;
	public void setPayBillBaseDao(IPayBillBaseDao payBillBaseDao) {
		this.payBillBaseDao = payBillBaseDao;
	}
	/**
	 * 根据条件查询(物业账单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBill> getPayBillByCondition(Map<String,Object> paramMap){
		return payBillBaseDao.selectPayBillByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业账单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBill> getPayBillByConditionDim(Map<String,Object> paramMap){
		return payBillBaseDao.selectPayBillByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业账单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBill> getPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillBaseDao.selectPayBillByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业账单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBill> getPayBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillBaseDao.selectPayBillByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业账单)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBill getPayBillBySeqId(java.math.BigInteger id){
		return payBillBaseDao.selectPayBillBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业账单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillCount(Map<String,Object> paramMap){
		return payBillBaseDao.selectPayBillCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业账单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillCountDim(Map<String,Object> paramMap){
		return payBillBaseDao.selectPayBillCount(paramMap,true);
	}
	/**
	 * 往(物业账单)新增一条记录
	 * @param payBill
	 * @return
	 */
	@Override
	public int insertPayBill(PayBill payBill){
		return payBillBaseDao.insertPayBill(payBill);
	}
	/**
	 * 批量新增(物业账单)
	 * @param payBillList
	 * @return
	 */
	@Override
	public int insertPayBillBatch(List<PayBill> payBillList){
		return payBillBaseDao.insertPayBillBatch(payBillList);
	}
	/**
	 * 更新(物业账单)信息
	 * @param payBill
	 * @return
	 */
	@Override
	public int updatePayBill(PayBill payBill){
		return payBillBaseDao.updatePayBill(payBill);
	}
	/**
	 * 批量更新(物业账单)信息
	 * @param payBillList
	 * @return
	 */
	@Override
	public int updatePayBillBatch(List<PayBill> payBillList){
		return payBillBaseDao.updatePayBillBatch(payBillList);
	}
	/**
	 * 根据序列号删除(物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillLogic(java.math.BigInteger id){
		return payBillBaseDao.deletePayBillLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillLogicBatch(List<java.math.BigInteger> idList){
		return payBillBaseDao.deletePayBillLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBill(java.math.BigInteger id){
//		return payBillBaseDao.deletePayBill(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillBatch(List<java.math.BigInteger> idList){
//		return payBillBaseDao.deletePayBillBatch(idList);
//	}
	
}
