package com.cnfantasia.server.domainbase.communitySupplyContect.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * 描述:(社区商家联系方式) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyContectBaseDao extends AbstractBaseDao implements ICommunitySupplyContectBaseDao{
	/**
	 * 根据条件查询(社区商家联系方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> selectCommunitySupplyContectByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyContectBase.select_communitySupplyContect",paramMap);
	}
	/**
	 * 按条件分页查询(社区商家联系方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyContect> selectCommunitySupplyContectByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyContectCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyContect> resMap= sqlSession.selectList("communitySupplyContectBase.select_communitySupplyContect_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区商家联系方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyContect selectCommunitySupplyContectBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyContectBase.select_communitySupplyContect_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyContectCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyContectBase.select_communitySupplyContect_count", paramMap);
	}
	/**
	 * 往(社区商家联系方式)新增一条记录
	 * @param communitySupplyContect
	 * @return
	 */
	@Override
	public int insertCommunitySupplyContect(CommunitySupplyContect communitySupplyContect){
		return sqlSession.insert("communitySupplyContectBase.insert_communitySupplyContect",communitySupplyContect);
	}
	/**
	 * 批量新增(社区商家联系方式)信息
	 * @param communitySupplyContectList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList) {
		return sqlSession.insert("communitySupplyContectBase.insert_communitySupplyContect_Batch",communitySupplyContectList);
	}
	
	/**
	 * 更新(社区商家联系方式)信息
	 * @param communitySupplyContect
	 * @return
	 */
	@Override
	public int updateCommunitySupplyContect(CommunitySupplyContect communitySupplyContect){
		return sqlSession.update("communitySupplyContectBase.update_communitySupplyContect", communitySupplyContect);
	}
	/**
	 * 批量更新(社区商家联系方式)信息
	 * @param communitySupplyContectList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList) {
		return sqlSession.update("communitySupplyContectBase.update_communitySupplyContect_Batch", communitySupplyContectList);
	}
	
	/**
	 * 根据序列号删除(社区商家联系方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyContectLogic(java.math.BigInteger id){
		CommunitySupplyContect communitySupplyContect = new CommunitySupplyContect();
		communitySupplyContect.setId(id);
		communitySupplyContect.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyContectBase.delete_communitySupplyContect_Logic",communitySupplyContect);
	}
	
	/**
	 * 根据Id 批量删除(社区商家联系方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyContectLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyContect> delList = new java.util.ArrayList<CommunitySupplyContect>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyContect communitySupplyContect = new CommunitySupplyContect();
			communitySupplyContect.setId(id);
			communitySupplyContect.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyContect);
		}
		return sqlSession.update("communitySupplyContectBase.delete_communitySupplyContect_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家联系方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyContect(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyContectBase.delete_communitySupplyContect", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家联系方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyContectBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyContectBase.delete_communitySupplyContect_Batch", idList);
//	}
	
	
}
