package com.cnfantasia.server.domainbase.microblogPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;

/**
 * 描述:(微博图片信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogPicBaseService {
	
	/**
	 * 根据条件查询(微博图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogPic> getMicroblogPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微博图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogPic> getMicroblogPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微博图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogPic> getMicroblogPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微博图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogPic> getMicroblogPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微博图片信息)信息
	 * @param id
	 * @return
	 */
	public MicroblogPic getMicroblogPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微博图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微博图片信息)新增一条记录
	 * @param microblogPic
	 * @return
	 */
	public int insertMicroblogPic(MicroblogPic microblogPic);
	/**
	 * 批量新增(微博图片信息)
	 * @param microblogPicList
	 * @return
	 */
	public int insertMicroblogPicBatch(List<MicroblogPic> microblogPicList);
	/**
	 * 更新(微博图片信息)信息
	 * @param microblogPic
	 * @return
	 */
	public int updateMicroblogPic(MicroblogPic microblogPic);
	/**
	 * 批量更新(微博图片信息)信息
	 * @param microblogPicList
	 * @return
	 */
	public int updateMicroblogPicBatch(List<MicroblogPic> microblogPicList);
	/**
	 * 根据序列号删除(微博图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微博图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微博图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微博图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogPicBatch(List<java.math.BigInteger> idList);
	
}
