package com.cnfantasia.server.domainbase.ebuyProductType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

/**
 * 描述:(商品类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductTypeBaseDao {
	/**
	 * 根据条件查询(商品类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductType> selectEbuyProductTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductType> selectEbuyProductTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品类别)信息
	 * @param id
	 * @return
	 */
	public EbuyProductType selectEbuyProductTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品类别)新增一条记录
	 * @param ebuyProductType
	 * @return
	 */
	public int insertEbuyProductType(EbuyProductType ebuyProductType);
	
	/**
	 * 批量新增(商品类别)信息
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
	 * 根据Id 批量删除(商品类别)信息_逻辑删除
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
