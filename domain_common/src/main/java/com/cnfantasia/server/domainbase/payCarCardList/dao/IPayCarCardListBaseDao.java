package com.cnfantasia.server.domainbase.payCarCardList.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCarCardList.entity.PayCarCardList;

/**
 * 描述:(车禁月卡付款列表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayCarCardListBaseDao {
	/**
	 * 根据条件查询(车禁月卡付款列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayCarCardList> selectPayCarCardListByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(车禁月卡付款列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayCarCardList> selectPayCarCardListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(车禁月卡付款列表)信息
	 * @param id
	 * @return
	 */
	public PayCarCardList selectPayCarCardListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(车禁月卡付款列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayCarCardListCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(车禁月卡付款列表)新增一条记录
	 * @param payCarCardList
	 * @return
	 */
	public int insertPayCarCardList(PayCarCardList payCarCardList);
	
	/**
	 * 批量新增(车禁月卡付款列表)信息
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
	 * 根据Id 批量删除(车禁月卡付款列表)信息_逻辑删除
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
