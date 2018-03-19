package com.cnfantasia.server.domainbase.commSysUrl.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commSysUrl.dao.ICommSysUrlBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;

/**
 * 描述:(基础的url信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommSysUrlBaseService implements ICommSysUrlBaseService{
	
	private ICommSysUrlBaseDao commSysUrlBaseDao;
	public void setCommSysUrlBaseDao(ICommSysUrlBaseDao commSysUrlBaseDao) {
		this.commSysUrlBaseDao = commSysUrlBaseDao;
	}
	/**
	 * 根据条件查询(基础的url信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSysUrl> getCommSysUrlByCondition(Map<String,Object> paramMap){
		return commSysUrlBaseDao.selectCommSysUrlByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(基础的url信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommSysUrl> getCommSysUrlByConditionDim(Map<String,Object> paramMap){
		return commSysUrlBaseDao.selectCommSysUrlByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(基础的url信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSysUrl> getCommSysUrlByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commSysUrlBaseDao.selectCommSysUrlByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(基础的url信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommSysUrl> getCommSysUrlByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commSysUrlBaseDao.selectCommSysUrlByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(基础的url信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSysUrl getCommSysUrlBySeqId(java.math.BigInteger id){
		return commSysUrlBaseDao.selectCommSysUrlBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSysUrlCount(Map<String,Object> paramMap){
		return commSysUrlBaseDao.selectCommSysUrlCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommSysUrlCountDim(Map<String,Object> paramMap){
		return commSysUrlBaseDao.selectCommSysUrlCount(paramMap,true);
	}
	/**
	 * 往(基础的url信息)新增一条记录
	 * @param commSysUrl
	 * @return
	 */
	@Override
	public int insertCommSysUrl(CommSysUrl commSysUrl){
		return commSysUrlBaseDao.insertCommSysUrl(commSysUrl);
	}
	/**
	 * 批量新增(基础的url信息)
	 * @param commSysUrlList
	 * @return
	 */
	@Override
	public int insertCommSysUrlBatch(List<CommSysUrl> commSysUrlList){
		return commSysUrlBaseDao.insertCommSysUrlBatch(commSysUrlList);
	}
	/**
	 * 更新(基础的url信息)信息
	 * @param commSysUrl
	 * @return
	 */
	@Override
	public int updateCommSysUrl(CommSysUrl commSysUrl){
		return commSysUrlBaseDao.updateCommSysUrl(commSysUrl);
	}
	/**
	 * 批量更新(基础的url信息)信息
	 * @param commSysUrlList
	 * @return
	 */
	@Override
	public int updateCommSysUrlBatch(List<CommSysUrl> commSysUrlList){
		return commSysUrlBaseDao.updateCommSysUrlBatch(commSysUrlList);
	}
	/**
	 * 根据序列号删除(基础的url信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSysUrlLogic(java.math.BigInteger id){
		return commSysUrlBaseDao.deleteCommSysUrlLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(基础的url信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSysUrlLogicBatch(List<java.math.BigInteger> idList){
		return commSysUrlBaseDao.deleteCommSysUrlLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(基础的url信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysUrl(java.math.BigInteger id){
//		return commSysUrlBaseDao.deleteCommSysUrl(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(基础的url信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysUrlBatch(List<java.math.BigInteger> idList){
//		return commSysUrlBaseDao.deleteCommSysUrlBatch(idList);
//	}
	
}
