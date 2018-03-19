package com.cnfantasia.server.domainbase.realroomSoftwareFee.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.realroomSoftwareFee.dao.IRealroomSoftwareFeeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareFee.entity.RealroomSoftwareFee;

/**
 * 描述:(查询的物业账单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealroomSoftwareFeeBaseService implements IRealroomSoftwareFeeBaseService{
	
	private IRealroomSoftwareFeeBaseDao realroomSoftwareFeeBaseDao;
	public void setRealroomSoftwareFeeBaseDao(IRealroomSoftwareFeeBaseDao realroomSoftwareFeeBaseDao) {
		this.realroomSoftwareFeeBaseDao = realroomSoftwareFeeBaseDao;
	}
	/**
	 * 根据条件查询(查询的物业账单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByCondition(Map<String,Object> paramMap){
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(查询的物业账单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByConditionDim(Map<String,Object> paramMap){
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(查询的物业账单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(查询的物业账单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(查询的物业账单)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealroomSoftwareFee getRealroomSoftwareFeeBySeqId(java.math.BigInteger id){
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealroomSoftwareFeeCount(Map<String,Object> paramMap){
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealroomSoftwareFeeCountDim(Map<String,Object> paramMap){
		return realroomSoftwareFeeBaseDao.selectRealroomSoftwareFeeCount(paramMap,true);
	}
	/**
	 * 往(查询的物业账单)新增一条记录
	 * @param realroomSoftwareFee
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee){
		return realroomSoftwareFeeBaseDao.insertRealroomSoftwareFee(realroomSoftwareFee);
	}
	/**
	 * 批量新增(查询的物业账单)
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList){
		return realroomSoftwareFeeBaseDao.insertRealroomSoftwareFeeBatch(realroomSoftwareFeeList);
	}
	/**
	 * 更新(查询的物业账单)信息
	 * @param realroomSoftwareFee
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee){
		return realroomSoftwareFeeBaseDao.updateRealroomSoftwareFee(realroomSoftwareFee);
	}
	/**
	 * 批量更新(查询的物业账单)信息
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList){
		return realroomSoftwareFeeBaseDao.updateRealroomSoftwareFeeBatch(realroomSoftwareFeeList);
	}
	/**
	 * 根据序列号删除(查询的物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareFeeLogic(java.math.BigInteger id){
		return realroomSoftwareFeeBaseDao.deleteRealroomSoftwareFeeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(查询的物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareFeeLogicBatch(List<java.math.BigInteger> idList){
		return realroomSoftwareFeeBaseDao.deleteRealroomSoftwareFeeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(查询的物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareFee(java.math.BigInteger id){
//		return realroomSoftwareFeeBaseDao.deleteRealroomSoftwareFee(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(查询的物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareFeeBatch(List<java.math.BigInteger> idList){
//		return realroomSoftwareFeeBaseDao.deleteRealroomSoftwareFeeBatch(idList);
//	}
	
}
