package com.cnfantasia.server.domainbase.userPayCount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserPayCountBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<UserPayCount> selectUserPayCountByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<UserPayCount> selectUserPayCountByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	UserPayCount selectUserPayCountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectUserPayCountCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param userPayCount
	 * @return
	 */
	int insertUserPayCount(UserPayCount userPayCount);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
