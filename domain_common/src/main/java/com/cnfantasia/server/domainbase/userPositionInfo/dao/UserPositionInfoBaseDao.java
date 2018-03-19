package com.cnfantasia.server.domainbase.userPositionInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPositionInfo.entity.UserPositionInfo;

/**
 * 描述:(用户定位信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserPositionInfoBaseDao extends AbstractBaseDao implements IUserPositionInfoBaseDao{
	/**
	 * 根据条件查询(用户定位信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPositionInfo> selectUserPositionInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userPositionInfoBase.select_userPositionInfo",paramMap);
	}
	/**
	 * 按条件分页查询(用户定位信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPositionInfo> selectUserPositionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserPositionInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserPositionInfo> resMap= sqlSession.selectList("userPositionInfoBase.select_userPositionInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户定位信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPositionInfo selectUserPositionInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userPositionInfoBase.select_userPositionInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户定位信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserPositionInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userPositionInfoBase.select_userPositionInfo_count", paramMap);
	}
	/**
	 * 往(用户定位信息)新增一条记录
	 * @param userPositionInfo
	 * @return
	 */
	@Override
	public int insertUserPositionInfo(UserPositionInfo userPositionInfo){
		return sqlSession.insert("userPositionInfoBase.insert_userPositionInfo",userPositionInfo);
	}
	/**
	 * 批量新增(用户定位信息)信息
	 * @param userPositionInfoList
	 * @return
	 */
	@Override
	public int insertUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList) {
		return sqlSession.insert("userPositionInfoBase.insert_userPositionInfo_Batch",userPositionInfoList);
	}
	
	/**
	 * 更新(用户定位信息)信息
	 * @param userPositionInfo
	 * @return
	 */
	@Override
	public int updateUserPositionInfo(UserPositionInfo userPositionInfo){
		return sqlSession.update("userPositionInfoBase.update_userPositionInfo", userPositionInfo);
	}
	/**
	 * 批量更新(用户定位信息)信息
	 * @param userPositionInfoList
	 * @return
	 */
	@Override
	public int updateUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList) {
		return sqlSession.update("userPositionInfoBase.update_userPositionInfo_Batch", userPositionInfoList);
	}
	
	/**
	 * 根据序列号删除(用户定位信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPositionInfoLogic(java.math.BigInteger id){
		UserPositionInfo userPositionInfo = new UserPositionInfo();
		userPositionInfo.setId(id);
		userPositionInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userPositionInfoBase.delete_userPositionInfo_Logic",userPositionInfo);
	}
	
	/**
	 * 根据Id 批量删除(用户定位信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPositionInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<UserPositionInfo> delList = new java.util.ArrayList<UserPositionInfo>();
		for(java.math.BigInteger id:idList){
			UserPositionInfo userPositionInfo = new UserPositionInfo();
			userPositionInfo.setId(id);
			userPositionInfo.setSys0DelState(RecordState_DELETED);
			delList.add(userPositionInfo);
		}
		return sqlSession.update("userPositionInfoBase.delete_userPositionInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户定位信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPositionInfo(java.math.BigInteger id){
//		return sqlSession.delete("userPositionInfoBase.delete_userPositionInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户定位信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPositionInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userPositionInfoBase.delete_userPositionInfo_Batch", idList);
//	}
	
	
}
