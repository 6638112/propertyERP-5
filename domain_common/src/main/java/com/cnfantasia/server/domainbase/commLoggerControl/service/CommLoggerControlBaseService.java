package com.cnfantasia.server.domainbase.commLoggerControl.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commLoggerControl.dao.ICommLoggerControlBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;

/**
 * 描述:(公共日志记录控制表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommLoggerControlBaseService implements ICommLoggerControlBaseService{
	
	private ICommLoggerControlBaseDao commLoggerControlBaseDao;
	public void setCommLoggerControlBaseDao(ICommLoggerControlBaseDao commLoggerControlBaseDao) {
		this.commLoggerControlBaseDao = commLoggerControlBaseDao;
	}
	/**
	 * 根据条件查询(公共日志记录控制表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommLoggerControl> getCommLoggerControlByCondition(Map<String,Object> paramMap){
		return commLoggerControlBaseDao.selectCommLoggerControlByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(公共日志记录控制表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommLoggerControl> getCommLoggerControlByConditionDim(Map<String,Object> paramMap){
		return commLoggerControlBaseDao.selectCommLoggerControlByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(公共日志记录控制表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommLoggerControl> getCommLoggerControlByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerControlBaseDao.selectCommLoggerControlByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(公共日志记录控制表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommLoggerControl> getCommLoggerControlByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerControlBaseDao.selectCommLoggerControlByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(公共日志记录控制表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommLoggerControl getCommLoggerControlBySeqId(java.math.BigInteger id){
		return commLoggerControlBaseDao.selectCommLoggerControlBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录控制表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerControlCount(Map<String,Object> paramMap){
		return commLoggerControlBaseDao.selectCommLoggerControlCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录控制表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerControlCountDim(Map<String,Object> paramMap){
		return commLoggerControlBaseDao.selectCommLoggerControlCount(paramMap,true);
	}
	/**
	 * 往(公共日志记录控制表)新增一条记录
	 * @param commLoggerControl
	 * @return
	 */
	@Override
	public int insertCommLoggerControl(CommLoggerControl commLoggerControl){
		return commLoggerControlBaseDao.insertCommLoggerControl(commLoggerControl);
	}
	/**
	 * 批量新增(公共日志记录控制表)
	 * @param commLoggerControlList
	 * @return
	 */
	@Override
	public int insertCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList){
		return commLoggerControlBaseDao.insertCommLoggerControlBatch(commLoggerControlList);
	}
	/**
	 * 更新(公共日志记录控制表)信息
	 * @param commLoggerControl
	 * @return
	 */
	@Override
	public int updateCommLoggerControl(CommLoggerControl commLoggerControl){
		return commLoggerControlBaseDao.updateCommLoggerControl(commLoggerControl);
	}
	/**
	 * 批量更新(公共日志记录控制表)信息
	 * @param commLoggerControlList
	 * @return
	 */
	@Override
	public int updateCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList){
		return commLoggerControlBaseDao.updateCommLoggerControlBatch(commLoggerControlList);
	}
	/**
	 * 根据序列号删除(公共日志记录控制表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerControlLogic(java.math.BigInteger id){
		return commLoggerControlBaseDao.deleteCommLoggerControlLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(公共日志记录控制表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerControlLogicBatch(List<java.math.BigInteger> idList){
		return commLoggerControlBaseDao.deleteCommLoggerControlLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(公共日志记录控制表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommLoggerControl(java.math.BigInteger id){
//		return commLoggerControlBaseDao.deleteCommLoggerControl(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(公共日志记录控制表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommLoggerControlBatch(List<java.math.BigInteger> idList){
//		return commLoggerControlBaseDao.deleteCommLoggerControlBatch(idList);
//	}
	
}
