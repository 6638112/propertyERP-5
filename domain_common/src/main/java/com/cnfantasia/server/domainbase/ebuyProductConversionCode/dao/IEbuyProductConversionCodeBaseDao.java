package com.cnfantasia.server.domainbase.ebuyProductConversionCode.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * 描述:(商品兑换码表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductConversionCodeBaseDao {
	/**
	 * 根据条件查询(商品兑换码表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductConversionCode> selectEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品兑换码表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductConversionCode> selectEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品兑换码表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductConversionCode selectEbuyProductConversionCodeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductConversionCodeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品兑换码表)新增一条记录
	 * @param ebuyProductConversionCode
	 * @return
	 */
	public int insertEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode);
	
	/**
	 * 批量新增(商品兑换码表)信息
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
	 * 根据Id 批量删除(商品兑换码表)信息_逻辑删除
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
