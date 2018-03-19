package com.cnfantasia.server.domainbase.payRedEnvelopeDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payRedEnvelopeDetail.dao.IPayRedEnvelopeDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;

/**
 * 描述:(粮票优惠详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayRedEnvelopeDetailBaseService implements IPayRedEnvelopeDetailBaseService{
	
	private IPayRedEnvelopeDetailBaseDao payRedEnvelopeDetailBaseDao;
	public void setPayRedEnvelopeDetailBaseDao(IPayRedEnvelopeDetailBaseDao payRedEnvelopeDetailBaseDao) {
		this.payRedEnvelopeDetailBaseDao = payRedEnvelopeDetailBaseDao;
	}
	/**
	 * 根据条件查询(粮票优惠详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByCondition(Map<String,Object> paramMap){
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(粮票优惠详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByConditionDim(Map<String,Object> paramMap){
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(粮票优惠详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(粮票优惠详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(粮票优惠详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayRedEnvelopeDetail getPayRedEnvelopeDetailBySeqId(java.math.BigInteger id){
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(粮票优惠详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRedEnvelopeDetailCount(Map<String,Object> paramMap){
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(粮票优惠详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayRedEnvelopeDetailCountDim(Map<String,Object> paramMap){
		return payRedEnvelopeDetailBaseDao.selectPayRedEnvelopeDetailCount(paramMap,true);
	}
	/**
	 * 往(粮票优惠详情)新增一条记录
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail){
		return payRedEnvelopeDetailBaseDao.insertPayRedEnvelopeDetail(payRedEnvelopeDetail);
	}
	/**
	 * 批量新增(粮票优惠详情)
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	@Override
	public int insertPayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList){
		return payRedEnvelopeDetailBaseDao.insertPayRedEnvelopeDetailBatch(payRedEnvelopeDetailList);
	}
	/**
	 * 更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail){
		return payRedEnvelopeDetailBaseDao.updatePayRedEnvelopeDetail(payRedEnvelopeDetail);
	}
	/**
	 * 批量更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	@Override
	public int updatePayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList){
		return payRedEnvelopeDetailBaseDao.updatePayRedEnvelopeDetailBatch(payRedEnvelopeDetailList);
	}
	/**
	 * 根据序列号删除(粮票优惠详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeDetailLogic(java.math.BigInteger id){
		return payRedEnvelopeDetailBaseDao.deletePayRedEnvelopeDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(粮票优惠详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayRedEnvelopeDetailLogicBatch(List<java.math.BigInteger> idList){
		return payRedEnvelopeDetailBaseDao.deletePayRedEnvelopeDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(粮票优惠详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeDetail(java.math.BigInteger id){
//		return payRedEnvelopeDetailBaseDao.deletePayRedEnvelopeDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票优惠详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayRedEnvelopeDetailBatch(List<java.math.BigInteger> idList){
//		return payRedEnvelopeDetailBaseDao.deletePayRedEnvelopeDetailBatch(idList);
//	}
	
}
