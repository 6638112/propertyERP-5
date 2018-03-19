package com.cnfantasia.server.domainbase.commonGatherData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commonGatherData.dao.ICommonGatherDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * 描述:(通用模块信息归集) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommonGatherDataBaseService implements ICommonGatherDataBaseService{
	
	private ICommonGatherDataBaseDao commonGatherDataBaseDao;
	public void setCommonGatherDataBaseDao(ICommonGatherDataBaseDao commonGatherDataBaseDao) {
		this.commonGatherDataBaseDao = commonGatherDataBaseDao;
	}
	/**
	 * 根据条件查询(通用模块信息归集)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommonGatherData> getCommonGatherDataByCondition(Map<String,Object> paramMap){
		return commonGatherDataBaseDao.selectCommonGatherDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(通用模块信息归集)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommonGatherData> getCommonGatherDataByConditionDim(Map<String,Object> paramMap){
		return commonGatherDataBaseDao.selectCommonGatherDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(通用模块信息归集)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommonGatherData> getCommonGatherDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commonGatherDataBaseDao.selectCommonGatherDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(通用模块信息归集)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommonGatherData> getCommonGatherDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commonGatherDataBaseDao.selectCommonGatherDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(通用模块信息归集)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommonGatherData getCommonGatherDataBySeqId(java.math.BigInteger id){
		return commonGatherDataBaseDao.selectCommonGatherDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(通用模块信息归集)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommonGatherDataCount(Map<String,Object> paramMap){
		return commonGatherDataBaseDao.selectCommonGatherDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(通用模块信息归集)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommonGatherDataCountDim(Map<String,Object> paramMap){
		return commonGatherDataBaseDao.selectCommonGatherDataCount(paramMap,true);
	}
	/**
	 * 往(通用模块信息归集)新增一条记录
	 * @param commonGatherData
	 * @return
	 */
	@Override
	public int insertCommonGatherData(CommonGatherData commonGatherData){
		return commonGatherDataBaseDao.insertCommonGatherData(commonGatherData);
	}
	/**
	 * 批量新增(通用模块信息归集)
	 * @param commonGatherDataList
	 * @return
	 */
	@Override
	public int insertCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList){
		return commonGatherDataBaseDao.insertCommonGatherDataBatch(commonGatherDataList);
	}
	/**
	 * 更新(通用模块信息归集)信息
	 * @param commonGatherData
	 * @return
	 */
	@Override
	public int updateCommonGatherData(CommonGatherData commonGatherData){
		return commonGatherDataBaseDao.updateCommonGatherData(commonGatherData);
	}
	/**
	 * 批量更新(通用模块信息归集)信息
	 * @param commonGatherDataList
	 * @return
	 */
	@Override
	public int updateCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList){
		return commonGatherDataBaseDao.updateCommonGatherDataBatch(commonGatherDataList);
	}
	/**
	 * 根据序列号删除(通用模块信息归集)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommonGatherDataLogic(java.math.BigInteger id){
		return commonGatherDataBaseDao.deleteCommonGatherDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(通用模块信息归集)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommonGatherDataLogicBatch(List<java.math.BigInteger> idList){
		return commonGatherDataBaseDao.deleteCommonGatherDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(通用模块信息归集)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommonGatherData(java.math.BigInteger id){
//		return commonGatherDataBaseDao.deleteCommonGatherData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(通用模块信息归集)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommonGatherDataBatch(List<java.math.BigInteger> idList){
//		return commonGatherDataBaseDao.deleteCommonGatherDataBatch(idList);
//	}
	
}
