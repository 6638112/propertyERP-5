package com.cnfantasia.server.domainbase.dredgeProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;

/**
 * 描述:(维修服务商品表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeProductBaseService {
	
	/**
	 * 根据条件查询(维修服务商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeProduct> getDredgeProductByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修服务商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeProduct> getDredgeProductByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修服务商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeProduct> getDredgeProductByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修服务商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeProduct> getDredgeProductByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修服务商品表)信息
	 * @param id
	 * @return
	 */
	public DredgeProduct getDredgeProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeProductCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeProductCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修服务商品表)新增一条记录
	 * @param dredgeProduct
	 * @return
	 */
	public int insertDredgeProduct(DredgeProduct dredgeProduct);
	/**
	 * 批量新增(维修服务商品表)
	 * @param dredgeProductList
	 * @return
	 */
	public int insertDredgeProductBatch(List<DredgeProduct> dredgeProductList);
	/**
	 * 更新(维修服务商品表)信息
	 * @param dredgeProduct
	 * @return
	 */
	public int updateDredgeProduct(DredgeProduct dredgeProduct);
	/**
	 * 批量更新(维修服务商品表)信息
	 * @param dredgeProductList
	 * @return
	 */
	public int updateDredgeProductBatch(List<DredgeProduct> dredgeProductList);
	/**
	 * 根据序列号删除(维修服务商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(维修服务商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(维修服务商品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeProductBatch(List<java.math.BigInteger> idList);
	
}
