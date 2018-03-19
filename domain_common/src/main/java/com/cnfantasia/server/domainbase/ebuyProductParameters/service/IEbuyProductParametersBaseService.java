package com.cnfantasia.server.domainbase.ebuyProductParameters.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;

/**
 * 描述:(产品参数) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductParametersBaseService {
	
	/**
	 * 根据条件查询(产品参数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductParameters> getEbuyProductParametersByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(产品参数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductParameters> getEbuyProductParametersByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(产品参数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductParameters> getEbuyProductParametersByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(产品参数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductParameters> getEbuyProductParametersByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(产品参数)信息
	 * @param id
	 * @return
	 */
	public EbuyProductParameters getEbuyProductParametersBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品参数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductParametersCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(产品参数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductParametersCountDim(Map<String,Object> paramMap);
	/**
	 * 往(产品参数)新增一条记录
	 * @param ebuyProductParameters
	 * @return
	 */
	public int insertEbuyProductParameters(EbuyProductParameters ebuyProductParameters);
	/**
	 * 批量新增(产品参数)
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
	 * 根据序列号批量删除(产品参数)信息_逻辑删除
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
