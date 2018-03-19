package com.cnfantasia.server.domainbase.hobby.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.hobby.entity.Hobby;

/**
 * 描述:(兴趣爱好表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IHobbyBaseDao {
	/**
	 * 根据条件查询(兴趣爱好表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Hobby> selectHobbyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(兴趣爱好表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<Hobby> selectHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(兴趣爱好表)信息
	 * @param id
	 * @return
	 */
	public Hobby selectHobbyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectHobbyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(兴趣爱好表)新增一条记录
	 * @param hobby
	 * @return
	 */
	public int insertHobby(Hobby hobby);
	
	/**
	 * 批量新增(兴趣爱好表)信息
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
	 * 根据Id 批量删除(兴趣爱好表)信息_逻辑删除
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
