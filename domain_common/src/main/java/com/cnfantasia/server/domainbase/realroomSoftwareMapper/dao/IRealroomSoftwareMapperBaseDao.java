package com.cnfantasia.server.domainbase.realroomSoftwareMapper.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareMapper.entity.RealroomSoftwareMapper;

/**
 * 描述:(房间与物业软件映射) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealroomSoftwareMapperBaseDao {
	/**
	 * 根据条件查询(房间与物业软件映射)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealroomSoftwareMapper> selectRealroomSoftwareMapperByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(房间与物业软件映射)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealroomSoftwareMapper> selectRealroomSoftwareMapperByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(房间与物业软件映射)信息
	 * @param id
	 * @return
	 */
	public RealroomSoftwareMapper selectRealroomSoftwareMapperBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRealroomSoftwareMapperCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(房间与物业软件映射)新增一条记录
	 * @param realroomSoftwareMapper
	 * @return
	 */
	public int insertRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper);
	
	/**
	 * 批量新增(房间与物业软件映射)信息
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
	 * 根据Id 批量删除(房间与物业软件映射)信息_逻辑删除
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
