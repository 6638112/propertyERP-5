package com.cnfantasia.server.domainbase.payConfigHb.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payConfigHb.dao.IPayConfigHbBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payConfigHb.entity.PayConfigHb;

/**
 * 描述:(粮票支付配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayConfigHbBaseService implements IPayConfigHbBaseService{
	
	private IPayConfigHbBaseDao payConfigHbBaseDao;
	public void setPayConfigHbBaseDao(IPayConfigHbBaseDao payConfigHbBaseDao) {
		this.payConfigHbBaseDao = payConfigHbBaseDao;
	}
	/**
	 * 根据条件查询(粮票支付配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayConfigHb> getPayConfigHbByCondition(Map<String,Object> paramMap){
		return payConfigHbBaseDao.selectPayConfigHbByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(粮票支付配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayConfigHb> getPayConfigHbByConditionDim(Map<String,Object> paramMap){
		return payConfigHbBaseDao.selectPayConfigHbByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(粮票支付配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayConfigHb> getPayConfigHbByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payConfigHbBaseDao.selectPayConfigHbByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(粮票支付配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayConfigHb> getPayConfigHbByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payConfigHbBaseDao.selectPayConfigHbByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(粮票支付配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayConfigHb getPayConfigHbBySeqId(java.math.BigInteger id){
		return payConfigHbBaseDao.selectPayConfigHbBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(粮票支付配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayConfigHbCount(Map<String,Object> paramMap){
		return payConfigHbBaseDao.selectPayConfigHbCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(粮票支付配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayConfigHbCountDim(Map<String,Object> paramMap){
		return payConfigHbBaseDao.selectPayConfigHbCount(paramMap,true);
	}
	/**
	 * 往(粮票支付配置表)新增一条记录
	 * @param payConfigHb
	 * @return
	 */
	@Override
	public int insertPayConfigHb(PayConfigHb payConfigHb){
		return payConfigHbBaseDao.insertPayConfigHb(payConfigHb);
	}
	/**
	 * 批量新增(粮票支付配置表)
	 * @param payConfigHbList
	 * @return
	 */
	@Override
	public int insertPayConfigHbBatch(List<PayConfigHb> payConfigHbList){
		return payConfigHbBaseDao.insertPayConfigHbBatch(payConfigHbList);
	}
	/**
	 * 更新(粮票支付配置表)信息
	 * @param payConfigHb
	 * @return
	 */
	@Override
	public int updatePayConfigHb(PayConfigHb payConfigHb){
		return payConfigHbBaseDao.updatePayConfigHb(payConfigHb);
	}
	/**
	 * 批量更新(粮票支付配置表)信息
	 * @param payConfigHbList
	 * @return
	 */
	@Override
	public int updatePayConfigHbBatch(List<PayConfigHb> payConfigHbList){
		return payConfigHbBaseDao.updatePayConfigHbBatch(payConfigHbList);
	}
	/**
	 * 根据序列号删除(粮票支付配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayConfigHbLogic(java.math.BigInteger id){
		return payConfigHbBaseDao.deletePayConfigHbLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(粮票支付配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayConfigHbLogicBatch(List<java.math.BigInteger> idList){
		return payConfigHbBaseDao.deletePayConfigHbLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(粮票支付配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayConfigHb(java.math.BigInteger id){
//		return payConfigHbBaseDao.deletePayConfigHb(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票支付配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayConfigHbBatch(List<java.math.BigInteger> idList){
//		return payConfigHbBaseDao.deletePayConfigHbBatch(idList);
//	}
	
}
