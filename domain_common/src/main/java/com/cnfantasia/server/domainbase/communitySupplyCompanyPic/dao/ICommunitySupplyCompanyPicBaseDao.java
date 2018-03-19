package com.cnfantasia.server.domainbase.communitySupplyCompanyPic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;

/**
 * 描述:(商家图片) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyCompanyPicBaseDao {
	/**
	 * 根据条件查询(商家图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyCompanyPic> selectCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商家图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyCompanyPic> selectCommunitySupplyCompanyPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyCompanyPic selectCommunitySupplyCompanyPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商家图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyCompanyPicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	public int insertCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic);
	
	/**
	 * 批量新增(商家图片)信息
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	public int insertCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList);
	
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyCompanyPic
	 * @return
	 */
	public int updateCommunitySupplyCompanyPic(CommunitySupplyCompanyPic communitySupplyCompanyPic);
	
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyCompanyPicList
	 * @return
	 */
	public int updateCommunitySupplyCompanyPicBatch(List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList);
	
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyCompanyPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyCompanyPicLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyCompanyPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyCompanyPicBatch(List<java.math.BigInteger> idList);
	
	
}
