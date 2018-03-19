package com.cnfantasia.server.domainbase.userXanaduRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * 描述:(用户世外桃源状态信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserXanaduRecordBaseService {
	
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserXanaduRecord> getUserXanaduRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserXanaduRecord> getUserXanaduRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserXanaduRecord> getUserXanaduRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserXanaduRecord> getUserXanaduRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户世外桃源状态信息)信息
	 * @param id
	 * @return
	 */
	public UserXanaduRecord getUserXanaduRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserXanaduRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserXanaduRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户世外桃源状态信息)新增一条记录
	 * @param userXanaduRecord
	 * @return
	 */
	public int insertUserXanaduRecord(UserXanaduRecord userXanaduRecord);
	/**
	 * 批量新增(用户世外桃源状态信息)
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
	 * 根据序列号批量删除(用户世外桃源状态信息)信息_逻辑删除
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
