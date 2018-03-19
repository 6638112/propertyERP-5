package com.cnfantasia.server.domainbase.ebuyOrder.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * 描述:(订单表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderBaseDao {
	/**
	 * 根据条件查询(订单表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrder> selectEbuyOrderByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(订单表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrder> selectEbuyOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(订单表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrder selectEbuyOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(订单表)新增一条记录
	 * @param ebuyOrder
	 * @return
	 */
	public int insertEbuyOrder(EbuyOrder ebuyOrder);
	
	/**
	 * 批量新增(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	public int insertEbuyOrderBatch(List<EbuyOrder> ebuyOrderList);
	
	/**
	 * 更新(订单表)信息
	 * @param ebuyOrder
	 * @return
	 */
	public int updateEbuyOrder(EbuyOrder ebuyOrder);
	
	/**
	 * 批量更新(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	public int updateEbuyOrderBatch(List<EbuyOrder> ebuyOrderList);
	
	/**
	 * 根据序列号删除(订单表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(订单表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(订单表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderBatch(List<java.math.BigInteger> idList);
	
	
}
