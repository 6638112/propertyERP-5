package com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;

/**
 * 描述:(商品订单关系所包含的兑换码) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderProductHasCodeBaseService {
	
	/**
	 * 根据条件查询(商品订单关系所包含的兑换码)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品订单关系所包含的兑换码)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品订单关系所包含的兑换码)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品订单关系所包含的兑换码)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品订单关系所包含的兑换码)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderProductHasCode getEbuyOrderProductHasCodeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品订单关系所包含的兑换码)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderProductHasCodeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品订单关系所包含的兑换码)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderProductHasCodeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品订单关系所包含的兑换码)新增一条记录
	 * @param ebuyOrderProductHasCode
	 * @return
	 */
	public int insertEbuyOrderProductHasCode(EbuyOrderProductHasCode ebuyOrderProductHasCode);
	/**
	 * 批量新增(商品订单关系所包含的兑换码)
	 * @param ebuyOrderProductHasCodeList
	 * @return
	 */
	public int insertEbuyOrderProductHasCodeBatch(List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList);
	/**
	 * 更新(商品订单关系所包含的兑换码)信息
	 * @param ebuyOrderProductHasCode
	 * @return
	 */
	public int updateEbuyOrderProductHasCode(EbuyOrderProductHasCode ebuyOrderProductHasCode);
	/**
	 * 批量更新(商品订单关系所包含的兑换码)信息
	 * @param ebuyOrderProductHasCodeList
	 * @return
	 */
	public int updateEbuyOrderProductHasCodeBatch(List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList);
	/**
	 * 根据序列号删除(商品订单关系所包含的兑换码)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderProductHasCodeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品订单关系所包含的兑换码)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderProductHasCodeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品订单关系所包含的兑换码)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderProductHasCode(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品订单关系所包含的兑换码)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderProductHasCodeBatch(List<java.math.BigInteger> idList);
	
}
