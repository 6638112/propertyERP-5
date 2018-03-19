package com.cnfantasia.server.domainbase.userPayCount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserPayCountBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<UserPayCount> getUserPayCountByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<UserPayCount> getUserPayCountByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<UserPayCount> getUserPayCountByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<UserPayCount> getUserPayCountByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	UserPayCount getUserPayCountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getUserPayCountCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getUserPayCountCountDim(Map<String, Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param userPayCount
	 * @return
	 */
	int insertUserPayCount(UserPayCount userPayCount);
	/**
	 * 批量新增()
	 * @param userPayCountList
	 * @return
	 */
	int insertUserPayCountBatch(List<UserPayCount> userPayCountList);
	/**
	 * 更新()信息
	 * @param userPayCount
	 * @return
	 */
	int updateUserPayCount(UserPayCount userPayCount);
	/**
	 * 批量更新()信息
	 * @param userPayCountList
	 * @return
	 */
	int updateUserPayCountBatch(List<UserPayCount> userPayCountList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteUserPayCountLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteUserPayCountLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserPayCount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserPayCountBatch(List<java.math.BigInteger> idList);
	
}
