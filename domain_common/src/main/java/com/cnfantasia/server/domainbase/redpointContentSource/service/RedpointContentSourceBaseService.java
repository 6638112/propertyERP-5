package com.cnfantasia.server.domainbase.redpointContentSource.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.redpointContentSource.dao.IRedpointContentSourceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;

/**
 * 描述:(红点来源表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RedpointContentSourceBaseService implements IRedpointContentSourceBaseService{
	
	private IRedpointContentSourceBaseDao redpointContentSourceBaseDao;
	public void setRedpointContentSourceBaseDao(IRedpointContentSourceBaseDao redpointContentSourceBaseDao) {
		this.redpointContentSourceBaseDao = redpointContentSourceBaseDao;
	}
	/**
	 * 根据条件查询(红点来源表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointContentSource> getRedpointContentSourceByCondition(Map<String,Object> paramMap){
		return redpointContentSourceBaseDao.selectRedpointContentSourceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(红点来源表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointContentSource> getRedpointContentSourceByConditionDim(Map<String,Object> paramMap){
		return redpointContentSourceBaseDao.selectRedpointContentSourceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(红点来源表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointContentSource> getRedpointContentSourceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointContentSourceBaseDao.selectRedpointContentSourceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(红点来源表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointContentSource> getRedpointContentSourceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointContentSourceBaseDao.selectRedpointContentSourceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(红点来源表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointContentSource getRedpointContentSourceBySeqId(java.math.BigInteger id){
		return redpointContentSourceBaseDao.selectRedpointContentSourceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointContentSourceCount(Map<String,Object> paramMap){
		return redpointContentSourceBaseDao.selectRedpointContentSourceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointContentSourceCountDim(Map<String,Object> paramMap){
		return redpointContentSourceBaseDao.selectRedpointContentSourceCount(paramMap,true);
	}
	/**
	 * 往(红点来源表)新增一条记录
	 * @param redpointContentSource
	 * @return
	 */
	@Override
	public int insertRedpointContentSource(RedpointContentSource redpointContentSource){
		return redpointContentSourceBaseDao.insertRedpointContentSource(redpointContentSource);
	}
	/**
	 * 批量新增(红点来源表)
	 * @param redpointContentSourceList
	 * @return
	 */
	@Override
	public int insertRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList){
		return redpointContentSourceBaseDao.insertRedpointContentSourceBatch(redpointContentSourceList);
	}
	/**
	 * 更新(红点来源表)信息
	 * @param redpointContentSource
	 * @return
	 */
	@Override
	public int updateRedpointContentSource(RedpointContentSource redpointContentSource){
		return redpointContentSourceBaseDao.updateRedpointContentSource(redpointContentSource);
	}
	/**
	 * 批量更新(红点来源表)信息
	 * @param redpointContentSourceList
	 * @return
	 */
	@Override
	public int updateRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList){
		return redpointContentSourceBaseDao.updateRedpointContentSourceBatch(redpointContentSourceList);
	}
	/**
	 * 根据序列号删除(红点来源表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentSourceLogic(java.math.BigInteger id){
		return redpointContentSourceBaseDao.deleteRedpointContentSourceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(红点来源表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentSourceLogicBatch(List<java.math.BigInteger> idList){
		return redpointContentSourceBaseDao.deleteRedpointContentSourceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(红点来源表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentSource(java.math.BigInteger id){
//		return redpointContentSourceBaseDao.deleteRedpointContentSource(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点来源表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentSourceBatch(List<java.math.BigInteger> idList){
//		return redpointContentSourceBaseDao.deleteRedpointContentSourceBatch(idList);
//	}
	
}
