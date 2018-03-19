package com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.dao.IFixedFeeItemHasRoomBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

/**
 * 描述:(固定收费项和房间关联信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FixedFeeItemHasRoomBaseService implements IFixedFeeItemHasRoomBaseService{
	
	private IFixedFeeItemHasRoomBaseDao fixedFeeItemHasRoomBaseDao;
	public void setFixedFeeItemHasRoomBaseDao(IFixedFeeItemHasRoomBaseDao fixedFeeItemHasRoomBaseDao) {
		this.fixedFeeItemHasRoomBaseDao = fixedFeeItemHasRoomBaseDao;
	}
	/**
	 * 根据条件查询(固定收费项和房间关联信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByCondition(Map<String,Object> paramMap){
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(固定收费项和房间关联信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByConditionDim(Map<String,Object> paramMap){
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(固定收费项和房间关联信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(固定收费项和房间关联信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(固定收费项和房间关联信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public FixedFeeItemHasRoom getFixedFeeItemHasRoomBySeqId(java.math.BigInteger id){
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(固定收费项和房间关联信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemHasRoomCount(Map<String,Object> paramMap){
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(固定收费项和房间关联信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemHasRoomCountDim(Map<String,Object> paramMap){
		return fixedFeeItemHasRoomBaseDao.selectFixedFeeItemHasRoomCount(paramMap,true);
	}
	/**
	 * 往(固定收费项和房间关联信息)新增一条记录
	 * @param fixedFeeItemHasRoom
	 * @return
	 */
	@Override
	public int insertFixedFeeItemHasRoom(FixedFeeItemHasRoom fixedFeeItemHasRoom){
		return fixedFeeItemHasRoomBaseDao.insertFixedFeeItemHasRoom(fixedFeeItemHasRoom);
	}
	/**
	 * 批量新增(固定收费项和房间关联信息)
	 * @param fixedFeeItemHasRoomList
	 * @return
	 */
	@Override
	public int insertFixedFeeItemHasRoomBatch(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList){
		return fixedFeeItemHasRoomBaseDao.insertFixedFeeItemHasRoomBatch(fixedFeeItemHasRoomList);
	}
	/**
	 * 更新(固定收费项和房间关联信息)信息
	 * @param fixedFeeItemHasRoom
	 * @return
	 */
	@Override
	public int updateFixedFeeItemHasRoom(FixedFeeItemHasRoom fixedFeeItemHasRoom){
		return fixedFeeItemHasRoomBaseDao.updateFixedFeeItemHasRoom(fixedFeeItemHasRoom);
	}
	/**
	 * 批量更新(固定收费项和房间关联信息)信息
	 * @param fixedFeeItemHasRoomList
	 * @return
	 */
	@Override
	public int updateFixedFeeItemHasRoomBatch(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList){
		return fixedFeeItemHasRoomBaseDao.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRoomList);
	}
	/**
	 * 根据序列号删除(固定收费项和房间关联信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemHasRoomLogic(java.math.BigInteger id){
		return fixedFeeItemHasRoomBaseDao.deleteFixedFeeItemHasRoomLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(固定收费项和房间关联信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemHasRoomLogicBatch(List<java.math.BigInteger> idList){
		return fixedFeeItemHasRoomBaseDao.deleteFixedFeeItemHasRoomLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(固定收费项和房间关联信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemHasRoom(java.math.BigInteger id){
//		return fixedFeeItemHasRoomBaseDao.deleteFixedFeeItemHasRoom(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(固定收费项和房间关联信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemHasRoomBatch(List<java.math.BigInteger> idList){
//		return fixedFeeItemHasRoomBaseDao.deleteFixedFeeItemHasRoomBatch(idList);
//	}
	
}
