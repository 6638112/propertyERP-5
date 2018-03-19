package com.cnfantasia.server.domainbase.paybillUserPrefer.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.paybillUserPrefer.dao.IPaybillUserPreferBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.paybillUserPrefer.entity.PaybillUserPrefer;

/**
 * 描述:(账单优惠用户关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PaybillUserPreferBaseService implements IPaybillUserPreferBaseService{
	
	private IPaybillUserPreferBaseDao paybillUserPreferBaseDao;
	public void setPaybillUserPreferBaseDao(IPaybillUserPreferBaseDao paybillUserPreferBaseDao) {
		this.paybillUserPreferBaseDao = paybillUserPreferBaseDao;
	}
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap){
		return paybillUserPreferBaseDao.selectPaybillUserPreferByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap){
		return paybillUserPreferBaseDao.selectPaybillUserPreferByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return paybillUserPreferBaseDao.selectPaybillUserPreferByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return paybillUserPreferBaseDao.selectPaybillUserPreferByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单优惠用户关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PaybillUserPrefer getPaybillUserPreferBySeqId(java.math.BigInteger id){
		return paybillUserPreferBaseDao.selectPaybillUserPreferBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPaybillUserPreferCount(Map<String,Object> paramMap){
		return paybillUserPreferBaseDao.selectPaybillUserPreferCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPaybillUserPreferCountDim(Map<String,Object> paramMap){
		return paybillUserPreferBaseDao.selectPaybillUserPreferCount(paramMap,true);
	}
	/**
	 * 往(账单优惠用户关系表)新增一条记录
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int insertPaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return paybillUserPreferBaseDao.insertPaybillUserPrefer(paybillUserPrefer);
	}
	/**
	 * 批量新增(账单优惠用户关系表)
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int insertPaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList){
		return paybillUserPreferBaseDao.insertPaybillUserPreferBatch(paybillUserPreferList);
	}
	/**
	 * 更新(账单优惠用户关系表)信息
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int updatePaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return paybillUserPreferBaseDao.updatePaybillUserPrefer(paybillUserPrefer);
	}
	/**
	 * 批量更新(账单优惠用户关系表)信息
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int updatePaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList){
		return paybillUserPreferBaseDao.updatePaybillUserPreferBatch(paybillUserPreferList);
	}
	/**
	 * 根据序列号删除(账单优惠用户关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePaybillUserPreferLogic(java.math.BigInteger id){
		return paybillUserPreferBaseDao.deletePaybillUserPreferLogic(id);
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
		return paybillUserPreferBaseDao.deletePaybillUserPreferLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(账单优惠用户关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPrefer(java.math.BigInteger id){
//		return paybillUserPreferBaseDao.deletePaybillUserPrefer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单优惠用户关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPreferBatch(List<java.math.BigInteger> idList){
//		return paybillUserPreferBaseDao.deletePaybillUserPreferBatch(idList);
//	}
	
}
