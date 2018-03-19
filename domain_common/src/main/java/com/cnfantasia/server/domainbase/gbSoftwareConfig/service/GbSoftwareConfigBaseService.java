package com.cnfantasia.server.domainbase.gbSoftwareConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.gbSoftwareConfig.dao.IGbSoftwareConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;

/**
 * 描述:(小区物业软件配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GbSoftwareConfigBaseService implements IGbSoftwareConfigBaseService{
	
	private IGbSoftwareConfigBaseDao gbSoftwareConfigBaseDao;
	public void setGbSoftwareConfigBaseDao(IGbSoftwareConfigBaseDao gbSoftwareConfigBaseDao) {
		this.gbSoftwareConfigBaseDao = gbSoftwareConfigBaseDao;
	}
	/**
	 * 根据条件查询(小区物业软件配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> getGbSoftwareConfigByCondition(Map<String,Object> paramMap){
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区物业软件配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> getGbSoftwareConfigByConditionDim(Map<String,Object> paramMap){
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区物业软件配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> getGbSoftwareConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区物业软件配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GbSoftwareConfig> getGbSoftwareConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区物业软件配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public GbSoftwareConfig getGbSoftwareConfigBySeqId(java.math.BigInteger id){
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGbSoftwareConfigCount(Map<String,Object> paramMap){
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGbSoftwareConfigCountDim(Map<String,Object> paramMap){
		return gbSoftwareConfigBaseDao.selectGbSoftwareConfigCount(paramMap,true);
	}
	/**
	 * 往(小区物业软件配置)新增一条记录
	 * @param gbSoftwareConfig
	 * @return
	 */
	@Override
	public int insertGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig){
		return gbSoftwareConfigBaseDao.insertGbSoftwareConfig(gbSoftwareConfig);
	}
	/**
	 * 批量新增(小区物业软件配置)
	 * @param gbSoftwareConfigList
	 * @return
	 */
	@Override
	public int insertGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList){
		return gbSoftwareConfigBaseDao.insertGbSoftwareConfigBatch(gbSoftwareConfigList);
	}
	/**
	 * 更新(小区物业软件配置)信息
	 * @param gbSoftwareConfig
	 * @return
	 */
	@Override
	public int updateGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig){
		return gbSoftwareConfigBaseDao.updateGbSoftwareConfig(gbSoftwareConfig);
	}
	/**
	 * 批量更新(小区物业软件配置)信息
	 * @param gbSoftwareConfigList
	 * @return
	 */
	@Override
	public int updateGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList){
		return gbSoftwareConfigBaseDao.updateGbSoftwareConfigBatch(gbSoftwareConfigList);
	}
	/**
	 * 根据序列号删除(小区物业软件配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGbSoftwareConfigLogic(java.math.BigInteger id){
		return gbSoftwareConfigBaseDao.deleteGbSoftwareConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区物业软件配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGbSoftwareConfigLogicBatch(List<java.math.BigInteger> idList){
		return gbSoftwareConfigBaseDao.deleteGbSoftwareConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区物业软件配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGbSoftwareConfig(java.math.BigInteger id){
//		return gbSoftwareConfigBaseDao.deleteGbSoftwareConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区物业软件配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGbSoftwareConfigBatch(List<java.math.BigInteger> idList){
//		return gbSoftwareConfigBaseDao.deleteGbSoftwareConfigBatch(idList);
//	}
	
}
