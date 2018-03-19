package com.cnfantasia.server.domainbase.ebuyBuyCar.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;

/**
 * 描述:(购物车) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyBuyCarBaseService {
	
	/**
	 * 根据条件查询(购物车)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyBuyCar> getEbuyBuyCarByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(购物车)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyBuyCar> getEbuyBuyCarByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(购物车)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyBuyCar> getEbuyBuyCarByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(购物车)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyBuyCar> getEbuyBuyCarByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(购物车)信息
	 * @param id
	 * @return
	 */
	public EbuyBuyCar getEbuyBuyCarBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(购物车)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyBuyCarCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(购物车)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyBuyCarCountDim(Map<String,Object> paramMap);
	/**
	 * 往(购物车)新增一条记录
	 * @param ebuyBuyCar
	 * @return
	 */
	public int insertEbuyBuyCar(EbuyBuyCar ebuyBuyCar);
	/**
	 * 批量新增(购物车)
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
	 * 根据序列号批量删除(购物车)信息_逻辑删除
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
