package com.cnfantasia.server.domainbase.ebuyInvoice.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;

/**
 * 描述:(发票) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyInvoiceBaseService {
	
	/**
	 * 根据条件查询(发票)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyInvoice> getEbuyInvoiceByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(发票)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyInvoice> getEbuyInvoiceByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(发票)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyInvoice> getEbuyInvoiceByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(发票)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyInvoice> getEbuyInvoiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(发票)信息
	 * @param id
	 * @return
	 */
	public EbuyInvoice getEbuyInvoiceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(发票)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyInvoiceCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(发票)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyInvoiceCountDim(Map<String,Object> paramMap);
	/**
	 * 往(发票)新增一条记录
	 * @param ebuyInvoice
	 * @return
	 */
	public int insertEbuyInvoice(EbuyInvoice ebuyInvoice);
	/**
	 * 批量新增(发票)
	 * @param ebuyInvoiceList
	 * @return
	 */
	public int insertEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList);
	/**
	 * 更新(发票)信息
	 * @param ebuyInvoice
	 * @return
	 */
	public int updateEbuyInvoice(EbuyInvoice ebuyInvoice);
	/**
	 * 批量更新(发票)信息
	 * @param ebuyInvoiceList
	 * @return
	 */
	public int updateEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList);
	/**
	 * 根据序列号删除(发票)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyInvoiceLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(发票)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyInvoiceLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(发票)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyInvoice(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(发票)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyInvoiceBatch(List<java.math.BigInteger> idList);
	
}
