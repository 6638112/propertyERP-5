package com.cnfantasia.server.domainbase.communitySupplyTmp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

/**
 * 描述:(店铺申请审核表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyTmpBaseDao extends AbstractBaseDao implements ICommunitySupplyTmpBaseDao{
	/**
	 * 根据条件查询(店铺申请审核表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> selectCommunitySupplyTmpByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyTmpBase.select_communitySupplyTmp",paramMap);
	}
	/**
	 * 按条件分页查询(店铺申请审核表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTmp> selectCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyTmpCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyTmp> resMap= sqlSession.selectList("communitySupplyTmpBase.select_communitySupplyTmp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(店铺申请审核表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTmp selectCommunitySupplyTmpBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyTmpBase.select_communitySupplyTmp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyTmpCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyTmpBase.select_communitySupplyTmp_count", paramMap);
	}
	/**
	 * 往(店铺申请审核表)新增一条记录
	 * @param communitySupplyTmp
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp){
		return sqlSession.insert("communitySupplyTmpBase.insert_communitySupplyTmp",communitySupplyTmp);
	}
	/**
	 * 批量新增(店铺申请审核表)信息
	 * @param communitySupplyTmpList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList) {
		return sqlSession.insert("communitySupplyTmpBase.insert_communitySupplyTmp_Batch",communitySupplyTmpList);
	}
	
	/**
	 * 更新(店铺申请审核表)信息
	 * @param communitySupplyTmp
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp){
		return sqlSession.update("communitySupplyTmpBase.update_communitySupplyTmp", communitySupplyTmp);
	}
	/**
	 * 批量更新(店铺申请审核表)信息
	 * @param communitySupplyTmpList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList) {
		return sqlSession.update("communitySupplyTmpBase.update_communitySupplyTmp_Batch", communitySupplyTmpList);
	}
	
	/**
	 * 根据序列号删除(店铺申请审核表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTmpLogic(java.math.BigInteger id){
		CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
		communitySupplyTmp.setId(id);
		communitySupplyTmp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyTmpBase.delete_communitySupplyTmp_Logic",communitySupplyTmp);
	}
	
	/**
	 * 根据Id 批量删除(店铺申请审核表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyTmp> delList = new java.util.ArrayList<CommunitySupplyTmp>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
			communitySupplyTmp.setId(id);
			communitySupplyTmp.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyTmp);
		}
		return sqlSession.update("communitySupplyTmpBase.delete_communitySupplyTmp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(店铺申请审核表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTmp(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyTmpBase.delete_communitySupplyTmp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(店铺申请审核表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTmpBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyTmpBase.delete_communitySupplyTmp_Batch", idList);
//	}
	
	
}
