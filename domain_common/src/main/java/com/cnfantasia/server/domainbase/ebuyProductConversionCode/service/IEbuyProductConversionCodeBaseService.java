package com.cnfantasia.server.domainbase.ebuyProductConversionCode.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * 描述:(商品兑换码表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductConversionCodeBaseService {
	
	/**
	 * 根据条件查询(商品兑换码表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品兑换码表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品兑换码表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品兑换码表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品兑换码表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductConversionCode getEbuyProductConversionCodeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductConversionCodeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductConversionCodeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品兑换码表)新增一条记录
	 * @param ebuyProductConversionCode
	 * @return
	 */
	public int insertEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode);
	/**
	 * 批量新增(商品兑换码表)
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	public int insertEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList);
	/**
	 * 更新(商品兑换码表)信息
	 * @param ebuyProductConversionCode
	 * @return
	 */
	public int updateEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode);
	/**
	 * 批量更新(商品兑换码表)信息
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	public int updateEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList);
	/**
	 * 根据序列号删除(商品兑换码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductConversionCodeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品兑换码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductConversionCodeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品兑换码表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductConversionCode(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品兑换码表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductConversionCodeBatch(List<java.math.BigInteger> idList);
	
}
