package com.cnfantasia.server.domainbase.microblogReport.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogReport.dao.IMicroblogReportBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;

/**
 * 描述:(微博信息举报表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogReportBaseService implements IMicroblogReportBaseService{
	
	private IMicroblogReportBaseDao microblogReportBaseDao;
	public void setMicroblogReportBaseDao(IMicroblogReportBaseDao microblogReportBaseDao) {
		this.microblogReportBaseDao = microblogReportBaseDao;
	}
	/**
	 * 根据条件查询(微博信息举报表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogReport> getMicroblogReportByCondition(Map<String,Object> paramMap){
		return microblogReportBaseDao.selectMicroblogReportByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微博信息举报表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogReport> getMicroblogReportByConditionDim(Map<String,Object> paramMap){
		return microblogReportBaseDao.selectMicroblogReportByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微博信息举报表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogReport> getMicroblogReportByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogReportBaseDao.selectMicroblogReportByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微博信息举报表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogReport> getMicroblogReportByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogReportBaseDao.selectMicroblogReportByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微博信息举报表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogReport getMicroblogReportBySeqId(java.math.BigInteger id){
		return microblogReportBaseDao.selectMicroblogReportBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogReportCount(Map<String,Object> paramMap){
		return microblogReportBaseDao.selectMicroblogReportCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogReportCountDim(Map<String,Object> paramMap){
		return microblogReportBaseDao.selectMicroblogReportCount(paramMap,true);
	}
	/**
	 * 往(微博信息举报表)新增一条记录
	 * @param microblogReport
	 * @return
	 */
	@Override
	public int insertMicroblogReport(MicroblogReport microblogReport){
		return microblogReportBaseDao.insertMicroblogReport(microblogReport);
	}
	/**
	 * 批量新增(微博信息举报表)
	 * @param microblogReportList
	 * @return
	 */
	@Override
	public int insertMicroblogReportBatch(List<MicroblogReport> microblogReportList){
		return microblogReportBaseDao.insertMicroblogReportBatch(microblogReportList);
	}
	/**
	 * 更新(微博信息举报表)信息
	 * @param microblogReport
	 * @return
	 */
	@Override
	public int updateMicroblogReport(MicroblogReport microblogReport){
		return microblogReportBaseDao.updateMicroblogReport(microblogReport);
	}
	/**
	 * 批量更新(微博信息举报表)信息
	 * @param microblogReportList
	 * @return
	 */
	@Override
	public int updateMicroblogReportBatch(List<MicroblogReport> microblogReportList){
		return microblogReportBaseDao.updateMicroblogReportBatch(microblogReportList);
	}
	/**
	 * 根据序列号删除(微博信息举报表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogReportLogic(java.math.BigInteger id){
		return microblogReportBaseDao.deleteMicroblogReportLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微博信息举报表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogReportLogicBatch(List<java.math.BigInteger> idList){
		return microblogReportBaseDao.deleteMicroblogReportLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微博信息举报表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogReport(java.math.BigInteger id){
//		return microblogReportBaseDao.deleteMicroblogReport(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博信息举报表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogReportBatch(List<java.math.BigInteger> idList){
//		return microblogReportBaseDao.deleteMicroblogReportBatch(idList);
//	}
	
}
