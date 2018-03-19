package com.cnfantasia.server.domainbase.communitySupplyBelong.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;

/**
 * 描述:(商家认领) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyBelongBaseDao extends AbstractBaseDao implements ICommunitySupplyBelongBaseDao{
	/**
	 * 根据条件查询(商家认领)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> selectCommunitySupplyBelongByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyBelongBase.select_communitySupplyBelong",paramMap);
	}
	/**
	 * 按条件分页查询(商家认领)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyBelong> selectCommunitySupplyBelongByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyBelongCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyBelong> resMap= sqlSession.selectList("communitySupplyBelongBase.select_communitySupplyBelong_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商家认领)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyBelong selectCommunitySupplyBelongBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyBelongBase.select_communitySupplyBelong_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商家认领)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyBelongCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyBelongBase.select_communitySupplyBelong_count", paramMap);
	}
	/**
	 * 往(商家认领)新增一条记录
	 * @param communitySupplyBelong
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong){
		return sqlSession.insert("communitySupplyBelongBase.insert_communitySupplyBelong",communitySupplyBelong);
	}
	/**
	 * 批量新增(商家认领)信息
	 * @param communitySupplyBelongList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList) {
		return sqlSession.insert("communitySupplyBelongBase.insert_communitySupplyBelong_Batch",communitySupplyBelongList);
	}
	
	/**
	 * 更新(商家认领)信息
	 * @param communitySupplyBelong
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong){
		return sqlSession.update("communitySupplyBelongBase.update_communitySupplyBelong", communitySupplyBelong);
	}
	/**
	 * 批量更新(商家认领)信息
	 * @param communitySupplyBelongList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList) {
		return sqlSession.update("communitySupplyBelongBase.update_communitySupplyBelong_Batch", communitySupplyBelongList);
	}
	
	/**
	 * 根据序列号删除(商家认领)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyBelongLogic(java.math.BigInteger id){
		CommunitySupplyBelong communitySupplyBelong = new CommunitySupplyBelong();
		communitySupplyBelong.setId(id);
		communitySupplyBelong.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyBelongBase.delete_communitySupplyBelong_Logic",communitySupplyBelong);
	}
	
	/**
	 * 根据Id 批量删除(商家认领)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyBelongLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyBelong> delList = new java.util.ArrayList<CommunitySupplyBelong>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyBelong communitySupplyBelong = new CommunitySupplyBelong();
			communitySupplyBelong.setId(id);
			communitySupplyBelong.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyBelong);
		}
		return sqlSession.update("communitySupplyBelongBase.delete_communitySupplyBelong_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商家认领)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBelong(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyBelongBase.delete_communitySupplyBelong", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家认领)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyBelongBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyBelongBase.delete_communitySupplyBelong_Batch", idList);
//	}
	
	
}
