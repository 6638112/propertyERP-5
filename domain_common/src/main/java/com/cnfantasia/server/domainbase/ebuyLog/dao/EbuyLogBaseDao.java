package com.cnfantasia.server.domainbase.ebuyLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyLog.entity.EbuyLog;

/**
 * 描述:(扫二维码进商品详情日志表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyLogBaseDao extends AbstractBaseDao implements IEbuyLogBaseDao{
	/**
	 * 根据条件查询(扫二维码进商品详情日志表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyLog> selectEbuyLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyLogBase.select_ebuyLog",paramMap);
	}
	/**
	 * 按条件分页查询(扫二维码进商品详情日志表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyLog> selectEbuyLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyLog> resMap= sqlSession.selectList("ebuyLogBase.select_ebuyLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(扫二维码进商品详情日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyLog selectEbuyLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyLogBase.select_ebuyLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(扫二维码进商品详情日志表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyLogBase.select_ebuyLog_count", paramMap);
	}
	/**
	 * 往(扫二维码进商品详情日志表)新增一条记录
	 * @param ebuyLog
	 * @return
	 */
	@Override
	public int insertEbuyLog(EbuyLog ebuyLog){
		return sqlSession.insert("ebuyLogBase.insert_ebuyLog",ebuyLog);
	}
	/**
	 * 批量新增(扫二维码进商品详情日志表)信息
	 * @param ebuyLogList
	 * @return
	 */
	@Override
	public int insertEbuyLogBatch(List<EbuyLog> ebuyLogList) {
		return sqlSession.insert("ebuyLogBase.insert_ebuyLog_Batch",ebuyLogList);
	}
	
	/**
	 * 更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLog
	 * @return
	 */
	@Override
	public int updateEbuyLog(EbuyLog ebuyLog){
		return sqlSession.update("ebuyLogBase.update_ebuyLog", ebuyLog);
	}
	/**
	 * 批量更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLogList
	 * @return
	 */
	@Override
	public int updateEbuyLogBatch(List<EbuyLog> ebuyLogList) {
		return sqlSession.update("ebuyLogBase.update_ebuyLog_Batch", ebuyLogList);
	}
	
	/**
	 * 根据序列号删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyLogLogic(java.math.BigInteger id){
		EbuyLog ebuyLog = new EbuyLog();
		ebuyLog.setId(id);
		ebuyLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyLogBase.delete_ebuyLog_Logic",ebuyLog);
	}
	
	/**
	 * 根据Id 批量删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyLogLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyLog> delList = new java.util.ArrayList<EbuyLog>();
		for(java.math.BigInteger id:idList){
			EbuyLog ebuyLog = new EbuyLog();
			ebuyLog.setId(id);
			ebuyLog.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyLog);
		}
		return sqlSession.update("ebuyLogBase.delete_ebuyLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(扫二维码进商品详情日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyLog(java.math.BigInteger id){
//		return sqlSession.delete("ebuyLogBase.delete_ebuyLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(扫二维码进商品详情日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyLogBase.delete_ebuyLog_Batch", idList);
//	}
	
	
}
