package com.cnfantasia.server.domainbase.bcFinanceOrg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcFinanceOrg.dao.IBcFinanceOrgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;

/**
 * 描述:(银行托收金融机构信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcFinanceOrgBaseService implements IBcFinanceOrgBaseService{
	
	private IBcFinanceOrgBaseDao bcFinanceOrgBaseDao;
	public void setBcFinanceOrgBaseDao(IBcFinanceOrgBaseDao bcFinanceOrgBaseDao) {
		this.bcFinanceOrgBaseDao = bcFinanceOrgBaseDao;
	}
	/**
	 * 根据条件查询(银行托收金融机构信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> getBcFinanceOrgByCondition(Map<String,Object> paramMap){
		return bcFinanceOrgBaseDao.selectBcFinanceOrgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(银行托收金融机构信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> getBcFinanceOrgByConditionDim(Map<String,Object> paramMap){
		return bcFinanceOrgBaseDao.selectBcFinanceOrgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> getBcFinanceOrgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcFinanceOrgBaseDao.selectBcFinanceOrgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> getBcFinanceOrgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcFinanceOrgBaseDao.selectBcFinanceOrgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(银行托收金融机构信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcFinanceOrg getBcFinanceOrgBySeqId(java.math.BigInteger id){
		return bcFinanceOrgBaseDao.selectBcFinanceOrgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcFinanceOrgCount(Map<String,Object> paramMap){
		return bcFinanceOrgBaseDao.selectBcFinanceOrgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcFinanceOrgCountDim(Map<String,Object> paramMap){
		return bcFinanceOrgBaseDao.selectBcFinanceOrgCount(paramMap,true);
	}
	/**
	 * 往(银行托收金融机构信息)新增一条记录
	 * @param bcFinanceOrg
	 * @return
	 */
	@Override
	public int insertBcFinanceOrg(BcFinanceOrg bcFinanceOrg){
		return bcFinanceOrgBaseDao.insertBcFinanceOrg(bcFinanceOrg);
	}
	/**
	 * 批量新增(银行托收金融机构信息)
	 * @param bcFinanceOrgList
	 * @return
	 */
	@Override
	public int insertBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList){
		return bcFinanceOrgBaseDao.insertBcFinanceOrgBatch(bcFinanceOrgList);
	}
	/**
	 * 更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrg
	 * @return
	 */
	@Override
	public int updateBcFinanceOrg(BcFinanceOrg bcFinanceOrg){
		return bcFinanceOrgBaseDao.updateBcFinanceOrg(bcFinanceOrg);
	}
	/**
	 * 批量更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrgList
	 * @return
	 */
	@Override
	public int updateBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList){
		return bcFinanceOrgBaseDao.updateBcFinanceOrgBatch(bcFinanceOrgList);
	}
	/**
	 * 根据序列号删除(银行托收金融机构信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcFinanceOrgLogic(java.math.BigInteger id){
		return bcFinanceOrgBaseDao.deleteBcFinanceOrgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(银行托收金融机构信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcFinanceOrgLogicBatch(List<java.math.BigInteger> idList){
		return bcFinanceOrgBaseDao.deleteBcFinanceOrgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(银行托收金融机构信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcFinanceOrg(java.math.BigInteger id){
//		return bcFinanceOrgBaseDao.deleteBcFinanceOrg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行托收金融机构信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcFinanceOrgBatch(List<java.math.BigInteger> idList){
//		return bcFinanceOrgBaseDao.deleteBcFinanceOrgBatch(idList);
//	}
	
}
