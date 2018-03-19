package com.cnfantasia.server.domainbase.ebuyRefundAudit.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyRefundAudit.dao.IEbuyRefundAuditBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundAudit.entity.EbuyRefundAudit;

/**
 * 描述:(商品审核不通过原因) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyRefundAuditBaseService implements IEbuyRefundAuditBaseService{
	
	private IEbuyRefundAuditBaseDao ebuyRefundAuditBaseDao;
	public void setEbuyRefundAuditBaseDao(IEbuyRefundAuditBaseDao ebuyRefundAuditBaseDao) {
		this.ebuyRefundAuditBaseDao = ebuyRefundAuditBaseDao;
	}
	/**
	 * 根据条件查询(商品审核不通过原因)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> getEbuyRefundAuditByCondition(Map<String,Object> paramMap){
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品审核不通过原因)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> getEbuyRefundAuditByConditionDim(Map<String,Object> paramMap){
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品审核不通过原因)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> getEbuyRefundAuditByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品审核不通过原因)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> getEbuyRefundAuditByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品审核不通过原因)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundAudit getEbuyRefundAuditBySeqId(java.math.BigInteger id){
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundAuditCount(Map<String,Object> paramMap){
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundAuditCountDim(Map<String,Object> paramMap){
		return ebuyRefundAuditBaseDao.selectEbuyRefundAuditCount(paramMap,true);
	}
	/**
	 * 往(商品审核不通过原因)新增一条记录
	 * @param ebuyRefundAudit
	 * @return
	 */
	@Override
	public int insertEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit){
		return ebuyRefundAuditBaseDao.insertEbuyRefundAudit(ebuyRefundAudit);
	}
	/**
	 * 批量新增(商品审核不通过原因)
	 * @param ebuyRefundAuditList
	 * @return
	 */
	@Override
	public int insertEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList){
		return ebuyRefundAuditBaseDao.insertEbuyRefundAuditBatch(ebuyRefundAuditList);
	}
	/**
	 * 更新(商品审核不通过原因)信息
	 * @param ebuyRefundAudit
	 * @return
	 */
	@Override
	public int updateEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit){
		return ebuyRefundAuditBaseDao.updateEbuyRefundAudit(ebuyRefundAudit);
	}
	/**
	 * 批量更新(商品审核不通过原因)信息
	 * @param ebuyRefundAuditList
	 * @return
	 */
	@Override
	public int updateEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList){
		return ebuyRefundAuditBaseDao.updateEbuyRefundAuditBatch(ebuyRefundAuditList);
	}
	/**
	 * 根据序列号删除(商品审核不通过原因)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundAuditLogic(java.math.BigInteger id){
		return ebuyRefundAuditBaseDao.deleteEbuyRefundAuditLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品审核不通过原因)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundAuditLogicBatch(List<java.math.BigInteger> idList){
		return ebuyRefundAuditBaseDao.deleteEbuyRefundAuditLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品审核不通过原因)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundAudit(java.math.BigInteger id){
//		return ebuyRefundAuditBaseDao.deleteEbuyRefundAudit(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品审核不通过原因)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundAuditBatch(List<java.math.BigInteger> idList){
//		return ebuyRefundAuditBaseDao.deleteEbuyRefundAuditBatch(idList);
//	}
	
}
