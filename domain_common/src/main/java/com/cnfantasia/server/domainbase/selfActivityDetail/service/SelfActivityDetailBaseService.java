package com.cnfantasia.server.domainbase.selfActivityDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.selfActivityDetail.dao.ISelfActivityDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;

/**
 * 描述:(自定义活动详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SelfActivityDetailBaseService implements ISelfActivityDetailBaseService{
	
	private ISelfActivityDetailBaseDao selfActivityDetailBaseDao;
	public void setSelfActivityDetailBaseDao(ISelfActivityDetailBaseDao selfActivityDetailBaseDao) {
		this.selfActivityDetailBaseDao = selfActivityDetailBaseDao;
	}
	/**
	 * 根据条件查询(自定义活动详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> getSelfActivityDetailByCondition(Map<String,Object> paramMap){
		return selfActivityDetailBaseDao.selectSelfActivityDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(自定义活动详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> getSelfActivityDetailByConditionDim(Map<String,Object> paramMap){
		return selfActivityDetailBaseDao.selectSelfActivityDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(自定义活动详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> getSelfActivityDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return selfActivityDetailBaseDao.selectSelfActivityDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(自定义活动详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> getSelfActivityDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return selfActivityDetailBaseDao.selectSelfActivityDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(自定义活动详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public SelfActivityDetail getSelfActivityDetailBySeqId(java.math.BigInteger id){
		return selfActivityDetailBaseDao.selectSelfActivityDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(自定义活动详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSelfActivityDetailCount(Map<String,Object> paramMap){
		return selfActivityDetailBaseDao.selectSelfActivityDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(自定义活动详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSelfActivityDetailCountDim(Map<String,Object> paramMap){
		return selfActivityDetailBaseDao.selectSelfActivityDetailCount(paramMap,true);
	}
	/**
	 * 往(自定义活动详情)新增一条记录
	 * @param selfActivityDetail
	 * @return
	 */
	@Override
	public int insertSelfActivityDetail(SelfActivityDetail selfActivityDetail){
		return selfActivityDetailBaseDao.insertSelfActivityDetail(selfActivityDetail);
	}
	/**
	 * 批量新增(自定义活动详情)
	 * @param selfActivityDetailList
	 * @return
	 */
	@Override
	public int insertSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList){
		return selfActivityDetailBaseDao.insertSelfActivityDetailBatch(selfActivityDetailList);
	}
	/**
	 * 更新(自定义活动详情)信息
	 * @param selfActivityDetail
	 * @return
	 */
	@Override
	public int updateSelfActivityDetail(SelfActivityDetail selfActivityDetail){
		return selfActivityDetailBaseDao.updateSelfActivityDetail(selfActivityDetail);
	}
	/**
	 * 批量更新(自定义活动详情)信息
	 * @param selfActivityDetailList
	 * @return
	 */
	@Override
	public int updateSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList){
		return selfActivityDetailBaseDao.updateSelfActivityDetailBatch(selfActivityDetailList);
	}
	/**
	 * 根据序列号删除(自定义活动详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityDetailLogic(java.math.BigInteger id){
		return selfActivityDetailBaseDao.deleteSelfActivityDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(自定义活动详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityDetailLogicBatch(List<java.math.BigInteger> idList){
		return selfActivityDetailBaseDao.deleteSelfActivityDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(自定义活动详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityDetail(java.math.BigInteger id){
//		return selfActivityDetailBaseDao.deleteSelfActivityDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自定义活动详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityDetailBatch(List<java.math.BigInteger> idList){
//		return selfActivityDetailBaseDao.deleteSelfActivityDetailBatch(idList);
//	}
	
}
