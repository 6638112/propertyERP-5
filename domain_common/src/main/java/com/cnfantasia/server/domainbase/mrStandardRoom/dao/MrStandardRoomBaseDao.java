package com.cnfantasia.server.domainbase.mrStandardRoom.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;

/**
 * 描述:(抄表收费标准(房间)) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MrStandardRoomBaseDao extends AbstractBaseDao implements IMrStandardRoomBaseDao{
	/**
	 * 根据条件查询(抄表收费标准(房间))信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrStandardRoom> selectMrStandardRoomByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("mrStandardRoomBase.select_mrStandardRoom",paramMap);
	}
	/**
	 * 按条件分页查询(抄表收费标准(房间))信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrStandardRoom> selectMrStandardRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMrStandardRoomCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MrStandardRoom> resMap= sqlSession.selectList("mrStandardRoomBase.select_mrStandardRoom_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抄表收费标准(房间))信息
	 * @param id
	 * @return
	 */
	@Override
	public MrStandardRoom selectMrStandardRoomBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("mrStandardRoomBase.select_mrStandardRoom_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(房间))记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMrStandardRoomCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("mrStandardRoomBase.select_mrStandardRoom_count", paramMap);
	}
	/**
	 * 往(抄表收费标准(房间))新增一条记录
	 * @param mrStandardRoom
	 * @return
	 */
	@Override
	public int insertMrStandardRoom(MrStandardRoom mrStandardRoom){
		return sqlSession.insert("mrStandardRoomBase.insert_mrStandardRoom",mrStandardRoom);
	}
	/**
	 * 批量新增(抄表收费标准(房间))信息
	 * @param mrStandardRoomList
	 * @return
	 */
	@Override
	public int insertMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList) {
		if (mrStandardRoomList == null || mrStandardRoomList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = mrStandardRoomList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MrStandardRoom> batchList = mrStandardRoomList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("mrStandardRoomBase.insert_mrStandardRoom_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(抄表收费标准(房间))信息
	 * @param mrStandardRoom
	 * @return
	 */
	@Override
	public int updateMrStandardRoom(MrStandardRoom mrStandardRoom){
		return sqlSession.update("mrStandardRoomBase.update_mrStandardRoom", mrStandardRoom);
	}
	/**
	 * 批量更新(抄表收费标准(房间))信息
	 * @param mrStandardRoomList
	 * @return
	 */
	@Override
	public int updateMrStandardRoomBatch(List<MrStandardRoom> mrStandardRoomList) {
		if (mrStandardRoomList == null || mrStandardRoomList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("mrStandardRoomBase.update_mrStandardRoom_Batch", mrStandardRoomList);
	}
	
	/**
	 * 根据序列号删除(抄表收费标准(房间))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrStandardRoomLogic(java.math.BigInteger id){
		MrStandardRoom mrStandardRoom = new MrStandardRoom();
		mrStandardRoom.setId(id);
		mrStandardRoom.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("mrStandardRoomBase.delete_mrStandardRoom_Logic",mrStandardRoom);
	}
	
	/**
	 * 根据Id 批量删除(抄表收费标准(房间))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrStandardRoomLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MrStandardRoom> delList = new java.util.ArrayList<MrStandardRoom>();
		for(java.math.BigInteger id:idList){
			MrStandardRoom mrStandardRoom = new MrStandardRoom();
			mrStandardRoom.setId(id);
			mrStandardRoom.setSys0DelState(RecordState_DELETED);
			delList.add(mrStandardRoom);
		}
		return sqlSession.update("mrStandardRoomBase.delete_mrStandardRoom_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抄表收费标准(房间))信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardRoom(java.math.BigInteger id){
//		return sqlSession.delete("mrStandardRoomBase.delete_mrStandardRoom", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(房间))信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardRoomBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("mrStandardRoomBase.delete_mrStandardRoom_Batch", idList);
//	}
	
	
}
