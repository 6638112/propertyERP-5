package com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;

/**
 * 描述:(产品介绍图片信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductIntroducePicBaseService {
	
	/**
	 * 根据条件查询(产品介绍图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(产品介绍图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(产品介绍图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(产品介绍图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(产品介绍图片信息)信息
	 * @param id
	 * @return
	 */
	public EbuyProductIntroducePic getEbuyProductIntroducePicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductIntroducePicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductIntroducePicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(产品介绍图片信息)新增一条记录
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	public int insertEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic);
	/**
	 * 批量新增(产品介绍图片信息)
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	public int insertEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList);
	/**
	 * 更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	public int updateEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic);
	/**
	 * 批量更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	public int updateEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList);
	/**
	 * 根据序列号删除(产品介绍图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductIntroducePicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(产品介绍图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductIntroducePicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(产品介绍图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductIntroducePic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(产品介绍图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductIntroducePicBatch(List<java.math.BigInteger> idList);
	
}
