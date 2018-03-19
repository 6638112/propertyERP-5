package com.cnfantasia.server.domainbase.golfGroup.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.golfGroup.entity.GolfGroup;

/**
 * 描述:(高尔夫组团表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGolfGroupBaseService {
	
	/**
	 * 根据条件查询(高尔夫组团表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GolfGroup> getGolfGroupByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(高尔夫组团表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GolfGroup> getGolfGroupByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(高尔夫组团表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GolfGroup> getGolfGroupByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(高尔夫组团表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GolfGroup> getGolfGroupByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(高尔夫组团表)信息
	 * @param id
	 * @return
	 */
	public GolfGroup getGolfGroupBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(高尔夫组团表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGolfGroupCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(高尔夫组团表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGolfGroupCountDim(Map<String,Object> paramMap);
	/**
	 * 往(高尔夫组团表)新增一条记录
	 * @param golfGroup
	 * @return
	 */
	public int insertGolfGroup(GolfGroup golfGroup);
	/**
	 * 批量新增(高尔夫组团表)
	 * @param golfGroupList
	 * @return
	 */
	public int insertGolfGroupBatch(List<GolfGroup> golfGroupList);
	/**
	 * 更新(高尔夫组团表)信息
	 * @param golfGroup
	 * @return
	 */
	public int updateGolfGroup(GolfGroup golfGroup);
	/**
	 * 批量更新(高尔夫组团表)信息
	 * @param golfGroupList
	 * @return
	 */
	public int updateGolfGroupBatch(List<GolfGroup> golfGroupList);
	/**
	 * 根据序列号删除(高尔夫组团表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGolfGroupLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(高尔夫组团表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGolfGroupLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(高尔夫组团表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGolfGroup(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(高尔夫组团表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGolfGroupBatch(List<java.math.BigInteger> idList);
	
}
