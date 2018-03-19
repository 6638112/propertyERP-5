package com.cnfantasia.server.domainbase.cookieInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cookieInfo.dao.ICookieInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;

/**
 * 描述:(记录用户的Cookie) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CookieInfoBaseService implements ICookieInfoBaseService{
	
	private ICookieInfoBaseDao cookieInfoBaseDao;
	public void setCookieInfoBaseDao(ICookieInfoBaseDao cookieInfoBaseDao) {
		this.cookieInfoBaseDao = cookieInfoBaseDao;
	}
	/**
	 * 根据条件查询(记录用户的Cookie)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CookieInfo> getCookieInfoByCondition(Map<String,Object> paramMap){
		return cookieInfoBaseDao.selectCookieInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(记录用户的Cookie)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CookieInfo> getCookieInfoByConditionDim(Map<String,Object> paramMap){
		return cookieInfoBaseDao.selectCookieInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(记录用户的Cookie)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CookieInfo> getCookieInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cookieInfoBaseDao.selectCookieInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(记录用户的Cookie)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CookieInfo> getCookieInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cookieInfoBaseDao.selectCookieInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(记录用户的Cookie)信息
	 * @param id
	 * @return
	 */
	@Override
	public CookieInfo getCookieInfoBySeqId(java.math.BigInteger id){
		return cookieInfoBaseDao.selectCookieInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCookieInfoCount(Map<String,Object> paramMap){
		return cookieInfoBaseDao.selectCookieInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCookieInfoCountDim(Map<String,Object> paramMap){
		return cookieInfoBaseDao.selectCookieInfoCount(paramMap,true);
	}
	/**
	 * 往(记录用户的Cookie)新增一条记录
	 * @param cookieInfo
	 * @return
	 */
	@Override
	public int insertCookieInfo(CookieInfo cookieInfo){
		return cookieInfoBaseDao.insertCookieInfo(cookieInfo);
	}
	/**
	 * 批量新增(记录用户的Cookie)
	 * @param cookieInfoList
	 * @return
	 */
	@Override
	public int insertCookieInfoBatch(List<CookieInfo> cookieInfoList){
		return cookieInfoBaseDao.insertCookieInfoBatch(cookieInfoList);
	}
	/**
	 * 更新(记录用户的Cookie)信息
	 * @param cookieInfo
	 * @return
	 */
	@Override
	public int updateCookieInfo(CookieInfo cookieInfo){
		return cookieInfoBaseDao.updateCookieInfo(cookieInfo);
	}
	/**
	 * 批量更新(记录用户的Cookie)信息
	 * @param cookieInfoList
	 * @return
	 */
	@Override
	public int updateCookieInfoBatch(List<CookieInfo> cookieInfoList){
		return cookieInfoBaseDao.updateCookieInfoBatch(cookieInfoList);
	}
	/**
	 * 根据序列号删除(记录用户的Cookie)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCookieInfoLogic(java.math.BigInteger id){
		return cookieInfoBaseDao.deleteCookieInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(记录用户的Cookie)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCookieInfoLogicBatch(List<java.math.BigInteger> idList){
		return cookieInfoBaseDao.deleteCookieInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(记录用户的Cookie)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCookieInfo(java.math.BigInteger id){
//		return cookieInfoBaseDao.deleteCookieInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(记录用户的Cookie)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCookieInfoBatch(List<java.math.BigInteger> idList){
//		return cookieInfoBaseDao.deleteCookieInfoBatch(idList);
//	}
	
}
