package com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.entity.EbuyAdvertiseHasPromoteShelfProduct;

/**
 * 描述:(商品推广广告对应商品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyAdvertiseHasPromoteShelfProductBaseDao {
	/**
	 * 根据条件查询(商品推广广告对应商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyAdvertiseHasPromoteShelfProduct> selectEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(商品推广广告对应商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyAdvertiseHasPromoteShelfProduct> selectEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(商品推广广告对应商品表)信息
	 * @param id
	 * @return
	 */
	public EbuyAdvertiseHasPromoteShelfProduct selectEbuyAdvertiseHasPromoteShelfProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品推广广告对应商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyAdvertiseHasPromoteShelfProductCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(商品推广广告对应商品表)新增一条记录
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	public int insertEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct);
	
	/**
	 * 批量新增(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	public int insertEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList);
	
	/**
	 * 更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	public int updateEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct);
	
	/**
	 * 批量更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	public int updateEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList);
	
	/**
	 * 根据序列号删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(商品推广广告对应商品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyAdvertiseHasPromoteShelfProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品推广广告对应商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyAdvertiseHasPromoteShelfProductBatch(List<java.math.BigInteger> idList);
	
	
}
