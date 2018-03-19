package com.cnfantasia.server.domainbase.golfGroupDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail;

/**
 * 描述:(高尔夫组团详细表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGolfGroupDetailBaseDao {
	/**
	 * 根据条件查询(高尔夫组团详细表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GolfGroupDetail> selectGolfGroupDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GolfGroupDetail> selectGolfGroupDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(高尔夫组团详细表)信息
	 * @param id
	 * @return
	 */
	public GolfGroupDetail selectGolfGroupDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGolfGroupDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(高尔夫组团详细表)新增一条记录
	 * @param golfGroupDetail
	 * @return
	 */
	public int insertGolfGroupDetail(GolfGroupDetail golfGroupDetail);
	
	/**
	 * 批量新增(高尔夫组团详细表)信息
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
	 * 根据Id 批量删除(高尔夫组团详细表)信息_逻辑删除
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
