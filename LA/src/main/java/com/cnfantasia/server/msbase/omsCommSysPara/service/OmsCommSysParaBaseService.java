package com.cnfantasia.server.msbase.omsCommSysPara.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsCommSysPara.dao.OmsCommSysParaBaseDao;
import com.cnfantasia.server.msbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsCommSysParaBaseService implements IOmsCommSysParaBaseService{
	
	private OmsCommSysParaBaseDao omsCommSysParaBaseDao;
	public void setOmsCommSysParaBaseDao(OmsCommSysParaBaseDao omsCommSysParaBaseDao) {
		this.omsCommSysParaBaseDao = omsCommSysParaBaseDao;
	}
	/**
	 * 根据条件查询(OMS系统参数表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public OmsCommSysPara getOmsCommSysParaBySeqId(String seqId){
		return omsCommSysParaBaseDao.selectOmsCommSysParaBySeqId(seqId);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsCommSysParaCount(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsCommSysParaCountDim(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.selectOmsCommSysParaCount(paramMap,true);
	}
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param paramMap
	 * @return
	 */
	public int insertOmsCommSysPara(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.insertOmsCommSysPara(paramMap);
	}
	/**
	 * 更新(OMS系统参数表)信息
	 * @param paramMap
	 * @return
	 */
	public int updateOmsCommSysPara(Map<String,Object> paramMap){
		return omsCommSysParaBaseDao.updateOmsCommSysPara(paramMap);
	}
	/**
	 * 根据序列号删除(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public int deleteOmsCommSysPara(String seqId){
		return omsCommSysParaBaseDao.deleteOmsCommSysPara(seqId);
	}
	
	//TODO ____续写...OmsCommSysParaService
}
