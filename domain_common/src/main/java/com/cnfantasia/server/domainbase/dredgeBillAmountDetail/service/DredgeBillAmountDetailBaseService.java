package com.cnfantasia.server.domainbase.dredgeBillAmountDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeBillAmountDetail.dao.IDredgeBillAmountDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;

/**
 * 描述:(上门维修费用明细) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeBillAmountDetailBaseService implements IDredgeBillAmountDetailBaseService{
	
	private IDredgeBillAmountDetailBaseDao dredgeBillAmountDetailBaseDao;
	public void setDredgeBillAmountDetailBaseDao(IDredgeBillAmountDetailBaseDao dredgeBillAmountDetailBaseDao) {
		this.dredgeBillAmountDetailBaseDao = dredgeBillAmountDetailBaseDao;
	}
	/**
	 * 根据条件查询(上门维修费用明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByCondition(Map<String,Object> paramMap){
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(上门维修费用明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByConditionDim(Map<String,Object> paramMap){
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(上门维修费用明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(上门维修费用明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(上门维修费用明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillAmountDetail getDredgeBillAmountDetailBySeqId(java.math.BigInteger id){
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(上门维修费用明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillAmountDetailCount(Map<String,Object> paramMap){
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(上门维修费用明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeBillAmountDetailCountDim(Map<String,Object> paramMap){
		return dredgeBillAmountDetailBaseDao.selectDredgeBillAmountDetailCount(paramMap,true);
	}
	/**
	 * 往(上门维修费用明细)新增一条记录
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	@Override
	public int insertDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail){
		return dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetail(dredgeBillAmountDetail);
	}
	/**
	 * 批量新增(上门维修费用明细)
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	@Override
	public int insertDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList){
		return dredgeBillAmountDetailBaseDao.insertDredgeBillAmountDetailBatch(dredgeBillAmountDetailList);
	}
	/**
	 * 更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	@Override
	public int updateDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail){
		return dredgeBillAmountDetailBaseDao.updateDredgeBillAmountDetail(dredgeBillAmountDetail);
	}
	/**
	 * 批量更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	@Override
	public int updateDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList){
		return dredgeBillAmountDetailBaseDao.updateDredgeBillAmountDetailBatch(dredgeBillAmountDetailList);
	}
	/**
	 * 根据序列号删除(上门维修费用明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillAmountDetailLogic(java.math.BigInteger id){
		return dredgeBillAmountDetailBaseDao.deleteDredgeBillAmountDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(上门维修费用明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillAmountDetailLogicBatch(List<java.math.BigInteger> idList){
		return dredgeBillAmountDetailBaseDao.deleteDredgeBillAmountDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(上门维修费用明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillAmountDetail(java.math.BigInteger id){
//		return dredgeBillAmountDetailBaseDao.deleteDredgeBillAmountDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门维修费用明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillAmountDetailBatch(List<java.math.BigInteger> idList){
//		return dredgeBillAmountDetailBaseDao.deleteDredgeBillAmountDetailBatch(idList);
//	}
	
}
