package com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.dao.IRealRoomHasTPropertyProprietorBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.entity.RealRoomHasTPropertyProprietor;

/**
 * 描述:(房间业主信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealRoomHasTPropertyProprietorBaseService implements IRealRoomHasTPropertyProprietorBaseService{
	
	private IRealRoomHasTPropertyProprietorBaseDao realRoomHasTPropertyProprietorBaseDao;
	public void setRealRoomHasTPropertyProprietorBaseDao(IRealRoomHasTPropertyProprietorBaseDao realRoomHasTPropertyProprietorBaseDao) {
		this.realRoomHasTPropertyProprietorBaseDao = realRoomHasTPropertyProprietorBaseDao;
	}
	/**
	 * 根据条件查询(房间业主信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyProprietor> getRealRoomHasTPropertyProprietorByCondition(Map<String,Object> paramMap){
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间业主信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyProprietor> getRealRoomHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap){
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间业主信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyProprietor> getRealRoomHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间业主信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyProprietor> getRealRoomHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间业主信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoomHasTPropertyProprietor getRealRoomHasTPropertyProprietorBySeqId(java.math.BigInteger id){
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间业主信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasTPropertyProprietorCount(Map<String,Object> paramMap){
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间业主信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasTPropertyProprietorCountDim(Map<String,Object> paramMap){
		return realRoomHasTPropertyProprietorBaseDao.selectRealRoomHasTPropertyProprietorCount(paramMap,true);
	}
	/**
	 * 往(房间业主信息表)新增一条记录
	 * @param realRoomHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyProprietor(RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietor){
		return realRoomHasTPropertyProprietorBaseDao.insertRealRoomHasTPropertyProprietor(realRoomHasTPropertyProprietor);
	}
	/**
	 * 批量新增(房间业主信息表)
	 * @param realRoomHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyProprietorBatch(List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorList){
		return realRoomHasTPropertyProprietorBaseDao.insertRealRoomHasTPropertyProprietorBatch(realRoomHasTPropertyProprietorList);
	}
	/**
	 * 更新(房间业主信息表)信息
	 * @param realRoomHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyProprietor(RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietor){
		return realRoomHasTPropertyProprietorBaseDao.updateRealRoomHasTPropertyProprietor(realRoomHasTPropertyProprietor);
	}
	/**
	 * 批量更新(房间业主信息表)信息
	 * @param realRoomHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyProprietorBatch(List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorList){
		return realRoomHasTPropertyProprietorBaseDao.updateRealRoomHasTPropertyProprietorBatch(realRoomHasTPropertyProprietorList);
	}
	/**
	 * 根据序列号删除(房间业主信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyProprietorLogic(java.math.BigInteger id){
		return realRoomHasTPropertyProprietorBaseDao.deleteRealRoomHasTPropertyProprietorLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间业主信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyProprietorLogicBatch(List<java.math.BigInteger> idList){
		return realRoomHasTPropertyProprietorBaseDao.deleteRealRoomHasTPropertyProprietorLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间业主信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyProprietor(java.math.BigInteger id){
//		return realRoomHasTPropertyProprietorBaseDao.deleteRealRoomHasTPropertyProprietor(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间业主信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyProprietorBatch(List<java.math.BigInteger> idList){
//		return realRoomHasTPropertyProprietorBaseDao.deleteRealRoomHasTPropertyProprietorBatch(idList);
//	}
	
}
