package com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.dao.IRealRoomHasTPropertyLesseeBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.entity.RealRoomHasTPropertyLessee;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 描述:(房间租户信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RealRoomHasTPropertyLesseeBaseService implements IRealRoomHasTPropertyLesseeBaseService{
	
	private IRealRoomHasTPropertyLesseeBaseDao realRoomHasTPropertyLesseeBaseDao;
	public void setRealRoomHasTPropertyLesseeBaseDao(IRealRoomHasTPropertyLesseeBaseDao realRoomHasTPropertyLesseeBaseDao) {
		this.realRoomHasTPropertyLesseeBaseDao = realRoomHasTPropertyLesseeBaseDao;
	}
	/**
	 * 根据条件查询(房间租户信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap){
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间租户信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByConditionDim(Map<String,Object> paramMap){
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间租户信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间租户信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> getRealRoomHasTPropertyLesseeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间租户信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoomHasTPropertyLessee getRealRoomHasTPropertyLesseeBySeqId(java.math.BigInteger id){
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间租户信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasTPropertyLesseeCount(Map<String,Object> paramMap){
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间租户信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRealRoomHasTPropertyLesseeCountDim(Map<String,Object> paramMap){
		return realRoomHasTPropertyLesseeBaseDao.selectRealRoomHasTPropertyLesseeCount(paramMap,true);
	}
	/**
	 * 往(房间租户信息表)新增一条记录
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee){
		return realRoomHasTPropertyLesseeBaseDao.insertRealRoomHasTPropertyLessee(realRoomHasTPropertyLessee);
	}
	/**
	 * 批量新增(房间租户信息表)
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList){
		return realRoomHasTPropertyLesseeBaseDao.insertRealRoomHasTPropertyLesseeBatch(realRoomHasTPropertyLesseeList);
	}
	/**
	 * 更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee){
		return realRoomHasTPropertyLesseeBaseDao.updateRealRoomHasTPropertyLessee(realRoomHasTPropertyLessee);
	}
	/**
	 * 批量更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList){
		return realRoomHasTPropertyLesseeBaseDao.updateRealRoomHasTPropertyLesseeBatch(realRoomHasTPropertyLesseeList);
	}
	/**
	 * 根据序列号删除(房间租户信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyLesseeLogic(java.math.BigInteger id){
		return realRoomHasTPropertyLesseeBaseDao.deleteRealRoomHasTPropertyLesseeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间租户信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyLesseeLogicBatch(List<java.math.BigInteger> idList){
		return realRoomHasTPropertyLesseeBaseDao.deleteRealRoomHasTPropertyLesseeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间租户信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyLessee(java.math.BigInteger id){
//		return realRoomHasTPropertyLesseeBaseDao.deleteRealRoomHasTPropertyLessee(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间租户信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyLesseeBatch(List<java.math.BigInteger> idList){
//		return realRoomHasTPropertyLesseeBaseDao.deleteRealRoomHasTPropertyLesseeBatch(idList);
//	}
	
}
