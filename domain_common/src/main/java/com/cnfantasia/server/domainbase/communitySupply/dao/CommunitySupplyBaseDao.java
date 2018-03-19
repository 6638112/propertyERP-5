package com.cnfantasia.server.domainbase.communitySupply.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;

/**
 * 描述:(社区商家信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyBaseDao extends AbstractBaseDao implements ICommunitySupplyBaseDao{
	/**
	 * 根据条件查询(社区商家信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupply> selectCommunitySupplyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyBase.select_communitySupply",paramMap);
	}
	/**
	 * 按条件分页查询(社区商家信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupply> selectCommunitySupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupply> resMap= sqlSession.selectList("communitySupplyBase.select_communitySupply_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区商家信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupply selectCommunitySupplyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyBase.select_communitySupply_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyBase.select_communitySupply_count", paramMap);
	}
	/**
	 * 往(社区商家信息)新增一条记录
	 * @param communitySupply
	 * @return
	 */
	@Override
	public int insertCommunitySupply(CommunitySupply communitySupply){
		return sqlSession.insert("communitySupplyBase.insert_communitySupply",communitySupply);
	}
	/**
	 * 批量新增(社区商家信息)信息
	 * @param communitySupplyList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBatch(List<CommunitySupply> communitySupplyList) {
		return sqlSession.insert("communitySupplyBase.insert_communitySupply_Batch",communitySupplyList);
	}
	
	/**
	 * 更新(社区商家信息)信息
	 * @param communitySupply
	 * @return
	 */
	@Override
	public int updateCommunitySupply(CommunitySupply communitySupply){
		return sqlSession.update("communitySupplyBase.update_communitySupply", communitySupply);
	}
	/**
	 * 批量更新(社区商家信息)信息
	 * @param communitySupplyList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBatch(List<CommunitySupply> communitySupplyList) {
		return sqlSession.update("communitySupplyBase.update_communitySupply_Batch", communitySupplyList);
	}
	
	/**
	 * 根据序列号删除(社区商家信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyLogic(java.math.BigInteger id){
		CommunitySupply communitySupply = new CommunitySupply();
		communitySupply.setId(id);
		communitySupply.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyBase.delete_communitySupply_Logic",communitySupply);
	}
	
	/**
	 * 根据Id 批量删除(社区商家信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupply> delList = new java.util.ArrayList<CommunitySupply>();
		for(java.math.BigInteger id:idList){
			CommunitySupply communitySupply = new CommunitySupply();
			communitySupply.setId(id);
			communitySupply.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupply);
		}
		return sqlSession.update("communitySupplyBase.delete_communitySupply_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupply(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyBase.delete_communitySupply", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyBase.delete_communitySupply_Batch", idList);
//	}
	
	
}
