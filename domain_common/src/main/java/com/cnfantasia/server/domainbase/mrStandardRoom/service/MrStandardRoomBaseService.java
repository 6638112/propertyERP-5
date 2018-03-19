package com.cnfantasia.server.domainbase.mrStandardRoom.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrStandardRoom.dao.IMrStandardRoomBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;

/**
 * 描述:(抄表收费标准(房间)) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrStandardRoomBaseService implements IMrStandardRoomBaseService{
	
	private IMrStandardRoomBaseDao mrStandardRoomBaseDao;
	public void setMrStandardRoomBaseDao(IMrStandardRoomBaseDao mrStandardRoomBaseDao) {
		this.mrStandardRoomBaseDao = mrStandardRoomBaseDao;
	}
	/**
	 * 根据条件查询(抄表收费标准(房间))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardRoom> getMrStandardRoomByCondition(Map<String,Object> paramMap){
		return mrStandardRoomBaseDao.selectMrStandardRoomByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表收费标准(房间))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardRoom> getMrStandardRoomByConditionDim(Map<String,Object> paramMap){
		return mrStandardRoomBaseDao.selectMrStandardRoomByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表收费标准(房间))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardRoom> getMrStandardRoomByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardRoomBaseDao.selectMrStandardRoomByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表收费标准(房间))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardRoom> getMrStandardRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardRoomBaseDao.selectMrStandardRoomByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表收费标准(房间))信息
	 * @param id
	 * @return
	 */
	@Override
	public MrStandardRoom getMrStandardRoomBySeqId(java.math.BigInteger id){
		return mrStandardRoomBaseDao.selectMrStandardRoomBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(房间))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardRoomCount(Map<String,Object> paramMap){
		return mrStandardRoomBaseDao.selectMrStandardRoomCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(房间))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardRoomCountDim(Map<String,Object> paramMap){
		return mrStandardRoomBaseDao.selectMrStandardRoomCount(paramMap,true);
	}
	/**
	 * 往(抄表收费标准(房间))新增一条记录
	 * @param mrStandardRoom
	 * @return
	 */
	@Override
	public int insertMrStandardRoom(MrStandardRoom mrStandardRoom){
		return mrStandardRoomBaseDao.insertMrStandardRoom(mrStandardRoom);
	}
	/**
	 * 批量新增(抄表收费标准(房间))
	 * @param mrStandardRoomList
	 * @return
	 */
	@Override
	public int insertMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList){
		return mrStandardRoomBaseDao.insertMrStandardRoomBatch(mrStandardRoomList);
	}
	/**
	 * 更新(抄表收费标准(房间))信息
	 * @param mrStandardRoom
	 * @return
	 */
	@Override
	public int updateMrStandardRoom(MrStandardRoom mrStandardRoom){
		return mrStandardRoomBaseDao.updateMrStandardRoom(mrStandardRoom);
	}
	/**
	 * 批量更新(抄表收费标准(房间))信息
	 * @param mrStandardRoomList
	 * @return
	 */
	@Override
	public int updateMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList){
		return mrStandardRoomBaseDao.updateMrStandardRoomBatch(mrStandardRoomList);
	}
	/**
	 * 根据序列号删除(抄表收费标准(房间))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrStandardRoomLogic(java.math.BigInteger id){
		return mrStandardRoomBaseDao.deleteMrStandardRoomLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表收费标准(房间))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrStandardRoomLogicBatch(List<java.math.BigInteger> idList){
		return mrStandardRoomBaseDao.deleteMrStandardRoomLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表收费标准(房间))信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardRoom(java.math.BigInteger id){
//		return mrStandardRoomBaseDao.deleteMrStandardRoom(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(房间))信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardRoomBatch(List<java.math.BigInteger> idList){
//		return mrStandardRoomBaseDao.deleteMrStandardRoomBatch(idList);
//	}
	
}
