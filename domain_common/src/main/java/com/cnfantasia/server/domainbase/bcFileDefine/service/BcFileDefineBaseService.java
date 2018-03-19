package com.cnfantasia.server.domainbase.bcFileDefine.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcFileDefine.dao.IBcFileDefineBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;

/**
 * 描述:(出盘回盘文件格式定义) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcFileDefineBaseService implements IBcFileDefineBaseService{
	
	private IBcFileDefineBaseDao bcFileDefineBaseDao;
	public void setBcFileDefineBaseDao(IBcFileDefineBaseDao bcFileDefineBaseDao) {
		this.bcFileDefineBaseDao = bcFileDefineBaseDao;
	}
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcFileDefine> getBcFileDefineByCondition(Map<String,Object> paramMap){
		return bcFileDefineBaseDao.selectBcFileDefineByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcFileDefine> getBcFileDefineByConditionDim(Map<String,Object> paramMap){
		return bcFileDefineBaseDao.selectBcFileDefineByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcFileDefine> getBcFileDefineByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcFileDefineBaseDao.selectBcFileDefineByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcFileDefine> getBcFileDefineByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcFileDefineBaseDao.selectBcFileDefineByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(出盘回盘文件格式定义)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcFileDefine getBcFileDefineBySeqId(java.math.BigInteger id){
		return bcFileDefineBaseDao.selectBcFileDefineBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcFileDefineCount(Map<String,Object> paramMap){
		return bcFileDefineBaseDao.selectBcFileDefineCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcFileDefineCountDim(Map<String,Object> paramMap){
		return bcFileDefineBaseDao.selectBcFileDefineCount(paramMap,true);
	}
	/**
	 * 往(出盘回盘文件格式定义)新增一条记录
	 * @param bcFileDefine
	 * @return
	 */
	@Override
	public int insertBcFileDefine(BcFileDefine bcFileDefine){
		return bcFileDefineBaseDao.insertBcFileDefine(bcFileDefine);
	}
	/**
	 * 批量新增(出盘回盘文件格式定义)
	 * @param bcFileDefineList
	 * @return
	 */
	@Override
	public int insertBcFileDefineBatch(List<BcFileDefine> bcFileDefineList){
		return bcFileDefineBaseDao.insertBcFileDefineBatch(bcFileDefineList);
	}
	/**
	 * 更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefine
	 * @return
	 */
	@Override
	public int updateBcFileDefine(BcFileDefine bcFileDefine){
		return bcFileDefineBaseDao.updateBcFileDefine(bcFileDefine);
	}
	/**
	 * 批量更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefineList
	 * @return
	 */
	@Override
	public int updateBcFileDefineBatch(List<BcFileDefine> bcFileDefineList){
		return bcFileDefineBaseDao.updateBcFileDefineBatch(bcFileDefineList);
	}
	/**
	 * 根据序列号删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcFileDefineLogic(java.math.BigInteger id){
		return bcFileDefineBaseDao.deleteBcFileDefineLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcFileDefineLogicBatch(List<java.math.BigInteger> idList){
		return bcFileDefineBaseDao.deleteBcFileDefineLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(出盘回盘文件格式定义)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcFileDefine(java.math.BigInteger id){
//		return bcFileDefineBaseDao.deleteBcFileDefine(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(出盘回盘文件格式定义)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcFileDefineBatch(List<java.math.BigInteger> idList){
//		return bcFileDefineBaseDao.deleteBcFileDefineBatch(idList);
//	}
	
}
