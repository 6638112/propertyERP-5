package com.cnfantasia.server.domainbase.ebuyProductParameters.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;

/**
 * 描述:(产品参数) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductParametersBaseDao {
	/**
	 * 根据条件查询(产品参数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(产品参数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(产品参数)信息
	 * @param id
	 * @return
	 */
	public EbuyProductParameters selectEbuyProductParametersBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品参数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductParametersCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(产品参数)新增一条记录
	 * @param ebuyProductParameters
	 * @return
	 */
	public int insertEbuyProductParameters(EbuyProductParameters ebuyProductParameters);
	
	/**
	 * 批量新增(产品参数)信息
	 * @param ebuyProductParametersList
	 * @return
	 */
	public int insertEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList);
	
	/**
	 * 更新(产品参数)信息
	 * @param ebuyProductParameters
	 * @return
	 */
	public int updateEbuyProductParameters(EbuyProductParameters ebuyProductParameters);
	
	/**
	 * 批量更新(产品参数)信息
	 * @param ebuyProductParametersList
	 * @return
	 */
	public int updateEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList);
	
	/**
	 * 根据序列号删除(产品参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductParametersLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(产品参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductParametersLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(产品参数)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductParameters(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(产品参数)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductParametersBatch(List<java.math.BigInteger> idList);
	
	
}
