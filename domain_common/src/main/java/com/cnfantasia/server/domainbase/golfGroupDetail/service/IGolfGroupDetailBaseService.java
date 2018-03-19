package com.cnfantasia.server.domainbase.golfGroupDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail;

/**
 * 描述:(高尔夫组团详细表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGolfGroupDetailBaseService {
	
	/**
	 * 根据条件查询(高尔夫组团详细表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GolfGroupDetail> getGolfGroupDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(高尔夫组团详细表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GolfGroupDetail> getGolfGroupDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GolfGroupDetail> getGolfGroupDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GolfGroupDetail> getGolfGroupDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(高尔夫组团详细表)信息
	 * @param id
	 * @return
	 */
	public GolfGroupDetail getGolfGroupDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGolfGroupDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGolfGroupDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(高尔夫组团详细表)新增一条记录
	 * @param golfGroupDetail
	 * @return
	 */
	public int insertGolfGroupDetail(GolfGroupDetail golfGroupDetail);
	/**
	 * 批量新增(高尔夫组团详细表)
	 * @param golfGroupDetailList
	 * @return
	 */
	public int insertGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList);
	/**
	 * 更新(高尔夫组团详细表)信息
	 * @param golfGroupDetail
	 * @return
	 */
	public int updateGolfGroupDetail(GolfGroupDetail golfGroupDetail);
	/**
	 * 批量更新(高尔夫组团详细表)信息
	 * @param golfGroupDetailList
	 * @return
	 */
	public int updateGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList);
	/**
	 * 根据序列号删除(高尔夫组团详细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGolfGroupDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(高尔夫组团详细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGolfGroupDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(高尔夫组团详细表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGolfGroupDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(高尔夫组团详细表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGolfGroupDetailBatch(List<java.math.BigInteger> idList);
	
}
