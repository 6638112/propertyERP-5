package com.cnfantasia.server.domainbase.ebuyFightOrder.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;

/**
 * 描述:(拼购详情订单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyFightOrderBaseDao {
	/**
	 * 根据条件查询(拼购详情订单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyFightOrder> selectEbuyFightOrderByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(拼购详情订单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyFightOrder> selectEbuyFightOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(拼购详情订单)信息
	 * @param id
	 * @return
	 */
	public EbuyFightOrder selectEbuyFightOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyFightOrderCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(拼购详情订单)新增一条记录
	 * @param ebuyFightOrder
	 * @return
	 */
	public int insertEbuyFightOrder(EbuyFightOrder ebuyFightOrder);
	
	/**
	 * 批量新增(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	public int insertEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList);
	
	/**
	 * 更新(拼购详情订单)信息
	 * @param ebuyFightOrder
	 * @return
	 */
	public int updateEbuyFightOrder(EbuyFightOrder ebuyFightOrder);
	
	/**
	 * 批量更新(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	public int updateEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList);
	
	/**
	 * 根据序列号删除(拼购详情订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyFightOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(拼购详情订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyFightOrderLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(拼购详情订单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyFightOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼购详情订单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyFightOrderBatch(List<java.math.BigInteger> idList);
	
	
}
