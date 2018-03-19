package com.cnfantasia.server.domainbase.room.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * 描述:(门牌信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RoomBaseDao extends AbstractBaseDao implements IRoomBaseDao{
	/**
	 * 根据条件查询(门牌信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Room> selectRoomByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("roomBase.select_room",paramMap);
	}
	/**
	 * 按条件分页查询(门牌信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Room> selectRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRoomCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Room> resMap= sqlSession.selectList("roomBase.select_room_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(门牌信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public Room selectRoomBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("roomBase.select_room_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(门牌信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRoomCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("roomBase.select_room_count", paramMap);
	}
	/**
	 * 往(门牌信息)新增一条记录
	 * @param room
	 * @return
	 */
	@Override
	public int insertRoom(Room room){
		return sqlSession.insert("roomBase.insert_room",room);
	}
	/**
	 * 批量新增(门牌信息)信息
	 * @param roomList
	 * @return
	 */
	@Override
	public int insertRoomBatch(List<Room> roomList) {
		return sqlSession.insert("roomBase.insert_room_Batch",roomList);
	}
	
	/**
	 * 更新(门牌信息)信息
	 * @param room
	 * @return
	 */
	@Override
	public int updateRoom(Room room){
		return sqlSession.update("roomBase.update_room", room);
	}
	/**
	 * 批量更新(门牌信息)信息
	 * @param roomList
	 * @return
	 */
	@Override
	public int updateRoomBatch(List<Room> roomList) {
		return sqlSession.update("roomBase.update_room_Batch", roomList);
	}
	
	/**
	 * 根据序列号删除(门牌信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomLogic(java.math.BigInteger id){
		Room room = new Room();
		room.setId(id);
		room.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("roomBase.delete_room_Logic",room);
	}
	
	/**
	 * 根据Id 批量删除(门牌信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomLogicBatch(List<java.math.BigInteger> idList) {
		List<Room> delList = new java.util.ArrayList<Room>();
		for(java.math.BigInteger id:idList){
			Room room = new Room();
			room.setId(id);
			room.setSys0DelState(RecordState_DELETED);
			delList.add(room);
		}
		return sqlSession.update("roomBase.delete_room_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(门牌信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoom(java.math.BigInteger id){
//		return sqlSession.delete("roomBase.delete_room", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("roomBase.delete_room_Batch", idList);
//	}
	
	
}
