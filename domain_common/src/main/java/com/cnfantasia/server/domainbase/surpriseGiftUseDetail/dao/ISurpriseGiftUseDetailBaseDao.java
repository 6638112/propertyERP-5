package com.cnfantasia.server.domainbase.surpriseGiftUseDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.surpriseGiftUseDetail.entity.SurpriseGiftUseDetail;

/**
 * 描述:(意外惊喜的优惠券使用详情) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISurpriseGiftUseDetailBaseDao {
	/**
	 * 根据条件查询(意外惊喜的优惠券使用详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SurpriseGiftUseDetail> selectSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(意外惊喜的优惠券使用详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SurpriseGiftUseDetail> selectSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(意外惊喜的优惠券使用详情)信息
	 * @param id
	 * @return
	 */
	public SurpriseGiftUseDetail selectSurpriseGiftUseDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(意外惊喜的优惠券使用详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectSurpriseGiftUseDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(意外惊喜的优惠券使用详情)新增一条记录
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	public int insertSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail);
	
	/**
	 * 批量新增(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	public int insertSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList);
	
	/**
	 * 更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	public int updateSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail);
	
	/**
	 * 批量更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	public int updateSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList);
	
	/**
	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSurpriseGiftUseDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSurpriseGiftUseDetailLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSurpriseGiftUseDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜的优惠券使用详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSurpriseGiftUseDetailBatch(List<java.math.BigInteger> idList);
	
	
}
