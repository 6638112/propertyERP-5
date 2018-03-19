package com.cnfantasia.server.domainbase.dredgeTypePriceConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeTypePriceConfig.dao.IDredgeTypePriceConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;

/**
 * 描述:(维修价格配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeTypePriceConfigBaseService implements IDredgeTypePriceConfigBaseService{
	
	private IDredgeTypePriceConfigBaseDao dredgeTypePriceConfigBaseDao;
	public void setDredgeTypePriceConfigBaseDao(IDredgeTypePriceConfigBaseDao dredgeTypePriceConfigBaseDao) {
		this.dredgeTypePriceConfigBaseDao = dredgeTypePriceConfigBaseDao;
	}
	/**
	 * 根据条件查询(维修价格配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> getDredgeTypePriceConfigByCondition(Map<String,Object> paramMap){
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修价格配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> getDredgeTypePriceConfigByConditionDim(Map<String,Object> paramMap){
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修价格配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> getDredgeTypePriceConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修价格配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeTypePriceConfig> getDredgeTypePriceConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修价格配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeTypePriceConfig getDredgeTypePriceConfigBySeqId(java.math.BigInteger id){
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeTypePriceConfigCount(Map<String,Object> paramMap){
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeTypePriceConfigCountDim(Map<String,Object> paramMap){
		return dredgeTypePriceConfigBaseDao.selectDredgeTypePriceConfigCount(paramMap,true);
	}
	/**
	 * 往(维修价格配置表)新增一条记录
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	@Override
	public int insertDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig){
		return dredgeTypePriceConfigBaseDao.insertDredgeTypePriceConfig(dredgeTypePriceConfig);
	}
	/**
	 * 批量新增(维修价格配置表)
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	@Override
	public int insertDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList){
		return dredgeTypePriceConfigBaseDao.insertDredgeTypePriceConfigBatch(dredgeTypePriceConfigList);
	}
	/**
	 * 更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	@Override
	public int updateDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig){
		return dredgeTypePriceConfigBaseDao.updateDredgeTypePriceConfig(dredgeTypePriceConfig);
	}
	/**
	 * 批量更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	@Override
	public int updateDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList){
		return dredgeTypePriceConfigBaseDao.updateDredgeTypePriceConfigBatch(dredgeTypePriceConfigList);
	}
	/**
	 * 根据序列号删除(维修价格配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypePriceConfigLogic(java.math.BigInteger id){
		return dredgeTypePriceConfigBaseDao.deleteDredgeTypePriceConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修价格配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypePriceConfigLogicBatch(List<java.math.BigInteger> idList){
		return dredgeTypePriceConfigBaseDao.deleteDredgeTypePriceConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修价格配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypePriceConfig(java.math.BigInteger id){
//		return dredgeTypePriceConfigBaseDao.deleteDredgeTypePriceConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修价格配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypePriceConfigBatch(List<java.math.BigInteger> idList){
//		return dredgeTypePriceConfigBaseDao.deleteDredgeTypePriceConfigBatch(idList);
//	}
	
}
