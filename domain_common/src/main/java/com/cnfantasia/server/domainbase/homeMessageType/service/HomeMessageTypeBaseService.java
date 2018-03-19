package com.cnfantasia.server.domainbase.homeMessageType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.homeMessageType.dao.IHomeMessageTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.homeMessageType.entity.HomeMessageType;

/**
 * 描述:(首页消息类型表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class HomeMessageTypeBaseService implements IHomeMessageTypeBaseService{
	
	private IHomeMessageTypeBaseDao homeMessageTypeBaseDao;
	public void setHomeMessageTypeBaseDao(IHomeMessageTypeBaseDao homeMessageTypeBaseDao) {
		this.homeMessageTypeBaseDao = homeMessageTypeBaseDao;
	}
	/**
	 * 根据条件查询(首页消息类型表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<HomeMessageType> getHomeMessageTypeByCondition(Map<String,Object> paramMap){
		return homeMessageTypeBaseDao.selectHomeMessageTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(首页消息类型表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<HomeMessageType> getHomeMessageTypeByConditionDim(Map<String,Object> paramMap){
		return homeMessageTypeBaseDao.selectHomeMessageTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(首页消息类型表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<HomeMessageType> getHomeMessageTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return homeMessageTypeBaseDao.selectHomeMessageTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(首页消息类型表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<HomeMessageType> getHomeMessageTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return homeMessageTypeBaseDao.selectHomeMessageTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(首页消息类型表)信息
	 * @param id
	 * @return
	 */
	@Override
	public HomeMessageType getHomeMessageTypeBySeqId(java.math.BigInteger id){
		return homeMessageTypeBaseDao.selectHomeMessageTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHomeMessageTypeCount(Map<String,Object> paramMap){
		return homeMessageTypeBaseDao.selectHomeMessageTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHomeMessageTypeCountDim(Map<String,Object> paramMap){
		return homeMessageTypeBaseDao.selectHomeMessageTypeCount(paramMap,true);
	}
	/**
	 * 往(首页消息类型表)新增一条记录
	 * @param homeMessageType
	 * @return
	 */
	@Override
	public int insertHomeMessageType(HomeMessageType homeMessageType){
		return homeMessageTypeBaseDao.insertHomeMessageType(homeMessageType);
	}
	/**
	 * 批量新增(首页消息类型表)
	 * @param homeMessageTypeList
	 * @return
	 */
	@Override
	public int insertHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList){
		return homeMessageTypeBaseDao.insertHomeMessageTypeBatch(homeMessageTypeList);
	}
	/**
	 * 更新(首页消息类型表)信息
	 * @param homeMessageType
	 * @return
	 */
	@Override
	public int updateHomeMessageType(HomeMessageType homeMessageType){
		return homeMessageTypeBaseDao.updateHomeMessageType(homeMessageType);
	}
	/**
	 * 批量更新(首页消息类型表)信息
	 * @param homeMessageTypeList
	 * @return
	 */
	@Override
	public int updateHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList){
		return homeMessageTypeBaseDao.updateHomeMessageTypeBatch(homeMessageTypeList);
	}
	/**
	 * 根据序列号删除(首页消息类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHomeMessageTypeLogic(java.math.BigInteger id){
		return homeMessageTypeBaseDao.deleteHomeMessageTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(首页消息类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHomeMessageTypeLogicBatch(List<java.math.BigInteger> idList){
		return homeMessageTypeBaseDao.deleteHomeMessageTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(首页消息类型表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHomeMessageType(java.math.BigInteger id){
//		return homeMessageTypeBaseDao.deleteHomeMessageType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页消息类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHomeMessageTypeBatch(List<java.math.BigInteger> idList){
//		return homeMessageTypeBaseDao.deleteHomeMessageTypeBatch(idList);
//	}
	
}
