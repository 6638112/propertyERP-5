package com.cnfantasia.server.domainbase.addressCityHot.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressCityHot.dao.IAddressCityHotBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCityHot.entity.AddressCityHot;

/**
 * 描述:(热门城市) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressCityHotBaseService implements IAddressCityHotBaseService{
	
	private IAddressCityHotBaseDao addressCityHotBaseDao;
	public void setAddressCityHotBaseDao(IAddressCityHotBaseDao addressCityHotBaseDao) {
		this.addressCityHotBaseDao = addressCityHotBaseDao;
	}
	/**
	 * 根据条件查询(热门城市)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCityHot> getAddressCityHotByCondition(Map<String,Object> paramMap){
		return addressCityHotBaseDao.selectAddressCityHotByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(热门城市)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCityHot> getAddressCityHotByConditionDim(Map<String,Object> paramMap){
		return addressCityHotBaseDao.selectAddressCityHotByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(热门城市)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCityHot> getAddressCityHotByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCityHotBaseDao.selectAddressCityHotByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(热门城市)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCityHot> getAddressCityHotByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCityHotBaseDao.selectAddressCityHotByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(热门城市)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressCityHot getAddressCityHotBySeqId(java.math.BigInteger id){
		return addressCityHotBaseDao.selectAddressCityHotBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(热门城市)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCityHotCount(Map<String,Object> paramMap){
		return addressCityHotBaseDao.selectAddressCityHotCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(热门城市)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCityHotCountDim(Map<String,Object> paramMap){
		return addressCityHotBaseDao.selectAddressCityHotCount(paramMap,true);
	}
	/**
	 * 往(热门城市)新增一条记录
	 * @param addressCityHot
	 * @return
	 */
	@Override
	public int insertAddressCityHot(AddressCityHot addressCityHot){
		return addressCityHotBaseDao.insertAddressCityHot(addressCityHot);
	}
	/**
	 * 批量新增(热门城市)
	 * @param addressCityHotList
	 * @return
	 */
	@Override
	public int insertAddressCityHotBatch(List<AddressCityHot> addressCityHotList){
		return addressCityHotBaseDao.insertAddressCityHotBatch(addressCityHotList);
	}
	/**
	 * 更新(热门城市)信息
	 * @param addressCityHot
	 * @return
	 */
	@Override
	public int updateAddressCityHot(AddressCityHot addressCityHot){
		return addressCityHotBaseDao.updateAddressCityHot(addressCityHot);
	}
	/**
	 * 批量更新(热门城市)信息
	 * @param addressCityHotList
	 * @return
	 */
	@Override
	public int updateAddressCityHotBatch(List<AddressCityHot> addressCityHotList){
		return addressCityHotBaseDao.updateAddressCityHotBatch(addressCityHotList);
	}
	/**
	 * 根据序列号删除(热门城市)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressCityHotLogic(java.math.BigInteger id){
		return addressCityHotBaseDao.deleteAddressCityHotLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(热门城市)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressCityHotLogicBatch(List<java.math.BigInteger> idList){
		return addressCityHotBaseDao.deleteAddressCityHotLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(热门城市)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCityHot(java.math.BigInteger id){
//		return addressCityHotBaseDao.deleteAddressCityHot(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(热门城市)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCityHotBatch(List<java.math.BigInteger> idList){
//		return addressCityHotBaseDao.deleteAddressCityHotBatch(idList);
//	}
	
}
