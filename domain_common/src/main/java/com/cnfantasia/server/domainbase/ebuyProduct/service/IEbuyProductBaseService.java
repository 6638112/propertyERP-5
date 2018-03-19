package com.cnfantasia.server.domainbase.ebuyProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * 描述:(商品表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductBaseService {
	
	/**
	 * 根据条件查询(商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProduct> getEbuyProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProduct> getEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品表)信息
	 * @param id
	 * @return
	 */
	public EbuyProduct getEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品表)新增一条记录
	 * @param ebuyProduct
	 * @return
	 */
	public int insertEbuyProduct(EbuyProduct ebuyProduct);
	/**
	 * 批量新增(商品表)
	 * @param ebuyProductList
	 * @return
	 */
	public int insertEbuyProductBatch(List<EbuyProduct> ebuyProductList);
	/**
	 * 更新(商品表)信息
	 * @param ebuyProduct
	 * @return
	 */
	public int updateEbuyProduct(EbuyProduct ebuyProduct);
	/**
	 * 批量更新(商品表)信息
	 * @param ebuyProductList
	 * @return
	 */
	public int updateEbuyProductBatch(List<EbuyProduct> ebuyProductList);
	/**
	 * 根据序列号删除(商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductBatch(List<java.math.BigInteger> idList);
	
}
