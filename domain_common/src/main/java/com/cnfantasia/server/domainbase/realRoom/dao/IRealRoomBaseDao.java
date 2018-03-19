package com.cnfantasia.server.domainbase.realRoom.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * 描述:(真实房间) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomBaseDao {
	/**
	 * 根据条件查询(真实房间)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoom> selectRealRoomByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(真实房间)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoom> selectRealRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(真实房间)信息
	 * @param id
	 * @return
	 */
	public RealRoom selectRealRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(真实房间)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRealRoomCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(真实房间)新增一条记录
	 * @param realRoom
	 * @return
	 */
	public int insertRealRoom(RealRoom realRoom);
	
	/**
	 * 批量新增(真实房间)信息
	 * @param realRoomList
	 * @return
	 */
	public int insertRealRoomBatch(List<RealRoom> realRoomList);
	
	/**
	 * 更新(真实房间)信息
	 * @param realRoom
	 * @return
	 */
	public int updateRealRoom(RealRoom realRoom);
	
	/**
	 * 批量更新(真实房间)信息
	 * @param realRoomList
	 * @return
	 */
	public int updateRealRoomBatch(List<RealRoom> realRoomList);
	
	/**
	 * 根据序列号删除(真实房间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealRoomLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(真实房间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealRoomLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(真实房间)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealRoom(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(真实房间)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealRoomBatch(List<java.math.BigInteger> idList);
	
	
}
