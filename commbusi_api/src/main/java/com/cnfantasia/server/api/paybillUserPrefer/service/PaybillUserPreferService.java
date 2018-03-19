package com.cnfantasia.server.api.paybillUserPrefer.service;

import java.util.Map;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.paybillUserPrefer.dao.IPaybillUserPreferDao;
import com.cnfantasia.server.api.paybillUserPrefer.entity.PaybillUserPrefer;
import com.cnfantasia.server.business.pub.entity.PageModel;


/**
 * 描述:(账单优惠用户关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PaybillUserPreferService implements IPaybillUserPreferService{
	
	private IPaybillUserPreferDao paybillUserPreferDao;
	public void setpaybillUserPreferDao(IPaybillUserPreferDao paybillUserPreferDao) {
		this.paybillUserPreferDao = paybillUserPreferDao;
	}
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap){
		return paybillUserPreferDao.selectPaybillUserPreferByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap){
		return paybillUserPreferDao.selectPaybillUserPreferByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return paybillUserPreferDao.selectPaybillUserPreferByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return paybillUserPreferDao.selectPaybillUserPreferByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单优惠用户关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PaybillUserPrefer getPaybillUserPreferBySeqId(java.math.BigInteger id){
		return paybillUserPreferDao.selectPaybillUserPreferBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPaybillUserPreferCount(Map<String,Object> paramMap){
		return paybillUserPreferDao.selectPaybillUserPreferCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPaybillUserPreferCountDim(Map<String,Object> paramMap){
		return paybillUserPreferDao.selectPaybillUserPreferCount(paramMap,true);
	}
	/**
	 * 往(账单优惠用户关系表)新增一条记录
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int insertPaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return paybillUserPreferDao.insertPaybillUserPrefer(paybillUserPrefer);
	}
	/**
	 * 批量新增(账单优惠用户关系表)
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int insertPaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList){
		return paybillUserPreferDao.insertPaybillUserPreferBatch(paybillUserPreferList);
	}
	/**
	 * 更新(账单优惠用户关系表)信息
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int updatePaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return paybillUserPreferDao.updatePaybillUserPrefer(paybillUserPrefer);
	}
	/**
	 * 批量更新(账单优惠用户关系表)信息
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int updatePaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList){
		return paybillUserPreferDao.updatePaybillUserPreferBatch(paybillUserPreferList);
	}
	/**
	 * 根据序列号删除(账单优惠用户关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePaybillUserPreferLogic(java.math.BigInteger id){
		return paybillUserPreferDao.deletePaybillUserPreferLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(账单优惠用户关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deletePaybillUserPreferLogicBatch(List<java.math.BigInteger> idList){
		return paybillUserPreferDao.deletePaybillUserPreferLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(账单优惠用户关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPrefer(java.math.BigInteger id){
//		return paybillUserPreferDao.deletePaybillUserPrefer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单优惠用户关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPreferBatch(List<java.math.BigInteger> idList){
//		return paybillUserPreferDao.deletePaybillUserPreferBatch(idList);
//	}
	
	/**
	 * 获取用户的
	 * @param payBillId
	 * @param userId
	 * @return
	 */
	@Override
	public PaybillUserPrefer getUserBillPrefer(BigInteger payBillId, BigInteger userId) {
		return paybillUserPreferDao.getUserBillPrefer(payBillId, userId);
	}
	
}
