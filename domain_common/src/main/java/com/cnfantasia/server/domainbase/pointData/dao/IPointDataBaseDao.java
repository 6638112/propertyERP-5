package com.cnfantasia.server.domainbase.pointData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * 描述:(用户积分表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPointDataBaseDao {
	/**
	 * 根据条件查询(用户积分表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PointData> selectPointDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户积分表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PointData> selectPointDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户积分表)信息
	 * @param id
	 * @return
	 */
	public PointData selectPointDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户积分表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPointDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户积分表)新增一条记录
	 * @param pointData
	 * @return
	 */
	public int insertPointData(PointData pointData);
	
	/**
	 * 批量新增(用户积分表)信息
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
	 * 根据Id 批量删除(用户积分表)信息_逻辑删除
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
