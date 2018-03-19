package com.cnfantasia.server.api.paybillUserPrefer.service;

import java.util.Map;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.paybillUserPrefer.entity.PaybillUserPrefer;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * 描述:(账单优惠用户关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPaybillUserPreferService {
	
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PaybillUserPrefer> getPaybillUserPreferByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PaybillUserPrefer> getPaybillUserPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(账单优惠用户关系表)信息
	 * @param id
	 * @return
	 */
	public PaybillUserPrefer getPaybillUserPreferBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPaybillUserPreferCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPaybillUserPreferCountDim(Map<String,Object> paramMap);
	/**
	 * 往(账单优惠用户关系表)新增一条记录
	 * @param paybillUserPrefer
	 * @return
	 */
	public int insertPaybillUserPrefer(PaybillUserPrefer paybillUserPrefer);
	/**
	 * 批量新增(账单优惠用户关系表)
	 * @param paybillUserPreferList
	 * @return
	 */
	public int insertPaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList);
	/**
	 * 更新(账单优惠用户关系表)信息
	 * @param paybillUserPrefer
	 * @return
	 */
	public int updatePaybillUserPrefer(PaybillUserPrefer paybillUserPrefer);
	/**
	 * 批量更新(账单优惠用户关系表)信息
	 * @param paybillUserPreferList
	 * @return
	 */
	public int updatePaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList);
	
	/**
	 * 获取用户的
	 * @param payBillId
	 * @param userId
	 * @return
	 */
	public PaybillUserPrefer getUserBillPrefer(BigInteger payBillId, BigInteger userId);
	
	/**
	 * 根据序列号删除(账单优惠用户关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deletePaybillUserPreferLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(账单优惠用户关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deletePaybillUserPreferLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(账单优惠用户关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePaybillUserPrefer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账单优惠用户关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePaybillUserPreferBatch(List<java.math.BigInteger> idList);
	
}
