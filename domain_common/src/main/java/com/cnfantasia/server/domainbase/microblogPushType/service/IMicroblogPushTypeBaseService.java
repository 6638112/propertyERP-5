package com.cnfantasia.server.domainbase.microblogPushType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;

/**
 * 描述:(街坊消息推送类别表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogPushTypeBaseService {
	
	/**
	 * 根据条件查询(街坊消息推送类别表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogPushType> getMicroblogPushTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(街坊消息推送类别表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogPushType> getMicroblogPushTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(街坊消息推送类别表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogPushType> getMicroblogPushTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(街坊消息推送类别表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogPushType> getMicroblogPushTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(街坊消息推送类别表)信息
	 * @param id
	 * @return
	 */
	public MicroblogPushType getMicroblogPushTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(街坊消息推送类别表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogPushTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(街坊消息推送类别表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogPushTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(街坊消息推送类别表)新增一条记录
	 * @param microblogPushType
	 * @return
	 */
	public int insertMicroblogPushType(MicroblogPushType microblogPushType);
	/**
	 * 批量新增(街坊消息推送类别表)
	 * @param microblogPushTypeList
	 * @return
	 */
	public int insertMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList);
	/**
	 * 更新(街坊消息推送类别表)信息
	 * @param microblogPushType
	 * @return
	 */
	public int updateMicroblogPushType(MicroblogPushType microblogPushType);
	/**
	 * 批量更新(街坊消息推送类别表)信息
	 * @param microblogPushTypeList
	 * @return
	 */
	public int updateMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList);
	/**
	 * 根据序列号删除(街坊消息推送类别表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogPushTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(街坊消息推送类别表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogPushTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(街坊消息推送类别表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogPushType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(街坊消息推送类别表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogPushTypeBatch(List<java.math.BigInteger> idList);
	
}
