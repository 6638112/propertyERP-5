package com.cnfantasia.server.domainbase.mrCalculateRuleCfg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrCalculateRuleCfg.dao.IMrCalculateRuleCfgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;

/**
 * 描述:(抄表计算规则配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrCalculateRuleCfgBaseService implements IMrCalculateRuleCfgBaseService{
	
	private IMrCalculateRuleCfgBaseDao mrCalculateRuleCfgBaseDao;
	public void setMrCalculateRuleCfgBaseDao(IMrCalculateRuleCfgBaseDao mrCalculateRuleCfgBaseDao) {
		this.mrCalculateRuleCfgBaseDao = mrCalculateRuleCfgBaseDao;
	}
	/**
	 * 根据条件查询(抄表计算规则配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> getMrCalculateRuleCfgByCondition(Map<String,Object> paramMap){
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表计算规则配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> getMrCalculateRuleCfgByConditionDim(Map<String,Object> paramMap){
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表计算规则配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> getMrCalculateRuleCfgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表计算规则配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> getMrCalculateRuleCfgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表计算规则配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrCalculateRuleCfg getMrCalculateRuleCfgBySeqId(java.math.BigInteger id){
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表计算规则配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrCalculateRuleCfgCount(Map<String,Object> paramMap){
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表计算规则配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrCalculateRuleCfgCountDim(Map<String,Object> paramMap){
		return mrCalculateRuleCfgBaseDao.selectMrCalculateRuleCfgCount(paramMap,true);
	}
	/**
	 * 往(抄表计算规则配置)新增一条记录
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	@Override
	public int insertMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg){
		return mrCalculateRuleCfgBaseDao.insertMrCalculateRuleCfg(mrCalculateRuleCfg);
	}
	/**
	 * 批量新增(抄表计算规则配置)
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	@Override
	public int insertMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList){
		return mrCalculateRuleCfgBaseDao.insertMrCalculateRuleCfgBatch(mrCalculateRuleCfgList);
	}
	/**
	 * 更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	@Override
	public int updateMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg){
		return mrCalculateRuleCfgBaseDao.updateMrCalculateRuleCfg(mrCalculateRuleCfg);
	}
	/**
	 * 批量更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	@Override
	public int updateMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList){
		return mrCalculateRuleCfgBaseDao.updateMrCalculateRuleCfgBatch(mrCalculateRuleCfgList);
	}
	/**
	 * 根据序列号删除(抄表计算规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrCalculateRuleCfgLogic(java.math.BigInteger id){
		return mrCalculateRuleCfgBaseDao.deleteMrCalculateRuleCfgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表计算规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrCalculateRuleCfgLogicBatch(List<java.math.BigInteger> idList){
		return mrCalculateRuleCfgBaseDao.deleteMrCalculateRuleCfgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表计算规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrCalculateRuleCfg(java.math.BigInteger id){
//		return mrCalculateRuleCfgBaseDao.deleteMrCalculateRuleCfg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表计算规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrCalculateRuleCfgBatch(List<java.math.BigInteger> idList){
//		return mrCalculateRuleCfgBaseDao.deleteMrCalculateRuleCfgBatch(idList);
//	}
	
}
