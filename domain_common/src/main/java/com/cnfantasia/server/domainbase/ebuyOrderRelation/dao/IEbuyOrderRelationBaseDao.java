package com.cnfantasia.server.domainbase.ebuyOrderRelation.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;

/**
 * 描述:(总订单与子订单关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderRelationBaseDao {
	/**
	 * 根据条件查询(总订单与子订单关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderRelation> selectEbuyOrderRelationByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(总订单与子订单关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderRelation> selectEbuyOrderRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(总订单与子订单关系表)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderRelation selectEbuyOrderRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(总订单与子订单关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderRelationCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(总订单与子订单关系表)新增一条记录
	 * @param ebuyOrderRelation
	 * @return
	 */
	public int insertEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation);
	
	/**
	 * 批量新增(总订单与子订单关系表)信息
	 * @param ebuyOrderRelationList
	 * @return
	 */
	public int insertEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList);
	
	/**
	 * 更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelation
	 * @return
	 */
	public int updateEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation);
	
	/**
	 * 批量更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelationList
	 * @return
	 */
	public int updateEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList);
	
	/**
	 * 根据序列号删除(总订单与子订单关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderRelationLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(总订单与子订单关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderRelationLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(总订单与子订单关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderRelation(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(总订单与子订单关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderRelationBatch(List<java.math.BigInteger> idList);
	
	
}
