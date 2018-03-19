package com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;

/**
 * 描述:(配送方式) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryMethodBaseDao {
	/**
	 * 根据条件查询(配送方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(配送方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryMethod selectEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(配送方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyDeliveryMethodCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(配送方式)新增一条记录
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	public int insertEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod);
	
	/**
	 * 批量新增(配送方式)信息
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	public int insertEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList);
	
	/**
	 * 更新(配送方式)信息
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	public int updateEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod);
	
	/**
	 * 批量更新(配送方式)信息
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	public int updateEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList);
	
	/**
	 * 根据序列号删除(配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryMethodLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryMethod(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList);
	
	
}
