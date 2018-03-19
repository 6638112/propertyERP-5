package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;

/**
 * 描述:(订单的配送设置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderDeliveryTypeBaseDao {
	/**
	 * 根据条件查询(订单的配送设置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderDeliveryType> selectEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(订单的配送设置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderDeliveryType> selectEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(订单的配送设置)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderDeliveryType selectEbuyOrderDeliveryTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderDeliveryTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(订单的配送设置)新增一条记录
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	public int insertEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType);
	
	/**
	 * 批量新增(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	public int insertEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList);
	
	/**
	 * 更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	public int updateEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType);
	
	/**
	 * 批量更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	public int updateEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList);
	
	/**
	 * 根据序列号删除(订单的配送设置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteEbuyOrderDeliveryTypeLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据Id 批量删除(订单的配送设置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteEbuyOrderDeliveryTypeLogicBatch(List<java.math.BigInteger> idList);
	 */
	
//	/**
//	 * 根据序列号删除(订单的配送设置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderDeliveryType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单的配送设置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderDeliveryTypeBatch(List<java.math.BigInteger> idList);
	
	
}
