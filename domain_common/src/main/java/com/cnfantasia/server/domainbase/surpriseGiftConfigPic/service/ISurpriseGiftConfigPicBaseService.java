package com.cnfantasia.server.domainbase.surpriseGiftConfigPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * 描述:(意外惊喜配置图标) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISurpriseGiftConfigPicBaseService {
	
	/**
	 * 根据条件查询(意外惊喜配置图标)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(意外惊喜配置图标)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(意外惊喜配置图标)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(意外惊喜配置图标)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(意外惊喜配置图标)信息
	 * @param id
	 * @return
	 */
	public SurpriseGiftConfigPic getSurpriseGiftConfigPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意外惊喜配置图标)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSurpriseGiftConfigPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(意外惊喜配置图标)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSurpriseGiftConfigPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(意外惊喜配置图标)新增一条记录
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	public int insertSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic);
	/**
	 * 批量新增(意外惊喜配置图标)
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	public int insertSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList);
	/**
	 * 更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	public int updateSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic);
	/**
	 * 批量更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	public int updateSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList);
	/**
	 * 根据序列号删除(意外惊喜配置图标)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSurpriseGiftConfigPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(意外惊喜配置图标)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSurpriseGiftConfigPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(意外惊喜配置图标)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSurpriseGiftConfigPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜配置图标)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSurpriseGiftConfigPicBatch(List<java.math.BigInteger> idList);
	
}
