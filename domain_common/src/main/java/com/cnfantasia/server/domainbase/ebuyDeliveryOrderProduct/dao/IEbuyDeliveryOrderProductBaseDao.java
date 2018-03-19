package com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;

/**
 * 描述:(供应商配送包含的商品信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderProductBaseDao {
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> selectEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrderProduct> selectEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商配送包含的商品信息)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrderProduct selectEbuyDeliveryOrderProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyDeliveryOrderProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商配送包含的商品信息)新增一条记录
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	public int insertEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct);
	
	/**
	 * 批量新增(供应商配送包含的商品信息)信息
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
	 * 根据Id 批量删除(供应商配送包含的商品信息)信息_逻辑删除
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
