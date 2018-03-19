package com.cnfantasia.server.api.paybillUserPrefer.dao;

import java.util.Map;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.paybillUserPrefer.entity.PaybillUserPrefer;
import com.cnfantasia.server.business.pub.entity.PageModel;


/**
 * 描述:(账单优惠用户关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPaybillUserPreferDao {
	/**
	 * 根据条件查询(账单优惠用户关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PaybillUserPrefer> selectPaybillUserPreferByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PaybillUserPrefer> selectPaybillUserPreferByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(账单优惠用户关系表)信息
	 * @param id
	 * @return
	 */
	public PaybillUserPrefer selectPaybillUserPreferBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPaybillUserPreferCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(账单优惠用户关系表)新增一条记录
	 * @param paybillUserPrefer
	 * @return
	 */
	public int insertPaybillUserPrefer(PaybillUserPrefer paybillUserPrefer);
	
	/**
	 * 批量新增(账单优惠用户关系表)信息
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
	 * 获取用户账单优惠信息
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
	 * 根据Id 批量删除(账单优惠用户关系表)信息_逻辑删除
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
