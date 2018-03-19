package com.cnfantasia.server.domainbase.surpriseGiftUseDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.surpriseGiftUseDetail.entity.SurpriseGiftUseDetail;

/**
 * 描述:(意外惊喜的优惠券使用详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SurpriseGiftUseDetailBaseDao extends AbstractBaseDao implements ISurpriseGiftUseDetailBaseDao{
	/**
	 * 根据条件查询(意外惊喜的优惠券使用详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> selectSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("surpriseGiftUseDetailBase.select_surpriseGiftUseDetail",paramMap);
	}
	/**
	 * 按条件分页查询(意外惊喜的优惠券使用详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> selectSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSurpriseGiftUseDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SurpriseGiftUseDetail> resMap= sqlSession.selectList("surpriseGiftUseDetailBase.select_surpriseGiftUseDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(意外惊喜的优惠券使用详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public SurpriseGiftUseDetail selectSurpriseGiftUseDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("surpriseGiftUseDetailBase.select_surpriseGiftUseDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜的优惠券使用详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSurpriseGiftUseDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("surpriseGiftUseDetailBase.select_surpriseGiftUseDetail_count", paramMap);
	}
	/**
	 * 往(意外惊喜的优惠券使用详情)新增一条记录
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	@Override
	public int insertSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail){
		return sqlSession.insert("surpriseGiftUseDetailBase.insert_surpriseGiftUseDetail",surpriseGiftUseDetail);
	}
	/**
	 * 批量新增(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	@Override
	public int insertSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList) {
		return sqlSession.insert("surpriseGiftUseDetailBase.insert_surpriseGiftUseDetail_Batch",surpriseGiftUseDetailList);
	}
	
	/**
	 * 更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	@Override
	public int updateSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail){
		return sqlSession.update("surpriseGiftUseDetailBase.update_surpriseGiftUseDetail", surpriseGiftUseDetail);
	}
	/**
	 * 批量更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	@Override
	public int updateSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList) {
		return sqlSession.update("surpriseGiftUseDetailBase.update_surpriseGiftUseDetail_Batch", surpriseGiftUseDetailList);
	}
	
	/**
	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftUseDetailLogic(java.math.BigInteger id){
		SurpriseGiftUseDetail surpriseGiftUseDetail = new SurpriseGiftUseDetail();
		surpriseGiftUseDetail.setId(id);
		surpriseGiftUseDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("surpriseGiftUseDetailBase.delete_surpriseGiftUseDetail_Logic",surpriseGiftUseDetail);
	}
	
	/**
	 * 根据Id 批量删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftUseDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<SurpriseGiftUseDetail> delList = new java.util.ArrayList<SurpriseGiftUseDetail>();
		for(java.math.BigInteger id:idList){
			SurpriseGiftUseDetail surpriseGiftUseDetail = new SurpriseGiftUseDetail();
			surpriseGiftUseDetail.setId(id);
			surpriseGiftUseDetail.setSys0DelState(RecordState_DELETED);
			delList.add(surpriseGiftUseDetail);
		}
		return sqlSession.update("surpriseGiftUseDetailBase.delete_surpriseGiftUseDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftUseDetail(java.math.BigInteger id){
//		return sqlSession.delete("surpriseGiftUseDetailBase.delete_surpriseGiftUseDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜的优惠券使用详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftUseDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("surpriseGiftUseDetailBase.delete_surpriseGiftUseDetail_Batch", idList);
//	}
	
	
}
