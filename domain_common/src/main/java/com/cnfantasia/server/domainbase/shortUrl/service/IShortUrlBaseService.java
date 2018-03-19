package com.cnfantasia.server.domainbase.shortUrl.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.shortUrl.entity.ShortUrl;

/**
 * 描述:(短链接映射表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IShortUrlBaseService {
	
	/**
	 * 根据条件查询(短链接映射表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ShortUrl> getShortUrlByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(短链接映射表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ShortUrl> getShortUrlByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(短链接映射表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ShortUrl> getShortUrlByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(短链接映射表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ShortUrl> getShortUrlByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(短链接映射表)信息
	 * @param id
	 * @return
	 */
	public ShortUrl getShortUrlBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(短链接映射表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getShortUrlCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(短链接映射表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getShortUrlCountDim(Map<String, Object> paramMap);
	/**
	 * 往(短链接映射表)新增一条记录
	 * @param shortUrl
	 * @return
	 */
	public int insertShortUrl(ShortUrl shortUrl);
	/**
	 * 批量新增(短链接映射表)
	 * @param shortUrlList
	 * @return
	 */
	public int insertShortUrlBatch(List<ShortUrl> shortUrlList);
	/**
	 * 更新(短链接映射表)信息
	 * @param shortUrl
	 * @return
	 */
	public int updateShortUrl(ShortUrl shortUrl);
	/**
	 * 批量更新(短链接映射表)信息
	 * @param shortUrlList
	 * @return
	 */
	public int updateShortUrlBatch(List<ShortUrl> shortUrlList);
	/**
	 * 根据序列号删除(短链接映射表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteShortUrlLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(短链接映射表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteShortUrlLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(短链接映射表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteShortUrl(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(短链接映射表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteShortUrlBatch(List<java.math.BigInteger> idList);
	
}
