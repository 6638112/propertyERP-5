package com.cnfantasia.server.domainbase.ebuyProductSpec.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * 描述:(商品价格规格表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductSpecBaseService {
	
	/**
	 * 根据条件查询(商品价格规格表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductSpec> getEbuyProductSpecByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品价格规格表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductSpec> getEbuyProductSpecByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品价格规格表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductSpec> getEbuyProductSpecByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品价格规格表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductSpec> getEbuyProductSpecByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品价格规格表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductSpec getEbuyProductSpecBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品价格规格表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductSpecCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品价格规格表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductSpecCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品价格规格表)新增一条记录
	 * @param ebuyProductSpec
	 * @return
	 */
	public int insertEbuyProductSpec(EbuyProductSpec ebuyProductSpec);
	/**
	 * 批量新增(商品价格规格表)
	 * @param ebuyProductSpecList
	 * @return
	 */
	public int insertEbuyProductSpecBatch(List<EbuyProductSpec> ebuyProductSpecList);
	/**
	 * 更新(商品价格规格表)信息
	 * @param ebuyProductSpec
	 * @return
	 */
	public int updateEbuyProductSpec(EbuyProductSpec ebuyProductSpec);
	/**
	 * 批量更新(商品价格规格表)信息
	 * @param ebuyProductSpecList
	 * @return
	 */
	public int updateEbuyProductSpecBatch(List<EbuyProductSpec> ebuyProductSpecList);
	/**
	 * 根据序列号删除(商品价格规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductSpecLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品价格规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductSpecLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品价格规格表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductSpec(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品价格规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductSpecBatch(List<java.math.BigInteger> idList);
	
}
