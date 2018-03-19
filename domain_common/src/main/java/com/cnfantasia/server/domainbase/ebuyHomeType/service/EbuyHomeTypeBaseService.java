package com.cnfantasia.server.domainbase.ebuyHomeType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyHomeType.dao.IEbuyHomeTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeType.entity.EbuyHomeType;

/**
 * 描述:(首页分类) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyHomeTypeBaseService implements IEbuyHomeTypeBaseService{
	
	private IEbuyHomeTypeBaseDao ebuyHomeTypeBaseDao;
	public void setEbuyHomeTypeBaseDao(IEbuyHomeTypeBaseDao ebuyHomeTypeBaseDao) {
		this.ebuyHomeTypeBaseDao = ebuyHomeTypeBaseDao;
	}
	/**
	 * 根据条件查询(首页分类)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHomeType> getEbuyHomeTypeByCondition(Map<String,Object> paramMap){
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(首页分类)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHomeType> getEbuyHomeTypeByConditionDim(Map<String,Object> paramMap){
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(首页分类)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHomeType> getEbuyHomeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(首页分类)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHomeType> getEbuyHomeTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(首页分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHomeType getEbuyHomeTypeBySeqId(java.math.BigInteger id){
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(首页分类)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHomeTypeCount(Map<String,Object> paramMap){
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(首页分类)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHomeTypeCountDim(Map<String,Object> paramMap){
		return ebuyHomeTypeBaseDao.selectEbuyHomeTypeCount(paramMap,true);
	}
	/**
	 * 往(首页分类)新增一条记录
	 * @param ebuyHomeType
	 * @return
	 */
	@Override
	public int insertEbuyHomeType(EbuyHomeType ebuyHomeType){
		return ebuyHomeTypeBaseDao.insertEbuyHomeType(ebuyHomeType);
	}
	/**
	 * 批量新增(首页分类)
	 * @param ebuyHomeTypeList
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList){
		return ebuyHomeTypeBaseDao.insertEbuyHomeTypeBatch(ebuyHomeTypeList);
	}
	/**
	 * 更新(首页分类)信息
	 * @param ebuyHomeType
	 * @return
	 */
	@Override
	public int updateEbuyHomeType(EbuyHomeType ebuyHomeType){
		return ebuyHomeTypeBaseDao.updateEbuyHomeType(ebuyHomeType);
	}
	/**
	 * 批量更新(首页分类)信息
	 * @param ebuyHomeTypeList
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList){
		return ebuyHomeTypeBaseDao.updateEbuyHomeTypeBatch(ebuyHomeTypeList);
	}
	/**
	 * 根据序列号删除(首页分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeLogic(java.math.BigInteger id){
		return ebuyHomeTypeBaseDao.deleteEbuyHomeTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(首页分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeLogicBatch(List<java.math.BigInteger> idList){
		return ebuyHomeTypeBaseDao.deleteEbuyHomeTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(首页分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeType(java.math.BigInteger id){
//		return ebuyHomeTypeBaseDao.deleteEbuyHomeType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeBatch(List<java.math.BigInteger> idList){
//		return ebuyHomeTypeBaseDao.deleteEbuyHomeTypeBatch(idList);
//	}
	
}
