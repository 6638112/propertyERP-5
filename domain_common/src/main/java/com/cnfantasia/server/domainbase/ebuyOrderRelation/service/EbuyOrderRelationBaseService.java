package com.cnfantasia.server.domainbase.ebuyOrderRelation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderRelation.dao.IEbuyOrderRelationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;

/**
 * 描述:(总订单与子订单关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderRelationBaseService implements IEbuyOrderRelationBaseService{
	
	private IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao;
	public void setEbuyOrderRelationBaseDao(IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao) {
		this.ebuyOrderRelationBaseDao = ebuyOrderRelationBaseDao;
	}
	/**
	 * 根据条件查询(总订单与子订单关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> getEbuyOrderRelationByCondition(Map<String,Object> paramMap){
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(总订单与子订单关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> getEbuyOrderRelationByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(总订单与子订单关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> getEbuyOrderRelationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(总订单与子订单关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> getEbuyOrderRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(总订单与子订单关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderRelation getEbuyOrderRelationBySeqId(java.math.BigInteger id){
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(总订单与子订单关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderRelationCount(Map<String,Object> paramMap){
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(总订单与子订单关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderRelationCountDim(Map<String,Object> paramMap){
		return ebuyOrderRelationBaseDao.selectEbuyOrderRelationCount(paramMap,true);
	}
	/**
	 * 往(总订单与子订单关系表)新增一条记录
	 * @param ebuyOrderRelation
	 * @return
	 */
	@Override
	public int insertEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation){
		return ebuyOrderRelationBaseDao.insertEbuyOrderRelation(ebuyOrderRelation);
	}
	/**
	 * 批量新增(总订单与子订单关系表)
	 * @param ebuyOrderRelationList
	 * @return
	 */
	@Override
	public int insertEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList){
		return ebuyOrderRelationBaseDao.insertEbuyOrderRelationBatch(ebuyOrderRelationList);
	}
	/**
	 * 更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelation
	 * @return
	 */
	@Override
	public int updateEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation){
		return ebuyOrderRelationBaseDao.updateEbuyOrderRelation(ebuyOrderRelation);
	}
	/**
	 * 批量更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelationList
	 * @return
	 */
	@Override
	public int updateEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList){
		return ebuyOrderRelationBaseDao.updateEbuyOrderRelationBatch(ebuyOrderRelationList);
	}
	/**
	 * 根据序列号删除(总订单与子订单关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderRelationLogic(java.math.BigInteger id){
		return ebuyOrderRelationBaseDao.deleteEbuyOrderRelationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(总订单与子订单关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderRelationLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderRelationBaseDao.deleteEbuyOrderRelationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(总订单与子订单关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderRelation(java.math.BigInteger id){
//		return ebuyOrderRelationBaseDao.deleteEbuyOrderRelation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(总订单与子订单关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderRelationBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderRelationBaseDao.deleteEbuyOrderRelationBatch(idList);
//	}
	
}
