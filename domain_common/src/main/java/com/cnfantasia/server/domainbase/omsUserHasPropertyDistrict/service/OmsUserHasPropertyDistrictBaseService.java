package com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.dao.IOmsUserHasPropertyDistrictBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.entity.OmsUserHasPropertyDistrict;

/**
 * 描述:(用户和片区关联表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsUserHasPropertyDistrictBaseService implements IOmsUserHasPropertyDistrictBaseService{
	
	private IOmsUserHasPropertyDistrictBaseDao omsUserHasPropertyDistrictBaseDao;
	public void setOmsUserHasPropertyDistrictBaseDao(IOmsUserHasPropertyDistrictBaseDao omsUserHasPropertyDistrictBaseDao) {
		this.omsUserHasPropertyDistrictBaseDao = omsUserHasPropertyDistrictBaseDao;
	}
	/**
	 * 根据条件查询(用户和片区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap){
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户和片区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByConditionDim(Map<String,Object> paramMap){
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户和片区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户和片区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户和片区关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserHasPropertyDistrict getOmsUserHasPropertyDistrictBySeqId(java.math.BigInteger id){
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户和片区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasPropertyDistrictCount(Map<String,Object> paramMap){
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户和片区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsUserHasPropertyDistrictCountDim(Map<String,Object> paramMap){
		return omsUserHasPropertyDistrictBaseDao.selectOmsUserHasPropertyDistrictCount(paramMap,true);
	}
	/**
	 * 往(用户和片区关联表)新增一条记录
	 * @param omsUserHasPropertyDistrict
	 * @return
	 */
	@Override
	public int insertOmsUserHasPropertyDistrict(OmsUserHasPropertyDistrict omsUserHasPropertyDistrict){
		return omsUserHasPropertyDistrictBaseDao.insertOmsUserHasPropertyDistrict(omsUserHasPropertyDistrict);
	}
	/**
	 * 批量新增(用户和片区关联表)
	 * @param omsUserHasPropertyDistrictList
	 * @return
	 */
	@Override
	public int insertOmsUserHasPropertyDistrictBatch(List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList){
		return omsUserHasPropertyDistrictBaseDao.insertOmsUserHasPropertyDistrictBatch(omsUserHasPropertyDistrictList);
	}
	/**
	 * 更新(用户和片区关联表)信息
	 * @param omsUserHasPropertyDistrict
	 * @return
	 */
	@Override
	public int updateOmsUserHasPropertyDistrict(OmsUserHasPropertyDistrict omsUserHasPropertyDistrict){
		return omsUserHasPropertyDistrictBaseDao.updateOmsUserHasPropertyDistrict(omsUserHasPropertyDistrict);
	}
	/**
	 * 批量更新(用户和片区关联表)信息
	 * @param omsUserHasPropertyDistrictList
	 * @return
	 */
	@Override
	public int updateOmsUserHasPropertyDistrictBatch(List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList){
		return omsUserHasPropertyDistrictBaseDao.updateOmsUserHasPropertyDistrictBatch(omsUserHasPropertyDistrictList);
	}
	/**
	 * 根据序列号删除(用户和片区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasPropertyDistrictLogic(java.math.BigInteger id){
		return omsUserHasPropertyDistrictBaseDao.deleteOmsUserHasPropertyDistrictLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户和片区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasPropertyDistrictLogicBatch(List<java.math.BigInteger> idList){
		return omsUserHasPropertyDistrictBaseDao.deleteOmsUserHasPropertyDistrictLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户和片区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasPropertyDistrict(java.math.BigInteger id){
//		return omsUserHasPropertyDistrictBaseDao.deleteOmsUserHasPropertyDistrict(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户和片区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasPropertyDistrictBatch(List<java.math.BigInteger> idList){
//		return omsUserHasPropertyDistrictBaseDao.deleteOmsUserHasPropertyDistrictBatch(idList);
//	}
	
}
