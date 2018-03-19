package com.cnfantasia.server.domainbase.blackList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.blackList.entity.BlackList;

/**
 * 描述:(黑名单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BlackListBaseDao extends AbstractBaseDao implements IBlackListBaseDao{
	/**
	 * 根据条件查询(黑名单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BlackList> selectBlackListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("blackListBase.select_blackList",paramMap);
	}
	/**
	 * 按条件分页查询(黑名单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BlackList> selectBlackListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBlackListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BlackList> resMap= sqlSession.selectList("blackListBase.select_blackList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(黑名单)信息
	 * @param id
	 * @return
	 */
	@Override
	public BlackList selectBlackListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("blackListBase.select_blackList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(黑名单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBlackListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("blackListBase.select_blackList_count", paramMap);
	}
	/**
	 * 往(黑名单)新增一条记录
	 * @param blackList
	 * @return
	 */
	@Override
	public int insertBlackList(BlackList blackList){
		return sqlSession.insert("blackListBase.insert_blackList",blackList);
	}
	/**
	 * 批量新增(黑名单)信息
	 * @param blackListList
	 * @return
	 */
	@Override
	public int insertBlackListBatch(List<BlackList> blackListList) {
		return sqlSession.insert("blackListBase.insert_blackList_Batch",blackListList);
	}
	
	/**
	 * 更新(黑名单)信息
	 * @param blackList
	 * @return
	 */
	@Override
	public int updateBlackList(BlackList blackList){
		return sqlSession.update("blackListBase.update_blackList", blackList);
	}
	/**
	 * 批量更新(黑名单)信息
	 * @param blackListList
	 * @return
	 */
	@Override
	public int updateBlackListBatch(List<BlackList> blackListList) {
		return sqlSession.update("blackListBase.update_blackList_Batch", blackListList);
	}
	
	/**
	 * 根据序列号删除(黑名单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBlackListLogic(java.math.BigInteger id){
		BlackList blackList = new BlackList();
		blackList.setId(id);
		blackList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("blackListBase.delete_blackList_Logic",blackList);
	}
	
	/**
	 * 根据Id 批量删除(黑名单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBlackListLogicBatch(List<java.math.BigInteger> idList) {
		List<BlackList> delList = new java.util.ArrayList<BlackList>();
		for(java.math.BigInteger id:idList){
			BlackList blackList = new BlackList();
			blackList.setId(id);
			blackList.setSys0DelState(RecordState_DELETED);
			delList.add(blackList);
		}
		return sqlSession.update("blackListBase.delete_blackList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(黑名单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBlackList(java.math.BigInteger id){
//		return sqlSession.delete("blackListBase.delete_blackList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(黑名单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBlackListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("blackListBase.delete_blackList_Batch", idList);
//	}
	
	
}
