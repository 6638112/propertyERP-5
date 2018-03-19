package com.cnfantasia.server.domainbase.commSysPara.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commSysPara.dao.ICommSysParaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysPara.entity.CommSysPara;

/**
 * 描述:(系统参数) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommSysParaBaseService implements ICommSysParaBaseService{
	
	private ICommSysParaBaseDao commSysParaBaseDao;
	public void setCommSysParaBaseDao(ICommSysParaBaseDao commSysParaBaseDao) {
		this.commSysParaBaseDao = commSysParaBaseDao;
	}
	/**
	 * 根据条件查询(系统参数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSysPara> getCommSysParaByCondition(Map<String,Object> paramMap){
		return commSysParaBaseDao.selectCommSysParaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(系统参数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSysPara> getCommSysParaByConditionDim(Map<String,Object> paramMap){
		return commSysParaBaseDao.selectCommSysParaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(系统参数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSysPara> getCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commSysParaBaseDao.selectCommSysParaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(系统参数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSysPara> getCommSysParaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commSysParaBaseDao.selectCommSysParaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(系统参数)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSysPara getCommSysParaBySeqId(java.math.BigInteger id){
		return commSysParaBaseDao.selectCommSysParaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(系统参数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSysParaCount(Map<String,Object> paramMap){
		return commSysParaBaseDao.selectCommSysParaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(系统参数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSysParaCountDim(Map<String,Object> paramMap){
		return commSysParaBaseDao.selectCommSysParaCount(paramMap,true);
	}
	/**
	 * 往(系统参数)新增一条记录
	 * @param commSysPara
	 * @return
	 */
	@Override
	public int insertCommSysPara(CommSysPara commSysPara){
		return commSysParaBaseDao.insertCommSysPara(commSysPara);
	}
	/**
	 * 批量新增(系统参数)
	 * @param commSysParaList
	 * @return
	 */
	@Override
	public int insertCommSysParaBatch(List<CommSysPara> commSysParaList){
		return commSysParaBaseDao.insertCommSysParaBatch(commSysParaList);
	}
	/**
	 * 更新(系统参数)信息
	 * @param commSysPara
	 * @return
	 */
	@Override
	public int updateCommSysPara(CommSysPara commSysPara){
		return commSysParaBaseDao.updateCommSysPara(commSysPara);
	}
	/**
	 * 批量更新(系统参数)信息
	 * @param commSysParaList
	 * @return
	 */
	@Override
	public int updateCommSysParaBatch(List<CommSysPara> commSysParaList){
		return commSysParaBaseDao.updateCommSysParaBatch(commSysParaList);
	}
	/**
	 * 根据序列号删除(系统参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSysParaLogic(java.math.BigInteger id){
		return commSysParaBaseDao.deleteCommSysParaLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(系统参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSysParaLogicBatch(List<java.math.BigInteger> idList){
		return commSysParaBaseDao.deleteCommSysParaLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(系统参数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysPara(java.math.BigInteger id){
//		return commSysParaBaseDao.deleteCommSysPara(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(系统参数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysParaBatch(List<java.math.BigInteger> idList){
//		return commSysParaBaseDao.deleteCommSysParaBatch(idList);
//	}
	
}
