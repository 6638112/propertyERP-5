package com.cnfantasia.server.domainbase.omsCommSysPara.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsCommSysPara.dao.IOmsCommSysParaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsCommSysParaBaseService implements IOmsCommSysParaBaseService{
	
	private IOmsCommSysParaBaseDao omsCommSysParaBaseDao;
	public void setOmsCommSysParaBaseDao(IOmsCommSysParaBaseDao omsCommSysParaBaseDao) {
		this.omsCommSysParaBaseDao = omsCommSysParaBaseDao;
	}
	/**
	 * 根据条件查询(OMS系统参数表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsCommSysPara getOmsCommSysParaBySeqId(java.math.BigInteger id){
		return omsCommSysParaBaseDao.selectOmsCommSysParaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsCommSysParaCount(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsCommSysParaCountDim(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaCount(paramMap,true);
	}
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param omsCommSysPara
	 * @return
	 */
	@Override
	public int insertOmsCommSysPara(OmsCommSysPara omsCommSysPara){
		return omsCommSysParaBaseDao.insertOmsCommSysPara(omsCommSysPara);
	}
	/**
	 * 批量新增(OMS系统参数表)
	 * @param omsCommSysParaList
	 * @return
	 */
	@Override
	public int insertOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList){
		return omsCommSysParaBaseDao.insertOmsCommSysParaBatch(omsCommSysParaList);
	}
	/**
	 * 更新(OMS系统参数表)信息
	 * @param omsCommSysPara
	 * @return
	 */
	@Override
	public int updateOmsCommSysPara(OmsCommSysPara omsCommSysPara){
		return omsCommSysParaBaseDao.updateOmsCommSysPara(omsCommSysPara);
	}
	/**
	 * 批量更新(OMS系统参数表)信息
	 * @param omsCommSysParaList
	 * @return
	 */
	@Override
	public int updateOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList){
		return omsCommSysParaBaseDao.updateOmsCommSysParaBatch(omsCommSysParaList);
	}
	/**
	 * 根据序列号删除(OMS系统参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsCommSysParaLogic(java.math.BigInteger id){
		return omsCommSysParaBaseDao.deleteOmsCommSysParaLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS系统参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsCommSysParaLogicBatch(List<java.math.BigInteger> idList){
		return omsCommSysParaBaseDao.deleteOmsCommSysParaLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS系统参数表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsCommSysPara(java.math.BigInteger id){
//		return omsCommSysParaBaseDao.deleteOmsCommSysPara(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS系统参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsCommSysParaBatch(List<java.math.BigInteger> idList){
//		return omsCommSysParaBaseDao.deleteOmsCommSysParaBatch(idList);
//	}
	
}
