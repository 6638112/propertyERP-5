package com.cnfantasia.server.domainbase.nuomiGb.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.nuomiGb.dao.INuomiGbBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.nuomiGb.entity.NuomiGb;

/**
 * 描述:(百度糯米对接小区信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class NuomiGbBaseService implements INuomiGbBaseService{
	
	private INuomiGbBaseDao nuomiGbBaseDao;
	public void setNuomiGbBaseDao(INuomiGbBaseDao nuomiGbBaseDao) {
		this.nuomiGbBaseDao = nuomiGbBaseDao;
	}
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<NuomiGb> getNuomiGbByCondition(Map<String,Object> paramMap){
		return nuomiGbBaseDao.selectNuomiGbByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<NuomiGb> getNuomiGbByConditionDim(Map<String,Object> paramMap){
		return nuomiGbBaseDao.selectNuomiGbByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<NuomiGb> getNuomiGbByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return nuomiGbBaseDao.selectNuomiGbByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<NuomiGb> getNuomiGbByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return nuomiGbBaseDao.selectNuomiGbByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(百度糯米对接小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public NuomiGb getNuomiGbBySeqId(java.math.BigInteger id){
		return nuomiGbBaseDao.selectNuomiGbBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getNuomiGbCount(Map<String,Object> paramMap){
		return nuomiGbBaseDao.selectNuomiGbCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getNuomiGbCountDim(Map<String,Object> paramMap){
		return nuomiGbBaseDao.selectNuomiGbCount(paramMap,true);
	}
	/**
	 * 往(百度糯米对接小区信息)新增一条记录
	 * @param nuomiGb
	 * @return
	 */
	@Override
	public int insertNuomiGb(NuomiGb nuomiGb){
		return nuomiGbBaseDao.insertNuomiGb(nuomiGb);
	}
	/**
	 * 批量新增(百度糯米对接小区信息)
	 * @param nuomiGbList
	 * @return
	 */
	@Override
	public int insertNuomiGbBatch(List<NuomiGb> nuomiGbList){
		return nuomiGbBaseDao.insertNuomiGbBatch(nuomiGbList);
	}
	/**
	 * 更新(百度糯米对接小区信息)信息
	 * @param nuomiGb
	 * @return
	 */
	@Override
	public int updateNuomiGb(NuomiGb nuomiGb){
		return nuomiGbBaseDao.updateNuomiGb(nuomiGb);
	}
	/**
	 * 批量更新(百度糯米对接小区信息)信息
	 * @param nuomiGbList
	 * @return
	 */
	@Override
	public int updateNuomiGbBatch(List<NuomiGb> nuomiGbList){
		return nuomiGbBaseDao.updateNuomiGbBatch(nuomiGbList);
	}
	/**
	 * 根据序列号删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteNuomiGbLogic(java.math.BigInteger id){
		return nuomiGbBaseDao.deleteNuomiGbLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteNuomiGbLogicBatch(List<java.math.BigInteger> idList){
		return nuomiGbBaseDao.deleteNuomiGbLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(百度糯米对接小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteNuomiGb(java.math.BigInteger id){
//		return nuomiGbBaseDao.deleteNuomiGb(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(百度糯米对接小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteNuomiGbBatch(List<java.math.BigInteger> idList){
//		return nuomiGbBaseDao.deleteNuomiGbBatch(idList);
//	}
	
}
