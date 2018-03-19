package com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.dao.IBcInfoHasTPropertyProprietorBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.entity.BcInfoHasTPropertyProprietor;

/**
 * 描述:(物业公司托收银行信息包含的业主) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcInfoHasTPropertyProprietorBaseService implements IBcInfoHasTPropertyProprietorBaseService{
	
	private IBcInfoHasTPropertyProprietorBaseDao bcInfoHasTPropertyProprietorBaseDao;
	public void setBcInfoHasTPropertyProprietorBaseDao(IBcInfoHasTPropertyProprietorBaseDao bcInfoHasTPropertyProprietorBaseDao) {
		this.bcInfoHasTPropertyProprietorBaseDao = bcInfoHasTPropertyProprietorBaseDao;
	}
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap){
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap){
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的业主)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcInfoHasTPropertyProprietor getBcInfoHasTPropertyProprietorBySeqId(java.math.BigInteger id){
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcInfoHasTPropertyProprietorCount(Map<String,Object> paramMap){
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcInfoHasTPropertyProprietorCountDim(Map<String,Object> paramMap){
		return bcInfoHasTPropertyProprietorBaseDao.selectBcInfoHasTPropertyProprietorCount(paramMap,true);
	}
	/**
	 * 往(物业公司托收银行信息包含的业主)新增一条记录
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int insertBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor){
		return bcInfoHasTPropertyProprietorBaseDao.insertBcInfoHasTPropertyProprietor(bcInfoHasTPropertyProprietor);
	}
	/**
	 * 批量新增(物业公司托收银行信息包含的业主)
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int insertBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList){
		return bcInfoHasTPropertyProprietorBaseDao.insertBcInfoHasTPropertyProprietorBatch(bcInfoHasTPropertyProprietorList);
	}
	/**
	 * 更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int updateBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor){
		return bcInfoHasTPropertyProprietorBaseDao.updateBcInfoHasTPropertyProprietor(bcInfoHasTPropertyProprietor);
	}
	/**
	 * 批量更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int updateBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList){
		return bcInfoHasTPropertyProprietorBaseDao.updateBcInfoHasTPropertyProprietorBatch(bcInfoHasTPropertyProprietorList);
	}
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTPropertyProprietorLogic(java.math.BigInteger id){
		return bcInfoHasTPropertyProprietorBaseDao.deleteBcInfoHasTPropertyProprietorLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTPropertyProprietorLogicBatch(List<java.math.BigInteger> idList){
		return bcInfoHasTPropertyProprietorBaseDao.deleteBcInfoHasTPropertyProprietorLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTPropertyProprietor(java.math.BigInteger id){
//		return bcInfoHasTPropertyProprietorBaseDao.deleteBcInfoHasTPropertyProprietor(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的业主)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTPropertyProprietorBatch(List<java.math.BigInteger> idList){
//		return bcInfoHasTPropertyProprietorBaseDao.deleteBcInfoHasTPropertyProprietorBatch(idList);
//	}
	
}
