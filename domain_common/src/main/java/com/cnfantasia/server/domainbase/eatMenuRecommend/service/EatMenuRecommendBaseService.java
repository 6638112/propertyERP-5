package com.cnfantasia.server.domainbase.eatMenuRecommend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.eatMenuRecommend.dao.IEatMenuRecommendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenuRecommend.entity.EatMenuRecommend;

/**
 * 描述:(推荐菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EatMenuRecommendBaseService implements IEatMenuRecommendBaseService{
	
	private IEatMenuRecommendBaseDao eatMenuRecommendBaseDao;
	public void setEatMenuRecommendBaseDao(IEatMenuRecommendBaseDao eatMenuRecommendBaseDao) {
		this.eatMenuRecommendBaseDao = eatMenuRecommendBaseDao;
	}
	/**
	 * 根据条件查询(推荐菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> getEatMenuRecommendByCondition(Map<String,Object> paramMap){
		return eatMenuRecommendBaseDao.selectEatMenuRecommendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(推荐菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> getEatMenuRecommendByConditionDim(Map<String,Object> paramMap){
		return eatMenuRecommendBaseDao.selectEatMenuRecommendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(推荐菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> getEatMenuRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return eatMenuRecommendBaseDao.selectEatMenuRecommendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(推荐菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> getEatMenuRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return eatMenuRecommendBaseDao.selectEatMenuRecommendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(推荐菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public EatMenuRecommend getEatMenuRecommendBySeqId(java.math.BigInteger id){
		return eatMenuRecommendBaseDao.selectEatMenuRecommendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEatMenuRecommendCount(Map<String,Object> paramMap){
		return eatMenuRecommendBaseDao.selectEatMenuRecommendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEatMenuRecommendCountDim(Map<String,Object> paramMap){
		return eatMenuRecommendBaseDao.selectEatMenuRecommendCount(paramMap,true);
	}
	/**
	 * 往(推荐菜谱)新增一条记录
	 * @param eatMenuRecommend
	 * @return
	 */
	@Override
	public int insertEatMenuRecommend(EatMenuRecommend eatMenuRecommend){
		return eatMenuRecommendBaseDao.insertEatMenuRecommend(eatMenuRecommend);
	}
	/**
	 * 批量新增(推荐菜谱)
	 * @param eatMenuRecommendList
	 * @return
	 */
	@Override
	public int insertEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList){
		return eatMenuRecommendBaseDao.insertEatMenuRecommendBatch(eatMenuRecommendList);
	}
	/**
	 * 更新(推荐菜谱)信息
	 * @param eatMenuRecommend
	 * @return
	 */
	@Override
	public int updateEatMenuRecommend(EatMenuRecommend eatMenuRecommend){
		return eatMenuRecommendBaseDao.updateEatMenuRecommend(eatMenuRecommend);
	}
	/**
	 * 批量更新(推荐菜谱)信息
	 * @param eatMenuRecommendList
	 * @return
	 */
	@Override
	public int updateEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList){
		return eatMenuRecommendBaseDao.updateEatMenuRecommendBatch(eatMenuRecommendList);
	}
	/**
	 * 根据序列号删除(推荐菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEatMenuRecommendLogic(java.math.BigInteger id){
		return eatMenuRecommendBaseDao.deleteEatMenuRecommendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(推荐菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEatMenuRecommendLogicBatch(List<java.math.BigInteger> idList){
		return eatMenuRecommendBaseDao.deleteEatMenuRecommendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(推荐菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuRecommend(java.math.BigInteger id){
//		return eatMenuRecommendBaseDao.deleteEatMenuRecommend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(推荐菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuRecommendBatch(List<java.math.BigInteger> idList){
//		return eatMenuRecommendBaseDao.deleteEatMenuRecommendBatch(idList);
//	}
	
}
