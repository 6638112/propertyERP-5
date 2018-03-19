package com.cnfantasia.server.domainbase.omsUserBankInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserBankInfo.entity.OmsUserBankInfo;

/**
 * 描述:(OMS用户银行信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsUserBankInfoBaseDao extends AbstractBaseDao implements IOmsUserBankInfoBaseDao{
	/**
	 * 根据条件查询(OMS用户银行信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> selectOmsUserBankInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsUserBankInfoBase.select_omsUserBankInfo",paramMap);
	}
	/**
	 * 按条件分页查询(OMS用户银行信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserBankInfo> selectOmsUserBankInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsUserBankInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsUserBankInfo> resMap= sqlSession.selectList("omsUserBankInfoBase.select_omsUserBankInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS用户银行信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserBankInfo selectOmsUserBankInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsUserBankInfoBase.select_omsUserBankInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户银行信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsUserBankInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsUserBankInfoBase.select_omsUserBankInfo_count", paramMap);
	}
	/**
	 * 往(OMS用户银行信息表)新增一条记录
	 * @param omsUserBankInfo
	 * @return
	 */
	@Override
	public int insertOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo){
		return sqlSession.insert("omsUserBankInfoBase.insert_omsUserBankInfo",omsUserBankInfo);
	}
	/**
	 * 批量新增(OMS用户银行信息表)信息
	 * @param omsUserBankInfoList
	 * @return
	 */
	@Override
	public int insertOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList) {
		if (omsUserBankInfoList == null || omsUserBankInfoList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = omsUserBankInfoList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<OmsUserBankInfo> batchList = omsUserBankInfoList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("omsUserBankInfoBase.insert_omsUserBankInfo_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfo
	 * @return
	 */
	@Override
	public int updateOmsUserBankInfo(OmsUserBankInfo omsUserBankInfo){
		return sqlSession.update("omsUserBankInfoBase.update_omsUserBankInfo", omsUserBankInfo);
	}
	/**
	 * 批量更新(OMS用户银行信息表)信息
	 * @param omsUserBankInfoList
	 * @return
	 */
	@Override
	public int updateOmsUserBankInfoBatch(List<OmsUserBankInfo> omsUserBankInfoList) {
		if (omsUserBankInfoList == null || omsUserBankInfoList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("omsUserBankInfoBase.update_omsUserBankInfo_Batch", omsUserBankInfoList);
	}
	
	/**
	 * 根据序列号删除(OMS用户银行信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserBankInfoLogic(java.math.BigInteger id){
		OmsUserBankInfo omsUserBankInfo = new OmsUserBankInfo();
		omsUserBankInfo.setId(id);
		omsUserBankInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsUserBankInfoBase.delete_omsUserBankInfo_Logic",omsUserBankInfo);
	}
	
	/**
	 * 根据Id 批量删除(OMS用户银行信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserBankInfoLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<OmsUserBankInfo> delList = new java.util.ArrayList<OmsUserBankInfo>();
		for(java.math.BigInteger id:idList){
			OmsUserBankInfo omsUserBankInfo = new OmsUserBankInfo();
			omsUserBankInfo.setId(id);
			omsUserBankInfo.setSys0DelState(RecordState_DELETED);
			delList.add(omsUserBankInfo);
		}
		return sqlSession.update("omsUserBankInfoBase.delete_omsUserBankInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户银行信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBankInfo(java.math.BigInteger id){
//		return sqlSession.delete("omsUserBankInfoBase.delete_omsUserBankInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户银行信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBankInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsUserBankInfoBase.delete_omsUserBankInfo_Batch", idList);
//	}
	
	
}
