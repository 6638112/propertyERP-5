package com.cnfantasia.server.domainbase.microblogReport.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogReport.entity.MicroblogReport;

/**
 * 描述:(微博信息举报表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogReportBaseDao extends AbstractBaseDao implements IMicroblogReportBaseDao{
	/**
	 * 根据条件查询(微博信息举报表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogReport> selectMicroblogReportByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogReportBase.select_microblogReport",paramMap);
	}
	/**
	 * 按条件分页查询(微博信息举报表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogReport> selectMicroblogReportByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogReportCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogReport> resMap= sqlSession.selectList("microblogReportBase.select_microblogReport_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微博信息举报表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogReport selectMicroblogReportBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogReportBase.select_microblogReport_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微博信息举报表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogReportCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogReportBase.select_microblogReport_count", paramMap);
	}
	/**
	 * 往(微博信息举报表)新增一条记录
	 * @param microblogReport
	 * @return
	 */
	@Override
	public int insertMicroblogReport(MicroblogReport microblogReport){
		return sqlSession.insert("microblogReportBase.insert_microblogReport",microblogReport);
	}
	/**
	 * 批量新增(微博信息举报表)信息
	 * @param microblogReportList
	 * @return
	 */
	@Override
	public int insertMicroblogReportBatch(List<MicroblogReport> microblogReportList) {
		return sqlSession.insert("microblogReportBase.insert_microblogReport_Batch",microblogReportList);
	}
	
	/**
	 * 更新(微博信息举报表)信息
	 * @param microblogReport
	 * @return
	 */
	@Override
	public int updateMicroblogReport(MicroblogReport microblogReport){
		return sqlSession.update("microblogReportBase.update_microblogReport", microblogReport);
	}
	/**
	 * 批量更新(微博信息举报表)信息
	 * @param microblogReportList
	 * @return
	 */
	@Override
	public int updateMicroblogReportBatch(List<MicroblogReport> microblogReportList) {
		return sqlSession.update("microblogReportBase.update_microblogReport_Batch", microblogReportList);
	}
	
	/**
	 * 根据序列号删除(微博信息举报表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogReportLogic(java.math.BigInteger id){
		MicroblogReport microblogReport = new MicroblogReport();
		microblogReport.setId(id);
		microblogReport.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogReportBase.delete_microblogReport_Logic",microblogReport);
	}
	
	/**
	 * 根据Id 批量删除(微博信息举报表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogReportLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogReport> delList = new java.util.ArrayList<MicroblogReport>();
		for(java.math.BigInteger id:idList){
			MicroblogReport microblogReport = new MicroblogReport();
			microblogReport.setId(id);
			microblogReport.setSys0DelState(RecordState_DELETED);
			delList.add(microblogReport);
		}
		return sqlSession.update("microblogReportBase.delete_microblogReport_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微博信息举报表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogReport(java.math.BigInteger id){
//		return sqlSession.delete("microblogReportBase.delete_microblogReport", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博信息举报表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogReportBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogReportBase.delete_microblogReport_Batch", idList);
//	}
	
	
}
