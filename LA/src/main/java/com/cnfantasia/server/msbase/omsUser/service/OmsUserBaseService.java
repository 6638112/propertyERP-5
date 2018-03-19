package com.cnfantasia.server.msbase.omsUser.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsUser.dao.IOmsUserBaseDao;
import com.cnfantasia.server.msbase.omsUser.entity.OmsUser;

/**
 * 描述:(OMS用户表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserBaseService implements IOmsUserBaseService{
	
	private IOmsUserBaseDao omsUserBaseDao;
	public void setOmsUserBaseDao(IOmsUserBaseDao omsUserBaseDao) {
		this.omsUserBaseDao = omsUserBaseDao;
	}
	/**
	 * 根据条件查询(OMS用户表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUser> getOmsUserByCondition(Map<String,Object> paramMap){
		return omsUserBaseDao.selectOmsUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS用户表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUser> getOmsUserByConditionDim(Map<String,Object> paramMap){
		return omsUserBaseDao.selectOmsUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS用户表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUser> getOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserBaseDao.selectOmsUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS用户表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUser> getOmsUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserBaseDao.selectOmsUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS用户表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUser getOmsUserBySeqId(java.math.BigInteger id){
		return omsUserBaseDao.selectOmsUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserCount(Map<String,Object> paramMap){
		return omsUserBaseDao.selectOmsUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserCountDim(Map<String,Object> paramMap){
		return omsUserBaseDao.selectOmsUserCount(paramMap,true);
	}
	/**
	 * 往(OMS用户表)新增一条记录
	 * @param omsUser
	 * @return
	 */
	@Override
	public int insertOmsUser(OmsUser omsUser){
		return omsUserBaseDao.insertOmsUser(omsUser);
	}
	/**
	 * 批量新增(OMS用户表)
	 * @param omsUserList
	 * @return
	 */
	@Override
	public int insertOmsUserBatch(List<OmsUser> omsUserList){
		return omsUserBaseDao.insertOmsUserBatch(omsUserList);
	}
	/**
	 * 更新(OMS用户表)信息
	 * @param omsUser
	 * @return
	 */
	@Override
	public int updateOmsUser(OmsUser omsUser){
		return omsUserBaseDao.updateOmsUser(omsUser);
	}
	/**
	 * 批量更新(OMS用户表)信息
	 * @param omsUserList
	 * @return
	 */
	@Override
	public int updateOmsUserBatch(List<OmsUser> omsUserList){
		return omsUserBaseDao.updateOmsUserBatch(omsUserList);
	}
	/**
	 * 根据序列号删除(OMS用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserLogic(java.math.BigInteger id){
		return omsUserBaseDao.deleteOmsUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserLogicBatch(List<java.math.BigInteger> idList){
		return omsUserBaseDao.deleteOmsUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUser(java.math.BigInteger id){
//		return omsUserBaseDao.deleteOmsUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBatch(List<java.math.BigInteger> idList){
//		return omsUserBaseDao.deleteOmsUserBatch(idList);
//	}
	
}
