package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.entity.EbuyProductHasTEbuyDeliveryMethod;

/**
 * 描述:(商品支持的配送方式) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductHasTEbuyDeliveryMethodBaseService {
	
	/**
	 * 根据条件查询(商品支持的配送方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品支持的配送方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品支持的配送方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品支持的配送方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> getEbuyProductHasTEbuyDeliveryMethodByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品支持的配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuyProductHasTEbuyDeliveryMethod getEbuyProductHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品支持的配送方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品支持的配送方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductHasTEbuyDeliveryMethodCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品支持的配送方式)新增一条记录
	 * @param ebuyProductHasTEbuyDeliveryMethod
	 * @return
	 */
	public int insertEbuyProductHasTEbuyDeliveryMethod(EbuyProductHasTEbuyDeliveryMethod ebuyProductHasTEbuyDeliveryMethod);
	/**
	 * 批量新增(商品支持的配送方式)
	 * @param ebuyProductHasTEbuyDeliveryMethodList
	 * @return
	 */
	public int insertEbuyProductHasTEbuyDeliveryMethodBatch(List<EbuyProductHasTEbuyDeliveryMethod> ebuyProductHasTEbuyDeliveryMethodList);
	/**
	 * 更新(商品支持的配送方式)信息
	 * @param ebuyProductHasTEbuyDeliveryMethod
	 * @return
	 */
	public int updateEbuyProductHasTEbuyDeliveryMethod(EbuyProductHasTEbuyDeliveryMethod ebuyProductHasTEbuyDeliveryMethod);
	/**
	 * 批量更新(商品支持的配送方式)信息
	 * @param ebuyProductHasTEbuyDeliveryMethodList
	 * @return
	 */
	public int updateEbuyProductHasTEbuyDeliveryMethodBatch(List<EbuyProductHasTEbuyDeliveryMethod> ebuyProductHasTEbuyDeliveryMethodList);
	/**
	 * 根据序列号删除(商品支持的配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductHasTEbuyDeliveryMethodLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品支持的配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductHasTEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品支持的配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductHasTEbuyDeliveryMethod(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品支持的配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductHasTEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList);
	
}
