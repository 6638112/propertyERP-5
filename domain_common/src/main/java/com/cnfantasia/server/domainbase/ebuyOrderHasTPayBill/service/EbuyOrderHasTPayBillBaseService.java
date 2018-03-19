package com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.dao.IEbuyOrderHasTPayBillBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderHasTPayBillBaseService implements IEbuyOrderHasTPayBillBaseService{
	
	private IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao;
	public void setEbuyOrderHasTPayBillBaseDao(IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao) {
		this.ebuyOrderHasTPayBillBaseDao = ebuyOrderHasTPayBillBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap){
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderHasTPayBill getEbuyOrderHasTPayBillBySeqId(java.math.BigInteger id){
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasTPayBillCount(Map<String,Object> paramMap){
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasTPayBillCountDim(Map<String,Object> paramMap){
		return ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyOrderHasTPayBill
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill){
		return ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBill);
	}
	/**
	 * 批量新增()
	 * @param ebuyOrderHasTPayBillList
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTPayBillBatch(List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList){
		return ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBillBatch(ebuyOrderHasTPayBillList);
	}
	/**
	 * 更新()信息
	 * @param ebuyOrderHasTPayBill
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill){
		return ebuyOrderHasTPayBillBaseDao.updateEbuyOrderHasTPayBill(ebuyOrderHasTPayBill);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyOrderHasTPayBillList
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTPayBillBatch(List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList){
		return ebuyOrderHasTPayBillBaseDao.updateEbuyOrderHasTPayBillBatch(ebuyOrderHasTPayBillList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTPayBillLogic(java.math.BigInteger id){
		return ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBillLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTPayBillLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBillLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTPayBill(java.math.BigInteger id){
//		return ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBill(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTPayBillBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBillBatch(idList);
//	}
	
}
