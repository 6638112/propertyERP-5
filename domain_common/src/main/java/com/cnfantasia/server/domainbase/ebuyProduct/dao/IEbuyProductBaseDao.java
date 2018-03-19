package com.cnfantasia.server.domainbase.ebuyProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * 描述:(商品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductBaseDao {
	/**
	 * 根据条件查询(商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProduct> selectEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProduct> selectEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品表)信息
	 * @param id
	 * @return
	 */
	public EbuyProduct selectEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品表)新增一条记录
	 * @param ebuyProduct
	 * @return
	 */
	public int insertEbuyProduct(EbuyProduct ebuyProduct);
	
	/**
	 * 批量新增(商品表)信息
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
	 * 根据Id 批量删除(商品表)信息_逻辑删除
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
