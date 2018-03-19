package com.cnfantasia.server.domainbase.familyMsgPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;

/**
 * 描述:(家庭留言板图片) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FamilyMsgPicBaseDao extends AbstractBaseDao implements IFamilyMsgPicBaseDao{
	/**
	 * 根据条件查询(家庭留言板图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> selectFamilyMsgPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("familyMsgPicBase.select_familyMsgPic",paramMap);
	}
	/**
	 * 按条件分页查询(家庭留言板图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> selectFamilyMsgPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFamilyMsgPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FamilyMsgPic> resMap= sqlSession.selectList("familyMsgPicBase.select_familyMsgPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(家庭留言板图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgPic selectFamilyMsgPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("familyMsgPicBase.select_familyMsgPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFamilyMsgPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("familyMsgPicBase.select_familyMsgPic_count", paramMap);
	}
	/**
	 * 往(家庭留言板图片)新增一条记录
	 * @param familyMsgPic
	 * @return
	 */
	@Override
	public int insertFamilyMsgPic(FamilyMsgPic familyMsgPic){
		return sqlSession.insert("familyMsgPicBase.insert_familyMsgPic",familyMsgPic);
	}
	/**
	 * 批量新增(家庭留言板图片)信息
	 * @param familyMsgPicList
	 * @return
	 */
	@Override
	public int insertFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList) {
		return sqlSession.insert("familyMsgPicBase.insert_familyMsgPic_Batch",familyMsgPicList);
	}
	
	/**
	 * 更新(家庭留言板图片)信息
	 * @param familyMsgPic
	 * @return
	 */
	@Override
	public int updateFamilyMsgPic(FamilyMsgPic familyMsgPic){
		return sqlSession.update("familyMsgPicBase.update_familyMsgPic", familyMsgPic);
	}
	/**
	 * 批量更新(家庭留言板图片)信息
	 * @param familyMsgPicList
	 * @return
	 */
	@Override
	public int updateFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList) {
		return sqlSession.update("familyMsgPicBase.update_familyMsgPic_Batch", familyMsgPicList);
	}
	
	/**
	 * 根据序列号删除(家庭留言板图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgPicLogic(java.math.BigInteger id){
		FamilyMsgPic familyMsgPic = new FamilyMsgPic();
		familyMsgPic.setId(id);
		familyMsgPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("familyMsgPicBase.delete_familyMsgPic_Logic",familyMsgPic);
	}
	
	/**
	 * 根据Id 批量删除(家庭留言板图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgPicLogicBatch(List<java.math.BigInteger> idList) {
		List<FamilyMsgPic> delList = new java.util.ArrayList<FamilyMsgPic>();
		for(java.math.BigInteger id:idList){
			FamilyMsgPic familyMsgPic = new FamilyMsgPic();
			familyMsgPic.setId(id);
			familyMsgPic.setSys0DelState(RecordState_DELETED);
			delList.add(familyMsgPic);
		}
		return sqlSession.update("familyMsgPicBase.delete_familyMsgPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言板图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgPic(java.math.BigInteger id){
//		return sqlSession.delete("familyMsgPicBase.delete_familyMsgPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("familyMsgPicBase.delete_familyMsgPic_Batch", idList);
//	}
	
	
}
