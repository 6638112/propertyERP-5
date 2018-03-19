package com.cnfantasia.server.domainbase.pointData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.pointData.dao.IPointDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * 描述:(用户积分表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PointDataBaseService implements IPointDataBaseService{
	
	private IPointDataBaseDao pointDataBaseDao;
	public void setPointDataBaseDao(IPointDataBaseDao pointDataBaseDao) {
		this.pointDataBaseDao = pointDataBaseDao;
	}
	/**
	 * 根据条件查询(用户积分表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointData> getPointDataByCondition(Map<String,Object> paramMap){
		return pointDataBaseDao.selectPointDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户积分表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointData> getPointDataByConditionDim(Map<String,Object> paramMap){
		return pointDataBaseDao.selectPointDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户积分表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointData> getPointDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return pointDataBaseDao.selectPointDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户积分表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointData> getPointDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return pointDataBaseDao.selectPointDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户积分表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointData getPointDataBySeqId(java.math.BigInteger id){
		return pointDataBaseDao.selectPointDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户积分表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointDataCount(Map<String,Object> paramMap){
		return pointDataBaseDao.selectPointDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户积分表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointDataCountDim(Map<String,Object> paramMap){
		return pointDataBaseDao.selectPointDataCount(paramMap,true);
	}
	/**
	 * 往(用户积分表)新增一条记录
	 * @param pointData
	 * @return
	 */
	@Override
	public int insertPointData(PointData pointData){
		return pointDataBaseDao.insertPointData(pointData);
	}
	/**
	 * 批量新增(用户积分表)
	 * @param pointDataList
	 * @return
	 */
	@Override
	public int insertPointDataBatch(List<PointData> pointDataList){
		return pointDataBaseDao.insertPointDataBatch(pointDataList);
	}
	/**
	 * 更新(用户积分表)信息
	 * @param pointData
	 * @return
	 */
	@Override
	public int updatePointData(PointData pointData){
		return pointDataBaseDao.updatePointData(pointData);
	}
	/**
	 * 批量更新(用户积分表)信息
	 * @param pointDataList
	 * @return
	 */
	@Override
	public int updatePointDataBatch(List<PointData> pointDataList){
		return pointDataBaseDao.updatePointDataBatch(pointDataList);
	}
	/**
	 * 根据序列号删除(用户积分表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointDataLogic(java.math.BigInteger id){
		return pointDataBaseDao.deletePointDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户积分表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointDataLogicBatch(List<java.math.BigInteger> idList){
		return pointDataBaseDao.deletePointDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户积分表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointData(java.math.BigInteger id){
//		return pointDataBaseDao.deletePointData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户积分表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointDataBatch(List<java.math.BigInteger> idList){
//		return pointDataBaseDao.deletePointDataBatch(idList);
//	}
	
}
