package com.cnfantasia.server.domainbase.ebuyProductTemp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;

/**
 * 描述:(商品表，临时存储从外部抓取的数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductTempBaseService {
	
	/**
	 * 根据条件查询(商品表，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductTemp> getEbuyProductTempByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品表，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductTemp> getEbuyProductTempByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品表，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductTemp> getEbuyProductTempByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品表，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductTemp> getEbuyProductTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品表，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	public EbuyProductTemp getEbuyProductTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品表，临时存储从外部抓取的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductTempCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品表，临时存储从外部抓取的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductTempCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品表，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductTemp
	 * @return
	 */
	public int insertEbuyProductTemp(EbuyProductTemp ebuyProductTemp);
	/**
	 * 批量新增(商品表，临时存储从外部抓取的数据)
	 * @param ebuyProductTempList
	 * @return
	 */
	public int insertEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList);
	/**
	 * 更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTemp
	 * @return
	 */
	public int updateEbuyProductTemp(EbuyProductTemp ebuyProductTemp);
	/**
	 * 批量更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTempList
	 * @return
	 */
	public int updateEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList);
	/**
	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductTempLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductTempLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductTemp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductTempBatch(List<java.math.BigInteger> idList);
	
}
