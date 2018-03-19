package com.cnfantasia.server.domainbase.ebuyProductRecommend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductRecommend.dao.IEbuyProductRecommendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductRecommend.entity.EbuyProductRecommend;

/**
 * 描述:(推荐的商品) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductRecommendBaseService implements IEbuyProductRecommendBaseService{
	
	private IEbuyProductRecommendBaseDao ebuyProductRecommendBaseDao;
	public void setEbuyProductRecommendBaseDao(IEbuyProductRecommendBaseDao ebuyProductRecommendBaseDao) {
		this.ebuyProductRecommendBaseDao = ebuyProductRecommendBaseDao;
	}
	/**
	 * 根据条件查询(推荐的商品)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductRecommend> getEbuyProductRecommendByCondition(Map<String,Object> paramMap){
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(推荐的商品)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductRecommend> getEbuyProductRecommendByConditionDim(Map<String,Object> paramMap){
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(推荐的商品)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductRecommend> getEbuyProductRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(推荐的商品)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductRecommend> getEbuyProductRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(推荐的商品)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductRecommend getEbuyProductRecommendBySeqId(java.math.BigInteger id){
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(推荐的商品)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductRecommendCount(Map<String,Object> paramMap){
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(推荐的商品)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductRecommendCountDim(Map<String,Object> paramMap){
		return ebuyProductRecommendBaseDao.selectEbuyProductRecommendCount(paramMap,true);
	}
	/**
	 * 往(推荐的商品)新增一条记录
	 * @param ebuyProductRecommend
	 * @return
	 */
	@Override
	public int insertEbuyProductRecommend(EbuyProductRecommend ebuyProductRecommend){
		return ebuyProductRecommendBaseDao.insertEbuyProductRecommend(ebuyProductRecommend);
	}
	/**
	 * 批量新增(推荐的商品)
	 * @param ebuyProductRecommendList
	 * @return
	 */
	@Override
	public int insertEbuyProductRecommendBatch(List<EbuyProductRecommend> ebuyProductRecommendList){
		return ebuyProductRecommendBaseDao.insertEbuyProductRecommendBatch(ebuyProductRecommendList);
	}
	/**
	 * 更新(推荐的商品)信息
	 * @param ebuyProductRecommend
	 * @return
	 */
	@Override
	public int updateEbuyProductRecommend(EbuyProductRecommend ebuyProductRecommend){
		return ebuyProductRecommendBaseDao.updateEbuyProductRecommend(ebuyProductRecommend);
	}
	/**
	 * 批量更新(推荐的商品)信息
	 * @param ebuyProductRecommendList
	 * @return
	 */
	@Override
	public int updateEbuyProductRecommendBatch(List<EbuyProductRecommend> ebuyProductRecommendList){
		return ebuyProductRecommendBaseDao.updateEbuyProductRecommendBatch(ebuyProductRecommendList);
	}
	/**
	 * 根据序列号删除(推荐的商品)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductRecommendLogic(java.math.BigInteger id){
		return ebuyProductRecommendBaseDao.deleteEbuyProductRecommendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(推荐的商品)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductRecommendLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductRecommendBaseDao.deleteEbuyProductRecommendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(推荐的商品)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductRecommend(java.math.BigInteger id){
//		return ebuyProductRecommendBaseDao.deleteEbuyProductRecommend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(推荐的商品)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductRecommendBatch(List<java.math.BigInteger> idList){
//		return ebuyProductRecommendBaseDao.deleteEbuyProductRecommendBatch(idList);
//	}
	
}
