package com.cnfantasia.server.domainbase.communityPinyipinContent.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;

/**
 * 描述:(拼一拼内容表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityPinyipinContentBaseService {
	
	/**
	 * 根据条件查询(拼一拼内容表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(拼一拼内容表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(拼一拼内容表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(拼一拼内容表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(拼一拼内容表)信息
	 * @param id
	 * @return
	 */
	public CommunityPinyipinContent getCommunityPinyipinContentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼一拼内容表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinContentCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼一拼内容表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinContentCountDim(Map<String,Object> paramMap);
	/**
	 * 往(拼一拼内容表)新增一条记录
	 * @param communityPinyipinContent
	 * @return
	 */
	public int insertCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent);
	/**
	 * 批量新增(拼一拼内容表)
	 * @param communityPinyipinContentList
	 * @return
	 */
	public int insertCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList);
	/**
	 * 更新(拼一拼内容表)信息
	 * @param communityPinyipinContent
	 * @return
	 */
	public int updateCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent);
	/**
	 * 批量更新(拼一拼内容表)信息
	 * @param communityPinyipinContentList
	 * @return
	 */
	public int updateCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList);
	/**
	 * 根据序列号删除(拼一拼内容表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityPinyipinContentLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼一拼内容表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityPinyipinContentLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼一拼内容表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityPinyipinContent(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼一拼内容表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityPinyipinContentBatch(List<java.math.BigInteger> idList);
	
}
