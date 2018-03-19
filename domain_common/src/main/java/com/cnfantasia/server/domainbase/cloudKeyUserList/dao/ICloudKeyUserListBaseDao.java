package com.cnfantasia.server.domainbase.cloudKeyUserList.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserList.entity.CloudKeyUserList;

/**
 * 描述:(业主门禁密钥表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserListBaseDao {
	/**
	 * 根据条件查询(业主门禁密钥表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserList> selectCloudKeyUserListByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(业主门禁密钥表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserList> selectCloudKeyUserListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(业主门禁密钥表)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserList selectCloudKeyUserListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCloudKeyUserListCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(业主门禁密钥表)新增一条记录
	 * @param cloudKeyUserList
	 * @return
	 */
	public int insertCloudKeyUserList(CloudKeyUserList cloudKeyUserList);
	
	/**
	 * 批量新增(业主门禁密钥表)信息
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
	 * 根据Id 批量删除(业主门禁密钥表)信息_逻辑删除
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
