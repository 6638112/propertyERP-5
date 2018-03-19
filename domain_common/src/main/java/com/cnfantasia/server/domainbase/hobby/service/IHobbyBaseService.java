package com.cnfantasia.server.domainbase.hobby.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;

/**
 * 描述:(兴趣爱好表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IHobbyBaseService {
	
	/**
	 * 根据条件查询(兴趣爱好表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Hobby> getHobbyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(兴趣爱好表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Hobby> getHobbyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(兴趣爱好表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Hobby> getHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(兴趣爱好表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Hobby> getHobbyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(兴趣爱好表)信息
	 * @param id
	 * @return
	 */
	public Hobby getHobbyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getHobbyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getHobbyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(兴趣爱好表)新增一条记录
	 * @param hobby
	 * @return
	 */
	public int insertHobby(Hobby hobby);
	/**
	 * 批量新增(兴趣爱好表)
	 * @param hobbyList
	 * @return
	 */
	public int insertHobbyBatch(List<Hobby> hobbyList);
	/**
	 * 更新(兴趣爱好表)信息
	 * @param hobby
	 * @return
	 */
	public int updateHobby(Hobby hobby);
	/**
	 * 批量更新(兴趣爱好表)信息
	 * @param hobbyList
	 * @return
	 */
	public int updateHobbyBatch(List<Hobby> hobbyList);
	/**
	 * 根据序列号删除(兴趣爱好表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteHobbyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(兴趣爱好表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteHobbyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(兴趣爱好表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteHobby(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(兴趣爱好表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteHobbyBatch(List<java.math.BigInteger> idList);
	
}
