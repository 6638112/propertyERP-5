package com.cnfantasia.server.domainbase.fixedFeeItemRoomData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.fixedFeeItemRoomData.dao.IFixedFeeItemRoomDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.entity.FixedFeeItemRoomData;

/**
 * 描述:(房间数据信息表（已经导入数据的）) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FixedFeeItemRoomDataBaseService implements IFixedFeeItemRoomDataBaseService{
	
	private IFixedFeeItemRoomDataBaseDao fixedFeeItemRoomDataBaseDao;
	public void setFixedFeeItemRoomDataBaseDao(IFixedFeeItemRoomDataBaseDao fixedFeeItemRoomDataBaseDao) {
		this.fixedFeeItemRoomDataBaseDao = fixedFeeItemRoomDataBaseDao;
	}
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByCondition(Map<String,Object> paramMap){
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByConditionDim(Map<String,Object> paramMap){
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(房间数据信息表（已经导入数据的）)信息
	 * @param id
	 * @return
	 */
	@Override
	public FixedFeeItemRoomData getFixedFeeItemRoomDataBySeqId(java.math.BigInteger id){
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemRoomDataCount(Map<String,Object> paramMap){
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFixedFeeItemRoomDataCountDim(Map<String,Object> paramMap){
		return fixedFeeItemRoomDataBaseDao.selectFixedFeeItemRoomDataCount(paramMap,true);
	}
	/**
	 * 往(房间数据信息表（已经导入数据的）)新增一条记录
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	@Override
	public int insertFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData){
		return fixedFeeItemRoomDataBaseDao.insertFixedFeeItemRoomData(fixedFeeItemRoomData);
	}
	/**
	 * 批量新增(房间数据信息表（已经导入数据的）)
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	@Override
	public int insertFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList){
		return fixedFeeItemRoomDataBaseDao.insertFixedFeeItemRoomDataBatch(fixedFeeItemRoomDataList);
	}
	/**
	 * 更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	@Override
	public int updateFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData){
		return fixedFeeItemRoomDataBaseDao.updateFixedFeeItemRoomData(fixedFeeItemRoomData);
	}
	/**
	 * 批量更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	@Override
	public int updateFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList){
		return fixedFeeItemRoomDataBaseDao.updateFixedFeeItemRoomDataBatch(fixedFeeItemRoomDataList);
	}
	/**
	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemRoomDataLogic(java.math.BigInteger id){
		return fixedFeeItemRoomDataBaseDao.deleteFixedFeeItemRoomDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemRoomDataLogicBatch(List<java.math.BigInteger> idList){
		return fixedFeeItemRoomDataBaseDao.deleteFixedFeeItemRoomDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemRoomData(java.math.BigInteger id){
//		return fixedFeeItemRoomDataBaseDao.deleteFixedFeeItemRoomData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间数据信息表（已经导入数据的）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemRoomDataBatch(List<java.math.BigInteger> idList){
//		return fixedFeeItemRoomDataBaseDao.deleteFixedFeeItemRoomDataBatch(idList);
//	}
	
}
