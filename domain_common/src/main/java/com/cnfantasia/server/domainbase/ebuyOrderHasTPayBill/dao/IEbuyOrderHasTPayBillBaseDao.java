package com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasTPayBillBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> selectEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> selectEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasTPayBill selectEbuyOrderHasTPayBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderHasTPayBillCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param ebuyOrderHasTPayBill
	 * @return
	 */
	public int insertEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill);
	
	/**
	 * 批量新增()信息
	 * @param ebuyOrderHasTPayBillList
	 * @return
	 */
	public int insertEbuyOrderHasTPayBillBatch(List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList);
	
	/**
	 * 更新()信息
	 * @param ebuyOrderHasTPayBill
	 * @return
	 */
	public int updateEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill);
	
	/**
	 * 批量更新()信息
	 * @param ebuyOrderHasTPayBillList
	 * @return
	 */
	public int updateEbuyOrderHasTPayBillBatch(List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList);
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderHasTPayBillLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderHasTPayBillLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderHasTPayBill(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderHasTPayBillBatch(List<java.math.BigInteger> idList);
	
	
}
