package com.cnfantasia.server.domainbase.realRoom.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * 描述:(真实房间) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealRoomBaseService implements IRealRoomBaseService{
	
	private IRealRoomBaseDao realRoomBaseDao;
	public void setRealRoomBaseDao(IRealRoomBaseDao realRoomBaseDao) {
		this.realRoomBaseDao = realRoomBaseDao;
	}
	/**
	 * 根据条件查询(真实房间)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoom> getRealRoomByCondition(Map<String,Object> paramMap){
		return realRoomBaseDao.selectRealRoomByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(真实房间)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoom> getRealRoomByConditionDim(Map<String,Object> paramMap){
		return realRoomBaseDao.selectRealRoomByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(真实房间)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoom> getRealRoomByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomBaseDao.selectRealRoomByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(真实房间)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoom> getRealRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomBaseDao.selectRealRoomByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(真实房间)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoom getRealRoomBySeqId(java.math.BigInteger id){
		return realRoomBaseDao.selectRealRoomBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(真实房间)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomCount(Map<String,Object> paramMap){
		return realRoomBaseDao.selectRealRoomCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(真实房间)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomCountDim(Map<String,Object> paramMap){
		return realRoomBaseDao.selectRealRoomCount(paramMap,true);
	}
	/**
	 * 往(真实房间)新增一条记录
	 * @param realRoom
	 * @return
	 */
	@Override
	public int insertRealRoom(RealRoom realRoom){
		return realRoomBaseDao.insertRealRoom(realRoom);
	}
	/**
	 * 批量新增(真实房间)
	 * @param realRoomList
	 * @return
	 */
	@Override
	public int insertRealRoomBatch(List<RealRoom> realRoomList){
		return realRoomBaseDao.insertRealRoomBatch(realRoomList);
	}
	/**
	 * 更新(真实房间)信息
	 * @param realRoom
	 * @return
	 */
	@Override
	public int updateRealRoom(RealRoom realRoom){
		return realRoomBaseDao.updateRealRoom(realRoom);
	}
	/**
	 * 批量更新(真实房间)信息
	 * @param realRoomList
	 * @return
	 */
	@Override
	public int updateRealRoomBatch(List<RealRoom> realRoomList){
		return realRoomBaseDao.updateRealRoomBatch(realRoomList);
	}
	/**
	 * 根据序列号删除(真实房间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomLogic(java.math.BigInteger id){
		return realRoomBaseDao.deleteRealRoomLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(真实房间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomLogicBatch(List<java.math.BigInteger> idList){
		return realRoomBaseDao.deleteRealRoomLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(真实房间)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoom(java.math.BigInteger id){
//		return realRoomBaseDao.deleteRealRoom(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(真实房间)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomBatch(List<java.math.BigInteger> idList){
//		return realRoomBaseDao.deleteRealRoomBatch(idList);
//	}
	
}
