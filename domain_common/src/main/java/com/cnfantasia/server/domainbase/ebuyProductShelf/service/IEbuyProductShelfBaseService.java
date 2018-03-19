package com.cnfantasia.server.domainbase.ebuyProductShelf.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

/**
 * 描述:(货架商品表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductShelfBaseService {
	
	/**
	 * 根据条件查询(货架商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductShelf> getEbuyProductShelfByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(货架商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductShelf> getEbuyProductShelfByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(货架商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductShelf> getEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(货架商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductShelf> getEbuyProductShelfByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(货架商品表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductShelf getEbuyProductShelfBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductShelfCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductShelfCountDim(Map<String,Object> paramMap);
	/**
	 * 往(货架商品表)新增一条记录
	 * @param ebuyProductShelf
	 * @return
	 */
	public int insertEbuyProductShelf(EbuyProductShelf ebuyProductShelf);
	/**
	 * 批量新增(货架商品表)
	 * @param ebuyProductShelfList
	 * @return
	 */
	public int insertEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList);
	/**
	 * 更新(货架商品表)信息
	 * @param ebuyProductShelf
	 * @return
	 */
	public int updateEbuyProductShelf(EbuyProductShelf ebuyProductShelf);
	/**
	 * 批量更新(货架商品表)信息
	 * @param ebuyProductShelfList
	 * @return
	 */
	public int updateEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList);
	/**
	 * 根据序列号删除(货架商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductShelfLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(货架商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(货架商品表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductShelf(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(货架商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductShelfBatch(List<java.math.BigInteger> idList);
	
}
