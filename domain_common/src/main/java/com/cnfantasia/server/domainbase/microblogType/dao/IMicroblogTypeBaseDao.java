package com.cnfantasia.server.domainbase.microblogType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * 描述:(微博类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogTypeBaseDao {
	/**
	 * 根据条件查询(微博类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogType> selectMicroblogTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(微博类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogType> selectMicroblogTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(微博类别)信息
	 * @param id
	 * @return
	 */
	public MicroblogType selectMicroblogTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMicroblogTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(微博类别)新增一条记录
	 * @param microblogType
	 * @return
	 */
	public int insertMicroblogType(MicroblogType microblogType);
	
	/**
	 * 批量新增(微博类别)信息
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
	 * 根据Id 批量删除(微博类别)信息_逻辑删除
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
