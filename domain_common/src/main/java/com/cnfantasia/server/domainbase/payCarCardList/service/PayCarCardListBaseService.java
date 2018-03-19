package com.cnfantasia.server.domainbase.payCarCardList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payCarCardList.dao.IPayCarCardListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCarCardList.entity.PayCarCardList;

/**
 * 描述:(车禁月卡付款列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayCarCardListBaseService implements IPayCarCardListBaseService{
	
	private IPayCarCardListBaseDao payCarCardListBaseDao;
	public void setPayCarCardListBaseDao(IPayCarCardListBaseDao payCarCardListBaseDao) {
		this.payCarCardListBaseDao = payCarCardListBaseDao;
	}
	/**
	 * 根据条件查询(车禁月卡付款列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayCarCardList> getPayCarCardListByCondition(Map<String,Object> paramMap){
		return payCarCardListBaseDao.selectPayCarCardListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(车禁月卡付款列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayCarCardList> getPayCarCardListByConditionDim(Map<String,Object> paramMap){
		return payCarCardListBaseDao.selectPayCarCardListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayCarCardList> getPayCarCardListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payCarCardListBaseDao.selectPayCarCardListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayCarCardList> getPayCarCardListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payCarCardListBaseDao.selectPayCarCardListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(车禁月卡付款列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayCarCardList getPayCarCardListBySeqId(java.math.BigInteger id){
		return payCarCardListBaseDao.selectPayCarCardListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayCarCardListCount(Map<String,Object> paramMap){
		return payCarCardListBaseDao.selectPayCarCardListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayCarCardListCountDim(Map<String,Object> paramMap){
		return payCarCardListBaseDao.selectPayCarCardListCount(paramMap,true);
	}
	/**
	 * 往(车禁月卡付款列表)新增一条记录
	 * @param payCarCardList
	 * @return
	 */
	@Override
	public int insertPayCarCardList(PayCarCardList payCarCardList){
		return payCarCardListBaseDao.insertPayCarCardList(payCarCardList);
	}
	/**
	 * 批量新增(车禁月卡付款列表)
	 * @param payCarCardListList
	 * @return
	 */
	@Override
	public int insertPayCarCardListBatch(List<PayCarCardList> payCarCardListList){
		return payCarCardListBaseDao.insertPayCarCardListBatch(payCarCardListList);
	}
	/**
	 * 更新(车禁月卡付款列表)信息
	 * @param payCarCardList
	 * @return
	 */
	@Override
	public int updatePayCarCardList(PayCarCardList payCarCardList){
		return payCarCardListBaseDao.updatePayCarCardList(payCarCardList);
	}
	/**
	 * 批量更新(车禁月卡付款列表)信息
	 * @param payCarCardListList
	 * @return
	 */
	@Override
	public int updatePayCarCardListBatch(List<PayCarCardList> payCarCardListList){
		return payCarCardListBaseDao.updatePayCarCardListBatch(payCarCardListList);
	}
	/**
	 * 根据序列号删除(车禁月卡付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayCarCardListLogic(java.math.BigInteger id){
		return payCarCardListBaseDao.deletePayCarCardListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(车禁月卡付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayCarCardListLogicBatch(List<java.math.BigInteger> idList){
		return payCarCardListBaseDao.deletePayCarCardListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(车禁月卡付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayCarCardList(java.math.BigInteger id){
//		return payCarCardListBaseDao.deletePayCarCardList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车禁月卡付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayCarCardListBatch(List<java.math.BigInteger> idList){
//		return payCarCardListBaseDao.deletePayCarCardListBatch(idList);
//	}
	
}
