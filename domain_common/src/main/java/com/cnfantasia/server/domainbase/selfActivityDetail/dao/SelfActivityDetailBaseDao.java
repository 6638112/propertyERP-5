package com.cnfantasia.server.domainbase.selfActivityDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;

/**
 * 描述:(自定义活动详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SelfActivityDetailBaseDao extends AbstractBaseDao implements ISelfActivityDetailBaseDao{
	/**
	 * 根据条件查询(自定义活动详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> selectSelfActivityDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("selfActivityDetailBase.select_selfActivityDetail",paramMap);
	}
	/**
	 * 按条件分页查询(自定义活动详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SelfActivityDetail> selectSelfActivityDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSelfActivityDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SelfActivityDetail> resMap= sqlSession.selectList("selfActivityDetailBase.select_selfActivityDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(自定义活动详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public SelfActivityDetail selectSelfActivityDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("selfActivityDetailBase.select_selfActivityDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(自定义活动详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSelfActivityDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("selfActivityDetailBase.select_selfActivityDetail_count", paramMap);
	}
	/**
	 * 往(自定义活动详情)新增一条记录
	 * @param selfActivityDetail
	 * @return
	 */
	@Override
	public int insertSelfActivityDetail(SelfActivityDetail selfActivityDetail){
		return sqlSession.insert("selfActivityDetailBase.insert_selfActivityDetail",selfActivityDetail);
	}
	/**
	 * 批量新增(自定义活动详情)信息
	 * @param selfActivityDetailList
	 * @return
	 */
	@Override
	public int insertSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList) {
		if (selfActivityDetailList == null || selfActivityDetailList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = selfActivityDetailList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<SelfActivityDetail> batchList = selfActivityDetailList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("selfActivityDetailBase.insert_selfActivityDetail_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(自定义活动详情)信息
	 * @param selfActivityDetail
	 * @return
	 */
	@Override
	public int updateSelfActivityDetail(SelfActivityDetail selfActivityDetail){
		return sqlSession.update("selfActivityDetailBase.update_selfActivityDetail", selfActivityDetail);
	}
	/**
	 * 批量更新(自定义活动详情)信息
	 * @param selfActivityDetailList
	 * @return
	 */
	@Override
	public int updateSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList) {
		if (selfActivityDetailList == null || selfActivityDetailList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("selfActivityDetailBase.update_selfActivityDetail_Batch", selfActivityDetailList);
	}
	
	/**
	 * 根据序列号删除(自定义活动详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityDetailLogic(java.math.BigInteger id){
		SelfActivityDetail selfActivityDetail = new SelfActivityDetail();
		selfActivityDetail.setId(id);
		selfActivityDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("selfActivityDetailBase.delete_selfActivityDetail_Logic",selfActivityDetail);
	}
	
	/**
	 * 根据Id 批量删除(自定义活动详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityDetailLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<SelfActivityDetail> delList = new java.util.ArrayList<SelfActivityDetail>();
		for(java.math.BigInteger id:idList){
			SelfActivityDetail selfActivityDetail = new SelfActivityDetail();
			selfActivityDetail.setId(id);
			selfActivityDetail.setSys0DelState(RecordState_DELETED);
			delList.add(selfActivityDetail);
		}
		return sqlSession.update("selfActivityDetailBase.delete_selfActivityDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(自定义活动详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityDetail(java.math.BigInteger id){
//		return sqlSession.delete("selfActivityDetailBase.delete_selfActivityDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自定义活动详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("selfActivityDetailBase.delete_selfActivityDetail_Batch", idList);
//	}
	
	
}
