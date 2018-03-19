package com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.dao.IDredgeBillHasEbuyProductShelfBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.entity.DredgeBillHasEbuyProductShelf;

/**
 * 描述:(用户自选耗材明细) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillHasEbuyProductShelfBaseService implements IDredgeBillHasEbuyProductShelfBaseService{
	
	private IDredgeBillHasEbuyProductShelfBaseDao dredgeBillHasEbuyProductShelfBaseDao;
	public void setDredgeBillHasEbuyProductShelfBaseDao(IDredgeBillHasEbuyProductShelfBaseDao dredgeBillHasEbuyProductShelfBaseDao) {
		this.dredgeBillHasEbuyProductShelfBaseDao = dredgeBillHasEbuyProductShelfBaseDao;
	}
	/**
	 * 根据条件查询(用户自选耗材明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap){
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户自选耗材明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByConditionDim(Map<String,Object> paramMap){
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户自选耗材明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户自选耗材明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户自选耗材明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasEbuyProductShelf getDredgeBillHasEbuyProductShelfBySeqId(java.math.BigInteger id){
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasEbuyProductShelfCount(Map<String,Object> paramMap){
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillHasEbuyProductShelfCountDim(Map<String,Object> paramMap){
		return dredgeBillHasEbuyProductShelfBaseDao.selectDredgeBillHasEbuyProductShelfCount(paramMap,true);
	}
	/**
	 * 往(用户自选耗材明细)新增一条记录
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	@Override
	public int insertDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf){
		return dredgeBillHasEbuyProductShelfBaseDao.insertDredgeBillHasEbuyProductShelf(dredgeBillHasEbuyProductShelf);
	}
	/**
	 * 批量新增(用户自选耗材明细)
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList){
		return dredgeBillHasEbuyProductShelfBaseDao.insertDredgeBillHasEbuyProductShelfBatch(dredgeBillHasEbuyProductShelfList);
	}
	/**
	 * 更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	@Override
	public int updateDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf){
		return dredgeBillHasEbuyProductShelfBaseDao.updateDredgeBillHasEbuyProductShelf(dredgeBillHasEbuyProductShelf);
	}
	/**
	 * 批量更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList){
		return dredgeBillHasEbuyProductShelfBaseDao.updateDredgeBillHasEbuyProductShelfBatch(dredgeBillHasEbuyProductShelfList);
	}
	/**
	 * 根据序列号删除(用户自选耗材明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasEbuyProductShelfLogic(java.math.BigInteger id){
		return dredgeBillHasEbuyProductShelfBaseDao.deleteDredgeBillHasEbuyProductShelfLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户自选耗材明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillHasEbuyProductShelfBaseDao.deleteDredgeBillHasEbuyProductShelfLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户自选耗材明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasEbuyProductShelf(java.math.BigInteger id){
//		return dredgeBillHasEbuyProductShelfBaseDao.deleteDredgeBillHasEbuyProductShelf(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户自选耗材明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasEbuyProductShelfBatch(List<java.math.BigInteger> idList){
//		return dredgeBillHasEbuyProductShelfBaseDao.deleteDredgeBillHasEbuyProductShelfBatch(idList);
//	}
	
}
