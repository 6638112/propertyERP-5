package com.cnfantasia.server.domainbase.payBillTimeCfg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payBillTimeCfg.dao.IPayBillTimeCfgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;

/**
 * 描述:(账单类型对应缴费时间配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayBillTimeCfgBaseService implements IPayBillTimeCfgBaseService{
	
	private IPayBillTimeCfgBaseDao payBillTimeCfgBaseDao;
	public void setPayBillTimeCfgBaseDao(IPayBillTimeCfgBaseDao payBillTimeCfgBaseDao) {
		this.payBillTimeCfgBaseDao = payBillTimeCfgBaseDao;
	}
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> getPayBillTimeCfgByCondition(Map<String,Object> paramMap){
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> getPayBillTimeCfgByConditionDim(Map<String,Object> paramMap){
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> getPayBillTimeCfgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> getPayBillTimeCfgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单类型对应缴费时间配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillTimeCfg getPayBillTimeCfgBySeqId(java.math.BigInteger id){
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillTimeCfgCount(Map<String,Object> paramMap){
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayBillTimeCfgCountDim(Map<String,Object> paramMap){
		return payBillTimeCfgBaseDao.selectPayBillTimeCfgCount(paramMap,true);
	}
	/**
	 * 往(账单类型对应缴费时间配置)新增一条记录
	 * @param payBillTimeCfg
	 * @return
	 */
	@Override
	public int insertPayBillTimeCfg(PayBillTimeCfg payBillTimeCfg){
		return payBillTimeCfgBaseDao.insertPayBillTimeCfg(payBillTimeCfg);
	}
	/**
	 * 批量新增(账单类型对应缴费时间配置)
	 * @param payBillTimeCfgList
	 * @return
	 */
	@Override
	public int insertPayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList){
		return payBillTimeCfgBaseDao.insertPayBillTimeCfgBatch(payBillTimeCfgList);
	}
	/**
	 * 更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfg
	 * @return
	 */
	@Override
	public int updatePayBillTimeCfg(PayBillTimeCfg payBillTimeCfg){
		return payBillTimeCfgBaseDao.updatePayBillTimeCfg(payBillTimeCfg);
	}
	/**
	 * 批量更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfgList
	 * @return
	 */
	@Override
	public int updatePayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList){
		return payBillTimeCfgBaseDao.updatePayBillTimeCfgBatch(payBillTimeCfgList);
	}
	/**
	 * 根据序列号删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillTimeCfgLogic(java.math.BigInteger id){
		return payBillTimeCfgBaseDao.deletePayBillTimeCfgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillTimeCfgLogicBatch(List<java.math.BigInteger> idList){
		return payBillTimeCfgBaseDao.deletePayBillTimeCfgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(账单类型对应缴费时间配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTimeCfg(java.math.BigInteger id){
//		return payBillTimeCfgBaseDao.deletePayBillTimeCfg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单类型对应缴费时间配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTimeCfgBatch(List<java.math.BigInteger> idList){
//		return payBillTimeCfgBaseDao.deletePayBillTimeCfgBatch(idList);
//	}
	
}
