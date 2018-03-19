package com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderHasTPayBillBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyOrderHasTPayBill getEbuyOrderHasTPayBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasTPayBillCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderHasTPayBillCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param ebuyOrderHasTPayBill
	 * @return
	 */
	public int insertEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill);
	/**
	 * 批量新增()
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
	 * 根据序列号批量删除()信息_逻辑删除
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
