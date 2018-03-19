package com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.entity.RealRoomHasTPropertyLessee;

import java.util.List;
import java.util.Map;

/**
 * 描述:(房间租户信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomHasTPropertyLesseeBaseService {
	
	/**
	 * 根据条件查询(房间租户信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(房间租户信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(房间租户信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(房间租户信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(房间租户信息表)信息
	 * @param id
	 * @return
	 */
	public RealRoomHasTPropertyLessee getRealRoomHasTPropertyLesseeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间租户信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomHasTPropertyLesseeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间租户信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRealRoomHasTPropertyLesseeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(房间租户信息表)新增一条记录
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	public int insertRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee);
	/**
	 * 批量新增(房间租户信息表)
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	public int insertRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList);
	/**
	 * 更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	public int updateRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee);
	/**
	 * 批量更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	public int updateRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList);
	/**
	 * 根据序列号删除(房间租户信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealRoomHasTPropertyLesseeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间租户信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealRoomHasTPropertyLesseeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间租户信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealRoomHasTPropertyLessee(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间租户信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealRoomHasTPropertyLesseeBatch(List<java.math.BigInteger> idList);
	
}
