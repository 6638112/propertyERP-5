package com.cnfantasia.server.domainbase.payKeyList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.payKeyList.dao.IPayKeyListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payKeyList.entity.PayKeyList;

/**
 * 描述:(云钥匙付款列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PayKeyListBaseService implements IPayKeyListBaseService{
	
	private IPayKeyListBaseDao payKeyListBaseDao;
	public void setPayKeyListBaseDao(IPayKeyListBaseDao payKeyListBaseDao) {
		this.payKeyListBaseDao = payKeyListBaseDao;
	}
	/**
	 * 根据条件查询(云钥匙付款列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayKeyList> getPayKeyListByCondition(Map<String,Object> paramMap){
		return payKeyListBaseDao.selectPayKeyListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(云钥匙付款列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PayKeyList> getPayKeyListByConditionDim(Map<String,Object> paramMap){
		return payKeyListBaseDao.selectPayKeyListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(云钥匙付款列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayKeyList> getPayKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return payKeyListBaseDao.selectPayKeyListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(云钥匙付款列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PayKeyList> getPayKeyListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return payKeyListBaseDao.selectPayKeyListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(云钥匙付款列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayKeyList getPayKeyListBySeqId(java.math.BigInteger id){
		return payKeyListBaseDao.selectPayKeyListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayKeyListCount(Map<String,Object> paramMap){
		return payKeyListBaseDao.selectPayKeyListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPayKeyListCountDim(Map<String,Object> paramMap){
		return payKeyListBaseDao.selectPayKeyListCount(paramMap,true);
	}
	/**
	 * 往(云钥匙付款列表)新增一条记录
	 * @param payKeyList
	 * @return
	 */
	@Override
	public int insertPayKeyList(PayKeyList payKeyList){
		return payKeyListBaseDao.insertPayKeyList(payKeyList);
	}
	/**
	 * 批量新增(云钥匙付款列表)
	 * @param payKeyListList
	 * @return
	 */
	@Override
	public int insertPayKeyListBatch(List<PayKeyList> payKeyListList){
		return payKeyListBaseDao.insertPayKeyListBatch(payKeyListList);
	}
	/**
	 * 更新(云钥匙付款列表)信息
	 * @param payKeyList
	 * @return
	 */
	@Override
	public int updatePayKeyList(PayKeyList payKeyList){
		return payKeyListBaseDao.updatePayKeyList(payKeyList);
	}
	/**
	 * 批量更新(云钥匙付款列表)信息
	 * @param payKeyListList
	 * @return
	 */
	@Override
	public int updatePayKeyListBatch(List<PayKeyList> payKeyListList){
		return payKeyListBaseDao.updatePayKeyListBatch(payKeyListList);
	}
	/**
	 * 根据序列号删除(云钥匙付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayKeyListLogic(java.math.BigInteger id){
		return payKeyListBaseDao.deletePayKeyListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(云钥匙付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayKeyListLogicBatch(List<java.math.BigInteger> idList){
		return payKeyListBaseDao.deletePayKeyListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(云钥匙付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayKeyList(java.math.BigInteger id){
//		return payKeyListBaseDao.deletePayKeyList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayKeyListBatch(List<java.math.BigInteger> idList){
//		return payKeyListBaseDao.deletePayKeyListBatch(idList);
//	}
	
}
