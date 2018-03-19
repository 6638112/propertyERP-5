package com.cnfantasia.server.domainbase.ebuyInvoice.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;

/**
 * 描述:(发票) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyInvoiceBaseDao {
	/**
	 * 根据条件查询(发票)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyInvoice> selectEbuyInvoiceByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(发票)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyInvoice> selectEbuyInvoiceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(发票)信息
	 * @param id
	 * @return
	 */
	public EbuyInvoice selectEbuyInvoiceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(发票)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyInvoiceCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(发票)新增一条记录
	 * @param ebuyInvoice
	 * @return
	 */
	public int insertEbuyInvoice(EbuyInvoice ebuyInvoice);
	
	/**
	 * 批量新增(发票)信息
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
	 * 根据Id 批量删除(发票)信息_逻辑删除
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
