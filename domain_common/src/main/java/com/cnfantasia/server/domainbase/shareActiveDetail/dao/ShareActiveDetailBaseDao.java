package com.cnfantasia.server.domainbase.shareActiveDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;

/**
 * 描述:(分享活动详情表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ShareActiveDetailBaseDao extends AbstractBaseDao implements IShareActiveDetailBaseDao{
	/**
	 * 根据条件查询(分享活动详情表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> selectShareActiveDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("shareActiveDetailBase.select_shareActiveDetail",paramMap);
	}
	/**
	 * 按条件分页查询(分享活动详情表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShareActiveDetail> selectShareActiveDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectShareActiveDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ShareActiveDetail> resMap= sqlSession.selectList("shareActiveDetailBase.select_shareActiveDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(分享活动详情表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShareActiveDetail selectShareActiveDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("shareActiveDetailBase.select_shareActiveDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectShareActiveDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("shareActiveDetailBase.select_shareActiveDetail_count", paramMap);
	}
	/**
	 * 往(分享活动详情表)新增一条记录
	 * @param shareActiveDetail
	 * @return
	 */
	@Override
	public int insertShareActiveDetail(ShareActiveDetail shareActiveDetail){
		return sqlSession.insert("shareActiveDetailBase.insert_shareActiveDetail",shareActiveDetail);
	}
	/**
	 * 批量新增(分享活动详情表)信息
	 * @param shareActiveDetailList
	 * @return
	 */
	@Override
	public int insertShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList) {
		return sqlSession.insert("shareActiveDetailBase.insert_shareActiveDetail_Batch",shareActiveDetailList);
	}
	
	/**
	 * 更新(分享活动详情表)信息
	 * @param shareActiveDetail
	 * @return
	 */
	@Override
	public int updateShareActiveDetail(ShareActiveDetail shareActiveDetail){
		return sqlSession.update("shareActiveDetailBase.update_shareActiveDetail", shareActiveDetail);
	}
	/**
	 * 批量更新(分享活动详情表)信息
	 * @param shareActiveDetailList
	 * @return
	 */
	@Override
	public int updateShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList) {
		return sqlSession.update("shareActiveDetailBase.update_shareActiveDetail_Batch", shareActiveDetailList);
	}
	
	/**
	 * 根据序列号删除(分享活动详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShareActiveDetailLogic(java.math.BigInteger id){
		ShareActiveDetail shareActiveDetail = new ShareActiveDetail();
		shareActiveDetail.setId(id);
		shareActiveDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("shareActiveDetailBase.delete_shareActiveDetail_Logic",shareActiveDetail);
	}
	
	/**
	 * 根据Id 批量删除(分享活动详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShareActiveDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<ShareActiveDetail> delList = new java.util.ArrayList<ShareActiveDetail>();
		for(java.math.BigInteger id:idList){
			ShareActiveDetail shareActiveDetail = new ShareActiveDetail();
			shareActiveDetail.setId(id);
			shareActiveDetail.setSys0DelState(RecordState_DELETED);
			delList.add(shareActiveDetail);
		}
		return sqlSession.update("shareActiveDetailBase.delete_shareActiveDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(分享活动详情表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveDetail(java.math.BigInteger id){
//		return sqlSession.delete("shareActiveDetailBase.delete_shareActiveDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享活动详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("shareActiveDetailBase.delete_shareActiveDetail_Batch", idList);
//	}
	
	
}
