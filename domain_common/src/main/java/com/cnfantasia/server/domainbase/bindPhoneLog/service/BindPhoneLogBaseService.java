package com.cnfantasia.server.domainbase.bindPhoneLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bindPhoneLog.dao.IBindPhoneLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bindPhoneLog.entity.BindPhoneLog;

/**
 * 描述:(绑定手机记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BindPhoneLogBaseService implements IBindPhoneLogBaseService{
	
	private IBindPhoneLogBaseDao bindPhoneLogBaseDao;
	public void setBindPhoneLogBaseDao(IBindPhoneLogBaseDao bindPhoneLogBaseDao) {
		this.bindPhoneLogBaseDao = bindPhoneLogBaseDao;
	}
	/**
	 * 根据条件查询(绑定手机记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BindPhoneLog> getBindPhoneLogByCondition(Map<String,Object> paramMap){
		return bindPhoneLogBaseDao.selectBindPhoneLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(绑定手机记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BindPhoneLog> getBindPhoneLogByConditionDim(Map<String,Object> paramMap){
		return bindPhoneLogBaseDao.selectBindPhoneLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(绑定手机记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BindPhoneLog> getBindPhoneLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bindPhoneLogBaseDao.selectBindPhoneLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(绑定手机记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BindPhoneLog> getBindPhoneLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bindPhoneLogBaseDao.selectBindPhoneLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(绑定手机记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BindPhoneLog getBindPhoneLogBySeqId(java.math.BigInteger id){
		return bindPhoneLogBaseDao.selectBindPhoneLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBindPhoneLogCount(Map<String,Object> paramMap){
		return bindPhoneLogBaseDao.selectBindPhoneLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBindPhoneLogCountDim(Map<String,Object> paramMap){
		return bindPhoneLogBaseDao.selectBindPhoneLogCount(paramMap,true);
	}
	/**
	 * 往(绑定手机记录表)新增一条记录
	 * @param bindPhoneLog
	 * @return
	 */
	@Override
	public int insertBindPhoneLog(BindPhoneLog bindPhoneLog){
		return bindPhoneLogBaseDao.insertBindPhoneLog(bindPhoneLog);
	}
	/**
	 * 批量新增(绑定手机记录表)
	 * @param bindPhoneLogList
	 * @return
	 */
	@Override
	public int insertBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList){
		return bindPhoneLogBaseDao.insertBindPhoneLogBatch(bindPhoneLogList);
	}
	/**
	 * 更新(绑定手机记录表)信息
	 * @param bindPhoneLog
	 * @return
	 */
	@Override
	public int updateBindPhoneLog(BindPhoneLog bindPhoneLog){
		return bindPhoneLogBaseDao.updateBindPhoneLog(bindPhoneLog);
	}
	/**
	 * 批量更新(绑定手机记录表)信息
	 * @param bindPhoneLogList
	 * @return
	 */
	@Override
	public int updateBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList){
		return bindPhoneLogBaseDao.updateBindPhoneLogBatch(bindPhoneLogList);
	}
	/**
	 * 根据序列号删除(绑定手机记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBindPhoneLogLogic(java.math.BigInteger id){
		return bindPhoneLogBaseDao.deleteBindPhoneLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(绑定手机记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBindPhoneLogLogicBatch(List<java.math.BigInteger> idList){
		return bindPhoneLogBaseDao.deleteBindPhoneLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(绑定手机记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBindPhoneLog(java.math.BigInteger id){
//		return bindPhoneLogBaseDao.deleteBindPhoneLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(绑定手机记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBindPhoneLogBatch(List<java.math.BigInteger> idList){
//		return bindPhoneLogBaseDao.deleteBindPhoneLogBatch(idList);
//	}
	
}
