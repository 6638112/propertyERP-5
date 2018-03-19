package com.cnfantasia.server.domainbase.communitySupplyCompanyPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;

/**
 * 描述:(商家图片) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyCompanyPicBaseDao extends AbstractBaseDao implements ICommunitySupplyCompanyPicBaseDao{
	/**
	 * 根据条件查询(商家图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> selectCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyCompanyPicBase.select_communitySupplyCompanyPic",paramMap);
	}
	/**
	 * 按条件分页查询(商家图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompanyPic> selectCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyCompanyPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyCompanyPic> resMap= sqlSession.selectList("communitySupplyCompanyPicBase.select_communitySupplyCompanyPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyCompanyPic selectCommunitySupplyCompanyPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyCompanyPicBase.select_communitySupplyCompanyPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商家图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyCompanyPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyCompanyPicBase.select_communitySupplyCompanyPic_count", paramMap);
	}
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic){
		return sqlSession.insert("communitySupplyCompanyPicBase.insert_communitySupplyCompanyPic",communitySupplyCompanyPic);
	}
	/**
	 * 批量新增(商家图片)信息
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList) {
		return sqlSession.insert("communitySupplyCompanyPicBase.insert_communitySupplyCompanyPic_Batch",communitySupplyCompanyPicList);
	}
	
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic){
		return sqlSession.update("communitySupplyCompanyPicBase.update_communitySupplyCompanyPic", communitySupplyCompanyPic);
	}
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList) {
		return sqlSession.update("communitySupplyCompanyPicBase.update_communitySupplyCompanyPic_Batch", communitySupplyCompanyPicList);
	}
	
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyPicLogic(java.math.BigInteger id){
		CommunitySupplyCompanyPic communitySupplyCompanyPic = new CommunitySupplyCompanyPic();
		communitySupplyCompanyPic.setId(id);
		communitySupplyCompanyPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyCompanyPicBase.delete_communitySupplyCompanyPic_Logic",communitySupplyCompanyPic);
	}
	
	/**
	 * 根据Id 批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyPicLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyCompanyPic> delList = new java.util.ArrayList<CommunitySupplyCompanyPic>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyCompanyPic communitySupplyCompanyPic = new CommunitySupplyCompanyPic();
			communitySupplyCompanyPic.setId(id);
			communitySupplyCompanyPic.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyCompanyPic);
		}
		return sqlSession.update("communitySupplyCompanyPicBase.delete_communitySupplyCompanyPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyPic(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyCompanyPicBase.delete_communitySupplyCompanyPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyCompanyPicBase.delete_communitySupplyCompanyPic_Batch", idList);
//	}
	
	
}
