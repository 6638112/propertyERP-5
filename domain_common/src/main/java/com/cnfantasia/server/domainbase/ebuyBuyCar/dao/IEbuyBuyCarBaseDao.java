package com.cnfantasia.server.domainbase.ebuyBuyCar.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;

/**
 * 描述:(购物车) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyBuyCarBaseDao {
	/**
	 * 根据条件查询(购物车)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyBuyCar> selectEbuyBuyCarByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(购物车)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyBuyCar> selectEbuyBuyCarByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(购物车)信息
	 * @param id
	 * @return
	 */
	public EbuyBuyCar selectEbuyBuyCarBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(购物车)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyBuyCarCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(购物车)新增一条记录
	 * @param ebuyBuyCar
	 * @return
	 */
	public int insertEbuyBuyCar(EbuyBuyCar ebuyBuyCar);
	
	/**
	 * 批量新增(购物车)信息
	 * @param ebuyBuyCarList
	 * @return
	 */
	public int insertEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList);
	
	/**
	 * 更新(购物车)信息
	 * @param ebuyBuyCar
	 * @return
	 */
	public int updateEbuyBuyCar(EbuyBuyCar ebuyBuyCar);
	
	/**
	 * 批量更新(购物车)信息
	 * @param ebuyBuyCarList
	 * @return
	 */
	public int updateEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList);
	
	/**
	 * 根据序列号删除(购物车)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyBuyCarLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(购物车)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyBuyCarLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(购物车)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyBuyCar(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(购物车)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyBuyCarBatch(List<java.math.BigInteger> idList);
	
	
}
