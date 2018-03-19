package com.cnfantasia.server.domainbase.ebuyProductFightPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductFightPic.entity.EbuyProductFightPic;

/**
 * 描述:(拼购产品列表图片信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductFightPicBaseService {
	
	/**
	 * 根据条件查询(拼购产品列表图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductFightPic> getEbuyProductFightPicByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(拼购产品列表图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductFightPic> getEbuyProductFightPicByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(拼购产品列表图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductFightPic> getEbuyProductFightPicByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(拼购产品列表图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductFightPic> getEbuyProductFightPicByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(拼购产品列表图片信息)信息
	 * @param id
	 * @return
	 */
	public EbuyProductFightPic getEbuyProductFightPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼购产品列表图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductFightPicCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼购产品列表图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductFightPicCountDim(Map<String, Object> paramMap);
	/**
	 * 往(拼购产品列表图片信息)新增一条记录
	 * @param ebuyProductFightPic
	 * @return
	 */
	public int insertEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic);
	/**
	 * 批量新增(拼购产品列表图片信息)
	 * @param ebuyProductFightPicList
	 * @return
	 */
	public int insertEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList);
	/**
	 * 更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPic
	 * @return
	 */
	public int updateEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic);
	/**
	 * 批量更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPicList
	 * @return
	 */
	public int updateEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList);
	/**
	 * 根据序列号删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductFightPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductFightPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼购产品列表图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductFightPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼购产品列表图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductFightPicBatch(List<java.math.BigInteger> idList);
	
}
