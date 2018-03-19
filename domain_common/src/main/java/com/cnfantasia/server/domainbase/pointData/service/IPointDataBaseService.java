package com.cnfantasia.server.domainbase.pointData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * 描述:(用户积分表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPointDataBaseService {
	
	/**
	 * 根据条件查询(用户积分表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PointData> getPointDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户积分表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PointData> getPointDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户积分表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PointData> getPointDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户积分表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PointData> getPointDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户积分表)信息
	 * @param id
	 * @return
	 */
	public PointData getPointDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户积分表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPointDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户积分表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPointDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户积分表)新增一条记录
	 * @param pointData
	 * @return
	 */
	public int insertPointData(PointData pointData);
	/**
	 * 批量新增(用户积分表)
	 * @param pointDataList
	 * @return
	 */
	public int insertPointDataBatch(List<PointData> pointDataList);
	/**
	 * 更新(用户积分表)信息
	 * @param pointData
	 * @return
	 */
	public int updatePointData(PointData pointData);
	/**
	 * 批量更新(用户积分表)信息
	 * @param pointDataList
	 * @return
	 */
	public int updatePointDataBatch(List<PointData> pointDataList);
	/**
	 * 根据序列号删除(用户积分表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePointDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户积分表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePointDataLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户积分表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePointData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户积分表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePointDataBatch(List<java.math.BigInteger> idList);
	
}
