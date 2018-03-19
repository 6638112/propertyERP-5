package com.cnfantasia.server.domainbase.ebuyProductType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

/**
 * 描述:(商品类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductTypeBaseService {
	
	/**
	 * 根据条件查询(商品类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductType> getEbuyProductTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductType> getEbuyProductTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductType> getEbuyProductTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductType> getEbuyProductTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品类别)信息
	 * @param id
	 * @return
	 */
	public EbuyProductType getEbuyProductTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品类别)新增一条记录
	 * @param ebuyProductType
	 * @return
	 */
	public int insertEbuyProductType(EbuyProductType ebuyProductType);
	/**
	 * 批量新增(商品类别)
	 * @param ebuyProductTypeList
	 * @return
	 */
	public int insertEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList);
	/**
	 * 更新(商品类别)信息
	 * @param ebuyProductType
	 * @return
	 */
	public int updateEbuyProductType(EbuyProductType ebuyProductType);
	/**
	 * 批量更新(商品类别)信息
	 * @param ebuyProductTypeList
	 * @return
	 */
	public int updateEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList);
	/**
	 * 根据序列号删除(商品类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductTypeBatch(List<java.math.BigInteger> idList);
	
}
