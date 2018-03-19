package com.cnfantasia.server.domainbase.redpointContent.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * 描述:(红点信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRedpointContentBaseService {
	
	/**
	 * 根据条件查询(红点信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointContent> getRedpointContentByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(红点信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointContent> getRedpointContentByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(红点信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointContent> getRedpointContentByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(红点信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointContent> getRedpointContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(红点信息表)信息
	 * @param id
	 * @return
	 */
	public RedpointContent getRedpointContentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(红点信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointContentCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(红点信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointContentCountDim(Map<String,Object> paramMap);
	/**
	 * 往(红点信息表)新增一条记录
	 * @param redpointContent
	 * @return
	 */
	public int insertRedpointContent(RedpointContent redpointContent);
	/**
	 * 批量新增(红点信息表)
	 * @param redpointContentList
	 * @return
	 */
	public int insertRedpointContentBatch(List<RedpointContent> redpointContentList);
	/**
	 * 更新(红点信息表)信息
	 * @param redpointContent
	 * @return
	 */
	public int updateRedpointContent(RedpointContent redpointContent);
	/**
	 * 批量更新(红点信息表)信息
	 * @param redpointContentList
	 * @return
	 */
	public int updateRedpointContentBatch(List<RedpointContent> redpointContentList);
	/**
	 * 根据序列号删除(红点信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRedpointContentLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(红点信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRedpointContentLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(红点信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRedpointContent(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(红点信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRedpointContentBatch(List<java.math.BigInteger> idList);
	
}
