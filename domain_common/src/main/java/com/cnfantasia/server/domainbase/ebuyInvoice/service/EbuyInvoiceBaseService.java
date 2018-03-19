package com.cnfantasia.server.domainbase.ebuyInvoice.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyInvoice.dao.IEbuyInvoiceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;

/**
 * 描述:(发票) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyInvoiceBaseService implements IEbuyInvoiceBaseService{
	
	private IEbuyInvoiceBaseDao ebuyInvoiceBaseDao;
	public void setEbuyInvoiceBaseDao(IEbuyInvoiceBaseDao ebuyInvoiceBaseDao) {
		this.ebuyInvoiceBaseDao = ebuyInvoiceBaseDao;
	}
	/**
	 * 根据条件查询(发票)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyInvoice> getEbuyInvoiceByCondition(Map<String,Object> paramMap){
		return ebuyInvoiceBaseDao.selectEbuyInvoiceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(发票)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyInvoice> getEbuyInvoiceByConditionDim(Map<String,Object> paramMap){
		return ebuyInvoiceBaseDao.selectEbuyInvoiceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(发票)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyInvoice> getEbuyInvoiceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyInvoiceBaseDao.selectEbuyInvoiceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(发票)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyInvoice> getEbuyInvoiceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyInvoiceBaseDao.selectEbuyInvoiceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(发票)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyInvoice getEbuyInvoiceBySeqId(java.math.BigInteger id){
		return ebuyInvoiceBaseDao.selectEbuyInvoiceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(发票)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyInvoiceCount(Map<String,Object> paramMap){
		return ebuyInvoiceBaseDao.selectEbuyInvoiceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(发票)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyInvoiceCountDim(Map<String,Object> paramMap){
		return ebuyInvoiceBaseDao.selectEbuyInvoiceCount(paramMap,true);
	}
	/**
	 * 往(发票)新增一条记录
	 * @param ebuyInvoice
	 * @return
	 */
	@Override
	public int insertEbuyInvoice(EbuyInvoice ebuyInvoice){
		return ebuyInvoiceBaseDao.insertEbuyInvoice(ebuyInvoice);
	}
	/**
	 * 批量新增(发票)
	 * @param ebuyInvoiceList
	 * @return
	 */
	@Override
	public int insertEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList){
		return ebuyInvoiceBaseDao.insertEbuyInvoiceBatch(ebuyInvoiceList);
	}
	/**
	 * 更新(发票)信息
	 * @param ebuyInvoice
	 * @return
	 */
	@Override
	public int updateEbuyInvoice(EbuyInvoice ebuyInvoice){
		return ebuyInvoiceBaseDao.updateEbuyInvoice(ebuyInvoice);
	}
	/**
	 * 批量更新(发票)信息
	 * @param ebuyInvoiceList
	 * @return
	 */
	@Override
	public int updateEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList){
		return ebuyInvoiceBaseDao.updateEbuyInvoiceBatch(ebuyInvoiceList);
	}
	/**
	 * 根据序列号删除(发票)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyInvoiceLogic(java.math.BigInteger id){
		return ebuyInvoiceBaseDao.deleteEbuyInvoiceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(发票)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyInvoiceLogicBatch(List<java.math.BigInteger> idList){
		return ebuyInvoiceBaseDao.deleteEbuyInvoiceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(发票)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyInvoice(java.math.BigInteger id){
//		return ebuyInvoiceBaseDao.deleteEbuyInvoice(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(发票)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyInvoiceBatch(List<java.math.BigInteger> idList){
//		return ebuyInvoiceBaseDao.deleteEbuyInvoiceBatch(idList);
//	}
	
}
