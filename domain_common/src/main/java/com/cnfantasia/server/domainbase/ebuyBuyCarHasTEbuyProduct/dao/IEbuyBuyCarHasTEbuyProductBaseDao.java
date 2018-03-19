package com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyBuyCarHasTEbuyProductBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyBuyCarHasTEbuyProduct selectEbuyBuyCarHasTEbuyProductBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyBuyCarHasTEbuyProductCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	public int insertEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
