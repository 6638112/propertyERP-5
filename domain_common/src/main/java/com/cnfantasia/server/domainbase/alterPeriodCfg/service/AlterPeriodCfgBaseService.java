package com.cnfantasia.server.domainbase.alterPeriodCfg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.alterPeriodCfg.dao.IAlterPeriodCfgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;

/**
 * 描述:(选择周期配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AlterPeriodCfgBaseService implements IAlterPeriodCfgBaseService{
	
	private IAlterPeriodCfgBaseDao alterPeriodCfgBaseDao;
	public void setAlterPeriodCfgBaseDao(IAlterPeriodCfgBaseDao alterPeriodCfgBaseDao) {
		this.alterPeriodCfgBaseDao = alterPeriodCfgBaseDao;
	}
	/**
	 * 根据条件查询(选择周期配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodCfg> getAlterPeriodCfgByCondition(Map<String,Object> paramMap){
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(选择周期配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodCfg> getAlterPeriodCfgByConditionDim(Map<String,Object> paramMap){
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(选择周期配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodCfg> getAlterPeriodCfgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(选择周期配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodCfg> getAlterPeriodCfgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(选择周期配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodCfg getAlterPeriodCfgBySeqId(java.math.BigInteger id){
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodCfgCount(Map<String,Object> paramMap){
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(选择周期配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodCfgCountDim(Map<String,Object> paramMap){
		return alterPeriodCfgBaseDao.selectAlterPeriodCfgCount(paramMap,true);
	}
	/**
	 * 往(选择周期配置)新增一条记录
	 * @param alterPeriodCfg
	 * @return
	 */
	@Override
	public int insertAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg){
		return alterPeriodCfgBaseDao.insertAlterPeriodCfg(alterPeriodCfg);
	}
	/**
	 * 批量新增(选择周期配置)
	 * @param alterPeriodCfgList
	 * @return
	 */
	@Override
	public int insertAlterPeriodCfgBatch(List<AlterPeriodCfg> alterPeriodCfgList){
		return alterPeriodCfgBaseDao.insertAlterPeriodCfgBatch(alterPeriodCfgList);
	}
	/**
	 * 更新(选择周期配置)信息
	 * @param alterPeriodCfg
	 * @return
	 */
	@Override
	public int updateAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg){
		return alterPeriodCfgBaseDao.updateAlterPeriodCfg(alterPeriodCfg);
	}
	/**
	 * 批量更新(选择周期配置)信息
	 * @param alterPeriodCfgList
	 * @return
	 */
	@Override
	public int updateAlterPeriodCfgBatch(List<AlterPeriodCfg> alterPeriodCfgList){
		return alterPeriodCfgBaseDao.updateAlterPeriodCfgBatch(alterPeriodCfgList);
	}
	/**
	 * 根据序列号删除(选择周期配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodCfgLogic(java.math.BigInteger id){
		return alterPeriodCfgBaseDao.deleteAlterPeriodCfgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(选择周期配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodCfgLogicBatch(List<java.math.BigInteger> idList){
		return alterPeriodCfgBaseDao.deleteAlterPeriodCfgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodCfg(java.math.BigInteger id){
//		return alterPeriodCfgBaseDao.deleteAlterPeriodCfg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodCfgBatch(List<java.math.BigInteger> idList){
//		return alterPeriodCfgBaseDao.deleteAlterPeriodCfgBatch(idList);
//	}
	
}
