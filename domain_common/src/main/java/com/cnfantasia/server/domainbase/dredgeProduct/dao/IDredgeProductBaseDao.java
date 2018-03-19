package com.cnfantasia.server.domainbase.dredgeProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;

/**
 * 描述:(维修服务商品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeProductBaseDao {
	/**
	 * 根据条件查询(维修服务商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeProduct> selectDredgeProductByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修服务商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeProduct> selectDredgeProductByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修服务商品表)信息
	 * @param id
	 * @return
	 */
	public DredgeProduct selectDredgeProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeProductCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修服务商品表)新增一条记录
	 * @param dredgeProduct
	 * @return
	 */
	public int insertDredgeProduct(DredgeProduct dredgeProduct);
	
	/**
	 * 批量新增(维修服务商品表)信息
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
	 * 根据Id 批量删除(维修服务商品表)信息_逻辑删除
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
