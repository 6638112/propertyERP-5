package com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasTPropertyProprietor.entity.RealRoomHasTPropertyProprietor;

/**
 * 描述:(房间业主信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealRoomHasTPropertyProprietorBaseDao {
	/**
	 * 根据条件查询(房间业主信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoomHasTPropertyProprietor> selectRealRoomHasTPropertyProprietorByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(房间业主信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealRoomHasTPropertyProprietor> selectRealRoomHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(房间业主信息表)信息
	 * @param id
	 * @return
	 */
	public RealRoomHasTPropertyProprietor selectRealRoomHasTPropertyProprietorBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间业主信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRealRoomHasTPropertyProprietorCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(房间业主信息表)新增一条记录
	 * @param realRoomHasTPropertyProprietor
	 * @return
	 */
	public int insertRealRoomHasTPropertyProprietor(RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietor);
	
	/**
	 * 批量新增(房间业主信息表)信息
	 * @param realRoomHasTPropertyProprietorList
	 * @return
	 */
	public int insertRealRoomHasTPropertyProprietorBatch(List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorList);
	
	/**
	 * 更新(房间业主信息表)信息
	 * @param realRoomHasTPropertyProprietor
	 * @return
	 */
	public int updateRealRoomHasTPropertyProprietor(RealRoomHasTPropertyProprietor realRoomHasTPropertyProprietor);
	
	/**
	 * 批量更新(房间业主信息表)信息
	 * @param realRoomHasTPropertyProprietorList
	 * @return
	 */
	public int updateRealRoomHasTPropertyProprietorBatch(List<RealRoomHasTPropertyProprietor> realRoomHasTPropertyProprietorList);
	
	/**
	 * 根据序列号删除(房间业主信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealRoomHasTPropertyProprietorLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(房间业主信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealRoomHasTPropertyProprietorLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(房间业主信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealRoomHasTPropertyProprietor(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间业主信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealRoomHasTPropertyProprietorBatch(List<java.math.BigInteger> idList);
	
	
}
