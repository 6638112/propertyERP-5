package com.cnfantasia.server.domainbase.htCarGb.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.htCarGb.dao.IHtCarGbBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 描述:(华庭车禁小区表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class HtCarGbBaseService implements IHtCarGbBaseService{
	
	private IHtCarGbBaseDao htCarGbBaseDao;
	public void setHtCarGbBaseDao(IHtCarGbBaseDao htCarGbBaseDao) {
		this.htCarGbBaseDao = htCarGbBaseDao;
	}
	/**
	 * 根据条件查询(华庭车禁小区表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<HtCarGb> getHtCarGbByCondition(Map<String,Object> paramMap){
		return htCarGbBaseDao.selectHtCarGbByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(华庭车禁小区表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<HtCarGb> getHtCarGbByConditionDim(Map<String,Object> paramMap){
		return htCarGbBaseDao.selectHtCarGbByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(华庭车禁小区表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<HtCarGb> getHtCarGbByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return htCarGbBaseDao.selectHtCarGbByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(华庭车禁小区表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<HtCarGb> getHtCarGbByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return htCarGbBaseDao.selectHtCarGbByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(华庭车禁小区表)信息
	 * @param id
	 * @return
	 */
	@Override
	public HtCarGb getHtCarGbBySeqId(java.math.BigInteger id){
		return htCarGbBaseDao.selectHtCarGbBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(华庭车禁小区表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHtCarGbCount(Map<String,Object> paramMap){
		return htCarGbBaseDao.selectHtCarGbCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(华庭车禁小区表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHtCarGbCountDim(Map<String,Object> paramMap){
		return htCarGbBaseDao.selectHtCarGbCount(paramMap,true);
	}
	/**
	 * 往(华庭车禁小区表)新增一条记录
	 * @param htCarGb
	 * @return
	 */
	@Override
	public int insertHtCarGb(HtCarGb htCarGb){
		return htCarGbBaseDao.insertHtCarGb(htCarGb);
	}
	/**
	 * 批量新增(华庭车禁小区表)
	 * @param htCarGbList
	 * @return
	 */
	@Override
	public int insertHtCarGbBatch(List<HtCarGb> htCarGbList){
		return htCarGbBaseDao.insertHtCarGbBatch(htCarGbList);
	}
	/**
	 * 更新(华庭车禁小区表)信息
	 * @param htCarGb
	 * @return
	 */
	@Override
	public int updateHtCarGb(HtCarGb htCarGb){
		return htCarGbBaseDao.updateHtCarGb(htCarGb);
	}
	/**
	 * 批量更新(华庭车禁小区表)信息
	 * @param htCarGbList
	 * @return
	 */
	@Override
	public int updateHtCarGbBatch(List<HtCarGb> htCarGbList){
		return htCarGbBaseDao.updateHtCarGbBatch(htCarGbList);
	}
	/**
	 * 根据序列号删除(华庭车禁小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHtCarGbLogic(java.math.BigInteger id){
		return htCarGbBaseDao.deleteHtCarGbLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(华庭车禁小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHtCarGbLogicBatch(List<java.math.BigInteger> idList){
		return htCarGbBaseDao.deleteHtCarGbLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(华庭车禁小区表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHtCarGb(java.math.BigInteger id){
//		return htCarGbBaseDao.deleteHtCarGb(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华庭车禁小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHtCarGbBatch(List<java.math.BigInteger> idList){
//		return htCarGbBaseDao.deleteHtCarGbBatch(idList);
//	}
	
}
