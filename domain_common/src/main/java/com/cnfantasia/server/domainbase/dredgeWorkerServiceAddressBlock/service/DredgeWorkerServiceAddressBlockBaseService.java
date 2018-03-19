package com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.dao.IDredgeWorkerServiceAddressBlockBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;

/**
 * 描述:(疏通师傅服务行政区) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeWorkerServiceAddressBlockBaseService implements IDredgeWorkerServiceAddressBlockBaseService{
	
	private IDredgeWorkerServiceAddressBlockBaseDao dredgeWorkerServiceAddressBlockBaseDao;
	public void setDredgeWorkerServiceAddressBlockBaseDao(IDredgeWorkerServiceAddressBlockBaseDao dredgeWorkerServiceAddressBlockBaseDao) {
		this.dredgeWorkerServiceAddressBlockBaseDao = dredgeWorkerServiceAddressBlockBaseDao;
	}
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap){
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByConditionDim(Map<String,Object> paramMap){
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(疏通师傅服务行政区)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerServiceAddressBlock getDredgeWorkerServiceAddressBlockBySeqId(java.math.BigInteger id){
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerServiceAddressBlockCount(Map<String,Object> paramMap){
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeWorkerServiceAddressBlockCountDim(Map<String,Object> paramMap){
		return dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockCount(paramMap,true);
	}
	/**
	 * 往(疏通师傅服务行政区)新增一条记录
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	@Override
	public int insertDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock){
		return dredgeWorkerServiceAddressBlockBaseDao.insertDredgeWorkerServiceAddressBlock(dredgeWorkerServiceAddressBlock);
	}
	/**
	 * 批量新增(疏通师傅服务行政区)
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList){
		return dredgeWorkerServiceAddressBlockBaseDao.insertDredgeWorkerServiceAddressBlockBatch(dredgeWorkerServiceAddressBlockList);
	}
	/**
	 * 更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	@Override
	public int updateDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock){
		return dredgeWorkerServiceAddressBlockBaseDao.updateDredgeWorkerServiceAddressBlock(dredgeWorkerServiceAddressBlock);
	}
	/**
	 * 批量更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList){
		return dredgeWorkerServiceAddressBlockBaseDao.updateDredgeWorkerServiceAddressBlockBatch(dredgeWorkerServiceAddressBlockList);
	}
	/**
	 * 根据序列号删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerServiceAddressBlockLogic(java.math.BigInteger id){
		return dredgeWorkerServiceAddressBlockBaseDao.deleteDredgeWorkerServiceAddressBlockLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerServiceAddressBlockLogicBatch(List<java.math.BigInteger> idList){
		return dredgeWorkerServiceAddressBlockBaseDao.deleteDredgeWorkerServiceAddressBlockLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通师傅服务行政区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerServiceAddressBlock(java.math.BigInteger id){
//		return dredgeWorkerServiceAddressBlockBaseDao.deleteDredgeWorkerServiceAddressBlock(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅服务行政区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerServiceAddressBlockBatch(List<java.math.BigInteger> idList){
//		return dredgeWorkerServiceAddressBlockBaseDao.deleteDredgeWorkerServiceAddressBlockBatch(idList);
//	}
	
}
