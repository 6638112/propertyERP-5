package com.cnfantasia.server.domainbase.cloudKeyUserList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cloudKeyUserList.dao.ICloudKeyUserListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserList.entity.CloudKeyUserList;

/**
 * 描述:(业主门禁密钥表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CloudKeyUserListBaseService implements ICloudKeyUserListBaseService{
	
	private ICloudKeyUserListBaseDao cloudKeyUserListBaseDao;
	public void setCloudKeyUserListBaseDao(ICloudKeyUserListBaseDao cloudKeyUserListBaseDao) {
		this.cloudKeyUserListBaseDao = cloudKeyUserListBaseDao;
	}
	/**
	 * 根据条件查询(业主门禁密钥表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> getCloudKeyUserListByCondition(Map<String,Object> paramMap){
		return cloudKeyUserListBaseDao.selectCloudKeyUserListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(业主门禁密钥表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> getCloudKeyUserListByConditionDim(Map<String,Object> paramMap){
		return cloudKeyUserListBaseDao.selectCloudKeyUserListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(业主门禁密钥表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> getCloudKeyUserListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserListBaseDao.selectCloudKeyUserListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(业主门禁密钥表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> getCloudKeyUserListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserListBaseDao.selectCloudKeyUserListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(业主门禁密钥表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserList getCloudKeyUserListBySeqId(java.math.BigInteger id){
		return cloudKeyUserListBaseDao.selectCloudKeyUserListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserListCount(Map<String,Object> paramMap){
		return cloudKeyUserListBaseDao.selectCloudKeyUserListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserListCountDim(Map<String,Object> paramMap){
		return cloudKeyUserListBaseDao.selectCloudKeyUserListCount(paramMap,true);
	}
	/**
	 * 往(业主门禁密钥表)新增一条记录
	 * @param cloudKeyUserList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserList(CloudKeyUserList cloudKeyUserList){
		return cloudKeyUserListBaseDao.insertCloudKeyUserList(cloudKeyUserList);
	}
	/**
	 * 批量新增(业主门禁密钥表)
	 * @param cloudKeyUserListList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList){
		return cloudKeyUserListBaseDao.insertCloudKeyUserListBatch(cloudKeyUserListList);
	}
	/**
	 * 更新(业主门禁密钥表)信息
	 * @param cloudKeyUserList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserList(CloudKeyUserList cloudKeyUserList){
		return cloudKeyUserListBaseDao.updateCloudKeyUserList(cloudKeyUserList);
	}
	/**
	 * 批量更新(业主门禁密钥表)信息
	 * @param cloudKeyUserListList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList){
		return cloudKeyUserListBaseDao.updateCloudKeyUserListBatch(cloudKeyUserListList);
	}
	/**
	 * 根据序列号删除(业主门禁密钥表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserListLogic(java.math.BigInteger id){
		return cloudKeyUserListBaseDao.deleteCloudKeyUserListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(业主门禁密钥表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserListLogicBatch(List<java.math.BigInteger> idList){
		return cloudKeyUserListBaseDao.deleteCloudKeyUserListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(业主门禁密钥表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserList(java.math.BigInteger id){
//		return cloudKeyUserListBaseDao.deleteCloudKeyUserList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁密钥表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserListBatch(List<java.math.BigInteger> idList){
//		return cloudKeyUserListBaseDao.deleteCloudKeyUserListBatch(idList);
//	}
	
}
