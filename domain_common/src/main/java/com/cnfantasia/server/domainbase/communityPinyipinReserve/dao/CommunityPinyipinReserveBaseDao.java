package com.cnfantasia.server.domainbase.communityPinyipinReserve.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * 描述:(拼单预定信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityPinyipinReserveBaseDao extends AbstractBaseDao implements ICommunityPinyipinReserveBaseDao{
	/**
	 * 根据条件查询(拼单预定信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> selectCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityPinyipinReserveBase.select_communityPinyipinReserve",paramMap);
	}
	/**
	 * 按条件分页查询(拼单预定信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityPinyipinReserve> selectCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityPinyipinReserveCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityPinyipinReserve> resMap= sqlSession.selectList("communityPinyipinReserveBase.select_communityPinyipinReserve_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(拼单预定信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityPinyipinReserve selectCommunityPinyipinReserveBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityPinyipinReserveBase.select_communityPinyipinReserve_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityPinyipinReserveCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityPinyipinReserveBase.select_communityPinyipinReserve_count", paramMap);
	}
	/**
	 * 往(拼单预定信息)新增一条记录
	 * @param communityPinyipinReserve
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve){
		return sqlSession.insert("communityPinyipinReserveBase.insert_communityPinyipinReserve",communityPinyipinReserve);
	}
	/**
	 * 批量新增(拼单预定信息)信息
	 * @param communityPinyipinReserveList
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList) {
		return sqlSession.insert("communityPinyipinReserveBase.insert_communityPinyipinReserve_Batch",communityPinyipinReserveList);
	}
	
	/**
	 * 更新(拼单预定信息)信息
	 * @param communityPinyipinReserve
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve){
		return sqlSession.update("communityPinyipinReserveBase.update_communityPinyipinReserve", communityPinyipinReserve);
	}
	/**
	 * 批量更新(拼单预定信息)信息
	 * @param communityPinyipinReserveList
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList) {
		return sqlSession.update("communityPinyipinReserveBase.update_communityPinyipinReserve_Batch", communityPinyipinReserveList);
	}
	
	/**
	 * 根据序列号删除(拼单预定信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinReserveLogic(java.math.BigInteger id){
		CommunityPinyipinReserve communityPinyipinReserve = new CommunityPinyipinReserve();
		communityPinyipinReserve.setId(id);
		communityPinyipinReserve.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityPinyipinReserveBase.delete_communityPinyipinReserve_Logic",communityPinyipinReserve);
	}
	
	/**
	 * 根据Id 批量删除(拼单预定信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinReserveLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityPinyipinReserve> delList = new java.util.ArrayList<CommunityPinyipinReserve>();
		for(java.math.BigInteger id:idList){
			CommunityPinyipinReserve communityPinyipinReserve = new CommunityPinyipinReserve();
			communityPinyipinReserve.setId(id);
			communityPinyipinReserve.setSys0DelState(RecordState_DELETED);
			delList.add(communityPinyipinReserve);
		}
		return sqlSession.update("communityPinyipinReserveBase.delete_communityPinyipinReserve_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(拼单预定信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinReserve(java.math.BigInteger id){
//		return sqlSession.delete("communityPinyipinReserveBase.delete_communityPinyipinReserve", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼单预定信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinReserveBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityPinyipinReserveBase.delete_communityPinyipinReserve_Batch", idList);
//	}
	
	
}
