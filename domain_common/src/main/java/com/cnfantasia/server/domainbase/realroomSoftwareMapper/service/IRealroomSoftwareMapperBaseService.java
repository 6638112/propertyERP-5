package com.cnfantasia.server.domainbase.realroomSoftwareMapper.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realroomSoftwareMapper.entity.RealroomSoftwareMapper;

/**
 * 描述:(房间与物业软件映射) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealroomSoftwareMapperBaseService {
	
	/**
	 * 根据条件查询(房间与物业软件映射)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(房间与物业软件映射)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(房间与物业软件映射)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(房间与物业软件映射)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealroomSoftwareMapper> getRealroomSoftwareMapperByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(房间与物业软件映射)信息
	 * @param id
	 * @return
	 */
	public RealroomSoftwareMapper getRealroomSoftwareMapperBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRealroomSoftwareMapperCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRealroomSoftwareMapperCountDim(Map<String, Object> paramMap);
	/**
	 * 往(房间与物业软件映射)新增一条记录
	 * @param realroomSoftwareMapper
	 * @return
	 */
	public int insertRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper);
	/**
	 * 批量新增(房间与物业软件映射)
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	public int insertRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList);
	/**
	 * 更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapper
	 * @return
	 */
	public int updateRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper);
	/**
	 * 批量更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	public int updateRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList);
	/**
	 * 根据序列号删除(房间与物业软件映射)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealroomSoftwareMapperLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间与物业软件映射)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealroomSoftwareMapperLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间与物业软件映射)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealroomSoftwareMapper(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间与物业软件映射)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealroomSoftwareMapperBatch(List<java.math.BigInteger> idList);
	
}
