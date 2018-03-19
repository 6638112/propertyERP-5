package com.cnfantasia.server.domainbase.payCarCardList.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payCarCardList.entity.PayCarCardList;

/**
 * 描述:(车禁月卡付款列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayCarCardListBaseService {
	
	/**
	 * 根据条件查询(车禁月卡付款列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayCarCardList> getPayCarCardListByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(车禁月卡付款列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayCarCardList> getPayCarCardListByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayCarCardList> getPayCarCardListByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayCarCardList> getPayCarCardListByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(车禁月卡付款列表)信息
	 * @param id
	 * @return
	 */
	public PayCarCardList getPayCarCardListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayCarCardListCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayCarCardListCountDim(Map<String,Object> paramMap);
	/**
	 * 往(车禁月卡付款列表)新增一条记录
	 * @param payCarCardList
	 * @return
	 */
	public int insertPayCarCardList(PayCarCardList payCarCardList);
	/**
	 * 批量新增(车禁月卡付款列表)
	 * @param payCarCardListList
	 * @return
	 */
	public int insertPayCarCardListBatch(List<PayCarCardList> payCarCardListList);
	/**
	 * 更新(车禁月卡付款列表)信息
	 * @param payCarCardList
	 * @return
	 */
	public int updatePayCarCardList(PayCarCardList payCarCardList);
	/**
	 * 批量更新(车禁月卡付款列表)信息
	 * @param payCarCardListList
	 * @return
	 */
	public int updatePayCarCardListBatch(List<PayCarCardList> payCarCardListList);
	/**
	 * 根据序列号删除(车禁月卡付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayCarCardListLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(车禁月卡付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayCarCardListLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(车禁月卡付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayCarCardList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(车禁月卡付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayCarCardListBatch(List<java.math.BigInteger> idList);
	
}
