package com.cnfantasia.server.domainbase.userXanaduRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * 描述:(用户世外桃源状态信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserXanaduRecordBaseDao {
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserXanaduRecord> selectUserXanaduRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserXanaduRecord> selectUserXanaduRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户世外桃源状态信息)信息
	 * @param id
	 * @return
	 */
	public UserXanaduRecord selectUserXanaduRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserXanaduRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户世外桃源状态信息)新增一条记录
	 * @param userXanaduRecord
	 * @return
	 */
	public int insertUserXanaduRecord(UserXanaduRecord userXanaduRecord);
	
	/**
	 * 批量新增(用户世外桃源状态信息)信息
	 * @param userXanaduRecordList
	 * @return
	 */
	public int insertUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList);
	
	/**
	 * 更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecord
	 * @return
	 */
	public int updateUserXanaduRecord(UserXanaduRecord userXanaduRecord);
	
	/**
	 * 批量更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecordList
	 * @return
	 */
	public int updateUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList);
	
	/**
	 * 根据序列号删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserXanaduRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserXanaduRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户世外桃源状态信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserXanaduRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户世外桃源状态信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserXanaduRecordBatch(List<java.math.BigInteger> idList);
	
	
}
