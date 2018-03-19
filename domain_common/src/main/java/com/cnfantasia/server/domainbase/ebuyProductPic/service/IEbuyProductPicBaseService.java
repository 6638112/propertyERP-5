package com.cnfantasia.server.domainbase.ebuyProductPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;

/**
 * 描述:(产品图片信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductPicBaseService {
	
	/**
	 * 根据条件查询(产品图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductPic> getEbuyProductPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(产品图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductPic> getEbuyProductPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(产品图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductPic> getEbuyProductPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(产品图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductPic> getEbuyProductPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(产品图片信息)信息
	 * @param id
	 * @return
	 */
	public EbuyProductPic getEbuyProductPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(产品图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(产品图片信息)新增一条记录
	 * @param ebuyProductPic
	 * @return
	 */
	public int insertEbuyProductPic(EbuyProductPic ebuyProductPic);
	/**
	 * 批量新增(产品图片信息)
	 * @param ebuyProductPicList
	 * @return
	 */
	public int insertEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList);
	/**
	 * 更新(产品图片信息)信息
	 * @param ebuyProductPic
	 * @return
	 */
	public int updateEbuyProductPic(EbuyProductPic ebuyProductPic);
	/**
	 * 批量更新(产品图片信息)信息
	 * @param ebuyProductPicList
	 * @return
	 */
	public int updateEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList);
	/**
	 * 根据序列号删除(产品图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(产品图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(产品图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(产品图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductPicBatch(List<java.math.BigInteger> idList);
	
}
