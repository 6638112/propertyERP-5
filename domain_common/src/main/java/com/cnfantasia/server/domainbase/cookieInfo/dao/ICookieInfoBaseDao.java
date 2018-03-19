package com.cnfantasia.server.domainbase.cookieInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;

/**
 * 描述:(记录用户的Cookie) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICookieInfoBaseDao {
	/**
	 * 根据条件查询(记录用户的Cookie)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CookieInfo> selectCookieInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(记录用户的Cookie)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CookieInfo> selectCookieInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(记录用户的Cookie)信息
	 * @param id
	 * @return
	 */
	public CookieInfo selectCookieInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCookieInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(记录用户的Cookie)新增一条记录
	 * @param cookieInfo
	 * @return
	 */
	public int insertCookieInfo(CookieInfo cookieInfo);
	
	/**
	 * 批量新增(记录用户的Cookie)信息
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
	 * 根据Id 批量删除(记录用户的Cookie)信息_逻辑删除
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
