package com.cnfantasia.server.domainbase.ebuyOrderExt.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderExt.entity.EbuyOrderExt;

/**
 * 描述:(订单扩展表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderExtBaseDao {
	/**
	 * 根据条件查询(订单扩展表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderExt> selectEbuyOrderExtByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(订单扩展表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderExt> selectEbuyOrderExtByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(订单扩展表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderExt selectEbuyOrderExtBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单扩展表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderExtCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(订单扩展表)新增一条记录
	 * @param ebuyOrderExt
	 * @return
	 */
	public int insertEbuyOrderExt(EbuyOrderExt ebuyOrderExt);
	
	/**
	 * 批量新增(订单扩展表)信息
	 * @param ebuyOrderExtList
	 * @return
	 */
	public int insertEbuyOrderExtBatch(List<EbuyOrderExt> ebuyOrderExtList);
	
	/**
	 * 更新(订单扩展表)信息
	 * @param ebuyOrderExt
	 * @return
	 */
	public int updateEbuyOrderExt(EbuyOrderExt ebuyOrderExt);
	
	/**
	 * 批量更新(订单扩展表)信息
	 * @param ebuyOrderExtList
	 * @return
	 */
	public int updateEbuyOrderExtBatch(List<EbuyOrderExt> ebuyOrderExtList);
	
	/**
	 * 根据序列号删除(订单扩展表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderExtLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(订单扩展表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderExtLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(订单扩展表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderExt(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单扩展表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderExtBatch(List<java.math.BigInteger> idList);
	
	
}
