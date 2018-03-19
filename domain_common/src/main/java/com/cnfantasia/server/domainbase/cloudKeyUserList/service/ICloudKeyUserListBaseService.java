package com.cnfantasia.server.domainbase.cloudKeyUserList.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cloudKeyUserList.entity.CloudKeyUserList;

/**
 * 描述:(业主门禁密钥表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserListBaseService {
	
	/**
	 * 根据条件查询(业主门禁密钥表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserList> getCloudKeyUserListByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(业主门禁密钥表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserList> getCloudKeyUserListByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(业主门禁密钥表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserList> getCloudKeyUserListByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(业主门禁密钥表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserList> getCloudKeyUserListByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(业主门禁密钥表)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserList getCloudKeyUserListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserListCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserListCountDim(Map<String,Object> paramMap);
	/**
	 * 往(业主门禁密钥表)新增一条记录
	 * @param cloudKeyUserList
	 * @return
	 */
	public int insertCloudKeyUserList(CloudKeyUserList cloudKeyUserList);
	/**
	 * 批量新增(业主门禁密钥表)
	 * @param cloudKeyUserListList
	 * @return
	 */
	public int insertCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList);
	/**
	 * 更新(业主门禁密钥表)信息
	 * @param cloudKeyUserList
	 * @return
	 */
	public int updateCloudKeyUserList(CloudKeyUserList cloudKeyUserList);
	/**
	 * 批量更新(业主门禁密钥表)信息
	 * @param cloudKeyUserListList
	 * @return
	 */
	public int updateCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList);
	/**
	 * 根据序列号删除(业主门禁密钥表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCloudKeyUserListLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(业主门禁密钥表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCloudKeyUserListLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(业主门禁密钥表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCloudKeyUserList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(业主门禁密钥表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCloudKeyUserListBatch(List<java.math.BigInteger> idList);
	
}
