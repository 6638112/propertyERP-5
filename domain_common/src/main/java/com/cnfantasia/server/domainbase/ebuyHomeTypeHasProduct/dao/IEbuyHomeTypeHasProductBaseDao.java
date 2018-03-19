package com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;

/**
 * 描述:(首页分类名称关联产品分类) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyHomeTypeHasProductBaseDao {
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> selectEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> selectEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(首页分类名称关联产品分类)信息
	 * @param id
	 * @return
	 */
	public EbuyHomeTypeHasProduct selectEbuyHomeTypeHasProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyHomeTypeHasProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(首页分类名称关联产品分类)新增一条记录
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	public int insertEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct);
	
	/**
	 * 批量新增(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	public int insertEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList);
	
	/**
	 * 更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	public int updateEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct);
	
	/**
	 * 批量更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	public int updateEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList);
	
	/**
	 * 根据序列号删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyHomeTypeHasProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyHomeTypeHasProductLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(首页分类名称关联产品分类)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyHomeTypeHasProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(首页分类名称关联产品分类)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyHomeTypeHasProductBatch(List<java.math.BigInteger> idList);
	
	
}
