package com.cnfantasia.server.domainbase.golfGroupDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.golfGroupDetail.dao.IGolfGroupDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail;

/**
 * 描述:(高尔夫组团详细表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GolfGroupDetailBaseService implements IGolfGroupDetailBaseService{
	
	private IGolfGroupDetailBaseDao golfGroupDetailBaseDao;
	public void setGolfGroupDetailBaseDao(IGolfGroupDetailBaseDao golfGroupDetailBaseDao) {
		this.golfGroupDetailBaseDao = golfGroupDetailBaseDao;
	}
	/**
	 * 根据条件查询(高尔夫组团详细表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> getGolfGroupDetailByCondition(Map<String,Object> paramMap){
		return golfGroupDetailBaseDao.selectGolfGroupDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(高尔夫组团详细表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> getGolfGroupDetailByConditionDim(Map<String,Object> paramMap){
		return golfGroupDetailBaseDao.selectGolfGroupDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> getGolfGroupDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return golfGroupDetailBaseDao.selectGolfGroupDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> getGolfGroupDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return golfGroupDetailBaseDao.selectGolfGroupDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(高尔夫组团详细表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GolfGroupDetail getGolfGroupDetailBySeqId(java.math.BigInteger id){
		return golfGroupDetailBaseDao.selectGolfGroupDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGolfGroupDetailCount(Map<String,Object> paramMap){
		return golfGroupDetailBaseDao.selectGolfGroupDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGolfGroupDetailCountDim(Map<String,Object> paramMap){
		return golfGroupDetailBaseDao.selectGolfGroupDetailCount(paramMap,true);
	}
	/**
	 * 往(高尔夫组团详细表)新增一条记录
	 * @param golfGroupDetail
	 * @return
	 */
	@Override
	public int insertGolfGroupDetail(GolfGroupDetail golfGroupDetail){
		return golfGroupDetailBaseDao.insertGolfGroupDetail(golfGroupDetail);
	}
	/**
	 * 批量新增(高尔夫组团详细表)
	 * @param golfGroupDetailList
	 * @return
	 */
	@Override
	public int insertGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList){
		return golfGroupDetailBaseDao.insertGolfGroupDetailBatch(golfGroupDetailList);
	}
	/**
	 * 更新(高尔夫组团详细表)信息
	 * @param golfGroupDetail
	 * @return
	 */
	@Override
	public int updateGolfGroupDetail(GolfGroupDetail golfGroupDetail){
		return golfGroupDetailBaseDao.updateGolfGroupDetail(golfGroupDetail);
	}
	/**
	 * 批量更新(高尔夫组团详细表)信息
	 * @param golfGroupDetailList
	 * @return
	 */
	@Override
	public int updateGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList){
		return golfGroupDetailBaseDao.updateGolfGroupDetailBatch(golfGroupDetailList);
	}
	/**
	 * 根据序列号删除(高尔夫组团详细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupDetailLogic(java.math.BigInteger id){
		return golfGroupDetailBaseDao.deleteGolfGroupDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(高尔夫组团详细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupDetailLogicBatch(List<java.math.BigInteger> idList){
		return golfGroupDetailBaseDao.deleteGolfGroupDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(高尔夫组团详细表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroupDetail(java.math.BigInteger id){
//		return golfGroupDetailBaseDao.deleteGolfGroupDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(高尔夫组团详细表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroupDetailBatch(List<java.math.BigInteger> idList){
//		return golfGroupDetailBaseDao.deleteGolfGroupDetailBatch(idList);
//	}
	
}
