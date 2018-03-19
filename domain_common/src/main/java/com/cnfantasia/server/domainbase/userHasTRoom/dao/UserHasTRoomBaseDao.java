package com.cnfantasia.server.domainbase.userHasTRoom.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * 描述:(用户门牌关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasTRoomBaseDao extends AbstractBaseDao implements IUserHasTRoomBaseDao{
	/**
	 * 根据条件查询(用户门牌关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTRoom> selectUserHasTRoomByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasTRoomBase.select_userHasTRoom",paramMap);
	}
	/**
	 * 按条件分页查询(用户门牌关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTRoom> selectUserHasTRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasTRoomCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasTRoom> resMap= sqlSession.selectList("userHasTRoomBase.select_userHasTRoom_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户门牌关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTRoom selectUserHasTRoomBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasTRoomBase.select_userHasTRoom_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户门牌关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasTRoomCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasTRoomBase.select_userHasTRoom_count", paramMap);
	}
	/**
	 * 往(用户门牌关系)新增一条记录
	 * @param userHasTRoom
	 * @return
	 */
	@Override
	public int insertUserHasTRoom(UserHasTRoom userHasTRoom){
		return sqlSession.insert("userHasTRoomBase.insert_userHasTRoom",userHasTRoom);
	}
	/**
	 * 批量新增(用户门牌关系)信息
	 * @param userHasTRoomList
	 * @return
	 */
	@Override
	public int insertUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList) {
		return sqlSession.insert("userHasTRoomBase.insert_userHasTRoom_Batch",userHasTRoomList);
	}
	
	/**
	 * 更新(用户门牌关系)信息
	 * @param userHasTRoom
	 * @return
	 */
	@Override
	public int updateUserHasTRoom(UserHasTRoom userHasTRoom){
		return sqlSession.update("userHasTRoomBase.update_userHasTRoom", userHasTRoom);
	}
	/**
	 * 批量更新(用户门牌关系)信息
	 * @param userHasTRoomList
	 * @return
	 */
	@Override
	public int updateUserHasTRoomBatch(List<UserHasTRoom> userHasTRoomList) {
		return sqlSession.update("userHasTRoomBase.update_userHasTRoom_Batch", userHasTRoomList);
	}
	
	/**
	 * 根据序列号删除(用户门牌关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTRoomLogic(java.math.BigInteger id){
		UserHasTRoom userHasTRoom = new UserHasTRoom();
		userHasTRoom.setId(id);
		userHasTRoom.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasTRoomBase.delete_userHasTRoom_Logic",userHasTRoom);
	}
	
	/**
	 * 根据Id 批量删除(用户门牌关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTRoomLogicBatch(List<java.math.BigInteger> idList) {
		List<UserHasTRoom> delList = new java.util.ArrayList<UserHasTRoom>();
		for(java.math.BigInteger id:idList){
			UserHasTRoom userHasTRoom = new UserHasTRoom();
			userHasTRoom.setId(id);
			userHasTRoom.setSys0DelState(RecordState_DELETED);
			delList.add(userHasTRoom);
		}
		return sqlSession.update("userHasTRoomBase.delete_userHasTRoom_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户门牌关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTRoom(java.math.BigInteger id){
//		return sqlSession.delete("userHasTRoomBase.delete_userHasTRoom", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户门牌关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTRoomBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasTRoomBase.delete_userHasTRoom_Batch", idList);
//	}
	
	
}
