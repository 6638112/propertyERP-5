package com.cnfantasia.server.domainbase.microblogReport.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;

/**
 * 描述:(微博信息举报表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogReportBaseDao {
	/**
	 * 根据条件查询(微博信息举报表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogReport> selectMicroblogReportByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(微博信息举报表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogReport> selectMicroblogReportByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(微博信息举报表)信息
	 * @param id
	 * @return
	 */
	public MicroblogReport selectMicroblogReportBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMicroblogReportCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(微博信息举报表)新增一条记录
	 * @param microblogReport
	 * @return
	 */
	public int insertMicroblogReport(MicroblogReport microblogReport);
	
	/**
	 * 批量新增(微博信息举报表)信息
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
	 * 根据Id 批量删除(微博信息举报表)信息_逻辑删除
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
