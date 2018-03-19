package com.cnfantasia.server.domainbase.homeMessageType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.homeMessageType.entity.HomeMessageType;

/**
 * 描述:(首页消息类型表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IHomeMessageTypeBaseDao {
	/**
	 * 根据条件查询(首页消息类型表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<HomeMessageType> selectHomeMessageTypeByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(首页消息类型表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<HomeMessageType> selectHomeMessageTypeByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(首页消息类型表)信息
	 * @param id
	 * @return
	 */
	public HomeMessageType selectHomeMessageTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectHomeMessageTypeCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(首页消息类型表)新增一条记录
	 * @param homeMessageType
	 * @return
	 */
	public int insertHomeMessageType(HomeMessageType homeMessageType);
	
	/**
	 * 批量新增(首页消息类型表)信息
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
	 * 根据Id 批量删除(首页消息类型表)信息_逻辑删除
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
