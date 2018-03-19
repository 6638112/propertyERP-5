package com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * 描述:(退货订单信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyRefundOrderProductBaseService {
	
	/**
	 * 根据条件查询(退货订单信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(退货订单信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(退货订单信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(退货订单信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(退货订单信息)信息
	 * @param id
	 * @return
	 */
	public EbuyRefundOrderProduct getEbuyRefundOrderProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundOrderProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundOrderProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(退货订单信息)新增一条记录
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	public int insertEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct);
	/**
	 * 批量新增(退货订单信息)
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	public int insertEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList);
	/**
	 * 更新(退货订单信息)信息
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	public int updateEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct);
	/**
	 * 批量更新(退货订单信息)信息
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	public int updateEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList);
	/**
	 * 根据序列号删除(退货订单信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyRefundOrderProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(退货订单信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyRefundOrderProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(退货订单信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyRefundOrderProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(退货订单信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyRefundOrderProductBatch(List<java.math.BigInteger> idList);
	
}
