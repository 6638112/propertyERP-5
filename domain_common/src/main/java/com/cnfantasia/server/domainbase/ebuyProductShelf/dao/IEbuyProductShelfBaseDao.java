package com.cnfantasia.server.domainbase.ebuyProductShelf.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

/**
 * 描述:(货架商品表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductShelfBaseDao {
	/**
	 * 根据条件查询(货架商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductShelf> selectEbuyProductShelfByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(货架商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductShelf> selectEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(货架商品表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductShelf selectEbuyProductShelfBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductShelfCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(货架商品表)新增一条记录
	 * @param ebuyProductShelf
	 * @return
	 */
	public int insertEbuyProductShelf(EbuyProductShelf ebuyProductShelf);
	
	/**
	 * 批量新增(货架商品表)信息
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
	 * 根据Id 批量删除(货架商品表)信息_逻辑删除
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
