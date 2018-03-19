package com.cnfantasia.server.domainbase.communitySupplyPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;

/**
 * 描述:(商家图片) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyPicBaseDao extends AbstractBaseDao implements ICommunitySupplyPicBaseDao{
	/**
	 * 根据条件查询(商家图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> selectCommunitySupplyPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyPicBase.select_communitySupplyPic",paramMap);
	}
	/**
	 * 按条件分页查询(商家图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyPic> selectCommunitySupplyPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyPic> resMap= sqlSession.selectList("communitySupplyPicBase.select_communitySupplyPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyPic selectCommunitySupplyPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyPicBase.select_communitySupplyPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyPicBase.select_communitySupplyPic_count", paramMap);
	}
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyPic
	 * @return
	 */
	@Override
	public int insertCommunitySupplyPic(CommunitySupplyPic communitySupplyPic){
		return sqlSession.insert("communitySupplyPicBase.insert_communitySupplyPic",communitySupplyPic);
	}
	/**
	 * 批量新增(商家图片)信息
	 * @param communitySupplyPicList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList) {
		return sqlSession.insert("communitySupplyPicBase.insert_communitySupplyPic_Batch",communitySupplyPicList);
	}
	
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyPic
	 * @return
	 */
	@Override
	public int updateCommunitySupplyPic(CommunitySupplyPic communitySupplyPic){
		return sqlSession.update("communitySupplyPicBase.update_communitySupplyPic", communitySupplyPic);
	}
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyPicList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList) {
		return sqlSession.update("communitySupplyPicBase.update_communitySupplyPic_Batch", communitySupplyPicList);
	}
	
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyPicLogic(java.math.BigInteger id){
		CommunitySupplyPic communitySupplyPic = new CommunitySupplyPic();
		communitySupplyPic.setId(id);
		communitySupplyPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyPicBase.delete_communitySupplyPic_Logic",communitySupplyPic);
	}
	
	/**
	 * 根据Id 批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyPicLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyPic> delList = new java.util.ArrayList<CommunitySupplyPic>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyPic communitySupplyPic = new CommunitySupplyPic();
			communitySupplyPic.setId(id);
			communitySupplyPic.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyPic);
		}
		return sqlSession.update("communitySupplyPicBase.delete_communitySupplyPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyPic(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyPicBase.delete_communitySupplyPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyPicBase.delete_communitySupplyPic_Batch", idList);
//	}
	
	
}
