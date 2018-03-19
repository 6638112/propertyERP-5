package com.cnfantasia.server.domainbase.ebuyProductSpec.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * 描述:(商品价格规格表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductSpecBaseDao {
	/**
	 * 根据条件查询(商品价格规格表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductSpec> selectEbuyProductSpecByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品价格规格表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductSpec> selectEbuyProductSpecByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品价格规格表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductSpec selectEbuyProductSpecBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品价格规格表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductSpecCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品价格规格表)新增一条记录
	 * @param ebuyProductSpec
	 * @return
	 */
	public int insertEbuyProductSpec(EbuyProductSpec ebuyProductSpec);
	
	/**
	 * 批量新增(商品价格规格表)信息
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
	 * 根据Id 批量删除(商品价格规格表)信息_逻辑删除
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
