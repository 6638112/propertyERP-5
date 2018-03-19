package com.cnfantasia.server.domainbase.cookieInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;

/**
 * 描述:(记录用户的Cookie) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICookieInfoBaseService {
	
	/**
	 * 根据条件查询(记录用户的Cookie)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CookieInfo> getCookieInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(记录用户的Cookie)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CookieInfo> getCookieInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(记录用户的Cookie)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CookieInfo> getCookieInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(记录用户的Cookie)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CookieInfo> getCookieInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(记录用户的Cookie)信息
	 * @param id
	 * @return
	 */
	public CookieInfo getCookieInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCookieInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCookieInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(记录用户的Cookie)新增一条记录
	 * @param cookieInfo
	 * @return
	 */
	public int insertCookieInfo(CookieInfo cookieInfo);
	/**
	 * 批量新增(记录用户的Cookie)
	 * @param cookieInfoList
	 * @return
	 */
	public int insertCookieInfoBatch(List<CookieInfo> cookieInfoList);
	/**
	 * 更新(记录用户的Cookie)信息
	 * @param cookieInfo
	 * @return
	 */
	public int updateCookieInfo(CookieInfo cookieInfo);
	/**
	 * 批量更新(记录用户的Cookie)信息
	 * @param cookieInfoList
	 * @return
	 */
	public int updateCookieInfoBatch(List<CookieInfo> cookieInfoList);
	/**
	 * 根据序列号删除(记录用户的Cookie)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCookieInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(记录用户的Cookie)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCookieInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(记录用户的Cookie)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCookieInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(记录用户的Cookie)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCookieInfoBatch(List<java.math.BigInteger> idList);
	
}
