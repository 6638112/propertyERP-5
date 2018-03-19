package com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * 描述:(退货订单信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyRefundOrderProductBaseDao {
	/**
	 * 根据条件查询(退货订单信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyRefundOrderProduct> selectEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(退货订单信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyRefundOrderProduct> selectEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(退货订单信息)信息
	 * @param id
	 * @return
	 */
	public EbuyRefundOrderProduct selectEbuyRefundOrderProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyRefundOrderProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(退货订单信息)新增一条记录
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	public int insertEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct);
	
	/**
	 * 批量新增(退货订单信息)信息
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
	 * 根据Id 批量删除(退货订单信息)信息_逻辑删除
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
