package com.cnfantasia.server.domainbase.realroomSoftwareMapper.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.realroomSoftwareMapper.dao.IRealroomSoftwareMapperBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareMapper.entity.RealroomSoftwareMapper;

/**
 * 描述:(房间与物业软件映射) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealroomSoftwareMapperBaseService implements IRealroomSoftwareMapperBaseService{
	
	private IRealroomSoftwareMapperBaseDao realroomSoftwareMapperBaseDao;
	public void setRealroomSoftwareMapperBaseDao(IRealroomSoftwareMapperBaseDao realroomSoftwareMapperBaseDao) {
		this.realroomSoftwareMapperBaseDao = realroomSoftwareMapperBaseDao;
	}
	/**
	 * 根据条件查询(房间与物业软件映射)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByCondition(Map<String,Object> paramMap){
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间与物业软件映射)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByConditionDim(Map<String,Object> paramMap){
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间与物业软件映射)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间与物业软件映射)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间与物业软件映射)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealroomSoftwareMapper getRealroomSoftwareMapperBySeqId(java.math.BigInteger id){
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealroomSoftwareMapperCount(Map<String,Object> paramMap){
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealroomSoftwareMapperCountDim(Map<String,Object> paramMap){
		return realroomSoftwareMapperBaseDao.selectRealroomSoftwareMapperCount(paramMap,true);
	}
	/**
	 * 往(房间与物业软件映射)新增一条记录
	 * @param realroomSoftwareMapper
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper){
		return realroomSoftwareMapperBaseDao.insertRealroomSoftwareMapper(realroomSoftwareMapper);
	}
	/**
	 * 批量新增(房间与物业软件映射)
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList){
		return realroomSoftwareMapperBaseDao.insertRealroomSoftwareMapperBatch(realroomSoftwareMapperList);
	}
	/**
	 * 更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapper
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper){
		return realroomSoftwareMapperBaseDao.updateRealroomSoftwareMapper(realroomSoftwareMapper);
	}
	/**
	 * 批量更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList){
		return realroomSoftwareMapperBaseDao.updateRealroomSoftwareMapperBatch(realroomSoftwareMapperList);
	}
	/**
	 * 根据序列号删除(房间与物业软件映射)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareMapperLogic(java.math.BigInteger id){
		return realroomSoftwareMapperBaseDao.deleteRealroomSoftwareMapperLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间与物业软件映射)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareMapperLogicBatch(List<java.math.BigInteger> idList){
		return realroomSoftwareMapperBaseDao.deleteRealroomSoftwareMapperLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间与物业软件映射)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareMapper(java.math.BigInteger id){
//		return realroomSoftwareMapperBaseDao.deleteRealroomSoftwareMapper(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间与物业软件映射)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareMapperBatch(List<java.math.BigInteger> idList){
//		return realroomSoftwareMapperBaseDao.deleteRealroomSoftwareMapperBatch(idList);
//	}
	
}
