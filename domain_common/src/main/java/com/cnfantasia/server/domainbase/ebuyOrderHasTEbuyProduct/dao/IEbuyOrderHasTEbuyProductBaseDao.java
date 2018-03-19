package com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;

/**
 * 描述:(订单商品关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasTEbuyProductBaseDao {
	/**
	 * 根据条件查询(订单商品关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> selectEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(订单商品关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasTEbuyProduct> selectEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(订单商品关系)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasTEbuyProduct selectEbuyOrderHasTEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderHasTEbuyProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(订单商品关系)新增一条记录
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	public int insertEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct);
	
	/**
	 * 批量新增(订单商品关系)信息
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
	 * 根据Id 批量删除(订单商品关系)信息_逻辑删除
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
