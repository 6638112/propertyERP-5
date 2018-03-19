package com.cnfantasia.server.domainbase.redpointModelcodeConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.redpointModelcodeConfig.dao.IRedpointModelcodeConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointModelcodeConfig.entity.RedpointModelcodeConfig;

/**
 * 描述:(红点模块编码配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RedpointModelcodeConfigBaseService implements IRedpointModelcodeConfigBaseService{
	
	private IRedpointModelcodeConfigBaseDao redpointModelcodeConfigBaseDao;
	public void setRedpointModelcodeConfigBaseDao(IRedpointModelcodeConfigBaseDao redpointModelcodeConfigBaseDao) {
		this.redpointModelcodeConfigBaseDao = redpointModelcodeConfigBaseDao;
	}
	/**
	 * 根据条件查询(红点模块编码配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByCondition(Map<String,Object> paramMap){
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(红点模块编码配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByConditionDim(Map<String,Object> paramMap){
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(红点模块编码配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(红点模块编码配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(红点模块编码配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointModelcodeConfig getRedpointModelcodeConfigBySeqId(java.math.BigInteger id){
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointModelcodeConfigCount(Map<String,Object> paramMap){
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointModelcodeConfigCountDim(Map<String,Object> paramMap){
		return redpointModelcodeConfigBaseDao.selectRedpointModelcodeConfigCount(paramMap,true);
	}
	/**
	 * 往(红点模块编码配置表)新增一条记录
	 * @param redpointModelcodeConfig
	 * @return
	 */
	@Override
	public int insertRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig){
		return redpointModelcodeConfigBaseDao.insertRedpointModelcodeConfig(redpointModelcodeConfig);
	}
	/**
	 * 批量新增(红点模块编码配置表)
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	@Override
	public int insertRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList){
		return redpointModelcodeConfigBaseDao.insertRedpointModelcodeConfigBatch(redpointModelcodeConfigList);
	}
	/**
	 * 更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfig
	 * @return
	 */
	@Override
	public int updateRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig){
		return redpointModelcodeConfigBaseDao.updateRedpointModelcodeConfig(redpointModelcodeConfig);
	}
	/**
	 * 批量更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	@Override
	public int updateRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList){
		return redpointModelcodeConfigBaseDao.updateRedpointModelcodeConfigBatch(redpointModelcodeConfigList);
	}
	/**
	 * 根据序列号删除(红点模块编码配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointModelcodeConfigLogic(java.math.BigInteger id){
		return redpointModelcodeConfigBaseDao.deleteRedpointModelcodeConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(红点模块编码配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointModelcodeConfigLogicBatch(List<java.math.BigInteger> idList){
		return redpointModelcodeConfigBaseDao.deleteRedpointModelcodeConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(红点模块编码配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointModelcodeConfig(java.math.BigInteger id){
//		return redpointModelcodeConfigBaseDao.deleteRedpointModelcodeConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点模块编码配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointModelcodeConfigBatch(List<java.math.BigInteger> idList){
//		return redpointModelcodeConfigBaseDao.deleteRedpointModelcodeConfigBatch(idList);
//	}
	
}
