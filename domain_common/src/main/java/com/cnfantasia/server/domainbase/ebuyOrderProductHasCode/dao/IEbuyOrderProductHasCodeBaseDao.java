package com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;

/**
 * 描述:(商品订单关系所包含的兑换码) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderProductHasCodeBaseDao {
	/**
	 * 根据条件查询(商品订单关系所包含的兑换码)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderProductHasCode> selectEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品订单关系所包含的兑换码)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderProductHasCode> selectEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品订单关系所包含的兑换码)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderProductHasCode selectEbuyOrderProductHasCodeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品订单关系所包含的兑换码)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderProductHasCodeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品订单关系所包含的兑换码)新增一条记录
	 * @param ebuyOrderProductHasCode
	 * @return
	 */
	public int insertEbuyOrderProductHasCode(EbuyOrderProductHasCode ebuyOrderProductHasCode);
	
	/**
	 * 批量新增(商品订单关系所包含的兑换码)信息
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
	 * 根据Id 批量删除(商品订单关系所包含的兑换码)信息_逻辑删除
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
