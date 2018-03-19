package com.cnfantasia.server.domainbase.dredgeCouponConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeCouponConfig.dao.IDredgeCouponConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;

/**
 * 描述:(维修券使用配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeCouponConfigBaseService implements IDredgeCouponConfigBaseService{
	
	private IDredgeCouponConfigBaseDao dredgeCouponConfigBaseDao;
	public void setDredgeCouponConfigBaseDao(IDredgeCouponConfigBaseDao dredgeCouponConfigBaseDao) {
		this.dredgeCouponConfigBaseDao = dredgeCouponConfigBaseDao;
	}
	/**
	 * 根据条件查询(维修券使用配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> getDredgeCouponConfigByCondition(Map<String,Object> paramMap){
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修券使用配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> getDredgeCouponConfigByConditionDim(Map<String,Object> paramMap){
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修券使用配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> getDredgeCouponConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修券使用配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeCouponConfig> getDredgeCouponConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修券使用配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeCouponConfig getDredgeCouponConfigBySeqId(java.math.BigInteger id){
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeCouponConfigCount(Map<String,Object> paramMap){
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeCouponConfigCountDim(Map<String,Object> paramMap){
		return dredgeCouponConfigBaseDao.selectDredgeCouponConfigCount(paramMap,true);
	}
	/**
	 * 往(维修券使用配置表)新增一条记录
	 * @param dredgeCouponConfig
	 * @return
	 */
	@Override
	public int insertDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig){
		return dredgeCouponConfigBaseDao.insertDredgeCouponConfig(dredgeCouponConfig);
	}
	/**
	 * 批量新增(维修券使用配置表)
	 * @param dredgeCouponConfigList
	 * @return
	 */
	@Override
	public int insertDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList){
		return dredgeCouponConfigBaseDao.insertDredgeCouponConfigBatch(dredgeCouponConfigList);
	}
	/**
	 * 更新(维修券使用配置表)信息
	 * @param dredgeCouponConfig
	 * @return
	 */
	@Override
	public int updateDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig){
		return dredgeCouponConfigBaseDao.updateDredgeCouponConfig(dredgeCouponConfig);
	}
	/**
	 * 批量更新(维修券使用配置表)信息
	 * @param dredgeCouponConfigList
	 * @return
	 */
	@Override
	public int updateDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList){
		return dredgeCouponConfigBaseDao.updateDredgeCouponConfigBatch(dredgeCouponConfigList);
	}
	/**
	 * 根据序列号删除(维修券使用配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeCouponConfigLogic(java.math.BigInteger id){
		return dredgeCouponConfigBaseDao.deleteDredgeCouponConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修券使用配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeCouponConfigLogicBatch(List<java.math.BigInteger> idList){
		return dredgeCouponConfigBaseDao.deleteDredgeCouponConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修券使用配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeCouponConfig(java.math.BigInteger id){
//		return dredgeCouponConfigBaseDao.deleteDredgeCouponConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修券使用配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeCouponConfigBatch(List<java.math.BigInteger> idList){
//		return dredgeCouponConfigBaseDao.deleteDredgeCouponConfigBatch(idList);
//	}
	
}
