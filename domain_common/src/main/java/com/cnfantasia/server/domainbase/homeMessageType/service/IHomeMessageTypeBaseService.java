package com.cnfantasia.server.domainbase.homeMessageType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.homeMessageType.entity.HomeMessageType;

/**
 * 描述:(首页消息类型表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IHomeMessageTypeBaseService {
	
	/**
	 * 根据条件查询(首页消息类型表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<HomeMessageType> getHomeMessageTypeByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(首页消息类型表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<HomeMessageType> getHomeMessageTypeByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(首页消息类型表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<HomeMessageType> getHomeMessageTypeByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(首页消息类型表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<HomeMessageType> getHomeMessageTypeByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(首页消息类型表)信息
	 * @param id
	 * @return
	 */
	public HomeMessageType getHomeMessageTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getHomeMessageTypeCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getHomeMessageTypeCountDim(Map<String, Object> paramMap);
	/**
	 * 往(首页消息类型表)新增一条记录
	 * @param homeMessageType
	 * @return
	 */
	public int insertHomeMessageType(HomeMessageType homeMessageType);
	/**
	 * 批量新增(首页消息类型表)
	 * @param homeMessageTypeList
	 * @return
	 */
	public int insertHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList);
	/**
	 * 更新(首页消息类型表)信息
	 * @param homeMessageType
	 * @return
	 */
	public int updateHomeMessageType(HomeMessageType homeMessageType);
	/**
	 * 批量更新(首页消息类型表)信息
	 * @param homeMessageTypeList
	 * @return
	 */
	public int updateHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList);
	/**
	 * 根据序列号删除(首页消息类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteHomeMessageTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(首页消息类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteHomeMessageTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(首页消息类型表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteHomeMessageType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(首页消息类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteHomeMessageTypeBatch(List<java.math.BigInteger> idList);
	
}
