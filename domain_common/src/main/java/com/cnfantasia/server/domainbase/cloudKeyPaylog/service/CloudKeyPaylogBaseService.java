package com.cnfantasia.server.domainbase.cloudKeyPaylog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cloudKeyPaylog.dao.ICloudKeyPaylogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyPaylog.entity.CloudKeyPaylog;

/**
 * 描述:(云钥匙付款记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CloudKeyPaylogBaseService implements ICloudKeyPaylogBaseService{
	
	private ICloudKeyPaylogBaseDao cloudKeyPaylogBaseDao;
	public void setCloudKeyPaylogBaseDao(ICloudKeyPaylogBaseDao cloudKeyPaylogBaseDao) {
		this.cloudKeyPaylogBaseDao = cloudKeyPaylogBaseDao;
	}
	/**
	 * 根据条件查询(云钥匙付款记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> getCloudKeyPaylogByCondition(Map<String,Object> paramMap){
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(云钥匙付款记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> getCloudKeyPaylogByConditionDim(Map<String,Object> paramMap){
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(云钥匙付款记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> getCloudKeyPaylogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(云钥匙付款记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> getCloudKeyPaylogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(云钥匙付款记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyPaylog getCloudKeyPaylogBySeqId(java.math.BigInteger id){
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyPaylogCount(Map<String,Object> paramMap){
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyPaylogCountDim(Map<String,Object> paramMap){
		return cloudKeyPaylogBaseDao.selectCloudKeyPaylogCount(paramMap,true);
	}
	/**
	 * 往(云钥匙付款记录)新增一条记录
	 * @param cloudKeyPaylog
	 * @return
	 */
	@Override
	public int insertCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog){
		return cloudKeyPaylogBaseDao.insertCloudKeyPaylog(cloudKeyPaylog);
	}
	/**
	 * 批量新增(云钥匙付款记录)
	 * @param cloudKeyPaylogList
	 * @return
	 */
	@Override
	public int insertCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList){
		return cloudKeyPaylogBaseDao.insertCloudKeyPaylogBatch(cloudKeyPaylogList);
	}
	/**
	 * 更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylog
	 * @return
	 */
	@Override
	public int updateCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog){
		return cloudKeyPaylogBaseDao.updateCloudKeyPaylog(cloudKeyPaylog);
	}
	/**
	 * 批量更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylogList
	 * @return
	 */
	@Override
	public int updateCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList){
		return cloudKeyPaylogBaseDao.updateCloudKeyPaylogBatch(cloudKeyPaylogList);
	}
	/**
	 * 根据序列号删除(云钥匙付款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyPaylogLogic(java.math.BigInteger id){
		return cloudKeyPaylogBaseDao.deleteCloudKeyPaylogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(云钥匙付款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyPaylogLogicBatch(List<java.math.BigInteger> idList){
		return cloudKeyPaylogBaseDao.deleteCloudKeyPaylogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(云钥匙付款记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyPaylog(java.math.BigInteger id){
//		return cloudKeyPaylogBaseDao.deleteCloudKeyPaylog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyPaylogBatch(List<java.math.BigInteger> idList){
//		return cloudKeyPaylogBaseDao.deleteCloudKeyPaylogBatch(idList);
//	}
	
}
