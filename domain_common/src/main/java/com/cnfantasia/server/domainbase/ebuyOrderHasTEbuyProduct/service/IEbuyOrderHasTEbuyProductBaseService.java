package com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;

/**
 * 描述:(订单商品关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasTEbuyProductBaseService {
	
	/**
	 * 根据条件查询(订单商品关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(订单商品关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(订单商品关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(订单商品关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(订单商品关系)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasTEbuyProduct getEbuyOrderHasTEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasTEbuyProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasTEbuyProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(订单商品关系)新增一条记录
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	public int insertEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct);
	/**
	 * 批量新增(订单商品关系)
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	public int insertEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList);
	/**
	 * 更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	public int updateEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct);
	/**
	 * 批量更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	public int updateEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList);
	/**
	 * 根据序列号删除(订单商品关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderHasTEbuyProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(订单商品关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(订单商品关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderHasTEbuyProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderHasTEbuyProductBatch(List<java.math.BigInteger> idList);
	
}
