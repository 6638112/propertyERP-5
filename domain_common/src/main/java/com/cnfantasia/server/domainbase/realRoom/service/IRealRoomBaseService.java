package com.cnfantasia.server.domainbase.realRoom.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * 描述:(真实房间) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomBaseService {
	
	/**
	 * 根据条件查询(真实房间)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoom> getRealRoomByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(真实房间)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoom> getRealRoomByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(真实房间)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoom> getRealRoomByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(真实房间)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoom> getRealRoomByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(真实房间)信息
	 * @param id
	 * @return
	 */
	public RealRoom getRealRoomBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(真实房间)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(真实房间)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomCountDim(Map<String,Object> paramMap);
	/**
	 * 往(真实房间)新增一条记录
	 * @param realRoom
	 * @return
	 */
	public int insertRealRoom(RealRoom realRoom);
	/**
	 * 批量新增(真实房间)
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
	 * 根据序列号批量删除(真实房间)信息_逻辑删除
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
