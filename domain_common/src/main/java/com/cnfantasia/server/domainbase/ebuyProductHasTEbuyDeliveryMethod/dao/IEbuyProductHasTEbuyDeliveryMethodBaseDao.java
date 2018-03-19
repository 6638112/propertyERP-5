package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyDeliveryMethod.entity.EbuyProductHasTEbuyDeliveryMethod;

/**
 * 描述:(商品支持的配送方式) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductHasTEbuyDeliveryMethodBaseDao {
	/**
	 * 根据条件查询(商品支持的配送方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> selectEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品支持的配送方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductHasTEbuyDeliveryMethod> selectEbuyProductHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品支持的配送方式)信息
	 * @param id
	 * @return
	 */
	public EbuyProductHasTEbuyDeliveryMethod selectEbuyProductHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品支持的配送方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品支持的配送方式)新增一条记录
	 * @param ebuyProductHasTEbuyDeliveryMethod
	 * @return
	 */
	public int insertEbuyProductHasTEbuyDeliveryMethod(EbuyProductHasTEbuyDeliveryMethod ebuyProductHasTEbuyDeliveryMethod);
	
	/**
	 * 批量新增(商品支持的配送方式)信息
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
	 * 根据Id 批量删除(商品支持的配送方式)信息_逻辑删除
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
