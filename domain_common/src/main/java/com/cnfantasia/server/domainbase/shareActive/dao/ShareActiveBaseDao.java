package com.cnfantasia.server.domainbase.shareActive.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActive.entity.ShareActive;

/**
 * 描述:(分享活动) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ShareActiveBaseDao extends AbstractBaseDao implements IShareActiveBaseDao{
	/**
	 * 根据条件查询(分享活动)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShareActive> selectShareActiveByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("shareActiveBase.select_shareActive",paramMap);
	}
	/**
	 * 按条件分页查询(分享活动)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShareActive> selectShareActiveByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectShareActiveCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ShareActive> resMap= sqlSession.selectList("shareActiveBase.select_shareActive_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(分享活动)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShareActive selectShareActiveBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("shareActiveBase.select_shareActive_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(分享活动)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectShareActiveCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("shareActiveBase.select_shareActive_count", paramMap);
	}
	/**
	 * 往(分享活动)新增一条记录
	 * @param shareActive
	 * @return
	 */
	@Override
	public int insertShareActive(ShareActive shareActive){
		return sqlSession.insert("shareActiveBase.insert_shareActive",shareActive);
	}
	/**
	 * 批量新增(分享活动)信息
	 * @param shareActiveList
	 * @return
	 */
	@Override
	public int insertShareActiveBatch(List<ShareActive> shareActiveList) {
		return sqlSession.insert("shareActiveBase.insert_shareActive_Batch",shareActiveList);
	}
	
	/**
	 * 更新(分享活动)信息
	 * @param shareActive
	 * @return
	 */
	@Override
	public int updateShareActive(ShareActive shareActive){
		return sqlSession.update("shareActiveBase.update_shareActive", shareActive);
	}
	/**
	 * 批量更新(分享活动)信息
	 * @param shareActiveList
	 * @return
	 */
	@Override
	public int updateShareActiveBatch(List<ShareActive> shareActiveList) {
		return sqlSession.update("shareActiveBase.update_shareActive_Batch", shareActiveList);
	}
	
	/**
	 * 根据序列号删除(分享活动)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShareActiveLogic(java.math.BigInteger id){
		ShareActive shareActive = new ShareActive();
		shareActive.setId(id);
		shareActive.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("shareActiveBase.delete_shareActive_Logic",shareActive);
	}
	
	/**
	 * 根据Id 批量删除(分享活动)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShareActiveLogicBatch(List<java.math.BigInteger> idList) {
		List<ShareActive> delList = new java.util.ArrayList<ShareActive>();
		for(java.math.BigInteger id:idList){
			ShareActive shareActive = new ShareActive();
			shareActive.setId(id);
			shareActive.setSys0DelState(RecordState_DELETED);
			delList.add(shareActive);
		}
		return sqlSession.update("shareActiveBase.delete_shareActive_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(分享活动)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShareActive(java.math.BigInteger id){
//		return sqlSession.delete("shareActiveBase.delete_shareActive", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(分享活动)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShareActiveBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("shareActiveBase.delete_shareActive_Batch", idList);
//	}
	
	
}
