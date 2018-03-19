package com.cnfantasia.server.domainbase.payRedEnvelope.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payRedEnvelope.dao.IPayRedEnvelopeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * 描述:(粮票信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayRedEnvelopeBaseService implements IPayRedEnvelopeBaseService{
	
	private IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao;
	public void setPayRedEnvelopeBaseDao(IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao) {
		this.payRedEnvelopeBaseDao = payRedEnvelopeBaseDao;
	}
	/**
	 * 根据条件查询(粮票信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> getPayRedEnvelopeByCondition(Map<String,Object> paramMap){
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(粮票信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> getPayRedEnvelopeByConditionDim(Map<String,Object> paramMap){
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(粮票信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> getPayRedEnvelopeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(粮票信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> getPayRedEnvelopeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(粮票信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayRedEnvelope getPayRedEnvelopeBySeqId(java.math.BigInteger id){
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRedEnvelopeCount(Map<String,Object> paramMap){
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRedEnvelopeCountDim(Map<String,Object> paramMap){
		return payRedEnvelopeBaseDao.selectPayRedEnvelopeCount(paramMap,true);
	}
	/**
	 * 往(粮票信息表)新增一条记录
	 * @param payRedEnvelope
	 * @return
	 */
	@Override
	public int insertPayRedEnvelope(PayRedEnvelope payRedEnvelope){
		return payRedEnvelopeBaseDao.insertPayRedEnvelope(payRedEnvelope);
	}
	/**
	 * 批量新增(粮票信息表)
	 * @param payRedEnvelopeList
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList){
		return payRedEnvelopeBaseDao.insertPayRedEnvelopeBatch(payRedEnvelopeList);
	}
	/**
	 * 更新(粮票信息表)信息
	 * @param payRedEnvelope
	 * @return
	 */
	@Override
	public int updatePayRedEnvelope(PayRedEnvelope payRedEnvelope){
		return payRedEnvelopeBaseDao.updatePayRedEnvelope(payRedEnvelope);
	}
	/**
	 * 批量更新(粮票信息表)信息
	 * @param payRedEnvelopeList
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList){
		return payRedEnvelopeBaseDao.updatePayRedEnvelopeBatch(payRedEnvelopeList);
	}
	/**
	 * 根据序列号删除(粮票信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeLogic(java.math.BigInteger id){
		return payRedEnvelopeBaseDao.deletePayRedEnvelopeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(粮票信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeLogicBatch(List<java.math.BigInteger> idList){
		return payRedEnvelopeBaseDao.deletePayRedEnvelopeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(粮票信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelope(java.math.BigInteger id){
//		return payRedEnvelopeBaseDao.deletePayRedEnvelope(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeBatch(List<java.math.BigInteger> idList){
//		return payRedEnvelopeBaseDao.deletePayRedEnvelopeBatch(idList);
//	}
	
}
