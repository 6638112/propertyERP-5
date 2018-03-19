package com.cnfantasia.server.domainbase.microblogReport.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;

/**
 * 描述:(微博信息举报表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogReportBaseService {
	
	/**
	 * 根据条件查询(微博信息举报表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogReport> getMicroblogReportByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微博信息举报表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogReport> getMicroblogReportByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微博信息举报表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogReport> getMicroblogReportByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微博信息举报表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogReport> getMicroblogReportByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微博信息举报表)信息
	 * @param id
	 * @return
	 */
	public MicroblogReport getMicroblogReportBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogReportCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogReportCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微博信息举报表)新增一条记录
	 * @param microblogReport
	 * @return
	 */
	public int insertMicroblogReport(MicroblogReport microblogReport);
	/**
	 * 批量新增(微博信息举报表)
	 * @param microblogReportList
	 * @return
	 */
	public int insertMicroblogReportBatch(List<MicroblogReport> microblogReportList);
	/**
	 * 更新(微博信息举报表)信息
	 * @param microblogReport
	 * @return
	 */
	public int updateMicroblogReport(MicroblogReport microblogReport);
	/**
	 * 批量更新(微博信息举报表)信息
	 * @param microblogReportList
	 * @return
	 */
	public int updateMicroblogReportBatch(List<MicroblogReport> microblogReportList);
	/**
	 * 根据序列号删除(微博信息举报表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogReportLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微博信息举报表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogReportLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微博信息举报表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogReport(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微博信息举报表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogReportBatch(List<java.math.BigInteger> idList);
	
}
