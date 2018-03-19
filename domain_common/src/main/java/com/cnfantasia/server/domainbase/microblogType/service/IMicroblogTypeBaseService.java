package com.cnfantasia.server.domainbase.microblogType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * 描述:(微博类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogTypeBaseService {
	
	/**
	 * 根据条件查询(微博类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogType> getMicroblogTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微博类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogType> getMicroblogTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微博类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogType> getMicroblogTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微博类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogType> getMicroblogTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微博类别)信息
	 * @param id
	 * @return
	 */
	public MicroblogType getMicroblogTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微博类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微博类别)新增一条记录
	 * @param microblogType
	 * @return
	 */
	public int insertMicroblogType(MicroblogType microblogType);
	/**
	 * 批量新增(微博类别)
	 * @param microblogTypeList
	 * @return
	 */
	public int insertMicroblogTypeBatch(List<MicroblogType> microblogTypeList);
	/**
	 * 更新(微博类别)信息
	 * @param microblogType
	 * @return
	 */
	public int updateMicroblogType(MicroblogType microblogType);
	/**
	 * 批量更新(微博类别)信息
	 * @param microblogTypeList
	 * @return
	 */
	public int updateMicroblogTypeBatch(List<MicroblogType> microblogTypeList);
	/**
	 * 根据序列号删除(微博类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微博类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微博类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微博类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogTypeBatch(List<java.math.BigInteger> idList);
	
}
