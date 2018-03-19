package com.cnfantasia.server.domainbase.omsUserBankInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsUserBankInfo.dao.IOmsUserBankInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserBankInfo.entity.OmsUserBankInfo;

/**
 * 描述:(OMS用户银行信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserBankInfoBaseService implements IOmsUserBankInfoBaseService{
	
	private IOmsUserBankInfoBaseDao omsUserBankInfoBaseDao;
	public void setOmsUserBankInfoBaseDao(IOmsUserBankInfoBaseDao omsUserBankInfoBaseDao) {
		this.omsUserBankInfoBaseDao = omsUserBankInfoBaseDao;
	}
	/**
	 * 根据条件查询(OMS用户银行信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> getOmsUserBankInfoByCondition(Map<String,Object> paramMap){
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS用户银行信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> getOmsUserBankInfoByConditionDim(Map<String,Object> paramMap){
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> getOmsUserBankInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> getOmsUserBankInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS用户银行信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserBankInfo getOmsUserBankInfoBySeqId(java.math.BigInteger id){
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserBankInfoCount(Map<String,Object> paramMap){
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserBankInfoCountDim(Map<String,Object> paramMap){
		return omsUserBankInfoBaseDao.selectOmsUserBankInfoCount(paramMap,true);
	}
	/**
	 * 往(OMS用户银行信息表)新增一条记录
	 * @param omsUserBankInfo
	 * @return
	 */
	@Override
	public int insertOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo){
		return omsUserBankInfoBaseDao.insertOmsUserBankInfo(omsUserBankInfo);
	}
	/**
	 * 批量新增(OMS用户银行信息表)
	 * @param omsUserBankInfoList
	 * @return
	 */
	@Override
	public int insertOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList){
		return omsUserBankInfoBaseDao.insertOmsUserBankInfoBatch(omsUserBankInfoList);
	}
	/**
	 * 更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfo
	 * @return
	 */
	@Override
	public int updateOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo){
		return omsUserBankInfoBaseDao.updateOmsUserBankInfo(omsUserBankInfo);
	}
	/**
	 * 批量更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfoList
	 * @return
	 */
	@Override
	public int updateOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList){
		return omsUserBankInfoBaseDao.updateOmsUserBankInfoBatch(omsUserBankInfoList);
	}
	/**
	 * 根据序列号删除(OMS用户银行信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserBankInfoLogic(java.math.BigInteger id){
		return omsUserBankInfoBaseDao.deleteOmsUserBankInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS用户银行信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserBankInfoLogicBatch(List<java.math.BigInteger> idList){
		return omsUserBankInfoBaseDao.deleteOmsUserBankInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户银行信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBankInfo(java.math.BigInteger id){
//		return omsUserBankInfoBaseDao.deleteOmsUserBankInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户银行信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBankInfoBatch(List<java.math.BigInteger> idList){
//		return omsUserBankInfoBaseDao.deleteOmsUserBankInfoBatch(idList);
//	}
	
}
