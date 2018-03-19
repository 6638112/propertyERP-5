package com.cnfantasia.server.domainbase.familyMsgHasTUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;

/**
 * 描述:(家庭留言艾特的用户列表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FamilyMsgHasTUserBaseDao extends AbstractBaseDao implements IFamilyMsgHasTUserBaseDao{
	/**
	 * 根据条件查询(家庭留言艾特的用户列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> selectFamilyMsgHasTUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("familyMsgHasTUserBase.select_familyMsgHasTUser",paramMap);
	}
	/**
	 * 按条件分页查询(家庭留言艾特的用户列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> selectFamilyMsgHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFamilyMsgHasTUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FamilyMsgHasTUser> resMap= sqlSession.selectList("familyMsgHasTUserBase.select_familyMsgHasTUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(家庭留言艾特的用户列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgHasTUser selectFamilyMsgHasTUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("familyMsgHasTUserBase.select_familyMsgHasTUser_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言艾特的用户列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFamilyMsgHasTUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("familyMsgHasTUserBase.select_familyMsgHasTUser_count", paramMap);
	}
	/**
	 * 往(家庭留言艾特的用户列表)新增一条记录
	 * @param familyMsgHasTUser
	 * @return
	 */
	@Override
	public int insertFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser){
		return sqlSession.insert("familyMsgHasTUserBase.insert_familyMsgHasTUser",familyMsgHasTUser);
	}
	/**
	 * 批量新增(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUserList
	 * @return
	 */
	@Override
	public int insertFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList) {
		return sqlSession.insert("familyMsgHasTUserBase.insert_familyMsgHasTUser_Batch",familyMsgHasTUserList);
	}
	
	/**
	 * 更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUser
	 * @return
	 */
	@Override
	public int updateFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser){
		return sqlSession.update("familyMsgHasTUserBase.update_familyMsgHasTUser", familyMsgHasTUser);
	}
	/**
	 * 批量更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUserList
	 * @return
	 */
	@Override
	public int updateFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList) {
		return sqlSession.update("familyMsgHasTUserBase.update_familyMsgHasTUser_Batch", familyMsgHasTUserList);
	}
	
	/**
	 * 根据序列号删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgHasTUserLogic(java.math.BigInteger id){
		FamilyMsgHasTUser familyMsgHasTUser = new FamilyMsgHasTUser();
		familyMsgHasTUser.setId(id);
		familyMsgHasTUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("familyMsgHasTUserBase.delete_familyMsgHasTUser_Logic",familyMsgHasTUser);
	}
	
	/**
	 * 根据Id 批量删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgHasTUserLogicBatch(List<java.math.BigInteger> idList) {
		List<FamilyMsgHasTUser> delList = new java.util.ArrayList<FamilyMsgHasTUser>();
		for(java.math.BigInteger id:idList){
			FamilyMsgHasTUser familyMsgHasTUser = new FamilyMsgHasTUser();
			familyMsgHasTUser.setId(id);
			familyMsgHasTUser.setSys0DelState(RecordState_DELETED);
			delList.add(familyMsgHasTUser);
		}
		return sqlSession.update("familyMsgHasTUserBase.delete_familyMsgHasTUser_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言艾特的用户列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgHasTUser(java.math.BigInteger id){
//		return sqlSession.delete("familyMsgHasTUserBase.delete_familyMsgHasTUser", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言艾特的用户列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgHasTUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("familyMsgHasTUserBase.delete_familyMsgHasTUser_Batch", idList);
//	}
	
	
}
