package com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;

/**
 * 描述:(产品参数，临时存储从外部抓取的数据) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductParametersTempBaseDao {
	/**
	 * 根据条件查询(产品参数，临时存储从外部抓取的数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductParametersTemp> selectEbuyProductParametersTempByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(产品参数，临时存储从外部抓取的数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductParametersTemp> selectEbuyProductParametersTempByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(产品参数，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	public EbuyProductParametersTemp selectEbuyProductParametersTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品参数，临时存储从外部抓取的数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductParametersTempCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(产品参数，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductParametersTemp
	 * @return
	 */
	public int insertEbuyProductParametersTemp(EbuyProductParametersTemp ebuyProductParametersTemp);
	
	/**
	 * 批量新增(产品参数，临时存储从外部抓取的数据)信息
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
	 * 根据Id 批量删除(产品参数，临时存储从外部抓取的数据)信息_逻辑删除
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
