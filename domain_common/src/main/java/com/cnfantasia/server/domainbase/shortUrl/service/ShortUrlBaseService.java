package com.cnfantasia.server.domainbase.shortUrl.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.shortUrl.dao.IShortUrlBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shortUrl.entity.ShortUrl;

/**
 * 描述:(短链接映射表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ShortUrlBaseService implements IShortUrlBaseService{
	
	private IShortUrlBaseDao shortUrlBaseDao;
	public void setShortUrlBaseDao(IShortUrlBaseDao shortUrlBaseDao) {
		this.shortUrlBaseDao = shortUrlBaseDao;
	}
	/**
	 * 根据条件查询(短链接映射表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShortUrl> getShortUrlByCondition(Map<String,Object> paramMap){
		return shortUrlBaseDao.selectShortUrlByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(短链接映射表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ShortUrl> getShortUrlByConditionDim(Map<String,Object> paramMap){
		return shortUrlBaseDao.selectShortUrlByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(短链接映射表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShortUrl> getShortUrlByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return shortUrlBaseDao.selectShortUrlByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(短链接映射表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ShortUrl> getShortUrlByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return shortUrlBaseDao.selectShortUrlByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(短链接映射表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShortUrl getShortUrlBySeqId(java.math.BigInteger id){
		return shortUrlBaseDao.selectShortUrlBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(短链接映射表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShortUrlCount(Map<String,Object> paramMap){
		return shortUrlBaseDao.selectShortUrlCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(短链接映射表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getShortUrlCountDim(Map<String,Object> paramMap){
		return shortUrlBaseDao.selectShortUrlCount(paramMap,true);
	}
	/**
	 * 往(短链接映射表)新增一条记录
	 * @param shortUrl
	 * @return
	 */
	@Override
	public int insertShortUrl(ShortUrl shortUrl){
		return shortUrlBaseDao.insertShortUrl(shortUrl);
	}
	/**
	 * 批量新增(短链接映射表)
	 * @param shortUrlList
	 * @return
	 */
	@Override
	public int insertShortUrlBatch(List<ShortUrl> shortUrlList){
		return shortUrlBaseDao.insertShortUrlBatch(shortUrlList);
	}
	/**
	 * 更新(短链接映射表)信息
	 * @param shortUrl
	 * @return
	 */
	@Override
	public int updateShortUrl(ShortUrl shortUrl){
		return shortUrlBaseDao.updateShortUrl(shortUrl);
	}
	/**
	 * 批量更新(短链接映射表)信息
	 * @param shortUrlList
	 * @return
	 */
	@Override
	public int updateShortUrlBatch(List<ShortUrl> shortUrlList){
		return shortUrlBaseDao.updateShortUrlBatch(shortUrlList);
	}
	/**
	 * 根据序列号删除(短链接映射表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShortUrlLogic(java.math.BigInteger id){
		return shortUrlBaseDao.deleteShortUrlLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(短链接映射表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShortUrlLogicBatch(List<java.math.BigInteger> idList){
		return shortUrlBaseDao.deleteShortUrlLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(短链接映射表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShortUrl(java.math.BigInteger id){
//		return shortUrlBaseDao.deleteShortUrl(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(短链接映射表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShortUrlBatch(List<java.math.BigInteger> idList){
//		return shortUrlBaseDao.deleteShortUrlBatch(idList);
//	}
	
}
