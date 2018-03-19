package com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyBuyCarHasTEbuyProductBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyBuyCarHasTEbuyProduct getEbuyBuyCarHasTEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyBuyCarHasTEbuyProductCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyBuyCarHasTEbuyProductCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	public int insertEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct);
	/**
	 * 批量新增()
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	public int insertEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList);
	/**
	 * 更新()信息
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	public int updateEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct);
	/**
	 * 批量更新()信息
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	public int updateEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyBuyCarHasTEbuyProductLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyBuyCarHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyBuyCarHasTEbuyProduct(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyBuyCarHasTEbuyProductBatch(List<java.math.BigInteger> idList);
	
}
