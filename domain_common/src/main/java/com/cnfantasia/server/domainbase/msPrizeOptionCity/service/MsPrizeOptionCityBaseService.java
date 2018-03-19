package com.cnfantasia.server.domainbase.msPrizeOptionCity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.msPrizeOptionCity.dao.IMsPrizeOptionCityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOptionCity.entity.MsPrizeOptionCity;

/**
 * 描述:(奖项城市关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MsPrizeOptionCityBaseService implements IMsPrizeOptionCityBaseService{
	
	private IMsPrizeOptionCityBaseDao msPrizeOptionCityBaseDao;
	public void setMsPrizeOptionCityBaseDao(IMsPrizeOptionCityBaseDao msPrizeOptionCityBaseDao) {
		this.msPrizeOptionCityBaseDao = msPrizeOptionCityBaseDao;
	}
	/**
	 * 根据条件查询(奖项城市关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByCondition(Map<String,Object> paramMap){
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(奖项城市关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByConditionDim(Map<String,Object> paramMap){
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(奖项城市关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(奖项城市关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(奖项城市关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeOptionCity getMsPrizeOptionCityBySeqId(java.math.BigInteger id){
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeOptionCityCount(Map<String,Object> paramMap){
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMsPrizeOptionCityCountDim(Map<String,Object> paramMap){
		return msPrizeOptionCityBaseDao.selectMsPrizeOptionCityCount(paramMap,true);
	}
	/**
	 * 往(奖项城市关系表)新增一条记录
	 * @param msPrizeOptionCity
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity){
		return msPrizeOptionCityBaseDao.insertMsPrizeOptionCity(msPrizeOptionCity);
	}
	/**
	 * 批量新增(奖项城市关系表)
	 * @param msPrizeOptionCityList
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList){
		return msPrizeOptionCityBaseDao.insertMsPrizeOptionCityBatch(msPrizeOptionCityList);
	}
	/**
	 * 更新(奖项城市关系表)信息
	 * @param msPrizeOptionCity
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity){
		return msPrizeOptionCityBaseDao.updateMsPrizeOptionCity(msPrizeOptionCity);
	}
	/**
	 * 批量更新(奖项城市关系表)信息
	 * @param msPrizeOptionCityList
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList){
		return msPrizeOptionCityBaseDao.updateMsPrizeOptionCityBatch(msPrizeOptionCityList);
	}
	/**
	 * 根据序列号删除(奖项城市关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionCityLogic(java.math.BigInteger id){
		return msPrizeOptionCityBaseDao.deleteMsPrizeOptionCityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(奖项城市关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionCityLogicBatch(List<java.math.BigInteger> idList){
		return msPrizeOptionCityBaseDao.deleteMsPrizeOptionCityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(奖项城市关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionCity(java.math.BigInteger id){
//		return msPrizeOptionCityBaseDao.deleteMsPrizeOptionCity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖项城市关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionCityBatch(List<java.math.BigInteger> idList){
//		return msPrizeOptionCityBaseDao.deleteMsPrizeOptionCityBatch(idList);
//	}
	
}
