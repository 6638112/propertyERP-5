package com.cnfantasia.server.domainbase.ebuyProductParametersTemp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;

/**
 * 描述:(产品参数，临时存储从外部抓取的数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductParametersTempBaseService {
	
	/**
	 * 根据条件查询(产品参数，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(产品参数，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(产品参数，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(产品参数，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(产品参数，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	public EbuyProductParametersTemp getEbuyProductParametersTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品参数，临时存储从外部抓取的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductParametersTempCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(产品参数，临时存储从外部抓取的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductParametersTempCountDim(Map<String,Object> paramMap);
	/**
	 * 往(产品参数，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductParametersTemp
	 * @return
	 */
	public int insertEbuyProductParametersTemp(EbuyProductParametersTemp ebuyProductParametersTemp);
	/**
	 * 批量新增(产品参数，临时存储从外部抓取的数据)
	 * @param ebuyProductParametersTempList
	 * @return
	 */
	public int insertEbuyProductParametersTempBatch(List<EbuyProductParametersTemp> ebuyProductParametersTempList);
	/**
	 * 更新(产品参数，临时存储从外部抓取的数据)信息
	 * @param ebuyProductParametersTemp
	 * @return
	 */
	public int updateEbuyProductParametersTemp(EbuyProductParametersTemp ebuyProductParametersTemp);
	/**
	 * 批量更新(产品参数，临时存储从外部抓取的数据)信息
	 * @param ebuyProductParametersTempList
	 * @return
	 */
	public int updateEbuyProductParametersTempBatch(List<EbuyProductParametersTemp> ebuyProductParametersTempList);
	/**
	 * 根据序列号删除(产品参数，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductParametersTempLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(产品参数，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductParametersTempLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(产品参数，临时存储从外部抓取的数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductParametersTemp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(产品参数，临时存储从外部抓取的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductParametersTempBatch(List<java.math.BigInteger> idList);
	
}
