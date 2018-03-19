package com.cnfantasia.server.domainbase.dredgeBillOtherInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillOtherInfo.dao.IDredgeBillOtherInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillOtherInfo.entity.DredgeBillOtherInfo;

/**
 * 描述:(维修单的与第三方供应商的信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillOtherInfoBaseService implements IDredgeBillOtherInfoBaseService{
	
	private IDredgeBillOtherInfoBaseDao dredgeBillOtherInfoBaseDao;
	public void setDredgeBillOtherInfoBaseDao(IDredgeBillOtherInfoBaseDao dredgeBillOtherInfoBaseDao) {
		this.dredgeBillOtherInfoBaseDao = dredgeBillOtherInfoBaseDao;
	}
	/**
	 * 根据条件查询(维修单的与第三方供应商的信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByCondition(Map<String,Object> paramMap){
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修单的与第三方供应商的信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByConditionDim(Map<String,Object> paramMap){
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修单的与第三方供应商的信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修单的与第三方供应商的信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修单的与第三方供应商的信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillOtherInfo getDredgeBillOtherInfoBySeqId(java.math.BigInteger id){
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修单的与第三方供应商的信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillOtherInfoCount(Map<String,Object> paramMap){
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修单的与第三方供应商的信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillOtherInfoCountDim(Map<String,Object> paramMap){
		return dredgeBillOtherInfoBaseDao.selectDredgeBillOtherInfoCount(paramMap,true);
	}
	/**
	 * 往(维修单的与第三方供应商的信息)新增一条记录
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	@Override
	public int insertDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo){
		return dredgeBillOtherInfoBaseDao.insertDredgeBillOtherInfo(dredgeBillOtherInfo);
	}
	/**
	 * 批量新增(维修单的与第三方供应商的信息)
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	@Override
	public int insertDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList){
		return dredgeBillOtherInfoBaseDao.insertDredgeBillOtherInfoBatch(dredgeBillOtherInfoList);
	}
	/**
	 * 更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	@Override
	public int updateDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo){
		return dredgeBillOtherInfoBaseDao.updateDredgeBillOtherInfo(dredgeBillOtherInfo);
	}
	/**
	 * 批量更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	@Override
	public int updateDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList){
		return dredgeBillOtherInfoBaseDao.updateDredgeBillOtherInfoBatch(dredgeBillOtherInfoList);
	}
	/**
	 * 根据序列号删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillOtherInfoLogic(java.math.BigInteger id){
		return dredgeBillOtherInfoBaseDao.deleteDredgeBillOtherInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillOtherInfoLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillOtherInfoBaseDao.deleteDredgeBillOtherInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修单的与第三方供应商的信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillOtherInfo(java.math.BigInteger id){
//		return dredgeBillOtherInfoBaseDao.deleteDredgeBillOtherInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修单的与第三方供应商的信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillOtherInfoBatch(List<java.math.BigInteger> idList){
//		return dredgeBillOtherInfoBaseDao.deleteDredgeBillOtherInfoBatch(idList);
//	}
	
}
