package com.cnfantasia.server.domainbase.ebuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;

/**
 * 描述:(配送方式) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryMethodBaseService {
	
	/**
	 * 根据条件查询(配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryMethod getEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryMethodCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryMethodCountDim(Map<String,Object> paramMap);
	/**
	 * 往(配送方式)新增一条记录
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	public int insertEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod);
	/**
	 * 批量新增(配送方式)
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
	 * 根据序列号批量删除(配送方式)信息_逻辑删除
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
