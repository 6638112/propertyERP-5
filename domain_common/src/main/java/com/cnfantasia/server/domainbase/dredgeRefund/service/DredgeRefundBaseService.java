package com.cnfantasia.server.domainbase.dredgeRefund.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeRefund.dao.IDredgeRefundBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeRefund.entity.DredgeRefund;

/**
 * 描述:(到家服务退款申请) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeRefundBaseService implements IDredgeRefundBaseService{
	
	private IDredgeRefundBaseDao dredgeRefundBaseDao;
	public void setDredgeRefundBaseDao(IDredgeRefundBaseDao dredgeRefundBaseDao) {
		this.dredgeRefundBaseDao = dredgeRefundBaseDao;
	}
	/**
	 * 根据条件查询(到家服务退款申请)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeRefund> getDredgeRefundByCondition(Map<String,Object> paramMap){
		return dredgeRefundBaseDao.selectDredgeRefundByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(到家服务退款申请)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeRefund> getDredgeRefundByConditionDim(Map<String,Object> paramMap){
		return dredgeRefundBaseDao.selectDredgeRefundByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(到家服务退款申请)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeRefund> getDredgeRefundByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeRefundBaseDao.selectDredgeRefundByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(到家服务退款申请)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeRefund> getDredgeRefundByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeRefundBaseDao.selectDredgeRefundByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(到家服务退款申请)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeRefund getDredgeRefundBySeqId(java.math.BigInteger id){
		return dredgeRefundBaseDao.selectDredgeRefundBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(到家服务退款申请)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeRefundCount(Map<String,Object> paramMap){
		return dredgeRefundBaseDao.selectDredgeRefundCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(到家服务退款申请)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeRefundCountDim(Map<String,Object> paramMap){
		return dredgeRefundBaseDao.selectDredgeRefundCount(paramMap,true);
	}
	/**
	 * 往(到家服务退款申请)新增一条记录
	 * @param dredgeRefund
	 * @return
	 */
	@Override
	public int insertDredgeRefund(DredgeRefund dredgeRefund){
		return dredgeRefundBaseDao.insertDredgeRefund(dredgeRefund);
	}
	/**
	 * 批量新增(到家服务退款申请)
	 * @param dredgeRefundList
	 * @return
	 */
	@Override
	public int insertDredgeRefundBatch(List<DredgeRefund> dredgeRefundList){
		return dredgeRefundBaseDao.insertDredgeRefundBatch(dredgeRefundList);
	}
	/**
	 * 更新(到家服务退款申请)信息
	 * @param dredgeRefund
	 * @return
	 */
	@Override
	public int updateDredgeRefund(DredgeRefund dredgeRefund){
		return dredgeRefundBaseDao.updateDredgeRefund(dredgeRefund);
	}
	/**
	 * 批量更新(到家服务退款申请)信息
	 * @param dredgeRefundList
	 * @return
	 */
	@Override
	public int updateDredgeRefundBatch(List<DredgeRefund> dredgeRefundList){
		return dredgeRefundBaseDao.updateDredgeRefundBatch(dredgeRefundList);
	}
	/**
	 * 根据序列号删除(到家服务退款申请)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeRefundLogic(java.math.BigInteger id){
		return dredgeRefundBaseDao.deleteDredgeRefundLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(到家服务退款申请)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeRefundLogicBatch(List<java.math.BigInteger> idList){
		return dredgeRefundBaseDao.deleteDredgeRefundLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(到家服务退款申请)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeRefund(java.math.BigInteger id){
//		return dredgeRefundBaseDao.deleteDredgeRefund(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(到家服务退款申请)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeRefundBatch(List<java.math.BigInteger> idList){
//		return dredgeRefundBaseDao.deleteDredgeRefundBatch(idList);
//	}
	
}
