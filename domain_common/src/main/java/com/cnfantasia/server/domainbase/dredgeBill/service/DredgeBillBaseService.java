package com.cnfantasia.server.domainbase.dredgeBill.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBill.dao.IDredgeBillBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

/**
 * 描述:(疏通工单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillBaseService implements IDredgeBillBaseService{
	
	private IDredgeBillBaseDao dredgeBillBaseDao;
	public void setDredgeBillBaseDao(IDredgeBillBaseDao dredgeBillBaseDao) {
		this.dredgeBillBaseDao = dredgeBillBaseDao;
	}
	/**
	 * 根据条件查询(疏通工单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBill> getDredgeBillByCondition(Map<String,Object> paramMap){
		return dredgeBillBaseDao.selectDredgeBillByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通工单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBill> getDredgeBillByConditionDim(Map<String,Object> paramMap){
		return dredgeBillBaseDao.selectDredgeBillByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通工单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBill> getDredgeBillByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillBaseDao.selectDredgeBillByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通工单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBill> getDredgeBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillBaseDao.selectDredgeBillByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通工单)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBill getDredgeBillBySeqId(java.math.BigInteger id){
		return dredgeBillBaseDao.selectDredgeBillBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillCount(Map<String,Object> paramMap){
		return dredgeBillBaseDao.selectDredgeBillCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillCountDim(Map<String,Object> paramMap){
		return dredgeBillBaseDao.selectDredgeBillCount(paramMap,true);
	}
	/**
	 * 往(疏通工单)新增一条记录
	 * @param dredgeBill
	 * @return
	 */
	@Override
	public int insertDredgeBill(DredgeBill dredgeBill){
		return dredgeBillBaseDao.insertDredgeBill(dredgeBill);
	}
	/**
	 * 批量新增(疏通工单)
	 * @param dredgeBillList
	 * @return
	 */
	@Override
	public int insertDredgeBillBatch(List<DredgeBill> dredgeBillList){
		return dredgeBillBaseDao.insertDredgeBillBatch(dredgeBillList);
	}
	/**
	 * 更新(疏通工单)信息
	 * @param dredgeBill
	 * @return
	 */
	@Override
	public int updateDredgeBill(DredgeBill dredgeBill){
		return dredgeBillBaseDao.updateDredgeBill(dredgeBill);
	}
	/**
	 * 批量更新(疏通工单)信息
	 * @param dredgeBillList
	 * @return
	 */
	@Override
	public int updateDredgeBillBatch(List<DredgeBill> dredgeBillList){
		return dredgeBillBaseDao.updateDredgeBillBatch(dredgeBillList);
	}
	/**
	 * 根据序列号删除(疏通工单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillLogic(java.math.BigInteger id){
		return dredgeBillBaseDao.deleteDredgeBillLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(疏通工单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillBaseDao.deleteDredgeBillLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(疏通工单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBill(java.math.BigInteger id){
//		return dredgeBillBaseDao.deleteDredgeBill(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通工单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillBatch(List<java.math.BigInteger> idList){
//		return dredgeBillBaseDao.deleteDredgeBillBatch(idList);
//	}
	
}
