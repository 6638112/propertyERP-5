package com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;

/**
 * 描述:(首页分类名称关联产品分类) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyHomeTypeHasProductBaseService {
	
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(首页分类名称关联产品分类)信息
	 * @param id
	 * @return
	 */
	public EbuyHomeTypeHasProduct getEbuyHomeTypeHasProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyHomeTypeHasProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyHomeTypeHasProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往(首页分类名称关联产品分类)新增一条记录
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	public int insertEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct);
	/**
	 * 批量新增(首页分类名称关联产品分类)
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
	 * 根据序列号批量删除(首页分类名称关联产品分类)信息_逻辑删除
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
