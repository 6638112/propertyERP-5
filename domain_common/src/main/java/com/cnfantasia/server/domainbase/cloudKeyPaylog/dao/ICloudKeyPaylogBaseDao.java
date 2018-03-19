package com.cnfantasia.server.domainbase.cloudKeyPaylog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyPaylog.entity.CloudKeyPaylog;

/**
 * 描述:(云钥匙付款记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyPaylogBaseDao {
	/**
	 * 根据条件查询(云钥匙付款记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyPaylog> selectCloudKeyPaylogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(云钥匙付款记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyPaylog> selectCloudKeyPaylogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(云钥匙付款记录)信息
	 * @param id
	 * @return
	 */
	public CloudKeyPaylog selectCloudKeyPaylogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCloudKeyPaylogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(云钥匙付款记录)新增一条记录
	 * @param cloudKeyPaylog
	 * @return
	 */
	public int insertCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog);
	
	/**
	 * 批量新增(云钥匙付款记录)信息
	 * @param cloudKeyPaylogList
	 * @return
	 */
	public int insertCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList);
	
	/**
	 * 更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylog
	 * @return
	 */
	public int updateCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog);
	
	/**
	 * 批量更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylogList
	 * @return
	 */
	public int updateCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList);
	
	/**
	 * 根据序列号删除(云钥匙付款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCloudKeyPaylogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(云钥匙付款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCloudKeyPaylogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(云钥匙付款记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCloudKeyPaylog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCloudKeyPaylogBatch(List<java.math.BigInteger> idList);
	
	
}
