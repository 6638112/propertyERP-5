package com.cnfantasia.server.domainbase.ebuyProductIntroducePic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;

/**
 * 描述:(产品介绍图片信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductIntroducePicBaseDao {
	/**
	 * 根据条件查询(产品介绍图片信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductIntroducePic> selectEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(产品介绍图片信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductIntroducePic> selectEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(产品介绍图片信息)信息
	 * @param id
	 * @return
	 */
	public EbuyProductIntroducePic selectEbuyProductIntroducePicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductIntroducePicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(产品介绍图片信息)新增一条记录
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	public int insertEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic);
	
	/**
	 * 批量新增(产品介绍图片信息)信息
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
	 * 根据Id 批量删除(产品介绍图片信息)信息_逻辑删除
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
