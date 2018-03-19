package com.cnfantasia.server.domainbase.golfGroup.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.golfGroup.dao.IGolfGroupBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.golfGroup.entity.GolfGroup;

/**
 * 描述:(高尔夫组团表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GolfGroupBaseService implements IGolfGroupBaseService{
	
	private IGolfGroupBaseDao golfGroupBaseDao;
	public void setGolfGroupBaseDao(IGolfGroupBaseDao golfGroupBaseDao) {
		this.golfGroupBaseDao = golfGroupBaseDao;
	}
	/**
	 * 根据条件查询(高尔夫组团表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GolfGroup> getGolfGroupByCondition(Map<String,Object> paramMap){
		return golfGroupBaseDao.selectGolfGroupByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(高尔夫组团表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GolfGroup> getGolfGroupByConditionDim(Map<String,Object> paramMap){
		return golfGroupBaseDao.selectGolfGroupByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(高尔夫组团表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GolfGroup> getGolfGroupByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return golfGroupBaseDao.selectGolfGroupByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(高尔夫组团表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GolfGroup> getGolfGroupByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return golfGroupBaseDao.selectGolfGroupByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(高尔夫组团表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GolfGroup getGolfGroupBySeqId(java.math.BigInteger id){
		return golfGroupBaseDao.selectGolfGroupBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(高尔夫组团表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGolfGroupCount(Map<String,Object> paramMap){
		return golfGroupBaseDao.selectGolfGroupCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(高尔夫组团表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGolfGroupCountDim(Map<String,Object> paramMap){
		return golfGroupBaseDao.selectGolfGroupCount(paramMap,true);
	}
	/**
	 * 往(高尔夫组团表)新增一条记录
	 * @param golfGroup
	 * @return
	 */
	@Override
	public int insertGolfGroup(GolfGroup golfGroup){
		return golfGroupBaseDao.insertGolfGroup(golfGroup);
	}
	/**
	 * 批量新增(高尔夫组团表)
	 * @param golfGroupList
	 * @return
	 */
	@Override
	public int insertGolfGroupBatch(List<GolfGroup> golfGroupList){
		return golfGroupBaseDao.insertGolfGroupBatch(golfGroupList);
	}
	/**
	 * 更新(高尔夫组团表)信息
	 * @param golfGroup
	 * @return
	 */
	@Override
	public int updateGolfGroup(GolfGroup golfGroup){
		return golfGroupBaseDao.updateGolfGroup(golfGroup);
	}
	/**
	 * 批量更新(高尔夫组团表)信息
	 * @param golfGroupList
	 * @return
	 */
	@Override
	public int updateGolfGroupBatch(List<GolfGroup> golfGroupList){
		return golfGroupBaseDao.updateGolfGroupBatch(golfGroupList);
	}
	/**
	 * 根据序列号删除(高尔夫组团表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupLogic(java.math.BigInteger id){
		return golfGroupBaseDao.deleteGolfGroupLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(高尔夫组团表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupLogicBatch(List<java.math.BigInteger> idList){
		return golfGroupBaseDao.deleteGolfGroupLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(高尔夫组团表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroup(java.math.BigInteger id){
//		return golfGroupBaseDao.deleteGolfGroup(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(高尔夫组团表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroupBatch(List<java.math.BigInteger> idList){
//		return golfGroupBaseDao.deleteGolfGroupBatch(idList);
//	}
	
}
