package com.cnfantasia.server.domainbase.cloudKeyPaylog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cloudKeyPaylog.entity.CloudKeyPaylog;

/**
 * 描述:(云钥匙付款记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyPaylogBaseService {
	
	/**
	 * 根据条件查询(云钥匙付款记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyPaylog> getCloudKeyPaylogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(云钥匙付款记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyPaylog> getCloudKeyPaylogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(云钥匙付款记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyPaylog> getCloudKeyPaylogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(云钥匙付款记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyPaylog> getCloudKeyPaylogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(云钥匙付款记录)信息
	 * @param id
	 * @return
	 */
	public CloudKeyPaylog getCloudKeyPaylogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyPaylogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyPaylogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(云钥匙付款记录)新增一条记录
	 * @param cloudKeyPaylog
	 * @return
	 */
	public int insertCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog);
	/**
	 * 批量新增(云钥匙付款记录)
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
	 * 根据序列号批量删除(云钥匙付款记录)信息_逻辑删除
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
