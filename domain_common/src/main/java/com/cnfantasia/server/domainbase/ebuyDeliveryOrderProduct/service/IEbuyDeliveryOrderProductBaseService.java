package com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;

/**
 * 描述:(供应商配送包含的商品信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderProductBaseService {
	
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商配送包含的商品信息)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrderProduct getEbuyDeliveryOrderProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商配送包含的商品信息)新增一条记录
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	public int insertEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct);
	/**
	 * 批量新增(供应商配送包含的商品信息)
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	public int insertEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList);
	/**
	 * 更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	public int updateEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct);
	/**
	 * 批量更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	public int updateEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList);
	/**
	 * 根据序列号删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商配送包含的商品信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商配送包含的商品信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderProductBatch(List<java.math.BigInteger> idList);
	
}
